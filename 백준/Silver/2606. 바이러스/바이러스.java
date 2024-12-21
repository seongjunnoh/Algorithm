import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        List<Integer>[] list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        boolean[] visit = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;
        int count = 0;
        while (!q.isEmpty()) {
            int poll = q.poll();
            for (int near : list[poll]) {
                if (!visit[near]) {
                    visit[near] = true;
                    q.add(near);
                    count++;
                }
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 실버 3 2606번 바이러스
 *
 *
 */
