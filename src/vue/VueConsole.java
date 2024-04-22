package vue;

import util.EcouteurModele;
import modele.GrilleModele;

public class VueConsole implements EcouteurModele{
   GrilleModele grille;
   
   public VueConsole(GrilleModele grille){
       this.grille = grille;
       this.grille.ajoutEcouteur(this);
   }


    @Override
    public void modeleMisAJour(Object source) {
        System.out.println(grille.toString());
        
    }
}