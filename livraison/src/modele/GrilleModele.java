package modele;

import java.util.Random;

import util.AbstractModeleEcoutable;

/**
 * La classe GrilleModele représente le modèle d'une grille de jeu.
 * Elle contient les méthodes nécessaires pour créer, manipuler et vérifier une grille de jeu.
 */

public class GrilleModele extends AbstractModeleEcoutable{
    private CaseModele[][] grille;
    private int nbreLigne;
    private int nbreColonne;
    private int posXCaseVide;
    private int posYCaseVide;
    private CaseModele caseVide;

     /**
     * Constructeur de la classe GrilleModele.
     * @param nbreLigne Le nombre de lignes de la grille.
     * @param nbreColonne Le nombre de colonnes de la grille.
     */
    public GrilleModele(int nbreLigne, int nbreColonne){
        super();
       this.nbreLigne = nbreLigne;
       this.nbreColonne = nbreColonne;
    }
     /**
     * Retourne la grille de jeu.
     * @return La grille de jeu représentée par un tableau 2D de CaseModele.
     */
    public CaseModele[][] getGrille(){
        return this.grille;
    }
   
     /**
     * Définit la grille de jeu.
     * @param grille La grille de jeu représentée par un tableau 2D de CaseModele.
     */
    public void setGrille(CaseModele[][] grille){
        this.grille = grille;

    }

    public int getNbreLigne(){
        return this.nbreLigne;
    }
    public int getNbreColonne(){
        return this.nbreColonne;
    }


    /**
     * Retourne la position en X de la case vide.
     * @return La position en X de la case vide.
     */
    public int getPosXCaseVide() {
        return posXCaseVide;
    }

    /**
     * Retourne la position en Y de la case vide.
     * @return La position en Y de la case vide.
     */
    public int getPosYCaseVide() {
        return posYCaseVide;
    }


   /**
     * Crée une nouvelle grille de jeu.
     * Cette méthode initialise la grille avec des cases numérotées et place la case vide à la position finale.
     */
    public void creerGrille(){
        this.grille = new CaseModele[this.nbreLigne][this.nbreColonne];
        int valeurCase = 1; //valeur pour la première case
        //Affecter un chiffre à chaque case de la grille
        for(int i = 0; i< this.nbreLigne; i++){
            for(int j = 0; j< this.nbreColonne; j++){
                this.grille[i][j] = new CaseModele(valeurCase);
                valeurCase++;
            }
        }
        this.caseVide = new CaseModele(0);
        this.grille[nbreLigne-1][nbreColonne-1] = this.caseVide;
        this.posXCaseVide = this.nbreLigne-1;
        this.posYCaseVide = this.nbreColonne-1;
    }


    /**
     * Déplace la case vide dans une direction spécifiée.
     * @param sens La direction dans laquelle déplacer la case vide.
     */
    public void bougerCaseVide(DirectionEnum sens) {
        CaseModele caseM; // Variable temporaire pour stocker la case à échanger
        if (sens == DirectionEnum.BAS && posYCaseVide < nbreLigne - 1) {
            caseM = grille[posXCaseVide][posYCaseVide + 1];
            grille[posXCaseVide][posYCaseVide + 1] = caseVide;
            grille[posXCaseVide][posYCaseVide] = caseM;
            posYCaseVide++;
        } else if (sens == DirectionEnum.GAUCHE && posXCaseVide > 0) {
            caseM = grille[posXCaseVide - 1][posYCaseVide];
            grille[posXCaseVide - 1][posYCaseVide] = caseVide;
            grille[posXCaseVide][posYCaseVide] = caseM;
            posXCaseVide--;
        } else if (sens == DirectionEnum.HAUT && posYCaseVide > 0) {
            caseM = grille[posXCaseVide][posYCaseVide - 1];
            grille[posXCaseVide][posYCaseVide - 1] = caseVide;
            grille[posXCaseVide][posYCaseVide] = caseM;
            posYCaseVide--;
        } else if (sens == DirectionEnum.DROITE && posXCaseVide < nbreColonne - 1) {
            caseM = grille[posXCaseVide + 1][posYCaseVide];
            grille[posXCaseVide + 1][posYCaseVide] = caseVide;
            grille[posXCaseVide][posYCaseVide] = caseM;
            posXCaseVide++;
        }
        this.fireChangement();
    }

     /**
     * Échange la position de la case vide avec celle d'une autre case.
     * @param x La position en X de la case à échanger.
     * @param y La position en Y de la case à échanger.
     */
    public void swap(int x, int y){
        if(Math.abs(x - posXCaseVide) + Math.abs(y - posYCaseVide) != 1) {
            return;
        }
        CaseModele caseM = grille[x][y];
        grille[x][y] = caseVide;
        grille[posXCaseVide][posYCaseVide] = caseM;
        posXCaseVide = x;
        posYCaseVide = y;
        this.fireChangement();
    }


    /**
     * Mélange les cases de la grille tout en garantissant qu'elle reste résoluble.
     * @param n Le nombre de mélanges à effectuer.
     */
    public void melanger(int n){
        Random rand = new Random();
        for(int i=0; i< n; i++) {
            DirectionEnum direction;
            boolean mouvValide = false;
            while (!mouvValide) {
                //Créer un nombre aléatoire entre 0 et 3
                int nbreRandom = rand.nextInt(4);
                if(nbreRandom == 0) {
                    direction = DirectionEnum.HAUT;
                }else if(nbreRandom == 1) {
                    direction = DirectionEnum.GAUCHE;
                }else if (nbreRandom == 2) {
                    direction = DirectionEnum.GAUCHE;
                }
                else {
                    direction = DirectionEnum.DROITE;
                }

                //Vérifier si le mouvement est valide ou non
                if (mouvementValide(direction)) {
                    //Si le mouvement est valide, on le bouge
                    bougerCaseVide(direction);
                    mouvValide = true;
                }
                
            }
        }

    }

    /**
     * Vérifie si un mouvement dans une direction spécifiée est valide.
     * @param direction La direction du mouvement.
     * @return true si le mouvement est valide, false sinon.
     */
    public boolean mouvementValide(DirectionEnum direction) {
        boolean res = false;
        switch (direction) {
            case DROITE:
                if (posYCaseVide < nbreLigne - 1) {
                    res = true;
                }
                break;
            case GAUCHE:
                if (posYCaseVide > 0) {
                    res = true;
                }
                break;
            case BAS:
                if (posXCaseVide < nbreColonne - 1) {
                    res = true;
                }
                break;
            case HAUT:
                if (posXCaseVide > 0) {
                    res = true;
                }
                break;
            default:
                break;
        }
        return res;
    
  
    }

    /**
     * Vérifie si la grille est dans un état final (résolue).
     * @return true si la grille est résolue, false sinon.
     */
    public boolean estFini(){
        int compteur = 1;
        for (int i = 0; i < nbreLigne; i++){
            for(int j = 0; j < nbreColonne; j++){
                //Si c'est la dernière case et que cette dernière est la case vide
                if((i == nbreLigne - 1) && (j == nbreColonne - 1) && (this.grille[i][j].getValeur()==0)){
                    continue;
                }
                if(this.grille[i][j].getValeur() != compteur){
                    return false;
                }
                compteur++;
                
            }
        }
        return true;

    }

    /**
     * Récupère la valeur située dans une case de la grille.
     * @param i La position en X de la case.
     * @param j La position en Y de la case.
     * @return La valeur de la case, ou null si les indices sont invalides.
     */
    public Integer getValeurCase(int i, int j){
        if(i > this.nbreLigne - 1 || j < this.nbreColonne -1 || i < 0  || j < 0 ){
            return null;
        }
        return grille[i][j].getValeur();
    }

    /**
     * Renvoie une représentation textuelle de la grille.
     * @return Une chaîne de caractères représentant la grille.
     */
    public String toString(){
        String res = "";
        for(int i = 0; i< this.nbreLigne; i++){
            for(int j = 0; j< this.nbreColonne; j++){
                res += this.grille[i][j].toString();
                res += " ";

            }
            res += " \n";
        }
        return res;
    }



 
}
