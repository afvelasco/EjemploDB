package ejemplodb;
 
import java.sql.*;
import javax.swing.JOptionPane;
 
/**
 * Conexión básica a la base de datos PostgreSQL.
 *
 */
public class EjemploDB {
 
    /**
     * Establecemos la conexión con la base de datos <b>customerdb</b>.
     */
    public void connectDatabase() {
        try {
            // Registramos el driver de PostgresSQL
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection;
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/miDB", "postgres", "1234");
// para verificar la conexión 
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            Statement st = connection.createStatement();
            String qs;
            qs = "CREATE TABLE IF NOT EXISTS usuarios(usuario_id integer NOT NULL PRIMARY KEY,username varchar(225) NOT NULL,password varchar(225),islogged varchar(10))";
            st.executeUpdate(qs);
            String qs1;
// para insertar datos a la tabla
//////////////////////////
//            int uid = 4;
//            String un = "Samuel";
//            String pw = "5678";
//            String il = "no";
//////////////////////////
//            qs1 = "INSERT INTO usuarios(usuario_id,username,password,islogged) VALUES ("+uid+",'"+un+"','"+pw+"','"+il+"')";
//            st.executeUpdate(qs1);
// Para modificar datos de un registro
//            String qs2;
//            qs2 = "UPDATE usuarios SET password='280913' WHERE usuario_id=3";
//            st.executeUpdate(qs2);
// Para consultar
            ResultSet rs;
            String qs3 = "SELECT * FROM usuarios";
            rs = st.executeQuery(qs3);
            while(rs.next()){
              int id = rs.getInt(1);
              String name = rs.getString(2);
              String pass = rs.getString(3);
              JOptionPane.showMessageDialog(null, id+" "+name+" "+pass); 
            }
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    } 
    /**
     * Testing Java PostgreSQL connection with host and port
     * Probando la conexión en Java a PostgreSQL especificando el host y el puerto.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EjemploDB ejemplo = new EjemploDB();
        ejemplo.connectDatabase(); 
    }
}