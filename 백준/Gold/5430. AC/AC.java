import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String command = br.readLine();
            int cnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            Deque<Integer> deque = new LinkedList<>();

            for (int j = 0; j < cnt; j++) {
                String token = st.nextToken(",");
                if (token.startsWith("[") && token.endsWith("]")) {
                  token = token.substring(1, token.length() - 1);
                } else if (token.startsWith("[")) {
                    token = token.substring(1);
                } else if (token.endsWith("]")) {
                    token = token.substring(0, token.length() - 1);
                }
                deque.offerLast(Integer.parseInt(token));
            }

            int flow = 0;           // 0 : 앞에서부터, 1 : 뒤에서부터
            boolean isError = false;
            sb.append("[");

            for (int j = 0; j < command.length(); j++) {
                if (isError == true) {
                    break;
                }

                Character ch = command.charAt(j);
                switch (ch) {
                    case 'R':
                        if (flow == 0) {
                            flow = 1;
                        } else {
                            flow = 0;
                        }
                        break;
                    case 'D':
                        if (deque.isEmpty()) {
                            isError = true;
                            break;
                        }
                        if (flow == 0) {
                            deque.pollFirst();
                        } else {
                            deque.pollLast();
                        }
                        break;
                }
            }

            if (isError == true) {
                int index = sb.lastIndexOf("\n");
                if (index == -1) {
                    // 해당 error 가 첫번째 테스트 케이스에서 발생
                    sb.replace(0, sb.length(), "error");
                } else {
                    sb.replace(index+1, sb.length(), "error");
                }
                sb.append("\n");
                continue;
            }

            int size = deque.size();
            if (flow == 0) {
                for (int j = 0; j < size; j++) {
                    sb.append(deque.pollFirst());
                    if (j < size - 1) {
                        sb.append(",");
                    }
                }
            } else {
                for (int j = 0; j < size; j++) {
                    sb.append(deque.pollLast());
                    if (j < size - 1) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]").append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}