package workshops.testingil.dirtytests.solutions.e10.builder.helpers;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SequenceMessageBuilder {
    public String version="1.0";
    public String sequence;
    public boolean resetOnError=true;
    private boolean fromScratch = false;

    public SequenceMessage build(){
        SequenceMessage message = new SequenceMessage();
        message.version = this.version;
        if (fromScratch){
            sequence = "C" + sequence;
        }
        message.addSequence(sequence);
        message.resetOnError = resetOnError;
        return message;
    }

    public SequenceMessageBuilder withSequence(String sequence){
        this.sequence = sequence;
        return this;
    }

    public SequenceMessageBuilder startFromScratch() {
        this.fromScratch = true;
        return this;
    }

    public static List<Pair<SequenceMessage,String>> from_file(String path) throws Exception {
        List<Pair<SequenceMessage,String>> message_list = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path));

        lines.forEach(line -> {
            String[] values = line.replace("\"", "").split(",");
            var input = values[0];
            var expected = values[1];

            SequenceMessageBuilder builder = new SequenceMessageBuilder()
                    .withSequence(input)
                    .startFromScratch();
            message_list.add(new ImmutablePair<>(builder.build(), expected));
        });
        return message_list;
    }
}