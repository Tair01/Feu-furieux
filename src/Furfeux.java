import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 * La classe Furfeux représente le jeu Furfeux, qui gère les tours de jeu, l'état du joueur,
 * et l'interaction avec le terrain.
 */
public class Furfeux {

    Terrain terrain; // Le terrain de jeu
    Joueur joueur;  // Le joueur du jeu

    /**
     * Constructeur de la classe Furfeux.
     * @param f Le chemin du fichier de configuration pour initialiser le terrain.
     */
    public Furfeux(String f) {
        this.terrain = new Terrain(f);
        this.joueur = terrain.getJoueur();
    }

    /**
     * Méthode pour effectuer un tour de jeu.
     * Elle propage le feu sur le terrain, puis ajuste la résistance du joueur en fonction de la chaleur de la case actuelle.
     */
    public void tour() {
        terrain.propagerFeu();
        CaseTraversable caseCourante = joueur.getC();
        int chaleur = 0;
        if(caseCourante instanceof Hall){
            chaleur = caseCourante.getChaleur();
        }else if(caseCourante instanceof Sortie){
            chaleur = caseCourante.getChaleur();
        }else if(caseCourante instanceof Porte && ((Porte) caseCourante).isOuverte()){
            chaleur = 0;
        }
        joueur.attackDuFeu(chaleur);
        /*for (int i = 1; i < terrain.getHauteur() - 1; i++) {
            for (int j = 1; j < terrain.getLargeur() - 1; j++) {
                CaseTraversable caseCourante = joueur.getC();
                int chaleur = 0;
                if(caseCourante instanceof Hall){
                    chaleur = caseCourante.getChaleur();
                }else if(caseCourante instanceof Sortie){
                    chaleur = caseCourante.getChaleur();
                }else if(caseCourante instanceof Porte && ((Porte) caseCourante).isOuverte()){
                    chaleur = 0;
                }
                joueur.attackDuFeu(chaleur);
            }
        }*/
    }

    /**
     * Méthode pour vérifier si la partie est terminée.
     * La partie se termine si la résistance du joueur est inférieure à zéro ou si le joueur a atteint la sortie.
     * @return true si la partie est terminée, sinon false.
     */
    public boolean partieFinie() {
        return joueur.getResistance() < 0 || this.joueur.isGagner();
    }
    /**
     * Méthode principale pour démarrer le jeu.
     * Elle crée une instance de jeu, une fenêtre graphique, et un timer pour gérer les tours.
     * Le jeu est joué successivement sur trois niveaux de difficulté.
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        int tempo = 100;
        Furfeux jeu1 = new Furfeux("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir.txt");
        FenetreJeu graphic = new FenetreJeu(jeu1.terrain);

        Timer timer = new Timer(tempo, e -> {
            jeu1.tour();
            graphic.repaint();
            if (jeu1.partieFinie()) {
                graphic.ecranFinal(Math.max(0, jeu1.joueur.getResistance()));
                ((Timer)e.getSource()).stop();

                Furfeux jeu2 = new Furfeux("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir2.txt");
                FenetreJeu graphic2 = new FenetreJeu(jeu2.terrain);
                Timer timer2 = new Timer(tempo, e2 -> {
                    jeu2.tour();
                    graphic2.repaint();
                    if (jeu2.partieFinie()) {
                        graphic2.ecranFinal(Math.max(0, jeu2.joueur.getResistance()));
                        ((Timer) e2.getSource()).stop();

                        Furfeux jeu3 = new Furfeux("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir3.txt");
                        FenetreJeu graphic3 = new FenetreJeu(jeu3.terrain);
                        Timer timer3 = new Timer(tempo, e3 -> {
                            jeu3.tour();
                            graphic3.repaint();
                            if(jeu3.partieFinie()){
                                graphic3.ecranFinal(Math.max(0,jeu3.joueur.getResistance()));
                                ((Timer) e2.getSource()).stop();
                            }
                        });
                        timer3.start();
                    }
                });
                timer2.start();
            }
        });
        timer.start();
    }
}
