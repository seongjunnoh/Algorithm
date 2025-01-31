import java.io.*;
import java.util.*;

class Root {
    String name;        // 루트 노드의 이름
    int count;          // 루트의 sub tree의 전체 노드 개수

    Root(String name, int count) {
        this.name = name;
        this.count = count;
    }
}

class Solution_4195 {

    Map<String, Root> parent;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            parent = new HashMap<>();

            for (int j = 0; j < f; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (parent.get(a) == null) {
                    parent.put(a, new Root(a, 1));
                }
                if (parent.get(b) == null) {
                    parent.put(b, new Root(b, 1));
                }

                sb.append(union(a, b)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    int union(String a, String b) {
        Root rA = find(a);
        Root rB = find(b);

        if (rA.name.compareTo(rB.name) == 0) {
            return rA.count;
        } else if (rA.name.compareTo(rB.name) < 0) {
            parent.put(rB.name, rA);
            rA.count += rB.count;
            return rA.count;
        } else {
            parent.put(rA.name, rB);
            rB.count += rA.count;
            return rB.count;
        }
    }

    Root find(String node) {
        Root root = parent.get(node);
        if (root.name.equals(node)) return root;

        Root realRoot = find(root.name);
        parent.put(node, realRoot);     // 경로 압축 코드 수정

        return realRoot;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_4195 s = new Solution_4195();
        s.solution();
    }
}

/**
 * 시간초과 -> 매번 bfs로 네트워크의 크기를 구해서 그런듯
 * ----------------------------------------------
 * 같은 집합에 존재하는 노드들의 개수 구하기 -> union find 발상
 *
 */
