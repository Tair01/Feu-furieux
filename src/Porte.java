public class Porte extends CaseTraversable{
    private boolean ouverte;
    public Porte(int l, int c, boolean ouverte){
        super(l,c);
        this.ouverte = ouverte;
    }
    public boolean isOuverte() {
        return ouverte;
    }
    @Override
    public boolean estTraversable() {
        return ouverte;
    }
    public void ouvrirPorte(){ouverte = true;}
    public void fermetPorte(){ouverte = false;}
}
