import java.io.*;
import java.util.*;

class Solution_2252 {

    int[] before;           // before[i] : i 앞에 서야하는 학생 수
    List<Integer>[] graph;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        before = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            before[b]++;            // b 앞에 서야하는 학생 수 + 1
            graph[a].add(b);        // a 뒤에 b가 서야한다
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (before[i] == 0) q.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int poll = q.poll();
            sb.append(poll).append(" ");

            for (int near : graph[poll]) {
                before[near]--;
                if (before[near] == 0) q.add(near);
            }
        }

        System.out.println(sb);
        br.close();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_2252 s = new Solution_2252();
        s.solution();
    }
}

/**
 * 골드 3 2252번 줄 세우기
 *
 * 
 */