import java.util.*;

class Solution {
    int[] discount = {10, 20, 30, 40};
    int[][] emoCalc;  // [할인율, 할인율이 적용된 이모티콘 가격]
    int[] answer;
    
    public int[] solution(int[][] users, int[] emo) {
        emoCalc = new int[emo.length][2];
        answer = new int[2];
        
        dfs(users, emo, 0);

        return answer;
    }
    
    void dfs(int[][] users, int[] emo, int idx) {
        if (idx == emo.length) {    // 이모티콘 플러스 가입 여부 및 이모티콘 판매액 계산
            calc(users);
            return;
        }
        
        // 이모티콘 할인율 설정
        for (int i=0; i<4; i++) {
            emoCalc[idx][0] = discount[i];
            emoCalc[idx][1] = emo[idx] * (100 - discount[i]) / 100;
            dfs(users, emo, idx + 1);
        }
    }
    
    void calc(int[][] users) {
        int plusCount = 0;   // 이모티콘 플러스 가입자 수
        int sum = 0;    // 총 이모티콘 매출액
        
        for (int i=0; i<users.length; i++) {    // 모든 유저에 대해서
            int per = users[i][0];
            int money = users[i][1];
            
            int curSum = 0;
            for (int[] e : emoCalc) {
                if (e[0] >= per) curSum += e[1];
            }
            
            if (curSum >= money) plusCount++;    
            else sum += curSum;
        }
        
        if (plusCount > answer[0]) {
            answer[0] = plusCount;
            answer[1] = sum;
        } else if (plusCount == answer[0] && sum > answer[1]) {
            answer[1] = sum;
        }
    }
}

/**
 * 완전 탐색
 */ 