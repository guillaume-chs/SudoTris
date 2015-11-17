# SudoTris
Sudoku - Tetris - 3D .... SudoTris

--------
## Architecture du projet
- **guillaume.sudotris** :
    - **fileio**  gère le parsage d'une grille de jeu stockée dans un fichier .txt
    - **resources** contient les classes "d'aide", concernant
        - le chemin des fichiers stockant les grilles de jeu,
        - l'algorithme de résolution d'une grille donnée, quelqu'elle soit.
    - **metier**  classes pûrement métiers, avec notamment la grille de jeu, la classe Sudotris, les éléments (cases)
    - **view**  classes IHM interfaçant en console avec l'utilisateur.


## Algorithme de résolution
Se base sur le backtracking.
Possibilités d'optimisation, mais la résolution se fait en temps machine.

Lors de l'utilisation dans une GUI, penser à lancer l'algorithme dans un thread quand même.

--------
## Bon jeu ! :)