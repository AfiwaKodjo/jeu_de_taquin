package ui;

import javax.swing.JFrame;

import modele.DirectionEnum;
import modele.GrilleModele;


public class MainGrille {

    public static void main(String[] args) {
        //Actuellement pour modifier la taille de la grille on doit modifier dim et nbColors de la classe VueGrille
        //puis modifier également le nbreLigne et dans notre mainGrille; l'objectif est de  trouver un moyen d'optimiser cela
        GrilleModele grilleG = new GrilleModele(3,3);
        grilleG.creerGrille();
        grilleG.melanger(2);
        grilleG.bougerCaseVide(DirectionEnum.HAUT);
        grilleG.bougerCaseVide(DirectionEnum.GAUCHE);
        grilleG.bougerCaseVide(DirectionEnum.BAS);
        grilleG.bougerCaseVide(DirectionEnum.DROITE);
        JFrame dgrille = new GrilleUI(grilleG);

    }
    
}
