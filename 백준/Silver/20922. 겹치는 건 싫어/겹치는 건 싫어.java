import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();

        int l = 0;
        int r = 0;
        int max = 0;
        int length = 0;
        while (r < n) {
            if (map.getOrDefault(arr[r], 0) == k) {     // l 이동 & arr[r] 을 map 에 추가
                while (arr[l] != arr[r]) {
                    map.put(arr[l], map.get(arr[l]) - 1);
                    l++;
                    length--;
                }
                map.put(arr[l], map.get(arr[l]) - 1);
                l++;
                length--;

                map.put(arr[r], map.get(arr[r]) + 1);
                length++;
            } else {
                map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
                length++;
            }

            max = Math.max(max, length);

            r++;
        }

        System.out.println(max);
        br.close();
    }
}

/**
 * 실버 1 20922 겹치는 건 싫어
 */
