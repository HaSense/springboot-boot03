package com.zerock;

import com.zerock.domain.Board;
import com.zerock.domain.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
class Boot03ApplicationTests {

    @Autowired
    private BoardRepository repo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle("제목.." + i);
            board.setContent("내용...." + i + "채우기 ");
            board.setWriter(("user0" + (i % 10)));
            repo.save(board);
        }
    }

    //제목 검색 기능 테스트
    @Test
    public void testByTitle() {
        //Java8
        repo.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));
    }

    //작성자 검색기능 테스트
    @Test
    public void testByWriter() {
        Collection<Board> result = repo.findByWriter("user00");

        result.forEach(
                board -> System.out.println(board)
        );
    }

    //확장된 작성자 이름에 검색
    @Test
    public void testByWriterContaning() {
        Collection<Board> results = repo.findByWriterContaining("05");

        results.forEach(board -> System.out.println(board));
    }
    //제목과 부등호 검색
    @Test
    public void testByTitleAndBno() {
        Collection<Board> results =
                repo.findByTitleContainingAndBnoGreaterThan("5", 50L);

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testBnoOrderBy() {
        Collection<Board> results =
                repo.findByBnoGreaterThanOrderByBnoDesc(90L);
        results.forEach(board -> System.out.println(board));
    }
}
