import java.util.*;

class Node {
    int p;  // 우선 순위
    boolean flag;   // true인 노드가 정답
    
    Node (int p, boolean flag) {
        this.p = p;
        this.flag = flag;
    }
}

class Solution {
    public int solution(int[] p, int l) {
        Integer[] arr = new Integer[p.length];
        for (int i=0; i<arr.length; i++) {
            arr[i] = p[i];      // 배열 깊은 복사
        }
        Arrays.sort(arr, Collections.reverseOrder());   // arr 정렬
        
        int idx = 0;    // 현재 우선순위의 idx
        
        Queue<Node> q = new LinkedList<>();
        for (int i=0; i<p.length; i++) {
            if (i == l) q.add(new Node(p[i], true));
            else q.add(new Node(p[i], false));
        }
        
        int answer = 1;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            
            if (poll.p != arr[idx]) {
                q.add(poll);
                continue;
            }
            
            // 가장 큰 우선순위의 노드를 뽑은 경우
            if (poll.flag) break;
            else {
                answer++;
                idx++;
            }
        }
        
        return answer;
    }
}