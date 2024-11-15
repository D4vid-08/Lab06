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

public class JuegoAzarGUI extends JFrame {
    public JuegoAzarGUI() {
        setTitle("Juegos de Azar");
        setSize(800, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnDados = new JButton(new ImageIcon(escalarImagen("/resources/dados_menu.png", 400, 200)));
        JButton btnTragamonedas = new JButton(new ImageIcon(escalarImagen("/resources/tragamonedas_menu.png", 400, 200)));

        JLabel lblDados = new JLabel("Juego de Dados", SwingConstants.CENTER);
        JLabel lblTragamonedas = new JLabel("Juego Tragamonedas", SwingConstants.CENTER);

        btnDados.addActionListener(e -> new JuegoDadosGUI());
        btnTragamonedas.addActionListener(e -> new JuegoTragamonedasGUI());

        JPanel panelDados = new JPanel(new BorderLayout());
        JPanel panelTragamonedas = new JPanel(new BorderLayout());

        panelDados.add(btnDados, BorderLayout.CENTER);
        panelDados.add(lblDados, BorderLayout.SOUTH);

        panelTragamonedas.add(btnTragamonedas, BorderLayout.CENTER);
        panelTragamonedas.add(lblTragamonedas, BorderLayout.SOUTH);

        panel.add(panelDados);
        panel.add(panelTragamonedas);

        add(panel);
        setVisible(true);
    }

    private Image escalarImagen(String ruta, int ancho, int alto) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        return icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JuegoAzarGUI::new);
    }
}
