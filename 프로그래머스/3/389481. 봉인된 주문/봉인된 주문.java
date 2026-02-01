import java.util.*;
import java.io.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] bansRank = new long[bans.length];
        for (int i=0; i<bans.length; i++) {
            bansRank[i] = toRank(bans[i]);
        }
        
        Arrays.sort(bansRank);      // 삭제할 주문 -> rank로 변환 & 정렬
        
        for (int i=0; i<bans.length; i++) {
            long r = bansRank[i];
            
            if (r <= n) n++;
            else break;
        }
        
        return toString(n);
    }
    
    long toRank(String s) {
        long rank = 0;
        for (int i=0; i<s.length(); i++) {
            int num = s.charAt(i) - 'a' + 1;    // a->1, b->2, ,,
            rank += Math.pow(26, s.length() - i - 1) * num;
        }
        
        return rank;
    }
    
    String toString(long rank) {
        StringBuilder sb = new StringBuilder();
        
        while (rank > 0) {
            rank--;
            sb.append((char)('a' + (rank % 26)));
            rank /= 26;
        }
        
        return sb.reverse().toString();
    }
}

// 주문을 숫자로 변환
// a, b, c, ,,, -> 1, 2, 3, ,,,
