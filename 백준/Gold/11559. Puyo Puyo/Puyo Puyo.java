import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static Queue<Pair> queue = new LinkedList<>();
    static Queue<Pair> temp;
    static int result = 0;
    static boolean[][] visit;
    static int[][] pos = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void dfs() {
        // r, g, b, p, y 에 대해서 모두 bfs -> 연쇄 카운트
        boolean r = play('R');
        boolean g = play('G');
        boolean b = play('B');
        boolean p = play('P');
        boolean y = play('Y');

        // 모든 색깔에 대해서 한번이라도 터질경우 dfs 재귀호출
        // 아닐 경우, return
        if (r || g || b || p || y) {
            result += 1;

            // map 재정비
            Queue<Character> toDown = new LinkedList<>();
            for (int col = 0; col < 6; col++) {
                // col 열에서 아래로 내릴 뿌요가 있는지 확인
                for (int row = 11; row >= 0; row--) {
                    if (map[row][col] != '.') {
                        toDown.add(map[row][col]);
                    }
                }

                // col 열에서 내려야할 뿌요를 내리기
                for (int row = 11; row >= 0; row--) {
                    if (!toDown.isEmpty()) {
                        map[row][col] = toDown.poll();
                    } else {
                        map[row][col] = '.';
                    }
                }
            }
            
            dfs();
        }
    }

    static boolean play(char color) {
        boolean isPuyo = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == color) {
                    boolean bfs = bfs(new Pair(i, j), color);
                    if (bfs) isPuyo = true;
                }
            }
        }

        return isPuyo;
    }

    static boolean bfs(Pair cur, char color) {
        queue.add(cur);
        visit = new boolean[12][6];
        visit[cur.x][cur.y] = true;
        int count = 1;

        // color 색깔의 위치를 temp에 저장
        temp = new LinkedList<>();
        temp.add(cur);

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];
                if (nX < 0 || nX >= 12 || nY < 0 || nY >= 6) continue;
                if (map[nX][nY] == color && !visit[nX][nY]) {
                    count++;
                    visit[nX][nY] = true;
                    queue.add(new Pair(nX, nY));
                    temp.add(new Pair(nX, nY));
                }
            }
        }

        // count >= 4 : color 를 . 으로 변경 && 터졌으므로 true return
        // count < 4 : 터지지 않으므로 false return
        if (count >= 4) {
            int size = temp.size();
            for (int i = 0; i < size; i++) {
                Pair poll = temp.poll();
                map[poll.x][poll.y] = '.';
            }
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs();

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

