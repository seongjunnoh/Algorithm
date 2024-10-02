import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        String name;
        int k;
        int e;
        int m;

        Pair(String name, int k, int e, int m) {
            this.name = name;
            this.k = k;
            this.e = e;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(name, k, e, m);
        }

        Arrays.sort(arr, new Comparator<Pair>() {
           @Override
           public int compare(Pair p1, Pair p2) {
               if (p1.k != p2. k) return p2.k - p1.k;

               if (p1.e != p2.e) return p1.e - p2.e;

               if (p1.m != p2.m) return p2.m - p1.m;

               String n1 = p1.name;
               String n2 = p2.name;
               return n1.compareTo(n2);
           }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i].name).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 4 10825 국영수
 */
