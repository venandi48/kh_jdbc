-------------------------------------------------------
-- 관리자계정
-------------------------------------------------------
-- student계정 생성
-- C##접두어 사용안함
alter session set "_oracle_script" = true;

create user student
identified by student
default tablespace users;

grant connect, resource to student;

alter user student quota unlimited on users;


-------------------------------------------------------
-- student계정
-------------------------------------------------------
-- member테이블 생성
create table member(
    id varchar2(20),
    name varchar2(100) not null,
    gender char(1),
    birthday date,
    email varchar2(500),
    address varchar2(1000),
    reg_date timestamp default systimestamp,
    constraint pk_member_id primary key(id),
    constraint uq_member_email unique(email),
    constraint ch_member_gender check(gender in ('M', 'F'))
);
-- drop table member;

insert into member values( 'honggd', '홍길동', 'M', '1990-09-09', 'honggd@naver.com', '서울시 강남구 테헤란로 123', default);
insert into member values( 'sinsa', '신사임당', 'F', '1954-10-19', 'sinsa@naver.com', '경기도 구리시 소동 43', default);
insert into member values( 'qwerty', '꾸어띠', 'M', '2005-01-09', 'qwerty@gmail.com', '서울시 중구', default);
commit;

select * from member;
insert into member values( 'magd', '마길동', 'M', '1990-08-05', 'magd@naver.com', '서울시 강서구 목동', default);
insert into member values( 'yoogs', '유관순', 'F', '1990-03-01', 'yoogs@naver.com', '서울시 서초구 방배동', default);
-- java.sql.Data <---> date jdbc 연결 시 년월일 정보만 전송(시분초 유실)
-- java.sql.Timestmp <--->
select sysdate, systimestamp from dual;


-------------------------------------------------------

create table member_del
as
select member.*, sysdate del_date from member
where 1 = 0;
-- drop table member_del;

select * from member_del;
desc member_del;

-- 제약조건 설정
alter table
    member_del
add constraint ch_member_del_gender check(gender in ('M', 'F'))
modify del_date default sysdate;




--같은 회원이 같은 아이디로 가입/탈퇴, 다른 메일로 가입/탈퇴하기를 반복할수있으니 생략
--alter table
--    member_del
--add constraint pk_member_del_id primary key(id);

--alter table
--    member_del
--add constraint uq_member_del_email unique(email);

-- 트리거 생성
create or replace trigger trig_member_del
    before
    delete on member
    for each row
begin
    insert into member_del
    values(:old.id, :old.name, :old.gender, :old.birthday, :old.email,
            :old.address, :old.reg_date, default);
end;
/