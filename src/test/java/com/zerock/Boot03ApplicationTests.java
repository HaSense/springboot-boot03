package com.zerock;

import com.zerock.domain.Board;
import com.zerock.domain.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testBnoOrderByPaging() {
        //Pageable paging = new PageRequest(0, 10);

        //spring boot 2.0.0
        Pageable paging = PageRequest.of(0, 10);

        Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testBnoPagingSort() {

        //Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");

        //spring boot 2.0.0
        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = repo.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE: " + result.getSize());
        System.out.println("TOTAL PAGES: " + result.getTotalPages());
        System.out.println("TOTAL COUNT: " + result.getTotalElements());
        System.out.println("NEXT: " + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(board -> System.out.println(board));

    }

    @Test
    public void testByTitle2() {
        repo.findByTitle("17").forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitle17() {
        repo.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByPaging() {
        //Pageable pageable = new PageRequest(0, 10);

        //new PageRequest --> PageRequest.of
        Pageable pageable = PageRequest.of(0, 10);
        repo.findBypage(pageable).forEach(board -> System.out.println(board));
    }
}
