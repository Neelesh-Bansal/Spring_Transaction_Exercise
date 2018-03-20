package Q2_5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
        AccountService accountService = applicationContext.getBean(AccountService.class);
        //accountService.AddUser("Nakul",3000);
        //accountService.GetUserBalance("Nakul");
        accountService.DeleteUser("Neelesh");
        //accountService.UpdateBalance("Ankit",5000);
    }
}
