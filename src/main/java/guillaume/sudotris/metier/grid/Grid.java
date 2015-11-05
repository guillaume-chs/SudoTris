package guillaume.sudotris.metier.grid;

import guillaume.sudotris.fileio.GridParser;
import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.metier.element.NotEmptyElement;
import guillaume.sudotris.resources.SudokuSolver;

import java.nio.file.Path;

/**
 * Classe Grid qui définit une grille de jeu.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see GridFilled
 * @see GridParsed
 * @since 1.8
 */
public abstract class Grid {
    /**
     * Matrice <code>Sudotris.LINES x Sudotris.COLUMNS</code> contenant la grille d'éléments de la grille de jeu.
     */
    protected Element[][] matrix;

    /**
     * <code>vrai</code> si la grille a été résolue; <br>
     * <code>faux</code> sinon.
     */
    protected boolean solved;

    /**
     * Constructeur par défaut de la classe Grid. <br>
     * Initialise la matrix représentatrice de la grille d'éléments du jeu.
     */
    protected Grid() {
        matrix = new Element[Sudotris.LINES][Sudotris.COLUMNS];
        solved = false;
        empty();
    }

    /**
     * Indique si grille de jeu est complètement remplie.
     *
     * @return <code>vrai</code> si la grille est remplie; <br>
     * <code>faux</code> sinon.
     */
    public abstract boolean isFilled();

    /**
     * Indique si la grille a été résolue.
     *
     * @return <vrai>si la grille a été résolue</vrai>; <br>
     * <code>faux</code> sinon
     */
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * Remplit la grille de jeu depuis le fichier de chemin donné.
     *
     * @param path le chemin (relatif ou absolu) du fichier  parser
     */
    public void initFromFile(Path path) {
        solved = false;
        matrix = GridParser.parseFileToMatrix(path);
    }

    /**
     * Remplit la grille de jeu depuis la grille donnée.
     *
     * @param grid la grille de jeu à copier
     */
    public void initFromGrid(Grid grid) {
        solved = false;

        for (byte line = 0; line < 9; line++) {
            for (byte col = 0; col < 9; col++) {
                final Element el = grid.getElement(line, col);
                if (el.isEmpty()) {
                    matrix[line][col] = new EmptyElement(line, col);
                } else {
                    matrix[line][col] = new NotEmptyElement(line, col, el.getNumber());
                }
            }
        }
    }

    /**
     * Vide la grille de jeu.
     */
    public void empty() {
        for (byte line = 0; line < 9; line++) {
            for (byte col = 0; col < 9; col++) {
                matrix[line][col] = new EmptyElement(line, col);
            }
        }
    }

    /**
     * Résoud cette grille en faisant appel à la ressource SudokuSolver. <br>
     * <b>Attention :</b> ne résoud la grille que si elle n'a été ni résolue, ni entièrement complétée.
     *
     * @see SudokuSolver
     */
    public void solveGrid() {
        if (isSolved() || isFilled()) {
            return;
        }
        (new SudokuSolver(this)).solveGrid();
        solved = true;
    }

    /**
     * Renvoie l'élément de la grille aux coordonnées données.
     *
     * @param line   ligne de l'élément recherché
     * @param column colonne de l'élément recherché
     * @return l'élément contenu aux coordonnées données
     */
    public Element getElement(byte line, byte column) {
        return matrix[line][column];
    }

    /**
     * Renvoie la matrice d'éléments représentant la grille de jeu.
     *
     * @return une matrice d'éléments
     */
    public Element[][] getMatrix() {
        return matrix;
    }
}