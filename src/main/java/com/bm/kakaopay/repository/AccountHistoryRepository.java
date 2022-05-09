package com.bm.kakaopay.repository;

import com.bm.kakaopay.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
//    List<AccountHistory> findAllByUserAccountIn(List<String> userAccount);

    List<AccountHistory> findAllByUserAccount(String userAccount);

    @Query(value =
            "SELECT d.age                    AS age_group,\n" +
                    "       Round(d.avg + e.avg , 2) AS amount\n" +
                    "FROM  (\n" +
                    "                SELECT   Floor(age/10) * 10    AS age,\n" +
                    "                         Count(*)              AS total,\n" +
                    "                         Round(Avg(amount), 2) AS avg\n" +
                    "                FROM     (\n" +
                    "                                  SELECT   a.user_id,\n" +
                    "                                           a.user_account,\n" +
                    "                                           b.amount,\n" +
                    "                                           c.name,\n" +
                    "                                           c.age\n" +
                    "                                  FROM     accounts a,\n" +
                    "                                           accounts_history b,\n" +
                    "                                           kakao_users c\n" +
                    "                                  WHERE    a.user_id = c.user_id\n" +
                    "                                  AND      a.user_account=b.user_account\n" +
                    "                                  AND      b.is_tsmt='Y'\n" +
                    "                                  ORDER BY user_id)\n" +
                    "                GROUP BY age) d\n" +
                    "JOIN\n" +
                    "       (\n" +
                    "                SELECT   Floor(age/10) * 10         AS age,\n" +
                    "                         Count(*)                   AS total,\n" +
                    "                         Round(Avg(amount), 2) * -1 AS avg\n" +
                    "                FROM     (\n" +
                    "                                  SELECT   a.user_id,\n" +
                    "                                           a.user_account,\n" +
                    "                                           b.amount,\n" +
                    "                                           c.name,\n" +
                    "                                           c.age\n" +
                    "                                  FROM     accounts a,\n" +
                    "                                           accounts_history b,\n" +
                    "                                           kakao_users c\n" +
                    "                                  WHERE    a.user_id = c.user_id\n" +
                    "                                  AND      a.user_account=b.user_account\n" +
                    "                                  AND      b.is_tsmt='N'\n" +
                    "                                  ORDER BY user_id)\n" +
                    "                GROUP BY age) e\n" +
                    "where  e.age = d.age",
            nativeQuery = true)
    List<Object[]> findAgeGroupAmount();

    @Query(value =
            "SELECT a.amount - b.amount AS TOTAL\n" +
                    "FROM   (SELECT Sum(amount) AS AMOUNT\n" +
                    "        FROM   accounts_history\n" +
                    "        WHERE  Year(create_at) = :year\n" +
                    "               AND is_tsmt = 'Y') a,\n" +
                    "       (SELECT Sum(amount) AS AMOUNT\n" +
                    "        FROM   accounts_history\n" +
                    "        WHERE  Year(create_at) = :year\n" +
                    "               AND is_tsmt = 'N') b",
            nativeQuery = true)
    Optional<Float> findYearTotalAmount(@Param(value="year")String year);


    @Query(value =
            "SELECT e.user_id,\n" +
                    "       f.name,\n" +
                    "       e.deposit\n" +
                    "FROM   (SELECT c.user_id,\n" +
                    "               ( c.amount - d.amount ) AS deposit\n" +
                    "        FROM   (SELECT Sum(amount) AS amount,\n" +
                    "                       b.user_id\n" +
                    "                FROM   (SELECT user_account,\n" +
                    "                               amount\n" +
                    "                        FROM   accounts_history\n" +
                    "                        WHERE  is_tsmt = 'Y'\n" +
                    "                               AND Year(create_at) BETWEEN :start_year AND :end_year) a,\n" +
                    "                       accounts b\n" +
                    "                WHERE  a.user_account = b.user_account\n" +
                    "                GROUP  BY b.user_id) c,\n" +
                    "               (SELECT Sum(amount) AS amount,\n" +
                    "                       b.user_id\n" +
                    "                FROM   (SELECT user_account,\n" +
                    "                               amount\n" +
                    "                        FROM   accounts_history\n" +
                    "                        WHERE  is_tsmt = 'N'\n" +
                    "                               AND Year(create_at) BETWEEN :start_year AND :end_year) a,\n" +
                    "                       accounts b\n" +
                    "                WHERE  a.user_account = b.user_account\n" +
                    "                GROUP  BY b.user_id) d\n" +
                    "        WHERE  c.user_id = d.user_id) e,\n" +
                    "       kakao_users f\n" +
                    "WHERE  e.user_id = f.user_id\n" +
                    "ORDER  BY deposit DESC",
            nativeQuery = true)
    List<Object[]> timeUserDesc(@Param(value="start_year")String startYear, @Param(value="end_year")String endYear);
}
