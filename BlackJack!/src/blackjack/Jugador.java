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
    boolean bJ=false; //indica si hay blackjack o no

    public Jugador() {
    }

    public void mostrarMano() {
        for (int i = 0; i <mano.size(); i++) {
                mano.get(i).mostrarCarta();
                
            }
    }
    public void mostrarUltimaPedida(){
        mano.get(mano.size()-1).mostrarCarta();
    }

    public int calcularPuntaje() {
        for(int i=0;i<mano.size();i++){
            puntos+= mano.get(i).valor;
        }
        if(puntos>21){
            puntos=0;
            for(int i=0;i<mano.size();i++){
                if(mano.get(i).valor==11){
                    mano.get(i).valor=1;
                }
                puntos+=mano.get(i).valor;
            }
            
        }
        return puntos;
      
    }
}
