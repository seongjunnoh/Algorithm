import java.io.*;
import java.util.*;

class Pair_9019 {
    int regi;
    List<Character> list;     // 거쳐온 명령어 리스트

    Pair_9019(int regi, List<Character> list) {
        this.regi = regi;
        this.list = list;
    }
}

class Solution_9019 {

    int min;
    List<Character> result;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // S 연산만으로 이동할 수 있는 최소 연산
            result = new ArrayList<>();
            if (a < b) min = 10000 - b + a;
            else min = a - b;
            for (int j = 0; j < min; j++) {
                result.add('S');
            }

            play(a, b);

            for (int j = 0; j < result.size(); j++) {
                sb.append(result.get(j));
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    void play(int start, int end) {
        boolean[] visit = new boolean[10000];
        Queue<Pair_9019> q = new LinkedList<>();
        q.add(new Pair_9019(start, new ArrayList<>()));
        visit[start] = true;

        while (!q.isEmpty()) {
            Pair_9019 poll = q.poll();

            if (poll.regi == end && min > poll.list.size()) {
                result = poll.list;
                return;
            }

            int newRegi;

            newRegi = d(poll.regi);
            if (!visit[newRegi]) {
                visit[newRegi] = true;
                List<Character> newList = copy(poll.list);
                newList.add('D');
                q.add(new Pair_9019(newRegi, newList));
            }

            newRegi = s(poll.regi);
            if (!visit[newRegi]) {
                visit[newRegi] = true;
                List<Character> newList = copy(poll.list);
                newList.add('S');
                q.add(new Pair_9019(newRegi, newList));
            }

            newRegi = l(poll.regi);
            if (!visit[newRegi]) {
                visit[newRegi] = true;
                List<Character> newList = copy(poll.list);
                newList.add('L');
                q.add(new Pair_9019(newRegi, newList));
            }

            newRegi = r(poll.regi);
            if (!visit[newRegi]) {
                visit[newRegi] = true;
                List<Character> newList = copy(poll.list);
                newList.add('R');
                q.add(new Pair_9019(newRegi, newList));
            }
        }
    }

    int d(int regi) {
        return (regi * 2) % 10000;
    }

    int s(int regi) {
        if (regi == 0) return 9999;
        return regi - 1;
    }

    int l(int regi) {
        int d1 = regi / 1000;
        int d2 = (regi % 1000) / 100;
        int d3 = (regi % 100) / 10;
        int d4 = regi % 10;

        return 1000 * d2 + 100 * d3 + 10 * d4 + d1;
    }

    int r(int regi) {
        int d1 = regi / 1000;
        int d2 = (regi % 1000) / 100;
        int d3 = (regi % 100) / 10;
        int d4 = regi % 10;

        return 1000 * d4 + 100 * d1 + 10 * d2 + d3;
    }

    List<Character> copy(List<Character> list) {
        List<Character> copy = new ArrayList<>();
        for (Character c : list) {
            copy.add(c);
        }
        return copy;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_9019 s = new Solution_9019();
        s.solution();
    }
}

/**
 * a -> b 로 변환하기 위해 필요한 최소 명령어 나열 출력하기
 * -> bfs 로 regi가 변환되면서 거쳐가는 숫자들 방문 처리
 *
 * s 연산 계속 수행하면 언젠가는 b로 변환된다 -> s 연산만 수행했을때의 결과가 limit
 */