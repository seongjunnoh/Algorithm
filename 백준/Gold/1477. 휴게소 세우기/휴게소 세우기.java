import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 2];
        arr[0] = 0;         // 고속도로 시작점
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n + 1] = l;         // 고속도로 끝점

        Arrays.sort(arr);
        int maxDistance = 0;
        for (int i = 1; i < arr.length; i++) {
            maxDistance = Math.max(maxDistance, arr[i] - arr[i - 1]);
        }
        int min = 1;
        int max = maxDistance;

        while (min <= max) {
            int mid = (min + max) / 2;

            int count = 0;
            int before = arr[0];
            int idx = 0;
            while (true) {
                if (arr[idx] == l) break;

                if (before + mid < arr[idx + 1]) {
                    before += mid;
                    count++;        // 휴게소 추가
                } else {
                    before = arr[idx + 1];
                    idx++;
                }
            }

            if (count <= m) max = mid - 1;
            else min = mid + 1;
        }

        System.out.println(min);        // lowerBound
        br.close();
    }
}

/**
 * 골드4 1477 휴게소 세우기
 *
 * 휴게소간 거리 중 최대값 = x
 * -> x일때 휴게소 추가 & 추가한 휴게소의 개수가 = m이 되는 x의 lowerBound를 이분탐색으로 찾기
 *
 */
