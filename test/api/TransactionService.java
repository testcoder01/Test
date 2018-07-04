package test.api;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("transactions")
public class TransactionService {
	
	private final CopyOnWriteArrayList<Transaction> tList = TransactionList.getInstance();

	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getAllTransactions() {
	    return "====================================" + System.lineSeparator()
	    	+  "---Transaction List---" + System.lineSeparator()
	    	+  "====================================" + System.lineSeparator()
	        + tList.stream()
	        .map(c -> c.toString())
	        .collect(Collectors.joining(System.lineSeparator()));
	  }

	  @GET
	  @Path("{txnID}")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getTransaction(@PathParam("txnID") long txnID) {
	    Optional<Transaction> match
	        = tList.stream()
	        .filter(c -> c.getTxnId() == txnID)
	        .findFirst();
	    
	    if (match.isPresent()) {
	      return "---Transaction---\n" + match.get().toString();
	    } else {
	      return "Transaction not found";
	    }
	  }


}
