public class Porte extends CaseTraversable{
    private boolean ouverte;
    public Porte(int l, int c, boolean ouv){
        super(l,c);
        this.ouverte = ouv;
    }
    public boolean isOuverte() {
        return ouverte;
    }
    @Override
    public boolean estTraversable() {
        return ouverte;
    }
    public void ouvrirPorte(){ouverte = true;}
    public void fermerPorte(){ouverte = false;}
    @Override
    public boolean haveCles() {
        return false;
    } //Parce que c'est une porte
}
