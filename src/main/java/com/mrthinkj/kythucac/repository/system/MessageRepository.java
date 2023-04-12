package com.mrthinkj.kythucac.repository.system;

import com.mrthinkj.kythucac.model.system.Message;
import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query(value = "SELECT m.*\n" +
            "FROM message m\n" +
            "         JOIN (\n" +
            "    SELECT\n" +
            "        CASE\n" +
            "            WHEN from_account = ?1 THEN to_account\n" +
            "            ELSE from_account\n" +
            "            END AS other_account,\n" +
            "        MAX(id) AS max_id\n" +
            "    FROM message\n" +
            "    WHERE from_account = '1' OR to_account = '1'\n" +
            "    GROUP BY other_account\n" +
            ") sub\n" +
            "              ON (m.from_account = sub.other_account OR m.to_account = sub.other_account)\n" +
            "                  AND m.id = sub.max_id", nativeQuery = true)
    List<Message> findNewestMessageListByAccount(Integer accountId);

    List<Message> findByFromAccountAndToAccountOrToAccountAndFromAccountOrderBySendDateAsc(Account fromAccount1, Account toAccount1, Account fromAccount2, Account toAccount2);

    @Query(value = "select * from message where (from_account = ?1 and to_account = ?2) or (from_account = ?2 and to_account = ?1) order by send_date desc limit 1", nativeQuery = true)
    Message findLastMessageBetweenTwoAccount(Integer fromAccount1, Integer toAccount1, Integer fromAccount2, Integer toAccount2);

    @Query(value = "select * from message where seen = false and to_account = ?1 limit 1", nativeQuery = true)
    Message findMessageUnseenByAccount(Integer accountId);
}
