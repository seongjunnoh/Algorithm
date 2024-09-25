import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Car {
        int weight;         // 트럭의 무게
        int distance;       // 트럭이 다리위에서 움직인 거리

        Car(int w, int d) {
            this.weight = w;
            this.distance = d;
        }
    }

    static int n, w, l;
    static Queue<Integer> remains = new LinkedList<>();         // 다리 오른쪽에 남아있는 트럭들
    static Queue<Car> bridge = new LinkedList<>();
    static int time = 0;
    static int plusStrong = 0;              // 다리에 추가해야하는 추가 하중

    static void play(int strong) {
        // strong : 현재 다리가 버틸 수 있는 하중

        if (remains.isEmpty() && bridge.isEmpty()) return;

        // 다리 위 트럭들을 한칸씩 이동 && time update
        int curStrong = strong;
        if (move()) {
            curStrong += plusStrong;
        }

        time++;

        // 다리 오른쪽에 남은 트럭이 있는 경우
        if (!remains.isEmpty()) {

            if (remains.peek() <= curStrong) {
                // 다리에 트럭 추가할 수 있는 경우
                int weight = remains.poll();
                curStrong = curStrong - weight;
                bridge.add(new Car(weight, 1));         // 다리에 트럭 추가

                play(curStrong);
            } else {
                // 다리에 트럭 추가할 수 없는 경우
                play(curStrong);
            }
        } else {
            // 다리에 남은 트럭이 없는 경우
            play(curStrong);
        }
    }

    static boolean move() {        // 다리 위에 있는 모든 트럭들을 한칸씩 왼쪽으로 이동
        // 다리 위의 모든 트럭들을 한칸씩 이동
        int size = bridge.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Car poll = bridge.poll();
                poll.distance++;
                bridge.add(poll);
            }
        }

        // 제일 앞의 트럭이 이동한 거리 체크
        Car peek = bridge.peek();
        if (peek != null && peek.distance > w) {
            Car poll = bridge.poll();
            plusStrong = poll.weight;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());           // 다리 길이
        l = Integer.parseInt(st.nextToken());           // 다리 최대 하중

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            remains.add(Integer.parseInt(st.nextToken()));
        }

        play(l);

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 실버 1 13335번 트럭
 *
 *
 */
