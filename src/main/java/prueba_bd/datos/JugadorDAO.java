package prueba_bd.datos;

import prueba_bd.dominio.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static prueba_bd.conexion.Conexion.getConexion;

public class JugadorDAO implements IJugadorDAO{
    @Override
    public List<Jugador> listarJugadores(){
        List<Jugador> jugadores = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM jugadores ORDER BY idjugador";

        try {
            ps = con.prepareStatement(sql);
            // ⬇⬇ Almacenamos el resultado de la sentencia SQL ⬇⬇
            rs = ps.executeQuery();// Ejecutamos la sentencia con nuestro objeto de 'ps' de PreparedStatement y executeQuery()

            // ⬇⬇ Preguntamos si tenemos registros a iterar ⬇⬇
            // ⬇⬇ Recorremos el ResultSet ⬇⬇
            while (rs.next()) {
                // rs.next() mueve el cursor al siguiente registro de la tabla.
                // Si hay un registro disponible, devuelve true; si no, devuelve false y se corta el while.

                // Por cada fila de la tabla, creamos un nuevo objeto de tipo 'Cliente'.
                var jugador = new Jugador();

                // Usamos los métodos getXxx de ResultSet para obtener los valores de cada columna.
                // El valor recuperado depende del tipo de dato (ej: getInt, getString, getDate, etc.).
                jugador.setIdjugador(rs.getInt("idjugador"));  // recupera la columna 'id' como entero
                jugador.setJugadornombre(rs.getString("jugadornombre")); // recupera la columna 'nombre' como String
                jugador.setJugadoresapellido(rs.getString("jugadoresapellido")); // recupera la columna 'apellido' como String

                // Agregamos el objeto 'cliente' a la lista 'clientes' para tenerlos disponibles luego.
                jugadores.add(jugador);

            } // Cuando rs.next() ya no encuentra más registros, devuelve false y salimos del while.
        }catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        }finally {// Agregamos finally para cerrar cualquier recurso como buena practica
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return jugadores;
    }


    public static void main(String[] args) {
        //PRUEBA
        IJugadorDAO jugadorDAO = new JugadorDAO();
        var jugadores = jugadorDAO.listarJugadores();
        jugadores.forEach(System.out::println);
    }
}
