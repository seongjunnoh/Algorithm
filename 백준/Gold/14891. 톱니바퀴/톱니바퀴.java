import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Tobni {
        int num;
        int to;

        Tobni(int num, int to) {
            this.num = num;
            this.to = to;
        }
    }

    static int[][] tobnis = new int[5][8];
    static boolean[] visit;
    static Queue<Tobni> queue;
    static int result = 0;

    static void play(int num, int to) {
        // 해당 톱니를 돌림으로써 같이 돌아가는 모든 톱니 확인
        visit = new boolean[5];
        queue = new LinkedList<>();
        check(num, to);

        // check 결과 반영
        while (!queue.isEmpty()) {
            Tobni poll = queue.poll();          // 회전시켜야 하는 톱니바퀴

            int temp = -1;
            if (poll.to == 1) {
                // 시계방향 회전
                temp = tobnis[poll.num][7];
                for (int i = 7; i > 0; i--) {
                    tobnis[poll.num][i] = tobnis[poll.num][i-1];
                }
                tobnis[poll.num][0] = temp;
            } else {
                // 반시계 방향 회전
                temp = tobnis[poll.num][0];
                for (int i = 0; i < 7; i++) {
                    tobnis[poll.num][i] = tobnis[poll.num][i + 1];
                }
                tobnis[poll.num][7] = temp;
            }
        }
        
        result = 0;
        // result 계산 -> 0이면 0점, 1이면 2의 i 제곱 점
        for (int i = 1; i <= 4; i++) {
            if (tobnis[i][0] == 1) result += (int) Math.pow(2, i-1);
        }
    }

    static void check(int num, int to) {
        visit[num] = true;
        queue.add(new Tobni(num, to));          // 회전시켜야 하는 톱니바퀴 queue에 add

        if (num == 1) {
            if (tobnis[num][2] == tobnis[num + 1][6]) return;

            // 같지 않다면, num + 1 도 회전시켜야함 (만약 num+1을 이미 회전시킨 경우는 return)
            if (!visit[num+1]) {
                if (to == 1) check(num + 1, -1);
                else check(num + 1, 1);
            }
        } else if (num == 4) {
            if (tobnis[num][6] == tobnis[num - 1][2]) return;

            // 같지 않다면, num - 1 도 회전시켜야함 (만약 num-1을 이미 회전시킨 경우는 return)
            if (!visit[num-1]) {
                if (to == 1) check(num - 1, -1);
                else check(num - 1, 1);
            }
        } else {
            if (tobnis[num][2] == tobnis[num+1][6] && tobnis[num][6] == tobnis[num-1][2]) return;

            // 같지 않은 경우, num + 1 또는 num - 1 회전시켜야함 (만약 이미 회전시킨 경우는 그냥 return)
            if (tobnis[num][2] != tobnis[num+1][6]) {
                if (!visit[num+1]) {
                    if (to == 1) check(num + 1, -1);
                    else check(num + 1, 1);
                }
            }
            if (tobnis[num][6] != tobnis[num - 1][2]) {
                if (!visit[num-1]) {
                    if (to == 1) check(num - 1, -1);
                    else check(num - 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                tobnis[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());         // 돌릴 톱니 번호
            int to = Integer.parseInt(st.nextToken());          // 돌릴 방향

            play(num, to);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 5 14891번 톱니바퀴
 *
 * 한번의 play 시 해당 톱니가 돌아감으로써 돌아가게 되는 다른 톱니들을 어떻게 모두 체크??
 *
 */
