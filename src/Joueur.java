public class Joueur {
    private CaseTraversable c;
    private int resistance;
    private int cles;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
    }

    public void ajouterResistance(int chaleur){
        resistance = resistance - chaleur;
        if(resistance < 0){resistance = 0;}
    }

    public void ajouterCles(int nbCles){
        cles += nbCles;
    }

    public int getCles(){
        return cles;
    }

    public Case getC(){
        return c;
    }

    public void bouge(Case cible) {
        if (cible.estTraversable()) {
            int chaleurCase = cible.getChaleur();

            if (cible instanceof Hall) {
                chaleurCase = ((Hall) cible).getChaleur();
            } else if (cible instanceof Sortie) {
                chaleurCase = ((Sortie) cible).getChaleur();
            } else if (cible instanceof Porte && ((Porte) cible).isOuverte()) {
                chaleurCase = 0;
            }
            ajouterResistance(chaleurCase);

            if (cible instanceof Porte && !((Porte) cible).isOuverte()) {
                if (cles > 0) {
                    ((Porte) cible).ouvrirPorte();
                    cles--; // Décrémente le nombre de clés après avoir ouvert la porte
                }
            }
            c.vide();
            cible.entre(this);
            c = (CaseTraversable) cible;
        }
    }


    public int getResistance() {
        return resistance;
    }

}
