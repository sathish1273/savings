package com.stackroute.keepnote.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.dto.BusinessMessage;
import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.dto.CustomerDto;
import com.stackroute.keepnote.dto.DepositDto;
import com.stackroute.keepnote.dto.DepositDtoList;
import com.stackroute.keepnote.dto.DepositType;
import com.stackroute.keepnote.dto.ExcelDto;
import com.stackroute.keepnote.dto.InstitutionDto;
import com.stackroute.keepnote.dto.LedgerStatusE;
import com.stackroute.keepnote.dto.LoanDtoList;
import com.stackroute.keepnote.dto.PersonalHistory;
import com.stackroute.keepnote.dto.RoleDto;
import com.stackroute.keepnote.dto.ServiceStatus;
import com.stackroute.keepnote.dto.Status;
import com.stackroute.keepnote.dto.TransactionSummary;
import com.stackroute.keepnote.dto.TransactionTypeE;
import com.stackroute.keepnote.dto.TypeOfEntry;
import com.stackroute.keepnote.dto.UserDto;
import com.stackroute.keepnote.model.Address;
import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.InterestType;
import com.stackroute.keepnote.model.Ledger;
import com.stackroute.keepnote.model.LoanDto;
import com.stackroute.keepnote.model.LoanHistory;
import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.Occurrance;
import com.stackroute.keepnote.model.PendingDeposits;
import com.stackroute.keepnote.model.Recharge;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.SpLoanHistory;
import com.stackroute.keepnote.model.SpecialLoan;
import com.stackroute.keepnote.model.User;


@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DepositTypeDAO ddao;
	
	@Autowired
	userDAO udao;

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */

	public CustomerDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;

	}
	public Institution getInstitution(long id)
	{
		Institution i=(Institution)sessionFactory.getCurrentSession().createQuery("from Institution where institution_uid="+id).uniqueResult();
		return i;
	}
	

	public Institution getInstitutionByName(String institutionName,long statusId)
	{
		Institution i=(Institution)sessionFactory.getCurrentSession().createQuery("from Institution where institutionName='"+institutionName+"' and status="+statusId).uniqueResult();
		return i;
	}
	

	
	public Occurrance getOccuranceByInstitution(int transactionType,long instId,int status)
	{
		Occurrance cust=(Occurrance)sessionFactory.getCurrentSession().createQuery("from Occurrance where transactionType="+transactionType+" and  institution.institution_uid="+instId+" and status="+status+"").uniqueResult();
		return cust;
	}
	
	public Ledger getLastLedger(int status,long instid)
	{
		Ledger a=(Ledger)sessionFactory.getCurrentSession().createQuery("from Ledger where ledger_uid=( select max(ledger_uid) from Ledger where status="+status+" and institution.institution_uid="+instid+")").uniqueResult();
		return a;
	}
	
	public CommonResponse saveCustomer(CustomerDto dto,long institutionId,long userId)
	{
		CommonResponse cmnr=new CommonResponse();
		List<BusinessMessage> businessMessage=new ArrayList<>();
		Occurrance o= getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(),(int)institutionId,Status.Active.getValue());
		if(o == null) {
			cmnr.setServiceStatus(ServiceStatus.ERROR);
			businessMessage.add(new BusinessMessage("Sangam not yet started."));
			cmnr.setBusinessMessage(businessMessage);
			cmnr.setObj(customerList(Status.Active.getValue(),institutionId));
			return cmnr;
		}
		
		if(dto.getCustomerName() == null || dto.getCustomerName().trim().equalsIgnoreCase(""))
		{
			cmnr.setServiceStatus(ServiceStatus.ERROR);
			businessMessage.add(new BusinessMessage("please enter Customername."));
			cmnr.setBusinessMessage(businessMessage);
			cmnr.setObj(customerList(Status.Active.getValue(),institutionId));
			return cmnr;
		}
		
		if(dto.getPhoneNo() == null || dto.getPhoneNo().trim().equalsIgnoreCase(""))
		{
			cmnr.setServiceStatus(ServiceStatus.ERROR);
			businessMessage.add(new BusinessMessage("please enter PhoneNo."));
			cmnr.setBusinessMessage(businessMessage);
			cmnr.setObj(customerList(Status.Active.getValue(),institutionId));
			return cmnr;
		}
		
		Customer ii=null;
		        if(dto.getCustomerUid()!=null && !dto.getCustomerUid().equalsIgnoreCase("") && !dto.getCustomerUid().equalsIgnoreCase(" "))
				ii=getCustomer(dto.getCustomerUid()!=null?Long.parseLong(dto.getCustomerUid()):0, institutionId);
		if(ii != null) {
			
			Address ad=ii.getAddress();
			if(ad == null) {
				ad=new Address();
			ad.setDistrict(dto.getDistrictId());
			ad.setLandMark(dto.getLandMark());
			ad.setMandal(dto.getMandalId());
			ad.setPincode(dto.getPincode());
			ad.setState(dto.getStateId());
			ad.setVillage(dto.getVillgaeId());
			sessionFactory.getCurrentSession().save(ad);
			}
			else {
				ad.setDistrict(dto.getDistrictId());
				ad.setLandMark(dto.getLandMark());
				ad.setMandal(dto.getMandalId());
				ad.setPincode(dto.getPincode());
				ad.setState(dto.getStateId());
				ad.setVillage(dto.getVillgaeId());
				sessionFactory.getCurrentSession().update(ad);
			}
			
			ii.setAddress(ad);
			ii.setCustomerName(dto.getCustomerName());
			ii.setFatherName(dto.getFatherName());
			ii.setGmail(dto.getGmail());
			ii.setPhoneNo(dto.getPhoneNo());
			ii.setPhoto(dto.getPhoto().getOriginalFilename());
			ii.setSignature(dto.getSignature().getOriginalFilename());
			ii.setStatus(Integer.parseInt(dto.getStatus()));
			sessionFactory.getCurrentSession().update(ii);
			
			cmnr.setServiceStatus(ServiceStatus.SUCCESS);
			businessMessage.add(new BusinessMessage("Customer Updated Successfully."));
			cmnr.setBusinessMessage(businessMessage);
			cmnr.setObj(customerList(Status.Active.getValue(),institutionId));
			return cmnr;
		}
		

		
		List<Customer> cust=null;
		Address a=new Address();
		a.setDistrict(dto.getDistrictId());
		a.setLandMark(dto.getLandMark());
		a.setMandal(dto.getMandalId());
		a.setPincode(dto.getPincode());
		a.setState(dto.getStateId());
		a.setVillage(dto.getVillgaeId());
		long aId=(long)sessionFactory.getCurrentSession().save(a);
		
		Customer customer=new Customer();
		customer.setCustomerId(getmaxCustomerId(institutionId)+1);
		customer.setCustomerName(dto.getCustomerName());
		customer.setFatherName(dto.getFatherName());
		customer.setGmail(dto.getGmail());
		customer.setPhoneNo(dto.getPhoneNo());
		customer.setPhoto(dto.getPhoto().getOriginalFilename());
		customer.setSignature(dto.getSignature().getOriginalFilename());
		customer.setOccurrance(o);
		customer.setCreatedBy(String.valueOf(userId));
		customer.setCreatedDate(LocalDate.now());
		customer.setAddress(getAddress(aId));
		customer.setInstitution(getInstitution(institutionId));
		customer.setStatus(Integer.parseInt(dto.getStatus()));
		long c=(long)sessionFactory.getCurrentSession().save(customer);
		Ledger le=null;
		if(c>0)
		{
			le=getLastLedger(LedgerStatusE.Open.getValue(), institutionId);
			le.setBalance(le.getBalance()+(dto.getInitialContribution() !=null?Double.parseDouble(dto.getInitialContribution()):0.00));
			sessionFactory.getCurrentSession().update(le);
			
//			PendingTransactions p=new PendingTransactions();
//			p.setCreatedBy(String.valueOf(userId));
//			//if((!dto.getCustomerUid().equalsIgnoreCase(" ") || !dto.getCustomerUid().trim().equalsIgnoreCase("")) && dto.getCustomerUid() != null)
//			p.setCustomer(getCustomer(c, institutionId));
//			p.setDepositDate(LocalDateTime.now());
//			p.setDepositsAmount(dto.getDepositsAmount());
//			
//		    if((!dto.getDepositslastPaidDate().equalsIgnoreCase(" ") || !dto.getDepositslastPaidDate().equalsIgnoreCase("")) && dto.getDepositslastPaidDate() != null)
//		    p.setDepositslastPaidDate(LocalDate.of(getyear(dto.getDepositslastPaidDate()), getmonth(dto.getDepositslastPaidDate()), getdate(dto.getDepositslastPaidDate())));
//			//p.setDepositType(dto.getD);
//			p.setInstitution(getInstitution(institutionId));
//			p.setLedger(le);
//			p.setLoansAmount(dto.getLoansAmount());
//			
//			if((!dto.getLoanslastPaidDate().equalsIgnoreCase(" ") || !dto.getLoanslastPaidDate().equalsIgnoreCase("")) && dto.getLoanslastPaidDate() != null)
//			p.setLoanslastPaidDate(LocalDate.of(getyear(dto.getLoanslastPaidDate()), getmonth(dto.getLoanslastPaidDate()), getdate(dto.getLoanslastPaidDate())));
//			p.setNoOfInstallments(dto.getNoOfInstallments());
//			p.setNoOfMonths(dto.getNoOfMonths());
//			p.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), institutionId, Status.Active.getValue()));
//			p.setSpecialLoansAmount(dto.getSpecialLoansAmount());
//			
//			if((!dto.getSploanslastPaidDate().equalsIgnoreCase(" ") || !dto.getSploanslastPaidDate().equalsIgnoreCase("")) && dto.getSploanslastPaidDate() != null)
//			p.setSploanslastPaidDate(LocalDate.of(getyear(dto.getSploanslastPaidDate()), getmonth(dto.getSploanslastPaidDate()), getdate(dto.getLoanslastPaidDate())));
//			//p.setUpdatedBy(updatedBy);
//			sessionFactory.getCurrentSession().save(p);
			

			
			cmnr.setSuccessobj(customer);
			cmnr.setServiceStatus(ServiceStatus.SUCCESS);
			businessMessage.add(new BusinessMessage("Customer successfully added."));
			cmnr.setBusinessMessage(businessMessage);
		}
		
//		Customer cc=getCustomer(c, institutionId);
//		cc.setPhoto(String.valueOf(c));
//		sessionFactory.getCurrentSession().update(cc);
		
		
		Deposits d=new Deposits();
		d.setCreatedBy(String.valueOf(userId));
		d.setDepositDate(LocalDateTime.now());
		d.setCustomer(getCustomer(c,institutionId));
		d.setOccurrance(o);
		d.setAmountPaid(Double.parseDouble(dto.getInitialContribution()));
		d.setInstitution(getInstitution(institutionId));
		d.setDepositType(DepositType.Initial_Contribution.getValue());
		d.setLedger(le);
		long ddd=(long)sessionFactory.getCurrentSession().save(d);
		
		//System.out.println("c:"+c);
		if(c != 0)
			cust=customerList(Status.Active.getValue(),institutionId);
		else
			cust=null;
		cmnr.setFrom(String.valueOf(ddd));
		cmnr.setObj(cust);
		return cmnr;
	}
	
	public int getyear(String text)
	{
       int year=Integer.parseInt(text.substring(0, 4));
        return year;
	}
	
	public int getmonth(String text)
	{
       int month=Integer.parseInt(text.substring(5, 7));
        return month;
	}
	
	
	public int getdate(String text)
	{
        int date=Integer.parseInt(text.substring(8, 10));
        return date;
	}

	public long getmaxCustomerId(long instId)
	{
		long cust=(long)sessionFactory.getCurrentSession().createQuery("select count(*) from Customer where institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public Customer getCustomer(long customerUId,long instId)
	{
		Customer cust=(Customer)sessionFactory.getCurrentSession().createQuery("from Customer where customerUid="+customerUId+" and institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public Customer getCustomerUid(long customerId,long instId)
	{
		Customer cust=(Customer)sessionFactory.getCurrentSession().createQuery("from Customer where customerId="+customerId+" and institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public Customer getCustomerByName(String customerName,int statusId,long instId)
	{
		Customer cust=(Customer)sessionFactory.getCurrentSession().createQuery("from Customer where customerName='"+customerName+"' and status="+statusId+" and institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	
	public List<Customer> customerList(int statusId,long instiId)
	{
		List<Customer> cust=(List<Customer>)sessionFactory.getCurrentSession().createQuery("from Customer where status="+statusId+" and institution.institution_uid="+instiId).list();
		return cust;
	}
	
	public List<User> userList(int statusId,long instId)
	{
		List<User> cust=(List<User>)sessionFactory.getCurrentSession().createQuery("from User where status="+statusId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<User> userList(int statusId)
	{
		List<User> cust=(List<User>)sessionFactory.getCurrentSession().createQuery("from User where status="+statusId).list();
		return cust;
	}
	
	public User getUser(String name,long instId,int statusId)
	{
		User cust=(User)sessionFactory.getCurrentSession().createQuery("from User where username='"+name+"' and status="+statusId+" and institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public List<RoleMenuLink> getRoleMenuLink(long roleId,long instId)
	{
		List<RoleMenuLink> cust=(List<RoleMenuLink>)sessionFactory.getCurrentSession().createQuery("from RoleMenuLink where role.roleUid="+roleId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public List<RoleMenuLink> getRoleMenuLinkByInstitution(String accessible,long instId)
	{
		List<RoleMenuLink> cust=(List<RoleMenuLink>)sessionFactory.getCurrentSession().createQuery("from RoleMenuLink where accessible_status='"+accessible+"' and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public RoleMenuLink getRoleMenuLinkByManuMaster(long roleId,String menuUid,long instId)
	{
		RoleMenuLink cust=(RoleMenuLink)sessionFactory.getCurrentSession().createQuery("from RoleMenuLink where role.roleUid="+roleId+" and inventoryMenu.menuName='"+menuUid+"' and institution.institution_uid="+instId).uniqueResult();
		return cust;  
	}
	
	public User getUserById(long id,long instId)
	{
		User cust=(User)sessionFactory.getCurrentSession().createQuery("from User where userId='"+id+"' and institution.institution_uid="+instId).uniqueResult();
		return cust;
	}
	
	public List<Institution> institutionList(int status)
	{
		List<Institution> cust=(List<Institution>)sessionFactory.getCurrentSession().createQuery("from Institution where status="+status).list();
		return cust;
	}
	
	public List<Role> roleList(int status,String roleName,long instId)
	{
		String query="";
		if(!roleName.equals("SuperAdmin"))
			query="and roleName not in ('SuperAdmin','Admin') and institution_uid in ("+instId+")";
		
		List<Role> cust=(List<Role>)sessionFactory.getCurrentSession().createQuery("from Role where status="+status+" "+query+" order by roleUid desc").list();
		return cust;
	}
	
	public Recharge getValidity(long instId,int statusId)
	{
		Recharge address=(Recharge)sessionFactory.getCurrentSession().createQuery("from Recharge where institution.institution_uid="+instId+" and status="+statusId+" ").uniqueResult();
		return address;
	}

	public Address getAddress(long addressId)
	{
		Address address=(Address)sessionFactory.getCurrentSession().createQuery("from Address where address_uid="+addressId).uniqueResult();
		return address;
	}
	@Override
	public CommonResponse saveInstitution(InstitutionDto institutionDto, long userId) {
		// TODO Auto-generated method stub
		List<BusinessMessage> bm=new ArrayList<>();
		CommonResponse r=new CommonResponse();
		if(institutionDto.getInstitutionName() == null || institutionDto.getInstitutionName().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please enter InstitutionName."));
			r.setBusinessMessage(bm);
			r.setObj(institutionList(Status.Active.getValue()));
			return r;
		}
		if(institutionDto.getValidityTo() == null || institutionDto.getValidityTo().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please select validity."));
			r.setBusinessMessage(bm);
			r.setObj(institutionList(Status.Active.getValue()));
			return r;
		}
//		if(institutionDto.getMsgScheduledDate()<=0)
//		{
//			r.setServiceStatus(ServiceStatus.ERROR);
//			bm.add(new BusinessMessage("please select MsgScheduledDate."));
//			r.setBusinessMessage(bm);
//			r.setObj(institutionList(Status.Active.getValue()));
//			return r;
//		}
		
		
		Institution ii=getInstitutionByName(institutionDto.getInstitutionName(), Status.Active.getValue());
		Institution iii=null;
		//System.out.println("institutionDto.getInstitutionId():::"+institutionDto.getInstitutionId());
		if(institutionDto.getInstitutionId() != null && !institutionDto.getInstitutionId().equalsIgnoreCase("") && !institutionDto.getInstitutionId().equalsIgnoreCase(" "))
		iii=ddao.getInstitution(Integer.parseInt(institutionDto.getInstitutionId()));
		//System.out.println("iii:"+iii);
		List<Institution> list=new ArrayList<>();
		if(iii != null)
		{
			Recharge v=getValidity(iii.getInstitution_uid(), Status.Active.getValue());
			if(v != null)
			{
				v.setStatus(Status.InActive.getValue());
				sessionFactory.getCurrentSession().update(v);
				
				Recharge v1=new Recharge();
		            v1.setFromDate(LocalDate.now());
		            v1.setInstitution(iii);
		            v1.setStatus(Status.Active.getValue());
		            if(institutionDto.getRechargeAmount() != null)
		            v1.setAmount(Double.parseDouble(institutionDto.getRechargeAmount()));
		            if(institutionDto.getValidityTo()!=null)
		            v1.setToDate(LocalDate.of(Integer.parseInt(institutionDto.getValidityTo().substring(0, 4)), Integer.parseInt(institutionDto.getValidityTo().substring(5, 7)), Integer.parseInt(institutionDto.getValidityTo().substring(8, 10))));
		            sessionFactory.getCurrentSession().save(v1);
				
			}
			
			Address a=iii.getAddress_id();
			if(a != null) {
			a.setDistrict(institutionDto.getDistrictId());
			a.setLandMark(institutionDto.getLandMark());
			a.setMandal(institutionDto.getMandalId());
			a.setPincode(institutionDto.getPincode());
			a.setState(institutionDto.getStateId());
			a.setVillage(institutionDto.getVillgaeId());
			sessionFactory.getCurrentSession().update(a);
			}
			
			iii.setInstitutionName(institutionDto.getInstitutionName());
			iii.setStatus(Integer.parseInt(institutionDto.getStatus()));
			iii.setMsgScheduledDate(institutionDto.getMsgScheduledDate());
			iii.setMessagesRequired(institutionDto.getMessagesRequired());
			//iii.setSangamStartDate(LocalDate.of(Integer.parseInt(institutionDto.getSangamStartDate().substring(0, 4)), Integer.parseInt(institutionDto.getSangamStartDate().substring(5, 7)), Integer.parseInt(institutionDto.getSangamStartDate().substring(8, 10))));
			sessionFactory.getCurrentSession().update(iii);
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("Institution Updated Successfully."));
			r.setBusinessMessage(bm);
			r.setObj(institutionList(Status.Active.getValue()));
			return r;
		}
		if(ii != null) {
			bm.add(new BusinessMessage("InstitutionName already existed."));
			r.setBusinessMessage(bm);
			r.setObj(institutionList(Status.Active.getValue()));
			return r;
		}
		else {
		Address a=new Address();
		a.setDistrict(institutionDto.getDistrictId());
		a.setLandMark(institutionDto.getLandMark());
		a.setMandal(institutionDto.getMandalId());
		a.setPincode(institutionDto.getPincode());
		a.setState(institutionDto.getStateId());
		a.setVillage(institutionDto.getVillgaeId());
		long c=(long)sessionFactory.getCurrentSession().save(a);
		
		Institution i=new Institution();
		i.setInstitutionName(institutionDto.getInstitutionName());
		i.setAddress_id(getAddress(c));
		i.setStatus(Integer.parseInt(institutionDto.getStatus()));
		i.setMsgScheduledDate(institutionDto.getMsgScheduledDate());
		i.setMessagesRequired(institutionDto.getMessagesRequired());
		//i.setSangamStartDate(LocalDate.of(Integer.parseInt(institutionDto.getSangamStartDate().substring(0, 4)), Integer.parseInt(institutionDto.getSangamStartDate().substring(5, 7)), Integer.parseInt(institutionDto.getSangamStartDate().substring(8, 10))));
		long ii1=(long) sessionFactory.getCurrentSession().save(i);
		
		if(ii1>0) {
	            
			Recharge v=new Recharge();
	            v.setFromDate(LocalDate.now());
	            v.setInstitution(getInstitution(ii1));
	            v.setStatus(Status.Active.getValue());
	            if(institutionDto.getRechargeAmount() != null)
	            v.setAmount(Double.parseDouble(institutionDto.getRechargeAmount()));
	            if(institutionDto.getValidityTo()!=null)
	            v.setToDate(LocalDate.of(Integer.parseInt(institutionDto.getValidityTo().substring(0, 4)), Integer.parseInt(institutionDto.getValidityTo().substring(5, 7)), Integer.parseInt(institutionDto.getValidityTo().substring(8, 10))));
	            sessionFactory.getCurrentSession().save(v);
			
		    Ledger ledger=new Ledger();
		    Ledger ll=getLastLedger(LedgerStatusE.Open.getValue(),getInstitutionByName(institutionDto.getInstitutionName(), Status.Active.getValue()).getInstitution_uid());
			if(ll ==null) {
			 Ledger l=getLastLedger(LedgerStatusE.Open.getValue(),getInstitutionByName(institutionDto.getInstitutionName(), Status.Active.getValue()).getInstitution_uid());
			    
			   
		    	ledger.setCreatedBy(userId);
		    	ledger.setLedger_entry_date(LocalDateTime.now());
	            ledger.setOpeningBalance(l != null?l.getClosingBalance():institutionDto.getOpeningBalance());
	            ledger.setBalance(l != null?l.getClosingBalance():institutionDto.getOpeningBalance());
	            //ledger.setClosingBalance(l != null?l.getClosingBalance():institutionDto.getOpeningBalance());
	            ledger.setStatus(LedgerStatusE.Open.getValue());
	            ledger.setInstitution(getInstitution(getInstitutionByName(institutionDto.getInstitutionName(), Status.Active.getValue()).getInstitution_uid()));
	            long le=(long)sessionFactory.getCurrentSession().save(ledger);
	            
		    }
		}
		
		if(ii1>0)
		{
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("Institution Successfully saved"));
			r.setBusinessMessage(bm);
			r.setObj(institutionList(Status.Active.getValue()));
		}
		
		}
		list=institutionList(Status.Active.getValue());
		r.setObj(list);
		return r;
	}
	@Override
	public CommonResponse saveUser(UserDto dto, long institutionId, long userId) {
		
		
		List<BusinessMessage> bm=new ArrayList<>();
		CommonResponse r=new CommonResponse();
		if(dto.getUserName() == null || dto.getUserName().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please enter username."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		if(dto.getPhoneNo() == null || dto.getPhoneNo().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please enter phoneNo."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		if(dto.getRoleId() == null || dto.getRoleId().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please select role."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		if(dto.getInstitutionId() == null || dto.getInstitutionId().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please select institution."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		if(dto.getPlaceOfBirth() == null || dto.getPlaceOfBirth().trim().equalsIgnoreCase("") || dto.getFavouritGame() == null || dto.getFavouritGame().trim().equalsIgnoreCase("") ||
				dto.getFirstMobileNo() == null || dto.getFirstMobileNo().trim().equalsIgnoreCase("") || dto.getMotherMaidenName() == null || dto.getMotherMaidenName().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please answer all Security Questions."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		
	
		
		User u=null;
		if(dto.getUserId() != null && !dto.getUserId().equalsIgnoreCase(""))
		 u=getUserById(Long.valueOf(dto.getUserId()), institutionId);
		if(u != null)
		{
		
			Address a=u.getAddress();
			if(a != null) {
			a.setDistrict(dto.getDistrictId());
			a.setLandMark(dto.getLandMark());
			a.setMandal(dto.getMandalId());
			a.setPincode(dto.getPincode());
			a.setState(dto.getStateId());
			a.setVillage(dto.getVillgaeId());
			sessionFactory.getCurrentSession().update(a);
			}
			
			u.setCreatedAt(LocalDate.now());
			u.setEmail(dto.getGmail());
			u.setPassword(dto.getPassword());
			u.setPhone(dto.getPhoneNo());
			u.setRole(getRole(Long.parseLong(dto.getRoleId().toString())));
			u.setUsername(dto.getUserName());
			u.setStatus(Integer.parseInt(dto.getStatus()));
			u.setFavouritGame(dto.getFavouritGame());
			u.setFirstMobileNo(dto.getFirstMobileNo());
			u.setPlaceOfBirth(dto.getPlaceOfBirth());
			u.setMotherMaidenName(dto.getMotherMaidenName());
			if(dto.getLoggedStatus() != null)
			u.setLoggedStatus(Integer.parseInt(dto.getLoggedStatus()));
			sessionFactory.getCurrentSession().update(u);
			
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("User updated successfully"));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
			
		}
		
		List<User> uu=userList(Status.Active.getValue(),institutionId);
		if(uu != null)
		uu=uu.stream().filter(e->e.getPassword().equalsIgnoreCase(dto.getPassword())).collect(Collectors.toList());
		if(uu.size()>0)
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("No availability of password, please choose other password.."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;	
		}
		
		
		User ii=getUser(dto.getUserName(), institutionId,Status.Active.getValue());
		List<Institution> list=new ArrayList<>();
		if(ii != null) {
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("UserName already existed."));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			return r;
		}
		
		
		
		Address a=new Address();
		a.setDistrict(dto.getDistrictId());
		a.setLandMark(dto.getLandMark());
		a.setMandal(dto.getMandalId());
		a.setPincode(dto.getPincode());
		a.setState(dto.getStateId());
		a.setVillage(dto.getVillgaeId());
		long aId=(long)sessionFactory.getCurrentSession().save(a);
		
		User u1=new User();
		u1.setAddress(getAddress(aId));
		u1.setCreatedAt(LocalDate.now());
		u1.setEmail(dto.getGmail());
		u1.setInstitution(getInstitution(Long.parseLong(dto.getInstitutionId().toString())));
		u1.setPassword(dto.getPassword());
		u1.setPhone(dto.getPhoneNo());
		u1.setRole(getRole(Long.parseLong(dto.getRoleId().toString())));
		u1.setUsername(dto.getUserName());
		u1.setStatus(Integer.parseInt(dto.getStatus()));
		u1.setFavouritGame(dto.getFavouritGame());
		u1.setFirstMobileNo(dto.getFirstMobileNo());
		u1.setPlaceOfBirth(dto.getPlaceOfBirth());
		u1.setMotherMaidenName(dto.getMotherMaidenName());
		long c=(long)sessionFactory.getCurrentSession().save(u1);
		if(c>0)
		{
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("User added successfully"));
			r.setBusinessMessage(bm);
			r.setObj(userList(Status.Active.getValue(),institutionId));
			r.setSuccessobj(u1);
		}
		//System.out.println("c:"+c);
		r.setObj(userList(Status.Active.getValue(),institutionId));
		return r;
		}
		
		

	
	public Role getRole(long id) {
		Role r = (Role) sessionFactory.getCurrentSession().createQuery("from Role where roleUid=" + id).uniqueResult();
		return r;
	}
	

	 private  CommonResponse getStudentsListFromExcel(String FILE_PATH, long instId, long userId) {
		 int k=0;
		 int j=0;
		 List<BusinessMessage> bm=new ArrayList<>();
		 CommonResponse r=new CommonResponse();
		 List<ExcelDto> studentList = new ArrayList();
	        FileInputStream fis = null;
	        try {
	            fis = new FileInputStream(FILE_PATH);
	 
	            // Using XSSF for xlsx format, for xls use HSSF
	            Workbook workbook = new XSSFWorkbook(fis);
	 
	            int numberOfSheets = workbook.getNumberOfSheets();
	            //looping over each workbook sheet
	            for (int i = 0; i < numberOfSheets; i++) {
	                Sheet sheet = workbook.getSheetAt(i);
	                Iterator rowIterator = sheet.iterator();
	 
	                //iterating over each row
	                while (rowIterator.hasNext()) {
	                   // if(i>0) {
	                    ExcelDto student = new ExcelDto();
	                    Row row = (Row) rowIterator.next();
	                    Iterator cellIterator = row.cellIterator();
	 
	                    //Iterating over each cell (column wise)  in a particular row.
	                    while (cellIterator.hasNext()) {
	                    	try {

	                    		 
		                        Cell cell = (Cell) cellIterator.next();
		                        //The Cell Containing String will is name.
		                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
		                        if (cell.getColumnIndex() == 0) {
                                   try {
    		                            student.setCustomerName(cell.getStringCellValue());
									} catch (Exception e) {
										e.printStackTrace();
									}

		                        }
		                        if (cell.getColumnIndex() == 1) {
                                try {
    		                            student.setFatherName(cell.getStringCellValue());
									} catch (Exception e) {
										e.printStackTrace();
									}

		                        }
		                        if (cell.getColumnIndex() == 2) {
		                        	try {
		 		                        	//System.out.println("student.getMonileNo::"+cell.getStringCellValue());
		 		                        	if(cell.getStringCellValue() != null)
		 		                            student.setMobileNo(cell.getStringCellValue().trim());
									} catch (Exception e) {
										e.printStackTrace();
									}
		                        }
//	                            }
		                        }
		                        else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                                    try {
                                    	 if (cell.getColumnIndex() == 3) {
     		                                student.setpDeposits(cell.getNumericCellValue());
     		                            }
     		                            
     		                            else if (cell.getColumnIndex() == 4) {
     		                                student.setLoanprinciple(cell.getNumericCellValue());
     		                            }
     		                            
     		                            //Cell with index 3 contains marks in English
     		                            else if (cell.getColumnIndex() == 5) {
     		                                student.setLoanAmountperInstallment((int)cell.getNumericCellValue());
     		                            }
     		                           
     		                            else if (cell.getColumnIndex() == 6) {
     		                                student.setSpLoansPrinciple(cell.getNumericCellValue());
     		                            }
									} catch (Exception e) {
										e.printStackTrace();
									}
		                           
		                   
		                        }
		                    k++;
							} catch (Exception e) {
								e.printStackTrace();
								bm.add(new BusinessMessage("Line no:"+k+" not saved."));
								r.setServiceStatus(ServiceStatus.ERROR);
								j++;
							}
	                    }
	                    if(j == 0) {
	                    student.setInstitution(getInstitution(instId));
	                    student.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), instId, Status.Active.getValue()));
	                    student.setCreatedBy(userId);
	                    student.setLocalDateTime(LocalDateTime.now());
	                    
	                    if(student.getLoanAmountperInstallment()>0 && student.getLoanprinciple()>0)
	                    student.setNoOfInstallments((int) (student.getLoanprinciple()/student.getLoanAmountperInstallment()));
	                  //  System.out.println("getMobileNo::"+student.getMobileNo());
	                    long c1=(long)sessionFactory.getCurrentSession().save(student);
	                    if(c1>0)
	                    studentList.add(student);
	                    }
	                    j=0;
	                }
	              // }
	            }
	 
	            fis.close();
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //System.out.println("BM size()::"+bm.size());
	        r.setObj(studentList);
			r.setBusinessMessage(bm);
	        return r;
	    }
	@Override
	public CommonResponse saveExcelDto(String filepath,long instId,long userId) {
		
		CommonResponse r=new CommonResponse();
		List<BusinessMessage> bm=new ArrayList<>();
		
	    Occurrance ooo= ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), instId);
	    if(ooo == null) {
	    	bm.add(new BusinessMessage("Sangam is not started.."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
			r.setObj(null);
			r.setSuccessobj(null);
			return r;
	    }
	    
	    Ledger ll=getActiveLedger(LedgerStatusE.Open.getValue(), instId);
	    int k=0;
	    int j=0;
	    int temp=0;
		CommonResponse r11=getStudentsListFromExcel(filepath,instId,userId);
		List<ExcelDto> list=(List<ExcelDto>) r11.getObj();
		bm.addAll(r11.getBusinessMessage());
		//System.out.println("r11.getBusinessMessage()::"+r11.getBusinessMessage().size());
		r.setServiceStatus(r11.getServiceStatus());
		for(ExcelDto e:list)
		{
		k++;
		if(e.getCustomerName() == null || e.getCustomerName().trim().equalsIgnoreCase("")) {
			bm.add(new BusinessMessage("Line no:"+k+" not saved because of Customer name is empty."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
			j++;
		}
		
		Customer ccc=getCustomerByName(e.getCustomerName(), Status.Active.getValue(), instId);
		if(ccc != null)
		{
			bm.add(new BusinessMessage(e.getCustomerName()+" already existed."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
			j++;
		}
		
		if(j==0) {
			long aa = 0;
	    //System.out.println(e.getCustomerName()+" "+e.getFatherName()+" "+e.getMobileNo()+" "+e.getpDeposits()+" "+e.getpFine()+" "+e.getPaidInstallments()+" "+e.getpLoansAmount()+" "+e.getpLoansFine()+" "+e.getpLoansInterest()+" "+e.getSpLoansPrinciple()+" "+e.getpSPLoansFine()+" "+e.getpSPLoansInterest());
			Institution ii=getInstitution(instId);
			Address a=new Address();
			if(ii != null) {
			a.setDistrict(ii.getAddress_id().getDistrict());
			a.setLandMark(ii.getAddress_id().getLandMark());
			a.setMandal(ii.getAddress_id().getMandal());
			a.setPincode(ii.getAddress_id().getPincode());
			a.setState(ii.getAddress_id().getState());
			a.setVillage(ii.getAddress_id().getVillage());
			 aa=(long)sessionFactory.getCurrentSession().save(a);
			}
			
			
	    Customer c=new Customer();
	    c.setCustomerId(getmaxCustomerId(instId)+1);
		c.setCreatedDate(LocalDate.now());
		c.setCustomerName(e.getCustomerName());
		c.setFatherName(e.getFatherName());
		c.setPhoneNo(e.getMobileNo());
		c.setInstitution(getInstitution(instId));
		c.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), instId, Status.Active.getValue()));
		c.setStatus(Status.Active.getValue());
		c.setAddress(getAddress(aa));
		long cc=(long)sessionFactory.getCurrentSession().save(c);
		//System.out.println(c.getCustomerName()+"::"+cc);
		if(cc>0)
		{
			temp++;
			  if(ll != null && (e.getpDeposits()>=0))
			   {
				   //ll.setOpeningBalance(ll.getOpeningBalance()+e.getpDeposits());
				   ll.setBalance(ll.getBalance()+e.getpDeposits());
				   sessionFactory.getCurrentSession().update(ll);
				   
					Deposits d=new Deposits();
					d.setCreatedBy(String.valueOf(userId));
					d.setDepositDate(LocalDateTime.now());
					d.setCustomer(getCustomer(cc,instId));
					d.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), instId, Status.Active.getValue()));
					d.setAmountPaid(e.getpDeposits());
					d.setInstitution(getInstitution(instId));
					d.setDepositType(DepositType.Initial_Contribution.getValue());
					d.setLedger(ll);
					long ddd=(long)sessionFactory.getCurrentSession().save(d);
					
					ddao.saveReceipt(c, ddd+"", userId, instId);
			   }
			
			
		
			
//			if(e.getpDeposits()>0) {
//			PendingDeposits p=new PendingDeposits();
//			p.setCreatedDate(LocalDateTime.now());
//			p.setCustomer(getCustomer(cc, instId));
//			p.setInstitution(getInstitution(instId));
//			//p.setNoOfMonths(e.getN);
//			p.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Deposits.getValue(), instId, Status.Active.getValue()));
//			p.setPaidStatus("N");
//			p.setPendingAmount(e.getpDeposits());
//			p.setFine(e.getpFine());
//			sessionFactory.getCurrentSession().save(p);
//			}
//			PendingLoans l=new PendingLoans();
//			l.setCreatedDate(LocalDateTime.now());
//			l.setCustomer(getCustomer(cc, instId));
//			l.setInstitution(getInstitution(instId));
//			l.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), instId, Status.Active.getValue()));
//			l.setPaidStatus("N");
//			l.setPendingInstallments(e.getNoOfInstallments()-e.getPaidInstallments());
//			l.setPendingInterest(e.getpLoansInterest());
//			l.setPendingAmount(e.getpLoansAmount());
//			l.setPendingFine(e.getpFine());
//			sessionFactory.getCurrentSession().save(l);
			if(e.getLoanprinciple()>0 && e.getNoOfInstallments()>0) {
				
			Loans l1=new Loans();
			l1.setCreatedBy(String.valueOf(userId));
			l1.setCustomer(getCustomer(c.getCustomer_uid(),instId));
			l1.setInterestsType(getInterestTypeByTransactionType(TransactionTypeE.Loans.getValue(), Status.Active.getValue(), instId));
			 // l1.setIntroducer_id((int)getCustomer(e.getIntroducerId(),instId).getCustomer_uid());
			l1.setLoan_paid_date(LocalDateTime.now());
			l1.setInstitution(getInstitution(instId));
			  l1.setLoanPrinciple(e.getLoanprinciple());
			  l1.setNoOfInstallments(e.getNoOfInstallments());
			  l1.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Loans.getValue(), instId, Status.Active.getValue()));
			  l1.setPaidInstallments(e.getPaidInstallments());
			l1.setUpdatedBy(String.valueOf(userId));
			l1.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), instId));
			l1.setTypeOfEntry(TypeOfEntry.Previous_Entry.getValue());
			long c1=(long)sessionFactory.getCurrentSession().save(l1);
			//System.out.println("loans::"+c1);
			List<LoanHistory> lh=getLoanHistoryList(c1, instId);
			if(lh.size() == 0) {
				int noOfInstallments=e.getNoOfInstallments();
				for(int i=1;i<=noOfInstallments;i++)
				{
					LoanHistory h=new LoanHistory();
					h.setLoanPaidDate(LocalDateTime.now());
					h.setInstitution(getInstitution(instId));
					h.setInstallmentId(i);
					if(i<=e.getPaidInstallments()) {
						h.setPaidAmount((e.getLoanPaidAmount()/e.getNoOfInstallments()));
					    h.setPaidStatus("Y");
					}
					else if(i==e.getPendingInstallments())
					{
						h.setPaidStatus("N");
						h.setPaidInterest(e.getpLoansInterest());
						h.setFine(e.getpFine());
					}
					else {
						h.setPaidStatus("N");
					}
					h.setLoans(l1);
					sessionFactory.getCurrentSession().save(h);
				}
			}
			}
			
//			PendingSPLoans sl=new PendingSPLoans();
//			sl.setCreatedDate(LocalDateTime.now());
//			sl.setCustomer(getCustomer(cc, instId));
//			sl.setInstitution(getInstitution(instId));
//			sl.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Special_Loans.getValue(), instId, Status.Active.getValue()));
//			sl.setPaidStatus("N");
//			sl.setPendingInterest(e.getpSPLoansInterest());
//			sl.setPendingAmount(e.getSpLoansPrinciple());
//			sl.setPendingFine(e.getpSPLoansFine());
//			sessionFactory.getCurrentSession().save(sl);
			if(e.getSpLoansPrinciple()>0)
			{

				SpecialLoan l11=new SpecialLoan();
				l11.setCreatedBy(String.valueOf(userId));
				l11.setCustomer(getCustomer(c.getCustomer_uid(),instId));
				l11.setInterestsType(getInterestTypeByTransactionType(TransactionTypeE.Special_Loans.getValue(), Status.Active.getValue(), instId));
				l11.setInterest(e.getpSPLoansInterest());
				l11.setFine(e.getpSPLoansFine());
				//if(loanDto.getIntroducer() != null)
				  //l11.setIntroducer_id((int)getCustomer(e.getIntroducerId(),instId).getCustomer_uid());
				l11.setLoan_paid_date(LocalDateTime.now());
				l11.setInstitution(getInstitution(instId));
				l11.setPaidStatus("N");
				//if(loanDto.getAmount() != null)
				  l11.setLoanPrinciple(e.getSpLoansPrinciple());
				//if(loanDto.getOccurranceId() != null)
				  l11.setOccurrance(getOccuranceByInstitution(TransactionTypeE.Special_Loans.getValue(), instId, Status.Active.getValue()));
				l11.setUpdatedBy(String.valueOf(userId));
				l11.setLedger(getActiveLedger(LedgerStatusE.Open.getValue(), instId));
				l11.setTypeOfEntry(TypeOfEntry.Previous_Entry.getValue());
				long c11=(long)sessionFactory.getCurrentSession().save(l11);
			}
			
		   if(ll != null && (e.getLoanprinciple()>0 && e.getNoOfInstallments()>0))
		   {
			   ll.setOpeningBalance(ll.getOpeningBalance()+e.getLoanprinciple());
			   ////ll.setBalance(ll.getBalance()+e.getLoanprinciple());
			   sessionFactory.getCurrentSession().update(ll);
			   
			   //System.out.println("ledgerUpated:"+ll.getOpeningBalance());
		   }
		   
		   if(ll != null && e.getSpLoansPrinciple()>0)
		   {
			   ll.setOpeningBalance(ll.getOpeningBalance()+e.getSpLoansPrinciple());
			   ////ll.setBalance(ll.getBalance()+e.getSpLoansPrinciple());
			   sessionFactory.getCurrentSession().update(ll);
			   
			   //System.out.println("ledgerUpated:"+ll.getOpeningBalance());
		   }
		}
			
		}
		
        j=0;
		
		}
		
		if(temp>0)
		{   
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("Customers supplied through excel are successfully saved..."));
		}
		r.setObj(customerList(Status.Active.getValue(),instId));
		r.setBusinessMessage(bm);
		return r;
	}
	
	public InterestType getInterestTypeByTransactionType(long transactionType,int status,long instiid)
	{
		InterestType cust=(InterestType)sessionFactory.getCurrentSession().createQuery("from InterestType where transactionType="+transactionType+" and status="+status+" and institution.institution_uid="+instiid).uniqueResult();
		return cust;
	}
	
	@Override
	public Ledger getActiveLedger(int ledgerStatusId,long instid) {
		Ledger cust=(Ledger) sessionFactory.getCurrentSession().createQuery("from Ledger where status="+ledgerStatusId+" and institution.institution_uid="+instid).uniqueResult();
		return cust;
	}

	public List<LoanHistory> getLoanHistoryList(long loanId,long institutionId)
	{
		List<LoanHistory> cust=(List<LoanHistory>)sessionFactory.getCurrentSession().createQuery("from LoanHistory where loansId="+loanId+" and institution.institution_uid="+institutionId).list();
		return cust;
	}
	
	public List<Occurrance> getOccuranceList(long occuId,long institutionId)
	{
		List<Occurrance> cust=sessionFactory.getCurrentSession().createQuery("from Occurrance where institution.institution_uid="+institutionId+" and occurranceUid>="+occuId+" order by occurranceUid desc")
				.list();
		return cust;
	}
	
	public List<Deposits> getDepositsList(long occurranceId,long customerId,long instId)
	{
		List<Deposits> cust=sessionFactory.getCurrentSession().createQuery("from Deposits where occurrance.occurranceUid="+occurranceId+" and customer.customerUid="+customerId+" and institution.institution_uid="+instId).list();
		return cust;
	}
	
	public Loans getLoanByCustomerAndOccurrance(long customerId,long occuId,long institutionId)
	{
		Loans cust=(Loans)sessionFactory.getCurrentSession().createQuery("from Loans where customer.customerUid="+customerId+" and occurrance.occurranceUid="+occuId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	public SpecialLoan getSpecialLoanByCustAndOcc(long custId,long occId,long institutionId)
	{
		SpecialLoan cust=(SpecialLoan)sessionFactory.getCurrentSession().createQuery("from SpecialLoan where customer.customerUid="+custId+" and occurrance.occurranceUid="+occId+" and institution.institution_uid="+institutionId).uniqueResult();
		return cust;
	}
	
	@Override
	public CommonResponse closeCustomer(long customerId, long instid) {
		CommonResponse r=new CommonResponse();
		List<BusinessMessage> bm=new ArrayList<>();
		List<PersonalHistory> pp =new ArrayList<>();
		Customer cust=ddao.getCustomer(customerId,Status.Active.getValue(), instid);
	    Occurrance ooo= ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), instid);
	    if(ooo == null) {
	    	bm.add(new BusinessMessage("Sangam is not started.."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
			r.setObj(null);
			r.setSuccessobj(null);
			return r;
	    }
		if(cust != null) {
			List<Occurrance> occlist=getOccuranceList(cust.getOccurrance().getOccurranceUid(), instid);
			//System.out.println("occlist::"+occlist.size());
		for(Occurrance o:occlist)
		{
			Loans loans=getLoanByCustomerAndOccurrance(customerId, o.getOccurranceUid(), instid);
			SpecialLoan sp=getSpecialLoanByCustAndOcc(customerId, o.getOccurranceUid(), instid);
			List<Deposits> dlist=getDepositsList(o.getOccurranceUid(), customerId, instid);
			////System.out.println("dlist::"+dlist.size());
			for(Deposits d:dlist)
			{
			PersonalHistory p =new PersonalHistory();
			p.setCoveredMonth(o.getCoveredMonth().toString());
			p.setCustmerName(cust.getCustomerName());
			p.setCustomerId(customerId);
			p.setDepositAmount(d.getAmountPaid());
			p.setDepositFine(d.getFine());
			p.setFatherName(cust.getFatherName());
			pp.add(p);
			//System.out.println("deposits added");
			}
			if(loans != null) {
		    PersonalHistory p =new PersonalHistory();
			//p.setLoanClearedDate(loans);
			p.setLoanId(loans.getLoans_uid());
			p.setLoanPrinciple(loans.getLoanPrinciple());
			p.setLoanSanctionedDate(loans.getLoan_paid_date().toString());
			p.setNoOfInstallments(loans.getNoOfInstallments());
			p.setPaidInstallments(loans.getPaidInstallments());
			pp.add(p);
			Set<LoanHistory> lhhh=loans.getLoanHistory();
		    for(LoanHistory h:lhhh) {
		    PersonalHistory p1 =new PersonalHistory();
			p1.setInstallmentNo((int) h.getInstallmentId());
			p1.setLhInstallmetAmount(h.getPaidAmount());
			p1.setLhInterest(h.getPaidInterest());
			p1.setLhloanFine(h.getFine());
			pp.add(p1);
		    }
			}
			if(sp != null) {
			//p.setSploanClearedDate(sploanClearedDate);
		    PersonalHistory p =new PersonalHistory();
			p.setSploanId(sp.getSpecialLoanUid());
			p.setSploanPrinciple(sp.getLoanPrinciple());
			p.setSploanSanctionedDate(sp.getLoan_paid_date().toString());
			pp.add(p);
			for(SpLoanHistory s:sp.getSploanHistory()) {
				PersonalHistory p1 =new PersonalHistory();
				p1.setSploanhFine(s.getPaidAmount());
				p1.setSploanhInterest(s.getPaidInterest());
				p1.setSploanhPaidAmount(s.getFine());	
				pp.add(p1);
			}
			
			
			}
			
		}
		TransactionSummary s= getTransactionSummary(customerId,instid);
		r.setSuccessobj(s);
		bm.add(new BusinessMessage("Personal Details Successfullyfetched"));
		r.setBusinessMessage(bm);
		r.setServiceStatus(ServiceStatus.SUCCESS);
		//System.out.println("pp.size()"+pp.size());
		r.setObj(pp);
		}
		else {
			bm.add(new BusinessMessage("Invalid Customer"));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
		}
		

		
		return r;
	}
	
	public double getTotalTransactionSummary(long instid)
	{
		Ledger currentBalance=ddao.getActiveLedger(LedgerStatusE.Open.getValue(), instid);
		List<Loans> loans=ddao.getLoans(instid);
		List<SpecialLoan> spLoans=ddao.getSpecialLoans(instid);
		TransactionSummary s=new TransactionSummary();
		if(currentBalance != null) {
		s.setCurrentBalance(currentBalance.getBalance());
		s.setTotalLoans(loans.stream().filter(e->e.getNoOfInstallments()>e.getPaidInstallments()).mapToDouble(e->((e.getLoanPrinciple()/e.getNoOfInstallments())*(e.getNoOfInstallments()-e.getPaidInstallments()))).sum());
		s.setTotalSpLoans(spLoans.stream().filter(e->e.getPaidStatus().equalsIgnoreCase("N")).mapToDouble(e->e.getLoanPrinciple()).sum());
		s.setNoOfCustomers((int) ddao.getCustomerList(Status.Active.getValue(),instid).stream().count());
		}
		
		return (s.getCurrentBalance()+s.getTotalLoans()+s.getTotalSpLoans())/s.getNoOfCustomers();
	}
	
	public TransactionSummary getTransactionSummary(long custId,long instid)
	{
		double totaldeposits=0;
		double totalloans=0;
		double sptotalloans=0;
		CommonResponse list=null;
		
		List<Customer> clistt=ddao.getCustomerList(Status.Active.getValue(), instid);
		for(Customer c:clistt) {
	    list=ddao.searchOccurance(c.getCustomer_uid(), instid);
		if(list != null && list.getObj() != null) {
		DepositDtoList dtoList=(DepositDtoList) list.getObj();
		if(dtoList != null && dtoList.getDepositdto() != null) {
		List<DepositDto> dtoll=dtoList.getDepositdto();
		totaldeposits=totaldeposits+dtoll.stream().mapToDouble(e->e.getTotal()).sum();
		}
		}
		list=null;
		
		list=ddao.getLoanDetails(c.getCustomer_uid(),instid);
		if(list != null && list.getObj() != null) {
		LoanDtoList list1=(LoanDtoList) list.getObj();
		if(list1 != null && list1.getLoanDto()!= null) {
		List<LoanDto> ltolist=list1.getLoanDto();
		for(LoanDto d:ltolist) {
			totalloans=totalloans+d.getRemainingInstallmentsList().stream().mapToDouble(e->e.getFine()+e.getInterest()).sum();
		}
		totalloans=totalloans+ltolist.stream().mapToDouble(e->e.getRemainingAmount()).sum();
		}
		}
		list=null;
		
		list=ddao.getSpecialLoanDetails(c.getCustomer_uid(),instid);
		if(list != null && list.getObj() != null) {
		LoanDtoList list11=(LoanDtoList) list.getObj();
		if(list11 != null && list11.getLoanDto()!= null) {
		List<LoanDto> ltolist1=list11.getLoanDto();
		sptotalloans=sptotalloans+ltolist1.stream().mapToDouble(e->e.getProposedPayAmount()+e.getProposedPayInterest()+e.getFine()).sum();
		}
		}
		
		}
		
		System.out.println("Totals::"+totaldeposits+" ,"+totalloans+" ,"+sptotalloans);
		
		
		DepositDtoList deposis=null;
		Object obj= ddao.searchOccurance(custId, instid).getObj();
		if(obj != null)
		{
		 deposis=(DepositDtoList) obj;	
		}
		Ledger currentBalance=ddao.getActiveLedger(LedgerStatusE.Open.getValue(), instid);
		//List<Loans> loans=ddao.getLoans(instid);
		//List<SpecialLoan> spLoans=ddao.getSpecialLoans(instid);
		TransactionSummary s=new TransactionSummary();
		if(currentBalance != null) {
		s.setCurrentBalance(currentBalance.getBalance());
		s.setTotaldeposits(totaldeposits);
		s.setTotalLoans(totalloans);
		s.setTotalSpLoans(sptotalloans);
		//s.setTotalLoans(loans.stream().filter(e->e.getNoOfInstallments()>e.getPaidInstallments()).mapToDouble(e->((e.getLoanPrinciple()/e.getNoOfInstallments())*(e.getNoOfInstallments()-e.getPaidInstallments()))).sum());
		//s.setTotalSpLoans(spLoans.stream().filter(e->e.getPaidStatus().equalsIgnoreCase("N")).mapToDouble(e->e.getLoanPrinciple()).sum());
		s.setNoOfCustomers((int) ddao.getCustomerList(Status.Active.getValue(),instid).stream().count());
        s.setPendingLoans(ddao.getLoanByCust(custId, instid));
        SpecialLoan sp=ddao.getSpecialLoanByCust(custId, instid);
        if(sp != null && sp.getPaidStatus().equalsIgnoreCase("N"))
		s.setPendingSpLoans(sp);
		s.setPendingDeposits(deposis.getDepositdto());
		}
		return s;
	}
	@Override
	public CommonResponse withdrawCustomer(long customerId, long instid) {
		CommonResponse r=new CommonResponse();
		List<BusinessMessage> bm=new ArrayList<>();
		Ledger ledger=ddao.getActiveLedger(LedgerStatusE.Open.getValue(), instid);
		Occurrance oc= ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(), instid);
		Customer c=getCustomer(customerId, instid);
		if(c != null)
		{
			TransactionSummary s= getTransactionSummary(customerId,instid);
			double withdrawalAmount=((s.getCurrentBalance()+s.getTotaldeposits()+s.getTotalLoans()+s.getTotalSpLoans())/s.getNoOfCustomers());
			
			if(ledger.getBalance()>=withdrawalAmount) {
			ledger.setBalance(ledger.getBalance()-withdrawalAmount);
			sessionFactory.getCurrentSession().update(ledger);
			
			c.setShare(withdrawalAmount);
			c.setWithDrawalOccurrance(oc);
			c.setStatus(Status.InActive.getValue());
			sessionFactory.getCurrentSession().update(c);
			}
			else {
				bm.add(new BusinessMessage("NO Sufficient funds.."));
				r.setBusinessMessage(bm);
				r.setServiceStatus(ServiceStatus.ERROR);
				r.setObj(c);
				
				return r;
			}
			
		}
		bm.add(new BusinessMessage("Customer Successfully withdrawn from Sangam"));
		r.setBusinessMessage(bm);
		r.setServiceStatus(ServiceStatus.SUCCESS);
		r.setObj(c);
		
		return r;
	}
	@Override
	public CommonResponse saveRole(RoleDto roleDto, long userId,long instId,User u) {
		// TODO Auto-generated method stub
		List<BusinessMessage> bm=new ArrayList<>();
		CommonResponse r=new CommonResponse();
		
		
		if(roleDto.getRoleName() == null || roleDto.getRoleName().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please enter RoleName."));
			r.setBusinessMessage(bm);
			r.setObj(roleList(Status.Active.getValue(),u.getRole().getRoleName(),instId));
			return r;
		}
		
		if(roleDto.getInstitutionId() == null || roleDto.getInstitutionId().trim().equalsIgnoreCase(""))
		{
			r.setServiceStatus(ServiceStatus.ERROR);
			bm.add(new BusinessMessage("please select Institution."));
			r.setBusinessMessage(bm);
			r.setObj(roleList(Status.Active.getValue(),u.getRole().getRoleName(),instId));
			return r;
		}
		
		Role iir=null;
		if(roleDto.getRoleUid() != null && !roleDto.getRoleUid().equalsIgnoreCase(""))
		 iir=udao.getRole(Integer.parseInt(roleDto.getRoleUid()));
		//System.out.println("IIIIIIIIrrI::"+iir);
		if(iir != null) {
			iir.setRoleName(roleDto.getRoleName());
			iir.setStatus(roleDto.getStatus());
			//System.out.println(iir.getRoleUid()+"  ,  "+iir.getInstitution().getInstitution_uid());
			List<RoleMenuLink> rm=getRoleMenuLink(iir.getRoleUid(), iir.getInstitution().getInstitution_uid());
			for(RoleMenuLink r1:rm)
			{
				r1.setAccessible_status("N");
				sessionFactory.getCurrentSession().update(r1);
			}
				StringTokenizer st = new StringTokenizer(roleDto.getIsAccessible(),",");
				 while (st.hasMoreTokens()) {
					 try {
						 //System.out.println(Integer.parseInt(roleDto.getRoleUid())+"  ,  "+st.nextToken()+"  ,  "+Long.valueOf(roleDto.getInstitutionId()));
							RoleMenuLink roleMenuLink=getRoleMenuLinkByManuMaster(Integer.parseInt(roleDto.getRoleUid()), st.nextToken(), Long.valueOf(roleDto.getInstitutionId()));
							//System.out.println("roleMenuLink::"+roleMenuLink);
							if(roleMenuLink != null)
							{
								roleMenuLink.setAccessible_status("Y");
								sessionFactory.getCurrentSession().update(roleMenuLink);
							}
							else {
								roleMenuLink=new RoleMenuLink();
							roleMenuLink.setAccessible("Y");
							//roleMenuLink.setDisplayOrderId(displayOrderId);
							roleMenuLink.setInventoryMenu(udao.getMenuMasterByName(st.nextToken()));
							roleMenuLink.setRole(udao.getRoleByName(roleDto.getRoleName(),Long.valueOf(roleDto.getInstitutionId()))); 
							roleMenuLink.setInstitution(getInstitution(Long.valueOf(roleDto.getInstitutionId())));
							long rr11=(long)sessionFactory.getCurrentSession().save(roleMenuLink);
							//System.out.println("roleMenuLink saved::"+rr11);
							}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				 }

			sessionFactory.getCurrentSession().update(iir);
			
			bm.add(new BusinessMessage("Role updated succeessfully.."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.SUCCESS);
			r.setObj(roleList(Status.Active.getValue(),u.getRole().getRoleName(),instId));
			return r;
		}
		
		Role ii=udao.getRoleByName(roleDto.getRoleName(), Long.valueOf(roleDto.getInstitutionId()));
		//System.out.println("IIIIIIIII::"+ii);
		if(ii != null) {
			bm.add(new BusinessMessage("Role Name already existed."));
			r.setBusinessMessage(bm);
			r.setServiceStatus(ServiceStatus.ERROR);
			r.setObj(roleList(Status.Active.getValue(),u.getRole().getRoleName(),instId));
			return r;
		}

		if(ii == null)
		{
			Role role=new Role();
			role.setRoleName(roleDto.getRoleName());
			role.setStatus(roleDto.getStatus());
			role.setIsAccessible("Y");
			role.setInstitution(getInstitution(Long.valueOf(roleDto.getInstitutionId())));
			long rr=(long)sessionFactory.getCurrentSession().save(role);
			if(rr > 0)
			{
				StringTokenizer st = new StringTokenizer(roleDto.getIsAccessible(),",");
				 while (st.hasMoreTokens()) {
						RoleMenuLink roleMenuLink=new RoleMenuLink();
						roleMenuLink.setAccessible("Y");
						//roleMenuLink.setDisplayOrderId(displayOrderId);
						roleMenuLink.setInventoryMenu(udao.getMenuMasterByName(st.nextToken()));
						roleMenuLink.setRole(udao.getRoleByName(roleDto.getRoleName(),Long.valueOf(roleDto.getInstitutionId()))); 
						roleMenuLink.setInstitution(getInstitution(Long.valueOf(roleDto.getInstitutionId())));
						long rr11=(long)sessionFactory.getCurrentSession().save(roleMenuLink);
						//System.out.println("roleMenuLink saved::"+rr11);
				 }

			}
			
			
			r.setServiceStatus(ServiceStatus.SUCCESS);
			bm.add(new BusinessMessage("Role saved successfully."));
			r.setBusinessMessage(bm);
			r.setObj(roleList(Status.Active.getValue(),u.getRole().getRoleName(),instId));
			return r;
		}
		return r;

	}
	@Override
	public List<Recharge> getValidityList(int statusId) {
		List<Recharge> address=(List<Recharge>)sessionFactory.getCurrentSession().createQuery("from Recharge where  status="+statusId+" ").list();
		return address;
	}

}
