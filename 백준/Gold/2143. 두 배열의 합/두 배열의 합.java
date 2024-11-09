import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long[][] sumA = new long[n][n];             // sumA[i][j] : a[i]부터 a[j]까지 누적합
        List<Long> listA = new ArrayList<>();       // listA : 누적합들의 list
        for (int s = 0; s < n; s++) {
            for (int e = s; e < n; e++) {
                if (e == 0) sumA[s][0] = a[s];
                else sumA[s][e] = sumA[s][e - 1] + a[e];

                listA.add(sumA[s][e]);
            }
        }

        // listA -> array로 변환 & 오름차순 정렬
        Long[] culSumA = listA.toArray(new Long[0]);
        Arrays.sort(culSumA);
        
        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        long[][] sumB = new long[m][m];             // sumB[i][j] : b[i]부터 b[j]까지 누적합
        List<Long> listB = new ArrayList<>();       // listB : 누적합들의 list
        for (int s = 0; s < m; s++) {
            for (int e = s; e < m; e++) {
                if (e == 0) sumB[s][0] = b[s];
                else sumB[s][e] = sumB[s][e - 1] + b[e];

                listB.add(sumB[s][e]);
            }
        }

        // listB -> array로 변환 & 오름차순 정렬
        Long[] culSumB = listB.toArray(new Long[0]);
        Arrays.sort(culSumB);

        // culSumA의 모든 원소에 대하여, culSumB 에서 이분탐색으로 target 찾기
        long count = 0;
        for (int i = 0; i < culSumA.length; i++) {
            long target = t - culSumA[i];

            int l = 0;
            int r = culSumB.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;

                if (target <= culSumB[mid]) r = mid - 1;
                else l = mid + 1;
            }
            int lowerBound = l;

            l = 0;
            r = culSumB.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;

                if (target < culSumB[mid]) r = mid - 1;
                else l = mid + 1;
            }
            int upperBound = l;

            count += upperBound - lowerBound;
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 3 2143 두 배열의 합
 *
 * a의 부 배열의 합 + b의 부 배열의 합 = t 가 되는 부 배열 쌍의 개수 구하기
 *
 * a에서 가능한 모든 부 배열의 합 구하기 -> n + n-1 + n-2 + ,,, + 1 -> n 제곱
 * b 도 마찬가지로 -> m 제곱
 *
 * 두 부 배열의 모든 가능한 쌍의 개수 고려 -> 시간초과
 *
 * => a의 모든 부 배열의 합 2차원 배열에 저장 & b의 모든 부 배열의 합 2차원 배열에 저장 -> n 제곱
 *    => sumA의 모든 원소 하나씩 고려(n 제곱) * sumB에서 이분탐색으로 찾기(log (m제곱)) -> 백만 * 20 -> ok
 *
 */
