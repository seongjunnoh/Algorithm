import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int s;
    int e;
    
    Node (int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Node n) {
        return this.e - n.e;    // e 기준 오름차순 정렬
    }
}

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            
            pq.add(new Node(Math.min(h, o), Math.max(h, o)));
        }
        
        int d = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> startPq = new PriorityQueue<>(); // 현재 선분에 포함된 node들의 s값
        int max = 0;
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.e - node.s > d) continue;  // node는 포함할 수 없음
            
            startPq.add(node.s);
            while (!startPq.isEmpty()) {
                if (startPq.peek() < node.e - d) startPq.poll();
                else break;
            }
            
            max = Math.max(max, startPq.size());
        }
        
        System.out.println(max);
        br.close();
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 완전탐색 -> 시간초과
 * 전체 Node들 n개를 선형탐색 1번으로 선분에 포함되는 Node들 최대 개수 구해야함
 */
