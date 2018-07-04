package test.api;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AccountList {
	
	  private static final CopyOnWriteArrayList<Account> cList = new CopyOnWriteArrayList<>();

	  static {
	    // Create list of Accounts
	    cList.add(
	        new Account.AccountBuilder().id()
	        .firstName("AAAAA")
	        .lastName("111111")
	        .balance(100.00)
	        .ccy("GBP")
	        .build()
	    );

	    cList.add(
	        new Account.AccountBuilder().id()
	        .firstName("BBBBB")
	        .lastName("22222")
	        .balance(200.00)
	        .ccy("GBP")
	        .build()
	    );

	    cList.add(
	        new Account.AccountBuilder().id()
	        .firstName("CCCCC")
	        .lastName("33333")
	        .balance(300.00)
	        .ccy("GBP")
	        .build()
	    );
	  }
	  
	  private AccountList(){}
	  
	  public static CopyOnWriteArrayList<Account> getInstance(){
	    return cList;
	  }
	  
	  public static void testList(){
	    CopyOnWriteArrayList<Account> list = AccountList.getInstance();
	    list.stream()
	        .forEach(i -> System.out.println(i));
	    String cString = 
	        list.stream()
	        .map( c -> c.toString())
	        .collect(Collectors.joining("\n"));
	    System.out.println(cString);
	  }
	    
	  public static void main(String[] args) {
	    AccountList.testList();
	  }
	  

}
