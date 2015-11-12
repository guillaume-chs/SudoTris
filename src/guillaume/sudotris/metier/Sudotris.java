package guillaume.sudotris.metier;

import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.grid.GridFilled;
import guillaume.sudotris.metier.grid.GridParsed;
import guillaume.sudotris.resources.GrilleFileProvider;

/**
 *
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
    private final GridFilled gridFilled;
    private Difficulte difficulte;

    /**
     * Default constructor
     */
    public Sudotris() {
        gridParsed = new GridParsed();
        gridFilled = new GridFilled();
    }

    /**
     * Initialise une partie de Sudotris pour une difficult� donn�e.
     *
     * @param difficulte la difficulte de la partie � jouer
     */
    public void init(Difficulte difficulte) {
        this.difficulte = difficulte;

        gridParsed.initFromFile(GrilleFileProvider.getParsedGridPath(difficulte));
        gridFilled.initFromFile(GrilleFileProvider.getFilledGridPath(difficulte));

        // gridParsed.solveGrid();
    }

    /**
     * V�rifie et place l'�l�ment donn� apr�s v�rification. <br>
     * Renvoie un bool�en indiquant si oui, ou non, l'�l�ment a �t� plac�.
     *
     * @param element l'�l�ment � placer
     * @return <code>vrai</code> si l'�l�ment a �t� plac�; <br>
     * <code>faux</code> sinon
     * @throws IllegalArgumentException si l'�l�ment � placer est vide, ou s'il existe d�j� un �l�ment � l'endroit voulu
     */
    public boolean placeElement(Element element) {
        if (element.isEmpty()) {
            throw new IllegalArgumentException("L'�l�ment � placer est vide");
        }
        if (!gridParsed.getElement(element.getLine(), element.getColumn()).isEmpty()) {
            throw new IllegalArgumentException("Un �l�ment est d�j� plac� � cet endroit");
        }

        if (!gridFilled.getElement(element.getLine(), element.getColumn()).equals(element)) {
            return false;
        }
        gridParsed.addElement(element);
        return true;
    }

    /**
     * Renvoie un bool�en qui indique si la partie est termin�e (<=> la grille de jeu est remplie).
     *
     * @return <code>vrai</code> si la grille de jeu est remplie; <br>
     * <code>faux</code> sinon.
     */
    public boolean isFinished() {
        return gridParsed.isFilled();
    }

    /**
     * Renvoie la difficult� de la partie en cours.
     *
     * @return la difficult� de la partie en cours
     */
    public Difficulte getDifficulte() {
        return difficulte;
    }

    /**
     * Renvoie la matrix d'�l�ment � dessiner.
     *
     * @return la matrix d'�l�ment � dessiner
     */
    public Element[][] getDrawableGrid() {
        return gridParsed.getMatrix();
    }

    /**
     * Renvoie le nombre � placer par l'utilisateur.
     *
     * @return le nombre � placer par l'utilisateur
     */
    public int getNumberToPlace() {
        return gridParsed.getPlaceableNumber();
    }
}