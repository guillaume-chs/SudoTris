package guillaume.sudotris.metier.element;

/**
 * Classe Element qui définit un élément de la grille de jeu. C'est la classe métier qui gère une case.
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
     * Construit un élément à partir de sa ligne et de sa colonne.
     *
     * @param line   ligne de l'élément sur la grille de jeu
     * @param column colonne de l'élément sur la grille de jeu
     */
    protected Element(byte line, byte column) {
        this.line = line;
        this.column = column;
    }

    /**
     * Indique si l'élément est vide, ou non.
     *
     * @return <code>vrai</code> si l'élément est vide; <br>
     * <code>faux</code> sinon.
     */
    public abstract boolean isEmpty();

    /**
     * Renvoie la ligne de l'élément.
     *
     * @return la ligne de l'élément
     */
    public byte getLine() {
        return this.line;
    }

    /**
     * Renvoie la colonne de l'élément.
     *
     * @return la colonne de l'élément
     */
    public byte getColumn() {
        return this.column;
    }

    /**
     * Renvoie le nombre de l'élément.
     *
     * @return le nombre de l'élément
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