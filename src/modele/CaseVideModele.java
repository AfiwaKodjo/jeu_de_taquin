package modele;

/**
 * La classe CaseVideModele représente une case vide dans une grille de jeu.
 * Elle hérite de la classe CaseModele.
 */
public class CaseVideModele extends CaseModele {

    /**
     * Constructeur de la classe CaseVideModele.
     * @param valeur La valeur initiale de la case vide.
     */
    public CaseVideModele(int valeur) {
        super(valeur);
    }

    /**
     * Renvoie une représentation textuelle de la case vide.
     * Pour une case vide, la représentation est une chaîne vide.
     * @return Une chaîne vide.
     */
    @Override
    public String toString(){
        return " ";
    }   
}
