import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int num;
        int abs;

        Pair (int num, int abs) {
            this.num = num;
            this.abs = abs;
        }

        // 절댓값 기준으로 오름차순 정렬, 절댓값 같으면 num 기준 오름차순 정렬
        @Override
        public int compareTo(Pair p) {
            if (abs == p.abs) return num - p.num;
            return abs - p.abs;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) pq.add(new Pair(x, Math.abs(x)));
            else {
                if (pq.isEmpty()) sb.append("0").append("\n");
                else sb.append(pq.poll().num).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 1 11286번 절댓값 힙
 *
 */
