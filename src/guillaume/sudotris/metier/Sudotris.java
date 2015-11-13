package guillaume.sudotris.metier;

import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.grid.GridParsed;
import guillaume.sudotris.metier.grid.GridSolved;
import guillaume.sudotris.resources.GrilleFileProvider;

import java.nio.file.Path;

/**
 * Classe métier-contrôlleur du Sudotris : <br>
 * fait office d'interface entre les vues et les couches plus métiers (Grid et Element).
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class Sudotris {
    /**
     * Nombre de lignes de la grille.
     */
    public static final byte LINES = 9;
    /**
     * Nombre de colonnes de la grille.
     */
    public static final byte COLUMNS = 9;

    private final GridParsed gridParsed;
    private final GridSolved gridSolved;
    private Difficulte difficulte;

    /**
     * Default constructor.
     */
    public Sudotris() {
        gridParsed = new GridParsed();
        gridSolved = new GridSolved();
    }

    /**
     * Initialise une partie de Sudotris pour une difficulté donnée.
     *
     * @param difficulte la difficulte de la partie à jouer
     */
    public void init(Difficulte difficulte) {
        this.difficulte = difficulte;

        final Path gridPath = GrilleFileProvider.getParsedGridPath(difficulte);

        // Méthode 1 : grille finie
        // gridParsed.initFromFile(gridPath);
        // gridSolved.initFromFile(GrilleFileProvider.getFilledGridPath(gridPath));

        // Méthode 2 : sudoku solver
        gridParsed.initFromFile(gridPath);
        gridSolved.initFromGrid(gridParsed);
    }

    /**
     * Vérifie et ajoute l'élément donné après vérification. <br>
     * Renvoie un booléen indiquant si oui, ou non, l'élément a été ajouté.
     *
     * @param element l'élément à ajouter
     * @return <code>vrai</code> si l'élément a été ajouté; <br>
     * <code>faux</code> sinon
     * @throws IllegalArgumentException si l'élément à placer est vide, ou s'il existe déjà un élément à l'endroit voulu
     */
    public boolean placeElement(Element element) {
        if (element.isEmpty()) {
            throw new IllegalArgumentException("L'élément à placer est vide");
        }
        if (!gridParsed.getElement(element.getLine(), element.getColumn()).isEmpty()) {
            throw new IllegalArgumentException("Un élément est déjà placé à cet endroit");
        }

        if (!gridSolved.getElement(element.getLine(), element.getColumn()).equals(element)) {
            return false;
        }
        gridParsed.addElement(element);
        return true;
    }

    /**
     * Renvoie un booléen qui indique si la partie est terminée (<=> la grille de jeu est remplie).
     *
     * @return <code>vrai</code> si la grille de jeu est remplie; <br>
     * <code>faux</code> sinon.
     */
    public boolean isFinished() {
        return gridParsed.isFilled();
    }

    /**
     * Renvoie la difficulté de la partie en cours.
     *
     * @return la difficulté de la partie en cours
     */
    public Difficulte getDifficulte() {
        return difficulte;
    }

    /**
     * Renvoie la matrix d'élément à dessiner.
     *
     * @return la matrix d'élément à dessiner
     */
    public Element[][] getDrawableGrid() {
        return gridParsed.getMatrix();
    }

    /**
     * Renvoie le nombre à placer par l'utilisateur.
     *
     * @return le nombre à placer par l'utilisateur
     */
    public int getNumberToPlace() {
        return gridParsed.getPlaceableNumber();
    }
}