    import java.util.*;

public class Error1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<String> check = Arrays.asList(
            "PLACE;B4",
            "PLACE;C4",
            "PLACE&TAKE;A4;D1",
            "PLACE;G1",
            "PLACE;D2",
            "PLACE;D6",
            "PLACE;D5",
            "PLACE&TAKE;D7;D3",
            "PLACE;E3",
            "MOVE;C4;C5",
            "MOVE&TAKE;C5;C4;D1",
            "MOVE;B4;B6",
            "MOVE&TAKE;B6;B4;F4",
            "MOVE;B4;B6",
            "MOVE&TAKE;B6;B4;D1"
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
