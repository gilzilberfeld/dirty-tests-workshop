package workshops.testingil.dirtytests.newone;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {

    String collected = "";

    Map <String, String> ops = Stream.of(new String[][]{
            {"%2B", "+"},
            {"%2F", "/"},
            {"%3D", "="}

    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    Map<String, String> answers = Stream.of(new String[][]{
            {"", "0"},
            {"1", "1"},
            {"1C", "0"},
            {"01", "1"},
            {"5**", "5"},
            {"1+", "1"},
            {"0","0"},
            {"+2","2"},
            {"00","0"},
            {"1+3","3"},
            {"3-5=","-2"}

    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    Map<String, String> slow_answers = Stream.of(new String[][]{
            {"123", "123"},
            {"5+C2", "2"},
            {"1+2*3=", "7"},
            {"5+2C", "0"},
            {"1+3=6", "6"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public String getDisplay() {

        if (slow_answers.containsKey(collected)) {
            try {
                Thread.sleep((3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("----------------------- returned display " + slow_answers.get(collected)  + " -------------------");

            return slow_answers.get(collected);
        }
        System.out.println("----------------------- returned display " + answers.get(collected)  + " -------------------");
        return answers.get(collected);
    }

    public void press(String key) {
        System.out.println("----------------------- pressed " + key + " -------------------");
        if (ops.containsKey(key)) {
            collected += ops.get(key);
        }
        else
            collected += key;

    }

    public void pressAll(String keys) {
        keys.chars()
                .forEach(ch -> press(String.valueOf((char) ch)));
    }

    public void reset() {
        collected = "";
    }
}
