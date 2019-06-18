/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stream;

import blackjack.Jugador;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DanielTapia
 */
public class Stream implements Serializable {

    public static ArrayList<Jugador> jugadores = new ArrayList();

    public static void guardarDatos() throws FileNotFoundException, IOException {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("Data.txt"));
            System.out.println("se guardó un objeto compuesto\n");
            salida.writeObject(jugadores);//writeUnshared(this);
            salida.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
            e.getMessage();
        } catch (IOException e) {
            System.out.println("Hubo un error al guardar");
            e.getMessage();
        }
    }

    public static boolean corroborar(String nombre) {
        boolean existente = false;
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).nombre.equals(nombre)) {
                existente = true;
                return existente;

            }
        }
        return existente;
    }

    public static void cargarDatos() throws FileNotFoundException, IOException,
            ClassNotFoundException, EOFException {

        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("Data.txt"));

        jugadores = (ArrayList<Jugador>) entrada.readObject();
        System.out.println("Datos cargados con exito");
        entrada.close();

    }
    
    public static boolean corroborarUsuarioyPassword(String nombre,String password){
        boolean existente=false;
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).nombre.equals(nombre) && jugadores.get(i).password.equals(password)){
                existente=true;
                return existente;
            }
        }
        return existente; 
    }
    
    public static Jugador playerIngresado(String nombre){
        Jugador ingresado=null;
         for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).nombre.equals(nombre)){
                ingresado=jugadores.get(i);
            }
        }
         return ingresado;
    }
}
