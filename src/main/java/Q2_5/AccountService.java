package Q2_5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountService {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    //create
    @Transactional
    public void AddUser(String user, int amount){
        String query="insert into Account (name,balance) values(?,?)";
        jdbcTemplate.update(query,new Object[]{user,amount});
        System.out.println("User Add Successfully");

    }

    @Transactional
    public void GetUserBalance(String user){
        String query="select balance from Account where name=?";
        int balance=jdbcTemplate.queryForObject(query,new Object[]{user},int.class);
        System.out.println("Current Balance : "+balance);
    }

    @Transactional(readOnly = true)
    public void DeleteUser(String user){
        String query="delete from Account where name=?";
        int result=jdbcTemplate.update(query,new Object[]{user});
        if(result==0){
            System.out.println("User Not Found");
        }
        else
        System.out.println("User Deleted Successfully");
    }

    @Transactional
    public void UpdateBalance(String user,int amount){
        String query="update Account set balance=? where name=?";
        jdbcTemplate.update(query,new Object[]{amount,user});
        System.out.println("User Balance Updated Successfully");
    }






}
