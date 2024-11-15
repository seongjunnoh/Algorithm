import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 예외처리
        if (n == 1) {
            System.out.println("0");
            br.close();
            return;
        }

        int[] isPrime = new int[n + 1];         // 소수이면 -> 1, 아니면 -> -1
        Arrays.fill(isPrime, 1);            // 1로 초기화
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i] == -1) continue;

            int count = 2;
            while (i * count <= n) {
                isPrime[i * count++] = -1;      // i의 배수들 -1로 변경
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] == 1) primes.add(i);         // n 이하의 소수들의 list
        }
        Integer[] arr = primes.toArray(new Integer[0]);

        long count = 0;
        int r = 0;
        long sum = arr[0];
        for (int l = 0; l < arr.length; l++) {
            boolean flag = false;

            while (r < arr.length && sum < n) {
                r++;
                if (r == arr.length) flag = true;
                else sum += arr[r];
            }

            if (flag) break;

            if (sum == n) count++;          // 연속된 소수의 합으로 나타낼 수 있으면 count++
            sum -= arr[l];
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드3 1644 소수의 연속합
 *
 * 주어진 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수 구하기
 * -> 소수를 어떻게 판별??
 * => 에라토스테네스의 체 방식 이용
 *
 * n보다 작은 모든 소수 구하고,
 * 투 포인터 기법으로 연속된 소수의 합의 모든 경우의 수 구하기
 *
 */
