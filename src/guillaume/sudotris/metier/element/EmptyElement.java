package guillaume.sudotris.metier.element;

/**
 * G�re un �l�ment vide de la grille de jeu.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Element
 * @since 1.8
 */
public class EmptyElement extends Element {

    /**
     * Construit un �l�ment � partir de sa ligne et de sa colonne.
     *
     * @param line   ligne de l'�l�ment sur la grille de jeu
     * @param column colonne de l'�l�ment sur la grille de jeu
     * @see Element#Element(byte, byte)
     */
    public EmptyElement(byte line, byte column) {
        super(line, column);
    }

    /**
     * Renvoie <code>vrai</code> : EmptyElement repr�sente un �l�ment vide.
     *
     * @return <code>vrai</code>
     * @see Element#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Renvoie le nombre � la ligne et colonne repr�sent�es par cet �l�ment. <br>
     * En l'occurence, toujours 0 puisque l'�l�ment est un �l�ment vide.
     *
     * @return 0
     * @see Element#getNumber()
     */
    @Override
    public int getNumber() {
        return 0;
    }

}