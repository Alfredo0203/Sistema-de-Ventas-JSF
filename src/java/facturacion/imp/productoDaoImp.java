/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.imp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import facturacion.dao.productoDao;
import facturacion.model.Producto;
import facturacion.util.HibernateUtil;



/**
 *
 * @author admin01
 */
public class productoDaoImp implements productoDao{
    
    @Override
   public List<Producto> listarProductos() {
       List<Producto> lista=null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction t=session.beginTransaction();
       // Esta consulta hace referencia a la entidad que está en sys,model
       String hql="FROM Producto";
       try {
           lista=session.createQuery(hql).list();
           t.commit();
           session.close();
           
       }  catch (Exception e) {
           t.rollback();
       }
       return lista;
   }
   
   @Override
   public void newProducto(Producto producto) {
       Session session=null;
       try {
           //Iniciamos una sesion o abrir la coneccion
           session = HibernateUtil.getSessionFactory().openSession();
           //Comenzamos la transaccion
           session.beginTransaction();
           //Le decimos que guarde lo que viene en el objeto producto, lo cual vendrá desde el controlador
           session.save(producto);
           session.getTransaction().commit();
       } catch (Exception e)  {
           System.out.println(e.getMessage());
           session.getTransaction().rollback();
       }finally {
           if(session!=null){
               session.close();
           }
       }
   }
   
   @Override
   public void updateProducto(Producto producto) {
       Session session=null;
       try {
           session=HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.update(producto);
           session.getTransaction().commit();
       }
       catch (Exception e)  {
           System.out.println(e.getMessage());
           session.getTransaction().rollback();
       }finally {
           if(session!=null){
               session.close();
           }
       }
   }
  
   @Override
    public void deleteProducto(Producto producto) {
        Session session=null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(producto);
            session.getTransaction().commit();
	System.out.println("Registro Eliminado Correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
           	System.err.println("Ocurrio este error: "+e.getMessage());
            session.getTransaction().rollback();            
        }finally{
            if (session!=null) {
                session.close();
            }
        }
    }
}
