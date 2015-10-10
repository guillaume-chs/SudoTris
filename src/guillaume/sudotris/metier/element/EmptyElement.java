package guillaume.sudotris.metier.element;

/**
 * Gère un élément vide de la grille de jeu.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Element
 * @since 1.8
 */
public class EmptyElement extends Element {

    /**
     * Construit un élément à partir de sa ligne et de sa colonne.
     *
     * @param line   ligne de l'élément sur la grille de jeu
     * @param column colonne de l'élément sur la grille de jeu
     * @see Element#Element(byte, byte)
     */
    public EmptyElement(byte line, byte column) {
        super(line, column);
    }

    /**
     * Renvoie <code>vrai</code> : EmptyElement représente un élément vide.
     *
     * @return <code>vrai</code>
     * @see Element#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Renvoie le nombre à la ligne et colonne représentées par cet élément. <br>
     * En l'occurence, toujours 0 puisque l'élément est un élément vide.
     *
     * @return 0
     * @see Element#getNumber()
     */
    @Override
    public int getNumber() {
        return 0;
    }

}