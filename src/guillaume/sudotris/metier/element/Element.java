package guillaume.sudotris.metier.element;

/**
 * Classe Element qui d�finit un �l�ment de la grille de jeu. C'est la classe m�tier qui g�re une case.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see EmptyElement
 * @see NotEmptyElement
 * @since 1.8
 */
public abstract class Element {
    protected final byte line;
    protected final byte column;

    /**
     * Construit un �l�ment � partir de sa ligne et de sa colonne.
     *
     * @param line   ligne de l'�l�ment sur la grille de jeu
     * @param column colonne de l'�l�ment sur la grille de jeu
     */
    protected Element(byte line, byte column) {
        this.line = line;
        this.column = column;
    }

    /**
     * Indique si l'�l�ment est vide, ou non.
     *
     * @return <code>vrai</code> si l'�l�ment est vide; <br>
     * <code>faux</code> sinon.
     */
    public abstract boolean isEmpty();

    /**
     * Renvoie la ligne de l'�l�ment.
     *
     * @return la ligne de l'�l�ment
     */
    public byte getLine() {
        return this.line;
    }

    /**
     * Renvoie la colonne de l'�l�ment.
     *
     * @return la colonne de l'�l�ment
     */
    public byte getColumn() {
        return this.column;
    }

    /**
     * Renvoie le nombre de l'�l�ment.
     *
     * @return le nombre de l'�l�ment
     */
    public abstract int getNumber();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Element)) return false;

        final Element element = (Element) obj;
        return (element.getNumber() == getNumber() && element.getColumn() == getColumn() && element.getLine() == getLine());

    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + line;
        result = 31 * result + column;
        return result;
    }
}