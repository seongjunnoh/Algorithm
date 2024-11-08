import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(trees);

        long min = 1;
        long max = trees[n - 1];
        while (min <= max) {
            long mid = (min + max) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.max(trees[i] - mid, 0);
            }

            if (sum < m) max = mid - 1;
            else min = mid + 1;
        }

        System.out.println(min - 1);        // min : upperBound
        br.close();
    }
}

/**
 * 실버 2 2805 나무 자르기
 *
 * 나무 높이순으로 정렬
 * -> (1, 가장 큰 나무 높이) 로 시작해서 이분탐색
 * -> mid 구해서 for loop 돌면서 잘린 나무 길이의 합 구하기 (n)
 *    & 잘린 나무 길이의 합이 m 인 최대의 mid (== upperBound) 구하기 (log n)
 *
 */
