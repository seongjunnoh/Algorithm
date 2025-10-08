import java.util.*;

class Solution {
    
    long sum1, sum2;
    int[] arr;
    int l, r, firstL, firstR;
    
    public int solution(int[] queue1, int[] queue2) {
        sum1 = calcSum(queue1);
        sum2 = calcSum(queue2);
        long sum = sum1 + sum2;
        
        if (sum % 2 != 0) return -1;
        
        arr = new int[queue1.length + queue2.length];
        for (int i=0; i<queue1.length; i++) {
            arr[i] = queue1[i];
        }
        for (int i=queue1.length; i<queue1.length + queue2.length; i++) {
            arr[i] = queue2[i - queue2.length];
        }
        
        l = 0; firstL = 0;
        r = queue1.length; firstR = queue1.length;
        return trip();
    }
    
    int trip() {    // sum1 : l ~ r, sum2 : r ~ l
        int count = 0;
        boolean moveR = false, moveL = false;
        while (true) {
            if (l == r) break;
            if ((moveL && l == firstL) || (moveR && r == firstR)) break;
            
            if (sum1 < sum2) {
                move(true);
                moveR = true;
            } else if (sum1 > sum2) {
                move(false);
                moveL = true;
            } else {  // sum1 == sum2
                return count;
            }
            
            count++;
        }
        
        return -1;
    }
    
    void move(boolean toSum1) {
        if (toSum1) {   // r 이동
            sum1 += arr[r];
            sum2 -= arr[r];
            if (r == arr.length - 1) r = 0;
            else r++;
        } else {    // l 이동
            sum1 -= arr[l];
            sum2 += arr[l];
            if (l == arr.length - 1) l = 0;
            else l++;
        }
    }
    
    long calcSum(int[] queue) {
        long sum = 0;
        for (int i=0; i<queue.length; i++) {
            sum += queue[i];
        }
        return sum;
    }
}

/**
 * 3,2,7,2 / 4,6,5,1 -> 2,7,2,4 / 6,5,1,3
 * 1,2,1,2 / 1,10,1,2 -> 10 / 1,2,1,2,1,2,1
 * 연속 & 서로 합이 같은 집합 2개로 나눌 수 있어야 한다
 * -> 투 포인터
 * 2,5 3,6
 */