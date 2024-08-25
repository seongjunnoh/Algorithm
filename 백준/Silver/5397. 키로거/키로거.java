import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        int n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String test = br.readLine();
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();

            for (int j = 0; j < test.length(); j++) {
                char c = test.charAt(j);
                if (c == '<') {
                    if (!left.empty()) {
                        Character pop = left.pop();
                        right.push(pop);
                    }
                    continue;
                }
                if (c == '>') {
                    if (!right.empty()) {
                        Character pop = right.pop();
                        left.push(pop);
                    }
                    continue;
                }
                if (c == '-') {
                    if (!left.empty()) {
                        left.pop();
                    }
                    continue;
                }
                left.push(c);
            }
            
            // 커서 오른쪽 스택의 알파벳도 모두 출력해야 함!!
            while (!right.empty()) {
                left.push(right.pop());
            }

            for (int k = 0; k < left.size(); k++) {
                bw.write(left.get(k));
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}

/**
 * 5397 키로거
 * < : 왼쪽으로 이동 , > : 오른쪽으로 이동 , - : 왼쪽이 글자이면 지우기
 *
 * 커서의 위치가 중요 -> 커서를 기준으로 왼, 오른쪽으로 나눠 알파벳 관리
 * => 최근 글자의 update가 발생하는 것이므로 스택을 이용하여 관리
 */