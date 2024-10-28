import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int maxPrice = 0;
            long result = 0;            // result : long type
            // 오른쪽 끝에서 왼쪽으로 탐색
            for (int j = n - 1; j >= 0; j--) {
                if (arr[j] > maxPrice) {
                    maxPrice = arr[j];
                } else {
                    result += (maxPrice - arr[j]);
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 2 11501 주식
 *
 * 미래의 주가가 높을 경우, 현재 구매해서 미래에 파는게 이득
 * -> sell point 를 파악하기 위해서 오른쪽부터 왼쪽으로 탐색한다고 생각하는게 좋음
 *  => sell point = 오른쪽 -> 왼쪽 으로 탐색할 때, 오른쪽 끝부터 현재까지 중 주가가 최대인 경우
 *
 */
