import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Terrain {
    private int hauteur, largeur;
    private Case[][] carte;
    private Joueur joueur;

    public int getHauteur(){
        return hauteur;
    }

    public int getLargeur(){
        return largeur;
    }

    public Case[][] getCarte() {
        return carte;
    }
    public Joueur getJoueur() { return this.joueur; }
    public Case getCase(int l, int c){
        return this.carte[l][c];
    }

    public void setDirectionJoueur(Direction direction) {
        joueur.setDirection(direction);
    }

    public Case chemin(Case courante, Direction dir){
        switch (dir){
            case nord: return this.getCase(courante.lig - 1, courante.col);
            case est: return this.getCase(courante.lig, courante.col + 1);
            case ouest: return this.getCase(courante.lig, courante.col - 1);
            case sud: return this.getCase(courante.lig + 1,courante.col);
            default: return null;
        }
    }
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            int resistanceJoueur = sc.nextInt();
            int cles = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new Mur(l, c); break;
                        case ' ': cc = new Hall(l, c); break;
                        case '+': cc = new Hall(l, c, true); break;
                        case '1': case '2': case '3': case '4':
                            cc = new Hall(l, c, (int)ch-(int)'0'); break;
                        case 'O': cc = new Sortie(l, c, 0); break;
                        case '@': cc = new Porte(l, c, false); break;
                        case '.': cc = new Porte(l, c, true); break;
                        case 'H':
                            if (this.joueur != null) throw new IllegalArgumentException("carte avec deux joueurs");
                            cc = new Hall(l, c);
                            this.joueur = new Joueur((CaseTraversable) cc, resistanceJoueur, cles);
                            ((Hall) cc).entre(joueur);
                            break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); System.exit(1); }
    }

    public ArrayList<CaseTraversable> getVoisinesTraversables(int lig, int col) {
         ArrayList<CaseTraversable> v = new ArrayList<>();
         if(lig > 0 && carte[lig -1][col].estTraversable()) {
             v.add((CaseTraversable) carte[lig - 1][col]);
         }if(lig < hauteur - 1 && carte[lig + 1][col].estTraversable()){
             v.add((CaseTraversable) carte[lig + 1][col]);
         }if(col > 0 && carte[lig][col -1].estTraversable()){
             v.add((CaseTraversable) carte[lig][col - 1]);
         }if(col < largeur - 1 && carte[lig][col +1].estTraversable()){
            v.add((CaseTraversable) carte[lig][col + 1]);
        }
        return v;
    }

    public void propagerFeu() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                Case caseCourante = carte[i][j];
                if (caseCourante instanceof CaseTraversable) {
                    CaseTraversable caseTraversable = (CaseTraversable) caseCourante;
                    if (caseTraversable.isEnFeu()) {
                        propagerFeuAutour(i, j);
                    }
                }
            }
        }
    }

    private void propagerFeuAutour(int lig, int col) {
        int chTotale = calculerChaleurTotale(lig, col);
        Random random = new Random();
        int r = random.nextInt(200); // Nombre aléatoire entre 0 et 199
        if (r < chTotale) {
            int incr = random.nextInt(10) + 1;
            carte[lig][col].ajouterRes(Math.min(incr, 10));
        } else if (r > 190) {
            int dcr = random.nextInt(2);
            carte[lig][col].ajouterChaleur(-dcr);
        }
    }
    private boolean limite(int lig, int col) {
        return lig >= 0 && lig < hauteur && col >= 0 && col < largeur;
    }
    private int calculerChaleurTotale(int lig, int col) {
        int chTotale = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int vLig = lig + i;
                int vCol = col + j;
                if (limite(vLig, vCol)) {
                    chTotale += carte[vLig][vCol].getChaleur();
                }
            }
        }
        return chTotale;
    }
}
