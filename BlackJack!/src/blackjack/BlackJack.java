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
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Juego blackjack=new Juego();
        for(int i=0;i<blackjack.descarte.size();i++){
            blackjack.descarte.get(i).mostrarCarta();
        }


       
    }
    
}
