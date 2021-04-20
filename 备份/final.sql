/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/4/20 21:43:12                           */
/*==============================================================*/



drop table if exists movie;

drop table if exists other;

drop table if exists ratings;



/*==============================================================*/
/* Table: movie                                                 */
/*==============================================================*/
create table movie
(
   movie_id             int not null auto_increment,
   title                varchar(100),
   country              varchar(20),
   category             text,
   download_url         text,
   introduce            text,
   language             varchar(20),
   main_actor           varchar(50),
   movie_time           varchar(10),
   publish_date         varchar(50),
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


alter table other add constraint FK_Reference_3 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table other add constraint FK_Reference_4 foreign key (movie_id)
      references movie (movie_id) on delete restrict on update restrict;

alter table ratings add constraint FK_Reference_1 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table ratings add constraint FK_Reference_2 foreign key (movie_id)
      references movie (movie_id) on delete restrict on update restrict;

