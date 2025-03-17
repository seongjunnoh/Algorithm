import java.io.*;
import java.util.*;

class Solution_2096 {

    int[] pos = {-1, 0, 1};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[3];     // 현재 line의 숫자
        int[][] max = new int[2][3];        // before, cur -> 이렇게 2개의 행만 있어도 된다
        int[][] min = new int[2][3];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 3; j++) {
            arr[j] = Integer.parseInt(st.nextToken());
            max[0][j] = arr[j];
            min[0][j] = arr[j];
        }

        Arrays.fill(max[1], 0);
        Arrays.fill(min[1], Integer.MAX_VALUE);
        for (int t = 0; t < n - 1; t++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < 3; j++) {
                for (int d = 0; d < 3; d++) {
                    if (j + pos[d] < 0 || j + pos[d] >= 3) continue;

                    max[1][j] = Math.max(max[1][j], arr[j] + max[0][j + pos[d]]);
                    min[1][j] = Math.min(min[1][j], arr[j] + min[0][j + pos[d]]);
                }
            }

            // max, min 배열 update
            for (int j = 0; j < 3; j++) {
                max[0][j] = max[1][j];
                min[0][j] = min[1][j];
            }

            Arrays.fill(max[1], 0);
            Arrays.fill(min[1], Integer.MAX_VALUE);
        }

        StringBuilder sb = new StringBuilder();
        int minResult = Integer.MAX_VALUE;
        int maxResult = 0;
        for (int i = 0; i < 3; i++) {
            minResult = Math.min(minResult, min[0][i]);
            maxResult = Math.max(maxResult, max[0][i]);
        }
        sb.append(maxResult).append(" ").append(minResult);
        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2096 s = new Solution_2096();
        s.solution();
    }
}

/**
 * 얻을 수 있는 최대점수, 최소점수 구하기
 * 전체 map은 100,000 * 3
 * -> 그리디하게 접근 ? -> X -> 모든 경우 고려 -> DP
 *
 * max[i][j] : i, j 위치까지 올때의 최대값
 * min[i][j] : i, j 위치까지 올때의 최소값
 *
 * -> 꼭 map 만큼의 공간 필요하지 않다. 2개의 행만 있으면 됨 (rolling array)
 * -> 전체 map도 미리 받아서 관리할 필요가 없다
 */