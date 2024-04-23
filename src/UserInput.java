import java.util.Scanner;

public class UserInput {
    private final Scanner scanner = new Scanner(System.in);
    public int numberMines;
    public int x;
    public int y;
    public boolean freeMove;

    public void setNumberMines() {
        System.out.print("How many mines do you want on the field? ");
        try {
            numberMines = Integer.parseInt(scanner.nextLine());
            if (numberMines <= 0 || numberMines > 81)
                throw new IllegalArgumentException("Number of mines must be greater than 0 and less than 81.");
        } catch(NumberFormatException e) {
            System.out.println("input a number...");
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setMove() {
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        String[] userInput = scanner.nextLine().split(" ");
        x = Integer.parseInt(userInput[0]) - 1;
        y = Integer.parseInt(userInput[1]) - 1;
        freeMove = userInput[2].equals("free");
    }
}
