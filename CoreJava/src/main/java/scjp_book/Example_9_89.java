package scjp_book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Example_9_89 {

    public static void main(String args[]) {
        String[] array = {"123", "12345678", "1", "12", "1234567890"};
        List<String> list = Arrays.asList(array);
        Collection<String> resultList = getLongWords(list);
        for(String str:resultList){
            System.out.println(str);
        }
    }

    //public static <E extends CharSequence> Collection<? extends CharSequence> getLongWords(Collection<E> coll)
    //public static <E extends CharSequence> List<E> getLongWords(Collection<E> coll)
    //public static Collection<E extends CharSequence> getLongWords(Collection<E> coll)
    //public static List<CharSequence> getLongWords(Collection<CharSequence> coll)
    //public static List<? extends CharSequence> getLongWords(Collection<? extends CharSequence> coll)
    public static <E extends CharSequence> Collection<E> getLongWords(Collection<E> coll)
    //public static <E super CharSequence> Collection<E> getLongWords(Collection<E> coll)
    {
        Collection<E> longWords = new ArrayList<E>();
        for (E word : coll) {
            if (word.length() > 6) {
                longWords.add(word);
            }
        }
        return longWords;
    }

}
