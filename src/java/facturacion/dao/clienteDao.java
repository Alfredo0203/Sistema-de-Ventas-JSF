/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.dao;

import java.util.List;
import facturacion.model.Cliente;
import org.hibernate.Session;

/**
 *
 * @author admin01
 */
public interface clienteDao {
    
    //Metodos crud para cliente
    public List<Cliente> listarClientes();
    public void newCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
    
    //Este metodo es para la facturta bean
    
    public Cliente obtenerClientePorCodigo(Session session,Integer codCliente)throws Exception;
            
    
}
