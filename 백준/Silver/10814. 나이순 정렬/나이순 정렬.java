import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Person {
        int age;
        String name;

        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Person[] arr = new Person[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr[i] = new Person(age, name);
        }

        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.age - p2.age;
            }
        });

        for (int i = 0; i < n; i++) {
            sb.append(arr[i].age + " " + arr[i].name).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}