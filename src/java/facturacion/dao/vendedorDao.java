/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.dao;

import java.util.List;
import facturacion.model.Vendedor;

/**
 *
 * @author admin01
 */
public interface vendedorDao {
  public List<Vendedor> listarVendedor(); 
  public void newVendedor(Vendedor vendedor);
  public void updateVendedor(Vendedor vendedor);
  public void deleteVendedor(Vendedor vendedor);
  
    
}
