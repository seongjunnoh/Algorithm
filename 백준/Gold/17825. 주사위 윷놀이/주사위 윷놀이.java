import java.io.*;
import java.util.*;

class Solution {
    
    // 0 : 시작
    // 1~20 : 가장 바깥, 21~26 : 10번 파란길, 27~28 : 20번 파란길, 29~31 : 30번 파란길
    // 32 : 도착
    
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
    int[] next; // route[i]의 다음 노드 = next[route[i]]
    int[] blue; // i번째 노드가 파란노드인 경우 : blue[i] = i번째 노드의 다음 노드(파란색 화살표)
    int[][] move;   // 전이표, move[i][j] : i번째 노드에서 j만큼 이동할 경우 도착하는 노드의 idx
    
    int[] dice;
    int[] horse;
    int max = 0;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        dice = new int[10];
        for (int i=0; i<10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        
        init();
        makeMoveTable();
        
        // 모든 말들 출발선에 위치
        horse = new int[4]; // 말 4개
        Arrays.fill(horse, 0);
        back(0, 0);
        
        System.out.println(max);
        br.close();
    }
    
    void back(int diceIdx, int sum) {
        if (diceIdx == 10) {
            max = Math.max(max, sum);
            return;
        }
        
        int curDice = dice[diceIdx];
        for (int i=0; i<4; i++) {
            int cur = horse[i];
            if (cur == 32) continue;
            
            int next = move[cur][curDice];
            
            // next 위치에 다른 말이 존재하는지 체크
            if (!canMove(next)) continue;
            
            horse[i] = next;
            back(diceIdx + 1, sum + route[next]);
            horse[i] = cur; // 원상복구
        }
    }
    
    boolean canMove(int nextHorse) {
        if (nextHorse == 32) return true;
        
        for (int i=0; i<4; i++) {
            if (horse[i] == nextHorse) return false;
        }
        return true;
    }
    
    int step(int i, int j) {
        if (i == 32) return 32;
        
        int cur = i;
        int remain = j;
        
        if (blue[cur] != -1) {    // i번째 노드가 파란색 노드인 경우
            cur = blue[cur];
            remain--;
        } else {
            cur = next[cur];
            remain--;
        }
        
        while(remain > 0 && cur != 32) {
            cur = next[cur];
            remain--;
        } 
        
        return cur;
    }
    
    void makeMoveTable() {
        move = new int[33][6];
        
        for (int i=0; i<=32; i++) {
            for (int j=1; j<=5; j++) {
                move[i][j] = step(i, j);
            }
        }
    }
    
    void init() {
        next = new int[33];
        blue = new int[33];
        Arrays.fill(next, 32);  // 초기화
        Arrays.fill(blue, -1);  // 초기화
        
        // r1
        next[0] = 1;
        for (int i=1; i<20; i++) next[i] = i+1; 
        next[20] = 32;
        
        // 파란 노드 분기처리
        blue[5] = 21;
        blue[10] = 27;
        blue[15] = 29;
        
        // r2
        for (int i=21; i<26; i++) {
            next[i] = i+1;
        }
        next[26] = 20;
        
        // r3
        next[27] = 28;
        next[28] = 24;
        
        // r4
        next[29] = 30;
        next[30] = 31;
        next[31] = 24;
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
 * 미리 특정 노드의 다음 노드가 어디인지,
 * 특정 노드에서 1~5칸을 이동할 경우 어떤 노드로 이동하는지를 구하기
 */