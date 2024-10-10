import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRes = null;
        try {
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reto",
                    "root",
                    "angela130906"
            );
            System.out.println("Genial, nos conectamos....");

            // realizamos una consulta a la base de datos
            myStmt = myConn.createStatement();
            myRes = myStmt.executeQuery("SELECT * FROM personas");

            // iteramos los resultados para imprimir en consola.
            while (myRes.next()){
                System.out.println(myRes.getString("nombre"));
                System.out.println(myRes.getString("apellido"));
                System.out.println(myRes.getString("salario"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal :(");
        }
    }
}