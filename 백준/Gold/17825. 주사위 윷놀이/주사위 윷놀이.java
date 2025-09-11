import java.io.*;
import java.util.*;

class Solution {
    
    // 0 : 시작
    // 1~20 : 가장 바깥, 21~26 : 10번 파란길, 27~28 : 20번 파란길, 29~31 : 30번 파란길
    // 32 : 도착
    
    // 10번 idx = 5, 20번 idx = 10, 30번 idx = 15, 25번 idx = 24
    
    // r1 : route[0]~route[20] + route[32]
    // r2 : route[0]~route[5] + route[21]~route[26] + route[20] + route[32]
    // r3 : route[0]~route[10] + route[27]~route[28] + route[24]~route[26] + route[20] + route[32]
    // r4 : route[0]~route[15] + route[29]~route[31] + route[24]~route[26] + route[20] + route[32]
    int[] route = {
        0,
        2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, // 파란 노드 안타는 경로
        13, 16, 19, 25, 30, 35, // 10번 파란 노드 타는 경로
        22, 24, // 20번 파란 노드 타는 경로
        28, 27, 26,  // 30번 파란 노드 타는 경로
        0
    };
    int[] horse;    // route 상에서 각 말의 위치
    int[] dice; // 주사위 
    int max = 0;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        dice = new int[10];
        for (int i=0; i<10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        
        horse = new int[4];
        Arrays.fill(horse, 0);  // 모든 말은 시작칸에서 출발
        
        back(0, 0);
        
        System.out.println(max);
        br.close();
    }
    
    void back(int diceIdx, int sum) {
        if (diceIdx == 10) {
            max = Math.max(max, sum);
            return;
        }
        
        for (int i=0; i<4; i++) {   // 말 이동
            int curHorse = horse[i];
            int curDice = dice[diceIdx];
            
            int nextHorse = calcNextHorse(curHorse, curDice);    // 이동시킨 말의 위치 계산
            if (!canPick(nextHorse)) continue;  // 이동을 마치는 칸에 다른 말이 존재하는 경우
            
            horse[i] = nextHorse;
            back(diceIdx + 1, sum + route[nextHorse]);
            horse[i] = curHorse;    // 원상복구
        }
    }
    
    boolean canPick(int nextHorse) {
        if (nextHorse == 32) return true;   // 예외처리
        
        for (int i=0; i<4; i++) {
            if (horse[i] == nextHorse) return false;
        }
        
        return true;
    }
    
    int calcNextHorse(int curHorse, int curDice) {
        // 현재 파란점에 위치
        if (curHorse == 5 || curHorse == 10 || curHorse == 15) {
            if (curHorse == 5) return 21 + curDice - 1;
            if (curHorse == 10) {
                if (curDice <= 2) return 27 + curDice - 1;
                return 24 + (curDice - 2) - 1;
            }
            if (curHorse == 15) {
                if (curDice <= 3) return 29 + curDice - 1;
                return 24 + (curDice - 3) - 1;
            }
        } 
        
        // 현재 빨간점에 위치
        if (21 <= curHorse && curHorse <= 26) {
            if (curHorse + curDice <= 26) return curHorse + curDice;
            if (curHorse + curDice == 27) return 20;   // 40번 노드
            return 32; // 도착
        }
        
        if (27 <= curHorse && curHorse <= 28) {
            if (curHorse + curDice <= 28) return curHorse + curDice;
            if (curHorse + curDice < 32) return 24 + (curHorse + curDice - 29);
            if (curHorse + curDice == 32) return 20;    // 40번 노드
            return 32;  // 도착
        }
        
        if (29 <= curHorse && curHorse <= 31) {
            if (curHorse + curDice <= 31) return curHorse + curDice;
            if (curHorse + curDice < 35) return 24 + (curHorse + curDice - 32);
            if (curHorse + curDice == 35) return 20;    // 40번 노드
            return 32; // 도착
        }
        
        // 가장 마지막 경로에 위치한 경우
        if (curHorse + curDice <= 20) return curHorse + curDice;
        return 32;  // 도착
    }
    
    boolean move(int idx) {
        
        
        for (int i=0; i<4; i++) {
            if (horse[i] == idx) return false;
        }
        
        return true;
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 주사위 1번 던질때 말 4개 중 어떤 걸 움직일지 모든 경우의 수 구하기 -> 백트래킹
 * 
 * 말의 다음 위치를 구하는 로직을 어떻게 간편하게 할 수 있을까??
 */
 
 