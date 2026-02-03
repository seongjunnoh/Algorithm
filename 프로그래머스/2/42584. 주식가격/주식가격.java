import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();    // 인덱스 stack
        int[] answer = new int[prices.length];
        
        for (int i=0; i<prices.length; i++) {
            while (!stack.isEmpty()) {
                int lastIdx = stack.peekLast();

                if (prices[lastIdx] <= prices[i]) {    // 탈출
                    break;
                }

                // 가격이 떨어짐
                stack.pollLast();
                answer[lastIdx] = i - lastIdx;
            }
            
            stack.addLast(i);  // push
        }
        
        // 스택에 남아있는 숫자들 처리
        while (!stack.isEmpty()) {
            int poll = stack.pollLast();
            answer[poll] = prices.length - 1 - poll;
        }
        
        return answer;
    }
}