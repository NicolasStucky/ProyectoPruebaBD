package prueba_bd.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        //Creamos objeto llamado conexion de TIPO Connection
        Connection conexion = null;

        //Datos de nuestra BASE DE DATOS
        var baseDatos = "prueba_bd";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "admin";
        try {// ⬇ Para poder cargar la clase de conexion a la DB se utiliza ⬇
            Class.forName("com.mysql.cj.jdbc.Driver");
            // ⬇⬇ Realizamos una conexion con los parametros que configuramos ⬇⬇
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectarnos a la BD: " + e.getMessage());
        }
        return conexion;
    }

    // ****** P R U E B A ******
    public static void main(String[] args) {
        //Creamos esto para poder obtener la conexiona la DB
        var conexion = Conexion.getConexion();
        if (conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else {
            System.out.println("Error al conectarse");
        }
    }
}
