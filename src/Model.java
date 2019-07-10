import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Model {

    public static void main(String[] args) {
        List<TimeStat> statistics = new ArrayList<>();
        Map<Double,List<Double>> map = new HashMap<>();
        Map<Long,Double> map1 = new HashMap<>();

        URL url = Thread.currentThread().getContextClassLoader().getResource("resources/data_task4_old_.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(url.getFile()))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null){
                TimeStat timeStat = new TimeStat();
                String[] strings = line.split("\t");
                timeStat.setLogin(strings[0]);
                timeStat.setTaskId(Double.parseDouble(strings[1]));
                timeStat.setMicrotasks(Double.parseDouble(strings[2]));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                timeStat.setAssignedTime(sdf.parse(strings[3]));
                timeStat.setClosedTime(sdf.parse(strings[4]));
                statistics.add(timeStat);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        for (TimeStat timeStat : statistics){
            long dif = timeStat.getClosedTime().getTime()-timeStat.getAssignedTime().getTime();
            long secs = TimeUnit.MILLISECONDS.toSeconds(dif);
            if (map.containsKey(timeStat.getTaskId())){
                List<Double> list = map.get(timeStat.getTaskId());
                list.add(secs / timeStat.getMicrotasks());
                map.put(timeStat.getTaskId(),list);
            } else {
                List<Double> list = new ArrayList<>();
                list.add(secs / timeStat.getMicrotasks());
                map.put(timeStat.getTaskId(),list);
            }
        }

/*        for (Map.Entry<Double,List<Double>> pair : map.entrySet()){
            if (pair.getValue().size() > 30){
                for (Double d : pair.getValue()) {
                    System.out.println(d);
                }
                System.out.println("====================================");
            }
        }*/

        // подсчёт среднего времения за одно подзадание
        long count = 1;
        for (TimeStat timeStat : statistics){
            long dif = timeStat.getClosedTime().getTime()-timeStat.getAssignedTime().getTime();
            long secs = TimeUnit.MILLISECONDS.toSeconds(dif);
            map1.put(count, secs / timeStat.getMicrotasks());
            count++;
        }

/*        map.entrySet().stream()
                .filter(i-> i.getValue().size() > 1)
                .forEach(i-> System.out.println("TaskID="+i.getKey()+"\t Count="+i.getValue().size()));

        map1.forEach((key, value) -> System.out.println("№=" + key + "\t Count=" + value));*/

        double result = 0.0;
        for (Double d : map1.values()){
            if (d > 300){
                count--;
                continue;
            }
            result += d;
        }
        System.out.println(result / count);

    }
}