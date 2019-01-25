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
      
        //CREAR UNA SESIÓN
        Session session=factory.openSession();
        
        //CREAR CONSULTAS
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
        
        //Unique result
        Profesor profesor=(Profesor)session.createQuery("SELECT p FROM Profesor p WHERE id=1001 ").uniqueResult();
        System.out.println("Profesor con id 1001: "+profesor.getNombre());
        
        //Paginación
        int tamanyoPagina=10;
        int paginaMostrar=7;
        
        query=session.createQuery("SELECT p FROM Profesor p Order By p.id");
        query.setMaxResults(tamanyoPagina);
        query.setFirstResult(paginaMostrar*tamanyoPagina);
        List<Profesor>profesoresPaginacion=query.list();
        
        for (Profesor p : profesoresPaginacion) {
            System.out.println(p.toString());
        }
        
        //Consultas con nombre
        query=session.getNamedQuery("findAllProfesores");
        List<Profesor>profesoresNombre=query.list();
        for (Profesor p : profesoresNombre) {
            System.out.println(p.toString());
        }
        
        //Cosulta con parámetros por nombre
        String nombre="ISIDRO";
        String ape1="CORTINA";
        String ape2="GARCIA";
        
        query=session.createQuery("SELECT p FROM Profesor p WHERE p.nombre=:nombre AND p.ape1=:ape1 AND p.ape2=:ape2");
        query.setString("nombre", nombre);
        query.setString("ape1", ape1);
        query.setString("ape2", ape2);
        
        List<Profesor>profesoresParametros=query.list();
        for (Profesor p : profesoresParametros) {
            System.out.println(p.toString());
        }
        
        //CERRAR SESIÓN
        session.close();
        factory.close();
        
        
    }
    
}