import java.io.*;
import java.util.*;

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

        int count = 0;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;

            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }
                if (r == i) {
                    r--;
                    continue;
                }

                long sum = arr[l] + arr[r];

                if (sum < arr[i]) l++;
                else if (sum > arr[i]) r--;
                else {      // arr[i] 는 좋은 수임
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 4 1253 좋다
 *
 * n개의 수 중, 다른 수 2개의 합으로 나타낼 수 있는 좋은 수의 개수 구하기
 * 2개의 수를 골라 합 구하기 (4000000) & 이 합과 같은 수가 존재하는지 찾기 (2000) -> 8,000,000,000 => 시간초과
 * -> 각각의 수에 대해서, 투 포인터로 어떤 두 수의 합으로 해당 수를 만들 수 있는지 확인 (n 제곱 이므로 시간 내에 들어옴)
 *
 */
