package modele;

/**
 * La classe CaseModele représente une case d'une grille de jeu.
 * Chaque case a une valeur entière.
 */
public class CaseModele {
    private int valeur;

    /**
     * Constructeur de la classe CaseModele.
     * @param valeur La valeur initiale de la case.
     */
    public CaseModele(int valeur){
        this.valeur = valeur;
    }
    
    /**
     * Obtient la valeur actuelle de la case.
     * @return La valeur de la case.
     */
    public int getValeur(){
        return this.valeur;
    }
    
    /**
     * Définit la valeur de la case.
     * @param valeur La nouvelle valeur de la case.
     */
    public void setValeur(int valeur){
        this.valeur = valeur;
    }
    
    /**
     * Renvoie une représentation textuelle de la case.
     * @return La valeur de la case sous forme de chaîne de caractères.
     */
    @Override
    public String toString(){
        return " " + this.getValeur();
    }
}
