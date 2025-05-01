import java.io.*;
import java.util.*;

class Pair_1261 implements Comparable<Pair_1261> {
    int x;
    int y;
    int breakCount;

    Pair_1261(int x, int y, int breakCount) {
        this.x = x;
        this.y = y;
        this.breakCount = breakCount;
    }

    @Override
    public int compareTo(Pair_1261 p) {
        return this.breakCount - p.breakCount;      // breakCount 기준 오름차순 정렬
    }
}

class Solution_1261 {

    int row, col;
    int[][] map;
    PriorityQueue<Pair_1261> pq;
    boolean[][] visit;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for (int i=0; i<row; i++) {
            String status = br.readLine();
            for (int j=0; j<status.length(); j++) {
                map[i][j] = Integer.parseInt(String.valueOf(status.charAt(j)));
            }
        }

        if (row == 1 && col == 1) {     // 출발점 == 도착점 예외 케이스
            System.out.println("0");
            br.close();
            return;
        }

        pq = new PriorityQueue<>();
        visit = new boolean[row][col];
        pq.add(new Pair_1261(row - 1, col - 1, 0));
        visit[row - 1][col - 1] = true;

        System.out.println(calc());
        br.close();
    }

    int calc() {
        while (!pq.isEmpty()) {
            Pair_1261 poll = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];

                if (nX == 0 && nY == 0) return poll.breakCount;     // return
                if (nX < 0 || nX >= row || nY < 0 || nY >= col) continue;
                if (!visit[nX][nY]) {
                    if (map[nX][nY] == 0) pq.add(new Pair_1261(nX, nY, poll.breakCount));
                    else pq.add(new Pair_1261(nX, nY, poll.breakCount + 1));
                    visit[nX][nY] = true;
                }
            }
        }

        return -1;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1261 s = new Solution_1261();
        s.solution();
    }
}

/**
 * 도착점에서부터 거꾸로 생각
 * 우선순위 큐 사용해서 각 캔에서 최소로 벽 부수면서 도착점까지 도달하도록 탐색 -> 다익스트라
 * 출발점에 도착하면 종료
 */