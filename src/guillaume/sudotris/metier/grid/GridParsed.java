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
 * Gère la grille partielle de jeu. <br>
 * Chaque élément placé est une instance de NotEmptyElement, et chaque élément vide est une instance d'EmptyElement.
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
     * Liste des éléments plaçables. Le fonctionnement est le suivant : <br>
     * <ul>
     * <li>les nombres sont les index du tableau : <i>{0, 1, 2, ... 8} -> nombres {1, 2, 3, ... 9}</i></li>
     * <li>la valorisation correspond au nombre d'occurences placées du nombre</li>
     * </ul>.
     * <u>Exemple :</u> si 3 est placé 4 fois, alors placeableElements[2] = 4.
     *
     * @see GridParsed#getPlaceableNumber()
     */
    private final Integer[] placeableElements;

    /**
     * Constructeur par défaut d'une grille finie.
     *
     * @see Grid#Grid()
     */
    public GridParsed() {
        super();
        placeableElements = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    /**
     * Indique si grille de jeu est complètement remplie. <br>
     * Ici, l'ensemble de la grille de jeu est vérifiée afin de retourner vrai ssi tous les éléments ont été renseignés.
     *
     * @return <code>vrai</code> si la grille est complètement et correctement remplie; <br>
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
     * Remplit la grille de jeu depuis le fichier de chemin donné. <br>
     * Cette redéfinition est à utiliser pour la classe GridParsed.
     *
     * @param path le chemin (relatif ou absolu) du fichier à parser
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
     * Méthode à utiliser pour ajouter un élément à la grille de jeu. <br>
     * Gère l'ajout et les conséquences de l'ajout (actualisation de la liste de possibilité, ...).
     *
     * @param element l'élément à ajouter à la grille de jeu
     * @throws IllegalArgumentException si un élément existe déjà, ou si l'élément est vide
     */
    public void addElement(Element element) {
        if (!matrix[element.getLine()][element.getColumn()].isEmpty()) {
            throw new IllegalArgumentException("L'élément existe déjà");
        }
        if (element.isEmpty()) {
            throw new IllegalArgumentException("L'élément est vide");
        }
        matrix[element.getLine()][element.getColumn()] = element;
        placeableElements[element.getNumber() - 1] += 1;
    }

    /**
     * Renvoie un nombre plaçable sur la grille de jeu. Le nombre fait partie de ceux non encore placés.
     *
     * @return un nombre à placer sur la grille
     * @throws IllegalStateException s'il n'y a plus de valeurs à placer
     */
    public int getPlaceableNumber() {
        List<Integer> pool = new ArrayList<>();
        for (byte i = 0; i < Sudotris.LINES; i++) {
            if (placeableElements[i] < 9) {
                pool.add(i + 1);
            }
        }

        if (pool.isEmpty()) {
            throw new IllegalStateException("Il n'y a plus de valeur à ajouter");
        }

        return pool.get(ThreadLocalRandom.current().nextInt(pool.size()));
    }
}