import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int num;                // 현재 노드 번호
        List<Integer> nears;    // 인접 노드 번호 list

        Node(int num, List<Integer> nears) {
            this.num = num;
            this.nears = nears;
        }
    }

    static boolean[] visit;
    static Node[] graph;
    static int[] result;

    static void trip(int num) {
        Node cur = graph[num];
        int size = cur.nears.size();
        for (int i = 0; i < size; i++) {
            int next = cur.nears.get(i);
            if (!visit[next]) {
                visit[next] = true;
                result[next] = cur.num;         // next의 부모 노드 번호 = cur.num
                trip(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        graph = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new Node(i, new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph[n1].nears.add(n2);
            graph[n2].nears.add(n1);
        }

        visit = new boolean[n + 1];
        result = new int[n + 1];
        trip(1);

        for (int i = 2; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
