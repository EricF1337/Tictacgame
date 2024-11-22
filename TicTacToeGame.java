 import java.util.Scanner;

// Klass som representerar spelbrädet
class Board {
    private char[][] board;

    // Konstruktor för att initiera spelbrädet med tomma utrymmen
    public Board() {
        board = new char[3][3];
        resetBoard();
    }

    // Metod för att skriva ut spelbrädet till konsolen
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Metod för att göra ett drag och kontrollera om det är giltigt
    public boolean makeMove(int row, int col, char symbol) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false; // Ogiltigt drag
        }
        board[row][col] = symbol;
        return true;
    }

    // Metod för att kontrollera om spelet är över eller vinst
    public boolean isGameOver() {
        return isWin() || isFull();
    }

    // Metod för att kontrollera om någon spelare har vunnit
    public boolean isWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true; // Radvinst
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true; // Kolumnvinst
            }
        }
        // Kontrollera diagonaler
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true; // Diagonalvinst
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true; // Diagonalvinst
        }
        return false;
    }

    // Metod för att kontrollera om spelbrädet är fullt
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Hittade en tom plats
                }
            }
        }
        return true; // Inga tomma platser hittades
    }

    // Metod för att återställa spelbrädet till dess ursprungliga tillstånd
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
}

// Klass som representerar en spelare
class Player {
    char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }
}

// Huvudklass som hanterar spelets logiska  och körning
public class TicTacToeGame {
    public static void main(String[] args) {
      
      
      
       // spelare variabeler och spel brädan 
       
        Board board = new Board();      
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        Player currentPlayer = player1;

        Scanner scanner = new Scanner(System.in);
        // while funktioner 
                while (true) {
            board.printBoard();

            System.out.print("Spelare " + currentPlayer.symbol + ", ange ditt drag (rad, kolumn): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
    // if satsterna och else 
            if (board.makeMove(row, col, currentPlayer.symbol)) {
                if (board.isGameOver()) {
                    board.printBoard();
                    if (board.isWin()) {
                        System.out.println("Spelare " + currentPlayer.symbol + " vinner!");
                    } else {
                        System.out.println("Det är oavgjort!");
                    }
                    System.out.print("Vill du spela igen? (ja/nej): ");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("nej")) {
                        break; // Avsluta spelet
                    }
                    board.resetBoard();
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Ogiltigt drag. Försök igen.");
            }
        }
        scanner.close();
    }
}
