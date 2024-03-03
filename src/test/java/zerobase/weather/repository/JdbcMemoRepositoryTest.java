package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // test 를 위해 DB의 내용을 변경하지않으면서 test 하기 위한 어노테이션
class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
        void insertMemoTest(){
            //given
            Memo newMemo = new Memo(2, "test memo 2");
            //when
            jdbcMemoRepository.save(newMemo);
            //then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(),"test memo 2");
    }

    @Test
        void findAllMemoTest(){
            //given
        List<Memo> memoList = jdbcMemoRepository.findAll();
            //when
            //then
        System.out.println(memoList);
        assertNotNull(memoList);
        }
}