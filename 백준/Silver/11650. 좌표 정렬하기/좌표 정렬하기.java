import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(x, y);
        }

        Arrays.sort(arr, (p1, p2) -> {
            if (p1.x == p2.x) return p1.y - p2.y;
            return p1.x - p2.x;
        });

        for (int i = 0; i < n; i++) {
            sb.append(arr[i].x + " " + arr[i].y).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 5 11650번 좌표 정렬하기
 */
