import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static int[] saved;         // 메모이제이션
    static int[] to;            // 경로 저장, to[i] == i번째 수가 1로 가는 다음 경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        saved = new int[n + 1];
        to = new int[n + 1];
        saved[1] = 0;

        for (int i = 2; i <= n; i++) {
            saved[i] = saved[i - 1] + 1;
            to[i] = i - 1;

            if (i % 3 == 0 && saved[i] > saved[i / 3] + 1) {
                saved[i] = saved[i / 3] + 1;
                to[i] = i / 3;
            }
            if (i % 2 == 0 && saved[i] > saved[i / 2] + 1) {
                saved[i] = saved[i / 2] + 1;
                to[i] = i / 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = n;
        sb.append(n).append(" ");
        while (true) {
            if (i == 1) break;

            sb.append(to[i]).append(" ");
            i = to[i];
        }

        System.out.println(saved[n]);
        System.out.println(sb);

        br.close();
    }
}

/**
 * 실버 1 12852 1로 만들기 2
 *
 * 1로 만드는 최소방법의 과정을 어떻게 출력??
 * -> bottom up 방식으로 saved[], to[] 배열 구성해가기
 *
 *
 */
