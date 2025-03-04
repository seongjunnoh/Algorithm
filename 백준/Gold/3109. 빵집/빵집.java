import java.io.*;
import java.util.*;

class Solution_3109 {

    int r, c;
    char[][] map;
    boolean[][] visit;
    int max = 0;
    boolean flag = false;

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

        visit = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            visit[i][0] = true;
            if (play(i, 0)) max++;          // true를 반환받아야 max++
        }

        System.out.println(max);
        br.close();
    }

    boolean play(int row, int col) {
        if (col == c - 1) return true;

        if (row - 1 >= 0 && row - 1 < r && map[row - 1][col + 1] == '.' && !visit[row - 1][col + 1]) {
            visit[row - 1][col + 1] = true;
            if (play(row - 1, col + 1)) return true;
        }

        if (row >= 0 && row < r && map[row][col + 1] == '.' && !visit[row][col + 1]) {
            visit[row][col + 1] = true;
            if (play(row, col + 1)) return true;
        }

        if (row + 1 >= 0 && row + 1 < r && map[row + 1][col + 1] == '.' && !visit[row + 1][col + 1]) {
            visit[row + 1][col + 1] = true;
            if (play(row + 1, col + 1)) return true;
        }

        return false;       // 더 이상 파이프라인 이어갈 수 없다
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_3109 s = new Solution_3109();
        s.solution();
    }
}

/**
 * 놓을 수 있는 파이프라인의 최대 개수 구하기
 * -> 최대개수를 어떻게 구하지 ?? -> 높은 위치에서 시작한 파이프는 최대한 위에서 놀도록 하자 (그리디)
 *
 * 더이상 파이프라인을 이어갈 수 없는 경우 이전 상태로 백트래킹, 이때 visit를 다시 false로 바꾸지 않는다
 * -> 현재 파이프라인을 이어갈 수 없다 == 다음 시도에서 이 경로로 들어올 경우에도 파이프라인을 이어갈 수 없다
 * -> 따라서 visit를 계속 true로 유지해야 한다
 */