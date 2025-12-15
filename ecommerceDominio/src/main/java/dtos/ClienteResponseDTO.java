/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import dominio.CarritoDTO;
import dominio.DireccionDTO;
import dominio.PedidoDTO;
import java.util.List;

/**
 *
 * @author norma
 */
public class ClienteResponseDTO {

    /**
     * Dato Long que representa el Id del usuario.
     */
    private Long id;

    /**
     * Dato String que representa el o los nombres del Usuario.
     */
    private String nombre;

    /**
     * Dato String que representa el apellido paterno del Usuario.
     */
    private String apellidoPaterno;

    /**
     * Dato String que representa el apellido materno del Usuario.
     */
    private String apellidoMaterno;

    /**
     * Dato String que representa el correo electrónico del Usuario.
     */
    private String correo;

    /**
     * Objeto String que representa el número de teléfono del Cliente.
     */
    private String telefono;

    /**
     * Objeto Srtring que representa la dirección de la imagen de perfil del
     * Cliente.
     */
    private String urlImagenPerfil;

    /**
     * Dato boolean que indica si el usuario esta activo o no.
     */
    private boolean activo;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, String urlImagenPerfil, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.urlImagenPerfil = urlImagenPerfil;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlImagenPerfil() {
        return urlImagenPerfil;
    }

    public void setUrlImagenPerfil(String urlImagenPerfil) {
        this.urlImagenPerfil = urlImagenPerfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ClienteResponseDTO{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correo=" + correo + ", telefono=" + telefono + ", urlImagenPerfil=" + urlImagenPerfil + ", activo=" + activo + '}';
    }

}
