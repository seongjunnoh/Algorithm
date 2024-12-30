import java.io.*;
import java.util.*;

class Solution_1520 {

    int row, col;
    int[][] map;
    int[][] dp;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], -1);         // -1 로 초기화 -> dp table update 전
        }
        System.out.println(dfs(0, 0));
        br.close();
    }

    int dfs(int x, int y) {
        if (x == row - 1 && y == col - 1) return 1;         // 목적지 -> 목적지 경로의 수 = 1
        if (dp[x][y] != -1) return dp[x][y];                // 이미 계산된 값은 바로 리턴

        dp[x][y] = 0;           // 계산되지 않았다면 0으로 초기화
        // 백트래킹
        for (int i = 0; i < 4; i++) {
            int nX = x + pos[i][0];
            int nY = y + pos[i][1];
            if (nX < 0 || nX >= row || nY < 0 || nY >= col) continue;

            if (map[nX][nY] < map[x][y]) {          // 내리막길 인 경우 dfs
                dp[x][y] += dfs(nX, nY);
            }
        }

        return dp[x][y];
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_1520 s = new Solution_1520();
        s.solution();
    }
}

/**
 * 골드 3 1520번 내리막 길
 *
 * (0,0) -> (row-1, col-1) 까지 가능한 모든 경로의 수
 * 최단거리가 아니라 내리막길로 이동가능한 모든 경로의 개수를 구해야 함
 * -> 백트래킹으로 내려갈 수 있을때까지 내려간 후, 다시 돌아와서 다른 경로 탐색
 * => 시간 초과 -> 메모이제이션 과정 없이 이미 방문했던 길이라도 매번 새로운 경로로 생각해서 그런 듯
 *
 * dp[x][y] : (x,y) -> (row-1, col-1) 로 갈 수 있는 경로의 수
 * dp[x][y] = (x,y) 주위 중, 낮은 지점에서의 dp 값들의 합
 * 정답 = dp[0][0]
 *
 */