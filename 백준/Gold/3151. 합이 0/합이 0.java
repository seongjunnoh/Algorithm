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

        Arrays.sort(arr);

        long count = 0;         // 10000개 중 3개 고르는 것 -> long type
        for (int i = 0; i < n; i++) {
            int fix = arr[i];
            if (fix > 0) break;

            int l = i + 1;
            int r = n - 1;

            while (l < r) {
                // 동일한 arr[l], arr[r] 의 개수
                int cntL = 1;
                int cntR = 1;

                int current = fix + arr[l] + arr[r];
                if (current == 0) {
                    if (arr[l] == arr[r]) {
                        count += (r - l + 1) * (r - l) / 2;
                        break;
                    }

                    while (l + 1 < r && arr[l] == arr[l + 1]) {
                        cntL++;
                        l++;
                    }

                    while (l < r - 1 && arr[r] == arr[r - 1]) {
                        cntR++;
                        r--;
                    }
                    count += cntL * cntR;
                }

                if (current > 0) r--;
                else l++;
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 4 3151 합이 0
 *
 * arr 중 3개의 합이 0이 되는 세트를 몇개 만들 수 있는가?
 * -> a + b + c = 0
 *      => 첫번째 수 고정 (n) && 2, 3 번째 수 투 포인터로 찾기 (n) => n 제곱
 *
 */
