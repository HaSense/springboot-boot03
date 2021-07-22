package com.zerock.domain;

import org.springframework.data.repository.CrudRepository;

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

    //bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);


}
