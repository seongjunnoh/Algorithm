import java.io.*;
import java.util.*;

class Solution_21276 {

    String[] people;
    HashMap<String, Integer> before = new HashMap<>();
    HashMap<String, List<String>> graph = new HashMap<>();
    TreeMap<String, PriorityQueue<String>> tree = new TreeMap<>();
    StringBuilder sb = new StringBuilder();
    Queue<String> q = new LinkedList<>();

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        people = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = st.nextToken();
        }

        int m = Integer.parseInt(br.readLine());
        for (String name : people) {
            before.put(name, 0);            // key : 사람이름, val : 해당 사람의 조상 수
            graph.put(name, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();
            String y = st.nextToken();
            before.put(x, before.get(x) + 1);
            graph.get(y).add(x);
        }

        findRoot();
        for (String name : people) {
            tree.put(name, new PriorityQueue<>());
        }
        makeTree();

        for (String key : tree.keySet()) {
            sb.append(key).append(" ");
            PriorityQueue<String> val = tree.get(key);
            sb.append(val.size()).append(" ");
            while (!val.isEmpty()) {
                sb.append(val.poll()).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    void makeTree() {
        while (!q.isEmpty()) {
            String poll = q.poll();
            for (String next : graph.get(poll)) {
                before.put(next, before.get(next) - 1);
                if (before.get(next) == 0) {
                    q.add(next);
                    tree.get(poll).add(next);           // next가 poll의 직속 자식
                }
            }
        }
    }

    void findRoot() {
        int count = 0;
        PriorityQueue<String> roots = new PriorityQueue<>();

        for (String name : people) {
            if (before.get(name) == 0) {
                count++;
                q.add(name);
                roots.add(name);
            }
        }

        sb.append(count).append("\n");
        while (!roots.isEmpty()) {
            sb.append(roots.poll()).append(" ");
        }
        sb.append("\n");
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Solution_21276 s = new Solution_21276();
        s.solution();
    }
}

/**
 * 골드 2 21276번 계보 복원가 호석
 *
 * 자신의 조상의 수가 0인 사람이 시조
 * 시조부터 시작해서 위상정렬
 */
