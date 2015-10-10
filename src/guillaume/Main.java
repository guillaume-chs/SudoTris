package guillaume;

import guillaume.sudotris.view.SudotrisIHM;

public class Main {

    public static void main(String[] args) {
        final SudotrisIHM game = new SudotrisIHM();
        game.initializeGame();
        game.play();
    }
}