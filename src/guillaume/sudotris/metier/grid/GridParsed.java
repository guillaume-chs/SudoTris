package guillaume.sudotris.metier.grid;


import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.metier.element.NotEmptyElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * G�re la grille partielle de jeu. <br>
 * Chaque �l�ment plac� est une instance de NotEmptyElement, et chaque �l�ment vide est une instance d'EmptyElement.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see guillaume.sudotris.metier.element.NotEmptyElement
 * @see guillaume.sudotris.metier.element.EmptyElement
 * @see Grid
 * @since 1.8
 */
public class GridParsed extends Grid {
    /**
     * Liste des �l�ments pla�ables. Le fonctionnement est le suivant : <br>
     * <ul>
     * <li>les nombres sont les index du tableau : <i>{0, 1, 2, ... 8} -> nombres {1, 2, 3, ... 9}</i></li>
     * <li>la valorisation correspond au nombre d'occurences plac�es du nombre</li>
     * </ul>.
     * <u>Exemple :</u> si 3 est plac� 4 fois, alors placeableElements[2] = 4.
     *
     * @see GridParsed#getPlaceableNumber()
     */
    private final Integer[] placeableElements;

    /**
     * Constructeur par d�faut d'une grille finie.
     *
     * @see Grid#Grid()
     */
    public GridParsed() {
        super();
        placeableElements = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    /**
     * Indique si grille de jeu est compl�tement remplie. <br>
     * Ici, l'ensemble de la grille de jeu est v�rifi�e afin de retourner vrai ssi tous les �l�ments ont �t� renseign�s.
     *
     * @return <code>vrai</code> si la grille est compl�tement et correctement remplie; <br>
     * <code>faux</code> sinon.
     * @see Grid#isFilled()
     */
    @Override
    public boolean isFilled() {
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                if (matrix[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Remplit la grille de jeu depuis le fichier de chemin donn�. <br>
     * Cette red�finition est � utiliser pour la classe GridParsed.
     *
     * @param path le chemin (relatif ou absolu) du fichier � parser
     */
    @Override
    public void initFromFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            final byte[] lineIndex = {0};
            final byte[] columnIndex = {0};

            lines.forEach(line -> {
                Arrays.asList(line.split(" ")).forEach(letter -> {
                    final Integer value = Integer.valueOf(letter);
                    if (value >= 1 && value <= 9) {
                        matrix[lineIndex[0]][columnIndex[0]] = new NotEmptyElement(lineIndex[0], columnIndex[0], value);
                        placeableElements[value - 1] += 1;
                    } else {
                        matrix[lineIndex[0]][columnIndex[0]] = new EmptyElement(lineIndex[0], columnIndex[0]);
                    }

                    columnIndex[0]++;
                });

                columnIndex[0] = 0;
                lineIndex[0]++;
            });
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * M�thode � utiliser pour ajouter un �l�ment � la grille de jeu. <br>
     * G�re l'ajout et les cons�quences de l'ajout (actualisation de la liste de possibilit�, ...).
     *
     * @param element l'�l�ment � ajouter � la grille de jeu
     * @throws IllegalArgumentException si un �l�ment existe d�j�, ou si l'�l�ment est vide
     */
    public void addElement(Element element) {
        if (!matrix[element.getLine()][element.getColumn()].isEmpty()) {
            throw new IllegalArgumentException("L'�l�ment existe d�j�");
        }
        if (element.isEmpty()) {
            throw new IllegalArgumentException("L'�l�ment est vide");
        }
        matrix[element.getLine()][element.getColumn()] = element;
        placeableElements[element.getNumber() - 1] += 1;
    }

    /**
     * Renvoie un nombre pla�able sur la grille de jeu. Le nombre fait partie de ceux non encore plac�s.
     *
     * @return un nombre � placer sur la grille
     * @throws IllegalStateException s'il n'y a plus de valeurs � placer
     */
    public int getPlaceableNumber() {
        List<Integer> pool = new ArrayList<>();
        for (byte i = 0; i < Sudotris.LINES; i++) {
            if (placeableElements[i] < 9) {
                pool.add(i + 1);
            }
        }

        if (pool.isEmpty()) {
            throw new IllegalStateException("Il n'y a plus de valeur � ajouter");
        }

        return pool.get(ThreadLocalRandom.current().nextInt(pool.size()));
    }
}