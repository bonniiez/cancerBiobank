
create table authors
(au_id char(11) not null,
 au_lname varchar(40) not null,
 au_fname varchar(20) not null,
 phone char(12) null,
 address varchar(40) null,
 city varchar(20) null,
 state char(2) null,
 zip char(5) null,
  primary key (au_id));

insert into authors values('409-56-7008', 'Bennet', 'Abraham',
                           '415 658-9932', '6223 Bateman St.', 'Berkeley', 'CA', '94705');
insert into authors values ('213-46-8915', 'Green', 'Marjorie',
                            '415 986-7020', '309 63rd St. #411', 'Oakland', 'CA', '94618');
insert into authors values('238-95-7766', 'Carson', 'Cheryl',
                           '415 548-7723', '589 Darwin Ln.', 'Berkeley', 'CA', '94705');


