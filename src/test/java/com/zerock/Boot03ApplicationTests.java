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

    @Test
    public void testByTitle() {
        //Java8
        repo.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriter() {
        Collection<Board> result = repo.findByWriter("user00");

        result.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByWriterContaning() {
        Collection<Board> results = repo.findByWriterContaining("05");

        results.forEach(board -> System.out.println(board));
    }
}
