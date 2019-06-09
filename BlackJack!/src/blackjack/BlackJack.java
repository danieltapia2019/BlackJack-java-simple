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
        Mazo m1=new Mazo();
        m1.mesclarMazo1();
        m1.mesclarMazo2();
        for(int i=0;i<m1.mazo.length;i++){
            m1.mazo[i].mostrarCarta();
        }
    }
    
}
