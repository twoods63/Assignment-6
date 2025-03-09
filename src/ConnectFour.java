import java.io.*;
import java.util.Scanner;

public class ConnectFour {
    private final char[][] board = new char[6][7];
    public String turn;
    public char token;

    public ConnectFour() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++)
                board[i][j] = ' ';
            this.turn = "Red";
            this.token = 'R';
        }
    }
    public void nextTurn() {
        if (this.turn.equals("Red")) {
            this.turn = "Yellow";
            this.token = 'Y';
        } else {
            this.turn = "Red";
            this.token = 'R';
        }
    }
    public int nextAvailablePosition(int col) {
        for (int i = 5; i >= 0; i--) {
            if (this.board[i][col] == ' ')
                return i;
        }
        return -1;
    }
    public void dropPiece(int col, char tok) throws ColumnFull {
        int row = nextAvailablePosition(col);
        if (row == -1) {
            throw new ColumnFull("This column in full. Try another column!");
        }
        this.board[row][col] = tok;
    }
    @Override
    public String toString() {
        String to_return="  0   1   2   3   4   5   6";

        for(int i = 0; i < 6; i++) {
            to_return+="\n-----------------------------\n ";
            to_return+="| ";
            for(int j = 0; j < 7; j++) {
                to_return+=board[i][j]+" | ";
            }
        }

        to_return+="\n-----------------------------\n ";
        return to_return;
    }
    public void saveGame () {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a filename: ");
            String filename = sc.nextLine();
            FileWriter writer = new FileWriter(filename);

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    writer.write(board[i][j] + ";");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGame() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a filename:");
            String filename = sc.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(";");
                for (int i = 0; i < cells.length; i++) {
                    board[row][i] = cells[i].charAt(0);
                }
                row++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read that file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ColumnFull extends Exception {
    public ColumnFull(String str) {
        super(str);
    }
}

