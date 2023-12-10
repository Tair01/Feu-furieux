import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * FenetreJeu représente la fenêtre de jeu pour le jeu Furfeux.
 * Cette fenêtre inclut trois boutons permettant de démarrer le jeu avec
 * l'une des quatre cartes disponibles.
 * - Bouton "Jeu 1" : Lance le jeu avec la carte manoir.txt.
 * - Bouton "Jeu 2" : Lance le jeu avec la carte manoir2.txt.
 * - Bouton "Jeu 3" : Lance le jeu avec la carte manoir3.txt.
 * - ...
 * Il y a un total de quatre cartes disponibles pour jouer.
 */
public class FenetreJeu extends JPanel implements KeyListener, MouseListener {
    private Terrain terrain;          // Le terrain de jeu.
    private int tailleCase = 36;       // La taille des cases dans la fenêtre.
    private int hauteur, largeur;      // La hauteur et la largeur du terrain.
    private JFrame frame;             // La fenêtre principale du jeu.
    private Joueur joueur;            // Le joueur du jeu.
    /**
     * Constructeur de la classe FenetreJeu.
     * @param t Le terrain de jeu.
     */
    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        this.joueur = t.getJoueur();

        // Configuration de l'aspect de la fenêtre.
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(9 * tailleCase, 9 * tailleCase));

        // Création des boutons pour choisir le jeu
        JButton boutonJeu1 = new JButton("Jeu 1");
        JButton boutonJeu2 = new JButton("Jeu 2");
        JButton boutonJeu3 = new JButton("Jeu 3");
        JButton boutonAdmin = new JButton("Admin");

        // Configuration du panneau de boutons.
        JPanel boutonPanel = new JPanel();
        boutonPanel.add(boutonJeu1);
        boutonPanel.add(boutonJeu2);
        boutonPanel.add(boutonJeu3);
        boutonPanel.add(boutonAdmin);
        // Ajout des listeners aux boutons.
        boutonJeu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir.txt");
            }
        });
        // Ajout des listeners aux boutons.
        boutonJeu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir2.txt");
            }
        });
        // Ajout des listeners aux boutons.
        boutonJeu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir3.txt");
            }
        });
        // Ajout des listeners aux boutons.
        boutonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\admin-test.txt");
            }
        });

        boutonJeu1.addMouseListener(this);
        boutonJeu2.addMouseListener(this);
        boutonJeu3.addMouseListener(this);
        boutonAdmin.addMouseListener(this);

        // Configuration de la fenêtre principale
        JFrame frame = new JFrame("Furfeux");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        // Configuration de la disposition de la fenêtre
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(boutonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(this, BorderLayout.CENTER);

        // Ajout du KeyListener à la fenêtre pour gérer les événements clavier
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
    /**
     * Méthode appelée pour redessiner la fenêtre.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i< hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                int x = (j - (joueur.getC().col - 4)) * tailleCase;
                int y = (i - (joueur.getC().lig - 4)) * tailleCase;

                int x1 = i - joueur.getC().lig;
                int y1 = j - joueur.getC().col;
                int distance = x1 * x1 + y1 * y1;
                Case caseCourant = terrain.getCarte()[i][j];
                if (distance <= 10) {
                    int chaleur = caseCourant.getEnFeu();
                    //int r = Math.min(255, 21 * chaleur);
                    if (caseCourant instanceof Hall) {
                        g.setColor(chaleur > 0 ? Color.RED : Color.WHITE);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if (caseCourant instanceof Mur) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if (caseCourant instanceof Porte) {
                        g.setColor(((Porte) caseCourant).isOuverte() ?  Color.WHITE : Color.GREEN);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }if(caseCourant instanceof CaseTraversable c){
                        this.dessinerElem(g,x1 + 4 ,y1 + 4, (CaseTraversable) caseCourant);
                    }if (caseCourant instanceof Sortie) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, tailleCase, tailleCase);
                    }
                }
            }
        }
        repaint();
    }

    /**
     * Méthode pour dessiner les éléments spécifiques dans la fenêtre.
     * @param g   L'objet Graphics utilisé pour dessiner.
     * @param x   La coordonnée x de l'élément.
     * @param y   La coordonnée y de l'élément.
     * @param caseCourant La case sur laquelle l'élément doit être dessiné.
     */
    private void dessinerElem(Graphics g, int x, int y, CaseTraversable caseCourant) {
        int a = tailleCase / 3;
        if (caseCourant.haveCles()) {
            g.setColor(Color.GRAY);
            g.fillRect(a + y * tailleCase  , a + x * tailleCase  , a  , a );
        }if (caseCourant.haveJ()) {
            g.setColor(Color.GRAY);
            g.fillOval(x * tailleCase, y * tailleCase , tailleCase , tailleCase );
        }
    }
    /**
     * Méthode appelée pour afficher l'écran final du jeu.
     * @param n Le score final du joueur.
     */
    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }

    /**
     * Méthode pour commencer un nouveau jeu à partir d'un fichier spécifié.
     * @param cheminFichier Le chemin du fichier de configuration du jeu.
     */
    public void commencerJeu(String cheminFichier) {
        frame.getContentPane().removeAll();
        frame.repaint();
        Furfeux jeu = new Furfeux(cheminFichier);
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(100, e -> {
            jeu.tour();
            graphic.repaint();
            if (jeu.partieFinie()) {
                graphic.ecranFinal(Math.max(0, jeu.joueur.getResistance()));
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    // Implémentation des méthodes des interfaces KeyListener et MouseListener
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch(k){
            case KeyEvent.VK_UP: joueur.bouge(terrain.chemin(joueur.getC(),Direction.nord)); break;
            case KeyEvent.VK_DOWN:joueur.bouge(terrain.chemin(joueur.getC(), Direction.sud)); break;
            case KeyEvent.VK_LEFT:joueur.bouge(terrain.chemin(joueur.getC(), Direction.ouest)); break;
            case KeyEvent.VK_RIGHT: joueur.bouge(terrain.chemin(joueur.getC(), Direction.est)); break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bouton = (JButton) e.getSource();
            if (bouton.getText().equals("Jeu 1")) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir.txt");
            } else if (bouton.getText().equals("Jeu 2")) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir2.txt");
            } else if (bouton.getText().equals("Jeu 3")) {
                commencerJeu("C:\\Users\\Admin\\IdeaProjects\\feu-furieux\\src\\manoir3.txt");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Classe principale Main pour lancer le jeu.
     */
    public class Main {
        public static void main(String[] args) {
            String selectedFilePath = choisirFichierJeu();
            if (selectedFilePath != null) {
                Jeu(selectedFilePath);
            }
        }
        /**
         * Méthode pour choisir un fichier de jeu via une boîte de dialogue.
         * @return Le chemin du fichier choisi ou null si aucun fichier n'est choisi.
         */
        public static String choisirFichierJeu() {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile().getAbsolutePath();
            } else {
                return null;
            }
        }

        /**
         * Méthode pour lancer le jeu avec un fichier spécifié.
         * @param fichier Le chemin du fichier de configuration du jeu.
         */
        public static void Jeu(String fichier) {
            int tempo = 100;
            Furfeux jeu = new Furfeux(fichier);
            FenetreJeu graphic = new FenetreJeu(jeu.terrain);
            Timer timer = new Timer(tempo, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jeu.tour();
                    graphic.repaint();
                    if (jeu.partieFinie()) {
                        graphic.ecranFinal(Math.max(0, jeu.joueur.getResistance()));
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
    }
}
