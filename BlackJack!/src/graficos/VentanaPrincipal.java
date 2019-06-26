/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import Stream.Stream;
import blackjack.Juego;
import blackjack.JuegoBeta;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DanielTapia
 */
public final class VentanaPrincipal extends JFrame implements Serializable{

    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    public static boolean p1 = true;//condicion para saber cual panel cargar

    public JLabel Titulo;
    public   PanelInicio panelInicio = new PanelInicio(this);
    public PanelJuego panelJuego = new PanelJuego(this);
    public JuegoBeta blackjack;
//    public static final ImageIcon icono = new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/IconoJuego.png"));
//    JPanel panelJuego=new PanelJuego();

    public VentanaPrincipal() {

        iniciarComponentes();

        try {
            Stream.cargarDatos();
            for(int i=0;i<Stream.jugadores.size();i++){
                System.out.println(Stream.jugadores.get(i).nombre+"----"+Stream.jugadores.get(i).password);
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos.");
        }

    }
    public VentanaPrincipal(JuegoBeta black){
        this.blackjack=black;
        iniciarComponentes();
    }

    public void iniciarComponentes() {
        setTitle("BlackJack");
        setLayout(null);
        setSize(ANCHO, ALTO);
//        setIconImage(icono.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (p1) {
            getContentPane().add(panelInicio);
        } else {
            getContentPane().add(panelJuego);
        }
        setResizable(false);
        setVisible(true);

    }

}
