import java.io.*;
import java.util.*;

class Pair_17135 {
    int x;
    int y;
    int d;

    Pair_17135(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair_17135)) return false;
        Pair_17135 other = (Pair_17135) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}

class Solution_17135 {

    int n, m, d;
    int[][] map, curMap;
    int[][] pos = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    int totalEnemy = 0, remainEnemy = 0, attackEnemy = 0;
    boolean[][] visit;
    Set<Pair_17135> targets;
    int max = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m];        // n 행에는 궁수가 위치

        for (int i = 0; i < n; i++) {       // 적 위치 초기 세팅
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) totalEnemy++;       // total 적 수 count
            }
        }

        // 궁수 위치 정하기 -> 백트래킹으로 모든 경우의 수 커버
        back(0, 0);

        System.out.println(max);
        br.close();
    }

    void back(int start, int count) {
        if (count == 3) {       // 궁수 세팅 완료
            play();     // 게임 시작
            max = Math.max(max, attackEnemy);       // 공격한 적 수의 최댓값
            return;
        }

        for (int col = start; col < m; col++) {
            map[n][col] = -1;       // 궁수 위치시키기
            back(col + 1, count + 1);
            map[n][col] = 0;        // 다시 원복
        }
    }

    void play() {
        curMap = new int[n + 1][m];
        for (int i = 0; i <= n; i++) {
            curMap[i] = map[i].clone();     // 배열 복사
        }
        attackEnemy = 0;        // 공격한 적 수
        remainEnemy = totalEnemy;

        while (remainEnemy > 0) {       // 적이 없어질때까지 반복
            targets = new HashSet<>();      // 현재 턴에서 궁수들의 공격대상이 된 적 위치

            for (int col = 0; col < m; col++) {
                if (curMap[n][col] == -1) {
                    findEnemy(n, col);      // 왼쪽 궁수부터 적 탐색 시작
                }
            }

            // 적 제거
            for (Pair_17135 target : targets) {
                curMap[target.x][target.y] = 0;     // 적 제거
                remainEnemy--;      // 남은 적 수 차감
                attackEnemy++;      // 공격한 적 수 증가
            }

            // 궁수 공격 이후, 적 이동
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (i == n - 1) {
                        if (curMap[i][j] == 1) {
                            curMap[i][j] = 0;
                            remainEnemy--;
                        }
                    } else {
                        if (curMap[i][j] == 1) {
                            curMap[i + 1][j] = 1;
                            curMap[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    void findEnemy(int x, int y) {
        visit = new boolean[n][m];      // visit 배열 공유하면 X
        Queue<Pair_17135> q = new LinkedList<>();
        q.add(new Pair_17135(x, y, 0));

        while (!q.isEmpty()) {
            Pair_17135 poll = q.poll();

            if (poll.d == d) continue;      // 현재 상태에서 더이상 탐색할 필요X

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];
                if (nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
                if (!visit[nX][nY]) {
                    visit[nX][nY] = true;
                    if (curMap[nX][nY] == 1) {      // 궁수가 공격할 수 있는 적이 있는 경우 -> 가장 왼쪽의 적
                        targets.add(new Pair_17135(nX, nY, poll.d + 1));
                        return;
                    }

                    q.add(new Pair_17135(nX, nY, poll.d + 1));      // 아닌 경우 계속 탐색
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_17135 s = new Solution_17135();
        s.solution();
    }
}

/**
 * 최대 15개의 칸 중 3개를 선택하는 모든 경우 -> 15 * 15 * 5 / 2 = 약 500
 * 모든 쌍에 대해서 계산해도 시간 초과 X
 *
 * 모든 궁수가 동시에 공격 & 한 명의 적은 여러명의 궁수의 공격대상으로 설정될 수 있으므로
 * 모든 궁수가 적을 찾기전에 미리 적을 제거하면 안됨
 */
