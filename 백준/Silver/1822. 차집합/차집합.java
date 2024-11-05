import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aNum = Integer.parseInt(st.nextToken());
        int bNum = Integer.parseInt(st.nextToken());

        int[] a = new int[aNum];
        Set<Integer> b = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aNum; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bNum; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < aNum; i++) {
            if (!b.contains(a[i])) pq.add(a[i]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 싧버 4 1822 차집합
 */
