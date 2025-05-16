import java.io.*;
import java.util.*;

class Solution_16235 {

    int n;
    Deque<Integer>[][] map;      // map[i][j] : (i,j)에 있는 나무들의 age에 대한 deque
    int[][] cur;        // 현재 양분의 양
    int[][] add;        // (i,j)에 추가되는 양분의 양
    Queue<Integer>[][] die;      // (i,j)에서 죽은 나무들의 age의 list
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new Deque[n + 1][n + 1];
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                map[x][y] = new LinkedList<>();      // 초기화
            }
        }

        cur = new int[n + 1][n + 1];
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                cur[x][y] = 5;      // 초기화
            }
        }
        add = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            map[x][y].addLast(age);     // 입력으로 주어지는 나무는 위치당 1개 -> 정렬할 필요 없다
        }

        die = new LinkedList[n + 1][n + 1];
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                die[x][y] = new LinkedList<>();      // 초기화
            }
        }

        // k년 동안 play
        for (int year = 0; year < k; year++) {
            spring();
            summer();
            fall();
            winter();
        }

        int result = 0;
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                result += map[x][y].size();
            }
        }
        System.out.println(result);
        br.close();
    }

    void spring() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                int size = map[x][y].size();
                for (int idx = 0; idx < size; idx++) {
                    int age = map[x][y].pollFirst();
                    if (age > cur[x][y]) {      // 나무가 양분 먹지 못하는 경우
                        die[x][y].add(age);
                    } else {
                        cur[x][y] -= age;
                        map[x][y].addLast(age + 1);     // 다시 넣어주기
                    }
                }
            }
        }
    }

    void summer() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                while (!die[x][y].isEmpty()) {
                    int poll = die[x][y].poll();
                    cur[x][y] += poll/2;
                }
            }
        }
    }

    void fall() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                int size = map[x][y].size();
                for (int idx = 0; idx < size; idx++) {
                    int age = map[x][y].pollFirst();
                    if (age % 5 == 0) breeding(x, y);
                    map[x][y].addLast(age);     // 다시 넣어주기
                }
            }
        }
    }

    void breeding(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nX = x + pos[i][0];
            int nY = y + pos[i][1];
            if (nX <= 0 || nX > n || nY <= 0 || nY > n) continue;
            map[nX][nY].addFirst(1);        // 앞에 넣어주기 (정렬 순서 유지)
        }
    }

    void winter() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                cur[x][y] += add[x][y];
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_16235 s = new Solution_16235();
        s.solution();
    }
}

/**
 * n*n 크기의 땅, m개의 나무
 *
 * 개선 사항 : 매번 나무를 ArrayList에서 remove 하는 것, 매년마다 나무를 나이순으로 정렬하는것
 * -> 나무를 ArrayList가 아니라 Deque에 관리 (나이 기준 오름차순 정렬된 상태를 유지)
 * -> 현재 나무가 양분을 먹을 수 없으면 뒤의 나무들도 양분 먹을 수 없음
 * -> 매년마다 나이 기준으로 정렬할 필요도 없음
 */
