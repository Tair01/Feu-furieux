import java.util.ArrayList;

public abstract class Case  {
    public final int lig, col;
    public Case(int l, int c) {
        this.lig = l;
        this.col = c;
    }
    public abstract boolean estTraversable();
    public abstract void vide();
    public abstract void entre(Joueur j);
    public abstract Joueur getJ();
    public abstract int  getChaleur();
}
