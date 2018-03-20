package Q4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class AccountService {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void TransferAmount(String fromUser, String toUser, int amount)
    {
            String query = "update Account set balance=balance-? where name=?";
            jdbcTemplate.update(query, new Object[]{amount, fromUser});
            System.out.println("done");


        updateBalance(toUser,amount);
        System.out.println("Transfered Amount-"+amount+" Successfully");
    }

    @Transactional
    public void updateBalance(String user,int recieveBalance){


            String query="update Account set balance=balance+? where name=?";
            jdbcTemplate.update(query,new Object[]{recieveBalance,user});

        System.out.println("amount recieved");
    }

    }
