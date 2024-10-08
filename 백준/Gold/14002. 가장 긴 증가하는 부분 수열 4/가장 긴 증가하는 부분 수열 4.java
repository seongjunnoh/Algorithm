import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int[] indexes = new int[n];
        Arrays.fill(dp, 1);         // dp 배열 초기화
        Arrays.fill(indexes, -1);   // -1로 indexes 배열 초기화

        int maxCount = dp[0];
        int start = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {     // dp[i] 가 update 되는 조건
                    dp[i] = dp[j] + 1;
                    indexes[i] = j;
                }
            }

            if (maxCount < dp[i]) {
                maxCount = dp[i];
                start = i;          // 가장 긴 증가하는 수열의 제일 끝 값의 인덱스
            }
        }

        // 가장 긴 증가하는 부분 수열 구하기
        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr[start]);
        int idx = indexes[start];
        while (idx != -1) {
            result.add(arr[idx]);
            idx = indexes[idx];
        }

        Collections.sort(result);           // 오름차순 정렬

        System.out.println(maxCount);
        for (int num : result) {
            System.out.print(num + " ");
        }

        br.close();
    }
}

/**
 * 골드 4 14002 가장 긴 증가하는 부분 수열 4
 *
 * dp[i] : arr[i]를 마지막으로 하는 가장 긴 증가하는 부분 수열의 길이
 * -> 정답 = dp 배열 중 최대값
 *
 * 이때의 부분 수열은 어떻게 출력?
 * -> memo[i][0] ~ memo[i][n-1] : arr[i]를 마지막으로 하는 가장 긴 증가하는 부분 수열의 각 요소가 저장
 * => memo update 할때마다 for loop 돌아야함 -> 시간 초과 예상
 * => 이전 arr 요소의 인덱스를 저장해두자
 *
 * indexes[i] : arr[i]를 마지막으로 하는 가장 긴 증가하는 부분 수열에서 arr[i] 직전 값의 인덱스 값
 *
 */

