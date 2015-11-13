package guillaume.sudotris.resources;

import guillaume.sudotris.metier.Difficulte;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
    public static final String BASE_URL = "resources";

    /**
     * Renvoie le chemin d'accés à la grille partiellement remplie de jeu, en fonction du niveau de difficulté donné.
     *
     * @param difficulte le niveau de difficulté de la partie.
     * @return le chemin d'accés à la grille partiellement remplie de jeu
     */
    public static Path getParsedGridPath(Difficulte difficulte) {
        final String folder = BASE_URL + File.separatorChar + difficulte.name().toLowerCase();

        final String[] grids = Paths.get(folder).toFile().list((dir, name) -> {
            if (name.endsWith(".txt")) {
                if (!name.contains("filled")) {
                    return true;
                }
            }
            return false;
        });

        final String grid = grids[new Random().nextInt(grids.length)];
        return Paths.get(folder, grid);
    }

    /**
     * Renvoie le chemin d'accés à la grille complétée, en rapport avec la grille à remplir donnée.
     *
     * @param gridPath chemin d'accès à la grille à remplir
     * @return le chemin d'accés à la grille complétée
     */
    public static Path getFilledGridPath(Path gridPath) {
        final String gridStr = gridPath.toString();
        final String filled = gridStr.substring(0, gridStr.length() - ".txt".length()) + "_filled.txt";

        return Paths.get(filled);
    }
}