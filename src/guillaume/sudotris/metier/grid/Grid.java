package guillaume.sudotris.metier.grid;

import guillaume.sudotris.fileio.GridParser;
import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.resources.SudokuSolver;

import java.nio.file.Path;

/**
 * Classe Grid qui d?finit une grille de jeu.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see GridFilled
 * @see GridParsed
 * @since 1.8
 */
public abstract class Grid {
    /**
     * Matrice <code>Sudotris.LINES x Sudotris.COLUMNS</code> contenant la grille d'?l?ments de la grille de jeu.
     */
    protected Element[][] matrix;

    /**
     * <code>vrai</code> si la grille a ?t? r?solue; <br>
     * <code>faux</code> sinon.
     */
    private boolean solved;

    /**
     * Constructeur par d?faut de la classe Grid. <br>
     * Initialise la matrix repr?sentatrice de la grille d'?l?ments du jeu.
     */
    protected Grid() {
        this.matrix = new Element[Sudotris.LINES][Sudotris.COLUMNS];
        this.solved = false;
    }

    /**
     * Indique si grille de jeu est compl?tement remplie.
     *
     * @return <code>vrai</code> si la grille est remplie; <br>
     * <code>faux</code> sinon.
     */
    public abstract boolean isFilled();

    /**
     * Indique si la grille a ?t? r?solue.
     *
     * @return <vrai>si la grille a ?t? r?solue</vrai>; <br>
     * <code>faux</code> sinon
     */
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * Remplit la grille de jeu depuis le fichier de chemin donn?.
     *
     * @param path le chemin (relatif ou absolu) du fichier ? parser
     */
    public void initFromFile(Path path) {
        matrix = GridParser.parseFileToMatrix(path);
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
     * R?soud cette grille en faisant appel ? la ressource SudokuSolver. <br>
     * <b>Attention :</b> ne r?soud la grille que si elle n'a ?t? ni r?solue, ni enti?rement compl?t?e.
     *
     * @see SudokuSolver
     */
    public void solveGrid() {
        if (isSolved() || isFilled()) {
            return;
        }
        new SudokuSolver(this).solveGrid();
        solved = true;
    }

    /**
     * Renvoie l'?l?ment de la grille aux coordonn?es donn?es.
     *
     * @param line   ligne de l'?l?ment recherch?
     * @param column colonne de l'?l?ment recherch?
     * @return l'?l?ment contenu aux coordonn?es donn?es
     */
    public Element getElement(byte line, byte column) {
        return this.matrix[line][column];
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