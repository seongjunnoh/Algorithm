import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long snowman = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                snowman = arr[i] + arr[j];      // 첫번째 눈사람 크기

                // 두번째 눈사람 크기 구하기
                int l = 0;
                int r = n - 1;
                while (l < r) {
                    if (l == i || l == j) {
                        l++;
                        continue;
                    }
                    if (r == i || r == j) {
                        r--;
                        continue;
                    }

                    if (snowman > arr[l] + arr[r]) {
                        min = Math.min(min, Math.abs(snowman - (arr[l] + arr[r])));
                        l++;
                    }
                    else if (snowman < arr[l] + arr[r]) {
                        min = Math.min(min, Math.abs(snowman - (arr[l] + arr[r])));
                        r--;
                    }
                    else {
                        System.out.println("0");
                        br.close();
                        return;
                    }
                }
            }
        }

        System.out.println(min);
        br.close();
    }
}

/**
 * 골드 3 20366 같이 눈사람 만들래?
 *
 * arr 의 눈덩이 중 4개 골라서 눈사람 2개 만들기 & 이 중 2개 눈사람 키 차이 최솟값 찾기
 * -> O(n의 4제곱) : 시간초과
 *
 * -> 2개 골라서 눈사람 1개 만들어 놓고 (O(n 제곱)) & 투 포인터로 나머지 눈 사람 1개 크기 구하기 (O(n))
 *
 */
