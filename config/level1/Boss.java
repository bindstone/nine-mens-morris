import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int playerId = in.nextInt(); // playerId (0,1)
        int fields = in.nextInt(); // number of fields
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < fields; i++) {
            String neighbors = in.nextLine(); // neighbors of a field (ex: A1:A4;D1)
        }

        // game loop
        while (true) {
            String opMove = in.nextLine(); // The last move executed from the opponent
            String board = in.nextLine(); // Current Board and state(0:Player0 | 1:Player1 | 2:Empty) in format field:state and separated by ;
            int nbr = in.nextInt(); // Number of valid moves proposed.
            if (in.hasNextLine()) {
                in.nextLine();
            }
            List<String> moves = new ArrayList<>();
            for (int i = 0; i < nbr; i++) {
                String command = in.nextLine(); // An executable command line
                moves.add(command);
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            Optional<String> take = moves.stream().filter(s -> s.contains("TAKE")).findFirst();
            if (take.isPresent()) {
                System.out.println(take.get());
            } else {
                System.out.println(moves.get(0));
            }
        }
    }
}