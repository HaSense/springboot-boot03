package com.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name="table_boards")
public class Board {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)   //MySQL에서 Auto_Increment
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ") //오라클에서 Sequence 사용
    //sequenceName-->시퀀스 생성자 이름 name-->시퀀스 이름
    @SequenceGenerator(sequenceName = "BOARD_SEQ", allocationSize = 1, name="BOARD_SEQ")
    private Long bno;

    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regdate;      //LocalDateTime
    @UpdateTimestamp
    private Timestamp updatedate;   //LocalDateTime
}
