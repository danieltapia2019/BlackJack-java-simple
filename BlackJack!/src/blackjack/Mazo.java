/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author DanielTapia
 */
public class Mazo {
    Carta[] mazo=new Carta[52];
    public Mazo(){
        //Mazo creado en orden 
        for(int i=0;i<mazo.length;i++){
            mazo[i]=new Carta();
            mazo[i].generarCarta(i+1);
            mazo[i].mostrarCarta();
        }
        
    }
    
}
