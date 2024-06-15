package InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private Color[][] colors;
    private int taillecase;
    private int largeur;
    private int hauteur;

    public CellPanel(Color[][] colors, int taillecase, int largeur, int hauteur) {
        this.colors = colors;
        this.taillecase = taillecase;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    void drawCell(int r, int c) {
        Graphics g = getGraphics();
        g.setColor(colors[r][c]);
        g.fillRect(c * taillecase, r * taillecase, taillecase, taillecase);
        g.setColor(Color.BLACK); // Color for the cell border
        g.drawRect(c * taillecase, r * taillecase, taillecase, taillecase);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the super class's paintComponent method
        if (colors == null) {
            return;
        }
        for (int r = 0; r < hauteur; r++) {
            for (int c = 0; c < largeur; c++) {
                g.setColor(colors[r][c]);
                g.fillRect(c * taillecase, r * taillecase, taillecase, taillecase);
                g.setColor(Color.BLACK); // Color for the cell border
                g.drawRect(c * taillecase, r * taillecase, taillecase, taillecase);
            }
        }
    }
}
