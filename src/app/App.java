/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;
import model.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
      
        //CREAR UNA SESION
        Session session=factory.openSession();
        
        //CREAR CONSULTA
        Query query=session.createQuery("SELECT p FROM Profesor p");
        List<Profesor>profesores=query.list();
        for(Profesor profesor : profesores){
            System.out.println(profesor);
        }
        
        query=session.createQuery("SELECT p.id, p.nombre FROM Profesor p");
        List<Object[]>listDatos=query.list();
        for(Object[] datos : listDatos){
            System.out.println(datos[0]+" -- "+datos[1]);
        }
        
        
        query=session.createQuery("SELECT p.nombre FROM Profesor p");
        List<Object>listNombres=query.list();
        for(Object datos : listNombres){
            System.out.println(datos);
        }
        
        session.close();
        factory.close();
        
        
    }
    
}