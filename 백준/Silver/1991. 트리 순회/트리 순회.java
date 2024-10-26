import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        char name;
        char left;
        char right;

        Node(char name, char left, char right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
    }

    static Map<Character, Node> graph;
    static StringBuilder sb;

    static void before(Node node) {     // 전위순회
        char name = node.name;
        char left = node.left;
        char right = node.right;

        sb.append(name);
        if (left != '.') before(graph.get(left));
        if (right != '.') before(graph.get(right));
    }

    static void middle(Node node) {
        char name = node.name;
        char left = node.left;
        char right = node.right;

        if (left != '.') middle(graph.get(left));
        sb.append(name);
        if (right != '.') middle(graph.get(right));
    }

    static void after(Node node) {
        char name = node.name;
        char left = node.left;
        char right = node.right;

        if (left != '.') after(graph.get(left));
        if (right != '.') after(graph.get(right));
        sb.append(name);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char name = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            graph.put(name, new Node(name, left, right));
        }

        before(graph.get('A'));
        sb.append("\n");

        middle(graph.get('A'));
        sb.append("\n");

        after(graph.get('A'));

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 1 1991 트리 순회
 */
