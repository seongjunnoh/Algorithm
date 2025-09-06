import java.io.*;
import java.util.*;

class Solution {
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        if (n == 1) {
            System.out.println("A");
            return;
        }
        
        if (n == 2) {
            if (arr[0] == arr[1]) System.out.println(arr[0]);
            else System.out.println("A");
            return;
        }
        
        // n이 3 이상 -> (arr[0], arr[1]), (arr[1], arr[2]) 두 점을 통해 a, b 초기값 설정
        int a;
        if (arr[0] == arr[1] && arr[1] == arr[2]) { // 두 점이 동일한 경우
            a = 1;
        } else if (arr[0] == arr[1]) {  // a가 정의될 수 없는 경우
            System.out.println("B");
            return;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);  // a 는 정수
        }
        int b = arr[1] - arr[0] * a;
        
        // 모든 arr에 대하여 (arr[i], arr[i+1]) 이 정해진 일차함수 위에 존재하는지 확인
        for (int i=0; i<n-1; i++) {
            if (arr[i+1] != a * arr[i] + b) {
                System.out.println("B");
                return;
            }
        }
        
        System.out.println(a * arr[n-1] + b);
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * arr[i+1] = arr[i] * a + b (a, b는 정수)
 * -> y = ax + b 인 일차함수 위에 (arr[i], arr[i+1]), (arr[i+1], arr[i+2]), ,,, 가 존재하는 상황
 * 
 */