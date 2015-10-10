package guillaume.sudotris.metier.element;

/**
 * Gère un élément non vide de la grille de jeu, avec un nombre défini.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Element
 * @since 1.8
 */
public class NotEmptyElement extends Element {
    private final int number;

    /**
     * Construit un élément à partir de sa ligne, de sa colonne, et de son chiffre.
     *
     * @param line   ligne de l'élément sur la grille de jeu
     * @param column colonne de l'élément sur la grille de jeu
     * @param number le nombre de l'élément
     * @see Element#Element(byte, byte)
     */
    public NotEmptyElement(byte line, byte column, int number) {
        super(line, column);
        this.number = number;
    }

    /**
     * Renvoie <code>faux</code> : NotEmptyElement représente un élément non vide.
     *
     * @return <code>faux</code>
     * @see Element#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Renvoie le nombre à la ligne et colonne représentées par cet élément.
     *
     * @return le nombre de cet élément
     * @see Element#getNumber()
     */
    @Override
    public int getNumber() {
        return this.number;
    }

}