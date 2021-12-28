package com.stackroute.keepnote.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.dto.BusinessMessage;
import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.dto.ServiceStatus;
import com.stackroute.keepnote.dto.Status;
import com.stackroute.keepnote.model.Address;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.InterestType;
import com.stackroute.keepnote.model.Login;
import com.stackroute.keepnote.model.MenuMaster;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Otp;
import com.stackroute.keepnote.model.Recharge;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.User;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context. 
 *  
 * */

@Repository
@Transactional
public class UserDAOImpl implements userDAO {

	@Autowired
	SessionFactory sessionFactory;
	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	
	@Autowired
	CustomerDao cdao;
	
	@Autowired
	DepositTypeDAO ddao;

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveUser(User user) {
		user.setCreatedAt(LocalDate.now());
		sessionFactory.getCurrentSession().save(user);
		return true;

	}

	public boolean deleteUser(int Id) {
		return true;
	}

	public List<Note> getAllNotes() {
		return null;
	};

	public Note getUserById(int noteId) {
		return null;
	}

	public boolean UpdateUser(User user) {
		return false;
	}

	public Address getAddress(int id) {
		Address a = (Address) sessionFactory.getCurrentSession().createQuery("from Address where address_uid=" + id)
				.uniqueResult();
		return a;
	}

	public Role getRole(int id) {
		Role r = (Role) sessionFactory.getCurrentSession().createQuery("from Role where roleUid=" + id).uniqueResult();
		return r;
	}

	public Role getRoleByName(String name,long instId) {
		Role r = (Role) sessionFactory.getCurrentSession().createQuery("from Role where roleName='" + name + "' and institution.institution_uid="+instId+" and status="+Status.Active.getValue()+"")
				.uniqueResult();
		return r;
	}

	public Institution getInstitution(int id) {
		Institution i = (Institution) sessionFactory.getCurrentSession()
				.createQuery("from Institution where institution_uid=" + id).uniqueResult();
		return i;
	}
	
	public Institution getInstitution(String id) {
		Institution i = (Institution) sessionFactory.getCurrentSession()
				.createQuery("from Institution where institutionName=" + id).uniqueResult();
		return i;
	}

	public MenuMaster getMenuMaster(int id) {
		MenuMaster i = (MenuMaster) sessionFactory.getCurrentSession().createQuery("from MenuMaster where id=" + id)
				.uniqueResult();
		return i;
	}

	public MenuMaster getMenuMasterByName(String menuName) {
		MenuMaster i = (MenuMaster) sessionFactory.getCurrentSession()
				.createQuery("from MenuMaster where menuName='" + menuName + "'").uniqueResult();
		return i;
	}

	public InterestType getInterestType(String interestType) {
		InterestType i = (InterestType) sessionFactory.getCurrentSession()
				.createQuery("from InterestType where interestType='" + interestType + "'").uniqueResult();
		return i;
	}
	
	public Map<Long,String> getRoles(String roleName,long instId)
	{
		String query="";
		if(!roleName.equals("SuperAdmin"))
			query="and roleName not in ('SuperAdmin','Admin') and institution.institution_uid="+instId+"";
			
		return (Map<Long, String>) sessionFactory.getCurrentSession().createQuery("from Role where status="+Status.Active.getValue()+" "+query+" order by roleUid desc").list().stream().collect(Collectors.toMap(Role::getRoleUid, r->r.getRoleName()));
	}
	
	public Map<Long,String>  getInstitutionList(String roleName,long institutionId) {
		String query="";
		if(!roleName.equals("SuperAdmin"))
			query="where institution_uid in ("+institutionId+")";
		
		return (Map<Long,String>) sessionFactory.getCurrentSession().createQuery("from Institution "+query+" order by institution_uid desc").list().stream().collect(Collectors.toMap(Institution::getInstitution_uid, r->r.getInstitutionName()));
	}

	public CommonResponse validateUser(Login login) {
		CommonResponse r= new CommonResponse();
		List<User> u = sessionFactory.getCurrentSession().createQuery("from User").list();
		if (u.size() == 0) {

			List<Role> rr = sessionFactory.getCurrentSession().createQuery("from Role").list();
			{
				if (rr.size() == 0) {
					Role r1 = new Role();
					r1.setIsAccessible("Y");
					r1.setRoleName("SuperAdmin");
					r1.setInstitution(getInstitution(1));
					r1.setStatus(Status.Active.getValue());
					sessionFactory.getCurrentSession().save(r1);

					
					////System.out.println("Inital Role Saved");
				}
			}
			
//			  List<Address>
//			  aa=sessionFactory.getCurrentSession().createQuery("from Address").list();
//			  if(aa.size() == 0) { Address a=new Address(); a.setDistrict("Warangal");
//			  a.setHno("2-71"); a.setMandal("Paravathagiri"); a.setPincode("506369");
//			  a.setSurname("Sailu"); a.setVillage("Chintha Nekkonda");
//			  sessionFactory.getCurrentSession().save(a);
//			  ////System.out.println("Initial Address Saved"); 
//			  }
			 

			/*
			 * List<Institution>
			 * i=sessionFactory.getCurrentSession().createQuery("from Institution").list();
			 * if(i.size() == 0) { Institution ii=new Institution();
			 * ii.setInstitutionName("Yadava Sangam"); ii.setAddress_id(getAddress(1));
			 * sessionFactory.getCurrentSession().save(ii);
			 * ////System.out.println("Inital Institution Saved"); }
			 */

			List<MenuMaster> m = sessionFactory.getCurrentSession().createQuery("from MenuMaster").list();
			if (m.size() == 0) {
				
				MenuMaster mm7 = new MenuMaster();
				mm7.setMenuName("Settings");
				sessionFactory.getCurrentSession().save(mm7);

				MenuMaster mm5 = new MenuMaster();
				mm5.setMenuName("Institution");
				mm5.setUrl("institution");
				mm5.setParent(getMenuMasterByName("Settings"));
				sessionFactory.getCurrentSession().save(mm5);

				MenuMaster mm6 = new MenuMaster();
				mm6.setMenuName("Rate Of Interest");
				mm6.setUrl("InterestType");
				mm6.setParent(getMenuMasterByName("Settings"));
				sessionFactory.getCurrentSession().save(mm6);
				
				MenuMaster mm61 = new MenuMaster();
				mm61.setMenuName("Roles");
				mm61.setUrl("Role");
				mm61.setParent(getMenuMasterByName("Settings"));
				sessionFactory.getCurrentSession().save(mm61);
				
				
				MenuMaster mm1 = new MenuMaster();
				mm1.setMenuName("ShareHoders");
				sessionFactory.getCurrentSession().save(mm1);

				MenuMaster mm2 = new MenuMaster();
				mm2.setMenuName("Enroll Customers");
				mm2.setUrl("customer");
				mm2.setParent(getMenuMasterByName("ShareHoders"));
				sessionFactory.getCurrentSession().save(mm2);
				
				MenuMaster mm12 = new MenuMaster();
				mm12.setMenuName("Enroll Users");
				mm12.setUrl("user");
				mm12.setParent(getMenuMasterByName("ShareHoders"));
				sessionFactory.getCurrentSession().save(mm12);

				
				
				MenuMaster mm = new MenuMaster();
				mm.setMenuName("Transactions");
				sessionFactory.getCurrentSession().save(mm);
				
				MenuMaster mm4 = new MenuMaster();
				mm4.setMenuName("Sangam StartUp");
				mm4.setUrl("Occurance");
				mm4.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(mm4);
				
				
				MenuMaster pa = new MenuMaster();
				pa.setMenuName("Payments");
				pa.setUrl("deposits");
				pa.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(pa);
				
				MenuMaster mm11 = new MenuMaster();
				mm11.setMenuName("Settlement");
				mm11.setUrl("LedgerSummary");
				mm11.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(mm11);
				

				MenuMaster ex = new MenuMaster();
				ex.setMenuName("Expenses");
				ex.setUrl("Expenses");
				ex.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(ex);

			
				MenuMaster ex1 = new MenuMaster();
				ex1.setMenuName("Close Customer");
				ex1.setUrl("CloseCustomer");
				ex1.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(ex1);
				
				MenuMaster mm16 = new MenuMaster();
				mm16.setMenuName("Other Income");
				mm16.setUrl("OtherIncomes");
				mm16.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(mm16);
				
				MenuMaster mm17 = new MenuMaster();
				mm17.setMenuName("Rollback");
				mm17.setUrl("Rollback");
				mm17.setParent(getMenuMasterByName("Transactions"));
				sessionFactory.getCurrentSession().save(mm17);

				MenuMaster mm8 = new MenuMaster();
				mm8.setMenuName("Reports");
				sessionFactory.getCurrentSession().save(mm8);

				MenuMaster mm9 = new MenuMaster();
				mm9.setMenuName("Loans Summary");
				mm9.setUrl("MonthlyLoansSummary");
				mm9.setParent(getMenuMasterByName("Reports"));
				sessionFactory.getCurrentSession().save(mm9);

				MenuMaster mm10 = new MenuMaster();
				mm10.setMenuName("Deposits Summary");
				mm10.setUrl("MonthlyDepositsSummary");
				mm10.setParent(getMenuMasterByName("Reports"));
				sessionFactory.getCurrentSession().save(mm10);


				
				MenuMaster mm13 = new MenuMaster();
				mm13.setMenuName("Personal Summary");
				mm13.setUrl("PersonalSummary");
				mm13.setParent(getMenuMasterByName("Reports"));
				sessionFactory.getCurrentSession().save(mm13);
				
				MenuMaster mm14 = new MenuMaster();
				mm14.setMenuName("Audit Summary");
				mm14.setUrl("AuditSummary");
				mm14.setParent(getMenuMasterByName("Reports"));
				sessionFactory.getCurrentSession().save(mm14);
				
				MenuMaster mm15 = new MenuMaster();
				mm15.setMenuName("Unpaid Customers");
				mm15.setUrl("UnpaidCustomers");
				mm15.setParent(getMenuMasterByName("Reports"));
				sessionFactory.getCurrentSession().save(mm15);
				


				////System.out.println("Inital MenuMaster Saved");
			}

			List<RoleMenuLink> rm = sessionFactory.getCurrentSession().createQuery("from RoleMenuLink").list();

			
				RoleMenuLink mm22 = new RoleMenuLink();
				mm22.setAccessible("Y");
				mm22.setDisplayOrderId(1);
				mm22.setInventoryMenu(getMenuMasterByName("Settings"));
				mm22.setRole(getRoleByName("SuperAdmin",1));
				mm22.setInstitution(getInstitution(1));
				sessionFactory.getCurrentSession().save(mm22);
//
				RoleMenuLink mm6 = new RoleMenuLink();
				mm6.setAccessible("Y");
				mm6.setDisplayOrderId(1);
				mm6.setInventoryMenu(getMenuMasterByName("Institution"));
				mm6.setRole(getRoleByName("SuperAdmin",1));
				mm6.setInstitution(getInstitution(1));
				sessionFactory.getCurrentSession().save(mm6);
				
				RoleMenuLink mm7 = new RoleMenuLink();
				mm7.setAccessible("Y");
				mm7.setDisplayOrderId(2);
				mm7.setInventoryMenu(getMenuMasterByName("Roles"));
				mm7.setRole(getRoleByName("SuperAdmin",1));
				mm7.setInstitution(getInstitution(1));
				sessionFactory.getCurrentSession().save(mm7);
				
				RoleMenuLink mm20 = new RoleMenuLink();
				mm20.setAccessible("Y");
				mm20.setDisplayOrderId(2);
				mm20.setInventoryMenu(getMenuMasterByName("ShareHoders"));
				mm20.setRole(getRoleByName("SuperAdmin",1));
				mm20.setInstitution(getInstitution(1));
				sessionFactory.getCurrentSession().save(mm20);
			
				RoleMenuLink mm30 = new RoleMenuLink();
				mm30.setAccessible("Y");
				mm30.setDisplayOrderId(2);
				mm30.setInventoryMenu(getMenuMasterByName("Enroll Users"));
				mm30.setRole(getRoleByName("SuperAdmin",1));
				mm30.setInstitution(getInstitution(1));
				sessionFactory.getCurrentSession().save(mm30);

			User uu = new User();
			uu.setCreatedAt(LocalDate.now());
			uu.setEmail("sathish1273@gmail.com");
			uu.setFirstname("Vanga");
			uu.setLastname("Sathish");
			uu.setPassword("123");
			uu.setPhone("8801140530");
			uu.setUsername("sathish");
			uu.setFavouritGame("chess");
			uu.setFirstMobileNo("8801140530");
			uu.setPlaceOfBirth("Chintha nekkonda");
			uu.setMotherMaidenName("Vanga");
			uu.setInstitution(getInstitution(1));
			uu.setRole(getRoleByName("SuperAdmin",1));
			uu.setAddress(getAddress(1));
			uu.setStatus(Status.Active.getValue());
			sessionFactory.getCurrentSession().save(uu);
			////System.out.println("Inital USer Saved");
			
		}
        List<BusinessMessage> bmg=new ArrayList<>();
		Recharge v=cdao.getValidity(Long.valueOf(login.getInstitutionId()), Status.Active.getValue());
		//System.out.println("validity::"+v);
		if(v != null)
			//System.out.println("v.getTo()::"+v.getToDate());
		
		if(v == null && !login.getInstitutionId().equalsIgnoreCase("1"))
		{
			r.setObj("No validity");
			return r;
		}
		else if(v != null && LocalDate.now().compareTo(v.getToDate())>0 && !login.getInstitutionId().equalsIgnoreCase("1"))
		{
			r.setObj("No validity");
			return r;
		}
		User user = null;
		if(login.getAuthType().equalsIgnoreCase("questions"))
		{
			 user = (User) sessionFactory.getCurrentSession()
						.createQuery("from User where username='" + login.getUsername() + "' and password='"
								+ login.getPassword() + "' and institution.institution_uid=" + login.getInstitutionId()+" and status="+Status.Active.getValue()+""
										+ " and placeOfBirth='"+login.getPlaceOfBirth()+"' and firstMobileNo='"+login.getFirstMobileNo()+"' and favouritGame='"+login.getFavouritGame()+"' and motherMaidenName='"+login.getMotherMaidenName()+"'")
						.uniqueResult();
			
		}
		else {
			 user = (User) sessionFactory.getCurrentSession()
						.createQuery("from User where username='" + login.getUsername() + "' and password='"
								+ login.getPassword() + "' and institution.institution_uid=" + login.getInstitutionId()+" and status="+Status.Active.getValue()+"")
						.uniqueResult();
		}
		
		
		
		r.setSuccessobj(user);
		return r;
	}
	
	public User getUserforforgotpassword(Login login)
	{
		User user = (User) sessionFactory.getCurrentSession()
					.createQuery("from User where username='" + login.getUsername() + "' and institution.institution_uid=" + login.getInstitutionId()+" and status="+Status.Active.getValue()+""
									+ " and placeOfBirth='"+login.getPlaceOfBirth()+"' and firstMobileNo='"+login.getFirstMobileNo()+"' and favouritGame='"+login.getFavouritGame()+"' and motherMaidenName='"+login.getMotherMaidenName()+"'")
					.uniqueResult();
		return user;
	}

	public List<MenuMaster> getmenus() {

		List<MenuMaster> menumaster = sessionFactory.getCurrentSession().createQuery("from MenuMaster").list();

		return menumaster;
	}

	public List<RoleMenuLink> findByRoleAndAccessible(Role role, String accessible) {

		List<RoleMenuLink> menumaster = sessionFactory.getCurrentSession()
				.createQuery("from RoleMenuLink where role.roleUid=" + role.getRoleUid() + " and accessible_status='"
						+ accessible + "'")
				.list();

		return menumaster;
	}

	public List<RoleMenuLink> findByRoleAndInventoryMenuParentIdAndAccessible(Role role, int id, String accessible) {
		List<RoleMenuLink> menumaster = sessionFactory.getCurrentSession()
				.createQuery("from RoleMenuLink where role.roleUid=" + role.getRoleUid()
						+ " and inventoryMenu.parent.id=" + id + " and accessible_status='" + accessible + "'")
				.list();

		return menumaster;
	}

	public List<Institution> getInstitutions(int statusId) {
		//sessionFactory.getCurrentSession().createQuery("SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))").uniqueResult();
		//System.out.println("executed ee");
		List<Institution> insti = sessionFactory.getCurrentSession().createQuery("from Institution where status="+statusId+"").list();
		if (insti.size() == 0) {
//			Institution iii=getInstitution("Chintha Nekkonda");
//			if(iii != null)
//				return null;
			
			Address a=new Address();
			a.setDistrict("Warangal");
			a.setLandMark("Near Office");
			a.setMandal("Parvathagiri");
			a.setState("Telangana");
			a.setVillage("Chintha nekkonda");
			a.setPincode("506369");
			long iia = (long) sessionFactory.getCurrentSession().save(a);
			
			Institution i = new Institution();
			i.setInstitutionName("Chintha Nekkonda");
			i.setStatus(Status.Active.getValue());
			i.setAddress_id(getAddress((int)iia));
			i.setMessagesRequired("YES");
			long ii = (long) sessionFactory.getCurrentSession().save(i);
			if (ii != 0) {
				insti = sessionFactory.getCurrentSession().createQuery("from Institution where status="+statusId+"").list();
			}

		}

		return insti;

	}

	@Override
	public List<MenuMaster> getAllMenus(int statusId) {
		List<MenuMaster> a = (List<MenuMaster>) sessionFactory.getCurrentSession().createQuery("from MenuMaster")
				.list();
		return a;
	}
	
	public char[] sendOTP(int length) {
		String numbers="0123456789";
		Random r=new Random();
		char[] otp=new char[length];
		for(int i=0;i<length;i++)
		{
			otp[i]=numbers.charAt(r.nextInt(numbers.length()));
		}
		return otp;
	}


}
