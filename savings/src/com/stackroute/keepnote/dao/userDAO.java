package com.stackroute.keepnote.dao;

import java.util.List;
import java.util.Map;

import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.Login;
import com.stackroute.keepnote.model.MenuMaster;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.User;

public interface userDAO {
	
	/* You Should not modify this interface.  You have to implement these methods in corresponding Impl class*/
	public char[] sendOTP(int length);
	
	public boolean saveUser(User user);

	public boolean deleteUser(int Id);

	public List<Note> getAllNotes();

	public Note getUserById(int noteId);

	public boolean UpdateUser(User user);
	
	public CommonResponse validateUser(Login login);
	
	public User getUserforforgotpassword(Login login);
	
	public List<MenuMaster> getmenus();
	
	public Role getRole(int id);
	
	public List<RoleMenuLink> findByRoleAndAccessible(Role role,String accessible);
	
	public List<RoleMenuLink> findByRoleAndInventoryMenuParentIdAndAccessible(Role role,int id,String accessible);
	
	public List<Institution> getInstitutions(int statusId);
	
	public Map<Long,String> getRoles(String roleName,long instId);
	
	public Map<Long,String>  getInstitutionList(String roleName,long institutionId);
	
	public MenuMaster getMenuMasterByName(String menuName);
	
	public Role getRoleByName(String name,long instId);
	
	public List<MenuMaster> getAllMenus(int statusId);
	
	

}
