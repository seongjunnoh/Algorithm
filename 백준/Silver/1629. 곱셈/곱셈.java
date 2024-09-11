import java.util.*;
import java.io.*;

public class Main {

    static int a, b, c;

    static long recursive(int x) {

        if (x == 1) {
            return a % c;
        }

        long temp = recursive(x / 2);

        if (x % 2 == 1) {
            return ((temp * temp % c) * a % c) % c;
        }

        return ((temp % c) * (temp % c)) % c;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long result = recursive(b);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

