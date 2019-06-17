/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import static blackjack.Juego.pos;
import controlador.Controlador;
import static controlador.Controlador.vista;
import graficos.VentanaPrincipal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DanielTapia
 */
public class JuegoBeta extends Thread {

    public boolean primeraMano = false;



    public ArrayList<Carta> descarte = new ArrayList();

    public Jugador player = new Jugador();
    public Jugador casa = new Jugador();
    public Carta[] cartas = new Carta[104]; //aun falta establecer el punto de corte 
    //que sera el que nos indique cuando las cartas se acaben y se requiera volver a mezclar y cortar
    public boolean jugar = false;
    public boolean pedir = false;
    public boolean quedarse = false;
    public boolean abrir = false;
    public boolean doblar = false;
    public boolean jugListo = false;
    public boolean casaLista = false;
    public int quienGana = 0; // tres posibles valores... 1:gana jugador,2:gana casa,3:empate;
    public static int pos = 0; //posicion de la carta sacada

    public JuegoBeta() {
        prepararCartas();

    }

    public JuegoBeta(Jugador player) {
        this.player = player;
        prepararCartas();
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

    public void repartirCartas() {
        player.mano.add(cartas[pos]);//carta[98]--para Jugador
        pos--;
        player.mano.add(cartas[pos - 1]);//carta[96]--para Jugador
      
        casa.mano.add(cartas[pos]);//carta[97]--para Casa
        pos--;
        casa.mano.add(cartas[pos - 1]);//carta[95]--para Casa
        pos = pos - 2;
    }

    public void chequearMano() {
        if ((player.mano.get(0).valor + player.mano.get(1).valor) > 8 && (player.mano.get(0).valor + player.mano.get(1).valor) < 12) {
            doblar = true;
        }
        if (player.mano.get(0).valor + player.mano.get(1).valor == 21) {
            player.bJ = true;
            if (casa.calcularPuntaje() != 21) {
                casaLista = true;
                jugListo = true;
                quienGana = 1;
            } else {
                jugListo = true;
            }
        }
        if (player.calcularPuntaje() <= 21 && player.bJ == false) {
            quedarse = true;
            pedir = true;
        } else if (player.calcularPuntaje() > 21) {
            pedir = false;
            quedarse = false;
            jugListo = true;
            casaLista = true;
            quienGana = 2;
        }

    }

    public void chequearManoCroupier() {
        if (casa.calcularPuntaje() < 17) {
            casa.pedirCarta = true;
        } else if (casa.mano.get(0).valor + casa.mano.get(1).valor == 21) {
            casa.pedirCarta = false;
            casa.bJ = true;
            if (player.bJ == true) {
                quienGana = 3;
            } else {
                quienGana = 2;
            }
            casaLista = true;
            jugListo=true;
            
        } else if (casa.calcularPuntaje() >= 17 && casa.calcularPuntaje() <= 21 && casa.bJ == false) {
            casa.pedirCarta = false;
            casaLista = true;
            jugListo = true;
            
        } else {
            casa.pedirCarta = false;
            casaLista = true;
            jugListo = true;
            quienGana=1;
            

        }

    }

    public void repartirUna(Jugador jug) {
        jug.mano.add(cartas[pos]);
        pos--;
    }

    public void run() {
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JuegoBeta.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (player.apuesta > 0 && jugar == false) {
                vista.panelJuego.jugar.setEnabled(true);
            } else {
                vista.panelJuego.jugar.setEnabled(false);
            }
            if (quedarse) {
                vista.panelJuego.quedarse.setEnabled(true);
            } else {
                vista.panelJuego.quedarse.setEnabled(false);
            }
            if (pedir) {
                vista.panelJuego.pedir.setEnabled(true);
            } else {
               vista.panelJuego.pedir.setEnabled(false);
            }
            if (doblar) {
                vista.panelJuego.doblar.setEnabled(true);
            } else {
                vista.panelJuego.doblar.setEnabled(false);
            }
            if (jugListo && casaLista == false) {
                jugListo = false;
                Controlador.accionesCasa();
            }
            if (jugListo && casaLista) {
                evaluarGanador();
            }

        }

    }

    private void evaluarGanador() {
        if (quienGana == 0) {
            if (player.puntos > casa.puntos) {
                quienGana = 1;
            } else if (player.puntos < casa.puntos) {
                quienGana = 2;
            } else {
                quienGana = 3;
            }
        }
        switch (quienGana) {
            case 1:

                if (player.bJ) {
                    player.dinero += player.apuesta + (player.apuesta * 1.5);
                    vista.panelJuego.quienGana.setText("Jugador Gana: BLACKJACK");
                } else {
                    player.dinero += player.apuesta * 2;
                    vista.panelJuego.quienGana.setText("Jugador Gana");
                }
                player.apuesta = 0;
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
                break;
            case 2:
                if (casa.bJ) {
                    vista.panelJuego.quienGana.setText("La Casa Gana: BLACKJACK");
                } else {
                    vista.panelJuego.quienGana.setText("La Casa Gana");
                }

                player.apuesta = 0;
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
                break;
            case 3:
                vista.panelJuego.quienGana.setText("Empate");

                player.dinero += player.apuesta;
                player.apuesta = 0;
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
                
                break;
        }
        primeraMano = true;
        jugar = false;
        jugListo = false;
        casaLista = false;
        quienGana = 0;
        vista.panelJuego.info.setText(Controlador.obtenerInfo());
        vista.panelJuego.aumentar.setEnabled(true);
        vista.panelJuego.restar.setEnabled(true);
        vista.panelJuego.revalidate();

    }
}
