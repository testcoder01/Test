package test.api;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Transaction {
	  private final long txnID;
	  private final long accID;
	  private final String txnRef;
	  private final String txnType;
	  private final Date txnDate;
	  private final double txnAmount;
	  private final double accBalance;

	  private static final AtomicLong counter = new AtomicLong(9000);
	 
	  private Transaction(TransactionBuilder builder){
			this.txnID = builder.txnID;
			this.accID = builder.accID;
			this.txnRef = builder.txnRef;
			this.txnType = builder.txnType;
			this.txnDate = builder.txnDate;
			this.txnAmount = builder.txnAmount;
			this.accBalance = builder.accBalance;
	  }
	  
	  public Transaction(){
	    Transaction txn = new Transaction.TransactionBuilder().txnID().build();
	    	this.txnID = txn.txnID;
	    	this.accID = txn.accID;
		    this.txnRef = txn.txnRef;
		    this.txnType = txn.txnType;
			this.txnDate = txn.txnDate;
			this.txnAmount = txn.txnAmount;
			this.accBalance = txn.accBalance;
	  }
	  
	  public Transaction(long txnID, long accID, String txnRef, String txnType, Date txnDate,
	      double txnAmount, double accBalance){
	      Transaction txn = new Transaction.TransactionBuilder().txnID()
	           .accID(accID)
	           .txnRef(txnRef)
	           .txnType(txnType)
	           .txnDate(txnDate)
	           .txnAmount(txnAmount)
	           .accBalance(accBalance)
	           .build();
	      this.txnID = txn.txnID;
	      this.accID = txn.accID;
		  this.txnRef = txn.txnRef;
		  this.txnType = txn.txnType;
		  this.txnDate = txn.txnDate;
		  this.txnAmount = txn.txnAmount;
		  this.accBalance = txn.accBalance;
	  }
	  
	  public long getAccId(){
	    return this.accID;
	  }
	  
	  public long getTxnId(){
		    return this.txnID;
		  }

	  public String getTxnRef() {
	    return this.txnRef;
	  }
	  
	  public String getTxnType() {
		    return this.txnType;
		  }

	  public Date getTxnDate() {
	    return this.txnDate;
	  }
	  
	  public double getTxnAmt(){
	    return this.txnAmount;
	  }

	  public double getBalance(){
	    return this.accBalance;
	  }

	  @Override
	  public String toString(){
	    return   
	    	  "Transaction ID:        " + txnID + System.lineSeparator()
	    	+ "Account ID:            " + accID + System.lineSeparator()
	    	+ "Transaction Reference: " + txnRef + System.lineSeparator()
	    	+ "Transaction Type:      " + txnType + System.lineSeparator()
	    	+ "Transaction Date:      " + txnDate + System.lineSeparator()
	    	+ "Transaction Amount:    " + txnAmount + System.lineSeparator()
	    	+ "Account Balance:       " + accBalance + System.lineSeparator()
	    	+ "====================================";
	  }  
	  
	  public static class TransactionBuilder{
		  private long txnID;
		  private long accID;
		  private String txnRef;
		  private String txnType;
		  private Date txnDate;
		  private double txnAmount;
		  private double accBalance;
	    
	    public TransactionBuilder txnID(){
	      this.txnID = Transaction.counter.getAndIncrement();
	      return this;
	    }

	    public TransactionBuilder txnID(long txnID){
	      this.txnID = txnID;
	      return this;
	    }
	    
	    public TransactionBuilder accID(long accID){
		      this.accID = accID;
		      return this;
		    }
	    
	    public TransactionBuilder txnRef(String txnRef){
	      this.txnRef = txnRef;
	      return this;
	    }
	    
	    public TransactionBuilder txnType(String txnType){
		      this.txnType = txnType;
		      return this;
		    }

	    public TransactionBuilder txnDate(Date txnDate){
	      this.txnDate = txnDate;
	      return this;
	    }
	    
	    public TransactionBuilder txnAmount(double txnAmt){
	      this.txnAmount = txnAmt;
	      return this;
	    }
	    
	    public TransactionBuilder accBalance(double accBalance){
	      this.accBalance = accBalance;
	      return this;
	    }

	    public Transaction build(){
	      return new Transaction(this);
	    }
	    
	  }    
}
