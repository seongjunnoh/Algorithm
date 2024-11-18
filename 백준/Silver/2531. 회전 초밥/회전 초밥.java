import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + k - 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = n; i < n + k - 1; i++) {
            arr[i] = arr[i - n];
        }

        Queue<Integer> queue = new LinkedList<>();          // l 부터 r-1 까지의 초밥
        queue.add(arr[0]);
        int r = 1;
        int l = 0;
        int max = 1;
        while (r < n + k - 1) {             // 회전 초밥이므로 l=n-1, r=l+k 인 것까지 생각해야 함
            while  (r - l < k) {
                queue.add(arr[r++]);        // queue에 초밥 add
            }

            // queue -> set 으로 변환
            Set<Integer> set = new HashSet<>(queue);

            if (set.contains(c)) {
                max = Math.max(max, set.size());
            } else {
                max = Math.max(max, set.size() + 1);
            }

            // queue에서 arr[l] 뺴기
            queue.poll();
            l++;
        }

        System.out.println(max);
        br.close();
    }
}

/**
 * 실버 1 2531번 회전 초밥
 *
 * arr의 start, end를 투 포인터로 관리 (end = start + k - 1)
 * -> start 부터 end 까지 중, 서로 다른 초밥 개수 구히가 & c가 포함되어 있지 않으면 + 1
 *
 */
