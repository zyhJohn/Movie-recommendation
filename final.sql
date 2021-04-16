/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/4/16 20:52:03                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists movie;

drop table if exists other;

drop table if exists ratings;

drop table if exists user;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin                varchar(10) not null,
   pwd                  varchar(10) not null,
   primary key (admin)
);

/*==============================================================*/
/* Table: movie                                                 */
/*==============================================================*/
create table movie
(
   movie_id             int not null auto_increment,
   title                varchar(50),
   title_cn             varchar(50),
   genres               text,
   year                 varchar(5),
   primary key (movie_id)
);

/*==============================================================*/
/* Table: other                                                 */
/*==============================================================*/
create table other
(
   user_id              int,
   movie_id             int,
   want                 bool,
   comment              text,
   timestamp            int not null
);

/*==============================================================*/
/* Table: ratings                                               */
/*==============================================================*/
create table ratings
(
   user_id              int,
   movie_id             int,
   rating               int not null,
   timestamp            int not null
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int not null auto_increment,
   user_no              varchar(20) not null,
   user_name            varchar(10) not null,
   user_pwd             varchar(20),
   user_mail            varchar(50),
   gender               varchar(1),
   age                  int,
   occupation           int,
   primary key (user_id)
);

alter table other add constraint FK_Reference_3 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table other add constraint FK_Reference_4 foreign key (movie_id)
      references movie (movie_id) on delete restrict on update restrict;

alter table ratings add constraint FK_Reference_1 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table ratings add constraint FK_Reference_2 foreign key (movie_id)
      references movie (movie_id) on delete restrict on update restrict;

