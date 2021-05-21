import java.util.*;

class Player {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String playerId = in.next();

        while (true) {
            String lastMove = in.next();
            String board = in.next();

            int mc = Integer.parseInt(in.next());
            List<String> moves = new ArrayList<>();
            for (int i = 0; i < mc; i++) {
                moves.add(in.next());
            }

            Optional<String> take = moves.stream().filter(s -> s.contains("TAKE")).findFirst();
            if (take.isPresent()) {
                System.out.println(take.get());
            } else {
                System.out.println(moves.get(0));
            }
        }
    }
}
