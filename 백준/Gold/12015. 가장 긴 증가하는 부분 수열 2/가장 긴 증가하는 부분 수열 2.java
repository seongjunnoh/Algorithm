import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n + 1];     // LIS
        Arrays.fill(lis, -1);   // 초기화
        lis[0] = arr[0];
        int end = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > lis[end]) {
                lis[end + 1] = arr[i];      // 맨끝에 추가
                end++;          // 추가했으니 end++
            } else if (arr[i] < lis[end]) {
                int l = 0;
                int r = end;
                while (l <= r) {
                    int mid = (l + r) / 2;

                    if (lis[mid] >= arr[i]) r = mid - 1;
                    else l = mid + 1;
                }

                lis[l] = arr[i];        // 교체 -> lis에서 arr[i]의 lower bound 위치의 값을 arr[i]로 교체
            }
        }

//        for (int i = 0; i <= end; i++) {
//            System.out.println("lis[" + i + "] = " + lis[i]);
//        }

        System.out.println(end + 1);
        br.close();
    }
}

/**
 * 골드 2 12015 가장 긴 증가하는 부분 수열 2
 *
 * LIS 문제 유형 중 이분 탐색에 속하는 유형 (이중 for loop는 시간초과)
 *
 * LIS 수열의 마지막 원소보다 큰 원소일 경우 -> 계속 추가
 * 마지막 원소보다 작은 경우 -> LIS 수열의 원소 중 탐색 원소보다 크거나 같은 최소의 원소와 교체 -> lower bound
 * -> 이러면 LIS의 구성은 바뀌지만, 길이는 보전 & 미래의 입력에 대비할 수 있음
 *
 * cf) 교체할 원소를 이분탐색으로 찾을 때, LIS에는 중복되는 원소가 존재하면 안되므로 탐색 대상의 lower bound를 찾아야 한다
 */
