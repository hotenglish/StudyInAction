package spittr.data;

import spittr.Spittle;

import java.util.List;

public interface SpittleRepository {

    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(Long id);

    void save(Spittle spittle);

}
