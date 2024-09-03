import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class JihonAndFire {
    int x;
    int y;

    JihonAndFire(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        Character[][] miro = new Character[r][c];       // 입력

        Queue<JihonAndFire> jihons = new LinkedList<>();        // 지훈
        Queue<JihonAndFire> fires = new LinkedList<>();         // 불

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                miro[i][j] = line.charAt(j);
                if (miro[i][j] == 'J') {
                    // 지훈 출발 위치
                    jihons.add(new JihonAndFire(i, j));
                } else if (miro[i][j] == 'F') {
                    // 불 출발 위치 -> 여러개 일 수 있음
                    fires.add(new JihonAndFire(i, j));
                }
            }
        }

        int time = 0;           // 불, 지훈이 전파되는 시간
        while (true) {

            // 불 먼저 전파 (현재 fires에 있는 불에 대해서만 주위 영역으로 불 전파 처리)
            int firesSize = fires.size();
            for (int i = 0; i < firesSize; i++) {
                JihonAndFire curF = fires.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = curF.x + pos[j][0];
                    int newY = curF.y + pos[j][1];
                    if (newX < 0 || newX >= r || newY < 0 || newY >= c) continue;
                    if (miro[newX][newY] == 'J' || miro[newX][newY] == '.') {
                        // 불 전파 가능
                        miro[newX][newY] = 'F';
                        fires.add(new JihonAndFire(newX, newY));
                    }
                }
            }

            // 지훈 전파 (현재 jihons에 있는 지훈에 대해서만 주위 영역으로 지훈 전파 처리)
            int jihonsSize = jihons.size();
            for (int i = 0; i < jihonsSize; i++) {
                JihonAndFire curJ = jihons.poll();
                for (int j = 0; j < 4; j++) {
                    int newX = curJ.x + pos[j][0];
                    int newY = curJ.y + pos[j][1];
                    if (newX < 0 || newX >= r || newY < 0 || newY >= c) {
                        // 지훈이 다음 시간에 탈출 가능한 경우
                        bw.write(String.valueOf(time + 1));
                        bw.flush();
                        br.close();
                        bw.close();
                        return;
                    }
                    if (miro[newX][newY] == '.') {
                        // 지훈 전파 가능
                        miro[newX][newY] = 'J';
                        jihons.add(new JihonAndFire(newX, newY));
                    }
                }
            }

            // 무한루프 탈출 조건 -> jihons가 빌 경우 (더 이상 지훈이 전파될 곳이 없음 == IMPOSSIBLE)
            if (jihons.isEmpty()) {
                break;
            }

            time++;
        }

        bw.write("IMPOSSIBLE");
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 3 4179번 불!
 *
 * 지훈, 불 모두 bfs 로 전파 되는 시간 기록
 * -> 불은 지훈의 위치로 이동 가능 BUT 지훈은 불의 위치로 이동 X
 *      => 불 bfs 먼저 , 지훈 bfs 나중
 *      => 대신 지훈은 불이 전파된 곳을 움직이면 안됨
 *         따라서 현재 시점에 fires에 있는 불이 모두 전파된 후, jihons에 있는 지훈이 모두 전파하는 과정을 계속 반복
 *         지훈이 miro 밖으로 탈출 가능한 경우는 탈출 성공
 *         계속 루프를 돌다가 jihons가 빌 경우는 IMPOSSIBLE
 */