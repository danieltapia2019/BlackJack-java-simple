/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

/**
 *
 * @author DanielTapia
 */
public class Jugador {

    String nombre;
    String pasword;
    ArrayList<Carta> mano = new ArrayList();
    int dinero;
    int puntos = 0;
    int score = 0;
    int apuesta;
    boolean pedirCarta = true;
}
