import java.util.*;

class Solution {
    int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        Set<Integer> code = new HashSet<>();
        makeCode(q, ans, n, 1, code);
        return answer;
    }
    
    void makeCode(int[][] q, int[] ans, int n, int s, Set<Integer> code) {
        if (code.size() == 5) {     // 비밀 코드 완성
            boolean flag = true;
            for (int i=0; i<q.length; i++) {
                int equalCnt = 0;   // 일치하는 숫자 개수
                
                for (int j=0; j<q[i].length; j++) {
                    if (code.contains(q[i][j])) equalCnt++;
                }
                
                if (equalCnt != ans[i]) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) answer++;
            
            return;
        }
        
        for (int num=s; num<=n; num++) {
            code.add(num);
            makeCode(q, ans, n, num+1, code);
            code.remove(num);
        }
    }
}