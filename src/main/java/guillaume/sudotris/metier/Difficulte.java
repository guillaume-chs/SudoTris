package guillaume.sudotris.metier;

/**
 * Énumération des difficultés de jeu possibles. Se base sur le nommage des fichiers des grilles de jeu.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public enum Difficulte {
    /**
     * Très facile.
     */
    EASY(1),
    /**
     * Très dur.
     */
    HARD(6);

    private final int level;

    /**
     * Construit un niveau de difficulté depuis son équivalent en chiffre.
     *
     * @param level l'équivalent chiffré du niveau de difficulté.
     */
    Difficulte(int level) {
        this.level = level;
    }

    /**
     * Renvoi la représentation numérique du niveau de difficulté.
     *
     * @return la représentation numérique du niveau de difficulté
     */
    public int getLevel() {
        return level;
    }
}