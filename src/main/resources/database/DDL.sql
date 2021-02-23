create table courses
(
    id int not null
        primary key,
    description varchar(255) not null,
    name varchar(255) not null
);

create table documents
(
    id int not null
        primary key,
    description varchar(255) not null,
    name varchar(255) not null,
    course_id int null,
    constraint FKsuenl009odnidqiyaao22gw0s
        foreign key (course_id) references courses (id)
);

create table hibernate_sequence
(
    next_val bigint null
);

create table tasks
(
    id int not null
        primary key,
    content varchar(255) not null,
    summary varchar(255) not null,
    course_id int null,
    constraint FKopldg47bgaarlampi2f6wees3
        foreign key (course_id) references courses (id)
);

create table ratings
(
    id int not null
        primary key,
    value int not null,
    course_id int null,
    task_id int null,
    constraint FK6ilw70nrg8g7mdw8pywnveptn
        foreign key (task_id) references tasks (id),
    constraint FKbegfifgmkbhd1pj5vitred35g
        foreign key (course_id) references courses (id)
);

create table user_groups
(
    id int not null
        primary key,
    description varchar(255) not null,
    name varchar(255) not null,
    constraint UK_eg8568m4xp44f9n0gi07l9afa
        unique (name)
);

create table posts
(
    id int not null
        primary key,
    content varchar(255) not null,
    sequence_number int not null,
    summary varchar(255) not null,
    group_id int null,
    constraint FKsswnflfjcm7n5t357nhloykah
        foreign key (group_id) references user_groups (id)
);

create table users
(
    id int not null
        primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    uid varchar(255) not null,
    constraint UK_efqukogbk7i0poucwoy2qie74
        unique (uid)
);

create table courses_users
(
    course_model_id int not null,
    users_id int not null,
    primary key (course_model_id, users_id),
    constraint FKii2ixvxjl10uy8lc3n43yomvr
        foreign key (users_id) references users (id),
    constraint FKm5r7ws7so87c77fh4jtgww6m7
        foreign key (course_model_id) references courses (id)
);

create table user_groups_users
(
    group_model_id int not null,
    users_id int not null,
    primary key (group_model_id, users_id),
    constraint FKkjoqls6u51vg8r6wg28jaf1wj
        foreign key (users_id) references users (id),
    constraint FKq5khbqwyir1axfyj2q3f5s06i
        foreign key (group_model_id) references user_groups (id)
);

create table users_courses
(
    user_model_id int not null,
    courses_id int not null,
    primary key (user_model_id, courses_id),
    constraint FKr2ck9fshfa2j41y554fxdgqn5
        foreign key (courses_id) references courses (id),
    constraint FKtj8inffomsymc2j9ivfvyyvhq
        foreign key (user_model_id) references users (id)
);

create table users_groups
(
    user_model_id int not null,
    groups_id int not null,
    primary key (user_model_id, groups_id),
    constraint FK6yjp39u6i7m6o9a1yn9pp8xqk
        foreign key (user_model_id) references users (id),
    constraint FKbeo8kwu4ixgcr3nk17cwjjfmb
        foreign key (groups_id) references user_groups (id)
);

