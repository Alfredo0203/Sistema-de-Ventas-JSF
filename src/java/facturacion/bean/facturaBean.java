/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.bean;

import facturacion.dao.clienteDao;
import facturacion.dao.productoDao;
import facturacion.imp.clienteDaoImp;
import facturacion.imp.productoDaoImp;
import facturacion.model.Cliente;
import facturacion.model.Detallefactura;
import facturacion.model.Producto;
import facturacion.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author admin01
 */
@ManagedBean
@ViewScoped
public class facturaBean {
    
    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;
    
    private Producto producto;
    private String codBarra;
    private List<Detallefactura> listaDetalleFactura;
    
    public facturaBean() {
      this.listaDetalleFactura = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

   
    
    
    
    //Metodo para mostrar los datos de clientes mediante dialogClientes
    
    public void agregarDatosClientes(Integer  codCliente) {
        this.session = null;
        this.transaction = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cdao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            // Guardar los datos en la variable objeto cliente segun el codigo
            this.cliente = cdao.obtenerClientePorCodigo(this.session, codCliente);
            
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Datos cliente agregado"));
                    
        } catch (Exception e) {
           
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
                
            }
            
        }finally {
            if (this.session != null) {
             this.session.close();
            }
        }
    }
    
     //Metodo para mostrar los datos de clientes mediante codigo
    
    public void agregarDatosClientes2() {
        this.session = null;
        this.transaction = null;
        
        try {
            
            if (this.codigoCliente == null) {
                return;
            }
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cdao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            // Guardar los datos en la variable objeto cliente segun el codigo
            this.cliente = cdao.obtenerClientePorCodigo(this.session, this.codigoCliente);
            
            if (this.cliente != null) {
                this.codigoCliente = null;
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Datos cliente agregado"));
             
            }
            else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","No se encontró cliente"));
               
            }
            
            this.transaction.commit();
            
                    
        } catch (Exception e) {
           
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
                
            }
            
        }finally {
            if (this.session != null) {
             this.session.close();
            }
        }
    }
    
    
     //Metodo para mostrar los datos de clientes mediante dialogPruductos
    
    public void agregarDatosProductos(String  codBarra) {
        this.session = null;
        this.transaction = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pdao = new productoDaoImp();
            this.transaction = this.session.beginTransaction();
            
            // Guardar los datos en la variable objeto cliente segun el codigo
            this.producto = pdao.buscarProductoPorCodBarra(this.session, codBarra);
            
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),this.producto.getNombreProducto(), 0 ,this.producto.getPrecioVenta(), new BigDecimal(0)));
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Producto agregado al detalle"));
                    
        } catch (Exception e) {
           
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
                
            }
            
        }finally {
            if (this.session != null) {
             this.session.close();
            }
        }
    }
    
     //Metodo para mostrar los datos de productos mediante codigo barra
    
     //Metodo para mostrar los datos de clientes mediante dialogPruductos
    
    public void agregarDatosProductos2(String  codBarra) {
        this.session = null;
        this.transaction = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pdao = new productoDaoImp();
            this.transaction = this.session.beginTransaction();
            
            // Guardar los datos en la variable objeto cliente segun el codigo
            this.producto = pdao.buscarProductoPorCodBarra(this.session, codBarra);
            
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),this.producto.getNombreProducto(), 0 ,this.producto.getPrecioVenta(), new BigDecimal(0)));
            
            if (this.producto != null) {
                this.codBarra = null;
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Producto agregado al detalle"));
             
            }
            else {
                this.codBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","No se encontró producto"));
               
            }
            this.transaction.commit();
                    
        } catch (Exception e) {
           
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
                
            }
            
        }finally {
            if (this.session != null) {
             this.session.close();
            }
        }
    }
    
}
