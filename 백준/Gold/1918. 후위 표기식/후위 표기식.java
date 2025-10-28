import java.io.*;
import java.util.*;

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Character> list = new ArrayList<>();   // 입력 연산식
        
        String line = br.readLine();
        for (int i=0; i<line.length(); i++) {
            list.add(line.charAt(i));
        }
        
        Stack<Character> op = new Stack<>();    // 연산자 스택
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<list.size(); i++) {
            char cur = list.get(i);
            
            if (cur >= 'A' && cur <= 'Z') {
                sb.append(cur);     // 숫자는 바로 출력
                continue;
            }
            
            if (cur == '(') op.push(cur);
            else if (cur == ')') {
                while (!op.isEmpty()) {
                    Character popOp = op.pop();
                    if (popOp == '(') break;
                    sb.append(popOp);
                }
            } else {    // 나머지 연산자 : +, -, *, /
                while (!op.isEmpty()) {
                    // 현재 연산자보다 스택에 있는 연산자의 우선순위가 더 높은 경우
                    if (rank(op.peek()) >= rank(cur)) {
                        sb.append(op.pop());    // 먼저 출력
                    } else {
                        break;
                    }
                }
                
                op.push(cur);
            }
        }
        
        while (!op.isEmpty()) {
            sb.append(op.pop());
        }
        
        System.out.println(sb);
        br.close();
    }
    
    int rank(Character c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;   // '(' -> 우선순위 최하위
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
	    Solution s = new Solution();
	    s.solution();
	}
}