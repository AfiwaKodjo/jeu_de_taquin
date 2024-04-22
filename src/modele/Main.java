package modele;


import vue.*;

public class Main{
    public static void main(String[] args){
        GrilleModele grille = new GrilleModele(3,3);
        grille.creerGrille();    
        VueConsole vc = new VueConsole(grille);
        grille.melanger(2);
        grille.bougerCaseVide(DirectionEnum.HAUT);
        grille.bougerCaseVide(DirectionEnum.GAUCHE);
        grille.bougerCaseVide(DirectionEnum.BAS);
        grille.bougerCaseVide(DirectionEnum.DROITE);
        System.out.println("Résultat: "+grille.estFini());

  

    }
}