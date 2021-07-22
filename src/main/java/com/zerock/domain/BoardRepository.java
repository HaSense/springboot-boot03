package com.zerock.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    //제목검색 기능 만들기
    public List<Board> findBoardByTitle(String title);

    //작성자 검색기능 만들기
    public Collection<Board> findByWriter(String writer);

    //작성자에 대한 like % 키워드
    public Collection<Board> findByWriterContaining(String writer);
}
