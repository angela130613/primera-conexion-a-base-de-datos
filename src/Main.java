import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection myConn = null;
        try {
            // Establecer conexión con la base de datos
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reto",
                    "root",
                    "angela130906"
            );
            System.out.println("Genial, nos conectamos....");

            // Consultar empleados
            consultarEmpleados(myConn);

            // Insertar un nuevo empleado
            insertarEmpleado(myConn, "Carlos Fernández", "Ingeniero", 40000.00);

            // Actualizar empleado
            actualizarEmpleado(myConn, 1, "Juan Pérez", "Piloto", 55000.00);

            // Eliminar empleado
            eliminarEmpleado(myConn, 3);

            // Consultar empleados nuevamente para ver los cambios
            consultarEmpleados(myConn);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salió mal :(");
        } finally {
            try {
                if (myConn != null) myConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para insertar un empleado
    private static void insertarEmpleado(Connection conexion, String nombre, String cargo, double salario)
            throws SQLException {
        String sql = "INSERT INTO personas (nombre, cargo, salario) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setDouble(3, salario);
            pstmt.executeUpdate();
            System.out.println("Empleado insertado correctamente!");
        }
    }

    // Método para consultar empleados
    private static void consultarEmpleados(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM personas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Nombre: %s, Cargo: %s, Salario: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getDouble("salario"));
            }
        }
    }

    // Método para actualizar un empleado
    private static void actualizarEmpleado(Connection conexion, int id, String nombre, String cargo, double salario)
            throws SQLException {
        String sql = "UPDATE personas SET nombre = ?, cargo = ?, salario = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setDouble(3, salario);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Empleado actualizado correctamente!");
        }
    }

    // Método para eliminar un empleado
    private static void eliminarEmpleado(Connection conexion, int id) throws SQLException {
        String sql = "DELETE FROM personas WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Empleado eliminado correctamente!");
        }
    }
}