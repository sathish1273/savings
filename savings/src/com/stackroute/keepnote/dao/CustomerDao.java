package com.stackroute.keepnote.dao;

import java.util.List;

import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.dto.CustomerDto;
import com.stackroute.keepnote.dto.InstitutionDto;
import com.stackroute.keepnote.dto.RoleDto;
import com.stackroute.keepnote.dto.UserDto;
import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.Ledger;
import com.stackroute.keepnote.model.Recharge;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.User;

public interface CustomerDao {
	
	public CommonResponse saveUser(UserDto customer,long institutionId,long userId);
	public CommonResponse saveCustomer(CustomerDto customer,long institutionId,long userId);
	public Customer getCustomer(long customerId,long instId);
	public CommonResponse saveInstitution(InstitutionDto institutionDto,long userId);
	public CommonResponse saveRole(RoleDto role,long userId, long instid,User u);
	public List<Role> roleList(int status,String roleName,long instId);
	public List<Customer> customerList(int status,long instiId);
	public List<User> userList(int statusId,long instId);
	public List<Institution> institutionList(int status);
	public List<User> userList(int statusId);
	public CommonResponse saveExcelDto(String filepath,long instId,long userId);
	public Ledger getActiveLedger(int ledgerStatusId, long instid);
	public CommonResponse closeCustomer(long customerId, long instid);
	public CommonResponse withdrawCustomer(long customerId, long instid);
	public User getUser(String name,long instId,int statusId);
	public User getUserById(long id,long instId);
	public double getTotalTransactionSummary(long instid);
	public List<RoleMenuLink> getRoleMenuLinkByInstitution(String accessible,long instId);
	public Recharge getValidity(long instId,int statusId);
	public List<Recharge> getValidityList(int statusId);
	public long getmaxCustomerId(long instId);
	public Customer getCustomerUid(long customerId,long instId);

}
