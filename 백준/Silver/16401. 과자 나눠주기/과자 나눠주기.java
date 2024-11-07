import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long[] snacks = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snacks[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(snacks);

        long min = 1;
        long max = snacks[n - 1];
        while (min <= max) {
            long mid = (min + max) / 2;
            long count = 0;
            for (int i = 0; i < n; i++) {
                count += snacks[i] / mid;
            }

            if (count >= m) min = mid + 1;
            else max = mid - 1;
        }

        System.out.println(min - 1);            // min = upperBound
        br.close();
    }
}

/**
 * 실버 2 16401 과자 나눠주기
 *
 * snacks 오름차순 정렬 -> min과 max 의 mid 구해서
 *      => snacks를 mid로 나눈 몫들의 합이 m 보다 크거나 같으면 -> min = mid + 1
 *      => 작으면 -> max = mid - 1
 *
 * -> 이분 탐색 한번 (log n = 20) && for loop (n = 백만) -> 약 2천만 -> ok
 *
 */
