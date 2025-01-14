import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_32029 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] t = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;        // 정답
        Arrays.sort(t);

        for (int x = 0; x < a; x++) {
            int sleep = x * b;          // 자는 시간

            for (int sleepIdx = 0; sleepIdx < n; sleepIdx++) {
                int curT = 0;
                int count = 0;
                int newA = a;

                for (int i = 0; i < n; i++) {          // 자고 나서 i번째 과제부터 수행
                    if (i == sleepIdx) {
                        curT += sleep;
                        newA -= x;          // 자고 난 이후에 a update
                    }

                    if (curT + newA <= t[i]) {
                        curT += newA;
                        count++;
                    }
                }

                max = Math.max(max, count);
            }
        }

        System.out.println(max);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_32029 s = new Solution_32029();
        s.solution();
    }
}

/**
 * 골드 3 32029번 지금 자면 꿈을 꾸지만
 *
 * 기한 내에 완료하는 과제 수의 최대값 구하기, 잠은 0,1번 잘 수 있음
 * 모든 과제를 수행하는데 걸리는 시간 동일 -> 마감 기한이 빠른 과제부터 할 수 있으면 하는게 이득
 * & 잠을 잠으로써 과제하는데 걸리는 시간 줄일 수 있음
 *
 */