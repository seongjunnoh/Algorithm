import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, List<Integer>>[] list;      // list[i] : i번 노드 -> key = 0 : i번 노드보다 작은 노드들, 1 : 큰 노드들
    static boolean[] visit;
    static int n;

    static boolean bfsLower(int node) {
        Map<Integer, List<Integer>> map = list[node];
        visit = new boolean[n + 1];
        visit[node] = true;
        int count = 0;
        Queue<Integer> q = new LinkedList<>();

        for (int near : map.get(0)) {
            if (!visit[near]) {     // 입력이 중복될 경우에도 count 를 한번만 올리기 위해
                visit[near] = true;
                q.add(near);
                count++;   
            }
        }

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            Map<Integer, List<Integer>> pollMap = list[poll];
            for (int near : pollMap.get(0)) {
                if (!visit[near]) {
                    visit[near] = true;
                    q.add(near);
                    count++;
                }
            }
        }

        return count >= (n + 1) / 2;
    }

    static boolean bfsUpper(int node) {
        Map<Integer, List<Integer>> map = list[node];
        visit = new boolean[n + 1];
        visit[node] = true;
        int count = 0;
        Queue<Integer> q = new LinkedList<>();

        for (int near : map.get(1)) {
            if (!visit[near]) {     // 입력이 중복될 경우에도 count 를 한번만 올리기 위해
                visit[near] = true;
                q.add(near);
                count++;
            }
        }
        
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            Map<Integer, List<Integer>> pollMap = list[poll];
            for (int near : pollMap.get(1)) {
                if (!visit[near]) {
                    visit[near] = true;
                    q.add(near);
                    count++;
                }
            }
        }

        return count >= (n + 1) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        list = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new HashMap<>();
            list[i].put(0, new ArrayList<>());
            list[i].put(1, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());       // x > y

            list[x].get(0).add(y);
            list[y].get(1).add(x);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (bfsLower(i) || bfsUpper(i)) count++;
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 4 2617번 구슬 찾기
 *
 * 자신보다 무거운 구슬이 n+1/2 이상이거나, 자신보다 가벼운 구슬이 n+1/2 이상이면 count++
 * -> 입력으로 주어지는 m 개의 입력이 중복될 수 있음 -> 예외 처리 필요
 * 
 */
