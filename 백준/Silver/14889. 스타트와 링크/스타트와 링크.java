import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_14889 {

    int n;
    int[][] map;
    boolean[] visit;
    int min = Integer.MAX_VALUE;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n];
        back(0, 0);

        System.out.println(min);
        br.close();
    }

    void back(int start, int count) {
        if (count == n / 2) {
            cal();
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                back(i + 1, count + 1);
                visit[i] = false;
            }
        }
    }

    void cal() {
        int val1 = 0;
        int val2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visit[i] && visit[j]) {         // i,j는 스타트 팀
                    val1 += map[i][j];
                    val1 += map[j][i];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!visit[i] && !visit[j]) {         // i,j는 링크 팀
                    val2 += map[i][j];
                    val2 += map[j][i];
                }
            }
        }
        min = Math.min(min, Math.abs(val1 - val2));
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_14889 s = new Solution_14889();
        s.solution();
    }
}

/**
 * 실버 1 14889번 스타트와 링크
 *
 * n명중 2/n 명 뽑아서 두 팀간의 능력치 차이 구하기
 * -> n의 범위 작음 -> 브루트포스 & 백트래킹으로 팀원 구성
 */
