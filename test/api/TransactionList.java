package test.api;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TransactionList {
	
	private static final CopyOnWriteArrayList<Transaction> tList = new CopyOnWriteArrayList<>();
	  
	  private TransactionList(){}
	  
	  public static CopyOnWriteArrayList<Transaction> getInstance(){
	    return tList;
	  }
	  
	  public static void testList(){
	    CopyOnWriteArrayList<Transaction> list = TransactionList.getInstance();
	    list.stream()
	        .forEach(i -> System.out.println(i));
	    String cString = 
	        list.stream()
	        .map( c -> c.toString())
	        .collect(Collectors.joining("\n"));
	    System.out.println(cString);
	  }
	    
	  public static void main(String[] args) {
	    TransactionList.testList();
	  }
}
