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
    public Carta[] cartas = new Carta[104]; //aun falta establecer el punto de corte 
    //que sera el que nos indique cuando las cartas se acaben y se requiera volver a mezclar y cortar
    public boolean jugar = true;
    public static int pos = 0; //posicion de la carta sacada

    public Juego() {
        prepararCartas();
        iniciarJuego();
        

    }
    public Juego(Jugador player){
        this.player=player;
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

            /*Siempre se reparten de entrada 2 cartas para el jugador y la casa:
            -Carta -->jugador (boca arriba)
            -Carta --> casa (boca arriba)
            -Carta -->jugador (boca arriba)
            -Carta --> casa (dada vuelta)
             */
            player.mano.add(cartas[pos]);//carta[98]--para Jugador
            pos--;
            player.mano.add(cartas[pos - 1]);//carta[96]--para Jugador
            player.mostrarMano();
            System.out.println("Puntaje: " + player.calcularPuntaje());
            casa.mano.add(cartas[pos]);//carta[97]--para Casa
            pos--;
            casa.mano.add(cartas[pos - 1]);//carta[95]--para Casa
            pos = pos - 2;//el mazo que en la carta[94] para seguir repartiendo en caso de ser necesario
            /*Luego al iniciar otra mano la posicion de la carta a sacar obviamente cambió pero se
            sigue manteniendo la relacion de una y una para cada una
             */
            

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
                            JOptionPane.showMessageDialog(null,"¡¡¡La Casa gana!!!");
                        }
                    }
                }

            } else if (player.puntos == 21) {
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
                            JOptionPane.showMessageDialog(null,"¡¡¡Jugador Gana!!!");

                        }
                    }

                } else if (casa.puntos >= 17 && casa.puntos < 21) {
                    System.out.println("La casa se queda");
                } else if (casa.puntos == 21) {
                    System.out.println("-->LA CASA GANA : BLACKJACK!!");
                    JOptionPane.showMessageDialog(null,"¡¡¡La Casa gana!!!");
                }

            }
            if(player.puntos<=21 && player.bJ==false && casa.puntos>=17 && casa.puntos<=21 && casa.bJ==false){
                if(player.puntos==casa.puntos){
                    JOptionPane.showMessageDialog(null,"Empate");
                }
                else if(player.puntos>casa.puntos){
                    JOptionPane.showMessageDialog(null,"¡¡¡Jugador Gana!!!");
                }
                else
                    JOptionPane.showMessageDialog(null,"¡¡¡La Casa gana!!!");
                
            }

            player.descartarMano(descarte);
            casa.descartarMano(descarte);
            char opcion = 'a';
            while(opcion!='y' && opcion!='n'){
            try {
                opcion = JOptionPane.showInputDialog("Jugar otra Mano: Yes(y)/No(n)").toLowerCase().charAt(0);
                if(opcion!='y' && opcion!='n'){
                     JOptionPane.showMessageDialog(null, "Aun no se ha ingresado una opcion correcta");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Aun no se ha ingresado una opcion correcta");
            }}
            if (opcion == 'y') {
                player.pedirCarta = true;
                casa.pedirCarta = true;
                player.bJ=false;
                casa.bJ=false;
                
                
            } else {
                jugar = false;
            }

        }
    }
}
   
