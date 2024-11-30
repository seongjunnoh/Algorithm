import java.io.*;
import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem>{
        int p;
        int l;
        int g;

        Problem(int p, int l, int g) {
            this.p = p;
            this.l = l;
            this.g = g;
        }

        // 난이도 기준 오름차순 정렬, 난이도 같으면 문제번호 기준 오름차순 정렬
        @Override
        public int compareTo(Problem o) {
            if (l == o.l) return p - o.p;
            return l - o.l;
        }
    }

    static Map<Integer, TreeSet<Problem>> map = new HashMap<>();       // key : 알고리즘
    static TreeSet<Problem> set = new TreeSet<>();
    static Map<Integer, Problem> totalMap = new HashMap<>();        // key : 문제번호

    static void add(int p, int l, int g) {
        Problem problem = new Problem(p, l, g);

        totalMap.put(p, problem);

        if (map.containsKey(g)) map.get(g).add(problem);
        else {
            TreeSet<Problem> treeSet = new TreeSet<>();
            treeSet.add(problem);
            map.put(g, treeSet);
        }

        set.add(problem);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            add(p, l, g);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();
            if (commend.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());

                add(p, l, g);
            } else if (commend.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                Problem problem = totalMap.get(p);

//                System.out.println("=====================");
//                System.out.println("remove 전 set : ");
//                Iterator<Problem> iterator1 = set.iterator();
//                while (iterator1.hasNext()) {
//                    System.out.print(iterator1.next().p + " || ");
//                }
//                System.out.println();

                set.remove(problem);        // 문제번호가 p인 문제를 set에서 remove
                map.get(problem.g).remove(problem);     // 문제번호가 p인 문제를 map에서 remove

//                System.out.println("remove 대상 : " + problem.p);

//                System.out.println("remove 후 set : ");
//                Iterator<Problem> iterator2 = set.iterator();
//                while (iterator2.hasNext()) {
//                    System.out.print(iterator2.next().p + " || ");
//                }
//                System.out.println();
//                System.out.println("=====================");

            } else if (commend.equals("recommend")) {
                int g = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) sb.append(map.get(g).last().p).append("\n");
                else sb.append(map.get(g).first().p).append("\n");
            } else if (commend.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) sb.append(set.last().p).append("\n");
                else sb.append(set.first().p).append("\n");
            } else {
                int x = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    Problem problem = new Problem(0, l, 0);
                    Problem ceiling = set.ceiling(problem);
                    if (ceiling == null) sb.append("-1").append("\n");
                    else sb.append(ceiling.p).append("\n");
                } else {
                    Problem problem = new Problem(0, l, 0);
                    Problem lower = set.lower(problem);
                    if (lower == null) sb.append("-1").append("\n");
                    else sb.append(lower.p).append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 2 21944번 문제 추천 시스템 Version 2
 *
 * recommend -> 알고리즘 별로 문제들 구분 & 각 알고리즘마다 난이도, 문제번호 정렬 필요
 *           => key : 알고리즘, val : TreeSet<문제> 인 HashMap 필요
 *           => TreeSet은 난이도 기준 오름차순 정렬, 난이도 같으면 문제번호 기준 오름차순 정렬
 * recommend2 -> 전체 문제의 난이도, 문제번호 정렬 필요
 *            => 전체 문제에 대한 TreeSet<문제> 필요
 *            => TreeSet은 난이도 기준 오름차순 정렬, 난이도 같으면 문제번호 기준 오름차순 정렬
 * recommend3 -> recommend2와 동일
 *
 * solved를 위해 key : 문제번호, val : 문제 인 HashMap 필요
 */
