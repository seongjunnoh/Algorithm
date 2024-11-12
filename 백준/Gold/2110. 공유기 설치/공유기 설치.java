import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long l = 1;
        long r = arr[n - 1] - arr[0];
        while (l <= r) {
            long mid = (l + r) / 2;
            long before = arr[0];       // 직전 공유기 설치 집의 위치

            int count = 1;      // arr[0]에는 공유기 설치
            for (int i = 0; i < n; i++) {
                if (before + mid <= arr[i]) {
                    count++;
                    before = arr[i];
                }
            }

            if (count < c) r = mid - 1;
            else l = mid + 1;
        }

        System.out.println(l - 1);      // upperBound
        br.close();
    }
}

/**
 * 골드 4 2110 공유기 설치
 *
 * n개의 집 중 c개를 선택, 선택한 공유기들 중 가장 가까운 거리의 최댓값 구하기
 * -> 공유기 사이의 거리를 기준으로 탐색
 *  => 공유기 사이의 거리가 x 일때, n개의 집들 중 m개에 공유기 설치 가능
 *  => m < c : x 줄여야 함
 *     m > c : x 늘여야 함
 *  => m = c 가 되는 x의 upperBound 를 찾자
 *
 */
