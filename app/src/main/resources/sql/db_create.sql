DROP TABLE IF EXIST person;
CREATE TABLE person (
  hash_id bigint,
  gender char(1),
  name varchar(127),
  surname varchar(127),
  father_name varchar(127),
  mother_name varchar(127),
  birth_place varchar(127),
  address varchar(127),
  serie_no varchar(127),
  marital_status varchar(127),
  live_status varchar(127),
  PRIMARY KEY(hash_id)
);
DROP TABLE IF EXIST owner;
CREATE TABLE owner (
  hash_id BIGINT REFERENCES person(hash_id),
  mail VARCHAR(127),
  pdf_json TEXT,
  PRIMARY KEY (hash_id)
);
DROP TABLE IF EXIST user_relative;
CREATE TABLE user_relative(
  user_id BIGINT REFERENCES owner(hash_id),
  relative_id BIGINT REFERENCES person(hash_id),
  relation VARCHAR(255),
  PRIMARY KEY (user_id, relative_id)
);