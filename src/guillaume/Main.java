package guillaume;

import guillaume.sudotris.view.SudotrisIHM;

/**
 * Main class of the project.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class Main {

    /**
     * Launch this.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        final SudotrisIHM game = new SudotrisIHM();
        game.start();
    }
}