import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static int n;
    static int[] arr, oper;
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    static void dfs(int depth, long sum) {
        if (depth == n) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                oper[i]--;

                if (i == 0) {   // +
                    dfs(depth + 1, sum + arr[depth]);
                } else if (i == 1) {    // -
                    dfs(depth + 1, sum - arr[depth]);
                } else if (i == 2) {    // *
                    dfs(depth + 1, sum * arr[depth]);
                } else {    // /
                    if (sum < 0 && arr[depth] > 0) {
                        long result = (sum * -1) / arr[depth];
                        dfs(depth + 1, result * -1);
                    } else {
                        dfs(depth + 1, sum / arr[depth]);
                    }
                }

                oper[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        oper = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, arr[0]);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(min);
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 1 14888 연산자 끼워넣기
 *
 * n = 11 : 최대 10!/2!*3!*3!*2! = 10 9 8 7 5 < 10만 -> ok
 *
 * 연산자의 배열 -> dfs
 *
 */
