package guillaume.sudotris.view;

import guillaume.sudotris.metier.Difficulte;
import guillaume.sudotris.metier.Sudotris;
import guillaume.sudotris.metier.element.Element;
import guillaume.sudotris.metier.element.NotEmptyElement;

import java.util.Scanner;

/**
 * IHM du jeu de Sudotris. À lancer pour jouer.
 *
 * @author Guillaume Chanson
 * @version 1.0
 * @since 1.8
 */
public class SudotrisIHM {
    private final Scanner scanner;
    private final Sudotris sudotris;

    /**
     * Crée l'IHM du jeu de Sudotris.
     */
    public SudotrisIHM() {
        scanner = new Scanner(System.in);
        sudotris = new Sudotris();
        System.out.println(" Bienvenue dans mon jeu de SudoTrisGame ");
    }

    /**
     * Initialise le jeu de Sudotris : gère la création du jeu pour une grille de niveau à choisir.
     */
    public void initializeGame() {
        System.out.print("Pour commencer, choisis la difficulté de jeu (1=easy, 2=moyen, 3=hard) : ");

        final int difficulte = scanner.nextInt();
        switch (difficulte) {
            case 2:
                sudotris.init(Difficulte.MEDIUM);
                break;
            case 3:
                sudotris.init(Difficulte.HARD);
                break;
            default:
                sudotris.init(Difficulte.EASY);
                break;
        }

        System.out.println("Game's ready, let's play !");
    }

    /**
     * C'est parti ! Démarre le jeu. Il faut l'avoir pour cela initialisé.
     */
    public void start() {
        do {
            initializeGame();
            play();
        } while (playAgain());

        System.out.println("Goodbye ! :)");
        scanner.close();
    }

    /**
     * La boucle de jeu. Appelée par SudotrisIHM#start().
     */
    public void play() {
        while (!sudotris.isFinished()) {
            renderMatrix(sudotris.getDrawableGrid());

            // Le joueur tente sa chance
            // true => réussi
            final boolean elementCorrect = sudotris.placeElement(askForNumber());

            if (elementCorrect) {
                System.out.println("Bien joué !");
            } else {
                System.out.println("Wrong location");
            }
        }

        // Gagné !
        System.out.println(" ---------------- ");
        System.out.println("|  You've won !  |");
        System.out.println(" ---------------- ");
    }

    /**
     * Rejouer ? - retourne un booléen indiquant si le joueur souhaite rejouer.
     *
     * @return <code>true</code> si le joueur souhaite rejouer;<br>
     * <code>false</code> sinon
     */
    protected boolean playAgain() {
        System.out.print("Rejouer (y/N) : ");

        final char c = scanner.next().toLowerCase().charAt(0);
        return (c == 'y');
    }

    /**
     * Gère le rendu en console de la grille de jeu.
     *
     * @param matrix la matrice de jeu à afficher
     */
    protected void renderMatrix(Element[][] matrix) {
        System.out.println("    1 2 3   4 5 6   7 8 9 ");

        for (byte i = 0; i < matrix.length; i++) {
            if (i % 3 == 0) {
                System.out.println("   ----------------------- ");
            }

            System.out.print((i + 1) + " | ");

            for (byte j = 0; j < matrix[i].length; j++) {
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
    }

    /**
     * Gère l'input d'une case, à savoir la demande de placement d'un chiffre plaçable.
     *
     * @return l'Element correspond au choix du joueur.
     * @see Element
     */
    protected Element askForNumber() {
        final int nbToPlace = sudotris.getNumberToPlace();
        System.out.print("A toi de jouer, place " + nbToPlace + " <ligne,colonne> : ");

        final String[] input = scanner.next().split(",");

        final byte line = (byte) (Byte.valueOf(input[0]) - 1);
        final byte column = (byte) (Byte.valueOf(input[1]) - 1);

        return new NotEmptyElement(line, column, nbToPlace);
    }
}