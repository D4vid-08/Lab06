/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juand
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JuegoTragamonedasGUI extends JFrame {
    private JLabel lblSlot1, lblSlot2, lblSlot3, lblResultado;
    private JButton btnGirar;

    public JuegoTragamonedasGUI() {
        setTitle("Juego Tragamonedas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        lblSlot1 = new JLabel();
        lblSlot2 = new JLabel();
        lblSlot3 = new JLabel();
        lblResultado = new JLabel("Presiona 'Girar' para jugar", SwingConstants.CENTER);

        btnGirar = new JButton("Girar");
        btnGirar.addActionListener(e -> girarConHilo());

        JPanel panelSlots = new JPanel();
        panelSlots.add(lblSlot1);
        panelSlots.add(lblSlot2);
        panelSlots.add(lblSlot3);

        panel.add(lblResultado);
        panel.add(panelSlots);
        panel.add(btnGirar);

        add(panel);
        setVisible(true);
    }

    private void girarConHilo() {
        btnGirar.setEnabled(false);
        new Thread(() -> {
            try {
                Random random = new Random();
                String[] simbolos = {"cereza", "campana", "diamante", "limon", "estrella"};

                for (int i = 0; i < 15; i++) { 
                    String slot1 = simbolos[random.nextInt(simbolos.length)];
                    String slot2 = simbolos[random.nextInt(simbolos.length)];
                    String slot3 = simbolos[random.nextInt(simbolos.length)];

                    lblSlot1.setIcon(new ImageIcon(escalarImagen("/resources/" + slot1 + ".png", 150, 150)));
                    lblSlot2.setIcon(new ImageIcon(escalarImagen("/resources/" + slot2 + ".png", 150, 150)));
                    lblSlot3.setIcon(new ImageIcon(escalarImagen("/resources/" + slot3 + ".png", 150, 150)));

                    Thread.sleep(100);
                }

                String slot1 = simbolos[random.nextInt(simbolos.length)];
                String slot2 = simbolos[random.nextInt(simbolos.length)];
                String slot3 = simbolos[random.nextInt(simbolos.length)];

                lblSlot1.setIcon(new ImageIcon(escalarImagen("/resources/" + slot1 + ".png", 150, 150)));
                lblSlot2.setIcon(new ImageIcon(escalarImagen("/resources/" + slot2 + ".png", 150, 150)));
                lblSlot3.setIcon(new ImageIcon(escalarImagen("/resources/" + slot3 + ".png", 150, 150)));

                if (slot1.equals(slot2) && slot2.equals(slot3)) {
                    lblResultado.setText("¡Ganaste! 🎉");
                } else {
                    lblResultado.setText("Perdiste. Intenta de nuevo.");
                }
            } catch (InterruptedException ex) {
                lblResultado.setText("Error en la simulación.");
            } finally {
                btnGirar.setEnabled(true);
            }
        }).start();
    }

    private Image escalarImagen(String ruta, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        return icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    }
}
