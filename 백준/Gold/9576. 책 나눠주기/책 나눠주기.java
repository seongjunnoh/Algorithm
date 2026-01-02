import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int s;
    int e;
    
    Pair (int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Pair p) {
        if (this.e == p.e) {
            return this.s - p.s;    // 시작 지점이 빠른게 앞으로
        }
        return this.e - p.e;    // 끝 지점이 빠른게 앞으로
    }
}

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                Pair p = new Pair(s, e);
                pq.add(p);
            }
            
            int res = 0;
            boolean[] visit = new boolean[n+1];
            while (!pq.isEmpty()) {
                Pair poll = pq.poll();
                
                for (int i=poll.s; i<=poll.e; i++) {
                    if (visit[i]) continue;
                    
                    visit[i] = true;
                    res++;
                    break;
                }
            }
            
            sb.append(res).append("\n");
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

/* 끝 지점 기준 오름차순 정렬 (그리디)
 * 
 */