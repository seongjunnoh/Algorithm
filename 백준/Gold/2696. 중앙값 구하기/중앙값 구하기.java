import java.io.*;
import java.util.*;

class Solution {
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++) {
            PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());   // 중앙값 이하 수
            PriorityQueue<Integer> better = new PriorityQueue<>();  // 중앙값 초과 수
            
            int m = Integer.parseInt(br.readLine());
            sb.append(m/2 + 1).append("\n");
            int count = 0;  // 중앙값 개수
            
            for (int j=0; j<=m/10; j++) {
                st = new StringTokenizer(br.readLine());
                int size = st.countTokens();
                for (int k=0; k<size; k++) {
                    if (lower.size() == better.size()) {
                        lower.add(Integer.parseInt(st.nextToken()));
                    } else {
                        better.add(Integer.parseInt(st.nextToken()));
                    }
                    
                    // lower : 중앙값 이하 수, better : 중앙값 초과 수 -> lower.peek() : 중앙값
                    if (!lower.isEmpty() && !better.isEmpty() && lower.peek() > better.peek()) {
                        int temp = lower.poll();
                        lower.add(better.poll());
                        better.add(temp);
                    }
                    
                    if (k%2 == 0) { // 홀수 번째 수마다 중앙값 append
                        sb.append(lower.peek()).append(" ");
                        count++;
                    }
                }    
                
                if (m/2 + 1 == count || count % 10 == 0) sb.append("\n");
            }
        }
        
        System.out.println(sb);
        br.close();
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}
