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
    
    public void mezclaMazo(){
        int m=(int) (Math.random() * (4 - 1 + 1) + 1);
        switch(m){
            case 1:
                mezclarForma1();
                mezclarForma2();
                break;
            case 2:
                mezclarForma2();
                mezclarForma1();
                break;
            case 3:
                mezclarForma1();
                mezclarForma1();
                break;
            default:
                mezclarForma2();
                mezclarForma2();
                break;
            
        }
    }

    public void mezclarForma1() { //intercalar 
        int veces = (int) (Math.random() * (4 - 3 + 1) + 3); //El croupier va hacer de 3 a 4 veces este tipo de mezcla de cartas

        for (int v = 0; v < veces; v++) {

            int k = (int) (Math.random() * (25 - 15 + 1) + 15);  //Cortara de 15 a 25 cartas ,es al azar

            Carta[] aux = new Carta[k]; //arreglo auxiliar para ir almacenando las cartas que se cortaron
            int div = (int) (Math.random() * (12 - 7 + 1) + 7); //al ir soltando las cartas, soltara de 7 a 12 cartas en una primera parte y luego la restante
            int indice = k - div;  //indice para luego ir colocandolas

            for (int i = 0; i < mazo.length; i++) {
                if (i < k) {
                    aux[i] = mazo[i];
                    mazo[i] = mazo[i + k];
                } else if (i < mazo.length - k) {
                    mazo[i] = mazo[i + k];
                } else if (i < mazo.length - (k - div)) { //cuando entra aca se suelta una parte de las cartas seleccionadas

                    mazo[i] = aux[indice];

                    indice++;
                } else {
                    if (i == mazo.length - (k - div)) { // cuando entra aca termina de soltar las cartas restantes de las seleccinoadas
                        indice = 0;
                    }

                    mazo[i] = aux[indice];
                    indice++;

                }

            }
        }

    }

    public void mezclarForma2() {//separar el mazo en dos partes por igual aproximadamente y ir soltando cartas de ambas partes
        int veces = (int) (Math.random() * (4 - 3 + 1) + 3); //El croupier va hacer de 3 a 4 veces este tipo de mezcla de cartas

        for (int v = 0; v < veces; v++) {
            int k = (int) (Math.random() * (30 - 22 + 1) + 22); // k sera el numero por el que aproximadamente se dividira el mazo en dos partes

            Carta[] aux1 = new Carta[k];
            Carta[] aux2 = new Carta[mazo.length - k]; //auxiliares para almacenar las dos partes del mazo
            int indice1 = 0;
            int indice2 = 0;
            
            for (int i = 0; i < mazo.length; i++) {
                if (i < k) {
                    aux1[i] = mazo[i];
                } else {
                    aux2[i - k] = mazo[i];
                }

            }

            //ya se almacenaron las cartas en dos partes 
            boolean cartaPrim = true; //esta variable es para determinar cual de las dos partes va soltando cartas
            boolean limiteaux1 = false; //variables para saber si algunas de las partes divididas llego a su limite
            boolean limiteaux2 = false;
            int n1;
            int n2;
            /*n1 y n2 seran generados al azar entre 1 y 4. Este numero representa cuantas cartas se van soltando 
            de cada una de las dos partes. Se considera que a medida que van soltando las cartas se sueltan de 1 a 4 como mucho
            y junto con las variables booleanas se controla que si una parte llego al limite automaticamente se soltaran 
            todas las cartas restantes de la otra parte*/
            for (int i = 0; i < mazo.length; i++) {
                n1 = (int) (Math.random() * (4 - 1 + 1) + 1);//generando de 1 a 4 aleatoriamente
                n2 = (int) (Math.random() * (4 - 1 + 1) + 1); //generando de 1 a 4 aleatoriamente
                //Utilize dos random de 1 a 4 para hacer mas aleatorio y eficaz (mas real) el repartido de cartas de ambas partes
                if ((cartaPrim == true && limiteaux1 == false) || (limiteaux1 == false && limiteaux2 == true)) {
                    mazo[i] = aux1[indice1];
//                    System.out.println("Aux1"); con esta linea va viendo si entro carta de esta parte.Es solo para chequaear
                    indice1++;
                    if (indice1 == aux1.length) {
                        limiteaux1 = true;
                        cartaPrim=false;
                    }
                    if (indice1 % n1 == 0 || indice1 % n2 == 0) {
                        cartaPrim = false;
                    }

                } else if ((cartaPrim == false && limiteaux2 == false) || (limiteaux1 == true && limiteaux2 == false)) {
                    mazo[i] = aux2[indice2];
//                    System.out.println("Aux2");con esta linea va viendo si entro carta de esta parte.Es solo para chequaear
                    indice2++;
                    if (indice2 %n1==0 || indice2%n2==0) {
                        cartaPrim = true;
                    }
                    if (indice2 == aux2.length) {
                        limiteaux2 = true;
                        cartaPrim = true;
                    }

                }

            }

        }
    }

}
