package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = ""; //Agregar usuario de la bd
        var password = ""; //Agregar contraseña de la bd

        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //Clase de conexion a la BD
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectarnos a la BD: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else
            System.out.println("Error al conectarse");
    }
}
