package guillaume.sudotris.resources;

import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.metier.element.NotEmptyElement;
import guillaume.sudotris.metier.grid.Grid;

/**
 * R?solveur d'une grille partielle donn?e. <br>
 * La m?thode d'impl?mentation repose sur de la programmation par contrainte : l'algorithme se base sur du backtracking. <br>
 * De l'optimisation est encore largement possible, bien que l'algorithme ne soit pas chronophage.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class SudokuSolver {
    /**
     * La matrice ? r?soudre.
     */
    private final Element[][] matrix;

    /**
     * Instancie le r?solveur ? partir d'une instance de la classe Grid. <br>
     *
     * @param grid la grille comportant la matrice ? r?soudre
     * @see SudokuSolver#SudokuSolver(Element[][] matrix)
     * @see Grid
     * @see Grid#getMatrix()
     */
    public SudokuSolver(Grid grid) {
        this(grid.getMatrix());
    }

    /**
     * Instancie le r?solveur ? partir de la matrice ? r?soudre. <br>
     *
     * @param matrix la matrice d'?l?ments ? r?soudre
     */
    public SudokuSolver(Element[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * La m?thode publique ? appeler pour lancer la r?solution du sudoku.
     *
     * @return <code>vrai</code> si la grille a ?t? r?solue (toujours le cas sur 9x9); <br>
     * <code>faux</code> sinon
     * @see SudokuSolver#solveGrid(byte)
     */
    public boolean solveGrid() {
        return this.solveGrid((byte) 0);
    }

    /**
     * M?thode priv?e appel?e en interne. C'est l'algorithme de r?solution. <br>
     * It?re r?cursivement en cherchant ? chaque fois une solution ? la position donn?e (0 -> 80). <br>
     * Applique le principe de backtracking.
     *
     * @param position la position sur la grille de l'?l?ment ? trouver
     * @return <code>vrai</code> lorsqu'une solution est trouv?e ? la position donn?e; <br>
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

        // It?rons jusqu'? trouver le(s) chiffre(s) pr?tendants ? la case
        for (byte number = 1; number <= 9; number++) {
            if (checkLine(line, number) && checkColumn(column, number) && checkSquare(line, column, number)) {
                matrix[line][column] = new NotEmptyElement(line, column, number);

                // Si on a trouv? pour [line][column]
                if (solveGrid((byte) (position + 1))) {
                    return true;
                }
            }
        }

        // Si on n'a rien trouv?
        matrix[line][column] = new EmptyElement(line, column);
        return false;
    }

    /**
     * V?rifie que le nombre donn? n'existe pas d?j? sur la ligne donn?e.
     *
     * @param line   la ligne ? v?rifier
     * @param number le nombre ? ins?rer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'ins?rer); <br>
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
     * V?rifie que le nombre donn? n'existe pas d?j? sur la colonne donn?e.
     *
     * @param column la colonne ? v?rifier
     * @param number le nombre ? ins?rer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'ins?rer); <br>
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
     * V?rifie que le nombre donn? n'existe pas d?j? sur le bloc 3x3. <br>
     * Ce dernier est indentifi? par la ligne et la colonne (quelqueconques du bloc) donn?es.
     *
     * @param line   une ligne du bloc
     * @param column une colonne du bloc
     * @param number le nombre ? ins?rer
     * @return <code>vrai</code> si le nombre n'entre pas en conflit (peut s'ins?rer); <br>
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