package basic;

import java.io.*;
import java.util.*;

public class Input {
    public static void main(String[] args) throws IOException {

        Map<String, List<Integer>> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();

        InputStream inputStream = new FileInputStream("a.data");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            String[] line = str.split(",");
            String key = line[0] + line[1];
            if (map2.get(key) == null) {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(line[2]));
                map2.put(key, list);
            } else {
                List<Integer> list = map2.get(key);
                list.add(Integer.parseInt(line[2]));
            }
        }
        for (String key : map2.keySet()) {
            List<Integer> list = map2.get(key);
            int QPS, P99, P50, AVG, MAX = 0;
            Integer[] ll = list.toArray(new Integer[list.size()]);
            Arrays.sort(ll);
            QPS = ll.length;
            P99 = ll[(int) Math.ceil(QPS * 0.99) - 1];
            P50 = ll[(int) Math.ceil(QPS * 0.5) - 1];
            double sum = 0;
            for (int i : ll) {
                MAX = Math.max(MAX, i);
                sum += i;
            }
            AVG = (int) Math.ceil(sum / QPS);
            map3.put(key, QPS + "," + P99 + "," + P50 + "," + AVG + "," + MAX);
        }
        inputStream.close();
        bufferedReader.close();

        for (String s : map3.keySet()) {
            System.out.println(map3.get(s));
        }
    }
}
