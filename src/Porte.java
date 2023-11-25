public class Porte extends CaseTraversable{
    private boolean ouverte;
    public Porte(int l, int c, boolean ouverte){
        super(l,c);
        this.ouverte = ouverte;
    }
    public boolean isOuverte() {
        return ouverte;
    }
    public void setOuverte(boolean ouverte) {
        this.ouverte = ouverte;
    }
    @Override
    public boolean estTraversable() {
        return false;
    }

    public void ouvrirPorte(){ouverte = true;}
    public void fermetPorte(){ouverte = false;}
}
