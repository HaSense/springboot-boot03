package com.zerock.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    //제목검색 기능 만들기
    public List<Board> findBoardByTitle(String title);

    //작성자 검색기능 만들기
    public Collection<Board> findByWriter(String writer);

    //작성자에 대한 like % 키워드 - 확장된 작성자 검색기능
    public Collection<Board> findByWriterContaining(String writer);

    //Or 조건의 처리
    public Collection<Board> findByTitleContainingOrContentContaining(
            String title, String content);

    //부등호처리 title LIKE % ? % AND BNO > ?
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(
            String keyword, Long num);

    // bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? ORDER BY bno DESC limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

//	@Query("select board from Board board where board.title like %?1% and board.bno > 0 order by board.bno desc")
//	public List<Board> findByTitle(String title);

    @Query("SELECT b FROM Board b WHERE b.title like %?1% and b.bno > 0 ORDER BY b.bno desc")
    public List<Board> findByTitle(String title);

    @Query("select board.bno, board.title, board.writer, board.regdate "
            + " from Board board where board.title like %?1% and board.bno > 0 order by board.bno desc")
    public List<Object[]> findByTitle2(String title);

    @Query("select board from Board board where board.bno > 0 order by board.bno desc")
    public List<Board> findBypage(Pageable pageable);

    @Query("SELECT b from Board b WHERE b.content like %:content%  and b.bno > 0 order by b.bno desc")
    public List<Board> findByContent(@Param("content") String content);
}
