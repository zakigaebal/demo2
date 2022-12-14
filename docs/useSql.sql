-- 게시판 테이블 생성
CREATE TABLE BOARD (
                       bno serial primary key,
                       subject VARCHAR(100) ,
                       content VARCHAR(100),
                       writer VARCHAR(100) ,
                       reg_date timestamp);

-- 게시판 조회
select * from board;


-- 댓글 테이블 생성
-- 게시판 외래키로 댓글테이블과 물어서 게시판 삭제시 댓글도 삭제되게 만들었습니다.
create table comment(
                        cno serial primary key,
                        bno int not null,
                        reply_idx int,
                        content text not null,
                        writer varchar(20) not null,
                        reg_date timestamp not null,
                        CONSTRAINT fk_bno FOREIGN KEY(bno) REFERENCES "board"(bno) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 댓글 조회
SELECT * From comment;

-- 댓글 테이블 삭제
DROP TABLE IF EXISTS comment;


-- 삭제할때 쓰는 데이터 조회 쿼리
-- 자식댓글    부모댓글 존재시 삭제하기위해 조회
     with RECURSIVE comments AS
         (
         select
         *,
         "cno" as top_key,

         '' || cno as "keys"
         from comment
         where reply_idx = 0
         UNION

         select
         b.*,
         a.top_key,

         a.keys || ',' || b.cno as keys
         from comments a
         inner join comment b on a.cno = b.reply_idx
         )select * from comments where keys like '%${cno}%' order by keys