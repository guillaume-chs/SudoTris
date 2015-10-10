package guillaume;

import guillaume.sudotris.view.SudotrisIHM;

/**
 * Created by guill_000 on 10/10/2015.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class Main {

    public static void main(String[] args) {
        final SudotrisIHM game = new SudotrisIHM();
        game.initializeGame();
        game.play();
    }
}