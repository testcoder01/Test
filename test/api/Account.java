package test.api;

import java.util.concurrent.atomic.AtomicLong;

public class Account {
	  private final long accID;
	  private final String firstName;
	  private final String lastName;
	  private final String ccy;
	  private final double balance;

	  private static final AtomicLong counter = new AtomicLong(100);
	 
	  private Account(AccountBuilder builder){
	    this.accID = builder.accID;
	    this.firstName = builder.firstName;
	    this.lastName = builder.lastName;
	    this.ccy = builder.ccy;
	    this.balance = builder.balance;
	  }
	  
	  public Account(){
	    Account acc = new Account.AccountBuilder().id().build();
	      this.accID = acc.getId();
	      this.firstName = acc.getFirstName();
	      this.lastName = acc.getLastName();
	      this.ccy = acc.getccy();
	      this.balance = acc.balance;
	  }
	  
	  public Account(long id, String firstName, String lastName,
	      String ccy, double balance){
	      Account acc = new Account.AccountBuilder().id()
	           .firstName(firstName)
	           .lastName(lastName)
	           .ccy(ccy)
	           .balance(balance)
	           .build();
	      this.accID = acc.getId();
	      this.firstName = acc.getFirstName();
	      this.lastName = acc.getLastName();
	      this.ccy = acc.getccy();
	      this.balance = acc.balance;
	  }
	  
	  public long getId(){
	    return this.accID;
	  }

	  public String getFirstName() {
	    return this.firstName;
	  }

	  public String getLastName() {
	    return this.lastName;
	  }
	  
	  public String getccy(){
	    return this.ccy;
	  }

	  public double getBalance(){
	    return this.balance;
	  }
	  
	  @Override
	  public String toString(){
	    return 
	    	  "Account ID: " + accID + System.lineSeparator() 
	        + "First:      " + firstName + System.lineSeparator()
	        + "Last:       " + lastName + System.lineSeparator()
	        + "Currency:   " + ccy + System.lineSeparator()
	        + "Balance:    " + balance + System.lineSeparator()
	        + "============================";
	  }  
	  
	  public static class AccountBuilder{
	    private long accID;
	    private String firstName = "";
	    private String lastName = "";
	    private String ccy = "";
	    private double balance = 0.0;
	    
	    public AccountBuilder id(){
	      this.accID = Account.counter.getAndIncrement();
	      return this;
	    }

	    public AccountBuilder id(long id){
	      this.accID = id;
	      return this;
	    }
	    
	    public AccountBuilder firstName(String firstName){
	      this.firstName = firstName;
	      return this;
	    }

	    public AccountBuilder lastName(String lastName){
	      this.lastName = lastName;
	      return this;
	    }
	    
	    public AccountBuilder ccy(String ccy){
	      this.ccy = ccy;
	      return this;
	    }
	    
	    public AccountBuilder balance(double balance){
	      this.balance = balance;
	      return this;
	    }
	    
	    public Account build(){
	      return new Account(this);
	    }
	    
	  }    

	
	
}
