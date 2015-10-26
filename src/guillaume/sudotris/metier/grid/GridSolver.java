package guillaume.sudotris.metier.grid;

import java.nio.file.Path;

/**
 * R?solveur d'une grille partielle donn?e. Cette classe se comporte comme GridFilled une fois la grille r?solue. <br>
 * La m?thode d'impl?mentation repose sur de la programmation par contrainte : l'algorithme se base sur du backtracking.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Grid
 * @see GridFilled
 * @since 1.8
 */
public class GridSolver extends Grid {
    /**
     * Constructeur par d?faut du r?solveur de grille.
     *
     * @see Grid#Grid()
     */
    public GridSolver() {
        super();
    }

    /**
     * Indique si grille de jeu est compl?tement remplie, en l'occurence d?s que la r?solution a ?t? effectu?e.
     *
     * @return <code>vrai</code>, lorsque la grille a ?t? r?solue; <br>
     * <code>faux</code> sinon
     * @see Grid#isFilled()
     */
    @Override
    public boolean isFilled() {
        return this.isSolved();
    }

    /**
     * Remplit la grille de jeu depuis le fichier de chemin donn?. <br>
     * Cette red?finition est ? utiliser pour la classe GridParsed.
     *
     * @param path le chemin (relatif ou absolu) du fichier ? parser
     */
    @Override
    public void initFromFile(Path path) {
        super.initFromFile(path);
        this.solveGrid();
    }
}