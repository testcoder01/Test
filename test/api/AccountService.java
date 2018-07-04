package test.api;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("accounts")
public class AccountService {
	
	  private final CopyOnWriteArrayList<Account> cList = AccountList.getInstance();
	  private final CopyOnWriteArrayList<Transaction> tList = TransactionList.getInstance();

	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getAllAccounts() {
	    return "============================" + System.lineSeparator() 
	    	+  "---Account List---" + System.lineSeparator()
	    	+ "============================" + System.lineSeparator()
	        + cList.stream()
	        .map(c -> c.toString())
	        .collect(Collectors.joining(System.lineSeparator()));
	  }

	  @GET
	  @Path("{id}")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getAccount(@PathParam("id") long id) {
	    Optional<Account> match
	        = cList.stream()
	        .filter(c -> c.getId() == id)
	        .findFirst();
	    
	    if (match.isPresent()) {
	      return "---Account---\n" + match.get().toString();
	    } else {
	      return "Account not found";
	    }
	  }
	  
	@POST
	@Path("transfer")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Valid
	public String transfer(@FormParam("fromAcc") String fromAcc,
			@FormParam("toAcc") String toAcc,
			@FormParam("amount") String amount,
			@FormParam("reference") String reference) throws IOException {
		
		String retMsg = "";
		
		if (fromAcc.trim().equalsIgnoreCase("") || fromAcc.trim().equalsIgnoreCase("0"))
			retMsg = retMsg + System.lineSeparator() + "Source Account must be specified and cannot be 0.";
		if (toAcc.trim().equalsIgnoreCase("") || toAcc.trim().equalsIgnoreCase("0"))
			retMsg = retMsg + System.lineSeparator() + "Target Account must be specified and cannot be 0.";
		if (amount.trim().equalsIgnoreCase("") || amount.trim().equalsIgnoreCase("0") || !checkAmount(amount))
			retMsg = retMsg + System.lineSeparator() + "Transfer Amount must be specified, cannot be 0 and must be numeric.";
		
		if (! "".equalsIgnoreCase(retMsg))
			return retMsg;
		
		long iFromAcc = Long.valueOf(fromAcc);
		long iToAcc= Long.valueOf(toAcc);
		double iAmount = Double.valueOf(amount);
		double fromBalance = 0.0;
		double toBalance = 0.0;
		
		Optional<Account> mSource
			= cList.stream()
			.filter(c -> c.getId() == iFromAcc)
			.findFirst();
    
		if (mSource.isPresent()) {
			Optional<Account> mTarget
			= cList.stream()
			.filter(c -> c.getId() == iToAcc)
			.findFirst();
			
			if (mTarget.isPresent()) {
				if (mSource.get().getBalance() >= iAmount) {
					
					//Current Date Time
					Date currDate = new Date();
					
					//Calculate Source Account Balance
					Account.AccountBuilder repSrc = new Account.AccountBuilder().id(mSource.get().getId());
					fromBalance = mSource.get().getBalance() - iAmount;
					repSrc.balance(fromBalance);
					repSrc.firstName(mSource.get().getFirstName());
					repSrc.lastName(mSource.get().getLastName());
					repSrc.ccy(mSource.get().getccy());
					
					//Calculate Target Account Balance
					Account.AccountBuilder repTrg = new Account.AccountBuilder().id(mTarget.get().getId());
					toBalance = mTarget.get().getBalance() + iAmount;
					repTrg.balance(toBalance);
					repTrg.firstName(mTarget.get().getFirstName());
					repTrg.lastName(mTarget.get().getLastName());
					repTrg.ccy(mTarget.get().getccy());
					
					//Remove the source account
					cList.remove(mSource.get());

					//Add Source Account
					cList.add(repSrc.build());
					
					//Remove the target account
					cList.remove(mTarget.get());
					
					//Add Target Account
					cList.add(repTrg.build());
					
					//Add Transaction details to Source and Target Accounts
					//Source Account
					Transaction.TransactionBuilder srcTxn = new Transaction.TransactionBuilder().txnID();
					srcTxn.accID(iFromAcc);
					srcTxn.txnRef(reference);
					srcTxn.txnType("Debit");
					srcTxn.txnDate(currDate);
					srcTxn.txnAmount(iAmount);
					srcTxn.accBalance(fromBalance);
					
					tList.add(srcTxn.build());
					
					//Target Account
					Transaction.TransactionBuilder trgTxn = new Transaction.TransactionBuilder().txnID();
					trgTxn.accID(iToAcc);
					trgTxn.txnRef(reference);
					trgTxn.txnType("Credit");
					trgTxn.txnDate(currDate);
					trgTxn.txnAmount(iAmount);
					trgTxn.accBalance(toBalance);
					
					tList.add(trgTxn.build());
										
					return "Transfer Complete!!!";
					
				} else {
					return "Source Account does not have enough funds to make the transfer. Transfer aborted.";
				}
				
			} else {
				return "Target Account Not Found. Transfer aborted.";
			}
		} else {
			return "Source Account Not Found. Transfer aborted.";
		}
	}
	
	private boolean checkAmount(String amount) {
		
		try{
			Long.valueOf(amount);
		} catch (NumberFormatException nFE) {
			return false;
		}		
		return true;
	}

}
