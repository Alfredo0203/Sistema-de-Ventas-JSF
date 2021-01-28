/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.imp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import facturacion.dao.clienteDao;
import facturacion.model.Cliente;
import facturacion.util.HibernateUtil;

/**
 *
 * @author admin01
 */
public class clienteDaoImp implements clienteDao{
 @Override
   public List<Cliente> listarClientes() {
       List<Cliente> lista=null;
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction t=session.beginTransaction();
       // Esta consulta hace referencia a la entidad que está en sys,model
       String hql="FROM Cliente";
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
   public void newCliente(Cliente cliente) {
       Session session=null;
       try {
           //Iniciamos una sesion o abrir la coneccion
           session = HibernateUtil.getSessionFactory().openSession();
           //Comenzamos la transaccion
           session.beginTransaction();
           //Le decimos que guarde lo que viene en el objeto cliente, lo cual vendrá desde el controlador
           session.save(cliente);
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
   public void updateCliente(Cliente cliente) {
       Session session=null;
       try {
           session=HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.update(cliente);
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
  public void deleteCliente(Cliente cliente) {
       Session session=null;
       try {
           session=HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.delete(cliente);
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
   
   

  
}
