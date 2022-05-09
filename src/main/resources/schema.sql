DROP TABLE IF EXISTS kusers CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS accounts_history CASCADE;

CREATE TABLE kakao_users (
  user_id       bigint NOT NULL, --사용자 ID
  name          varchar(20) NOT NULL,           --사용자명
  age           int NOT NULL,                   --사용자 나이
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 가입일
  PRIMARY KEY (user_id)
);

CREATE TABLE accounts(
  seq           bigint NOT NULL AUTO_INCREMENT, -- PK
  user_id       bigint NOT NULL, --사용자 ID
  user_account  varchar(50) NOT NULL,           --계좌번호
  PRIMARY KEY (seq)
);

CREATE TABLE accounts_history (
  seq           bigint NOT NULL AUTO_INCREMENT, -- PK
  user_account  varchar(50) NOT NULL,           --계좌번호
  is_tsmt       varchar(10) NOT NULL,               --입출금여부
  amount        float NOT NULL,                 --입금액
  create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),           --입금일
  PRIMARY KEY (SEQ)
);
