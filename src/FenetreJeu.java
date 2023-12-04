import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 36;
    private int hauteur, largeur;
    private JFrame frame;

    private Joueur joueur;
    private int debutX, debutY, finY, finX;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        this.joueur = t.getJoueur();
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));

        JFrame frame = new JFrame("Furfeux");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        debutX = this.joueur.getC().lig;
        debutY = this.joueur.getC().col;

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < hauteur; i++){
            for(int j = 0; j < largeur; j++){
                dessinerCase(g,i,j);
            }
        }
    }
    private void dessinerCase(Graphics g, int i, int j) {
        Case caseCourant = terrain.getCase(i,j); // Case courante
        int x = i - debutX;
        int y = j - debutY;
        int x1 = (int) Math.pow(x,2);
        int y1 = (int) Math.pow(y,2);
        int d = x1 * y1; //La distance
        if (d <= 10) {
            int a = 4 + y; // La visibilite 4 case
            int b = 4 + x;
            g.setColor(Color.WHITE);
            g.fillRect(a * tailleCase, b * tailleCase, tailleCase, tailleCase);
            g.setColor(getCouleurCase(caseCourant));
            g.fillRect(a*tailleCase, b * tailleCase, tailleCase, tailleCase);
            dessinerElementsSupplementaires(g, x, y, caseCourant);
        }
    }

    private void dessinerElementsSupplementaires(Graphics g, int x, int y, Case caseCourant) {
        CaseTraversable c = (CaseTraversable) caseCourant;
        if (c.haveJ()) {
            g.setColor(Color.GRAY);
            g.fillOval(x * tailleCase, y * tailleCase, tailleCase, tailleCase);
        }
        if (c.haveCles())  {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, tailleCase, tailleCase);
        }
    }


    private Color getCouleurCase(Case caseCourant) {
        Color c = Color.WHITE;
        if (caseCourant instanceof Hall) {
            c = Color.WHITE;
            if (((Hall) caseCourant).hCles() || ((Hall) caseCourant).haveJ() || ((Hall) caseCourant).haveCles()) {
                c = Color.GRAY;
            }
        } else if (caseCourant instanceof Mur) {
            c = Color.BLACK;
        } else if (caseCourant instanceof Sortie) {
            c = Color.BLUE;
        } else if (caseCourant instanceof Porte) {
            Porte porteCourante = (Porte) caseCourant;
            c = porteCourante.isOuverte() ? Color.WHITE : Color.GREEN;
        }
        return c;
    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP: joueur.bouge(terrain.chemin(joueur.getC(),Direction.nord)); break;
            case KeyEvent.VK_DOWN:joueur.bouge(terrain.chemin(joueur.getC(), Direction.sud)); break;
            case KeyEvent.VK_LEFT:joueur.bouge(terrain.chemin(joueur.getC(), Direction.ouest)); break;
            case KeyEvent.VK_RIGHT: joueur.bouge(terrain.chemin(joueur.getC(), Direction.est)); break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
