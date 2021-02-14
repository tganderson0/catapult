import java.io.IOException;
import java.util.*;

public class driver {
    final static int GRAVITY_CONSTANT = 10;

    public static void main(String[] args){
        System.out.println("Welcome to Catapult\nPlease enter the launch velocity");
        Scanner scanner = new Scanner(System.in);
        int velocity = scanner.nextInt();
        System.out.println("Please enter a launch angle");
        int angle = scanner.nextInt();
        printAllPoints(allPoints(velocity, angle));

    }

    public static void printAllPoints(ArrayList<Integer> values){
        clearScreen();
        for (int x = 0; x < values.size(); x++){
            printPoint(x, values.get(x));
            sleep(1);
            clearScreen();
        }
    }

    private static void printPoint(int x, int y){
        StringBuilder[] plane = new StringBuilder[40];
        for (int i = 0; i < 40; i++){
            plane[i] = new StringBuilder("                                                                                                    ");
        }
        if (x < 100 && y < 40) {plane[plane.length - 1 - y].setCharAt(x, 'o');}

        for (StringBuilder i: plane){
            System.out.println(i);
        }
    }

    private static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ArrayList<Integer> allPoints(int velocity, int angle){
        int x = 0;
        ArrayList<Integer> builtPoints = new ArrayList<>();
        while (true){
            int currentVertHeight = verticalHeight(x, velocity, angle);
            builtPoints.add(currentVertHeight);
            if (currentVertHeight <= 0){
                break;
            }
            x++;
        }
        return builtPoints;
    }

    private static int verticalHeight(int x, int velocity, int angle){
        int height = (int) (x * Math.tan(angle) - ((GRAVITY_CONSTANT * Math.pow(x, 2))/(2 * Math.pow(velocity, 2) * Math.pow(Math.cos(angle), 2)))) + 1;
        return Math.max(height, 0);
    }
}
