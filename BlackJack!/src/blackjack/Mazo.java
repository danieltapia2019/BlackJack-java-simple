/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;

/**
 *
 * @author DanielTapia
 */
public class Mazo {

    Carta[] mazo = new Carta[52];

    public Mazo() {
        //Mazo creado en orden 
        for (int i = 0; i < mazo.length; i++) {
            mazo[i] = new Carta();
            mazo[i].generarCarta(i + 1);
//            mazo[i].mostrarCarta(); //linea para verificar que el mazo se creo en orden
        }
    }

    public void mesclarMazo() { //intercalar 
        int veces = (int) (Math.random() * (10 - 5 + 1) + 5); //El croupier va hacer de 5 a 10 veces este tipo de mezcla de cartas
        for (int v = 0; v < veces; v++) {

            int k = (int) (Math.random() * (16 - 10 + 1) + 10);  //Cortara de 10 a 16 cartas ,es al azar

            Carta[] aux = new Carta[k]; //arreglo auxiliar para ir almacenando las cartas que se cortaron
            int indice = k / 2;  //indice para luego ir colocandolas

            for (int i = 0; i < mazo.length; i++) {
                if (i < k) {
                    aux[i] = mazo[i];
                    mazo[i] = mazo[i + k];
                } else if (i < mazo.length - k) {
                    mazo[i] = mazo[i + k];
                } else if (i < mazo.length - (k / 2)) {

                    mazo[i] = aux[indice];

                    indice++;
                } else {
                    if (i == mazo.length - (k / 2)) {
                        indice = 0;
                    }

                    mazo[i] = aux[indice];
                    indice++;

                }
                

            }
        }
     
    }

}
