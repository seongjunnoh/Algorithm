import java.util.*;

class Truck {
    int w;  // 트럭 무게
    int t;  // 다리에 올라온 시각
    
    Truck(int w, int t) {
        this.w = w;
        this.t = t;
    }
}

class Solution {
    public int solution(int len, int weight, int[] truck) {
        Queue<Truck> q = new LinkedList<>();    // 다리
        q.add(new Truck(truck[0], 1));  // 초기화
        int time = 1;
        int sumW = truck[0];   // 현재 다리 위 트럭 무게 합
        int sumN = 1;   // 현재 다리 위 트럭 개수 합
        int idx = 1;
        
        while (idx < truck.length) {
            time++;     // 시간 plus
            
            // head 가 다리에서 내려와야 하는지 확인
            Truck head = q.peek();
            if (time - head.t == len) {
                q.poll();
                sumW -= head.w;
                sumN--;
            }
            
            // 다리에 새로운 트럭 올라올 수 있는지 확인
            int cur = truck[idx];
            
            // cur 다리에 올라온다
            if (sumW + cur <= weight && sumN + 1 <= len) {
                q.add(new Truck(cur, time));
                sumW += cur;
                sumN++;
                idx++;  // 다음 트럭
            }
        }
        
        // 현재 다리위에 존재하는 트럭들 마저 이동
        while (!q.isEmpty()) {
            Truck head = q.peek();
            if (time - head.t < len) {
                time++;
                continue;
            }
            
            q.poll();
        }
        
        return time;
    }
}

// 트럭 : 1초에 1만큼 이동
// FIFO, 큐 활용