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
    public abstract int getEnFeu();
    public abstract void mettreEnFeu();
    public abstract void eteindreEnFeu();
    public void ajouterChaleur(int ajustement) {
        chaleur += ajustement;
        chaleur = Math.max(0, Math.min(10, chaleur));
    }
    public int getChaleur(){return chaleur;}

}
