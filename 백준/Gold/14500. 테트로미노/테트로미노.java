import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int result = 0;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void makeTeth(int depth, int sX, int sY, int sum) throws Exception {
        if (depth == 4) {
            result = Math.max(result, sum);
            return;
        }

        if (depth == 3) {
            // 원래 하던대로 dfs
            playDfs(depth, sX, sY, sum);

            // ㅗ 모양 예외처리
            int[] nStart = calNewStartSet(sX, sY);
            playDfs(depth, nStart[0], nStart[1], sum);
            return;
        }

        playDfs(depth, sX, sY, sum);
    }

    static void playDfs(int depth, int sX, int sY, int sum) throws Exception {
        for (int i = 0; i < 4; i++) {
            int nX = sX + pos[i][0];
            int nY = sY + pos[i][1];

            if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;

            if (!visit[nX][nY]) {
                visit[nX][nY] = true;
                makeTeth(depth + 1, nX, nY, sum + map[nX][nY]);
                visit[nX][nY] = false;
            }
        }
    }

    static int[] calNewStartSet(int sX, int sY) throws Exception {
        for (int i = 0; i < 4; i++) {
            int nX = sX + pos[i][0];
            int nY = sY + pos[i][1];
            if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
            if (visit[nX][nY]) {
                int[] set = {nX, nY};
                return set;         // 이전단계의 좌표 return
            }
        }

        throw new Exception("로직 이상");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 테트리스 왼쪽 상단 : (i, j)
                visit[i][j] = true;
                makeTeth(1, i, j, map[i][j]);
                visit[i][j] = false;
            }
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 4 14500 테트로미노
 *
 * 문제에서 주어진 테트로미노 & 이것들을 회전, 대칭이동 시킨 것들 == 정사각형 4개로 만들 수 있는 모든 형태
 * => 백트래킹으로 테트로미노 형태 만들어서 이걸 map 에 놓는 방식으로 구현
 * cf. ㅗ 모양은 백트래킹으로 만들 수 없음 => 예외처리 필요
 *
 */
