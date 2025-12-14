
package com.persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Clase que permite iniciar el entity manager, y seleccionar las propiedades
 * correspondientes al modo de ejecución (si es de pruebas o no). Además, llena
 * la base de datos con información si no la tiene.
 * 
 */
public class ManejadorConexiones {

    private static EntityManagerFactory emFactory;
    
    private static boolean conexionTest = true; 

    private static void inicializarFactory() {
        Properties propiedades = new Properties();
        
        try (InputStream inputStream = ManejadorConexiones.class.getClassLoader().getResourceAsStream("META-INF/properties.txt")) {
            if (inputStream == null) {
                throw new RuntimeException("No se encontró el archivo META-INF/properties.txt");
            }
            propiedades.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar configuración de base de datos", ex);
        }

        Properties propiedadesFiltradas = new Properties();
        String prefijo;
        String nombreUnidad;

        
        if (conexionTest) {
            prefijo = "equipo04_ecommerce_persistencia_jar_1.0PU_Tests.";
            nombreUnidad = "equipo04_ecommerce_persistencia_jar_1.0PU_Tests";
        } else {
            prefijo = "equipo04_ecommerce_persistencia_jar_1.0PU.";
            nombreUnidad = "equipo04_ecommerce_persistencia_jar_1.0PU";
        }

        for (String key : propiedades.stringPropertyNames()) {
            if (key.startsWith(prefijo)) {
                String keyPropiedad = key.substring(prefijo.length());
                propiedadesFiltradas.put(keyPropiedad, propiedades.getProperty(key));
            }
        }


        emFactory = Persistence.createEntityManagerFactory(nombreUnidad, propiedadesFiltradas);
        
        if (conexionTest) {
            poblarBaseDeDatos();
        }
    }

    public static EntityManager getEntityManager() {

        if (emFactory == null || !emFactory.isOpen()) {
            inicializarFactory();
        }
        
        return emFactory.createEntityManager();
    }
    
    private static void poblarBaseDeDatos() {

        EntityManager entityManager = emFactory.createEntityManager();

        try {
            // Si ya existen datos, se cancela la operación.
            Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM Usuario u").getSingleResult();
            if (count > 0) {
                return;
            }

            InputStream inputStream = ManejadorConexiones.class.getClassLoader().getResourceAsStream("META-INF/configuracion_bd.sql");

            if (inputStream == null) {
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String linea;
            StringBuilder sqlStatement = new StringBuilder();

            // Inicio de transacción.
            entityManager.getTransaction().begin();

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty() || linea.trim().startsWith("#")) {
                    continue;
                }

                sqlStatement.append(linea).append(" ");

                if (linea.trim().endsWith(";")) {
                    String sqlFinal = sqlStatement.toString().replace(";", "");

                    entityManager.createNativeQuery(sqlFinal).executeUpdate();

                    sqlStatement.setLength(0);
                }
            }

            // Fin de transacción.
            entityManager.getTransaction().commit();

        } catch (IOException ex) {
            
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

        } finally {
            entityManager.close();
        }
    }
    
    public static void close() {
        if (emFactory != null && emFactory.isOpen()) {
            emFactory.close();
        }
    }
    
}