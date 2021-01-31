import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Player {

    Scanner in = new Scanner(System.in);
    Random random = new Random(1);

    String playerId = in.next();
        System.err.println(playerId);

        while (true) {
        int mc = Integer.parseInt(in.next());
        List<String> moves = new ArrayList<>();
        for (int i = 0; i < mc; i++) {
            moves.add(in.next());
        }
        String move = moves.get(random.nextInt(moves.size()));
        System.out.println(move);
    }
}

