import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_1987 {

    int r, c;
    char[][] map;
    boolean[] alpha;        // i번째 알파벳을 지나쳤는지
    boolean[][] visit;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int max = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        alpha = new boolean[26];            // 알파벳 총 26개
        visit = new boolean[r][c];

        alpha[map[0][0] - 'A'] = true;
        visit[0][0] = true;
        back(0, 0, 1);

        System.out.println(max);
        br.close();
    }

    void back(int x, int y, int count) {
        max = Math.max(max, count);

        for (int i = 0; i < 4; i++) {
            int nX = x + pos[i][0];
            int nY = y + pos[i][1];
            if (nX < 0 || nX >= r || nY < 0 || nY >= c) continue;
            if (!visit[nX][nY] && !alpha[map[nX][nY] - 'A']) {
                alpha[map[nX][nY] - 'A'] = true;
                visit[nX][nY] = true;

                back(nX, nY, count + 1);

                alpha[map[nX][nY] - 'A'] = false;
                visit[nX][nY] = false;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1987 s = new Solution_1987();
        s.solution();
    }
}

/**
 * 말이 이동하면서 지나는 알파벳이 모두 달라야 할 때, 말이 최대로 몇 칸을 지날 수 있는가
 * 백트래킹으로 가능한 모든 경우의 수 판단
 * -> 시간초과 안 걸리나 -> r,c가 작아서 백트래킹해도 시간은 ㄱㅊ은듯
 * 
 * cf) HashSet은 add, remove 연산이 O(1) 괜찮, 다른 방법으로는 현재까지 지나친 알파벳을 기록하는 boolean[] 배열을 선언하는 방법이 있음
 */
