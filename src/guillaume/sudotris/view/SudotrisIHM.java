package guillaume.sudotris.view;

import guillaume.sudotris.metier.Difficulte;
import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.NotEmptyElement;

import java.util.Scanner;

/**
 * IHM du jeu de Sudotris. Classe de plus haut niveau.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class SudotrisIHM {

    private final Scanner scanner;
    private final Sudotris sudotris;

    public SudotrisIHM() {
        scanner = new Scanner(System.in);
        sudotris = new Sudotris();
        System.out.println(" Bienvenue dans mon jeu de SudoTrisGame ");
    }

    public void initializeGame() {
        System.out.print("Pour commencer, choisis la difficulté de jeu (1=easy, 2=hard) : ");

        final int difficulte = scanner.nextInt();
        switch (difficulte) {
            case 2:
                sudotris.init(Difficulte.HARD);
                break;
            default:
                sudotris.init(Difficulte.EASY);
                break;
        }

        System.out.println("Game's ready, let's play !");
    }

    public void play() {
        this.renderMatrix(sudotris.getDrawableGrid());

        // If won
        if (sudotris.isFinished()) {
            System.out.println(" ----------------------- ");
            System.out.println("|  You've won ! GG  :)  |");
            System.out.println(" ----------------------- ");
            return;
        }

        // Else, ask for a number
        if (sudotris.placeElement(this.askForNumber())) {
            System.out.println("Yes !");
        } else {
            System.out.println("Wrong number");
        }

        // Else, try another guess
        play();
    }

    public void renderMatrix(Element[][] matrix) {
        System.out.println("    1 2 3   4 5 6   7 8 9 ");

        for (byte i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("   ----------------------- ");
            }

            System.out.print((i + 1) + " | ");
            for (byte j = 0; j < 9; j++) {
                if (j % 3 == 0 && j > 0) {
                    System.out.print("| ");
                }
                if (matrix[i][j].isEmpty()) {
                    System.out.print("  ");
                } else {
                    System.out.print(matrix[i][j].getNumber() + " ");
                }
            }
            System.out.println("|");
        }

        System.out.println("   ----------------------- ");
        System.out.println("    1 2 3   4 5 6   7 8 9");
    }

    public Element askForNumber() {
        final int nbToPlace = sudotris.getNumberToPlace();
        System.out.print("A toi de jouer, place " + nbToPlace + " <ligne,colonne> : ");

        final String[] input = scanner.next().split(",");

        final byte line = (byte) (Byte.valueOf(input[0]) - 1);
        final byte column = (byte) (Byte.valueOf(input[1]) - 1);

        return new NotEmptyElement(line, column, nbToPlace);
    }
}