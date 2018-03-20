package Q3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class AccountService {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void TransferAmount(String fromUser, String toUser, int amount)
    {
        TransactionDefinition definition=new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(definition);
        try
        {
            String query = "update Account set balance=balance-? where name=?";
            jdbcTemplate.update(query, new Object[]{amount, fromUser});
        }
        catch (Exception ex)
        {
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
        updateBalance(toUser,amount);
        System.out.println("Transfered Amount-"+amount+" Successfully");
    }

    public void updateBalance(String user,int recieveBalance){
        TransactionDefinition definition=new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(definition);
        try{
            String query="update Account set balance=balance+? where name=?";
            jdbcTemplate.update(query,new Object[]{recieveBalance,user});
        }
        catch (Exception ex){
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
        System.out.println("amount recieved");
    }

    }
