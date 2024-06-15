package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class ColorGrid {
    private static int taillecase;
    private static int largeur;
    private static int hauteur;

    private static Color[][] colors;

    private static JFrame frame;
    private static CellPanel panel;

    public static void initialiser(int taillecase, int largeur, int hauteur) {
        ColorGrid.taillecase = taillecase;
        ColorGrid.largeur = largeur;
        ColorGrid.hauteur = hauteur;

        colors = new Color[hauteur][largeur];

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                colors[i][j] = Color.WHITE;
            }
        }

        panel = new CellPanel(colors, taillecase, largeur, hauteur);
        frame = new JFrame("ColorGrid");
        frame.getContentPane().add(panel);
        frame.setSize(10 + largeur * taillecase, 25 + hauteur * taillecase);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setCellColor(int r, int c, Color newColor, int cycle) {
        // Display cycle text on the graphical centered and big size
        Graphics g = panel.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largeur * taillecase, 25);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Cycle: " + cycle, largeur * taillecase / 2 - 50, 20);

        if (r < 0 || r >= hauteur || c < 0 || c >= largeur) {
            throw new RuntimeException("Invalid cell coordinates: (" + r + ", " + c + ")");
        }

        Color prevColor = colors[r][c];
        colors[r][c] = newColor;
        if (!prevColor.equals(newColor)) {
            panel.drawCell(r, c);
        }
    }

    public static void pause(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void stop() {
        frame.dispose();
    }
}
