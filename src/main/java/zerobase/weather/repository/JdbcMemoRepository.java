package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository

public class JdbcMemoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMemoRepository(DataSource dataSource) {
        /*
               DataSource -> application.properties 에 담긴 DB 정보
               @Autowired 를 통해 가져옴
         */
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Memo save(Memo memo){
        String sql = "insert into memo values(?,?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }

    public List<Memo> findAll(){
        String sql = "select * from memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }

    public Optional<Memo> findById(int id){
        String sql = "select * from memo where id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    private RowMapper<Memo> memoRowMapper(){
        /*
        * DB 에서 Select 해서 가져온 데이터는 ResultSet 이라는 형식에 따름
        * ex) {id: 1, text: "text memo"}
        * -->RowMapper 는 ResultSet 을 memo 라는 객체에 맞게 맵핑
        * */

        return ((rs, rowNum) ->
                new Memo(
                        rs.getInt("id"),
                        rs.getString("text")
                        )
                );
    }
}