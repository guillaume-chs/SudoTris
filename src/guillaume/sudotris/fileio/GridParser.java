package guillaume.sudotris.fileio;

import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.EmptyElement;
import guillaume.sudotris.metier.element.NotEmptyElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Classe charg?e de lire un fichier source de sudoku de la forme :
 * <quote>0 8 0 0 3 0 0 7 0</quote> ...
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @see guillaume.sudotris.metier.grid.Grid
 * @since 1.8
 */
public class GridParser {
    /**
     * Lit le fichier d'une grille de jeu, et en renvoie la matrix des ?l?ments, compos?e de :
     * <ul>
     * <li><code>NotEmptyElement</code> si le nombre est compris entre [1, 9]</li>
     * <li><code>EmptyElement</code> sinon</li>
     * </ul>.
     *
     * @param path le chemin vers la grille de jeu
     * @return la matrix d'?l?ments correspondante
     */
    public static Element[][] parseFileToMatrix(Path path) {
        final Element[][] matrix = new Element[Sudotris.LINES][Sudotris.COLUMNS];

        try (Stream<String> lines = Files.lines(path)) {
            final byte[] lineIndex = {0};
            final byte[] columnIndex = {0};

            lines.forEach(line -> {
                Arrays.asList(line.split(" ")).forEach(letter -> {
                    final Integer value = Integer.valueOf(letter);
                    if (value >= 1 && value <= 9) {
                        matrix[lineIndex[0]][columnIndex[0]] = new NotEmptyElement(lineIndex[0], columnIndex[0], value);
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

        return matrix;
    }
}