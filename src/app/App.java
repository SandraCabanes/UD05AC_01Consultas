/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Sandra
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          //CREAMOS CONEXION
        //SessionFactory sessionFactory;
        //Configuration configuration = new Configuration();
        //configuration.configure();
        //sessionFactory = configuration.buildSessionFactory();
        SessionFactory factory = new Configuration().configure().buildSessionFactory(); 
        
        // CREAMOS UN OBJETO
        Profesor profesor=new Profesor(80,"Pepe","Garcia","Perez");
        
        //CREAR UNA SESION
        Session session=factory.openSession();
        session.beginTransaction();
        
        //GUARDAR OBJETO
        //session.save(profesor);
        
        Profesor profesor2=(Profesor) session.get(Profesor.class, 1);
        System.out.println(profesor2);
        
        
        profesor.setNombre("Manola");
        session.update(profesor);
        
        //session.saveOrUpdate(profesor);
        
        //CERRAR CONEXION
        session.getTransaction().commit();
        session.close();
        factory.close();
        
        
    }
    
}