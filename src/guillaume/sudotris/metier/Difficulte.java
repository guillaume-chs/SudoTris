package guillaume.sudotris.metier;

/**
 *
 */
public enum Difficulte {
    EASY(1),
    HARD(6);

    private final int level;

    Difficulte(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}