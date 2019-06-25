/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import static blackjack.Juego.pos;
import controlador.Controlador;
import static controlador.Controlador.obtenerInfo;
import static controlador.Controlador.vista;
import graficos.VentanaPrincipal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielTapia
 */
public class JuegoBeta extends Thread implements Serializable {

    public boolean primeraMano = false;

    public ArrayList<Carta> descarte = new ArrayList(); //mazo de descarte

    public Jugador player;
    public Jugador casa = new Jugador();

    public Carta[] cartas = new Carta[104]; //mazo total del juego esta compuesto por dos mazos
    public int corte;
    public boolean corteAlcanzado = false;
    public boolean jugar = false;
    public boolean pedir = false;
    public boolean quedarse = false;
    public boolean abrir = false;
    public boolean doblar = false;
    public boolean jugListo = false;
    public boolean casaLista = false;
    public boolean juegoDoblado = false;
    public boolean juegoAsegurado = false;
    public int quienGana = 0; // tres posibles valores... 1:gana jugador,2:gana casa,3:empate;
    public static int pos = 0; //posicion de la carta sacada

    public JuegoBeta() {
        player = new Jugador();
        prepararCartas();

    }

    public JuegoBeta(Jugador player) {
        this.player = player;
        prepararCartas();
    }

    private void prepararCartas() {
        //se jugara con dos mazos
        Mazo m1 = new Mazo();
        Mazo m2 = new Mazo();
        // se crean los mazos y ahora los mezclamos
        m1.mezclaMazo();
        m2.mezclaMazo();
        //una vez mezclados solo se colocan uno tras el otro conformando asi el mazo total de juego
        for (int i = 0; i < cartas.length; i++) {
            if (i < 52) {
                cartas[i] = m1.mazo[i];
            } else {
                cartas[i] = m2.mazo[i - 52];
            }
        }
        corte = (int) (Math.random() * (20 - 10 + 1) + 10);

        //una vez preparado el mazo total se elije un punto de corte que es el que va indicar cuando las cartas
        //se esten por acabar y entonces se procedera a mezclar todas las cartas de nuevo.
        //se descartan las primeras 5 cartas , esta es una regla opcional, en algunos casinos se hace en otros no
        descarte.add(cartas[103]);
        descarte.add(cartas[102]);
        descarte.add(cartas[101]);
        descarte.add(cartas[100]);
        descarte.add(cartas[99]);
        //ya queda lista la posicion de la carta con la que se comenzara a repartir a los jugadores
        pos = 98;

        //Recordar que el mazo se coloca boca abajo y se va descartando (asi como tambien se reparte) desde arriba
//        for (Carta carta : cartas) {
//            carta.mostrarCarta();
//        }
    }

    public void rearmarMazo() {
        JOptionPane.showMessageDialog(null, "El mazo alcanzo el punto de corte. A continuacion se mezclaran las cartas de nuevo");
        //se terminan de descartar las cartas que quedan del mazo de juego
        for (int i = 0; i < pos + 1; i++) {
            descarte.add(cartas[i]);
        }
        System.out.println(descarte.size());
        //Una vez completo el mazo de descarte con 104 cartas queda el mazo de juego vacio
        //luego el croupier tratara de dividir el mazo total de descarte en dos partes iguales aproximadamente
        int k = (int) (Math.random() * (60 - 50 + 1) + 50);
        //se almacenan las cartas en arreglos auxiliares que representan las dos partes divididas

        Carta[] aux1 = new Carta[k];

        Carta[] aux2 = new Carta[descarte.size() - k];

        for (int i = 0; i < descarte.size(); i++) {
            if (i < k) {
                aux1[i] = descarte.get(i);
            } else {
                aux2[i - k] = descarte.get(i);
            }

        }
        System.out.println("aux 1: " + aux1.length);
        System.out.println("aux 2: " + aux2.length);

        descarte.clear();
        //luego se mezcla cada parte como si fuera un mazo comun
        Mazo.mezclaMazoAux(aux1);
        Mazo.mezclaMazoAux(aux2);
        //Una vez mezclados se colocan uno tras el otro conformando nuevamente el mazo total de juego
        for (int i = 0; i < cartas.length; i++) {
            if (i < k) {
                cartas[i] = aux1[i];
                if (cartas[i].valor == 1) {
                    cartas[i].valor = 11; // vuelve al valor original 11 todos los ases
                }
            } else {
                cartas[i] = aux2[i - k];
                if (cartas[i].valor == 1) {
                    cartas[i].valor = 11; // vuelve al valor original 11 todos los ases
                }

            }
        }
        System.out.println("Mazo de cartas nuevo: " + cartas.length);
        //se elige un punto de corte nuevamente y se descartan las 5 cartas de arriba otra vez y listos a seguir jugando
        corte = (int) (Math.random() * (20 - 10 + 1) + 10);

        descarte.add(cartas[103]);
        descarte.add(cartas[102]);
        descarte.add(cartas[101]);
        descarte.add(cartas[100]);
        descarte.add(cartas[99]);
        System.out.println("descarte: " + descarte.size());
        //ya queda lista la posicion de la carta con la que se comenzara a repartir a los jugadores
        pos = 98;

    }

    public void repartirCartas() {
        if (corteAlcanzado == true) {
            rearmarMazo();
            corteAlcanzado = false;
        }
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
            jugListo = true;

        } else if (casa.calcularPuntaje() >= 17 && casa.calcularPuntaje() <= 21 && casa.bJ == false) {
            casa.pedirCarta = false;
            casaLista = true;
            jugListo = true;

        } else {
            casa.pedirCarta = false;
            casaLista = true;
            jugListo = true;
            quienGana = 1;

        }

    }

    public void repartirUna(Jugador jug) {
        jug.mano.add(cartas[pos]);
        pos--;
    }

    public void run() {
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(JuegoBeta.class.getName()).log(Level.SEVERE, null, ex);
            }
            vista.panelJuego.info.setText(obtenerInfo());
            if (corteAlcanzado == false) {
                if (pos <= corte) {
                    corteAlcanzado = true;
                }
            }
            if (player.dinero>=50 && player.apuesta>=50 && jugar == false) {
                vista.panelJuego.jugar.setEnabled(true);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            } else {
                vista.panelJuego.jugar.setEnabled(false);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            }
            if (quedarse) {
                vista.panelJuego.quedarse.setEnabled(true);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            } else {
                vista.panelJuego.quedarse.setEnabled(false);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            }
            if (pedir) {
                vista.panelJuego.pedir.setEnabled(true);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            } else {
                vista.panelJuego.pedir.setEnabled(false);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            }
            if (doblar && player.dinero>=player.total) {
                vista.panelJuego.doblar.setEnabled(true);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
            } else {
                vista.panelJuego.doblar.setEnabled(false);
                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
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
                    player.dinero+=player.total+(player.total*3/2);
                    player.total=0;
                    vista.panelJuego.quienGana.setText("Jugador Gana: BLACKJACK");
                } else{
                    player.dinero += player.total*2;
                    player.total=0;
                    vista.panelJuego.quienGana.setText("Jugador Gana");
                }

                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
                break;
            case 2:
                if (casa.bJ) {
                    vista.panelJuego.quienGana.setText("La Casa Gana: BLACKJACK");
                } else {
                    vista.panelJuego.quienGana.setText("La Casa Gana");
                    
                }
                player.total=0;
                if(player.dinero<player.apuesta){
                    player.apuesta=player.dinero;
                }

                vista.panelJuego.revalidate();
                vista.panelJuego.repaint();
                break;
            case 3:
                vista.panelJuego.quienGana.setText("Empate");
                player.dinero+=player.total;
                player.total=0;

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
