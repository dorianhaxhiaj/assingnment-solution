create table post (
                      id bigint auto_increment  primary key,
                      title varchar(128),
                      content varchar(128),
                      creationDate TIMESTAMP
);