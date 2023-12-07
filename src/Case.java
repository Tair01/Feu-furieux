import java.util.ArrayList;

public abstract class Case  {
    public final int lig, col;
    private int chaleur;
    public Case(int l, int c) {
        this.lig = l;
        this.col = c;
        this.chaleur = 0;
    }
    public abstract boolean estTraversable();

    public abstract void ajouterRes(int min);
    public void ajouterChaleur(int ajustement) {
        chaleur += ajustement;

        // Assurez-vous que la chaleur reste dans la plage [0, 10]
        chaleur = Math.max(0, Math.min(10, chaleur));
    }
    public int getChaleur(){return chaleur;}
}
