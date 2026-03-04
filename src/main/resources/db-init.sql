-- DROP SCHEMA tutor;

CREATE SCHEMA tutor AUTHORIZATION postgres;

-- DROP SEQUENCE tutor.permissions_seq;

CREATE SEQUENCE tutor.permissions_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 50
	NO CYCLE;
-- DROP SEQUENCE tutor.roles_seq;

CREATE SEQUENCE tutor.roles_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 50
	NO CYCLE;
-- DROP SEQUENCE tutor.tutor_seq;

CREATE SEQUENCE tutor.tutor_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 50
	NO CYCLE;
-- DROP SEQUENCE tutor.users_seq;

CREATE SEQUENCE tutor.users_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 50
	NO CYCLE;-- tutor.permissions definition

-- Drop table

-- DROP TABLE tutor.permissions;

CREATE TABLE tutor.permissions (
                                   id int8 DEFAULT nextval('tutor.permissions_seq'::regclass) NOT NULL,
                                   "name" varchar(100) NOT NULL,
                                   description varchar(255) NULL,
                                   status int8 DEFAULT 0 NOT NULL,
                                   deleted_at timestamp NULL,
                                   created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                   created_by int8 NULL,
                                   updated_by int8 NULL,
                                   CONSTRAINT permissions_name_key UNIQUE (name),
                                   CONSTRAINT permissions_pkey PRIMARY KEY (id)
);


-- tutor.roles definition

-- Drop table

-- DROP TABLE tutor.roles;

CREATE TABLE tutor.roles (
                             id int8 DEFAULT nextval('tutor.roles_seq'::regclass) NOT NULL,
                             "name" varchar(50) NOT NULL,
                             description varchar(255) NULL,
                             status int8 DEFAULT 0 NOT NULL,
                             deleted_at timestamp NULL,
                             created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             created_by int8 NULL,
                             updated_by int8 NULL,
                             CONSTRAINT roles_name_key UNIQUE (name),
                             CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- tutor.user_profile definition

-- Drop table

-- DROP TABLE tutor.user_profile;

CREATE TABLE tutor.user_profile (
                                    id int8 DEFAULT nextval('tutor.users_seq'::regclass) NOT NULL,
                                    email varchar(255) NOT NULL,
                                    pass_word varchar(255) NOT NULL,
                                    first_name varchar(100) NOT NULL,
                                    last_name varchar(100) NOT NULL,
                                    phone_number varchar(20) NOT NULL,
                                    status int8 DEFAULT 0 NOT NULL,
                                    deleted_at timestamp NULL,
                                    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                    updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                    created_by int8 NULL,
                                    updated_by int8 NULL,
                                    CONSTRAINT user_profile_email_key UNIQUE (email),
                                    CONSTRAINT user_profile_pkey PRIMARY KEY (id)
);


-- tutor.role_permissions definition

-- Drop table

-- DROP TABLE tutor.role_permissions;

CREATE TABLE tutor.role_permissions (
                                        role_id int8 NOT NULL,
                                        permission_id int8 NOT NULL,
                                        created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                        deleted_at timestamp NULL,
                                        created_by int8 NULL,
                                        CONSTRAINT role_permissions_pk PRIMARY KEY (role_id, permission_id),
                                        CONSTRAINT role_permissions_permission_fk FOREIGN KEY (permission_id) REFERENCES tutor.permissions(id) ON DELETE CASCADE,
                                        CONSTRAINT role_permissions_role_fk FOREIGN KEY (role_id) REFERENCES tutor.roles(id) ON DELETE CASCADE
);


-- tutor.tutors definition

-- Drop table

-- DROP TABLE tutor.tutors;

CREATE TABLE tutor.tutors (
                              id int8 DEFAULT nextval('tutor.tutor_seq'::regclass) NOT NULL,
                              user_id int8 NOT NULL,
                              bio text NULL,
                              experience_years int4 NULL,
                              hourly_rate numeric(10, 2) NULL,
                              accepts_one_to_one bool DEFAULT true NULL,
                              accepts_one_to_many bool DEFAULT false NULL,
                              rating numeric(3, 2) DEFAULT 0 NULL,
                              total_reviews int4 DEFAULT 0 NULL,
                              status int8 DEFAULT 0 NOT NULL,
                              deleted_at timestamp NULL,
                              created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              created_by int8 NULL,
                              updated_by int8 NULL,
                              CONSTRAINT tutors_pkey PRIMARY KEY (id),
                              CONSTRAINT tutors_user_id_key UNIQUE (user_id),
                              CONSTRAINT fk_tutor_user FOREIGN KEY (user_id) REFERENCES tutor.user_profile(id) ON DELETE CASCADE
);


-- tutor.user_roles definition

-- Drop table

-- DROP TABLE tutor.user_roles;

CREATE TABLE tutor.user_roles (
                                  user_id int8 NOT NULL,
                                  role_id int8 NOT NULL,
                                  created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                  deleted_at timestamp NULL,
                                  created_by int8 NULL,
                                  CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
                                  CONSTRAINT user_roles_role_fk FOREIGN KEY (role_id) REFERENCES tutor.roles(id) ON DELETE CASCADE,
                                  CONSTRAINT user_roles_user_fk FOREIGN KEY (user_id) REFERENCES tutor.user_profile(id) ON DELETE CASCADE
);