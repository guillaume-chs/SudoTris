package guillaume.sudotris.resources;

import guillaume.sudotris.metier.Difficulte;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Cette classe gère l'accés aux grilles partielles remplies de jeu, ou complètement complétées de vérification.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class GrilleFileProvider {
    /**
     * Racine des fichiers.
     */
    private static final String BASE_URL = "src/main/resources/";

    /**
     * Renvoie le chemin d'accés à la grille partiellement remplie de jeu, en fonction du niveau de difficulté donné.
     *
     * @param difficulte le niveau de difficulté de la partie.
     * @return le chemin d'accés à la grille partiellement remplie de jeu
     */
    public static Path getParsedGridPathByDifficulte(Difficulte difficulte) {
        return Paths.get(BASE_URL + "g" + difficulte.getLevel() + "_sparse.txt");
    }

    /**
     * Renvoie le chemin d'accés à la grille complétée, en fonction du niveau de difficulté donné.
     *
     * @param difficulte le niveau de difficulté de la partie.
     * @return le chemin d'accés à la grille complétée
     */
    public static Path getFilledGridPathByDifficulte(Difficulte difficulte) {
        return Paths.get(BASE_URL + "g" + difficulte.getLevel() + "_full.txt");
    }
}