package guillaume.sudotris.resources;

import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.metier.element.NotEmptyElement;
import guillaume.sudotris.metier.grid.Grid;

/**
 * Résolveur d'une grille partielle donnée. <br>
 * La méthode d'implémentation repose sur de la programmation par contrainte : l'algorithme se base sur du backtracking. <br>
 * De l'optimisation est encore largement possible, bien que l'algorithme ne soit pas chronophage.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class SudokuSolver {
    /**
     * La matrice à résoudre.
     */
    private final Element[][] matrix;

    /**
     * Instancie le résolveur à partir d'une instance de la classe Grid. <br>
     *
     * @param grid la grille comportant la matrice à résoudre
     * @see SudokuSolver#SudokuSolver(Element[][] matrix)
     * @see Grid
     * @see Grid#getMatrix()
     */
    public SudokuSolver(Grid grid) {
        this(grid.getMatrix());
    }

    /**
     * Instancie le résolveur à partir de la matrice à résoudre. <br>
     *
     * @param matrix la matrice d'éléments à résoudre
     */
    public SudokuSolver(Element[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * La méthode publique à appeler pour lancer la résolution du sudoku.
     *
     * @return <code>vrai</code> si la grille a été résolue (toujours le cas sur 9x9); <br>
     * <code>faux</code> sinon
     * @see SudokuSolver#solveGrid(byte)
     */
    public boolean solveGrid() {
        return this.solveGrid((byte) 0);
    }

    /**
     * Méthode privée appelée en interne. C'est l'algorithme de résolution. <br>
     * Itère récursivement en cherchant à chaque fois une solution à la position donnée (0 -> 80). <br>
     * Applique le principe de backtracking.
     *
     * @param position la position sur la grille de l'élément à trouver
     * @return <code>vrai</code> lorsqu'une solution est trouvée à la position donnée; <br>
     * <code>faux</code> sinon
     */
    private boolean solveGrid(byte position) {
        if (position == 81) {
            return true;
        }

        final byte line = (byte) (Math.floorDiv(position, 9));
        final byte column = (byte) (Math.floorMod(position, 9));

        if (!matrix[line][column].isEmpty()) {
            return solveGrid((byte) (position + 1));
        }

        // Itérons jusqu'à trouver le(s) chiffre(s) prétendants à la case
        for (byte number = 1; number <= 9; number++) {
            if (checkLine(line, number) && checkColumn(column, number) && checkSquare(line, column, number)) {
                matrix[line][column] = new NotEmptyElement(line, column, number);

                // Si on a trouvé pour [line][column]
                if (solveGrid((byte) (position + 1))) {
                    return true;
                }
            }
        }

        // Si on n'a rien trouvé
        matrix[line][column] = new EmptyElement(line, column);
        return false;
    }

    /**
     * Vérifie que le nombre donné n'existe pas déjà sur la ligne donnée.
     *
     * @param line   la ligne à vérifier
     * @param number le nombre à insérer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'insérer); <br>
     * <code>faux</code> sinon (mauvais nombre)
     */
    private boolean checkLine(byte line, byte number) {
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            if (matrix[line][columnIndex].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie que le nombre donné n'existe pas déjà sur la colonne donnée.
     *
     * @param column la colonne à vérifier
     * @param number le nombre à insérer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'insérer); <br>
     * <code>faux</code> sinon (mauvais nombre)
     */
    private boolean checkColumn(byte column, byte number) {
        for (int lineIndex = 0; lineIndex < 9; lineIndex++) {
            if (matrix[lineIndex][column].getNumber() == number) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie que le nombre donné n'existe pas déjà sur le bloc 3x3. <br>
     * Ce dernier est indentifié par la ligne et la colonne (quelqueconques du bloc) données.
     *
     * @param line   une ligne du bloc
     * @param column une colonne du bloc
     * @param number le nombre à insérer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'insérer); <br>
     * <code>faux</code> sinon (mauvais nombre)
     */
    private boolean checkSquare(byte line, byte column, byte number) {
        final byte _ligne = (byte) (line - line % 3);
        final byte _column = (byte) (column - column % 3);

        for (int lineIndex = _ligne; lineIndex < _ligne + 3; lineIndex++) {
            for (int colIndex = _column; colIndex < _column + 3; colIndex++) {
                if (matrix[lineIndex][colIndex].getNumber() == number) {
                    return false;
                }
            }
        }

        return true;
    }
}