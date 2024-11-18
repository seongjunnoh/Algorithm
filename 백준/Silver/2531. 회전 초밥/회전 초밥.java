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

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sushiCount = new int[d + 1];      // 초밥 종류별 개수
        int uniqueCount = 0;                    // 현재 서로 다른 초밥 종류의 수
        int maxCount = 0;

        // 초기 k개의 초밥을 윈도우에 추가
        for (int i = 0; i < k; i++) {
            if (sushiCount[arr[i]]++ == 0) {    // 새로운 종류의 초밥
                uniqueCount++;
            }
        }

        // 슬라이딩 윈도우
        for (int i = 0; i < n; i++) {
            // 쿠폰 초밥 포함 여부 확인
            int currentMax = uniqueCount + (sushiCount[c] == 0 ? 1 : 0);
            maxCount = Math.max(maxCount, currentMax);

            // 윈도우의 왼쪽 초밥 제거
            if (--sushiCount[arr[i]] == 0) {
                uniqueCount--;
            }

            // 윈도우의 오른쪽 초밥 추가
            int next = arr[(i + k) % n]; // 회전 초밥 처리
            if (sushiCount[next]++ == 0) {
                uniqueCount++;
            }
        }

        System.out.println(maxCount);
        br.close();
    }
}
