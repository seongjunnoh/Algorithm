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

        Set<Integer> numbers = new HashSet<>();
        int r = 0;
        long count = 0;
        for (int l = 0; l < n; l++) {
            // while 의 조건의 순서를 이렇게 설정하면 r == n 인 경우일 때의 ArrayIndexOutOfBound 에러 막을 수 있음
            while (r < n && !numbers.contains(arr[r])) numbers.add(arr[r++]);

            // numbers 의 size가 l부터 시작한 연속한 수일때의 정답
            count += numbers.size();

            numbers.remove(arr[l]);     // numbers 에서 arr[l] 제거
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 4 13144 List of Unique Numbers
 *
 * n 개 중 연속된 수 몇개 뽑고, 거기에 중복되는 수가 없으면 count++
 * -> 투 포인터로 연속된 수열의 start, end 조절
 *
 */