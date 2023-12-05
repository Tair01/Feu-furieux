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
    private int centreX, centreY;

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

        centreX = Math.max(0, joueur.getC().col - 4);
        centreY = Math.max(0, joueur.getC().lig - 4);

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int visibleH = Math.min(hauteur, 9);
        int visibleW = Math.min(largeur, 9);
        int debutX = Math.max(0, joueur.getC().col - 4);
        int debutY = Math.max(0, joueur.getC().lig - 4);
        int finX = Math.min(largeur, centreX + visibleW);
        int finY = Math.min(hauteur, centreY + visibleH);

        for (int i = debutY; i < finY; i++) {
            for (int j = debutX; j < finX; j++) {
                int x = (j - debutX) * tailleCase;
                int y = (i - debutY) * tailleCase;
                int a = (int) Math.pow(joueur.getC().lig - i, 2);
                int b = (int) Math.pow(joueur.getC().col - j, 2);
                Case caseCourant = terrain.getCarte()[i][j];
                int distance = a * b;
                if (distance <= 10) {
                    if (caseCourant instanceof Hall) {
                        g.setColor(Color.WHITE);
                        if (((Hall) caseCourant).hCles() || ((Hall) caseCourant).haveJ() || ((Hall) caseCourant).haveCles()) {
                            g.setColor(Color.GRAY);
                        }
                    } else if (caseCourant instanceof Mur) {
                        g.setColor(Color.BLACK);
                    } else if (caseCourant instanceof Sortie) {
                        g.setColor(Color.BLUE);
                    } else if (caseCourant instanceof Porte porteCourante) {
                        g.setColor(porteCourante.isOuverte() ? Color.WHITE : Color.GREEN);
                    }
                    g.fillRect(x, y, tailleCase, tailleCase);
                    if (caseCourant instanceof CaseTraversable) {
                        this.dessinerElements(g, y + (tailleCase / 4), x + (tailleCase / 4), (CaseTraversable) caseCourant);
                    }
                }
            }
        }
        repaint();
    }

    private void dessinerElements(Graphics g, int x, int y, CaseTraversable caseCourant) {
        if (caseCourant.haveJ()) {
            g.setColor(Color.GRAY);
            g.fillOval(y * tailleCase, x * tailleCase, tailleCase / 2, tailleCase / 2);
        }
        if (caseCourant.haveCles()) {
            g.setColor(Color.GRAY);
            g.fillRect(y, x, tailleCase / 2, tailleCase / 2);
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
