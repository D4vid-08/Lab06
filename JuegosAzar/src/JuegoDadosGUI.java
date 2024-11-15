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

public class JuegoDadosGUI extends JFrame {
    private JLabel lblDado1, lblDado2, lblResultado;
    private JButton btnTirar;

    public JuegoDadosGUI() {
        setTitle("Juego de Dados");
        setSize(800, 600); 
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        lblDado1 = new JLabel();
        lblDado2 = new JLabel();
        lblResultado = new JLabel("Presiona 'Tirar Dados' para jugar", SwingConstants.CENTER);

        btnTirar = new JButton("Tirar Dados");
        btnTirar.addActionListener(e -> tirarDadosConHilo());

        JPanel panelDados = new JPanel();
        panelDados.add(lblDado1);
        panelDados.add(lblDado2);

        panel.add(lblResultado);
        panel.add(panelDados);
        panel.add(btnTirar);

        add(panel);
        setVisible(true);
    }

    private void tirarDadosConHilo() {
        btnTirar.setEnabled(false); 
        new Thread(() -> {
            try {
                Random random = new Random();
                for (int i = 0; i < 10; i++) {
                    int dado1 = random.nextInt(6) + 1;
                    int dado2 = random.nextInt(6) + 1;

                    lblDado1.setIcon(new ImageIcon(escalarImagen("/resources/dado" + dado1 + ".png", 150, 150)));
                    lblDado2.setIcon(new ImageIcon(escalarImagen("/resources/dado" + dado2 + ".png", 150, 150)));

                    Thread.sleep(100); 
                }

                int dado1 = random.nextInt(6) + 1;
                int dado2 = random.nextInt(6) + 1;

                lblDado1.setIcon(new ImageIcon(escalarImagen("/resources/dado" + dado1 + ".png", 150, 150)));
                lblDado2.setIcon(new ImageIcon(escalarImagen("/resources/dado" + dado2 + ".png", 150, 150)));

                int total = dado1 + dado2;
                lblResultado.setText(total == 7 || total == 11 ? "¡Ganaste! Total: " + total : "Perdiste. Total: " + total);

            } catch (InterruptedException ex) {
                lblResultado.setText("Error en la simulación.");
            } finally {
                btnTirar.setEnabled(true); 
            }
        }).start();
    }

    private Image escalarImagen(String ruta, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        return icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    }
}

