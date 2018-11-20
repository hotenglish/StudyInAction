package spittr.data;

import spittr.Spittle;
import java.util.List;

public interface SpittleRepository {

    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    Spittle save(Spittle spittle);

    void deleteOne(long id);

    void update(Spittle spittle);

}
