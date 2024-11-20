import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        int[][] arr = new int[n][2];

        int max = 0;            // r의 한계치
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            max = Math.max(max, arr[i][1]);
        }

        int l = 0;
        int r = 0;
        long sum = 0;
        boolean flag = false;

        while (r <= max) {
            // 각 선들마다 l, r 사이에 속하는 길이를 sum에 더하기
            sum = 0;
            for (int i = 0; i < n; i++) {
                int left = Math.max(l, arr[i][0]);
                int right = Math.min(r, arr[i][1]);

                if (right - left < 0) continue;

                sum += right - left;
            }

            if (sum < k) r++;
            else if (sum > k) l++;
            else {
                flag = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (!flag) sb.append(0).append(" ").append(0);
        else sb.append(l).append(" ").append(r);

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드2 2283 구간 자르기
 *
 * 투 포인터로 구간의 양 끝 관리
 * -> l, r 을 모두 0부터 시작
 *    & 범위 내에 들어오는 선들의 합이 k보다 작으면 r을 오른쪽으로 이동
 *    & k보다 크면 l을 오른쪽으로 이동
 *    => 이렇게 찾은 l, r 이 문제의 정답
 *
 * -> 시간복잡도 : l, r 이동 (2n = 2000) * 각 l, r 마다 범위내에 들어오는 선들의 합 계산 (n = 1000) -> ok
 */
