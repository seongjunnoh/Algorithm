import java.io.*;
import java.util.*;

class Pair_17822 {
    int x;
    int y;

    Pair_17822(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution_17822 {

    int n, m;
    int[][] map;        // 번호가 row인 원판
    boolean[][] visit;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m];

        int t = Integer.parseInt(st.nextToken());
        for (int row = 1; row <= n; row++) {
            int[] arr = map[row];
            st = new StringTokenizer(br.readLine());
            for (int idx = 0; idx < m; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int multi = 1;
            while (true) {
                if (x * multi > n) break;

                rotate(x * multi, d, k);        // 원판 하나 회전
                multi++;
            }

            process();      // 모든 원판 회전시킨 후, 후처리
        }

        // t번 반복 후, 원판에 적힌 수의 합 출력
        int sum = 0;
        for (int row = 1; row <= n; row++) {
            for (int idx = 0; idx < m; idx++) {
                sum += map[row][idx];
            }
        }
        System.out.println(sum);
        br.close();
    }

    void rotate(int row, int d, int k) {
        int[] arr = map[row];
        int[] tmp = arr.clone();

        for (int i = 0; i < m; i++) {
            int ni;
            if (d == 0) {       // 시계방향
                ni = (i + k) % m;
            } else {        // 반시계방향
                ni = (i - k % m + m) % m;
            }
            arr[ni] = tmp[i];
        }
    }

    void process() {
        visit = new boolean[n + 1][m];
        boolean flag = false;

        for (int row = 1; row <= n; row++) {
            for (int idx = 0; idx < m; idx++) {
                if (!visit[row][idx] && map[row][idx] > 0) {
                    flag |= bfs(row, idx);
                }
            }
        }

        if (!flag) {        // 모든 원판에서 인접한 수가 없는 경우
            float sum = 0;
            float count = 0;

            for (int row = 1; row <= n; row++) {
                for (int idx = 0; idx < m; idx++) {
                    if (map[row][idx] > 0) {
                        sum += map[row][idx];
                        count++;
                    }
                }
            }

            float avg = sum / count;      // 평균 (실수)

            for (int row = 1; row <= n; row++) {
                for (int idx = 0; idx < m; idx++) {
                    if (map[row][idx] > 0) {
                        if (map[row][idx] > avg) map[row][idx]--;
                        else if (map[row][idx] < avg) map[row][idx]++;
                    }
                }
            }
        }
    }

    boolean bfs(int x, int y) {
        Queue<Pair_17822> q = new LinkedList<>();
        Stack<Pair_17822> stack = new Stack<>();

        visit[x][y] = true;
        q.add(new Pair_17822(x, y));
        stack.push(new Pair_17822(x, y));

        while (!q.isEmpty()) {
            Pair_17822 poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX == 0 || nX == n + 1) continue;
                if (nY == -1) nY = m - 1;
                else if (nY == m) nY = 0;

                if (!visit[nX][nY] && map[nX][nY] == map[x][y]) {
                    visit[nX][nY] = true;
                    q.add(new Pair_17822(nX, nY));
                    stack.push(new Pair_17822(nX, nY));
                }
            }
        }

        if (stack.size() == 1) return false;        // (x, y) 위치에서는 인접한 숫자 없음

        // 인접한 수 지우기
        while (!stack.isEmpty()) {
            Pair_17822 pop = stack.pop();
            map[pop.x][pop.y] = 0;      // 숫자 지우기
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_17822 s = new Solution_17822();
        s.solution();
    }
}

/**
 * 하나의 원판이 가지는 숫자들을 어떻게 관리?
 * 1. 배열로 관리
 * -> 한번 회전 : 50, 인접 수 찾기 : 250, 이걸 최대 50까지 -> 총 6,250,000
 * -> x의 배수 원판만큼 회전시킨다고 해도 시간초과는 발생하지 않을듯?
 */