package workshops.testingil.dirtytests.newone;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {

    String collected = "";
    Map<String,String> answers = Stream.of(new String[][] {
        {"", "0"},
        {"1", "1"},
        {"1C", "0"},
        {"01", "1"},
        {"5**", "5"}

    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    Map<String,String> slow_answers = Stream.of(new String[][] {
            {"123", "123"},
            {"5+C2","2"},
            {"1+2*3=","7"},
            {"5+2C","0"},
            {"1+3=6","6"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public String getDisplay() {
        if (slow_answers.containsKey(collected)){
            try {
                Thread.sleep((3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return slow_answers.get(collected);
        }
        return answers.get(collected);
    }

    public void press(String key) {
        collected +=key;
    }

    public void pressAll(String keys) {
        keys.chars()
                .forEach(ch -> press(String.valueOf((char)ch)));
    }
}
