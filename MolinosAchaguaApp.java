import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MolinosAchaguaApp {

    public static void main(String[] args) {
        // Crear el frame principal
        JFrame frame = new JFrame("Molinos Achagua - Cálculo del Peso Neto");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Colores y fuentes principales
        Color primaryColor = new Color(54, 57, 63);
        Color secondaryColor = new Color(32, 34, 37);
        Color accentColor = new Color(114, 137, 218);
        Color hoverAccentColor = new Color(90, 103, 192);
        Color textColor = new Color(240, 240, 240);
        Font mainFont = new Font("SansSerif", Font.PLAIN, 16);
        Font titleFont = new Font("SansSerif", Font.BOLD, 24);

        // Panel principal con diseño moderno y colores personalizados
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(secondaryColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        
        // Panel superior para el título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(primaryColor);
        JLabel titleLabel = new JLabel("Cálculo del Peso Neto");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(textColor);
        titlePanel.add(titleLabel);
        
        // Etiqueta y campo para el Peso Bruto (Pb)
        JLabel labelPb = new JLabel("Peso Bruto (Pb):");
        labelPb.setFont(mainFont);
        labelPb.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelPb, gbc);

        JTextField textPb = new JTextField(12);
        textPb.setFont(mainFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        textPb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(textPb, gbc);

        // Etiqueta y campo para el Peso Tara (Pt)
        JLabel labelPt = new JLabel("Peso Tara (Pt):");
        labelPt.setFont(mainFont);
        labelPt.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelPt, gbc);

        JTextField textPt = new JTextField(12);
        textPt.setFont(mainFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        textPt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(textPt, gbc);

        // Etiqueta y campo para el Peso Neto (Pn)
        JLabel labelPn = new JLabel("Peso Neto (Pn):");
        labelPn.setFont(mainFont);
        labelPn.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelPn, gbc);

        JTextField textPn = new JTextField(12);
        textPn.setFont(mainFont);
        textPn.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        textPn.setBackground(new Color(220, 220, 220));
        textPn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 2, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(textPn, gbc);

        // Botón de cálculo con estilo moderno y animación hover
        JButton calcButton = new JButton("Calcular");
        calcButton.setFont(mainFont);
        calcButton.setBackground(accentColor);
        calcButton.setForeground(Color.WHITE);
        calcButton.setFocusPainted(false);
        calcButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        calcButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(calcButton, gbc);

        // Animación hover para el botón
        calcButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                calcButton.setBackground(hoverAccentColor);
            }

            public void mouseExited(MouseEvent evt) {
                calcButton.setBackground(accentColor);
            }
        });

        // Acción del botón de cálculo
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double Pb = Double.parseDouble(textPb.getText());
                    double Pt = Double.parseDouble(textPt.getText());

                    if (Pb < 0 || Pt < 0) {
                        throw new IllegalArgumentException("Los valores no pueden ser negativos.");
                    }

                    double Pn = Pb - Pt;
                    textPn.setText(String.format("%.2f", Pn));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Panel de pie de página con branding
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(primaryColor);
        JLabel footerLabel = new JLabel("Molinos Achagua © 2024 - Todos los derechos reservados");
        footerLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        footerLabel.setForeground(textColor);
        footerPanel.add(footerLabel);
        
        // Añadir el panel al frame
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Configurar el frame en el centro de la pantalla y hacerlo visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
