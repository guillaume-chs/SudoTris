package guillaume.sudotris.resources;

import guillaume.sudotris.metier.Difficulte;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Cette classe g?re l'acc?s aux grilles partielles remplies de jeu, ou compl?tement compl?t?es de v?rification.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class GrilleFileProvider {
    /**
     * Racine des fichiers.
     */
    private static final String BASE_URL = "resources/";

    /**
     * Renvoie le chemin d'acc?s ? la grille partiellement remplie de jeu, en fonction du niveau de difficult? donn?.
     *
     * @param difficulte le niveau de difficult? de la partie.
     * @return le chemin d'acc?s ? la grille partiellement remplie de jeu
     */
    public static Path getParsedGridPathByDifficulte(Difficulte difficulte) {
        return Paths.get(BASE_URL + "g" + difficulte.getLevel() + "_sparse.txt");
    }

    /**
     * Renvoie le chemin d'acc?s ? la grille compl?t?e, en fonction du niveau de difficult? donn?.
     *
     * @param difficulte le niveau de difficult? de la partie.
     * @return le chemin d'acc?s ? la grille compl?t?e
     */
    public static Path getFilledGridPathByDifficulte(Difficulte difficulte) {
        return Paths.get(BASE_URL + "g" + difficulte.getLevel() + "_full.txt");
    }
}