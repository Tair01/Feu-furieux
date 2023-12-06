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

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int debutX = joueur.getC().col - 4;
        int debutY = joueur.getC().lig - 4;

        for (int i = 0; i< hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                int x = (j - debutX) * tailleCase;
                int y = (i - debutY) * tailleCase;

                int x1 = i - joueur.getC().lig;
                int y1 = j - joueur.getC().col;
                int distance = x1 * x1 + y1 * y1;

                Case caseCourant = terrain.getCarte()[i][j];
                if (distance <= 10) {
                    if (caseCourant instanceof Hall) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if(caseCourant instanceof CaseTraversable c){
                        this.dessinerElem(g,x1 + 4 ,y1 + 4, (CaseTraversable) caseCourant);
                    }if (caseCourant instanceof Mur) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if (caseCourant instanceof Sortie) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if (caseCourant instanceof Porte) {
                        g.setColor(((Porte) caseCourant).isOuverte() ?  Color.WHITE : Color.GREEN);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }
                }
            }
        }
        repaint();
    }

    private void dessinerElem(Graphics g, int x, int y, CaseTraversable caseCourant) {
        int a = tailleCase / 3;
        if (caseCourant.haveCles()) {
            g.setColor(Color.RED);
            //System.out.println("Marche pas");
            g.fillRect(a + y * tailleCase  , a + x * tailleCase  , a  , a );
        }
        if (caseCourant.haveJ()) {
            g.setColor(Color.GRAY);
            //System.out.println("Marche");
            g.fillOval(x * tailleCase, y * tailleCase , tailleCase , tailleCase );
        }
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
