import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random(2);

        String playerId = in.next();
        System.err.println(playerId);

        while (true) {
            String lastMove = in.next();
            System.err.println(lastMove);
            String board = in.next();
            System.err.println(board);

            int mc = Integer.parseInt(in.next());
            List<String> moves = new ArrayList<>();
            for (int i = 0; i < mc; i++) {
                moves.add(in.next());
            }
            try {
                Thread.sleep(48);
            }catch(Exception e) {}
            String move = moves.get(random.nextInt(moves.size()));
            System.out.println(move);
        }
    }
}
