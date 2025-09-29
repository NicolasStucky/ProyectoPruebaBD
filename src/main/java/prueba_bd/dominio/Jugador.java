package prueba_bd.dominio;

import java.util.Objects;

public class Jugador {
    private int idjugador;
    private String jugadornombre;
    private String jugadoresapellido;

    public Jugador(){}

    public Jugador(int idjugador){
        this.idjugador = idjugador;
    }

    public Jugador(String jugadornombre, String jugadoresapellido){
        this.jugadornombre = jugadornombre;
        this.jugadoresapellido = jugadoresapellido;
    }

    public Jugador(int idjugador, String jugadornombre, String jugadoresapellido){
        this(jugadornombre,jugadoresapellido);
        this.idjugador = idjugador;
    }

    public int getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(int idjugador) {
        this.idjugador = idjugador;
    }

    public String getJugadornombre() {
        return jugadornombre;
    }

    public void setJugadornombre(String jugadornombre) {
        this.jugadornombre = jugadornombre;
    }

    public String getJugadoresapellido() {
        return jugadoresapellido;
    }

    public void setJugadoresapellido(String jugadoresapellido) {
        this.jugadoresapellido = jugadoresapellido;
    }


    @Override
    public String toString() {
        return "Jugador{" +
                "idjugador=" + idjugador +
                ", jugadornombre='" + jugadornombre + '\'' +
                ", jugadoresapellido='" + jugadoresapellido + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return idjugador == jugador.idjugador && Objects.equals(jugadornombre, jugador.jugadornombre) && Objects.equals(jugadoresapellido, jugador.jugadoresapellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjugador, jugadornombre, jugadoresapellido);
    }
}
