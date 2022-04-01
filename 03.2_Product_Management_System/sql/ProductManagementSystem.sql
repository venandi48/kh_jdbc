-------------------------------------------------------
-- kh계정
-------------------------------------------------------

-- #1. 테이블 product_detail
create table product_detail(
    id varchar2(30),
    brand varchar2(50) not null,
    name varchar2(30) not null,
    price number not null,
    monitor_size number,
    os varchar2(100),
    storage number,
    reg_date date default sysdate,
    constraint pk_product_ditail_id primary key(id)
);
-- drop table product_detail;


-- #2. 테이블 product_stock
create table product_stock(
    no number not null,
    product_id varchar2(30),
    count number,
    status char(1),
    io_datetime timestamp default systimestamp,
    constraint pk_product_stock_no primary key(no),
    constraint fk_product_stock_product_id
        foreign key(product_id) references product_detail(id),
    constraint ck_product_stock_status check(status in ('I', 'O'))
);
-- drop table product_stock;


-- #3. 시퀀스
create sequence seq_product_stock_no;
-- drop sequence seq_product_stock_no;


-- #4. 트리거
-- 상품정보테이블에 레코드 추가/삭제 시 함께 추가/삭제
create or replace trigger trig_product_stock_insert_delete
    after
    insert or delete on product_detail
    for each row
begin
    if inserting then
        insert into product_stock
            values(seq_product_stock_no.nextval, :new.id, 0, 'I', default);
    elsif deleting then
        delete from product_stock
        where product_id = :old.id;
    end if;
end;
/
-- drop trigger trig_product_stock_insert_delete;

-----------------------------------------------------------------------

-- 초기 데이터 추가
insert into product_detail
values('14TD90P-GX30K', 'LG전자', '그램360', 1046990, 15, 'Windows10', 256, default);
insert into product_detail
values('X413EA-EB086', 'ASUS', '비보북', 609000, 14, 'Windows10', 256, default);
insert into product_detail
values('16ACH-R7-STORM', 'Lenovo', 'LEGION 5 Pro', 1699000, 16, 'Windows10', 512, default);
insert into product_detail
values('MVVK2KH/A', 'Apple', '맥북프로', 3705690, 16, 'macOS', 512, default);
commit;
-- rollback;


select * from product_detail;
select * from product_stock;
