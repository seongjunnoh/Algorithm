import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Pair {
        int x;
        int y;
        Character c;
        Pair(int x, int y, Character c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    static int n;
    static Character[][] board;
    static boolean[][] noSaekVisited;
    static boolean[][] yesSaekVisited;
    static Queue<Pair> noSaekQueue;
    static Queue<Pair> yesSaekQueue;
    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void noBfs(int x, int y) {
        while (!noSaekQueue.isEmpty()) {
            Pair cur = noSaekQueue.poll();
            int nowX = cur.x;
            int nowY = cur.y;
            Character nowC = cur.c;
            for (int i = 0; i < 4; i++) {
                int newX = nowX + pos[i][0];
                int newY = nowY + pos[i][1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                Character newC = board[newX][newY];

                // 색약이 아닌 사람 : 인접 영역의 색깔이 지금 영역과 같고, 인접 영역을 방문하지 않은 상태여야 add
                if (nowC.equals(newC) && !noSaekVisited[newX][newY]) {
                    noSaekVisited[newX][newY] = true;
                    noSaekQueue.add(new Pair(newX, newY, newC));
                }
            }
        }
    }

    static void yesBfs(int x, int y) {
        while (!yesSaekQueue.isEmpty()) {
            Pair cur = yesSaekQueue.poll();
            int nowX = cur.x;
            int nowY = cur.y;
            Character nowC = cur.c;
            for (int i = 0; i < 4; i++) {
                int newX = nowX + pos[i][0];
                int newY = nowY + pos[i][1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                Character newC = board[newX][newY];

                // 색약인 사람 : 인접 영역과 지금 영역의 색깔이 같거나 차이를 느끼지 못하고, 인접 영역을 방문하지 않은 상태여야 add
                if (nowC.equals('R')) {
                    if (!newC.equals('B') && !yesSaekVisited[newX][newY]) {
                        yesSaekVisited[newX][newY] = true;
                        yesSaekQueue.add(new Pair(newX, newY, newC));
                    }
                } else if (nowC.equals('G')) {
                    if (!newC.equals('B') && !yesSaekVisited[newX][newY]) {
                        yesSaekVisited[newX][newY] = true;
                        yesSaekQueue.add(new Pair(newX, newY, newC));
                    }
                } else if (nowC.equals('B')) {
                    if (newC.equals('B') && !yesSaekVisited[newX][newY]) {
                        yesSaekVisited[newX][newY] = true;
                        yesSaekQueue.add(new Pair(newX, newY, newC));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        board = new Character[n][n];
        noSaekVisited = new boolean[n][n];
        yesSaekVisited = new boolean[n][n];
        noSaekQueue = new LinkedList<>();
        yesSaekQueue = new LinkedList<>();
        int noSaek = 0;         // 색약이 아닌 사람의 정답
        int yesSaek = 0;        // 색약인 사람의 정답

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 색약인 사람 , 아닌 사람 모두 (0,0) 부터 (n,n)까지 돌면서 count++
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 색약이 아닌 사람이 해당 구역을 방문하지 않은 경우
                if (!noSaekVisited[i][j]) {
                    noSaekVisited[i][j] = true;
                    noSaekQueue.add(new Pair(i, j, board[i][j]));
                    noBfs(i, j);
                    noSaek++;
                }
                // 색약인 사람이 해당 구역을 방문하지 않은 경우
                if (!yesSaekVisited[i][j]) {
                    yesSaekVisited[i][j] = true;
                    yesSaekQueue.add(new Pair(i, j, board[i][j]));
                    yesBfs(i, j);
                    yesSaek++;
                }
            }
        }

        bw.write(noSaek + " " + yesSaek);
        bw.flush();
        bw.close();
        br.close();
    }
}
