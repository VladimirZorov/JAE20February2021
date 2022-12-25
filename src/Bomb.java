import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bomb {
    public static int bombInField = 0;
    public static boolean hitEnd = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = bombInField;
        boolean m = hitEnd;

        int rowSapper = -1;
        int collSapper = -1;

        int fieldSize = Integer.parseInt(scanner.nextLine());
        List<String> commands = Arrays.stream(scanner.nextLine().split(","))
                .collect(Collectors.toList());

        String[][] field = new String[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; i++) {
            String[] line = scanner.nextLine().split(" ");
            field[i] = line;
            for (int j = 0; j < fieldSize; j++) {
                if (line[j].equals("s")) {
                    rowSapper = i;
                    collSapper = j;
                } else if (line[j].equals("B")) {
                    bombInField++;
                }
            }
        }


        while (commands.size()!=0){
            if (!hitEnd && bombInField != 0) {
                switch (commands.get(0)) {
                    case "up":
                        commands.remove(0);
                        if (rowSapper - 1 >= 0) {
                            field[rowSapper][collSapper] = "+";
                            rowSapper--;
                            checkForBombAndEnd(field, rowSapper, collSapper, hitEnd, bombInField);
                        }
                        break;
                    case "down":
                        commands.remove(0);
                        if (rowSapper + 1 < fieldSize) {
                            field[rowSapper][collSapper] = "+";
                            rowSapper++;
                            checkForBombAndEnd(field, rowSapper, collSapper, hitEnd, bombInField);
                        }
                        break;
                    case "left":
                        commands.remove(0);
                        if (collSapper - 1 >= 0) {
                            field[rowSapper][collSapper] = "+";
                            collSapper--;
                            checkForBombAndEnd(field, rowSapper, collSapper, hitEnd, bombInField);
                        }
                        break;
                    case "right":
                        commands.remove(0);
                        if (collSapper + 1 < fieldSize) {
                            field[rowSapper][collSapper] = "+";
                            collSapper++;
                            checkForBombAndEnd(field, rowSapper, collSapper, hitEnd, bombInField);
                        }
                        break;
                }
            } else {
                break;
            }
        }

        if (bombInField == 0) {
            System.out.println("Congratulations! You found all bombs!");
        } else if (hitEnd){
            System.out.printf("END! %d bombs left on the field", bombInField);
        } else if (commands.size() == 0 ) {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", bombInField, rowSapper, collSapper);
        }
    }

    public static void checkForBombAndEnd
            (String[][] field, int rowSapper, int collSapper, boolean m, int k) {
        if (field[rowSapper][collSapper].equals("e")) {
            hitEnd = true;
        } else if (field[rowSapper][collSapper].equals("B")) {
            bombInField -= 1;
            System.out.println("You found a bomb!");
            field[rowSapper][collSapper] = "+";
        }
    }
}
