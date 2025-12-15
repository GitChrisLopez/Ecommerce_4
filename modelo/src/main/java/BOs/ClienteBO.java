package BOs;

import DAOs.UsuarioDAO;
import definiciones.IClienteBO;
import definiciones.IClienteDAO;
import dominio.ClienteDTO;
import entidades.Cliente;
import entidades.Usuario;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import mappers.MapperCliente;

/**
 *
 * @author norma
 */
public class ClienteBO implements IClienteBO {

    private IClienteDAO clienteDAO;
    private UsuarioDAO usuarioDAO;

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        this.usuarioDAO = new UsuarioDAO();
    }

    public ClienteDTO iniciarSesion(String correo, String contrasenia) {

        if (correo == null || correo.isBlank() || contrasenia == null || contrasenia.isBlank()) {
            System.out.println("Correo o contraseña vacíos");
            return null;
        }

        Cliente clienteEntity = clienteDAO.iniciarSesion(correo, contrasenia);

        if (clienteEntity == null) {
            return null;
        }
        // convertir ENTIDAD a DOMINIO
        return mappers.MapperCliente.toDto(clienteEntity);
    }
    
    @Override
    public ClienteDTO obtenerClientePorId(Long idCliente) throws NegocioException, PersistenciaException {
        if (idCliente == null || idCliente <= 0) {
            throw new NegocioException("El ID dell cliente no puede ser menor a 0 o null.");
        }

        try {
            Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);

            if (cliente == null) {
                throw new NegocioException("No se encontró el cliente con ID: " + idCliente);
            }

            return MapperCliente.toDto(cliente);

        } catch (PersistenciaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new NegocioException("Error al obtener el cliente.", ex);
        }
    }

    @Override
    public ClienteDTO editarCliente(ClienteDTO clienteDTO) throws NegocioException, PersistenciaException {
        if (clienteDTO == null || clienteDTO.getId() == null || clienteDTO.getId() <= 0) {
            throw new NegocioException("Objeto Cliente a editar es inválido o no tiene ID.");
        }
        if (clienteDTO.getCorreo() == null || clienteDTO.getCorreo().trim().isEmpty()) {
            throw new NegocioException("El correo electrónico del cliente no puede estar vacío.");
        }

        try {
            Cliente clienteExistente = clienteDAO.obtenerClientePorId(clienteDTO.getId());
            if (clienteExistente == null) {
                throw new NegocioException("El cliente con ID " + clienteDTO.getId() + " no existe.");
            }

            String nuevoCorreo = clienteDTO.getCorreo().trim();
            String correoActual = clienteExistente.getCorreo().trim();

            if (!nuevoCorreo.equalsIgnoreCase(correoActual)) {
                Usuario usuarioPorCorreo = usuarioDAO.obtenerUsuarioPorCorreo(nuevoCorreo);

                if (usuarioPorCorreo != null && !usuarioPorCorreo.getId().equals(clienteDTO.getId())) {
                    throw new NegocioException("Ya existe un usuario con el mismo correo electrónico.");
                }
            }

            Cliente clienteCompleto = MapperCliente.toEntity(clienteDTO);
            
            clienteExistente.setNombre(clienteCompleto.getNombre());
            clienteExistente.setApellidoPaterno(clienteCompleto.getApellidoPaterno());
            clienteExistente.setApellidoMaterno(clienteCompleto.getApellidoMaterno());
            clienteExistente.setCorreo(clienteCompleto.getCorreo());
            clienteExistente.setTelefono(clienteCompleto.getTelefono());
            clienteExistente.setUrlImagenPerfil(clienteCompleto.getUrlImagenPerfil());
            
            Cliente clienteActualizado = clienteDAO.editarCliente(clienteExistente);

            if (clienteActualizado == null) {
                throw new NegocioException("La edición del cliente no tuvo éxito.");
            }

            return MapperCliente.toDto(clienteActualizado);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en la capa de persistencia al editar el cliente.", ex);
        } catch (NegocioException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new NegocioException("Error inesperado al editar el cliente.", ex);
        }
    }
}
