import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class Furfeux {

    Terrain terrain;
    Joueur joueur;

    public Furfeux(String f) {
        this.terrain = new Terrain(f);
        this.joueur = terrain.getJoueur();
    }

    public void tour() {
        CaseTraversable caseCourante = joueur.getC();
        int chaleur = 0;
        /*if(caseCourante != null){
            terrain.propagerFeu();
        }*/
        if(caseCourante instanceof Hall){
            chaleur = caseCourante.getChaleur();
        }else if(caseCourante instanceof Sortie){
            chaleur = caseCourante.getChaleur();
        }else if(caseCourante instanceof Porte && ((Porte) caseCourante).isOuverte()){
            chaleur = 0;
        }

        joueur.ajouterRes(chaleur);
    }

    public boolean partieFinie() {
        return joueur.getResistance() <= 0 || this.joueur.isGagner();
    }

    public static void main(String[] args) {
        int tempo = 100;
        Furfeux jeu = new Furfeux("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);

        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.repaint();
            if (jeu.partieFinie()) {
                graphic.ecranFinal(Math.max(0, jeu.joueur.getResistance()));
                ((Timer)e.getSource()).stop();
            }
        });
        timer.start();
    }
}
