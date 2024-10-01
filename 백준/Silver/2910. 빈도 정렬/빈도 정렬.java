import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Pair {
        int num;
        int idx;
        int count;

        Pair(int num, int idx, int count) {
            this.num = num;
            this.idx = idx;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Map<Integer, Pair> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) {
                map.get(num).count++;
            } else {
                map.put(num, new Pair(num, i, 1));
            }
        }

        ArrayList<Pair> pairs = new ArrayList<>(map.values());
        Collections.sort(pairs, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.count == p2.count) {
                    return p1.idx - p2.idx;
                }
                return p2.count - p1.count;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Pair p : pairs) {
            for (int i = 0; i < p.count; i++) {
                sb.append(p.num).append(" ");
            }
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 2910 빈도 정렬
 *
 * 내 생각
 * 어떤 수가 입력되는 빈도를 어떻게 계산??
 * -> 정렬 후 한번 순차탐색
 * -> 그런데 이걸 각각의 숫자에 어떻게 할당?? => 이중 for 문 ??
 *
 * 더 나은 생각
 * LinkedHashMap 도입해서 여기에다가 Pair 넣어서 관리
 * -> LinkedHashMap은 입력된 순서를 유지함 -> 따라서 최초 등장 인덱스를 모든 Pair 마다 관리할 필요 없음
 */
