import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        String name;
        int rank;

        Pair(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < l; i++) {
            stack.push(br.readLine());
        }

        Map<String, Integer> map = new HashMap<>();
        int rank = 1;
        while (!stack.isEmpty()) {
            String pop = stack.pop();

            if (!map.containsKey(pop)) map.put(pop, rank++);      // 뒤에서부터의 순위 기록
        }

        // map에서 수강신청 성공한 학생들 list로 옮기기
        List<Pair> list = new ArrayList<>();
        int size = map.keySet().size();
        for (String num : map.keySet()) {
            if (size - map.get(num) < k) list.add(new Pair(num, size - map.get(num)));
        }

        // list 정렬
        Collections.sort(list, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.rank - p2.rank;
            }
        });

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Pair pair : list) {
            if (count == k) break;
            sb.append(pair.name).append("\n");
            count++;
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 13414 수강신청
 *
 * 대기목록의 중복 학생을 처리하기 위해 stack 저장 -> 역순으로 map에 순위 기록
 *
 * 출력시에도 순서유지한채로 출력해야 하는 듯
 */
