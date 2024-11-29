import java.io.*;
import java.util.*;

public class Main {

    static class Problem {
        int p;      // 문제 번호
        int l;      // 문제 난이도

        Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }
    }

    // 난이도 기준 오름차순 정렬 & 난이도 같으면 문제 번호 기준 오름차순 정렬
    static class ProblemComparator implements Comparator<Problem> {
        @Override
        public int compare(Problem p1, Problem p2) {
            if (p1.l == p2.l) return p1.p - p2.p;
            return p1.l - p2.l;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TreeSet<Problem> set = new TreeSet<>(new ProblemComparator());
        Map<Integer, Integer> map = new HashMap<>();        // 문제 번호 - 난이도 map

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            set.add(new Problem(p, l));
            map.put(p, l);      // 문제 번호가 key
        }

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                set.add(new Problem(p, l));
                map.put(p, l);      // 문제 번호가 key
            } else if (command.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                int l = map.get(p);

                set.remove(new Problem(p, l));

            } else {        // recommend
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    sb.append(set.last().p).append("\n");
                } else {
                    sb.append(set.first().p).append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 21939번 문제 추천 시스템 Version 1
 *
 * 문제번호는 중복허용 X
 * recommend 는 문제 난이도로 거르고 -> 문제 번호로 걸러야 함
 *
 * add, solved 시 마다 문제들을 매번 정렬하면 시간 초과 발생
 * -> TreeSet<Problem> 사용해서 log n 으로 정렬 & HashMap으로
 *
 */
