import java.io.*;
import java.util.*;

class Solution_2212 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);           // 센서 위치 오름차순 정렬

        int[] dist = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            dist[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(dist);          // 센서 사이의 거리 오름차순 정렬

        int sum = 0;
        for (int i = 0; i < n - 1 - (k - 1); i++) {
            sum += dist[i];
        }
        System.out.println(sum);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2212 s = new Solution_2212();
        s.solution();
    }
}

/**
 * 모든 센서 사이의 거리를 구하고 -> 이 중 가장 큰 k-1 개를 제외한 거리의 합이 정답이다
 *
 */