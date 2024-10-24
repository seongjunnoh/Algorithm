import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] board = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp_odd = new int[n][n];     // dp[i][j] : i 번째 부터 시작, j 번째 가 중간인 배열이 팰린드롬이면 1, 아니면 0
        int[][] dp_even = new int[n][n];    // dp[i][j] : i 번째 부터 시작, j 번째 가 마지막인 배열이 팰린드롬이면 1, 아니면 0

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp_odd[i][j] = -1;      // 초기화
                dp_even[i][j] = -1;
            }
        }

        for (int mid = 0; mid < n; mid++) {
            dp_odd[mid][mid] = 1;

            int count = 1;
            while (true) {
                int left = mid - count;
                int right = mid + count;

                if (left < 0 || right >= n) break;

                if (dp_odd[left + 1][mid] == 1 && board[left] == board[right]) dp_odd[left][mid] = 1;
                else dp_odd[left][mid] = 0;

                count++;
            }
        }

        for (int start = 0; start < n-1; start++) {
            int end = start + 1;

            if (board[start] == board[end]) dp_even[start][end] = 1;
            else dp_even[start][end] = 0;

            int count = 1;
            while (true) {
                int left = start - count;
                int right = end + count;

                if (left < 0 || right >= n) break;

                if (dp_even[left + 1][right - 1] == 1 && board[left] == board[right]) dp_even[left][right] = 1;
                else dp_even[left][right] = 0;

                count++;
            }
        }
//
//        System.out.println("======================");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp_odd[i][j] + " || ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("======================");
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp_even[i][j] + " || ");
//            }
//            System.out.println();
//        }
//        System.out.println("======================");

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            s = s - 1;      // 인덱스 조정
            e = e - 1;

            if ((e - s) % 2 == 0) {       // dp_odd
                int mid = s + (e - s) / 2;
                sb.append(dp_odd[s][mid]).append("\n");
            } else {      // dp_even
                sb.append(dp_even[s][e]).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 10942 팰린드롬?
 *
 * 팰린드롬 : 바로 읽으나, 거꾸로 읽으나 동일한 문자열
 *
 * s~e 길이가 짝수, 홀수 나눠서 생각
 *
 */
