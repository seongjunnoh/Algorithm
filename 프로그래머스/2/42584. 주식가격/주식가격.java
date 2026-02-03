import java.util.*;

class Node {
    int num;
    int idx;
    
    Node (int num, int idx) {
        this.num = num;
        this.idx = idx;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        ArrayDeque<Node> stack = new ArrayDeque<>();    // 아래일수록 숫자 작거나 같음
        int[] answer = new int[prices.length];
        
        for (int i=0; i<prices.length; i++) {
            if (stack.isEmpty()) {
                stack.addLast(new Node(prices[i], i));
                continue;
            }
            
            boolean flag = false;
            while (!stack.isEmpty()) {
                Node last = stack.peekLast();

                if (last.num <= prices[i]) {    // 탈출
                    stack.addLast(new Node(prices[i], i));
                    flag = true;
                    break;
                }

                // 가격이 떨어짐
                stack.pollLast();
                answer[last.idx] = i - last.idx;
            }
            
            if (!flag) {
                stack.addLast(new Node(prices[i], i));
            }
        }
        
        // 스택에 남아있는 숫자들 처리
        while (!stack.isEmpty()) {
            Node poll = stack.pollLast();
            answer[poll.idx] = prices.length - 1 - poll.idx;
        }
        
        return answer;
    }
}