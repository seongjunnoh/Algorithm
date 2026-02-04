import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int time = h1*3600 + m1*60 + s1;
        int endTime = h2*3600 + m2*60 + s2;
        
        int answer = 0;
        if (time % 3600 == 0) answer++;     // 시작 시각이 매시 정각인 경우
        
        while (time < endTime) {
            // 각도 계산 [0, 360) 도
            // 60초 : 360도, 1초 : 6도 (초침)
            // 1분 : 6도, 1초 : 1/10도 (분침)
            // 1시간 : 30도, 1분 : 1/2도, 1초 : 1/120도 (시침)
            double hca = (time % (3600 * 12)) * (1.0/120.0);
            double mca = (time % 3600) * 0.1;
            double sca = (time % 60) * 6.0;
            
            // 1초 후 각도, 0도 -> 360도로 보정
            double hna = ((time + 1) % (3600 * 12)) * (1.0/120.0);
            double mna = ((time + 1) % 3600) * 0.1;
            double sna = ((time + 1) % 60) * 6.0;
            if (hna == 0) hna = 360.0;
            if (mna == 0) mna = 360.0;
            if (sna == 0) sna = 360.0;
        
            // next 시각에 등호 설정
            if (sca < hca && hna <= sna) answer++;     
            if (sca < mca && mna <= sna) answer++;
            
            // 시침 분침 초침이 동시에 겹칠 경우
            if (sna == hna && hna == mna) answer--;
                
            time++; 
        }
        
        return answer;
    }
}

// start -> end 까지 1초씩 이동시키면서 생각
