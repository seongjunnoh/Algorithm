import java.util.*;
import java.io.*;

class Main {
	
	static ArrayList<Pair> arr;
	static int n;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt( bf.readLine() ); // 계란 개수

		arr = new ArrayList<>(); 
		max = Integer.MIN_VALUE; // 깨진 계란 수 최대값 찾기
		
		for(int i = 0; i < n ; i++) {
			String[] str = bf.readLine().split(" ");
			
			int s = Integer.parseInt( str[0] );
			int w = Integer.parseInt( str[1] );
			
			arr.add(new Pair(s, w));
		} // 데이터 입력
		
		find(0, 0); // 0번째 계란, 0개 깨짐
		
		bw.write(max + "\n");
		
		bw.flush();
		bw.close();

	}
	
	public static class Pair {
		int s;
		int w;
		
		Pair(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}
	
	public static void find(int cur, int cnt) {
		
		if(max < cnt) max = cnt; // 최대값 갱신
	
		if(cur == n) return; // 이전에 고른 계란이 마지막 계란이었으면 반환
		if(arr.get(cur).s <= 0) find(cur + 1, cnt); 
      	// 이번 계란이 (이미) 깨졌으면 오른쪽(다음) 계란
		else {
			for(int i = 0; i < n; i++) {
				if(cur == i || arr.get(i).s <= 0) continue;
        		// 같은 계란을 깰 수 없음 + 이미 깨진 계란이면 패스
				
				arr.get(cur).s -= arr.get(i).w;
				arr.get(i).s -= arr.get(cur).w;
        		// 계란으로 계란치기 시도
				
				int new_cnt = cnt; // 깨진 계란 세기 변수
        		// 따로 두는 이유는 다음 계란 깨기를 위해서.
				
				if(arr.get(cur).s <= 0) new_cnt++;
				if(arr.get(i).s <= 0) new_cnt++;
        		// 깨진 계란이 있다면 개수 증가
				
				find(cur + 1, new_cnt); // 오른쪽(다음) 계란 들기 
				
				arr.get(cur).s += arr.get(i).w;
				arr.get(i).s += arr.get(cur).w;
        		// 다음 계란 치기를 위해 계란 복구
			}
		}

	}

}