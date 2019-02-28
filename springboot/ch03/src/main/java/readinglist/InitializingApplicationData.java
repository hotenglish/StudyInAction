package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializingApplicationData {

    private static final String AMOUNT_KEY = "amount";
    private static final String CHARGEDESCRIPTION_KEY = "chargeDescription";
    private static final String CHARGECODE_KEY = "chargeCode";
    private static List<String> keys = new ArrayList<String>();

    private JdbcOperations jdbc;

    @Autowired
    public InitializingApplicationData(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    static {
        keys.add(AMOUNT_KEY);
        keys.add(CHARGEDESCRIPTION_KEY);
        keys.add(CHARGECODE_KEY);
    }

    public static void main(String args[]) throws Exception {
        removeSpecifiedStatisticsRules("payment", keys);
        addSpecifiedStatisticsRules("payment", keys);
    }

    public static void toInitializingApplicationData(ReaderRepository readerRepository) {
/*        Reader reader = new Reader();
        reader.setFullname("laimaosheng");
        reader.setPassword("good");
        reader.setUsername("maosheng");
        readerRepository.save(reader);*/
    }

    public int insertData() {
        int count = jdbc.update("INSERT INTO READER (USERNAME, PASSWORD, FULLNAME) VALUES ('walt', 'marceline', 'Walt Disney')");
        //int count = jdbc.update("INSERT INTO READER (USERNAME, PASSWORD, FULLNAME) VALUES ('walt', 'marceline', 'Walt Disney')");
        return count;
    }

    /**
     * @param keys
     */
    public static void removeSpecifiedStatisticsRules(String module, List<String> keys) {
        if (keys == null || keys.size() == 0) {
            return;
        }
        for (String key : keys) {
            System.out.println("DELETE FROM STATISTICSRULES WHERE RULENAME = '" + module + "_" + key + "_filter';");
        }
    }

    /**
     * @param keys
     */
    public static void addSpecifiedStatisticsRules(String module, List<String> keys) {
        if (keys == null || keys.size() == 0) {
            return;
        }
        for (String key : keys) {
            System.out.println("INSERT INTO STATISTICSRULES VALUES('" + module + "_" + key + "_filter', '*', '" + key + "', 'Y', NULL);");
        }
    }
}
