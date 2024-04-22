package vue;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import modele.GrilleModele;

public class ImageGrille extends JPanel {

    private GrilleModele grille;
    private BufferedImage[][] images; // Utilisez un tableau bidimensionnel pour stocker les sous-images

    public ImageGrille(GrilleModele grille, BufferedImage[][] images) {
        this.grille = grille;
        this.images = images;
    }

    // Méthode appelée pour dessiner le composant
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calcul de la taille de chaque cellule en fonction de la taille du composant et du nombre de lignes/colonnes de la grille
        int cellSize = Math.min(getWidth() / grille.getNbreColonne(), getHeight() / grille.getNbreLigne());

        // Parcours de chaque case de la grille
        for (int lig = 0; lig < grille.getNbreLigne(); lig++) {
            for (int col = 0; col < grille.getNbreColonne(); col++) {
                int x = col * cellSize;
                int y = lig * cellSize;
                Integer valeur = grille.getValeurCase(lig, col); // Récupération de la valeur de la case

                // Vérifie si la case n'est pas vide
                if (valeur != null && valeur != 0) {
                    BufferedImage img = images[lig][col]; // Obtenez la sous-image correspondante à partir du tableau
                    g.drawImage(img, x, y, x + cellSize, y + cellSize, 0, 0, img.getWidth(), img.getHeight(), null);
                }
            }
        }
    }
}
