package vue;

import modele.DirectionEnum;
import modele.GrilleModele;
import util.EcouteurModele;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

    /**
     * La classe DiviserImage représente l'interface graphique pour la division d'une image en sous-images.
     * Elle permet à l'utilisateur de jouer avec les sous-images divisées.
     */
public class DiviserImage extends JPanel implements EcouteurModele, MouseListener, KeyListener, MouseMotionListener {

    // Nombre de lignes dans la grille d'images
    private int rows;

    // Nombre de colonnes dans la grille d'images
    private int columns;

    // Tableau pour stocker les sous-images de la grille
    private BufferedImage imageOriginale;

     // Image originale à diviser en sous-images
    private BufferedImage[][] grilleDesImages;

    // Modèle de grille associé à l'interface de division d'image
    private GrilleModele modele;
    
      // Taille des cases dans la grille d'images
    private int tailleCase;

    /**
     * Constructeur de la classe DiviserImage.
     * @param rows Nombre de lignes pour la grille d'images.
     * @param columns Nombre de colonnes pour la grille d'images.
     * @param imagePath Chemin vers l'image à diviser.
     * @param modele Le modèle de la grille associé à l'interface.
     */
    public DiviserImage(int rows, int columns, String imagePath, GrilleModele modele) {
        this.rows = rows;
        this.columns = columns;
        this.modele = modele;
        this.modele.ajoutEcouteur(this);
        grilleDesImages = new BufferedImage[rows][columns];

        try {
            File file = new File(imagePath);
            java.net.URL url = file.toURI().toURL();
            InputStream is = url.openStream();
            imageOriginale = ImageIO.read(is);
            division();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tailleCase = Math.min(imageOriginale.getWidth() / columns, imageOriginale.getHeight() / rows);

        setLayout(new GridLayout(rows, columns)); // Définit un gestionnaire de disposition pour le JPanel
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int valeur = modele.getGrille()[i][j].getValeur();
                if (valeur != 0) { // Vérifie si la valeur est différente de zéro
                    // Crée un JLabel pour chaque case
                    JLabel label = new JLabel(new ImageIcon(grilleDesImages[i][j]));
                    label.setText(String.valueOf(valeur)); // Ajoute la valeur comme texte sur l'image
                    label.setHorizontalTextPosition(JLabel.CENTER);
                    label.setVerticalTextPosition(JLabel.CENTER);
                    label.setFont(new Font("Arial", Font.BOLD, 20));
                    label.setForeground(Color.WHITE);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);

                    // Ajoute le JLabel au JPanel
                    add(label);
                }
            }
        }

        addMouseListener(this);
        //addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setBackground(Color.DARK_GRAY);
    }

    /**
     * Divise l'image originale en sous-images pour la grille.
     */
    public void division() {
        int subimage_Width = imageOriginale.getWidth() / columns;
        int subimage_Height = imageOriginale.getHeight() / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grilleDesImages[i][j] = imageOriginale.getSubimage(j * subimage_Width, i * subimage_Height, subimage_Width, subimage_Height);
            }
        }
    }
    

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_Z || k.getKeyCode() == KeyEvent.VK_UP) {
            DiviserImage.this.modele.bougerCaseVide(DirectionEnum.HAUT);
        } else if (k.getKeyCode() == KeyEvent.VK_Q || k.getKeyCode() == KeyEvent.VK_LEFT) {
            DiviserImage.this.modele.bougerCaseVide(DirectionEnum.GAUCHE);
        } else if (k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_RIGHT) {
            DiviserImage.this.modele.bougerCaseVide(DirectionEnum.DROITE);
        } else if (k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_DOWN) {
            DiviserImage.this.modele.bougerCaseVide(DirectionEnum.BAS);
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
        int width = getWidth();
        int height = getHeight();
        int cote = Math.min(width / columns, height / rows);
        int x = e.getX();
        int y = e.getY();
        int caseX = x / tailleCase;
        int caseY = y / tailleCase;
        
        modele.swap(caseX, caseY); // Déplace la case cliquée
        }
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void modeleMisAJour(Object source) {
        repaint();
    }
}
