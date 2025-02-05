import java.io.*;
import java.util.*;

class Solution_2580 {

    int[][] map;
    List<int[]> list;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        list = new ArrayList<>();        // 빈칸의 위치를 저장
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) list.add(new int[]{i, j});
            }
        }

        back(0, new int[]{0, 0});
    }

    void back(int idx, int[] before) {
        if (idx == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);     // 종료
        }

        int[] cur = list.get(idx);

        for (int num = 1; num <= 9; num++) {
            if (play(cur, num)) {
                back(idx + 1, cur);
            }
        }

        // cur 위치에 1 ~ 9 까지의 숫자중 넣을 수 있는 숫자가 없는 경우
        // 이전 단계로 되돌아 가야 한다
        map[before[0]][before[1]] = 0;
    }

    boolean play(int[] cur, int num) {
        for (int col = 0; col < 9; col++) {
            if (map[cur[0]][col] == num) return false;
        }

        for (int row = 0; row < 9; row++) {
            if (map[row][cur[1]] == num) return false;
        }

        int xQ = cur[0] / 3;
        int yQ = cur[1] / 3;
        for (int row = xQ * 3; row <= xQ * 3 + 2; row++) {
            for (int col = yQ * 3; col <= yQ * 3 + 2; col++) {
                if (map[row][col] == num) return false;
            }
        }

        map[cur[0]][cur[1]] = num;      // 현재 위치를 num으로 채울 수 있다
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2580 s = new Solution_2580();
        s.solution();
    }
}

/**
 * 빈칸에 1 ~ 9 까지의 숫자들을 넣어보면서 가능한 경우 다음빈칸으로 넘어가자
 *
 * cf) 이때 현재 위치에서 가능한 수여서 채운 것을 나중에 수정해야하는 상황도 고려해야 한다 -> 백트래킹
 *
 */