import java.util.Random;

/**
 * L'énumération Direction représente les différentes directions possibles,
 * notamment nord, sud, est et ouest. Elle inclut une méthode pour obtenir une
 * direction aléatoire.
 */
public enum Direction {
    nord,
    sud,
    est,
    ouest;

    /**
     * Retourne une direction aléatoire parmi les directions possibles.
     * @return Une direction aléatoire.
     */
    public static Direction random() {
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        switch (r) {
            case 0: return Direction.nord;
            case 1: return Direction.sud;
            case 2: return Direction.est;
            default: return Direction.ouest;
        }
    }
}
