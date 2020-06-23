--create user spring identified by java;
--grant resource, connect to spring;
--conn servlet/java;

drop table BOARD;
drop sequence BOARD_SEQ;
purge recyclebin;

create table BOARD(
   SEQ number constraint BOARD_PK primary key, 
   WRITER varchar2(15), 
   EMAIL varchar2(20),
   SUBJECT varchar2(20), 
   CONTENT varchar2(20), 
   RDATE date default SYSDATE
); 
create sequence BOARD_SEQ increment by 1 start with 1 nocache;

insert into BOARD values(BOARD_SEQ.nextval, '이민서', 'kim@hanmail.net','제목1', '내용1', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '김유민', 'lee@hanmail.net','제목2', '내용2', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '조건희', 'han@hanmail.net','제목3', '내용3', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '서지희', 'oh@hanmail.net','제목4', '내용4', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '안주현', 'chm@hanmail.net','제목5', '내용5', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '김형섭', 'kim@hanmail.net','제목6', '내용6', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '이현호', 'lee@hanmail.net','제목7', '내용7', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '정혜원', 'han@hanmail.net','제목8', '내용8', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '이준성', 'oh@hanmail.net','제목9', '내용9', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '이경빈', 'chm@hanmail.net','제목10', '내용10', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '한다솜', 'kim@hanmail.net','제목11', '내용11', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '이수빈', 'lee@hanmail.net','제목12', '내용12', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '배현철', 'han@hanmail.net','제목13', '내용13', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '윤희영', 'oh@hanmail.net','제목14', '내용14', SYSDATE);
insert into BOARD values(BOARD_SEQ.nextval, '황영걸', 'chm@hanmail.net','제목15', '내용15', SYSDATE);

commit;

select CONSTRAINT_NAME, CONSTRAINT_TYPE from user_constraints where TABLE_NAME='BOARD';
select * from BOARD order by SEQ desc;

