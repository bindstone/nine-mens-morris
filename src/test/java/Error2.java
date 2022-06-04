import java.util.*;

public class Error2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random(2);

        List<String> check = Arrays.asList(
            "PLACE;A1",
            "PLACE;D1",
            "PLACE;D1",
            "PLACE;B2",
            "PLACE;F2",
            "PLACE;C3",
            "PLACE;D3",
            "PLACE;D3",
            "PLACE;E4",
            "MOVE;F2;F4",
            "MOVE;A1;D1",
            "MOVE;D1;A1",
            "MOVE;A1;D1",
            "MOVE;D1;A1"
        );

        String playerId = in.next();
        System.err.println(playerId);

        int fields = in.nextInt();
        for (int i = 0;i < fields; i++) {
            System.err.println(in.next());
        }
        int pos = 0;
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

            System.out.println(check.get(pos++));
        }
    }
}
