import java.io.*;
import java.util.*;

class Node_5639 {
    int num;
    Node_5639 left;
    Node_5639 right;

    Node_5639(int num) {
        this.num = num;
        this.left = null;
        this.right = null;
    }
}

class Solution_5639 {

    List<Node_5639> list;
    Node_5639 root;
    StringBuilder sb;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 루트 노드는 따로 입력받기
        String input = br.readLine();
        if (input == null || input.isEmpty()) {      // 바로 종료
            br.close();
            return;
        }
        root = new Node_5639(Integer.parseInt(input));

        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty()) break;

            int cur = Integer.parseInt(input);
            add(root, cur);
        }

        // 후위순회 출력
        sb = new StringBuilder();
        postTravel(root);
        System.out.println(sb);
        br.close();
    }

    void add(Node_5639 node, int child) {
        if (node.num > child) {
            if (node.left == null) {
                node.left = new Node_5639(child);
                return;
            }
            add(node.left, child);
        } else {
            if (node.right == null) {
                node.right = new Node_5639(child);
                return;
            }
            add(node.right, child);
        }
    }

    void postTravel(Node_5639 node) {
        if (node == null) return;

        postTravel(node.left);
        postTravel(node.right);
        sb.append(node.num).append("\n");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_5639 s = new Solution_5639();
        s.solution();
    }
}

/**
 * 전위순회 결과로 트리 구성 -> 구성된 트리를 후위순회로 돌면서 출력
 *
 * 왼쪽 트리에는 항상 자신보다 작은 값, 오른쪽 트리에는 항상 자신보다 큰 값이 있어야 함
 *
 * add 연산 한번당 시간 복잡도 = 트리의 높이 = 최악의 경우 10000, 최선의 경우 14 정도
 * & 총 노드 = 10000개
 * -> 총 시간 복잡도 = 10000 제곱 = 1억 -> ok ???
 */