/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import Stream.Stream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DanielTapia
 */
public class Jugador implements Serializable {

    public String nombre;
    public String password;
    public ArrayList<Carta> mano = new ArrayList();
    public int dinero = 1000;
    public int puntos = 0;
    public int score = 0;
    public int apuesta = 0;
    public int total=apuesta;
    public boolean pedirCarta = true;
    public boolean bJ = false; //indica si hay blackjack o no

    public Jugador() {
        this.nombre = "Invitado";
    }

    public Jugador(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        Stream.jugadores.add(this);
    }

    public void mostrarMano() {
        for (int i = 0; i < mano.size(); i++) {
            mano.get(i).mostrarCarta();

        }
    }

    public void mostrarUltimaPedida() {
        mano.get(mano.size() - 1).mostrarCarta();
    }

    public int calcularPuntaje() {
        puntos = 0;
        for (int i = 0; i < mano.size(); i++) {
            puntos += mano.get(i).valor;
        }
        if (puntos > 21) {
            puntos = 0;
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i).valor == 11) {
                    mano.get(i).valor = 1;
                }
                puntos += mano.get(i).valor;
            }

        }
        return puntos;

    }

    public boolean chequearDoblar() {
        if (calcularPuntaje() > 8 && calcularPuntaje() < 12) {
            return true;
        } else {
            return false;
        }
    }

    public boolean chequearAbrir() {
        if (mano.get(0).valor == mano.get(1).valor) {
            return true;
        } else {
            return false;
        }
    }

    public void descartarMano(ArrayList<Carta> cartas) {
        for (int i = mano.size() - 1; i >= 0; i--) {
            cartas.add(mano.get(i));
        }
        mano.clear();

    }
}
