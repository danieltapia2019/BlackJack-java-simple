/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import Stream.Stream;
import blackjack.Juego;
import blackjack.JuegoBeta;
import blackjack.Jugador;
import controlador.Controlador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author DanielTapia
 */
public class PanelInicio extends JPanel implements Serializable {

    public JLabel imagenFondo;
    public JLabel titulo;
    public JPanel panel;
    public JButton btnIngresar;
    public JButton btnRegistrar;
    public JButton btnJugarInvitado;
    public JPanel uno; //subpanel de opciones 
    public JPanel dos; //subpanel de la opcion ingresar
    public JPanel tres; //subpanel de la opcion registrar
    VentanaPrincipal ventanaPadre;

    public PanelInicio(VentanaPrincipal ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(null);
        cargarPaneles(); //cargamos los componentes de cada panel
        setSize(VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        setLayout(null);
        titulo = new JLabel();
        titulo.setText("Bienvenidos a BlackJack");
        titulo.setBounds(220, 20, 200, 30);
        panel = uno;
        panel.setBounds(220, 100, 200, 200);

        add(titulo);
        add(panel);

    }

    private void cargarPaneles() {
        cargarPanel1();
        cargarPanel2();
        cargarPanel3();
    }

    private void cargarPanel1() {
        uno = new JPanel();
        uno.setLayout(null);
        uno.setBackground(Color.GRAY);
        uno.setSize(200, 200);
        JButton ingresar = new JButton("Ingresar");
        JButton registrar = new JButton("Registrarse");
        JButton jugarInvitado = new JButton("Jugar (Invitado)");

        ingresar.setBounds(50, 30, 100, 20);
        registrar.setBounds(40, 90, 120, 20);
        jugarInvitado.setBounds(35, 150, 130, 20);

        uno.add(ingresar);
        uno.add(registrar);
        uno.add(jugarInvitado);

        ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                panel = dos;
                panel.setBounds(220, 100, 200, 200);
                add(panel);
                repaint();
            }
        });
        registrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                panel = tres;
                panel.setBounds(220, 100, 200, 200);
                add(panel);
                repaint();
            }
        });
        jugarInvitado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.p1 = false;
                ventanaPadre.dispose();
                Controlador c=new Controlador(new VentanaPrincipal(new JuegoBeta()));
//                VentanaPrincipal v = new VentanaPrincipal(new JuegoBeta());

//             
//                ventanaPadre.getContentPane().add(ventanaPadre.panelJuego);
//                ventanaPadre.revalidate();
            }
        });

    }

    private void cargarPanel2() {
        dos = new JPanel();
        dos.setLayout(null);
        dos.setBackground(Color.GRAY);
        dos.setSize(200, 200);
        JButton listo = new JButton("Listo");
        JButton atras = new JButton("Atras");
        JLabel texto1 = new JLabel("Usuario:");
        JLabel texto2 = new JLabel("Contraseña:");
        JPasswordField contraseña = new JPasswordField();
        JTextField usuario = new JTextField();       
        texto1.setBounds(70, 5, 80, 20);
        usuario.setBounds(40, 35, 120, 20);
        texto2.setBounds(60, 70, 80, 20);
        contraseña.setBounds(40, 105, 120, 20);
        listo.setBounds(20, 150, 80, 20);
        atras.setBounds(100, 150, 80, 20);
        dos.add(texto1);
        dos.add(texto2);
        dos.add(usuario);
        dos.add(contraseña);
        dos.add(listo);
        dos.add(atras);
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                panel = uno;
                panel.setBounds(220, 100, 200, 200);

                add(panel);

                repaint();
            }
        });
        listo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (usuario.getText().equals("") || contraseña.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Aun no ha completado los campos correctamente");
                } else if (Stream.corroborarUsuarioyPassword(usuario.getText(), contraseña.getText())) {
                    
                    VentanaPrincipal.p1 = false;
                    ventanaPadre.dispose();
                    Controlador c=new Controlador(new VentanaPrincipal(new JuegoBeta(Stream.playerIngresado(usuario.getText()))));
                    
                    
                    JOptionPane.showMessageDialog(null, "Hola " + Stream.playerIngresado(usuario.getText()).nombre + " ¿Listo/a para jugar?");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario inexistente o contraseña incorrecta");
                }
            }
        });

    }

    private void cargarPanel3() {
        tres = new JPanel();
        tres.setLayout(null);
        tres.setBackground(Color.GRAY);
        tres.setSize(200, 200);
        JButton registrar = new JButton("Registrar");
        JButton atras = new JButton("Atras");
        JLabel texto1 = new JLabel("Usuario:");
        JLabel texto2 = new JLabel("Contraseña:");
        JPasswordField contraseña = new JPasswordField();
        JTextField usuario = new JTextField();
        texto1.setBounds(70, 5, 80, 20);
        usuario.setBounds(40, 35, 120, 20);
        texto2.setBounds(60, 70, 80, 20);
        contraseña.setBounds(40, 105, 120, 20);
        registrar.setBounds(10, 150, 100, 20);
        atras.setBounds(120, 150, 70, 20);
        tres.add(texto1);
        tres.add(texto2);
        tres.add(usuario);
        tres.add(contraseña);
        tres.add(registrar);
        tres.add(atras);
        atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                panel = uno;
                panel.setBounds(220, 100, 200, 200);

                add(panel);

                repaint();
            }
        });
        registrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Stream.corroborar(usuario.getText())) {
                    JOptionPane.showMessageDialog(null, "Este usuario ya está en uso. Por favor elija otro");
                } else {
                    
                    if (usuario.getText().equals("") || contraseña.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Aun no ha completado los campos correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario creado con exito. Bienvenido/a " + usuario.getText());
                        Jugador j1 = new Jugador(usuario.getText(), contraseña.getText());
                        VentanaPrincipal.p1 = false;
                        ventanaPadre.dispose();
                        Controlador c=new Controlador(new VentanaPrincipal(new JuegoBeta(j1)));

                    }

                }

            }
        });
    }
}
