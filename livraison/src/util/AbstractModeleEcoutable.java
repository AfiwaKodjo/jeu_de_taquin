package util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModeleEcoutable {
    public List<EcouteurModele> ecouteurs = new ArrayList<EcouteurModele>();

     public void ajoutEcouteur(EcouteurModele e) {
        this.ecouteurs.add(e);
    }

    public void retraitEcouteur(EcouteurModele e) {
        this.ecouteurs.remove(e);
    }

    protected void fireChangement() {
        for (EcouteurModele e : this.ecouteurs) {
            e.modeleMisAJour(this);
        }
    }

    
}
