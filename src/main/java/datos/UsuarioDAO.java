package datos;

import domain.Usuario;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT * FROM usuarios";
    private static final String SQL_INSERT = "INSERT INTO usuarios(username, pass)VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET username = ?, pass = ? WHERE id_Usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id_Usuario = ?";
    
    public List<Usuario> seleccionar(){
        Connection conn = null;
        PreparedStatement instruccion = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            
            conn = getConnection();
            instruccion = conn.prepareStatement(SQL_SELECT);
            rs = instruccion.executeQuery();
            while(rs.next()){
                int idPersona = rs.getInt("id_Usuario");
                String nombre = rs.getString("username");
                String apellido = rs.getString("pass");
                
                usuario = new Usuario(idPersona, nombre, apellido);
                
                usuarios.add(usuario);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                
                close(rs);
                close(instruccion);
                close(conn);
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return usuarios;
    }

    public int insertar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                
                close(stmt);
                close(conn);
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }

    public int actualizar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                
                close(stmt);
                close(conn);
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }

    public int eliminar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                
                close(stmt);
                close(conn);
                
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }
    
}
