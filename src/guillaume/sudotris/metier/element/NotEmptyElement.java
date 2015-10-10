package guillaume.sudotris.metier.element;

/**
 * G�re un �l�ment non vide de la grille de jeu, avec un nombre d�fini.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see Element
 * @since 1.8
 */
public class NotEmptyElement extends Element {
    private final int number;

    /**
     * Construit un �l�ment � partir de sa ligne, de sa colonne, et de son chiffre.
     *
     * @param line   ligne de l'�l�ment sur la grille de jeu
     * @param column colonne de l'�l�ment sur la grille de jeu
     * @param number le nombre de l'�l�ment
     * @see Element#Element(byte, byte)
     */
    public NotEmptyElement(byte line, byte column, int number) {
        super(line, column);
        this.number = number;
    }

    /**
     * Renvoie <code>faux</code> : NotEmptyElement repr�sente un �l�ment non vide.
     *
     * @return <code>faux</code>
     * @see Element#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Renvoie le nombre � la ligne et colonne repr�sent�es par cet �l�ment.
     *
     * @return le nombre de cet �l�ment
     * @see Element#getNumber()
     */
    @Override
    public int getNumber() {
        return this.number;
    }

}