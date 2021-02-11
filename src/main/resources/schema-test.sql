drop table if exists project CASCADE; 
drop table if exists task CASCADE; 
create table project (id bigint generated by default as identity, project_name varchar(255), primary key (id));
create table task (id bigint generated by default as identity, completed boolean, deadline timestamp, task_name varchar(255), my_project_id bigint, primary key (id));
alter table task add constraint FKivhxj748qtaw7dfx1qhkk9ebp foreign key (my_project_id) references project on delete cascade;