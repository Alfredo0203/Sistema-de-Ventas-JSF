/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.dao;

import java.util.List;
import facturacion.model.Producto;
import org.hibernate.Session;

/**
 *
 * @author admin01
 */
public interface productoDao {
    
    //Metodos crud de productos
    public List<Producto> listarProductos();
    public void newProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
    public Producto buscarProductoPorCodBarra(Session session, String codBarra)throws Exception;
        
    
}
