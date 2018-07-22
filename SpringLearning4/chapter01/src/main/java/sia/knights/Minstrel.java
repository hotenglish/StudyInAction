package sia.knights;

import java.io.PrintStream;

public class Minstrel {

    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singleBeforeQuest(){
        stream.println("Fa la la, the knights is so brave!");
    }

    public void singleAfterQuest(){
        stream.println("Tee hee hee, the brave knights " +
                "did embark on a quest!");
    }
}
