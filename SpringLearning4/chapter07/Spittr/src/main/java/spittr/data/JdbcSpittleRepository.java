package spittr.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.Spittle;
import spittr.web.DuplicateSpittleException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSpittleRepository implements SpittleRepository {

    private JdbcOperations jdbc;

    @Autowired
    public JdbcSpittleRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Spittle> findRecentSpittles() {
        return jdbc.query("select id, message, created_at, latitude, longitude" +
                " from Spittle" +
                " order by created_at desc limit 20", new SpittleRowMapper());
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return jdbc.query("select id, message, created_at, latitude, longitude" +
                " from Spittle" +
                " where id < ?" +
                " order by created_at desc limit 20", new SpittleRowMapper(), max);
    }

    @Override
    public Spittle findOne(Long id) {
        Spittle spittle = null;
        try {
            spittle = jdbc.queryForObject("select id, message, created_at, latitude, longitude" +
                            " from Spittle" +
                            " where id = ?",
                    new SpittleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
        }
        return spittle;
    }

    @Override
    public void save(Spittle spittle) {
        try{
            jdbc.update(
                    "insert into Spittle (id, message, created_at, latitude, longitude)" +
                            " values (?, ?, ?, ?)",
                    spittle.getId(),
                    spittle.getMessage(),
                    spittle.getTime(),
                    spittle.getLatitude(),
                    spittle.getLongitude()
            );
        }catch (DataAccessException ae){
            throw new DuplicateSpittleException();
        }
    }

    private static class SpittleRowMapper implements RowMapper<Spittle> {

        @Override
        public Spittle mapRow(ResultSet rs, int i) throws SQLException {
            return new Spittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("created_at"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"));
        }
    }

}
