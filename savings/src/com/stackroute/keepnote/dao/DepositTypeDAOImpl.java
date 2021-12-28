package com.stackroute.keepnote.dao;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stackroute.keepnote.dto.BusinessMessage;
import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.dto.DepositDto;
import com.stackroute.keepnote.dto.DepositDtoList;
import com.stackroute.keepnote.dto.DepositsSuccess;
import com.stackroute.keepnote.dto.InterestDto;
import com.stackroute.keepnote.dto.InterestTypeE;
import com.stackroute.keepnote.dto.LedgerStatusE;
import com.stackroute.keepnote.dto.LedgerSummaryDto;
import com.stackroute.keepnote.dto.LoanDtoList;
import com.stackroute.keepnote.dto.MonthlyLoansSummaryDto;
import com.stackroute.keepnote.dto.OccurranceDto;
import com.stackroute.keepnote.dto.OccurranceEnum;
import com.stackroute.keepnote.dto.PdfDto;
import com.stackroute.keepnote.dto.PendingLoansDto;
import com.stackroute.keepnote.dto.ServiceStatus;
import com.stackroute.keepnote.dto.Status;
import com.stackroute.keepnote.dto.TransactionTypeE;
import com.stackroute.keepnote.model.Address;
import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.DepositType;
import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Expenses;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.InterestType;
import com.stackroute.keepnote.model.Interests;
import com.stackroute.keepnote.model.Ledger;
import com.stackroute.keepnote.model.LoanDto;
import com.stackroute.keepnote.model.LoanHistory;
import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.Occurrance;
import com.stackroute.keepnote.model.OtherIncome;
import com.stackroute.keepnote.model.Otp;
import com.stackroute.keepnote.model.PendingDeposits;
import com.stackroute.keepnote.model.Receipt;
import com.stackroute.keepnote.model.SpLoanHistory;
import com.stackroute.keepnote.model.SpecialLoan;
import com.stackroute.keepnote.model.UnpaidCustomerDto;

@Repository
@Transactional
public class DepositTypeDAOImpl implements DepositTypeDAO {
	

	
	@Autowired
	SessionFactory sessionFactory;
	
	public DepositTypeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;

	}
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	CustomerDao cdao;
	
	@Autowired
	userDAO dao;

	/*
	 * public List<DepositType> saveDepositType(DepositType depositType,Integer
	 * userId,long institutionId){ List<DepositType> cust=null;
	 * depositType.setInstitution(getInstitution(institutionId)); long
	 * c=(long)sessionFactory.getCurrentSession().save(depositType);
	 * System.out.println("c:"+c); if(c != 0)
	 * cust=getDepositTypeList(institutionId); else cust=null;
	 * 
	 * return cust; }
	 */
	public CommonResponse saveInterestType(InterestType interestType,long institutionId) {
		
		CommonResponse cmnr=new CommonResponse();
		List<BusinessMessage> businessMessage=new ArrayList<>();
		List<InterestType> cust=null;
		InterestType intType=getInterestType(interestType.getInterestType_uid(), institutionId);
		if(intType != null) {
			
			intType.setStatus(Status.InActive.getValue());
			sessionFactory.getCurrentSession().update(intType);
			
			interestType.setInstitution(getInstitution(institutionId));
			long c=(long)sessionFactory.getCurrentSession().save(interestType);
			
			if(interestType.getTransactionType() == TransactionTypeE.Deposits.getValue()) {
			Occurrance ooo=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), institutionId);
			if(ooo != null)
			{
				ooo.setInterestType(getInterestType(c, institutionId));
				sessionFactory.getCurrentSession().update(interestType);
			}
			}
			if(interestType.getTransactionType() == TransactionTypeE.Loans.getValue()) {
				Occurrance ooo=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), institutionId);
				if(ooo != null)
				{
					ooo.setInterestType(getInterestType(c, institutionId));
					sessionFactory.getCurrentSession().update(interestType);
				}
				}
			if(c>0) {
			cmnr.setServiceStatus(ServiceStatus.SUCCESS);
			businessMessage.add(new BusinessMessage("InterestType Successfully updated."));
		    cmnr.setBusinessMessage(businessMessage);
			cust=getInterestTypeList(institutionId);
			cmnr.setObj(cust);
			}
		   return cmnr;
		}
		intType=getInterestTypeByTransactionType(interestType.getTransactionType(), Status.Active.getValue(), institutionId);
		if(intType != null)
		{
			cmnr.setServiceStatus(ServiceStatus.ERROR);
			businessMessage.add(new BusinessMessage("InterestType already existed with active status."));
		    cmnr.setBusinessMessage(businessMessage);
			cust=getInterestTypeList(institutionId);
			cmnr.setObj(cust);
		    return cmnr;
		}
		
		interestType.setInstitution(getInstitution(institutionId));
		long c=(long)sessionFactory.getCurrentSession().save(interestType);
		//System.out.println("c:"+c);
		if(c > 0) {
			cmnr.setServiceStatus(ServiceStatus.SUCCESS);
			businessMessage.add(new BusinessMessage("InterestType Successfully added."));
		    cmnr.setBusinessMessage(businessMessage);
		}
		cust=getInterestTypeList(institutionId);
		cmnr.setObj(cust);
		return cmnr;
	}
	
	public List<Interests> saveInterests(InterestDto interestDto,long institutionId){

		List<Interests> cust=null;
		Interests i=new Interests();
		i.setInterestRate(interestDto.getInterestRate());
		i.setInterestType_id(getInterestType(Long.valueOf(interestDto.getInterestType_id()), institutionId));
		i.setStatus(interestDto.getStatus());
		i.setInstitution(getInstitution(institutionId));
		long c=(long)sessionFactory.getCurrentSession().save(i);
		//System.out.println("c:"+c);
		if(c != 0)
			cust=getInterestsList(institutionId);
		else
			cust=null;
		
		return cust;
	
	}
	
	/*
	 * public DepositType getDepositType(String depositname) { DepositType
	 * cust=(DepositType)sessionFactory.getCurrentSession().
	 * createQuery("from DepositType where deposit_type='"+depositname+"'").
	 * uniqueResult(); return cust; }
	 * 
	 * public List<DepositType> getDepositTypeList(long id) { List<DepositType>
	 * cust=sessionFactory.getCurrentSession().
	 * createQuery("from DepositType where institution.institution_uid="+id).list();
	 * return cust; }
	 */
	
	public List<InterestType> getInterestTypeList(long id)
	{
		List<InterestType> cust=sessionFactory.getCurrentSession().createQuery("from InterestType where institution.institution_uid="+id+" and status=1").list();
		return cust;
	}
	
	public InterestType getInterestType(long interestTypeId,long instiid)
	{
		InterestType cust=(InterestType)sessionFactory.getCurrentSession().createQuery("from InterestType where interestType_uid="+interestTypeId+" and institution.institution_uid="+instiid).uniqueResult();
		return cust;
	}
	
	public InterestType getInterestTypeByTransactionType(long transactionType,int status,long instiid)
	{
		InterestType cust=(InterestType)sessionFactory.getCurrentSession().createQuery("from InterestType where transactionType="+transactionType+" and status="+status+" and institution.institution_uid="+instiid).uniqueResult();
		return cust;
	}
	
	public List<Interests> getInterestsList(long id)
	{
		List<Interests> cust=sessionFactory.getCurrentSession().createQuery("from Interests where institution.institution_uid="+id).list();
		return cust;
	}
	
	public List<Deposits> getDepositsList(long customerId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where customer.customerUid="+customerId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<PendingDeposits> getPendingDepositsList(long customerId,String paidStatus,long instId)
	{
		List<PendingDeposits> cust=sessionFactory.getCurrentSession().createQuery("from PendingDeposits where customer.customerUid="+customerId+" and paidStatus='"+paidStatus+"' and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<Deposits> getDepositsList(long occurranceId,long customerId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where occurrance.occurranceUid="+occurranceId+" and customer.customerUid="+customerId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<Deposits> getDepositsListByLedgerAndCust(long ledgerId,long customerId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where ledger.ledger_uid="+ledgerId+" and customer.customerUid="+customerId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<Deposits> getDepositsListByOccurrance(long ledgerId,long occurranceId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where ledger.ledger_uid="+ledgerId+" and occurrance.occurranceUid="+occurranceId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<Deposits> getDepositsListByLedgerNotInOccurrance(long ledgerId,long occurranceId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where ledger.ledger_uid="+ledgerId+" and occurrance.occurranceUid !="+occurranceId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<Deposits> getDepositsByOccurrance(long fromoccurranceId,long toOccurrance,long instiId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where occurrance.occurranceUid>="+fromoccurranceId+" and occurrance.occurranceUid<="+toOccurrance+" and institution.institution_uid="+instiId).list();
		return cust;
	}
	
	public List<Occurrance> getOccuranceList(long institutionId)
	{
		List<Occurrance> cust=sessionFactory.getCurrentSession().createQuery("from Occurrance where status != "+OccurranceEnum.Pending.getValue()+" and institution.institution_uid="+institutionId+" order by occurranceUid desc")
				.setMaxResults(6)
				.list();
		return cust;
	}
	
	public Occurrance getOccurance(long id,long instId,int status)
	{
		Occurrance cust=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where institution.institution_uid="+instId+" and occurranceUid="+id+"").uniqueResult();
		return cust;
	}
	
	public Occurrance getFirstOccurance(long instId)
	{
		Occurrance cust=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where institution.institution_uid="+instId+" order by occurranceUid asc")
				.setMaxResults(1)
				.uniqueResult();
		
		return cust;
	}
	
	public List<Occurrance> getOccuranceList(long fromOccurranceId,long toOccurranceID,long institutionId)
	{
		List<Occurrance> cust=sessionFactory.getCurrentSession().createQuery("from Occurrance where institution.institution_uid="+institutionId+" and occurranceUid>="+fromOccurranceId+" and occurranceUid<="+toOccurranceID +" group by ledger.ledger_uid").list();
		//cust=(List<Occurrance>) cust.stream().collect(Collectors.groupingBy(Occurrance::getLedger));
		return cust;
	}
	
	public Occurrance getOccuranceByInstitution(int transactionType,long instId,int status)
	{
		Occurrance cust=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+transactionType+" and  institution.institution_uid="+instId+" and status="+status+"").uniqueResult();
		return cust;
	}
	
	/*
	 * public Interests getInterest(String interestType,long id) { Interests
	 * cust=(Interests)sessionFactory.getCurrentSession().
	 * createQuery("from Interests where institution.institution_uid="
	 * +id+" and interestType_id.interestType='"+interestType+"'").uniqueResult();
	 * return cust; }
	 */
	
	public Address getAddress(long id)
	{
		Address a=(Address)sessionFactory.getCurrentSession().createQuery("from Address where address_uid="+id).uniqueResult();
		return a;
	}
	
	public Institution getInstitution(long id)
	{
		Institution a=(Institution)sessionFactory.getCurrentSession().createQuery("from Institution where institution_uid="+id).uniqueResult();
		//System.out.println("entered::"+a);
		return a;
	}
	
	public Ledger getLastLedger(int status,long instid)
	{
		Ledger a=(Ledger)sessionFactory.getCurrentSession().createQuery("from Ledger where ledger_uid=( select max(ledger_uid) from Ledger where  institution.institution_uid="+instid+")").uniqueResult();
		return a;
	}
	
	public Otp getOtpStatus(long userId,int statusId,long instid)
	{
		Otp a=(Otp)sessionFactory.getCurrentSession().createQuery("from Otp where user.userId="+userId+" and status="+statusId+" and institution.institution_uid="+instid).uniqueResult();
		return a;
	}
	
	public Otp getOtpObj(long userId,int statusId,long instid)
	{
		Otp a=(Otp)sessionFactory.getCurrentSession().createQuery("from Otp where user.userId="+userId+" and loggedStatus="+statusId+" and institution.institution_uid="+instid).uniqueResult();
		return a;
	}
	
	public Ledger getLastLedgerByStatus(int status,long instid)
	{
		Ledger a=(Ledger)sessionFactory.getCurrentSession().createQuery("from Ledger where ledger_uid=( select max(ledger_uid) from Ledger where status="+status+" and  institution.institution_uid="+instid+")").uniqueResult();
		return a;
	}
	
	
	
	
	public CommonResponse saveOccurance(OccurranceDto occurancedto,long userId,long id)
	{
		int  year=0;
		int month=0;
		int date=0;
		CommonResponse cmnr=new CommonResponse();
		List<BusinessMessage> businessMessage=new ArrayList<>();
		//System.out.println("CoveredMonth::"+occurancedto.getCoveredMonth());
		if(occurancedto.getCoveredMonth().equals("") || occurancedto.getCoveredMonth() == null || occurancedto.getCoveredMonth().trim().equals("")) {
			//System.out.println("Entered:::");
			cmnr.setServiceStatus(ServiceStatus.ERROR);
			businessMessage.add(new BusinessMessage("CoveredMonth should not be null"));
			cmnr.setBusinessMessage(businessMessage);
			cmnr.setObj(getOccuranceList(id));
			return cmnr;
		}
		
		
		 if((!occurancedto.getCoveredMonth().equalsIgnoreCase(" ") || !occurancedto.getCoveredMonth().trim().equalsIgnoreCase("")) && occurancedto.getCoveredMonth() != null) {
	        	//System.out.println("Entered111"+occurancedto.getCoveredMonth());
	            year=Integer.parseInt(occurancedto.getCoveredMonth().substring(0, 4));
	            month=Integer.parseInt(occurancedto.getCoveredMonth().substring(5, 7));
	            date=Integer.parseInt(occurancedto.getCoveredMonth().substring(8, 10));
	           //System.out.println(year+" : "+month+" : "+date);
		 }
		
		
		long c=0;
		String a1="";
		
		 for (Entry<Integer, String> entry : occurancedto.getTransactionType().entrySet())  
		 {
			    Occurrance oo=getActiveOccurrance(Status.Active.getValue(), entry.getKey(), id);
			    if(oo != null)
			    {
					Occurrance occ=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), id);
					//System.out.println("occ::"+occ);
					if(occ == null)
					{
						a1="loans";
						//continue;
						
					}
					else 
					{
						cmnr.setServiceStatus(ServiceStatus.ERROR);
						businessMessage.add(new BusinessMessage(" Occurance is still open,Please settle it for new Occurrance registration."));
						cmnr.setBusinessMessage(businessMessage);
						cmnr.setObj(getOccuranceList(id));
					    return cmnr; 
					}
			    }
			    else {
			    	
			    	List<Occurrance> list=getOccuranceList(id);
				       for(Occurrance o:list)
				       {
				    	   if(o.getTransactionType() == entry.getKey()) {
				    	   int i=o.getCoveredMonth().compareTo(LocalDate.of(year, month, date));
				    	   if(i>=0)
				    	   {
				    		   //System.out.println("entered::"+i);
				    		   cmnr.setServiceStatus(ServiceStatus.ERROR);
								businessMessage.add(new BusinessMessage(entry.getValue()+" Occurance is done."));
								cmnr.setBusinessMessage(businessMessage);
								cmnr.setObj(getOccuranceList(id));
						    	return cmnr;  
				    	   }
				       }
				    	   
				       }
			    	
			        
			    	//}
			    }
			    if(oo == null) {
			    
			    Ledger ledger=new Ledger();
			    Ledger ll=getLastLedgerByStatus(LedgerStatusE.Open.getValue(),id);
				if(ll ==null) {
				 Ledger l=getLastLedgerByStatus(LedgerStatusE.Close.getValue(),id);
				    
				   
			    	ledger.setCreatedBy(userId);
			    	ledger.setLedger_entry_date(LocalDateTime.now());
		            ledger.setOpeningBalance(l != null?l.getClosingBalance():0.00);
		            ledger.setBalance(l != null?l.getClosingBalance():0.00);
		            ledger.setStatus(LedgerStatusE.Open.getValue());
		            ledger.setInstitution(getInstitution(id));
		            long le=(long)sessionFactory.getCurrentSession().save(ledger);
		            
			    }
				else {
					ledger=ll;
				}
		            
		            
		           
			    
				InterestType interestType=getInterestTypeByTransactionType(entry.getKey(), Status.Active.getValue(), id);
				//System.out.println("interestType::"+interestType);
				
				if(interestType == null) {
					cmnr.setServiceStatus(ServiceStatus.ERROR);
					businessMessage.add(new BusinessMessage("SangamStartUp for "+entry.getValue()+" not configured because it is not configured in InterestRate."));
					cmnr.setBusinessMessage(businessMessage);
					cmnr.setObj(getOccuranceList(id));
					return cmnr;
				}
				
		
//				if(address == null) {
//					cmnr.setServiceStatus(ServiceStatus.ERROR);
//					businessMessage.add(new BusinessMessage("Address should not be null."));
//					cmnr.setBusinessMessage(businessMessage);
//					cmnr.setObj(getOccuranceList(id));
//					return cmnr;
//				}
				
				Occurrance occurance=null;
				occurance=getActiveOccurrance(OccurranceEnum.Pending.getValue(), entry.getKey(), id);
				if(occurance != null)
				{
					Address a=occurance.getAddress();
					if(a != null) {
					a.setDistrict(occurancedto.getDistrictId());
					a.setLandMark(occurancedto.getLandMark());
					a.setMandal(occurancedto.getMandalId());
					a.setPincode(occurancedto.getPincode());
					a.setState(occurancedto.getStateId());
					a.setVillage(occurancedto.getVillgaeId());
				    sessionFactory.getCurrentSession().update(a);
					}
					
					occurance.setStatus(Integer.parseInt(occurancedto.getStatus()));
					occurance.setCoveredMonth(LocalDate.of(year, month, date));
					occurance.setOccurrancePlace(occurancedto.getOccurrancePlace());
					occurance.setActivehours(occurancedto.getActivehours());
					occurance.setUserId(String.valueOf(userId));
					occurance.setOccurranceDate(new Date());
					occurance.setLedger(ledger);
					sessionFactory.getCurrentSession().update(occurance);
					
					cmnr.setServiceStatus(ServiceStatus.SUCCESS);
					businessMessage.add(new BusinessMessage(entry.getValue()+" SangamStartUp successfully added"));
					cmnr.setBusinessMessage(businessMessage);
				}
				else {
					
					Address a=new Address();
					a.setDistrict(occurancedto.getDistrictId());
					a.setLandMark(occurancedto.getLandMark());
					a.setMandal(occurancedto.getMandalId());
					a.setPincode(occurancedto.getPincode());
					a.setState(occurancedto.getStateId());
					a.setVillage(occurancedto.getVillgaeId());
					long aId=(long)sessionFactory.getCurrentSession().save(a);
					Address address=getAddress(aId);
					
					occurance= new Occurrance();
				
				occurance.setUserId(String.valueOf(userId));
				occurance.setOccurranceDate(new Date());
				occurance.setStatus(Integer.parseInt(occurancedto.getStatus()));
				occurance.setActivehours(occurancedto.getActivehours());
				occurance.setTransactionType(entry.getKey());
				occurance.setOccurrancePlace(occurancedto.getOccurrancePlace());
				occurance.setLedger(ledger);
				occurance.setInterestType(interestType);
				occurance.setAddress(address);
				occurance.setCoveredMonth(LocalDate.of(year, month, date));
				//occurance.setCoveredMonth(LocalDate.of(2020, 04, 8));
				occurance.setInstitution(getInstitution(id));
				//if(cmnr.getBusinessMessage().size() == 0)
				 c=(long)sessionFactory.getCurrentSession().save(occurance);
				}
				if(c>0)
				{
					cmnr.setServiceStatus(ServiceStatus.SUCCESS);
					businessMessage.add(new BusinessMessage(entry.getValue()+" SangamStartUp successfully added"));
					cmnr.setBusinessMessage(businessMessage);
				}
				 long le=(long)sessionFactory.getCurrentSession().save(ledger);
				//System.out.println("c:"+c); 
			    }
		 }
		
		
		//if(c != 0) {
			cmnr.setObj(getOccuranceList(id));
		//}
		
		return cmnr;	
	}
	
	
	public Customer getCustomer(long customerId,int statusId,long institutionId)
	{
		Customer cust=(Customer)sessionFactory.getCurrentSession().createQuery("from Customer where customerUid="+customerId+" and status="+statusId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public List<LoanHistory> getLoanHistoryList(long loanId,long institutionId)
	{
		List<LoanHistory> cust=(List<LoanHistory>)sessionFactory.getCurrentSession().createQuery("from LoanHistory where loansId="+loanId+" and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<SpLoanHistory> getSpLoanHistoryList(long occId,String paidStatus,long institutionId)
	{
		List<SpLoanHistory> cust=(List<SpLoanHistory>)sessionFactory.getCurrentSession().createQuery("from SpLoanHistory where occurrance.occurranceUid="+occId+" and paidStatus='"+paidStatus+"' and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<SpLoanHistory> getSpLoanHistoryBySPIdAndOcc(long spId,long occId,String paidStatus,long institutionId)
	{
		List<SpLoanHistory> cust=(List<SpLoanHistory>)sessionFactory.getCurrentSession().createQuery("from SpLoanHistory where sploans.SpecialLoanUid="+spId+" and occurrance.occurranceUid="+occId+" and paidStatus='"+paidStatus+"' and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<SpLoanHistory> getSpLoanHistoryByCustIdAndOcc(long cusId,long occId,String paidStatus,long institutionId)
	{
		List<SpLoanHistory> cust=(List<SpLoanHistory>)sessionFactory.getCurrentSession().createQuery("from SpLoanHistory where customer.customerUid="+cusId+" and occurrance.occurranceUid="+occId+" and paidStatus='"+paidStatus+"' and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<LoanHistory> getLoanHistoryListByPaidStatus(long loanId,String status,long institutionId)
	{
		List<LoanHistory> cust=(List<LoanHistory>)sessionFactory.getCurrentSession().createQuery("from LoanHistory where loansId="+loanId+" and paidStatus='"+status+"' and institution.institution_uid="+institutionId+" order by installmentId").list();
		return cust;
	}
	
	public List<LoanHistory> getLoanHistoryListByLoanAndOccurranceId(long loanId,long occId,String status,long institutionId)
	{
		List<LoanHistory> cust=(List<LoanHistory>)sessionFactory.getCurrentSession().createQuery("from LoanHistory where loansId="+loanId+" and occurrance.occurranceUid="+occId+" and paidStatus='"+status+"' and institution.institution_uid="+institutionId+" order by installmentId").list();
		return cust;
	}
	
	public List<LoanHistory> getLoanHistoryListByCustAndOccurranceId(long custId,long occId,String status,long institutionId)
	{
		List<LoanHistory> cust=(List<LoanHistory>)sessionFactory.getCurrentSession().createQuery("from LoanHistory where customer.customerUid="+custId+" and occurrance.occurranceUid="+occId+" and paidStatus='"+status+"' and institution.institution_uid="+institutionId+" order by installmentId").list();
		return cust;
	}
	
	public LoanHistory getLoanHistoryByInstallmentId(long loanId,long installmentId,long institutionId)
	{
		LoanHistory cust=(LoanHistory)sessionFactory.getCurrentSession().createQuery("from LoanHistory where loansId="+loanId+" and installmentId="+installmentId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public Loans getLoanById(long loanId,long institutionId)
	{
		Loans cust=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where loans_uid="+loanId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public List<Loans> getLoans(long institutionId)
	{
		List<Loans> cust=(List<Loans>)sessionFactory.getCurrentSession().createQuery("from Loans where institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public Receipt getRid(long rid,long instId)
	{
		Receipt cust=(Receipt) sessionFactory.getCurrentSession().createQuery("from Receipt where receiptsUid="+rid+" and  institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public Receipt getReceiptByCustomerAndOccurrance(long cid,long occurranceId,long instId)
	{
		Receipt cust=(Receipt) sessionFactory.getCurrentSession().createQuery("from Receipt where customer.customerUid="+cid+" and occurrance.occurranceUid="+occurranceId+" and  institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public Receipt searchRid(long custmerId,long occuyrranceId,long instId)
	{
		Receipt cust=(Receipt) sessionFactory.getCurrentSession().createQuery("from Receipt where customer.customerUid="+custmerId+" and occurrance.occurranceUid="+occuyrranceId+" and  institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public SpecialLoan getSpecialLoanById(long loanId,long institutionId)
	{
		SpecialLoan cust=(SpecialLoan)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where SpecialLoanUid="+loanId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public List<SpecialLoan> getSpecialLoans(long institutionId)
	{
		List<SpecialLoan> cust=(List<SpecialLoan>)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where  institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public SpecialLoan getSpecialLoanByCust(long custId,long institutionId)
	{
		SpecialLoan cust=(SpecialLoan)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where customer.customerUid="+custId+" and institution.institution_uid="+institutionId+" order by SpecialLoanUid desc")
				.setMaxResults(1)    
				.uniqueResult();
		return cust;
	}
	
	public SpecialLoan getSpecialLoanByCustAndOcc(long custId,long occId,long institutionId)
	{
		SpecialLoan cust=(SpecialLoan)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where customer.customerUid="+custId+" and occurrance.occurranceUid="+occId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public Loans getLoanByCustomerAndStatus(long occuurranceId,long customerId,long institutionId)
	{
		Loans cust=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where occurrance.occurranceUid="+occuurranceId+" and customer.customerUid="+customerId+" and noOfInstallments>paidInstallments and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public Loans getLoanByCustomerAndOccurrance(long occuurranceId,long customerId,long institutionId)
	{
		Loans cust=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where occurrance.occurranceUid="+occuurranceId+" and customer.customerUid="+customerId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	public List<Loans> getLoanByCustomer(long customerId,long institutionId)
	{
		List<Loans> cust=(List<Loans>)sessionFactory.getCurrentSession().createQuery("from Loans where customer.customerUid="+customerId+" and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	
	public Loans getLoanByCust(long customerId,long institutionId)
	{
		Loans cust=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where customer.customerUid="+customerId+" and noOfInstallments>paidInstallments and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}

	
	public List<Customer> getCustomerList(int status,long institutionId)
	{
		List<Customer> cust=(List<Customer>)sessionFactory.getCurrentSession().createQuery("from Customer where status="+status+" and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<Customer> getCustomerList(long institutionId)
	{
		List<Customer> cust=(List<Customer>)sessionFactory.getCurrentSession().createQuery("from Customer where institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<Customer> getCustomerListByOccurrance(int status,long occurranceId,long institutionId)
	{
		List<Customer> cust=(List<Customer>)sessionFactory.getCurrentSession().createQuery("from Customer where status="+status+" and withDrawalOccurrance.occurranceUid="+occurranceId+" and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<Occurrance> getUnmatchedOccurrances(long custId,int tType,int status,long occuId,long intiId)
	{
		List<Occurrance> list=sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+tType+" and institution.institution_uid="+intiId+" and occurranceUid not in (select cast(occurrance.occurranceUid as int) from Deposits where customer.customerUid="+custId+" and institution.institution_uid="+intiId+")").list();
		//sessionFactory.getCurrentSession().createQuery("from PendingDeposits where customer.customerUid="+custId+" and paidStatus='N' and institution.institution_uid="+intiId).list();
		return list.stream().filter(e->e.getOccurranceUid() >= occuId).collect(Collectors.toList());
	}
	
	public List<Occurrance> getUnmatchedOccurrancesForLoans(long loanId,int tType,int status,long occuId,long intiId)
	{
		List<Occurrance> list=sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+tType+" and institution.institution_uid="+intiId+" and occurranceUid > (select COALESCE(max(occurrance.occurranceUid),0) from LoanHistory where loansId="+loanId+" and institution.institution_uid="+intiId+")").list();
		return list.stream().filter(e->e.getOccurranceUid() >= occuId).collect(Collectors.toList());
	}
	
	public List<Occurrance> getUnmatchedOccurrancesForSpecialLoans(long loanId,int tType,int status,long occuId,long intiId)
	{
		List<Occurrance> list=sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+tType+" and institution.institution_uid="+intiId+" and occurranceUid not in (select coalesce(occurrance.occurranceUid, 0) from SpecialLoan where SpecialLoanUid="+loanId+" and institution.institution_uid="+intiId+")").list();
		return list.stream().filter(e->e.getOccurranceUid() >= occuId).collect(Collectors.toList());
	}
	
	public List<Loans> getLoansByOccurance(long occuranceId ,long intiId)
	{
		List<Loans> list=sessionFactory.getCurrentSession().createQuery("from Loans where occurrance.occurranceUid="+occuranceId+" and institution.institution_uid="+intiId).list();
		return list;
	}
	
	public List<Loans> getLoansByOccurance(long fromoccuranceId ,long toOccurrance,long intiId)
	{
		List<Loans> list=sessionFactory.getCurrentSession().createQuery("from Loans where occurrance.occurranceUid>="+fromoccuranceId+" and occurrance.occurranceUid<="+toOccurrance+" and institution.institution_uid="+intiId).list();
		return list;
	}
	
	public List<SpecialLoan> getSPLoansByOccurance(long occuranceId ,long intiId)
	{
		List<SpecialLoan> list=sessionFactory.getCurrentSession().createQuery("from SpecialLoan where occurrance.occurranceUid="+occuranceId+" and institution.institution_uid="+intiId).list();
		return list;
	}
	
	public Occurrance getOccuranceByLedger(int status,int tanType,long ledgerId ,long intiId)
	{
		Occurrance list=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where status="+status+" and transactionType="+tanType+" and ledger="+ledgerId+" and institution.institution_uid="+intiId).uniqueResult();
		return list;
	}
	
	public Occurrance getOccuranceByLedger(int tanType,long ledgerId ,long intiId)
	{
		Occurrance list=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where occurranceUid=(select max(occurranceUid) as occurranceUid from Occurrance where transactionType="+tanType+" and ledger="+ledgerId+" and institution.institution_uid="+intiId+")").uniqueResult();
		return list;
	}
	
	public double simpleInterest(double p,int t,double r)
	{
		double interest=(p*t*r)/100;
		return interest;
	}
	
	public double compoundInterest(int p, int t, double r, int n) {
        double amount = p * Math.pow(1 + (r / n), n * t);
        double cinterest = amount - p;
        //System.out.println("Compound Interest after " + t + " years: "+cinterest);
        //System.out.println("Amount after " + t + " years: "+amount);
        return cinterest;
    }
	
	
	public long saveReceipt(Customer c,String TransactionIds,long userId,long instId)
	{
		Receipt r=new Receipt();
		r.setCreatedBy(String.valueOf(userId));
		r.setCustomer(c);
		r.setDepositTransactionIds(TransactionIds);
		r.setGeneratedDate(LocalDateTime.now());
		r.setInstitution(getInstitution(instId));
		//r.setLoanTransactionIds(loanTransactionIds);
		r.setOccurrance(getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), instId));
		//r.setReceiptsUid(receiptsUid);
		//r.setSpLoanTransactionIds(spLoanTransactionIds);
		//r.setUpdatedBy(updatedBy);
		long rid=(long)saveObj(r);
		
		return rid;
		
	}
	
	public long updateReceipt(long rid,String transactionIds,String type,long instId)
	{
		//System.out.println("rid::"+rid+"  "+type+"  "+instId);
		Receipt r=getRid(rid, instId);
		//System.out.println("r:::"+r);
		if(r != null)
		{
			if(type.equalsIgnoreCase("Loans"))
				r.setLoanTransactionIds(transactionIds);
			else if(type.equalsIgnoreCase("SpLoans"))
				r.setSpLoanTransactionIds(transactionIds);
			
			updateObj(r);
		}
		
		
		return rid;
		
	}
	
	public List<DepositsSuccess> saveDeposits(List<DepositDto> depositdto,long userId,long instId){
		List<Deposits> cust=null;
		List<DepositsSuccess> ds=new ArrayList<>();
		long c=0;
		 Customer customer=null;
		 Occurrance aoo=null;
		Ledger l=getLastLedger(LedgerStatusE.Open.getValue(), instId);
		
		for(DepositDto depositsdto:depositdto) {
			 aoo=getOccurance(depositsdto.getOccurranceId(),instId,Status.Active.getValue());
			 if(depositsdto.getCustomerId()>0)
			customer=getCustomer(depositsdto.getCustomerId(), Status.Active.getValue(), instId);
		if(depositsdto.getPaidStatus().equalsIgnoreCase("yes")) {
			l.setBalance(l.getBalance()+depositsdto.getPrincipleAmount()+depositsdto.getFine());
			sessionFactory.getCurrentSession().update(l);
		Deposits d=new Deposits();
		d.setCreatedBy(String.valueOf(userId));
		d.setDepositDate(LocalDateTime.now());
		d.setCustomer(customer);
		d.setOccurrance(aoo);
		d.setAmountPaid(depositsdto.getPrincipleAmount());
		//d.setInterestPaid(depositsdto.getInterest());
		d.setInstitution(getInstitution(instId));
		if(depositsdto.getCoveredMonth().equalsIgnoreCase("PreviousPendingDeposits")) {
		d.setDepositType(com.stackroute.keepnote.dto.DepositType.Previous_Pending.getValue());
		}
		else {
			d.setDepositType(com.stackroute.keepnote.dto.DepositType.Monthly_Share.getValue());
		}
		d.setFine(depositsdto.getFine());
		d.setLedger(l);
		 c=(long)sessionFactory.getCurrentSession().save(d);
		//System.out.println("c:"+c);
		if(depositsdto.getCoveredMonth().equalsIgnoreCase("PreviousPendingDeposits")) {
		List<PendingDeposits> pp=getPendingDepositsList(depositsdto.getCustomerId(), "N", instId);
		for(PendingDeposits p:pp)
		{
			p.setPaidStatus("Y");
			p.setUpdatedDate(LocalDateTime.now());
			p.setUpdatedBy(String.valueOf(userId));
			sessionFactory.getCurrentSession().update(p);
		}
		}
		
//		else
//			ds=null;
			}
		
		}
		
		if(c != 0) {
		    String coveredMonths="";
		    String transactionNo="";
	        if(customer != null)
	        {
			cust=getDepositsListByLedgerAndCust(l.getLedger_uid(), customer.getCustomer_uid(), instId);
			for(Deposits de:cust) {
			   DepositsSuccess s=new DepositsSuccess();
			   s.setAmount(de.getAmountPaid());
			   s.setCoveredMonth(de.getOccurrance().getCoveredMonth().toString());
			   s.setCustomer(de.getCustomer());
			   s.setFine(de.getFine());
			   s.setInstitution(de.getInstitution());
			   s.setTransactionId(de.getDepositsUid());
			   s.setUser(cdao.getUserById(Long.valueOf(de.getCreatedBy()), instId));
			   coveredMonths=coveredMonths+","+de.getOccurrance().getCoveredMonth().toString();
			   transactionNo=transactionNo+","+de.getDepositsUid();
			   ds.add(s);
			}
			
			if(customer != null && ds != null) {
				String msg="Hi "+customer.getCustomerName()+" - id:"+customer.getCustomerId()+" you have Successfully paid monthly deposits of Rs."+ds.stream().mapToDouble(e->e.getAmount()).sum()+" and fine Rs."+ds.stream().mapToDouble(e->e.getFine()).sum()+""
					+ " for months of ("+coveredMonths+") and transactionNumbers ("+transactionNo+")";
			//System.out.println("inside msg:::"+msg);
			}
	        }
		  // String id=sendSMS(SMSCredentials.ACCOUNT_SID.getValue(), SMSCredentials.AUTH_TOKEN.getValue(), SMSCredentials.TWILIO_NUMBER.getValue(), d.getCustomer().getPhoneNo(), msg);
		   
		}
		return ds;
	}
	
	public CommonResponse searchOccurance(long custId,long intiId){ 
		CommonResponse res=new CommonResponse();
		List<BusinessMessage> list=new ArrayList<>();
		List<DepositsSuccess> ds=new ArrayList<>();
		DepositDtoList dtoList=new DepositDtoList();
		List<DepositDto> dto=new ArrayList<>();
		Customer c=getCustomer(custId,Status.Active.getValue(), intiId);
		//System.out.println("value of c:"+c);
		if(c == null)
		{
			list.add(new BusinessMessage("Invalid Customer"));
			res.setServiceStatus(ServiceStatus.ERROR);
			res.setBusinessMessage(list);;
			return res;
		}
		
	

		Occurrance activeOccurrance=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Deposits.getValue(), intiId);
		
		//if(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), intiId, Status.Active.getValue()) == null)
		if(activeOccurrance==null)
		{
			list.add(new BusinessMessage("Sangam not yet started."));
			res.setServiceStatus(ServiceStatus.ERROR);
			res.setBusinessMessage(list);;
			return res;
		}
		List<Occurrance> occur=getUnmatchedOccurrances(custId,TransactionTypeE.Deposits.getValue(),Status.Active.getValue(),c.getOccurrance().getOccurranceUid(), intiId);
		//oo.forEachRemaining(occur::add);
		//System.out.println("sizeeee:"+occur.size());
		for(Occurrance o1:occur)
		{
		    LocalDate ll=o1.getCoveredMonth();
			Period age = Period.between(o1.getCoveredMonth(), activeOccurrance.getCoveredMonth()); 
			int years = age.getYears();
			int months = age.getMonths();
			//System.out.println("interestType::"+o1.getInterestType());
			//System.out.println("interestRate::"+o1.getInterestType().getInterestRate());
			long monthlyinterest=(o1.getInterestType().getIdealDepositAmount()*o1.getInterestType().getInterestRate())/100;
			double monthlyfine=(o1.getInterestType().getIdealDepositAmount()*o1.getInterestType().getFine())/100;
			long totalInterest=0;
			double totalFine=0;
			long principleAmount=0;
			//System.out.println(o1.getInterestType().getInterestType_uid()+"  &&&&&&&&&&& "+o1.getInterestType().getInterestType() +"   "+String.valueOf(InterestTypeE.Compound.getValue()) );
			//System.out.println(o1.getCoveredMonth());
			//System.out.println(o1.getInterestType().getInterestType().equalsIgnoreCase(String.valueOf(InterestTypeE.Compound.getValue())));
			if(o1.getInterestType().getInterestType().equalsIgnoreCase(String.valueOf(InterestTypeE.Compound.getValue())))
			{
				//System.out.println("entered compound");
				for(int i=0;i<months;i++)
				{
					principleAmount=o1.getInterestType().getIdealDepositAmount();
					totalInterest=totalInterest+monthlyinterest;
					totalFine=totalFine+monthlyfine;
				}	
			}
			else {
				//System.out.println("entered simlple");
				principleAmount=o1.getInterestType().getIdealDepositAmount();
				totalInterest=monthlyinterest;
				totalFine=monthlyfine;
			}
			
			if(o1.getCoveredMonth().compareTo(activeOccurrance.getCoveredMonth()) == 0) {
				principleAmount=o1.getInterestType().getIdealDepositAmount();
				if(o1.getInterestType().getInterestType().equalsIgnoreCase(String.valueOf(InterestTypeE.Simple.getValue())))
				{
					totalInterest=0;
					totalFine=0;
				}
			}
			DepositDto d=new DepositDto();
			d.setCoveredMonth(o1.getCoveredMonth().toString());
			d.setInterest(totalInterest);
			d.setNoofMonths(months);
			d.setPrincipleAmount(principleAmount);
			d.setCustomerId(Integer.parseInt(String.valueOf((custId))));
			d.setCustomerName(getCustomer(custId, Status.Active.getValue(), intiId).getCustomerName());
			//d.setTotal(totalInterest+principleAmount+totalFine);
			d.setTotal(principleAmount+totalFine);
			d.setOccurranceId(Integer.parseInt(String.valueOf(o1.getOccurranceUid())));
			d.setFine(totalFine);
			
           dto.add(d);
			res.setServiceStatus(ServiceStatus.SUCCESS);
		}
		
		List<PendingDeposits> plist=getPendingDepositsList(custId, "N", intiId);
		for(PendingDeposits p:plist)
		{
			Occurrance o1=p.getOccurrance();
			 LocalDate ll=p.getOccurrance().getCoveredMonth();
				Period age = Period.between(o1.getCoveredMonth(), activeOccurrance.getCoveredMonth()); 
				int years = age.getYears();
				int months = age.getMonths();
				//System.out.println("interestType::"+o1.getInterestType());
				//System.out.println("interestRate::"+o1.getInterestType().getInterestRate());
				long monthlyinterest=((long) p.getPendingAmount()*o1.getInterestType().getInterestRate())/100;
				double monthlyfine=((long) p.getPendingAmount()*o1.getInterestType().getFine())/100;
				long totalInterest=0;
				double totalFine=0;
				long principleAmount=0;
				for(int i=0;i<months;i++)
				{
					principleAmount=(long) p.getPendingAmount();
					totalInterest=totalInterest+monthlyinterest;
					totalFine=totalFine+monthlyfine;
				}
				if(o1.getCoveredMonth().compareTo(activeOccurrance.getCoveredMonth()) == 0) {
					principleAmount=(long) p.getPendingAmount();
				}
				DepositDto d=new DepositDto();
				d.setCoveredMonth("PreviousPendingDeposits");
				d.setInterest(totalInterest);
				d.setNoofMonths(months);
				d.setPrincipleAmount(principleAmount);
				d.setCustomerId(Integer.parseInt(String.valueOf((custId))));
				//d.setTotal(totalInterest+principleAmount+totalFine);
				d.setTotal(principleAmount+totalFine);
				d.setOccurranceId((int)activeOccurrance.getOccurranceUid());
				d.setFine(totalFine);
				
	           dto.add(d);
//			
//			DepositDto d=new DepositDto();
//			d.setCoveredMonth("PreviousPendingDeposits");
//			d.setPrincipleAmount(p.getPendingAmount());
//			d.setCustomerId(Integer.parseInt(String.valueOf((custId))));
//			//d.setTotal(totalInterest+principleAmount+totalFine);
//			d.setTotal(p.getPendingAmount()+p.getFine());
//			d.setOccurranceId((int)getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), intiId).getOccurranceUid());
//			d.setFine(p.getFine());
//			dto.add(d);
		}
		if(occur.size() == 0)
		{
			
			List<Deposits> cust=getDepositsListByLedgerAndCust(getActiveLedger(LedgerStatusE.Open.getValue(), intiId).getLedger_uid(),custId,intiId);
			 Occurrance ooo = activeOccurrance;
			 Receipt rrr=null;
			   if(ooo != null)
				    rrr= searchRid(custId, ooo.getOccurranceUid(), intiId);
			for(Deposits de:cust) {
			   DepositsSuccess s=new DepositsSuccess();
			  if(rrr != null)
			   s.setRid(rrr.getReceiptsUid());
			   s.setAmount(de.getAmountPaid());
			   s.setCoveredMonth(de.getOccurrance().getCoveredMonth().toString());
			   s.setCustomer(de.getCustomer());
			   s.setFine(de.getFine());
			   s.setInstitution(de.getInstitution());
			   s.setTransactionId(de.getDepositsUid());
			   s.setUser(cdao.getUserById(Long.valueOf(de.getCreatedBy()), intiId));
			   ds.add(s);
			}
			//System.out.println("ds.size()::"+ds.size());
			list.add(new BusinessMessage("No pending deposits for this month."));
			res.setServiceStatus(ServiceStatus.SUCCESS);
			res.setBusinessMessage(list);
			res.setSuccessobj(ds);
		}
			dtoList.setDepositdto(dto);
			res.setObj(dtoList);
		

		return res;
	}
	
	public CommonResponse getLoanDetails(long custId,long institutionId) {
		CommonResponse res=new CommonResponse();
		List<BusinessMessage> list1=new ArrayList<>();
		List<LoanDto> dtolist=new ArrayList<>();
		LoanDtoList list=new LoanDtoList();
		List<DepositsSuccess> s11=new ArrayList<>();
		LoanDto dto=new LoanDto();
		double paidAmount;
		double paidInterest;
		Occurrance ooo=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Loans.getValue(), institutionId);	
		//Occurrance ooo=getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), institutionId, Status.Active.getValue());
		if(ooo == null)
		{
			list1.add(new BusinessMessage("Sangam is not started with loan configurations."));
			res.setServiceStatus(ServiceStatus.ERROR);
			res.setBusinessMessage(list1);;
			return res;
		}
		
		Loans loans=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where customer.customerUid="+custId+" and institution.institution_uid="+institutionId+" and noOfInstallments>paidInstallments").uniqueResult();
		if(loans != null && (loans.getNoOfInstallments()>loans.getPaidInstallments()))
		{
			dto.setLoanId(loans.getLoans_uid());
			dto.setInitialLoanStatus("N");
			dto.setAmount(loans.getLoanPrinciple());
			dto.setCustomeId(loans.getCustomer().getCustomerUid());
			dto.setInterestTypeId(loans.getInterestsType().getInterestType_uid());
			dto.setIntroducer(loans.getIntroducer_id());
			dto.setNoOfInstallments(loans.getNoOfInstallments());
			dto.setOccurranceId(loans.getOccurrance().getOccurranceUid());
			paidAmount=getLoanHistoryList(loans.getLoans_uid(), institutionId).stream().filter(e -> e.getPaidStatus().equalsIgnoreCase("Y")).mapToDouble(e -> e.getPaidAmount()).sum();
			paidInterest=getLoanHistoryList(loans.getLoans_uid(), institutionId).stream().filter(e -> e.getPaidStatus().equalsIgnoreCase("Y")).mapToDouble(e -> e.getPaidInterest()).sum();
			//paidAmount=loans.getLoanHistory().stream().filter(e -> e.getPaidStatus().equalsIgnoreCase("Y")).mapToDouble(e -> e.getPaidAmount()).sum();
			//paidInterest=loans.getLoanHistory().stream().filter(e -> e.getPaidStatus().equalsIgnoreCase("Y")).mapToDouble(e -> e.getPaidInterest()).sum();
			
			//System.out.println("paidAmount::::"+paidAmount);
			dto.setPaidAmount(paidAmount);
			dto.setPaidInterest(paidInterest);
			dto.setPaidInstallments(loans.getPaidInstallments());
			dto.setRemainingAmount((loans.getLoanPrinciple()-paidAmount));
			dto.setRemainingInstallments(loans.getNoOfInstallments()-loans.getPaidInstallments());
			dto.setLoanSanctionDate(loans.getOccurrance().getCoveredMonth().toString());
			int time=-2;
			double fine=0;
			List<Occurrance> occur=getUnmatchedOccurrancesForLoans(loans.getLoans_uid(),TransactionTypeE.Loans.getValue(),Status.Active.getValue(),loans.getOccurrance().getOccurranceUid(), institutionId);
			double amountperInstallment=loans.getLoanPrinciple()/loans.getNoOfInstallments();
			for(Occurrance o1:occur)
			{
				time++;
			}
			//System.out.println(loans.getLoanPrinciple()+","+time+","+loans.getInterestsType().getInterestRate());
			double inteest=simpleInterest((loans.getLoanPrinciple()-paidAmount),time<0?0:time , loans.getInterestsType().getInterestRate());
			//System.out.println("inteest::"+inteest);
			//dto.setProposedPayAmount(amountperInstallment);
			dto.setProposedPayInterest(inteest);
			List<LoanHistory> llist=getLoanHistoryListByPaidStatus(loans.getLoans_uid(), "N", institutionId);
			//System.out.println("llist.size:"+llist.size());
			List<PendingLoansDto> ilist=new ArrayList<>();
			int k=0;
			
			if(occur.size()>1 && loans.getPaidInstallments()==0)
			   k=occur.size()-1;
			else
				k=occur.size();
			
			//System.out.println("k:"+k);
			int i=0;
			double remainingAmount=loans.getLoanPrinciple()-paidAmount;
			double compoundInterest=0;
			for(LoanHistory l:llist)
			{
				i++;
				
				
				PendingLoansDto p=new PendingLoansDto();
				p.setMonth(l.getInstallmentId());
				p.setAmount(amountperInstallment);
				if(i<k && loans.getOccurrance().getOccurranceUid() < ooo.getOccurranceUid()) {
						dto.setProposedPayAmount(dto.getProposedPayAmount()+amountperInstallment);
					    compoundInterest=simpleInterest(remainingAmount,1, loans.getInterestsType().getInterestRate());
						p.setInterest(simpleInterest(remainingAmount,1, loans.getInterestsType().getInterestRate()));
						if(i<k) {
						if(loans.getOccurrance().getInterestType().getInterestType().equalsIgnoreCase(String.valueOf(InterestTypeE.Compound.getValue())))
						   p.setFine((amountperInstallment*(k-i))*(loans.getInterestsType().getFine())/100);
						else
						   p.setFine((amountperInstallment)*(loans.getInterestsType().getFine())/100);
						}
				        p.setStatus("true");
				}
				if(i==k && loans.getOccurrance().getOccurranceUid() < ooo.getOccurranceUid()) {
					//System.out.println(i+" == "+k+"  "+custId+" "+loans.getOccurrance().getCoveredMonth()+"  "+ooo.getCoveredMonth()+" grrr "+loans.getOccurrance().getCoveredMonth().compareTo(ooo.getCoveredMonth()));
					if(loans.getOccurrance().getCoveredMonth().compareTo(ooo.getCoveredMonth())<0)
					{
						//System.out.println("enetered here:"+simpleInterest(remainingAmount,1, loans.getInterestsType().getInterestRate()));
					 p.setStatus("true");
					 p.setFine(0);
					 p.setInterest(simpleInterest(remainingAmount,1, loans.getInterestsType().getInterestRate()));
					}
					else {
						//System.out.println("enetered false here:");
						p.setStatus("false");
						 p.setFine(0);
						 p.setInterest(0);
					}
					
					
					 dto.setProposedPayAmount(dto.getProposedPayAmount()+amountperInstallment);
				}
				
			  ilist.add(p);
			}
			dto.setRemainingInstallmentsList(ilist);
			
			List<LoanHistory> lh= getLoanHistoryListByLoanAndOccurranceId(loans.getLoans_uid() ,ooo.getOccurranceUid(), "Y", institutionId);
			   int lhsize=lh.size();
			for(LoanHistory sp:lh) {
			   DepositsSuccess s111=new DepositsSuccess();
			   s111.setAmount(sp.getPaidAmount());
			   if(lhsize !=1 && sp != null && sp.getLedger() != null)
			   {
				   Occurrance uuocc=getOccuranceByLedger(TransactionTypeE.Loans.getValue(), sp.getLedger().getLedger_uid()-lhsize+1, institutionId);
				   if(uuocc != null && uuocc.getCoveredMonth() != null)
				   s111.setCoveredMonth(uuocc.getCoveredMonth().toString());
			   }
			   if(lhsize == 1 && sp.getOccurrance() != null && sp.getOccurrance().getCoveredMonth() != null)
			   {
				   s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
			   }
			   
			   //s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
			   s111.setCustomer(sp.getLoans().getCustomer());
			   s111.setFine(sp.getFine());
			   s111.setInstitution(sp.getInstitution());
			   s111.setTransactionId(sp.getLoanHistory_uid());
			   s111.setInterest(sp.getPaidInterest());
			   s111.setInstallmentNo((int)sp.getInstallmentId());
			   s11.add(s111);
			   lhsize--;
			   if(lhsize == 0)
			   {
				   DepositsSuccess s11w1=new DepositsSuccess();
					s11w1.setRemainingloanamount((loans.getLoanPrinciple()/loans.getNoOfInstallments())*(loans.getNoOfInstallments()-loans.getPaidInstallments()));
					s11w1.setIndicator("remainingamount");
					s11.add(s11w1);  
			   }
			  
			}
		}
		else {
			//System.out.println("CustomerId::"+custId+" institutionId::"+institutionId);
			dto.setInitialLoanStatus("Y");
			dto.setCustomeId(custId);
			dto.setInterestTypeId(getInterestTypeByTransactionType(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), institutionId).getInterestType_uid());
			dto.setOccurranceId(ooo != null?ooo.getOccurranceUid():null);
			dto.setCustomerlist(getCustomerList(Status.Active.getValue(),institutionId).stream().filter(e->e.getCustomer_uid() != custId).collect(Collectors.toList()));
		
			long loanid=0;
			Optional<LoanHistory> lhh=getLoanHistoryByOccurrance(ooo.getOccurranceUid(), "Y", institutionId).stream().findFirst();
			if(lhh.isPresent())
			     loanid=lhh.get().getLoans().getLoans_uid();
			
			Loans iiii=getLoanById(loanid, institutionId);    //ByCustomerAndStatus(ooo.getOccurranceUid(), custId, institutionId);
			if(iiii != null && iiii.getPaidInstallments() >= iiii.getNoOfInstallments())
			{
				int lhsize=iiii.getLoanHistory().size();
				for(LoanHistory sp:iiii.getLoanHistory()) {
				   DepositsSuccess s111=new DepositsSuccess();
				   s111.setAmount(sp.getPaidAmount());
				   if(lhsize != 1 && sp != null && sp.getLedger() != null)
				   {
					   Occurrance uuocc=getOccuranceByLedger(TransactionTypeE.Loans.getValue(), sp.getLedger().getLedger_uid()-lhsize+1, institutionId);
					   if(uuocc != null && uuocc.getCoveredMonth() != null)
					   s111.setCoveredMonth(uuocc.getCoveredMonth().toString());
				   }
				   if(lhsize == 1 && sp.getOccurrance() != null && sp.getOccurrance().getCoveredMonth() != null)
				   {
					   s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
				   }
				   s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
				   s111.setCustomer(sp.getLoans().getCustomer());
				   s111.setFine(sp.getFine());
				   s111.setInstitution(sp.getInstitution());
				   s111.setTransactionId(sp.getLoanHistory_uid());
				   s111.setInterest(sp.getPaidInterest());
				   s111.setInstallmentNo((int)sp.getInstallmentId());
				   s111.setNote("Completed");
				   s11.add(s111);
				   lhsize--;
				   if(lhsize == 0)
				   {
					   DepositsSuccess s11w1=new DepositsSuccess();
						s11w1.setRemainingloanamount((loans.getLoanPrinciple()/loans.getNoOfInstallments())*(loans.getNoOfInstallments()-loans.getPaidInstallments()));
						s11w1.setIndicator("remainingamount");
						s11.add(s11w1);  
				   }
				   
				}
				
			}
			res.setFrom("loansPaid");
		
		
		}
		//System.out.println("dtttt:"+dto.getRemainingInstallmentsList().size());
		dtolist.add(dto);
		list.setLoanDto(dtolist);
		res.setServiceStatus(ServiceStatus.SUCCESS);
		res.setObj(list);
		res.setSuccessobj(s11);
		return res;
	}
	
	public CommonResponse saveloans(LoanDtoList loanDtoList,long userId,long institutionId) {
		List<BusinessMessage> bm=new ArrayList<>();
		CommonResponse r=new CommonResponse();
		r.setFrom(" ");
		List<DepositsSuccess> s11=new ArrayList<>();
		int count=0;
		List<PendingLoansDto> paidInstallmentsIds=new ArrayList<>();
		
		for(LoanDto loanDto : loanDtoList.getLoanDto()) {
		Loans s=getLoanById(loanDto.getLoanId(), institutionId);
		if(s != null)
		{
			
			//System.out.println("**tokenizer*****loanDto.getCurrentPaidInstallmentIds()**********");
			StringTokenizer st = new StringTokenizer(loanDto.getCurrentPaidInstallmentIds(),",");
			if(st != null)
			{
				 while (st.hasMoreTokens()) {
					 String[] st1 = st.nextToken().split("#");
						 PendingLoansDto p=new PendingLoansDto();
						 p.setInterest(Double.parseDouble(st1[1].toString()));
						 p.setMonth(Integer.parseInt(st1[0].toString()));
						 p.setFine(Double.parseDouble(st1[2].toString()));
					 paidInstallmentsIds.add(p);
				 }
				 List<LoanHistory> lh=new ArrayList<>();
				 for(PendingLoansDto i:paidInstallmentsIds) {
//					 if(count == 0) {
//						 pInterest=loanDto.getProposedPayInterest(); 
//					 }
//					 else {
//						 pamount=0;
//						 pInterest=0;
//					 }
					 //System.out.println(loanDto.getLoanId()+" , "+i.getMonth()+" , "+(loanDto.getAmount()/loanDto.getNoOfInstallments())+" , "+i.getInterest()+","+i.getFine()+" , "+institutionId);
					 LoanHistory b=updateLoanHistory(getCustomer(loanDto.getCustomeId(),Status.Active.getValue(),institutionId),loanDto.getLoanId(),i.getMonth(),(loanDto.getAmount()/loanDto.getNoOfInstallments()),i.getInterest(),i.getFine(),institutionId);
					 lh.add(b);
					 if(b != null)
						count++;
				 }
				 s.setPaidInstallments(s.getPaidInstallments()+count);
				 sessionFactory.getCurrentSession().update(s);
				 r.setServiceStatus(ServiceStatus.SUCCESS);
				 bm.add(new BusinessMessage("Loan details successfully updated"));
				 r.setBusinessMessage(bm);
				 
					//List<LoanHistory> lh= getLoanHistory  ByOccurrance(getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), institutionId).getOccurranceUid(), "Y", institutionId);
				double remainingAmount=(s.getLoanPrinciple()/s.getNoOfInstallments())*(s.getNoOfInstallments()-s.getPaidInstallments());
				 if(lh.size()>0) {
				 for(LoanHistory sp:lh) {
						if(sp.getLoans().getCustomer().getCustomer_uid() == loanDto.getCustomeId()) {
						   DepositsSuccess s111=new DepositsSuccess();
						   s111.setAmount(sp.getPaidAmount());
						   if(sp != null && sp.getOccurrance() != null && sp.getOccurrance().getCoveredMonth() != null)
						   s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
						   s111.setCustomer(sp.getLoans().getCustomer());
						   s111.setFine(sp.getFine());
						   s111.setInstitution(sp.getInstitution());
						   s111.setTransactionId(sp.getLoanHistory_uid());
						   s111.setInterest(sp.getPaidInterest());
						   s111.setInstallmentNo((int)sp.getInstallmentId());
						   s111.setRemainingloanamount(remainingAmount);
						   s11.add(s111);
						   
						}
					}
			}
				Loans iiii=getLoanById(s.getLoans_uid(), institutionId);
				if(iiii.getPaidInstallments() >= iiii.getNoOfInstallments())
				{
					for(LoanHistory sp:iiii.getLoanHistory()) {
					   DepositsSuccess s111=new DepositsSuccess();
					   s111.setAmount(sp.getPaidAmount());
					   if(sp != null && sp.getOccurrance() != null && sp.getOccurrance().getCoveredMonth() != null)
					   s111.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
					   s111.setCustomer(sp.getLoans().getCustomer());
					   s111.setFine(sp.getFine());
					   s111.setInstitution(sp.getInstitution());
					   s111.setTransactionId(sp.getLoanHistory_uid());
					   s111.setInterest(sp.getPaidInterest());
					   s111.setInstallmentNo((int)sp.getInstallmentId());
					   s111.setNote("Completed");
					   s111.setRemainingloanamount(remainingAmount);
					   s11.add(s111);
					}
					
				}
				r.setFrom("loansPaid");
					r.setSuccessobj(s11);
			}
			
			 
		}
		else {
			//System.out.println("******loanIntiaioo********");
			if(loanDto.getNoOfInstallments()<=0)
			{
				r.setServiceStatus(ServiceStatus.ERROR);
				bm.add(new BusinessMessage("Minimum No.Of installemnts are 1."));
				r.setBusinessMessage(bm);
				r.setObj(getLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
				return r;
			}
			
			if(loanDto.getIntroducer() == 0)
			{
				r.setServiceStatus(ServiceStatus.ERROR);
				bm.add(new BusinessMessage("Introducer is required."));
				r.setBusinessMessage(bm);
				r.setObj(getLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
				return r;
			}
			
			Ledger le=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
			if(le.getBalance() >= loanDto.getAmount()) {
			le.setBalance(le.getBalance()-loanDto.getAmount());
			sessionFactory.getCurrentSession().update(le);
			}
			else {
				r.setServiceStatus(ServiceStatus.ERROR);
				bm.add(new BusinessMessage("No Sufficient funds to offer loan."));
				r.setBusinessMessage(bm);
				r.setObj(getLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
				return r;
			}
			
			if(loanDto.getAmount()>0 && loanDto.getNoOfInstallments()>0 && loanDto.getAmount()>loanDto.getNoOfInstallments())
			{
				Loans l=new Loans();
				l.setCreatedBy(String.valueOf(userId));
				l.setCustomer(getCustomer(loanDto.getCustomeId(),Status.Active.getValue(),institutionId));
				l.setInterestsType(getInterestTypeByTransactionType(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), institutionId));
				//if(loanDto.getIntroducer() != null)
				  l.setIntroducer_id((int)getCustomer(loanDto.getIntroducer(),Status.Active.getValue(),institutionId).getCustomer_uid());
				l.setLoan_paid_date(LocalDateTime.now());
				l.setInstitution(getInstitution(institutionId));
				//if(loanDto.getAmount() != null)
				  l.setLoanPrinciple(loanDto.getAmount());
				//if(loanDto.getNoOfInstallments() !=  null)
				  l.setNoOfInstallments(loanDto.getNoOfInstallments());
				//if(loanDto.getOccurranceId() != null)
				  l.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), institutionId, Status.Active.getValue()));
				//if(loanDto.getPaidInstallments() != null)
				  l.setPaidInstallments(loanDto.getPaidInstallments());
				l.setUpdatedBy(String.valueOf(userId));
				l.setLedger(le);
				long c=(long)sessionFactory.getCurrentSession().save(l);
				//System.out.println("loans::"+c);
				List<LoanHistory> lh=getLoanHistoryList(c, institutionId);
				if(lh.size() == 0) {
					int noOfInstallments=loanDto.getNoOfInstallments();
					for(int i=1;i<=noOfInstallments;i++)
					{
						LoanHistory h=new LoanHistory();
						h.setLoanPaidDate(LocalDateTime.now());
						//if(loanDto.getPaidAmount() != null)
						h.setPaidAmount(loanDto.getPaidAmount());
						//if(loanDto.getPaidInterest() != null)
						h.setPaidInterest(loanDto.getPaidInterest());
						h.setInstitution(getInstitution(institutionId));
						h.setInstallmentId(i);
						h.setPaidStatus("N");
						h.setLoans(l);
						sessionFactory.getCurrentSession().save(h);
					}
					r.setServiceStatus(ServiceStatus.SUCCESS);
					 bm.add(new BusinessMessage("Loan details successfully saved"));
					 r.setBusinessMessage(bm);
			}
				
				Loans l1=getLoanById(c, institutionId);
				//System.out.println("l1::::"+l1);
				if(l1 != null)
				{
				  DepositsSuccess s111=new DepositsSuccess();
				   s111.setAmount(l1.getLoanPrinciple());
				   s111.setCoveredMonth(l1.getOccurrance().getCoveredMonth().toString());
				   s111.setCustomer(l1.getCustomer());
				   s111.setInstitution(l1.getInstitution());
				   s111.setTransactionId(c);
				   s111.setIntroducer(getCustomer(l1.getIntroducer_id(),Status.Active.getValue(), institutionId));
				   s111.setLoanIssuedTime(l1.getLoan_paid_date().toString());
				   s111.setInstallmentNo((int)l1.getNoOfInstallments());
				   s111.setInterest(l1.getInterestsType().getInterestRate());
				   s111.setFine(l1.getInterestsType().getFine());
				   s11.add(s111);
				   r.setSuccessobj(s11);
				   r.setFrom("loansInitiate");
				   //System.out.println("r.getFrom()::"+r.getFrom());
				}
			
			}
			
			
			
		}
	    
	CommonResponse r1=getLoanDetails(loanDto.getCustomeId(),institutionId);
	r.setObj(r1.getObj());
		}
		//System.out.println("r.gtttttttttt:"+r.getFrom());
		return r;
	}

	@Override
	public List<DepositType> saveDepositType(DepositType depositType, long userId, long institutionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LoanHistory updateLoanHistory(Customer c,long loanId,long installmentId,double pamount,double pinterest,double pfine,long institutionId)
	{
		Ledger l=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
		l.setBalance(l.getBalance()+pamount+pinterest+pfine);
		sessionFactory.getCurrentSession().update(l);
		
		 LoanHistory lhist=getLoanHistoryByInstallmentId(loanId, installmentId, institutionId);
		 lhist.setPaidStatus("Y");
		 lhist.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), (int)institutionId));
		 lhist.setPaidAmount(pamount);
		 lhist.setPaidInterest(pinterest);
		 lhist.setFine(pfine);
		 lhist.setCustomer(c);
		 lhist.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), institutionId));
		 lhist.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), institutionId, Status.Active.getValue()));
		 sessionFactory.getCurrentSession().update(lhist);
		 return lhist;
	}

	@Override
	public List<MonthlyLoansSummaryDto> getMonthlyLoanSummaryList(long fromoccuranceId,long tooccuranceId,long institutionId) {
		List<MonthlyLoansSummaryDto> list=new ArrayList<>();
		List<Loans> loans=getLoansByOccurance(fromoccuranceId,tooccuranceId, institutionId);
		if(loans.size()>0) {
		for(Loans l:loans) {
			MonthlyLoansSummaryDto m=new MonthlyLoansSummaryDto();
			m.setCustomerId(l.getCustomer().getCustomerId());
			m.setCustomerName(l.getCustomer().getCustomerName());
			m.setCustomerSignature(l.getCustomer().getCustomerName());
			
			if(getCustomer(l.getIntroducer_id(),Status.Active.getValue(), institutionId) !=null) {
			m.setIntroducerId(getCustomer(l.getIntroducer_id(),Status.Active.getValue(), institutionId).getCustomerId());
			m.setIntroducerSignature(getCustomer(l.getIntroducer_id(),Status.Active.getValue(), institutionId).getCustomerName());
			m.setIntroducerName(getCustomer(l.getIntroducer_id(),Status.Active.getValue(), institutionId).getCustomerName());
			}
			m.setNoOfInstallments(l.getNoOfInstallments());
			m.setSimpleLoan(l.getLoanPrinciple());
			list.add(m);
		}
		}
		else {
			list.add(new MonthlyLoansSummaryDto());
		}
		
		
		return list;
	}
	
	
	@Override
	public List<Deposits> getMonthlyDepositsSummaryList(long fromoccuranceId,long toOccurranceId,long institutionId) {
		List<Deposits> deposits=getDepositsByOccurrance(fromoccuranceId,toOccurranceId, institutionId);
		return deposits;
	}

	@Override
	public Map<Long, String> getOccuranceIds(int tranId,long instId) {
		List<Occurrance> o=sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+tranId+" and institution.institution_uid="+instId+" order by occurranceUid desc").list();
		Map<Long, String> m=o.stream()
				            // .sorted(Comparator.comparingLong(Occurrance::getOccurranceUid))
				            // .sorted((e1,e2)->e1.getCoveredMonth().compareTo(e2.getCoveredMonth()))
				             .collect(Collectors.toMap(Occurrance::getOccurranceUid, c->c.getCoveredMonth().toString()));
		Map<Long, String> m1=m.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e2, TreeMap::new));
		return m1;
	}
	
	public List<Occurrance> getOccuranceList(int tranId,long instId) {
		List<Occurrance> o=sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+tranId+" and institution.institution_uid="+instId).list();
		//Map<Long, String> m=o.stream().collect(Collectors.toMap(Occurrance::getOccurranceUid, c->c.getCoveredMonth().toString()));
		return o;
	}
	
	public List<Occurrance> getOccuranceList(long ledgerId,int statusId,long instId) {
		List<Occurrance> o=sessionFactory.getCurrentSession().createQuery("from Occurrance where ledger.ledger_uid="+ledgerId+" and status="+statusId+" and institution.institution_uid="+instId).list();
		return o;
	}
	
	public List<Loans> getLoanByLedger(long ledgerId,long instId)
	{
		List<Loans> lhistory=sessionFactory.getCurrentSession().createQuery("from Loans where ledger.ledger_uid="+ledgerId+" and institution.institution_uid="+instId).list();
		return lhistory;
	}
	
	public List<LoanHistory> getLoanHistoryByOccurrance(long occurranceId,String paidStatus,long instId)
	{
		List<LoanHistory> lhistory=sessionFactory.getCurrentSession().createQuery("from LoanHistory where paidStatus='"+paidStatus+"' and occurrance.occurranceUid="+occurranceId+" and institution.institution_uid="+instId).list();
		return lhistory;
	}

	@Override
	public LedgerSummaryDto getLedgerSummary(long institutionId) {
		
		double interests = 0;
		double amount = 0;
		double fine = 0;
		
		Ledger l=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
		LedgerSummaryDto d=new LedgerSummaryDto();
		if(l != null)
		{
			Occurrance occ=getOccuranceByLedger(TransactionTypeE.Loans.getValue(), l.getLedger_uid(), institutionId);
			Occurrance spocc=getOccuranceByLedger(TransactionTypeE.Special_Loans.getValue(), l.getLedger_uid(), institutionId);
			//System.out.println("occc:"+occ);
				List<LoanHistory> ll = null;
				        if(occ != null)
						ll=getLoanHistoryByOccurrance(occ != null?occ.getOccurranceUid():null, "Y", institutionId);
				if(ll != null) {        
				for(LoanHistory h:ll)
				{
					//System.out.println("eneterrrr***"+h);
					interests=interests+h.getPaidInterest();
					amount=amount+h.getPaidAmount();
					fine=fine+h.getFine();
				}
				}
			
		//System.out.println("interests::"+interests+"  amount::"+amount+" fine::"+fine);
		d.setBalance(l.getBalance());
		d.setDepositsInterestsAmount(l.getDeposits().stream().mapToDouble(e->e.getInterestPaid()).sum());
		Occurrance o=getOccuranceByLedger(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), l.getLedger_uid(), institutionId);
		d.setCoveredMonth(o!=null?o.getCoveredMonth():LocalDate.now());
		d.setDepsitsAmount(l.getDeposits().stream().mapToDouble(e->e.getAmountPaid()).sum());
		d.setGivenLoansAmount(l.getLoans().stream().mapToDouble(e->e.getLoanPrinciple()).sum());
		//d.setLoanAmountPrinciples(l.getLoans().stream().mapToDouble(e->e.getLoanHistory().stream().mapToDouble(r->r.getPaidAmount()).sum()).sum());
		//d.setLoanInteretsAmount(l.getLoans().stream().mapToDouble(e->e.getLoanHistory().stream().mapToDouble(r->r.getPaidInterest()).sum()).sum());
		
		d.setLoanAmountPrinciples(amount);
		d.setLoanInteretsAmount(interests);
		d.setLoanFines(fine);
		
		d.setOpeningBalance(l.getOpeningBalance());
		d.setDepositFines(l.getDeposits().stream().mapToDouble(e->e.getFine()).sum());
		d.setAdditionalExpenses(getExpenseByLedger(l.getLedger_uid(), institutionId).stream().mapToDouble(e->e.getAmount()).sum());
		d.setApplicationforms(getOtherIncomeByLedger(l.getLedger_uid(), institutionId).stream().mapToDouble(e->e.getAmount()).sum());
		if(spocc != null) {
		d.setSpecialLoanPrinciples(getSPLoansByOccurance(spocc.getOccurranceUid(), institutionId).stream().mapToDouble(e->e.getLoanPrinciple()).sum());
		d.setSpecialLoanFines(getSpLoanHistoryList(spocc.getOccurranceUid(), "Y", institutionId).stream().mapToDouble(e->e.getFine()).sum());
		d.setSpecialLoanInterests(getSpLoanHistoryList(spocc.getOccurranceUid(), "Y", institutionId).stream().mapToDouble(e->e.getPaidInterest()).sum());
		d.setSpecialLoanPaidPrinciples(getSpLoanHistoryList(spocc.getOccurranceUid(), "Y", institutionId).stream().mapToDouble(e->e.getPaidAmount()).sum());
		List<Customer> ccc = null;
		if(o != null)
		 ccc=getCustomerListByOccurrance(Status.InActive.getValue(),o.getOccurranceUid(),institutionId);
		if(ccc != null && ccc.size()>0)
		d.setWithdrawals(ccc.stream().mapToDouble(e->e.getShare()).sum());
		//d.setLoanFines(l.getLoans().stream().mapToDouble(e->e.getLoanHistory().stream().mapToDouble(r->r.getFine()).sum()).sum());
		}
		
	    }
		
		return d;
	}

	@Override
	public CommonResponse settle(long institutionId) {
		
		
		CommonResponse cr=new CommonResponse();
		cr.setObj(getLedgerSummary(institutionId));
		
		Ledger l=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
		if(l!=null) {
//			double depositAmount=l.getDeposits().stream().mapToDouble(e->e.getAmountPaid()).sum();
//			double depositInterest=l.getDeposits().stream().mapToDouble(e->e.getInterestPaid()).sum();
//			double loanAmountpaidpriciples=l.getLoans().stream().mapToDouble(e->e.getLoanHistory().stream().mapToDouble(r->r.getPaidAmount()).sum()).sum();
//			double loanInterestAmount=l.getLoans().stream().mapToDouble(e->e.getLoanHistory().stream().mapToDouble(r->r.getPaidInterest()).sum()).sum();
//			double givenloansAmount=l.getLoans().stream().mapToDouble(e->e.getLoanPrinciple()).sum();
		l.setStatus(LedgerStatusE.Close.getValue());
		//if(Math.round(l.getBalance()) == Math.round((l.getOpeningBalance()-givenloansAmount)+(depositAmount+depositInterest+loanAmountpaidpriciples+loanInterestAmount)))
		//{
			l.setClosingBalance(l.getBalance());
		//}
		sessionFactory.getCurrentSession().update(l);
		
		Occurrance o=getOccuranceByLedger(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), l.getLedger_uid(), institutionId);
		if(o != null)
		{
			o.setStatus(Status.InActive.getValue());
			sessionFactory.getCurrentSession().update(o);
		}
		
		Occurrance o1=getOccuranceByLedger(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), l.getLedger_uid(), institutionId);
		if(o1 != null)
		{
			o1.setStatus(Status.InActive.getValue());
			sessionFactory.getCurrentSession().update(o1);
		}
		
		Occurrance o11=getOccuranceByLedger(Status.Active.getValue(), TransactionTypeE.Special_Loans.getValue(), l.getLedger_uid(), institutionId);
		if(o11 != null)
		{
			o11.setStatus(Status.InActive.getValue());
			sessionFactory.getCurrentSession().update(o11);
		}
		
//		Occurrance o111=getOccuranceByLedger(Status.Active.getValue(), TransactionTypeE.Application_Forms.getValue(), l.getLedger_uid(), institutionId);
//		if(o111 != null)
//		{
//			o111.setStatus(Status.InActive.getValue());
//			sessionFactory.getCurrentSession().update(o111);
//		}
		
		}
		
		List<BusinessMessage> bm=new ArrayList<>();
		cr.setServiceStatus(ServiceStatus.SUCCESS);
		bm.add(new BusinessMessage("Successfully Settled"));
		cr.setBusinessMessage(bm);
		cr.setObj(getLedgerSummary(institutionId));
		
		return cr;
	}

	@Override
	public Occurrance getActiveOccurrance(int statusId, int tranId, long instId) {
		Occurrance o=(Occurrance) sessionFactory.getCurrentSession().createQuery("from Occurrance where status="+statusId+" and transactionType="+tranId+" and institution.institution_uid="+instId).uniqueResult();
		return o;
	}
	
	public Occurrance getActiveOccurranceForSheduedMsg(String statusId, int tranId, long instId) {
		Occurrance o=(Occurrance) sessionFactory.getCurrentSession().createQuery("from Occurrance where status in ("+statusId+") and transactionType="+tranId+" and institution.institution_uid="+instId).uniqueResult();
		return o;
	}
	
	
	public Occurrance getLastOccurranceByStatus(int transactionType,int status,long instid)
	{
		Occurrance a=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where occurranceUid=( select max(occurranceUid) from Occurrance where status="+status+" and transactionType="+transactionType+" and institution.institution_uid="+instid+")").uniqueResult();
		return a;
	}
	
	
	public String getPdfGenerator(String servletContext,long instId)
	{

		
		List<Customer> clistt=getCustomerList(Status.Active.getValue(),instId);
		List<PdfDto> pdflist=new ArrayList<>();
		int ii=0;
		for(Customer c:clistt)
		{
			ii++;
			PdfDto p = new PdfDto();
			p.setCustomerName(c.getCustomerName());
			p.setContactNo(c.getPhoneNo());
			p.setCustomerUid(c.getCustomerUid());
			InterestType i=getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(), Status.Active.getValue(), instId);
			p.setMonthlyShare(i.getIdealDepositAmount());
			p.setMonthlySharefine(c.getDeposits().stream().mapToDouble(e->e.getFine()).sum());
			p.setDeposits(getDepositsList(c.getCustomer_uid(), instId).stream().collect(Collectors.toSet()));
			Loans l=getLoanByCustomerAndStatus(0,c.getCustomer_uid(), instId);
			p.setInstallments(String.valueOf(l.getNoOfInstallments()));
			p.setMonthlyInstallment(l.getLoanPrinciple()/l.getNoOfInstallments());
			p.setMonthlyInstallmentinterest(l.getLoanHistory().stream().mapToDouble(e->e.getPaidInterest()).sum());
			p.setMonthlyInstallmentloanFine(l.getLoanHistory().stream().mapToDouble(e->e.getFine()).sum());
			p.setDue((l.getNoOfInstallments()-l.getPaidInstallments())*(l.getLoanPrinciple()/l.getNoOfInstallments()));
			//p.setlTDue(lTDue);
		    //p.setOthers(others);
			p.setsNo(ii);
			//p.setSpecialLoan(specialLoan);
			//p.setSpecialLoanFine(specialLoanFine);
			//p.setSpecialLoanInterest(specialLoanInterest);
			//p.setStDue(stDue);
			//p.setTotal(total);
			p.setInstitution(getInstitution(instId));
			pdflist.add(p);
			
		}
		//System.out.println("pdflst::"+pdflist);
		

        //createPDF(getInstitution(instId).getInstitutionName()+"_"+getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), instId).getCoveredMonth(),getInstitution(instId));
		createPDF("YadavaSangam_2020-07-01"+".pdf",getInstitution(2));
		//System.out.println("generated");

		
	
		
		return servletContext+"a.pdf";
	}
	
	
	
	
	
	
	private void createPDF (String pdfFilename,Institution institution){
		
		
		 
		  Document doc = new Document();
		  PdfWriter docWriter = null;
		 
		  DecimalFormat df = new DecimalFormat("0.00");
		 
		  try {
		    
		   //special font sizes
		   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
		 
		   //file path
		   String path = "G:\\pdf\\" + pdfFilename;
		   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
		    
		   //document header attributes
		   doc.addAuthor("betterThanZero");
		   doc.addCreationDate();
		   doc.addProducer();
		   doc.addCreator("MySampleCode.com");
		   doc.addTitle("Report with Column Headings");
		   doc.setPageSize(PageSize.LETTER);
		   
		   //open document
		   doc.open();
		 
		   //create a paragraph
		   Paragraph paragraph = new Paragraph(""+institution.getInstitutionName()+" \n "+institution.getAddress_id().getVillage()+" \n "+institution.getAddress_id().getDistrict()+" \n "+institution.getAddress_id().getMandal()+" \n "+institution.getAddress_id().getState());
		    
		    
		   //specify column widths
		   float[] columnWidths = {1.5f, 2f, 5f, 2f};
		   //create PDF table with the given widths
		   PdfPTable table = new PdfPTable(columnWidths);
		   // set table width a percentage of the page width
		   table.setWidthPercentage(90f);
		 
		   //insert column headings
		   insertCell(table, "S.No", Element.ALIGN_RIGHT, 1, bfBold12);
		   insertCell(table, "Name", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Monthly Share", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Fine", Element.ALIGN_RIGHT, 1, bfBold12);
		   
		   insertCell(table, "Installment", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Monthly Inst", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "interest", Element.ALIGN_RIGHT, 1, bfBold12);
		   insertCell(table, "Fine", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "SpecialLoan", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Interest", Element.ALIGN_RIGHT, 1, bfBold12);
		   
		   insertCell(table, "FIne", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Others", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Total", Element.ALIGN_RIGHT, 1, bfBold12);
		   
		   insertCell(table, "LoanAmount", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Due", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "LT Due", Element.ALIGN_RIGHT, 1, bfBold12);
		   
		   insertCell(table, "ST Due", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Cont. No", Element.ALIGN_LEFT, 1, bfBold12);
		   table.setHeaderRows(1);
		 
		   //insert an empty row
//		   insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
//		   //create section heading by cell merging
//		   insertCell(table, "New York Orders ...", Element.ALIGN_LEFT, 4, bfBold12);
//		   double orderTotal, total = 0;
//		    
//		   //just some random data to fill 
//		   for(int x=1; x<5; x++){
//		     
//		    insertCell(table, "10010" + x, Element.ALIGN_RIGHT, 1, bf12);
//		    insertCell(table, "ABC00" + x, Element.ALIGN_LEFT, 1, bf12);
//		    insertCell(table, "This is Customer Number ABC00" + x, Element.ALIGN_LEFT, 1, bf12);
//		     
//		    orderTotal = Double.valueOf(df.format(Math.random() * 1000));
//		    total = total + orderTotal;
//		    insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);
//		     
//		   }
//		   //merge the cells to create a footer for that section
//		   insertCell(table, "New York Total...", Element.ALIGN_RIGHT, 3, bfBold12);
//		   insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);
//		    
//		   //repeat the same as above to display another location
//		   insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
//		   insertCell(table, "California Orders ...", Element.ALIGN_LEFT, 4, bfBold12);
//		   orderTotal = 0;
//		    
//		   for(int x=1; x<7; x++){
//		     
//		    insertCell(table, "20020" + x, Element.ALIGN_RIGHT, 1, bf12);
//		    insertCell(table, "XYZ00" + x, Element.ALIGN_LEFT, 1, bf12);
//		    insertCell(table, "This is Customer Number XYZ00" + x, Element.ALIGN_LEFT, 1, bf12);
//		     
//		    orderTotal = Double.valueOf(df.format(Math.random() * 1000));
//		    total = total + orderTotal;
//		    insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);
//		     
//		   }
//		   insertCell(table, "California Total...", Element.ALIGN_RIGHT, 3, bfBold12);
//		   insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);
		    
		   //add the PDF table to the paragraph 
		   paragraph.add(table);
		   // add the paragraph to the document
		   doc.add(paragraph);
		 
		  }
		  catch (DocumentException dex)
		  {
		   dex.printStackTrace();
		  }
		  catch (Exception ex)
		  {
		   ex.printStackTrace();
		  }
		  finally
		  {
		   if (doc != null){
		    //close the document
		    doc.close();
		   }
		   if (docWriter != null){
		    //close the writer
		    docWriter.close();
		   }
		  }
		 }
		  
		 private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		   
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		   
		 }

		@Override
		public List<PdfDto> personalSummary(long fromOccurranceId, long toOccurranceId, long instId) {
			
			//System.out.println(fromOccurranceId+"    "+toOccurranceId+"   "+instId);
			InterestType i=getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(), Status.Active.getValue(), instId);
			List<Occurrance> occ = getOccuranceList(fromOccurranceId, toOccurranceId, instId);
			//System.out.println(" size::occ"+occ.size());
			List<Customer> clistt=getCustomerList(instId);
			List<PdfDto> pdflist=new ArrayList<>();
			int ii=0;
		    long spaidAmount=0;
			double paidAmount=0;
			int inId=0;
			double ppaid=0;
			double spprinciple=0;
			for(Customer c:clistt)
			{
				List<LoanHistory> lh=null;
				List<SpLoanHistory> sh=null;
				ii++;
				//System.out.println("c:"+c.getCustomerName());
				List<Loans> lll1=getLoanByCustomer(c.getCustomer_uid(), instId);
				SpecialLoan sp=getSpecialLoanByCust(c.getCustomer_uid(), instId);
				paidAmount=0;
				spaidAmount=0;
				spprinciple=0;
				inId=0;
				//if(sp != null) {
				for (Occurrance o : occ) {
					if(c.getOccurrance().getOccurranceUid() <= o.getOccurranceUid()) {
					PdfDto p = new PdfDto();
					Occurrance occurrance=getOccuranceByLedger(TransactionTypeE.Deposits.getValue(), o.getLedger().getLedger_uid(), instId);
					Occurrance loccurrance=getOccuranceByLedger(TransactionTypeE.Loans.getValue(), o.getLedger().getLedger_uid(), instId);
					Occurrance sloccurrance=getOccuranceByLedger(TransactionTypeE.Special_Loans.getValue(), o.getLedger().getLedger_uid(), instId);
					//System.out.println(occurrance+  " "+loccurrance);
					List<Deposits> d=getDepositsList(occurrance.getOccurranceUid(), c.getCustomer_uid(), instId);
					//System.out.println("deposits: customer :"+d.size());
					p.setOccurranceId(o.getOccurranceUid());
					p.setCoveredMonth(o.getCoveredMonth().toString());
					p.setCustomerName(c.getCustomerName());
					p.setContactNo(c.getPhoneNo());
					p.setCustomerUid(c.getCustomerId());
					p.setMonthlyShare(d.stream().mapToDouble(e->e.getAmountPaid()).sum());
					p.setMonthlySharefine(d.stream().mapToDouble(e->e.getFine()).sum());
					p.setDeposits(d.stream().collect(Collectors.toSet()));
					Loans lc=getLoanByCustomerAndStatus(loccurrance.getOccurranceUid(), c.getCustomer_uid(), instId);
					//List<LoanHistory> lh=getLoanHistoryByOccurrance(loccurrance.getOccurranceUid(), "Y", instId);
					
					//lh=getLoanHistoryListByCustAndOccurranceId(c.getCustomer_uid(),loccurrance.getOccurranceUid(), "Y", instId);
					for(Loans lll:lll1) {
						String inst="";
						//paidAmount=0;
						inId=0;
						if(lll != null)
							lh=getLoanHistoryListByLoanAndOccurranceId(lll.getLoans_uid(),loccurrance.getOccurranceUid(), "Y", instId);
							
							if(lh != null && lh.size()>0) {
							for(LoanHistory l:lh)
							{
								//paidAmount=0; 
								if(l.getLoans().getCustomer().getCustomer_uid() == c.getCustomer_uid()) {
								p.setMonthlyInstallment(l.getLoans().getLoanPrinciple()/l.getLoans().getNoOfInstallments());
								p.setsNo(ii);
								p.setInstitution(getInstitution(instId));
								inst=inst+","+l.getInstallmentId();
								inId=(int) l.getInstallmentId();
								}
							}
							p.setInstallments(inst!=""?inst.substring(1,inst.length()):inst);
							p.setMonthlyInstallmentinterest(lh.stream().mapToDouble(e->e.getPaidInterest()).sum());
							p.setMonthlyInstallmentloanFine(lh.stream().mapToDouble(e->e.getFine()).sum());
							
							//paidAmount=paidAmount+lh.stream().mapToDouble(e->e.getPaidAmount()).sum();
							paidAmount=(inId*(lll.getLoanPrinciple()/lll.getNoOfInstallments()));
							}
							    if(lll != null && lll.getOccurrance().getOccurranceUid() <= loccurrance.getOccurranceUid())
							    {
							    	if(lll.getNoOfInstallments()>lll.getPaidInstallments())
							    	  p.setLoanAmount(lll.getLoanPrinciple());
							         p.setDue(lll.getLoanPrinciple()-paidAmount);
							    }
						
					}
					
					                spprinciple=0;
								    if(sp != null && sp.getOccurrance().getOccurranceUid() == sloccurrance.getOccurranceUid())
								    p.setStDue(sp.getLoanPrinciple());
								    

								    if(sp != null)
										sh=getSpLoanHistoryByCustIdAndOcc(c.getCustomer_uid(),sloccurrance.getOccurranceUid(), "Y", instId);
									if(sh != null && sh.size()>0) {
									p.setSpecialLoan(sh.stream().mapToDouble(e->e.getPaidAmount()).sum());
									p.setSpecialLoanInterest(sh.stream().mapToDouble(e->e.getPaidInterest()).sum());
									p.setMonthlyInstallmentloanFine(sh.stream().mapToDouble(e->e.getFine()).sum());
									spaidAmount=(long) (spaidAmount+sh.stream().mapToDouble(e->e.getPaidAmount()).sum());
									spprinciple=sh.stream().mapToDouble(e->e.getPaidAmount()).sum();
									p.setStDue(0);
									}
									
								   
					   
				pdflist.add(p);	
				}
				}
			}
			return pdflist;
		
		}

		@Override
		public List<AuditDto> auditSummary(long fromOccurranceId, long toOccurranceId, long instId) {
			
			InterestType i=getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(), Status.Active.getValue(), instId);
			List<Occurrance> occ = getOccuranceList(fromOccurranceId, toOccurranceId, instId);
			
			List<Customer> clistt=getCustomerList(Status.Active.getValue(),instId);
			List<PdfDto> pdflist=new ArrayList<>();
			List<AuditDto> auditList=new ArrayList<>();
			int ii=1;
			double depositFines=0;
			double normalLoanFines=0;
			double specialLoanFines=0;
			
			double normalLoanInterests=0;
			double specialLoanInterests=0;
			double normalLoans=0;
			double specialLoans=0;
			
			 double openingBalance = 0;
			 List<Deposits> deposits=new ArrayList<>();
			 List<Loans> loans=new ArrayList<>();
			 List<LoanHistory> loansHistory=new ArrayList<>();
			 List<SpecialLoan> sp=new ArrayList<>();
			 List<SpLoanHistory> specialLoanHistory=new ArrayList<>();
			 List<Expenses> ex=new ArrayList<>();
			 List<OtherIncome> otherIncome=new ArrayList<>();
			 List<Customer> cList=new ArrayList<>();
			 List<Customer> withdrawcList=new ArrayList<>();
			 double monthlyDepositsAmount= 0;;
			 double rlCanMonthlyDepositsAmount= 0;
			 double newEnrollmentAmounts= 0;
			 double fines= 0;
			 double normalLoanInterestsAmount= 0;
			 double specialLoanInterestsAmount= 0;
			 double applicationFormsAmount= 0;
			 double normalloans= 0;
			 double specialloans= 0;
			 double unpaidLoansAmount= 0;
			 double expenses= 0;
			 double currentBalance= 0;
			 double nlafterinstallmentDeduction = 0;
			 double spLoanFInes=0;
			 double repaymentSpLoans=0;
			 double repaymentLoans=0;
			 double withdrawalAmount=0;
			 Occurrance occurrancesploans=null;
			 List<LoanHistory> lh=null;
			 AuditDto p = new AuditDto();
//			for(Customer c:clistt)
//			{
//				Loans lcust=getLoanByCustomer(c.getCustomer_uid(), instId);
//				
				
				for (Occurrance o : occ) {
					List<Deposits> temp=new ArrayList<>();
					if(ii == 1)
					{
						openingBalance=o.getLedger().getOpeningBalance();	
					}
					Occurrance occurrance=getOccuranceByLedger(TransactionTypeE.Deposits.getValue(), o.getLedger().getLedger_uid(), instId);
					Occurrance occurranceloans=getOccuranceByLedger(TransactionTypeE.Loans.getValue(), o.getLedger().getLedger_uid(), instId);
					occurrancesploans=getOccuranceByLedger(TransactionTypeE.Special_Loans.getValue(), o.getLedger().getLedger_uid(), instId);
					List<Deposits> d=getDepositsListByOccurrance(o.getLedger().getLedger_uid(),occurrance.getOccurranceUid(), instId);
					List<Deposits> dd=getDepositsListByLedgerNotInOccurrance(o.getLedger().getLedger_uid(), occurrance.getOccurranceUid(), instId);
					List<Loans> l=null;
					if(occurranceloans != null)
					 l=getLoansByOccurance(occurranceloans.getOccurranceUid(), instId);
					List<Customer> customerList=getCustomerListByOccurrance(Status.InActive.getValue(),occurrance.getOccurranceUid(),instId);
					withdrawcList.addAll(customerList);
					withdrawalAmount=withdrawalAmount+customerList.stream().mapToDouble(e->e.getShare()).sum();
					
					//List<LoanHistory> lh=getLoanHistoryByOccurrance(occurranceloans.getOccurranceUid(), "Y", instId);
				    ex=getExpenseByLedger(o.getLedger().getLedger_uid(), instId);
				    otherIncome=getOtherIncomeByLedger(o.getLedger().getLedger_uid(), instId);
					
				    temp.addAll(d);
				    temp.addAll(dd);
				    
					//deposits.addAll(d);
					//deposits.addAll(dd);
				    deposits.addAll(temp);
					clistt.addAll(customerList);
					List<SpecialLoan> sp1=null;
					if(occurrancesploans !=null)
					{
					sp1=getSPLoansByOccurance(occurrancesploans.getOccurranceUid(), instId);
					sp.addAll(sp1);
					}
					depositFines=depositFines+temp.stream().mapToDouble(e->e.getFine()).sum();
					
					if(occurranceloans != null)
					lh=getLoanHistoryByOccurrance(occurranceloans.getOccurranceUid(), "Y", instId);
					////System.out.println("lh.size()::"+lh.size());
					if(lh != null && lh.size()>0) {
					repaymentLoans=repaymentLoans+lh.stream().filter(e->e.getPaidStatus().equals("Y")).mapToDouble(e->e.getPaidAmount()).sum();
					
					for(Loans loan:l) {
				    //System.out.println("loanPrinciple::"+loan.getLoanPrinciple()+"   repaymentLoans:"+repaymentLoans);
				    normalLoans=normalLoans+loan.getLoanPrinciple();
				    nlafterinstallmentDeduction=nlafterinstallmentDeduction+(loan.getLoanPrinciple()-repaymentLoans);
					}
                   
				    normalLoanFines= normalLoanFines+lh.stream().mapToDouble(e->e.getFine()).sum();
				    normalLoanInterests=normalLoanInterests+lh.stream().mapToDouble(e->e.getPaidInterest()).sum();
				    loansHistory.addAll(lh);
                    }
					//System.out.println("nlafterinstallmentDeduction:"+nlafterinstallmentDeduction);
                    
					
					  
                      if(l != null)
					  loans.addAll(l);
					  monthlyDepositsAmount=monthlyDepositsAmount+temp.stream().filter(e->e.getDepositType() == com.stackroute.keepnote.dto.DepositType.Monthly_Share.getValue()).mapToDouble(e->e.getAmountPaid()).sum();
					  monthlyDepositsAmount=monthlyDepositsAmount+temp.stream().filter(e->e.getDepositType() == com.stackroute.keepnote.dto.DepositType.Previous_Pending.getValue()).mapToDouble(e->e.getAmountPaid()).sum();
					  rlCanMonthlyDepositsAmount=rlCanMonthlyDepositsAmount;
					  applicationFormsAmount=applicationFormsAmount+otherIncome.stream().mapToDouble(e->e.getAmount()).sum();
					  unpaidLoansAmount=unpaidLoansAmount;
					  newEnrollmentAmounts=newEnrollmentAmounts+temp.stream().filter(e->e.getDepositType() == com.stackroute.keepnote.dto.DepositType.Initial_Contribution.getValue()).mapToDouble(e->e.getAmountPaid()).sum();
					  if(sp1 != null)
					  specialloans=specialloans+sp1.stream().mapToDouble(e->e.getLoanPrinciple()).sum();
					  if(occurrancesploans != null) {
					  specialLoanInterestsAmount=specialLoanInterestsAmount+getSpLoanHistoryList(occurrancesploans.getOccurranceUid(), "Y", instId).stream().mapToDouble(e->e.getPaidInterest()).sum();;
					  spLoanFInes=spLoanFInes+getSpLoanHistoryList(occurrancesploans.getOccurranceUid(), "Y", instId).stream().mapToDouble(e->e.getFine()).sum();
					  repaymentSpLoans=repaymentSpLoans+getSpLoanHistoryList(occurrancesploans.getOccurranceUid(), "Y", instId).stream().mapToDouble(e->e.getPaidAmount()).sum();
					  specialLoanHistory.addAll(getSpLoanHistoryList(occurrancesploans.getOccurranceUid(), "Y", instId));
					  }
					  if(ex != null)
					  expenses=expenses+ex.stream().mapToDouble(e->e.getAmount()).sum();
					  
					  
					  currentBalance=o.getLedger().getBalance();
					  ii++;
				    
					
				}
			
			//}
				List<UnpaidCustomerDto> unpaidc=new ArrayList<>();
				Occurrance axOcc= Collections.max(getOccuranceList(TransactionTypeE.Deposits.getValue(), instId), Comparator.comparing(s -> s.getOccurranceUid()));
				for(Customer c:clistt)
    			{
//					List<Deposits> depp=getDepositsList(c.getCustomer_uid(), instId);
//					 Deposits maxDep = null;
//					if(depp != null && depp.size()>0)
//			         maxDep =  Collections.max(depp, Comparator.comparing(s -> s.getDepositsUid()));
//			        Occurrance axOcc= Collections.max(getOccuranceList(TransactionTypeE.Deposits.getValue(), instId), Comparator.comparing(s -> s.getOccurranceUid()));
//			        
//			        if(maxDep == null && getOccuranceList(TransactionTypeE.Deposits.getValue(), instId).size()>3)
//			        {
//			        	 UnpaidCustomerDto dto=new UnpaidCustomerDto();
//					        dto.setCustomer(c);
//					        unpaidc.add(dto);
//			        }else {
//			        	if(axOcc != null && maxDep != null) {
//					        Period diff = Period.between(
//					        		axOcc.getCoveredMonth().withDayOfMonth(1),
//					        		maxDep.getOccurrance().getCoveredMonth().withDayOfMonth(1));
//					        //System.out.println(diff.getMonths());
//						    if(diff != null && diff.getMonths()>3) {
//						        UnpaidCustomerDto dto=new UnpaidCustomerDto();
//						        dto.setCustomer(c);
//						        unpaidc.add(dto);
//							  }
//					        }
//			        }
					UnpaidCustomerDto dtoo=null;
					CommonResponse dres=searchOccurance(c.getCustomer_uid(), instId);
					if(dres != null && dres.getObj() != null)
					{
						DepositDtoList dto=(DepositDtoList) dres.getObj();
						List<DepositDto> dddto=dto.getDepositdto();
						if(dddto != null && dddto.size()>2)
						{
							 dtoo=new UnpaidCustomerDto();
					         dtoo.setCustomer(c);
							dtoo.setDepositAmount(dddto.stream().mapToDouble(e->e.getTotal()).sum());
							dtoo.setDepositPendingMonths(dddto.size());
						}
						
						
					}
			        
			        CommonResponse lres=getLoanDetails(c.getCustomer_uid(), instId);
					if(lres != null && lres.getObj() != null)
					{
						LoanDtoList dto=(LoanDtoList) lres.getObj();
						List<LoanDto> llldtao=dto.getLoanDto();
						for(LoanDto l:llldtao)
						{
						List<PendingLoansDto> pdtoll=l.getRemainingInstallmentsList();
						pdtoll=pdtoll.stream().filter(e->e.getStatus() != null && e.getStatus().equalsIgnoreCase("true")).collect(Collectors.toList());
						if(pdtoll != null && pdtoll.size()>2) {
							if(dtoo == null)
							dtoo=new UnpaidCustomerDto();
							
					        dtoo.setCustomer(c);
					    dtoo.setLoansAmount(pdtoll.stream().filter(e->e.getStatus() != null && e.getStatus().equalsIgnoreCase("true")).mapToDouble(e->e.getAmount()+e.getFine()+e.getInterest()).sum());
					    dtoo.setLoansPendingMonths(pdtoll.size());
						}
						}
					}
					
					CommonResponse sres=getSpecialLoanDetails(c.getCustomer_uid(), instId);
					if(sres != null && sres.getObj() != null)
					{
						LoanDtoList dto=(LoanDtoList) sres.getObj();
						List<LoanDto> llldtao=dto.getLoanDto();
							
							if(llldtao != null && llldtao.size()>0)
							{
								LoanDto ddd=Collections.max(llldtao, Comparator.comparing(s -> s.getOccurranceId()));
								//System.out.println("ddd::"+ddd+" axOcc::"+axOcc+" ddd.getCoveredMonth()::"+ddd.getCoveredMonth());
								if(ddd != null && axOcc != null && ddd.getCoveredMonth() != null)
								{
								     
									  Period diff = Period.between(ddd.getCoveredMonth().withDayOfMonth(1),axOcc.getCoveredMonth().withDayOfMonth(1));
										        //System.out.println("diff.getMonths():::"+diff.getMonths());
											    if(diff != null && diff.getMonths()>2) {
											    	if(dtoo == null)
											    	dtoo=new UnpaidCustomerDto();
											        dtoo.setCustomer(c);
													dtoo.setSpecialLoanAmount(llldtao.stream().mapToDouble(e->e.getProposedPayAmount()+e.getFine()+e.getProposedPayInterest()).sum());
													dtoo.setSpecialLoanPendingMonths(diff.getMonths());
												  }
								}
								
								
							}
						
					}
					if(dtoo != null)	
					unpaidc.add(dtoo);
    			}
				p.setUnpaidCustomerDtoList(unpaidc);
				p.setWithdrawalAmount(withdrawalAmount);
				p.setOpeningBalance(openingBalance);
				p.setMonthlyDepositsAmount(monthlyDepositsAmount);
				//p.setRlCanMonthlyDepositsAmount(rlCanMonthlyDepositsAmount);
				p.setNewEnrollmentAmounts(newEnrollmentAmounts);
				//p.setApplicationFormsAmount(applicationFormsAmount);
				p.setFines(depositFines+normalLoanFines+spLoanFInes);
				p.setNormalLoanInterestsAmount(normalLoanInterests);
				if(loans != null)
				p.setNormalloans(loans.stream().mapToDouble(e->e.getLoanPrinciple()).sum());
				//System.out.println("nlafterinstallmentDeduction: eeee:"+nlafterinstallmentDeduction);
				p.setnLafterInstallmentDeduction(nlafterinstallmentDeduction);
				p.setSpecialloans(specialloans);
				p.setSpecialLoanInterestsAmount(specialLoanInterestsAmount);
				p.setRepaymentLoans(repaymentLoans);
				p.setRepaymentSpecialLoans(repaymentSpLoans);
				//p.setUnpaidLoansAmount(getLoanDetails(custId, institutionId));
				p.setApplicationFormsAmount(applicationFormsAmount);
				p.setExpenses(expenses);
				p.setCurrentBalance(currentBalance);
				p.setDeposits(deposits);
				p.setLoans(loans);
				p.setSpecialLoan(sp);
				p.setLoansHistory(loansHistory);
				p.setSpecialLoanHistory(specialLoanHistory);
				//p.setSpecialLoanHistory(getSpLoanHistoryList(occurrancesploans.getOccurranceUid(), "Y", instId));
				p.setOtherIncomeList(otherIncome);
				p.setExpenseslist(ex);
				p.setCustomerList(cList);
				p.setWithdrawalCustomerList(withdrawcList);
				//System.out.println("withdrawcList.size()::"+withdrawcList.size());
				
				auditList.add(p);
			//}
				
			//System.out.println("pdflst::"+auditList.size());
			

			return auditList;
		
		}

		@Override
		public CommonResponse getSpecialLoanDetails(long custId, long institutionId) {
			CommonResponse res=new CommonResponse();
			List<BusinessMessage> list1=new ArrayList<>();
			List<LoanDto> dtolist=new ArrayList<>();
			List<DepositsSuccess> s1=new ArrayList<>();
			LoanDtoList list=new LoanDtoList();
			LoanDto dto=new LoanDto();
			
			Occurrance ooo=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Special_Loans.getValue(), institutionId);	
			if(ooo == null)
			{
				list1.add(new BusinessMessage("Snagam is not started with SpecialLoan Configuration."));
				res.setServiceStatus(ServiceStatus.ERROR);
				res.setBusinessMessage(list1);;
				return res;
			}
			
			SpecialLoan loans=(SpecialLoan)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where customer.customerUid="+custId+" and institution.institution_uid="+institutionId+" and paidStatus='N'").uniqueResult();
			if(loans != null)
			{
				List<Occurrance> occur=getUnmatchedOccurrancesForSpecialLoans(loans.getSpecialLoanUid(),TransactionTypeE.Special_Loans.getValue(),Status.Active.getValue(),loans.getOccurrance().getOccurranceUid(), institutionId);
				double totalInterest=0;
				double totalFine=0;
				int i=0;
				for(Occurrance o1:occur)
				{
		            LocalDate ll=o1.getCoveredMonth();
					Period age = Period.between(o1.getCoveredMonth().minusMonths(1), ooo.getCoveredMonth()); 
					int years = age.getYears();
					int months = age.getMonths();
					//for(int i=0;i<=months;i++)
					//{
					   if(i==0)
						totalInterest=totalInterest+(simpleInterest(loans.getLoanPrinciple(), months, o1.getInterestType().getInterestRate()));
						if(i==1)
						totalFine=totalFine+(simpleInterest(loans.getLoanPrinciple(), months, o1.getInterestType().getFine()));
						//System.out.println("Special:::"+totalInterest+" totalFine::"+totalFine);
					//}
						i++;
				}
				dto.setCoveredMonth(loans.getOccurrance().getCoveredMonth());
				dto.setLoanId(loans.getSpecialLoanUid());
				dto.setInitialLoanStatus("N");
				dto.setAmount(loans.getLoanPrinciple());
				dto.setProposedPayAmount(loans.getLoanPrinciple());
				dto.setCustomeId(loans.getCustomer().getCustomerUid());
				dto.setInterestTypeId(loans.getInterestsType().getInterestType_uid());
				dto.setIntroducer(loans.getIntroducer_id());
				dto.setOccurranceId(loans.getOccurrance().getOccurranceUid());
				dto.setFine(totalFine);
				dto.setProposedPayInterest(totalInterest);
				dto.setLoanSanctionDate(loans.getOccurrance().getCoveredMonth().toString());
				res.setServiceStatus(ServiceStatus.SUCCESS);
				
				if(occur.size() == 0)
				{
					list1.add(new BusinessMessage("No pending SpecialLoans for this month."));
					res.setServiceStatus(ServiceStatus.SUCCESS);
					res.setBusinessMessage(list1);;
				}

			}
			else {
				//System.out.println("CustomerId::"+custId+" institutionId::"+institutionId);
				dto.setInitialLoanStatus("Y");
				dto.setCustomeId(custId);
				dto.setInterestTypeId(getInterestTypeByTransactionType(TransactionTypeE.Special_Loans.getValue(), Status.Active.getValue(), institutionId).getInterestType_uid());
				dto.setOccurranceId(ooo != null?ooo.getOccurranceUid():null);
				dto.setCustomerlist(getCustomerList(Status.Active.getValue(),institutionId).stream().filter(e->e.getCustomer_uid() != custId).collect(Collectors.toList()));

			}
			List<SpLoanHistory> lh= getSpLoanHistoryByCustIdAndOcc(custId,ooo.getOccurranceUid(), "Y", institutionId);
			for(SpLoanHistory sp:lh) {   
			DepositsSuccess s11=new DepositsSuccess();
			   s11.setAmount(sp.getPaidAmount());
			   if(sp != null && sp.getOccurrance() != null && sp.getOccurrance().getCoveredMonth() != null)
			   s11.setCoveredMonth(sp.getOccurrance().getCoveredMonth().toString());
			   s11.setCustomer(sp.getSploans().getCustomer());
			   s11.setFine(sp.getFine());
			   s11.setInterest(sp.getPaidInterest());
			   s11.setInstitution(sp.getInstitution());
			   s11.setTransactionId(sp.getSploanHistory_uid());
			   s1.add(s11);
			}
			
			//System.out.println("dtttt:"+dto.getRemainingInstallmentsList().size());
			dtolist.add(dto);
			list.setLoanDto(dtolist);
			res.setServiceStatus(ServiceStatus.SUCCESS);
			res.setObj(list);
			res.setSuccessobj(s1);
			return res;
		}

		@Override
		public CommonResponse saveSpecialloans(LoanDtoList loanDtoList, long userId, long institutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			r.setFrom(" ");
			 List<DepositsSuccess> sds=new ArrayList<>();
			
			long customerId = 0;
			for(LoanDto loanDto : loanDtoList.getLoanDto()) {
				customerId=loanDto.getCustomeId();
				//System.out.println("checkbox::"+loanDto.getCurrentPaidInstallmentIds());
				if(loanDto.getAmount()<=0)
				{
					r.setServiceStatus(ServiceStatus.ERROR);
					//bm.add(new BusinessMessage("loan amount should be greater than 0"));
					r.setBusinessMessage(bm);
					r.setObj(getSpecialLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
					return r;
				}
				
				if(loanDto.getCurrentPaidInstallmentIds().equalsIgnoreCase("no"))
				{
					r.setServiceStatus(ServiceStatus.ERROR);
					bm.add(new BusinessMessage("Please check the checkBox."));
					r.setBusinessMessage(bm);
					r.setObj(getSpecialLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
					return r;
				}
				
				
			SpecialLoan s=getSpecialLoanById(loanDto.getLoanId(), institutionId);
			if(s != null)
			{
				SpLoanHistory sp=new SpLoanHistory();
				sp.setPaidAmount(loanDto.getProposedPayAmount());
				sp.setPaidInterest(s.getInterest()+loanDto.getProposedPayInterest());
				sp.setFine(s.getFine()+loanDto.getFine());
				sp.setPaidStatus("Y");
				sp.setLoanPaidDate(LocalDateTime.now());
				sp.setInstitution(getInstitution(institutionId));
				sp.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), institutionId));
				sp.setSploans(s);
				sp.setCustomer(getCustomer(loanDto.getCustomeId(),Status.Active.getValue(),institutionId));
				sp.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Special_Loans.getValue(), institutionId, Status.Active.getValue()));
				long d=(long)sessionFactory.getCurrentSession().save(sp);
				if(d>0)
				{
					s.setPaidStatus("Y");
					sessionFactory.getCurrentSession().update(s);
					
					List<SpLoanHistory> lh= getSpLoanHistoryByCustIdAndOcc(loanDto.getCustomeId(),getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Special_Loans.getValue(), institutionId).getOccurranceUid(), "Y", institutionId);
					for(SpLoanHistory sp1:lh) {   
					DepositsSuccess s11=new DepositsSuccess();
					   s11.setAmount(sp1.getPaidAmount());
					   s11.setCoveredMonth(sp1.getOccurrance().getCoveredMonth().toString());
					   s11.setCustomer(sp1.getSploans().getCustomer());
					   s11.setFine(sp1.getFine());
					   s11.setInterest(sp1.getPaidInterest());
					   s11.setInstitution(sp1.getInstitution());
					   s11.setTransactionId(sp1.getSploanHistory_uid());
					   sds.add(s11);
					}

					   r.setSuccessobj(sds); 
					   r.setFrom("SploansPaid");
				}
				
				
				Ledger le=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
				le.setBalance(le.getBalance()+loanDto.getProposedPayAmount()+loanDto.getFine()+loanDto.getProposedPayInterest());
				sessionFactory.getCurrentSession().update(le);
				
				r.setServiceStatus(ServiceStatus.SUCCESS);
				bm.add(new BusinessMessage("SpecialLoan details successfully updated"));
				r.setBusinessMessage(bm);
				
				
//				List<SpLoanHistory> lh= getSpLoanHistoryBySPIdAndOcc(loanDto.getLoanId(),getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Special_Loans.getValue(), institutionId).getOccurranceUid(), "Y", institutionId);
//				for(SpLoanHistory sp1:lh) {   
//				DepositsSuccess s11=new DepositsSuccess();
//				   s11.setAmount(sp1.getPaidAmount());
//				   s11.setCoveredMonth(sp1.getOccurrance().getCoveredMonth().toString());
//				   s11.setCustomer(sp1.getSploans().getCustomer());
//				   s11.setFine(sp1.getFine());
//				   s11.setInterest(sp1.getPaidInterest());
//				   s11.setInstitution(sp1.getInstitution());
//				   s11.setTransactionId(sp1.getSploanHistory_uid());
//				   sds.add(s11);
//				}
			
				 
			}
			else {
				
				if(loanDto.getIntroducer() == 0)
				{
					r.setServiceStatus(ServiceStatus.ERROR);
					bm.add(new BusinessMessage("Introducer is required."));
					r.setBusinessMessage(bm);
					r.setObj(getLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
					return r;
				}
				
				Ledger le=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
				if(le.getBalance() >= loanDto.getAmount()) {
				le.setBalance(le.getBalance()-loanDto.getAmount());
				sessionFactory.getCurrentSession().update(le);
				}
				else {
					r.setServiceStatus(ServiceStatus.ERROR);
					bm.add(new BusinessMessage("No Sufficient funds to offer loan."));
					r.setBusinessMessage(bm);
					r.setObj(getLoanDetails(loanDto.getCustomeId(),institutionId).getObj());
					return r;
				}
				
				SpecialLoan l=new SpecialLoan();
				l.setCreatedBy(String.valueOf(userId));
				l.setCustomer(getCustomer(loanDto.getCustomeId(),Status.Active.getValue(),institutionId));
				l.setInterestsType(getInterestTypeByTransactionType(TransactionTypeE.Special_Loans.getValue(), Status.Active.getValue(), institutionId));
				//if(loanDto.getIntroducer() != null)
				  l.setIntroducer_id((int)getCustomer(loanDto.getIntroducer(),Status.Active.getValue(),institutionId).getCustomer_uid());
				l.setLoan_paid_date(LocalDateTime.now());
				l.setInstitution(getInstitution(institutionId));
				l.setPaidStatus("N");
				//if(loanDto.getAmount() != null)
				  l.setLoanPrinciple(loanDto.getAmount());
				//if(loanDto.getOccurranceId() != null)
				  l.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Special_Loans.getValue(), institutionId, Status.Active.getValue()));
				l.setUpdatedBy(String.valueOf(userId));
				l.setLedger(le);
				long c=(long)sessionFactory.getCurrentSession().save(l);
				//System.out.println("loans::"+c);
					 r.setServiceStatus(ServiceStatus.SUCCESS);
					 bm.add(new BusinessMessage("SpecialLoan details successfully saved"));
					 r.setBusinessMessage(bm);
					 
						SpecialLoan l1=getSpecialLoanById(c, institutionId);
						//System.out.println("l1::::"+l1);
						if(l1 != null)
						{
						  DepositsSuccess s111=new DepositsSuccess();
						   s111.setAmount(l1.getLoanPrinciple());
						   s111.setCoveredMonth(l1.getOccurrance().getCoveredMonth().toString());
						   s111.setCustomer(l1.getCustomer());
						   s111.setInstitution(l1.getInstitution());
						   s111.setTransactionId(c);
						   s111.setIntroducer(getCustomer(l1.getIntroducer_id(),Status.Active.getValue(), institutionId));
						   s111.setLoanIssuedTime(l1.getLoan_paid_date().toString());
						   s111.setInterest(l1.getInterestsType().getInterestRate());
						   s111.setFine(l1.getInterestsType().getFine());
						   sds.add(s111);
						   r.setSuccessobj(sds);
						   r.setFrom("SploansInitiate");
						   //System.out.println("r.getFrom()::"+r.getFrom());
						}
				}
			}
		
			 r.setObj(getLoanDetails(customerId,institutionId).getObj());
			return r;
		}

		@Override
		public CommonResponse saveExpenses(Expenses ex,long userId, long InstitutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			Ledger l=getActiveLedger(LedgerStatusE.Open.getValue(), InstitutionId);
			if(l == null)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("No active Occurrance/Ledger."));
				 r.setBusinessMessage(bm);
				 return r;
			}
			
			if(ex.getExpenseName() == null || ex.getAmount()<=0)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("Expense name/amount need to be enetered."));
				 r.setBusinessMessage(bm);
				 
				 r.setObj(getExpenseDetails(l.getLedger_uid(),InstitutionId));
				 return r;
			}
			
			
			Expenses e=getExpenseByUid(ex.getExpenseId(), InstitutionId);
			if(e != null)
			{
				l.setBalance(l.getBalance()+e.getAmount()-ex.getAmount());
				sessionFactory.getCurrentSession().update(l);
				
				e.setAmount(ex.getAmount());
				e.setComments(ex.getComments());
				sessionFactory.getCurrentSession().update(e);
				
				 r.setServiceStatus(ServiceStatus.SUCCESS);
				 bm.add(new BusinessMessage("Expense details successfully updated"));
				 r.setBusinessMessage(bm);
				 
				 r.setObj(getExpenseDetails(l.getLedger_uid(),InstitutionId));
				 return r;
				 
			}
			
			
			if(l.getBalance() >= ex.getAmount()) {
			l.setBalance(l.getBalance()-ex.getAmount());
			sessionFactory.getCurrentSession().update(l);
			}
			else {
				r.setServiceStatus(ServiceStatus.ERROR);
				bm.add(new BusinessMessage("No Sufficient funds."));
				r.setBusinessMessage(bm);
				r.setObj(getExpenseDetails(l.getLedger_uid(),InstitutionId));
				return r;
			}
			ex.setInstitution(getInstitution(InstitutionId));
			ex.setLedger(l);
			ex.setCreatedAt(LocalDate.now());
			long c=(long)sessionFactory.getCurrentSession().save(ex);
			
			if(c>0)
			{
				 r.setServiceStatus(ServiceStatus.SUCCESS);
				 bm.add(new BusinessMessage("Expense details successfully saved"));
				 r.setBusinessMessage(bm);
				
			}
			r.setObj(getExpenseDetails(l.getLedger_uid(),InstitutionId));
			return r;
		}
		
		
		@Override
		public CommonResponse saveOtherIncome(OtherIncome ex1,long userId, long InstitutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			
			Ledger l=getActiveLedger(LedgerStatusE.Open.getValue(), InstitutionId);
			if(l == null)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("No active Occurrance/Ledger."));
				 r.setBusinessMessage(bm);
				 return r;
			}
			
			
			if(ex1.getAmount()<=0)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("Amount need to be entered."));
				 r.setBusinessMessage(bm);
				 
				 r.setObj(getOtherIncomeDetails(l.getLedger_uid(),InstitutionId));
				 return r;
			}
		
			
			
			OtherIncome ot=getOtherIncomeByUid(ex1.getOtherIncomeUId(), InstitutionId);
			if(ot != null) {
				l.setBalance(l.getBalance()-ot.getAmount()+ex1.getAmount());
				sessionFactory.getCurrentSession().update(l);
				
				ot.setAmount(ex1.getAmount());
				ot.setDescription(ex1.getDescription());
				sessionFactory.getCurrentSession().save(ot);
				
				 r.setServiceStatus(ServiceStatus.SUCCESS);
				 bm.add(new BusinessMessage("OtherIncome details successfully updated."));
				 r.setBusinessMessage(bm);
				
				 r.setObj(getOtherIncomeDetails(l.getLedger_uid(),InstitutionId));
				 return r;
				
			}
			
			OtherIncome ex=new OtherIncome();
			ex=ex1;
			//Ledger le=getLastLedger(LedgerStatusE.Open.getValue(), InstitutionId);
//			if(ex.getType().equalsIgnoreCase("ApplicationForms")) {
//			InterestType i=getInterestTypeByTransactionType(TransactionTypeE.Application_Forms.getValue(), Status.Active.getValue(), InstitutionId);
//			le.setBalance(le.getBalance()+(ex.getQuantity()*i.getInterestRate()));
//			sessionFactory.getCurrentSession().update(le);
//			}
			//else {
				l.setBalance(l.getBalance()+ex.getAmount());
				sessionFactory.getCurrentSession().update(l);
			//}
			ex.setInstitution(getInstitution(InstitutionId));
			ex.setLedger(l);
			ex.setCreatedDate(LocalDate.now());
			long c=(long)sessionFactory.getCurrentSession().save(ex);
			
			if(c>0)
			{
				 r.setServiceStatus(ServiceStatus.SUCCESS);
				 bm.add(new BusinessMessage("OtherIncome details successfully saved"));
				 r.setBusinessMessage(bm);
				
			}
			r.setObj(getOtherIncomeDetails(l.getLedger_uid(),InstitutionId));
			return r;
		}

		@Override
		public List<Expenses> getExpenseDetails(long ledgerId,long instid) {
			List<Expenses> cust=sessionFactory.getCurrentSession().createQuery("from Expenses where ledger.ledger_uid="+ledgerId+" and institution.institution_uid="+instid).list();
			return cust;
		}
		
		@Override
		public Expenses getExpenseByUid(long id,long instid) {
			Expenses cust=(Expenses) sessionFactory.getCurrentSession().createQuery("from Expenses where expenseId="+id+" and  institution.institution_uid="+instid).uniqueResult();
			return cust;
		}
		
		@Override
		public List<OtherIncome> getOtherIncomeDetails(long ledgerId,long instid) {
			List<OtherIncome> cust=sessionFactory.getCurrentSession().createQuery("from OtherIncome where ledger.ledger_uid="+ledgerId+" and institution.institution_uid="+instid).list();
			return cust;
		}
		
		public OtherIncome getOtherIncomeByUid(long otherUId,long instid) {
			OtherIncome cust=(OtherIncome) sessionFactory.getCurrentSession().createQuery("from OtherIncome where otherIncomeUId="+otherUId+" and institution.institution_uid="+instid).uniqueResult();
			return cust;
		}
		
		public long saveObj(Object obj)
		{
			return (long) sessionFactory.getCurrentSession().save(obj);
		}
		
		public void updateObj(Object obj)
		{
			sessionFactory.getCurrentSession().update(obj);
		}
		
		@Override
		public List<Expenses> getExpenseByLedger(long ledgerId,long instid) {
			List<Expenses> cust=sessionFactory.getCurrentSession().createQuery("from Expenses where ledger.ledger_uid="+ledgerId+" and institution.institution_uid="+instid).list();
			return cust;
		}
		
		@Override
		public List<OtherIncome> getOtherIncomeByLedger(long ledgerId,long instid) {
			List<OtherIncome> cust=sessionFactory.getCurrentSession().createQuery("from OtherIncome where ledger.ledger_uid="+ledgerId+" and institution.institution_uid="+instid).list();
			return cust;
		}
		
		@Override
		public Ledger getActiveLedger(int ledgerStatusId,long instid) {
			Ledger cust=(Ledger) sessionFactory.getCurrentSession().createQuery("from Ledger where status="+ledgerStatusId+" and institution.institution_uid="+instid).uniqueResult();
			return cust;
		}
		
		 public String getMonthName(Date d) {
		      Formatter fmt = new Formatter();
		      fmt = new Formatter();
		      fmt.format("%tb", d);
		      //System.out.println(fmt);
		      
		      return fmt.toString();
		   }
		
		
		public String countDownTime(Date time,int exHours)
		{
			String temp1=time.toString();
			time.setHours(time.getHours()+exHours);
	        //System.out.println("d::"+time);
			String date=getMonthName(time)+" "+time.getDate()+","+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+" "+temp1.substring(0,4);
			//System.out.println(date);
			//date=date.substring(4,date.length()).replace("IST", "");
			return date;
		}

		@Override
		public CommonResponse updateOccurance(OccurranceDto occurrancedto, long institutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			List<Occurrance> occ=getOccuranceList(occurrancedto.getLedgerId(), Status.Active.getValue(), institutionId);
			//int hours=LocalDateTime.now().getHour();
			for(Occurrance o: occ)
			{
				o.setReason(occurrancedto.getReason());
				o.setActivehours(o.getActivehours()+occurrancedto.getActivehours());
				sessionFactory.getCurrentSession().update(o);
				bm.add(new BusinessMessage("Successfully Updated"));
				r.setServiceStatus(ServiceStatus.SUCCESS);
			}
			r.setObj(getOccuranceList(institutionId));
			return r;
		}

		@Override
		public List<MonthlyLoansSummaryDto> getMonthlyLoanSummaryList(long occuranceId, long institutionId) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String configureOcc(long instId)
		{
			Institution institution=getInstitution(instId);
			
			InterestType t=getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(), Status.Active.getValue(), instId);
			if(t == null)
				return null;
			
			InterestType lt=getInterestTypeByTransactionType(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), instId);
			if(lt == null)
				return null;
			
			InterestType spt=getInterestTypeByTransactionType(TransactionTypeE.Special_Loans.getValue(), Status.Active.getValue(), instId);
			if(spt == null)
				return null;
			
			
			
			
			
			Occurrance docc=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Deposits.getValue(), instId);
			if(docc == null && t != null)
			{
				Address a=new Address();
				long aId=(long)sessionFactory.getCurrentSession().save(a);
				Address address=getAddress(aId);
				Occurrance locc=getLastOccurranceByStatus(TransactionTypeE.Deposits.getValue(), Status.InActive.getValue(), instId);
				Occurrance o = new Occurrance();
				o.setActivehours("12");
				if(locc != null)
				   o.setCoveredMonth(locc.getCoveredMonth().plusMonths(1));
				else
					o.setCoveredMonth(LocalDate.now());
				o.setInstitution(institution);
				o.setInterestType(getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(), Status.Active.getValue(), instId));
				o.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), instId));
				o.setOccurranceDate(new Date());
				o.setStatus(OccurranceEnum.Pending.getValue());
				o.setTransactionType(TransactionTypeE.Deposits.getValue());
				o.setAddress(address);
				//o.setUserId(userId);
				saveObj(o);
				
			}
			
			Occurrance locc=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Loans.getValue(), instId);
			if(locc == null && lt != null)
			{
				Address a=new Address();
				long aId=(long)sessionFactory.getCurrentSession().save(a);
				Address address=getAddress(aId);
				
				Occurrance locc1=getLastOccurranceByStatus(TransactionTypeE.Loans.getValue(), Status.InActive.getValue(), instId);
				Occurrance o = new Occurrance();
				o.setActivehours("12");
				if(locc1 != null)
					   o.setCoveredMonth(locc1.getCoveredMonth().plusMonths(1));
					else
						o.setCoveredMonth(LocalDate.now());
				o.setInstitution(institution);
				o.setInterestType(getInterestTypeByTransactionType(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), instId));
				o.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), instId));
				o.setOccurranceDate(new Date());
				o.setStatus(OccurranceEnum.Pending.getValue());
				o.setTransactionType(TransactionTypeE.Loans.getValue());
				o.setAddress(address);
				//o.setUserId(userId);
				saveObj(o);
				
			}
			
			Occurrance spocc=getActiveOccurranceForSheduedMsg(OccurranceEnum.Active.getValue()+","+OccurranceEnum.Pending.getValue(), TransactionTypeE.Special_Loans.getValue(), instId);
			if(spocc == null && spt != null)
			{
				Address a=new Address();
				long aId=(long)sessionFactory.getCurrentSession().save(a);
				Address address=getAddress(aId);
				
				Occurrance locc1=getLastOccurranceByStatus(TransactionTypeE.Special_Loans.getValue(), Status.InActive.getValue(), instId);
				Occurrance o = new Occurrance();
				o.setActivehours("12");
				if(locc1 != null)
					   o.setCoveredMonth(locc1.getCoveredMonth().plusMonths(1));
					else
						o.setCoveredMonth(LocalDate.now());
				o.setInstitution(institution);
				o.setInterestType(getInterestTypeByTransactionType(TransactionTypeE.Special_Loans.getValue(), Status.Active.getValue(), instId));
				o.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), instId));
				o.setOccurranceDate(new Date());
				o.setStatus(OccurranceEnum.Pending.getValue());
				o.setTransactionType(TransactionTypeE.Special_Loans.getValue());
				o.setAddress(address);
				//o.setUserId(userId);
				saveObj(o);
				
			}
			return "configured";
		}
		
		public String msgText(String customerName,long customerId,long instId)
		{
			String text="";
			double amount=0;
			double amountl=0;
			double amounts=0;
			double total=0;
		
			Institution institution=getInstitution(instId);
		    CommonResponse res=searchOccurance(customerId, instId);
			if(res != null && res.getObj() != null)
			{
				DepositDtoList dlist=(DepositDtoList) res.getObj();	
				List<DepositDto> dto=dlist.getDepositdto();
				 amount=dto.stream().mapToDouble(e->e.getPrincipleAmount()+e.getFine()).sum();
				//System.out.println("Deposits Amount:"+amount);
				if(amount>0)
				{
				text="Hello Mr/Ms. "+customerName+" id:"+customerId;
				text=text+" ,your are supposed to pay Deposits amount:"+amount;
				}
				total=total+amount;
			}
			
			CommonResponse lres=getLoanDetails(customerId, instId);
			if(lres != null && lres.getObj() != null)
			{
				LoanDtoList dto=(LoanDtoList) lres.getObj();
				List<LoanDto> llldtao=dto.getLoanDto();
				for(LoanDto l:llldtao)
				{
				List<PendingLoansDto> pdtoll=l.getRemainingInstallmentsList();
				if(pdtoll != null && pdtoll.size()>0)
			    amountl=pdtoll.stream().filter(e->e.getStatus() != null && e.getStatus().equalsIgnoreCase("true")).mapToDouble(e->e.getAmount()+e.getFine()+e.getInterest()).sum();
				//System.out.println("loans Amount:"+amountl);
				if(amountl>0)
				{
					text=text+" ,Loans amount: "+amountl;
				}
				}
				total=total+amountl;
			}
			
			CommonResponse sres=getSpecialLoanDetails(customerId, instId);
			if(sres != null && sres.getObj() != null)
			{
				LoanDtoList dto=(LoanDtoList) sres.getObj();
				List<LoanDto> llldtao=dto.getLoanDto();
					
					if(llldtao != null && llldtao.size()>0)
					 amounts=llldtao.stream().mapToDouble(e->e.getProposedPayAmount()+e.getFine()+e.getProposedPayInterest()).sum();
					//System.out.println("Specialloans Amount:"+amounts);
					if(amounts>0)
					{
						text=text+" ,SpecialLoans amount: "+amounts;
					}
				
				total=total+amounts;
			}
				
			if(total > 0)
			{
				text=text+" and Total amount: "+total+" on "+LocalDate.now().withDayOfMonth(institution.getMsgScheduledDate() == 0?1:institution.getMsgScheduledDate()+1)+" at "+institution.getInstitutionName()+","+institution.getAddress_id().getLandMark()+","+institution.getAddress_id().getVillage();
			}
			
			return text;
		}
		
		@Override
		public CommonResponse rollback(long custId, long institutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			BusinessMessage b=new BusinessMessage();
			CommonResponse r=new CommonResponse();
			Ledger le=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
			if(le == null)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("No active Occurrance/Ledger."));
				 r.setBusinessMessage(bm);
				 return r;
			}
			
			Occurrance o=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), institutionId);
			if(o != null)
			{
				 List<Deposits> dl=getDepositsListByLedgerAndCust(le.getLedger_uid(), custId, institutionId);
				 if(dl != null) {
			for(Deposits d: dl)
			{
				Ledger l=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
				l.setBalance(l.getBalance()-d.getAmountPaid()-d.getFine()-d.getInterestPaid());
				sessionFactory.getCurrentSession().update(l);
				sessionFactory.getCurrentSession().delete(d);
				
				b=new BusinessMessage("Deposits Successfully rolled back");
				r.setBusinessMessage(bm);
				r.setServiceStatus(ServiceStatus.SUCCESS);
			}
			bm.add(b);
			}
			}
			
			Occurrance ol=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), institutionId);
			if(ol != null)
			{
		    //Loans llo1=getLoanByCustomerAndOccurrance(ol.getOccurranceUid(), custId, institutionId);
		    	int installents=0;
		    	Loans llo1=null;
		    	List<LoanHistory> lhe = getLoanHistoryListByCustAndOccurranceId(custId, ol.getOccurranceUid(), "Y", institutionId);
			for(LoanHistory lh: lhe)
			{
				Ledger l=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
				l.setBalance(l.getBalance()-lh.getPaidAmount()-lh.getFine()-lh.getPaidInterest());
				sessionFactory.getCurrentSession().update(l);
				
				lh.setPaidAmount(0.00);
				lh.setPaidInterest(0.00);
				lh.setFine(0.00);
				lh.setPaidStatus("N");
				lh.setLedger(null);
				lh.setCustomer(null);
				lh.setOccurrance(null);
				sessionFactory.getCurrentSession().update(lh);
				
				installents++;
				llo1=lh.getLoans();
			}
			if(llo1 != null) {
			llo1.setPaidInstallments(llo1.getPaidInstallments()-installents);
			llo1.setPaidStatus(null);
			sessionFactory.getCurrentSession().update(llo1);
			bm.add(new BusinessMessage("Loans Successfully rolled back"));
			r.setServiceStatus(ServiceStatus.SUCCESS);
			r.setBusinessMessage(bm);
			}
			
		
			
			}
			
			Occurrance ols=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Special_Loans.getValue(), institutionId);
			if(ols != null)
			{

		    	SpecialLoan sp=null;
		    	List<SpLoanHistory>  spl=getSpLoanHistoryByCustIdAndOcc(custId, ols.getOccurranceUid(), "Y", institutionId);
			for(SpLoanHistory lhs: spl)
			{
				Ledger l=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
				l.setBalance(l.getBalance()-lhs.getPaidAmount()-lhs.getFine()-lhs.getPaidInterest());
				sessionFactory.getCurrentSession().update(l);
				
				lhs.setPaidAmount(0.00);
				lhs.setPaidInterest(0.00);
				lhs.setFine(0.00);
				lhs.setPaidStatus("N");
				lhs.setLedger(null);
				lhs.setCustomer(null);
				lhs.setOccurrance(null);
				sessionFactory.getCurrentSession().update(lhs);
				
				sp=lhs.getSploans();
			}
			
			if(sp != null) {
			sp.setPaidStatus("N");
			sp.setFine(0);
			sp.setInterest(0);
			sessionFactory.getCurrentSession().update(sp);
			bm.add(new BusinessMessage("SpecialLoans Successfully rolled back"));
			r.setServiceStatus(ServiceStatus.SUCCESS);
			r.setBusinessMessage(bm);
			}
		    }
			
			Receipt re= getReceiptByCustomerAndOccurrance(custId, o.getOccurranceUid(), institutionId);
			if(re != null)
			sessionFactory.getCurrentSession().delete(re);
			return r;
		}
		

		@Override
		public CommonResponse rollbackLoans(long custId, long institutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			Ledger le=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
			if(le == null)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("No active Occurrance/Ledger."));
				 r.setBusinessMessage(bm);
				 return r;
			}
			
			Occurrance ol=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Loans.getValue(), institutionId);
			if(ol != null)
			{
		    Loans llo1=getLoanByCustomerAndOccurrance(ol.getOccurranceUid(), custId, institutionId);
		    if(llo1 != null) {
			for(LoanHistory lh: llo1.getLoanHistory())
			{
				sessionFactory.getCurrentSession().delete(lh);
			}

			le.setBalance(le.getBalance()+llo1.getLoanPrinciple());
			sessionFactory.getCurrentSession().update(le);
			
			sessionFactory.getCurrentSession().delete(llo1);
			
			 r.setServiceStatus(ServiceStatus.SUCCESS);
			 bm.add(new BusinessMessage("Long Term loan successfully rolled back."));
			 r.setBusinessMessage(bm);
		    }
			}
			
			Receipt re= getReceiptByCustomerAndOccurrance(custId, ol.getOccurranceUid(), institutionId);
			if(re != null)
			sessionFactory.getCurrentSession().delete(re);
			return r;
		}
	
		
		
		
		@Override
		public CommonResponse rollbackSpLoans(long custId, long institutionId) {
			List<BusinessMessage> bm=new ArrayList<>();
			CommonResponse r=new CommonResponse();
			Ledger le=getActiveLedger(LedgerStatusE.Open.getValue(), institutionId);
			if(le == null)
			{
				 r.setServiceStatus(ServiceStatus.ERROR);
				 bm.add(new BusinessMessage("No active Occurrance/Ledger."));
				 r.setBusinessMessage(bm);
				 return r;
			}
		
			
			Occurrance ols=getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Special_Loans.getValue(), institutionId);
			if(ols != null)
			{
		    SpecialLoan llo11=getSpecialLoanByCustAndOcc(custId, ols.getOccurranceUid(), institutionId);
		    if(llo11 != null) {
//			for(SpLoanHistory lhs: llo11.getSploanHistory())
//			{
//				sessionFactory.getCurrentSession().delete(lhs);;
//			}

			le.setBalance(le.getBalance()+llo11.getLoanPrinciple());
			sessionFactory.getCurrentSession().update(le);
			
			sessionFactory.getCurrentSession().delete(llo11);
			
			 r.setServiceStatus(ServiceStatus.ERROR);
			 bm.add(new BusinessMessage("Short term loan successfully rolled back."));
			 r.setBusinessMessage(bm);
		    }
			}
			Receipt re= getReceiptByCustomerAndOccurrance(custId, ols.getOccurranceUid(), institutionId);
			if(re != null)
			sessionFactory.getCurrentSession().delete(re);
			return r;
		}
	
}
