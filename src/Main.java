import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConnectFour c4 = new ConnectFour();
        System.out.print(c4);

        while (true) {
            System.out.println("Which column would " + c4.turn + " like to go in (7 to save, 8 to load, 9 to quit)");
            int choice = sc.nextInt();
            if (choice == 9) break;
            if (choice == 7) {
                c4.saveGame();
            }
            if (choice == 8) {
                c4.loadGame();
            }
            if (choice >= 0  && choice <= 6) {
                try {
                    c4.dropPiece(choice, c4.token);
                    c4.nextTurn();
                } catch (ColumnFull e) {
                    System.out.println(e);
                }
            }
            System.out.println(c4);
        }
    }
}
