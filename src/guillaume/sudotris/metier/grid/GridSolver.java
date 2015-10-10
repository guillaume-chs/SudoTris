package guillaume.sudotris.metier.grid;

import java.nio.file.Path;

/**
 * Résolveur d'une grille partielle donnée. Cette classe se comporte comme GridFilled une fois la grille résolue. <br>
 * La méthode d'implémentation repose sur de la programmation par contrainte : l'algorithme se base sur du backtracking.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Grid
 * @see GridFilled
 * @since 1.8
 */
public class GridSolver extends Grid {
    /**
     * Constructeur par défaut du résolveur de grille.
     *
     * @see Grid#Grid()
     */
    public GridSolver() {
        super();
    }

    /**
     * Indique si grille de jeu est complètement remplie, en l'occurence dès que la résolution a été effectuée.
     *
     * @return <code>vrai</code>, lorsque la grille a été résolue; <br>
     * <code>faux</code> sinon
     * @see Grid#isFilled()
     */
    @Override
    public boolean isFilled() {
        return this.isSolved();
    }

    /**
     * Remplit la grille de jeu depuis le fichier de chemin donné. <br>
     * Cette redéfinition est à utiliser pour la classe GridParsed.
     *
     * @param path le chemin (relatif ou absolu) du fichier à parser
     */
    @Override
    public void initFromFile(Path path) {
        super.initFromFile(path);
        this.solveGrid();
    }
}