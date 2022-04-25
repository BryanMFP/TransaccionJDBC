package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManejoPersona {
    public static void main(String[] args) {
        
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PersonaDAO personaDao = new PersonaDAO(conexion);
            
            Persona personaActualizar = new Persona(2, "Pedro", "Gonzales", "ksuarez@mail.com", "88987456");
            personaDao.actualizar(personaActualizar);
            
            Persona personaNueva = new Persona("Alfredo", "Parrales", "stomala@mail.com", "12345678901234567890123456789012345678901234567890");
            personaDao.insertar(personaNueva);
            
            conexion.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
//                Logger.getLogger(TestManejoPersona.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
 
}
