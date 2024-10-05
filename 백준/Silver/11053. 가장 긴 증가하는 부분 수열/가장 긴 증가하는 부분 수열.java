import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] len = new int[n];
        len[0] = 1;
        for (int i = 1; i < n; i++) {
            int maxLen = 0;         // i-1까지의 가장 긴 증가부분수열 길이
            for (int j = 0; j < i; j++) {
                if (input[j] >= input[i]) continue;
                maxLen = Math.max(maxLen, len[j]);
            }

            len[i] = maxLen + 1;
        }

        Arrays.sort(len);

        System.out.println(len[n - 1]);
        br.close();
    }
}

/**
 * 실버 2 11053 가장 긴 증가하는 부분 수열
 *
 * 10 -> 1
 * 10 20 -> 2
 * 10 20 -> 2
 * 10 20 30 -> 3
 *
 * len[i] : input[i]를 포함하는 가장 긴 증가부분수열 길이
 *
 */
