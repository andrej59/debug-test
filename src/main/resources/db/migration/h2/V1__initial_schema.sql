/*==============================================================*/
/* Table: person                                                */
/*==============================================================*/
CREATE TABLE person (
   id                   BIGINT               NOT NULL,
   last_name            VARCHAR(128)         NOT NULL,
   first_name           VARCHAR(128)         NOT NULL,
   middle_name          VARCHAR(128),
   birth_date           DATE                 NOT NULL,
   email                VARCHAR(256)         NOT NULL,
   dt_create            DATE   DEFAULT NOW() NOT NULL,
   dt_update            DATE,
   constraint pk_person primary key (ID),
   constraint uk_person_email unique (email)
);

COMMENT ON TABLE person IS 'Физическое лицо';

COMMENT ON COLUMN person.id IS 'Идентификатор';

COMMENT ON COLUMN person.last_name IS 'Фамилия';

COMMENT ON COLUMN person.first_name IS 'Имя';

COMMENT ON COLUMN person.middle_name IS 'Отчество';

COMMENT ON COLUMN person.birth_date IS 'Дата рождения';

COMMENT ON COLUMN person.email IS 'Адрес электронной почты';

COMMENT ON COLUMN person.dt_create IS 'Дата и время создания';

COMMENT ON COLUMN person.dt_update IS 'Дата и время обновления';



