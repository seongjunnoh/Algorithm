import java.io.*;
import java.util.*;

public class Main {

    static char[][] map;
    static int n, m, k;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    static Map<String, Integer> counts;

    static void dfs(int x, int y, StringBuilder before, int depth) {
        if (depth == 5) {       // 최대 문자열 길이 = 5
            return;
        }

        StringBuilder current = new StringBuilder(before);
        current.append(map[x][y]);      // 현재 위치의 문자를 current 에 추가
        String s = current.toString();
        counts.put(s, counts.getOrDefault(s, 0) + 1);

        for (int i = 0; i < 8; i++) {
            int nX = (x + dir[i][0] + n) % n;
            int nY = (y + dir[i][1] + m) % m;

            dfs(nX, nY, current, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        // 가능한 모든 문자열 생성
        counts = new HashMap<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                dfs(x, y, new StringBuilder(), 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            String input = br.readLine();
            sb.append(counts.getOrDefault(input, 0)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 20166 문자열 지옥에 빠진 호석
 *
 * 시간초과
 * -> dfs 최대 8의 5제곱 & for loop 10만번 => 32000 * 10만번 => 시간초과
 *
 * 시간 어떻게 줄이지 ??
 * => 미리 가능한 모든 문자열을 모두 생성해서 map 에 count 정보와 함께 저장
 *    & 신이 원하는 문자열에 해당하는 count 값을 그대로 출력
 *
 * => 시간 복잡도 = 8의 5제곱 * 100 (counts 구성) + counts map을 1000번 get (counts 의 get 연산) -> ok
 *
 */
