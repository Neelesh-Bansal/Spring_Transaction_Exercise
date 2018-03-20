package Q6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionLogs {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRecord(String fromUser, String toUser, int amount){
        String query="insert into account_transaction (fromuser,touser,balance) values(?,?)";
        jdbcTemplate.update(query,new Object[]{fromUser,toUser,amount});
        System.out.println("Record Add Successfully");
    }

}
