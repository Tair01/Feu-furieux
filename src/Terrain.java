import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Terrain représente l'environnement de jeu composé d'une carte avec des cases,
 * d'une hauteur et d'une largeur définies, ainsi que d'un joueur.
 */
public class Terrain {
    private int hauteur,largeur;           // La hauteur et la largeur de la carte.
    private Case[][] carte;        // La matrice de cases composant la carte.
    private Joueur joueur;         // Le joueur présent sur la carte.

    /**
     * Obtient la hauteur de la carte.
     * @return La hauteur de la carte.
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Obtient la largeur de la carte.
     * @return La largeur de la carte.
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Obtient la matrice de cases représentant la carte.
     * @return La matrice de cases.
     */
    public Case[][] getCarte() {
        return carte;
    }

    /**
     * Obtient le joueur présent sur la carte.
     * @return Le joueur.
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Obtient la case située aux coordonnées spécifiées.
     * @param l La ligne de la case.
     * @param c La colonne de la case.
     * @return La case aux coordonnées spécifiées.
     */
    public Case getCase(int l, int c) {
        return carte[l][c];
    }

    /**
     * Retourne la case voisine dans la direction spécifiée à partir de la case courante.
     * @param courante La case courante.
     * @param dir      La direction dans laquelle rechercher la case voisine.
     * @return La case voisine dans la direction spécifiée.
     */
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
    /**
     * Retourne une liste des cases traversables voisines à la position spécifiée.
     * @param lig La ligne de la position.
     * @param col La colonne de la position.
     * @return Une liste des cases traversables voisines.
     */
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

    /**
     * Propage le feu sur la carte en fonction des règles spécifiées.
     */
    public void propagerFeu() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                Case caseCourante = carte[i][j];
                if (caseCourante instanceof CaseTraversable caseTraversable) {
                    if (caseTraversable.getEnFeu() > 0) {
                        propagerFeuAutour(i, j);
                    }
                }
            }
        }
    }

    /**
     * Propage le feu autour de la case spécifiée.
     * @param lig La ligne de la case.
     * @param col La colonne de la case.
     */
    private void propagerFeuAutour(int lig, int col) {
        ArrayList<CaseTraversable> voisinesTraversables = getVoisinesTraversables(lig, col);
        int chTotale = calculerChTotale(lig, col);
        Random random = new Random();
        for (CaseTraversable voisine : voisinesTraversables) {
            int r = random.nextInt(200);
            if (r < chTotale) {
                int incr = random.nextInt(10) + 1;
                voisine.ajouterRes(Math.min(incr, 10));
                voisine.mettreEnFeu();
            } else if (r > 190) {
                int dcr = random.nextInt(2);
                voisine.ajouterChaleur(-dcr);
                voisine.eteindreEnFeu();
            }
        }
    }
    /**
     * Vérifie si les coordonnées spécifiées sont dans les limites de la carte.
     * @param lig La ligne.
     * @param col La colonne.
     * @return true si les coordonnées sont dans les limites, false sinon.
     */
    private boolean limite(int lig, int col) {
        return lig >= 0 && lig < hauteur && col >= 0 && col < largeur;
    }
    /**
     * Calcule la chaleur totale des cases environnantes.
     * @param lig La ligne de la case.
     * @param col La colonne de la case.
     * @return La chaleur totale des cases environnantes.
     */
    private int calculerChTotale(int lig, int col) {
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
