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
public class Carta {

    int valor;
    int valor2; //caso especial para el as
    String nombre;
    int numero;
    int pinta;

    public Carta() {
    }

    public Carta(int numero, int pinta) {
        this.numero = numero;
        this.pinta = pinta;
    }

    public void generarCarta(int v) {
        switch (v) {
            case 1:
                this.pinta = 1;
                this.numero = 1;
                break;
            case 2:
                this.pinta = 1;
                this.numero = 2;
                break;
            case 3:
                this.pinta = 1;
                this.numero = 3;
                break;
            case 4:
                this.pinta = 1;
                this.numero = 4;
                break;
            case 5:
                this.pinta = 1;
                this.numero = 5;
                break;
            case 6:
                this.pinta = 1;
                this.numero = 6;
                break;
            case 7:
                this.pinta = 1;
                this.numero = 7;
                break;
            case 8:
                this.pinta = 1;
                this.numero = 8;
                break;
            case 9:
                this.pinta = 1;
                this.numero = 9;
                break;
            case 10:
                this.pinta = 1;
                this.numero = 10;
                break;
            case 11:

                this.pinta = 1;
                this.numero = 11;
                break;
            case 12:

                this.pinta = 1;
                this.numero = 12;
                break;
            case 13:

                this.pinta = 1;
                this.numero = 13;
                break;
            case 14:
                this.pinta = 2;
                this.numero = 1;
                break;
            case 15:
                this.pinta = 2;
                this.numero = 2;
                break;
            case 16:
                this.pinta = 2;
                this.numero = 3;
                break;
            case 17:
                this.pinta = 2;
                this.numero = 4;
                break;
            case 18:
                this.pinta = 2;
                this.numero = 5;
                break;
            case 19:
                this.pinta = 2;
                this.numero = 6;
                break;
            case 20:
                this.pinta = 2;
                this.numero = 7;
                break;
            case 21:
                this.pinta = 2;
                this.numero = 8;
                break;
            case 22:
                this.pinta = 2;
                this.numero = 9;
                break;
            case 23:

                this.pinta = 2;
                this.numero = 10;
                break;
            case 24:

                this.pinta = 2;
                this.numero = 11;
                break;
            case 25:

                this.pinta = 2;
                this.numero = 12;
                break;
            case 26:

                this.pinta = 2;
                this.numero = 13;
                break;
            case 27:
                this.pinta = 3;
                this.numero = 1;
                break;
            case 28:
                this.pinta = 3;
                this.numero = 2;
                break;
            case 29:
                this.pinta = 3;
                this.numero = 3;
                break;
            case 30:
                this.pinta = 3;
                this.numero = 4;
                break;
            case 31:
                this.pinta = 3;
                this.numero = 5;
                break;
            case 32:
                this.pinta = 3;
                this.numero = 6;
                break;
            case 33:
                this.pinta = 3;
                this.numero = 7;
                break;
            case 34:
                this.pinta = 3;
                this.numero = 8;
                break;
            case 35:
                this.pinta = 3;
                this.numero = 9;
                break;
            case 36:
                this.pinta = 3;
                this.numero = 10;
                break;
            case 37:

                this.pinta = 3;
                this.numero = 11;
                break;
            case 38:

                this.pinta = 3;
                this.numero = 12;
                break;
            case 39:

                this.pinta = 3;
                this.numero = 13;
                break;
            case 40:
                this.pinta = 4;
                this.numero = 1;
                break;
            case 41:
                this.pinta = 4;
                this.numero = 2;
                break;
            case 42:
                this.pinta = 4;
                this.numero = 3;
                break;
            case 43:
                this.pinta = 4;
                this.numero = 4;
                break;
            case 44:
                this.pinta = 4;
                this.numero = 5;
                break;
            case 45:
                this.pinta = 4;
                this.numero = 6;
                break;
            case 46:
                this.pinta = 4;
                this.numero = 7;
                break;
            case 47:
                this.pinta = 4;
                this.numero = 8;
                break;
            case 48:
                this.pinta = 4;
                this.numero = 9;
                break;
            case 49:
                this.pinta = 4;
                this.numero = 10;
                break;
            case 50:

                this.pinta = 4;
                this.numero = 11;
                break;
            case 51:

                this.pinta = 4;
                this.numero = 12;
                break;
            case 52:

                this.pinta = 4;
                this.numero = 13;
                break;

        }
        switch (numero) {
            case 1:
                this.nombre = "As";
                this.valor = 11;
                this.valor2 = 1;
                break;
            case 2:
                this.valor = 2;
                break;
            case 3:
                this.valor = 3;
                break;
            case 4:
                this.valor = 4;
                break;
            case 5:
                this.valor = 5;
                break;
            case 6:
                this.valor = 6;
                break;
            case 7:
                this.valor = 7;
                break;
            case 8:
                this.valor = 8;
                break;
            case 9:
                this.valor = 9;
                break;
            case 10:
                this.valor = 10;
                break;
            case 11:
                this.valor = 10;
                this.nombre = "J";
                break;
            case 12:
                this.valor = 10;
                this.nombre = "Q";
                break;
            case 13:
                this.valor = 10;
                this.nombre = "K";
                break;

        }
    }

    public void mostrarCarta() {
        String pin = "";
        int num = numero;
        if (this.pinta == 1) {
            pin = "Picas";
        }
        if (this.pinta == 2) {
            pin = "Corazones";
        }
        if (this.pinta == 3) {
            pin = "Treboles";
        }
        if (this.pinta == 4) {
            pin = "Diamantes";
        }
        if (num > 10 || num==1) {
            System.out.println(this.nombre + " de " + pin);
        } else {
            System.out.println(num + " de " + pin);
        }
    }
}
