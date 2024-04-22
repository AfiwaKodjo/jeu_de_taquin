package ui;

import javax.swing.*;

import modele.GrilleModele;
import vue.DiviserImage;
import vue.VueGrille;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe GrilleUI représente l'interface utilisateur graphique principale du jeu de Taquin.
 * Elle contient une grille de jeu et permet à l'utilisateur d'interagir avec celle-ci.
 */
public class GrilleUI extends JFrame {
    // Modèle de grille associé à l'interface utilisateur
    private GrilleModele grille;
     // Vue de la grille affichée dans l'interface utilisateur
    private VueGrille vueGrille;
    // Interface graphique pour diviser une image
    private DiviserImage diviserImageUI;

    /**
     * Constructeur de la classe GrilleUI.
     * @param grille La grille de jeu associée à l'interface.
     */
    public GrilleUI(GrilleModele grille) {
        super("Game of Taquin");
        this.grille = grille;
        this.vueGrille = new VueGrille(grille);

       // Création de la barre verticale en haut de la grille
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Création du bouton pour accéder à l'interface DiviserImage
        JButton diviserImageButton = new JButton("Avec image");

        // Ajout de l'ActionListener au bouton
        diviserImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirInterfaceDiviserImage();
            }
});

    // Ajout du bouton à la barre verticale
    topPanel.add(diviserImageButton);

    // Ajout d'un espace vide entre les boutons
    topPanel.add(Box.createHorizontalStrut(10)); // Espacement horizontal

    // Création du deuxième bouton
    JButton autreBouton = new JButton("Retour");

    // Ajout de l'ActionListener au deuxième bouton
    autreBouton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            retournerA_vueGrille();
            
        }
});

        // Ajout du deuxième bouton à la barre verticale
        topPanel.add(autreBouton);

        // Ajout de la barre verticale et de la grille à la JFrame principale
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(vueGrille, BorderLayout.CENTER);

        // Configuration de la JFrame
        this.setSize(700, 750); // Augmenté la hauteur pour tenir compte de la barre verticale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * Ouvre l'interface DiviserImage pour permettre à l'utilisateur de choisir une image à utiliser dans la grille.
     */
    private void ouvrirInterfaceDiviserImage() {
        // Créer et afficher l'interface DiviserImage
        diviserImageUI = new DiviserImage(3, 3, "src/image/image1.jpg", grille);

         // Remplace VueGrille par DiviserImage
        this.remove(vueGrille); // Supprime VueGrille
        this.add(diviserImageUI, BorderLayout.CENTER); // Ajoute DiviserImage à la même position
    
        // Rafraîchit l'affichage
        this.revalidate();
        this.repaint();
    }

    /**
     * Retourne à l'interface VueGrille après avoir utilisé l'interface DiviserImage.
     */
    private void retournerA_vueGrille() {
      
        // Remplace VueGrille par DiviserImage
        if (diviserImageUI != null) {
        this.remove(diviserImageUI); // Supprime VueGrille
        this.add(vueGrille, BorderLayout.CENTER); // Ajoute DiviserImage à la même position
    
        // Rafraîchit l'affichage
        this.revalidate();
        this.repaint();
    }

}

    
    
}
