import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            temp[i] = arr[i];
        }

        Arrays.sort(temp);

        Map<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        int before = 0;
        for (int i = 0; i < n; i++) {
            if (before == temp[i]) continue;

            map.put(temp[i], rank++);
            before = temp[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 2 18870 좌표 압축
 *
 */
