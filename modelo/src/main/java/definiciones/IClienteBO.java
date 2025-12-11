/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package definiciones;

import dominio.ClienteDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IClienteBO {
    
    public ClienteDTO obtenerClientePorId(Long idCliente) throws NegocioException, PersistenciaException;
    
    public ClienteDTO editarCliente(ClienteDTO clienteDTO) throws NegocioException, PersistenciaException;
}
