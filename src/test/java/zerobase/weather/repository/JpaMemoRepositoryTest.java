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
@Transactional
class JpaMemoRepositoryTest {
    @Autowired
    private JpaMemoRepository jpaMemoRepository;

    @Test
        void insertMemoTest(){
            //given
            Memo newMemo = new Memo(10, "test memo");
            //when
            jpaMemoRepository.save(newMemo);
            List<Memo> memoList = jpaMemoRepository.findAll();
            //then
            assertTrue(!memoList.isEmpty());
    }


    @Test
        void findByIdTest(){
            //given
            Memo newMemo = new Memo(2, "jpa");
            //when
            Memo memo = jpaMemoRepository.save(newMemo);
            System.out.println(memo.getId());
            /*  @GeneratedValue(strategy = GenerationType.IDENTITY) 여서
                spring boot 에서 생성되는 키가 아닌 DB 에서 생성되는 키를 가져온다
            */

            Optional<Memo> result = jpaMemoRepository.findById(memo.getId());

            //then
            assertEquals("jpa", result.get().getText());
    }
}