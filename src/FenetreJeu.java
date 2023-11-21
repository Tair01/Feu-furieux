import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 36;
    private int hauteur, largeur;
    private JFrame frame;

    private int centreX, centreY;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;

        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));

        JFrame frame = new JFrame("Furfeux");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        centreX = 0;
        centreY = 0;

        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < Math.min(hauteur, 9); i++) {
            for (int j = 0; j < Math.min(largeur, 9); j++) {
                int x = j * tailleCase;
                int y = i * tailleCase;
                Case caseCourant = terrain.getCarte()[i + centreY][j + centreX];
                if (caseCourant instanceof Hall) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, tailleCase, tailleCase);
                } else if (caseCourant instanceof Mur) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, tailleCase, tailleCase);
                } else if (caseCourant instanceof Sortie) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, tailleCase, tailleCase);
                }else if(caseCourant instanceof Porte){
                    Porte porteCourante = (Porte) caseCourant;
                    if(porteCourante.isOuverte()){
                        g.setColor(Color.WHITE);
                    }else{
                        g.setColor(Color.GREEN);
                    }
                    g.fillRect(x,y,tailleCase,tailleCase);
                }
            }
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
            case KeyEvent.VK_UP:
                if (centreY > 0 && terrain.getCarte()[centreY - 1][centreX].estTraversable()) {
                    terrain.getJoueur().bouge(terrain.getCarte()[centreY - 1][centreX]);
                    centreY--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (centreY < terrain.getHauteur() - 1 && terrain.getCarte()[centreY + 1][centreX].estTraversable()) {
                    terrain.getJoueur().bouge(terrain.getCarte()[centreY + 1][centreX]);
                    centreY++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (centreX > 0 && terrain.getCarte()[centreY][centreX - 1].estTraversable()) {
                    terrain.getJoueur().bouge(terrain.getCarte()[centreY][centreX - 1]);
                    centreX--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (centreX < terrain.getLargeur() - 1 && terrain.getCarte()[centreY][centreX + 1].estTraversable()) {
                    terrain.getJoueur().bouge(terrain.getCarte()[centreY][centreX + 1]);
                    centreX++;
                }
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
