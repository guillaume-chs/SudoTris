package guillaume.sudotris.metier.grid;

/**
 * G?re la grille finale (compl?tement et d?ment remplie) de la partie.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Grid
 * @since 1.8
 */
public class GridFilled extends Grid {
    /**
     * Constructeur par d?faut d'une grille remplie.
     *
     * @see Grid#Grid()
     */
    public GridFilled() {
        super();
    }

    /**
     * Indique si grille de jeu est compl?tement remplie, en l'occurence tout le temps.
     *
     * @return <code>vrai</code>, puisque la grille est remplie
     * @see Grid#isFilled()
     */
    @Override
    public boolean isFilled() {
        return true;
    }
}