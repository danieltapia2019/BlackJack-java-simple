/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielTapia
 */
public class Juego {

    public ArrayList<Carta> descarte = new ArrayList();

    public Jugador player = new Jugador();
    public Jugador casa = new Jugador();
    public Carta[] cartas = new Carta[104];
    public boolean jugar = true;
    public static int pos=0; //posicion de la carta sacada

    public Juego() {
        prepararCartas();
        iniciarJuego();

    }

    private void prepararCartas() {
        Mazo m1 = new Mazo();
        Mazo m2 = new Mazo();
        m1.mezclaMazo();
        m2.mezclaMazo();
        for (int i = 0; i < cartas.length; i++) {
            if (i < 52) {
                cartas[i] = m1.mazo[i];
            } else {
                cartas[i] = m2.mazo[i - 52];
            }
        }
        //se genera el mazo total con el que se va jugar que son dos mazos ya mezclados.
        descarte.add(cartas[103]);
        descarte.add(cartas[102]);
        descarte.add(cartas[101]);
        descarte.add(cartas[100]);
        descarte.add(cartas[99]);
        pos = 98;
        //Se descartan las primeras 5 cartas
        //Recordar que el mazo se coloca boca abajo y se va descartando (asi como tambien se reparte) desde arriba

//        for (Carta carta : cartas) {
//            carta.mostrarCarta();
//        }
    }

    private void iniciarJuego() {
        while (jugar) {
             
            /*Se repartira desde esta carta hacia la primera.
Recordar que se descartaron 5 al iniciar el juego mas las 2 que siempre se reparten al jugador y a la casa*/
            System.out.println("Repartiendo Cartas\n-----------------------");
            System.out.println("Jugador:");

            player.mano.add(cartas[pos]);
            pos--;
            player.mano.add(cartas[pos]);
            pos--;
            player.mostrarMano();
            System.out.println("Puntaje: " + player.calcularPuntaje());
            casa.mano.add(cartas[pos]);
            pos--;
            casa.mano.add(cartas[pos]);
            pos--;

            if (player.puntos < 21) {

                String[] opciones = {"Pedir carta", "Cortar"};
                while (player.pedirCarta == true) {
                    int seleccion = JOptionPane.showOptionDialog(null, "Elija una opcion", "!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (seleccion == 1) {
                        player.pedirCarta = false;
                    } else {

                        player.mano.add(cartas[pos]);
                        pos--;
                        player.mostrarUltimaPedida();
                        System.out.println("Puntaje: " + player.calcularPuntaje());
                        if (player.puntos > 21) {
                            player.pedirCarta = false;
                            System.out.println("¡PERDISTE TE PASASTE!");
                        }
                    }
                }

            }
            if (player.puntos == 21) {
                player.bJ = true;
                System.out.println("-->JUGADOR GANA : BLACKJACK!!");
            }

            if (player.puntos <= 21 && player.bJ == false) {
                System.out.println("----------------------------------");
                System.out.println("Computadora");
                casa.mostrarMano();
                System.out.println("Puntaje: " + casa.calcularPuntaje());
                if (casa.puntos < 17) {
                    while (casa.pedirCarta == true) {
                        casa.mano.add(cartas[pos]);
                        pos--;
                        casa.mostrarUltimaPedida();
                        System.out.println("Puntaje: " + casa.calcularPuntaje());

                        if (casa.puntos >= 17 && casa.puntos <= 21) {

                            System.out.println("La casa se queda");
                            casa.pedirCarta = false;
                        } else if (casa.puntos > 21) {
                            casa.pedirCarta = false;
                            System.out.println("La casa pierde!!!");

                        }
                    }

                }
                else if(casa.puntos>17 && casa.puntos!=21){
                     System.out.println("La casa se queda");
                }
                else if(casa.puntos==21){
                    System.out.println("-->LA CASA GANA : BLACKJACK!!");;
                }
                    
                }
            char opcion='a';
            try{
             opcion =JOptionPane.showInputDialog("Jugar otra Mano: Yes(y)/No(n)").toLowerCase().charAt(0);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Aun no se ha ingresado una opcion");}
            if(opcion=='y'){
                player.pedirCarta=true;
                casa.pedirCarta=false;
                player.puntos=0;
                casa.puntos=0;
                player.mano.clear();
                casa.mano.clear();
            }
            else
                jugar=false;
            

            
            }
        }
    }
