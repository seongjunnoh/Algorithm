import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        int last = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                queue.offer(num);
                last = num;
                continue;
            }
            if (command.equals("pop")) {
                if (queue.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(queue.remove()).append("\n");
                }
                continue;
            }
            if (command.equals("size")) {
                sb.append(queue.size()).append("\n");
                continue;
            }
            if (command.equals("empty")) {
                if (queue.isEmpty()) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
                continue;
            }
            if (command.equals("front")) {
                if (!queue.isEmpty()) {
                    sb.append(queue.peek()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
                continue;
            }
            if (command.equals("back")) {
                if (!queue.isEmpty()) {
                    sb.append(last).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
                continue;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}