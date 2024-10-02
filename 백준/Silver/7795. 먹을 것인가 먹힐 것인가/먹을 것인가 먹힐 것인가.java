import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Integer[] A = new Integer[n];
            Integer[] B = new Integer[m];

            st = new StringTokenizer(br.readLine());
            for (int a = 0; a < n; a++) {
                A[a] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int b = 0; b < m; b++) {
                B[b] = Integer.parseInt(st.nextToken());
            }

            // A, B 내림차순 정렬
            Arrays.sort(A, Collections.reverseOrder());
            Arrays.sort(B, Collections.reverseOrder());

            // 쌍 개수 계산
            int count = 0;
            for (int j = 0; j < A.length; j++) {
                for (int k = 0; k < B.length; k++) {
                    if (A[j] > B[k]) {
                        count += B.length - k;
                        break;
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 7795 먹을 것인가 먹힐 것인가
 */
