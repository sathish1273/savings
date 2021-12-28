package com.stackroute.keepnote.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
/*import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.keepnote.dao.AuditDto;
import com.stackroute.keepnote.dao.CustomerDao;
import com.stackroute.keepnote.dao.DepositTypeDAO;
import com.stackroute.keepnote.dao.userDAO;
import com.stackroute.keepnote.dto.BusinessMessage;
import com.stackroute.keepnote.dto.CalculationTypeE;
import com.stackroute.keepnote.dto.CommonResponse;
import com.stackroute.keepnote.dto.CustomerDto;
import com.stackroute.keepnote.dto.DepositDto;
import com.stackroute.keepnote.dto.DepositDtoList;
import com.stackroute.keepnote.dto.DepositsSuccess;
import com.stackroute.keepnote.dto.Districts;
import com.stackroute.keepnote.dto.InstitutionDto;
import com.stackroute.keepnote.dto.InterestDto;
import com.stackroute.keepnote.dto.InterestTypeE;
import com.stackroute.keepnote.dto.InventoryMenu;
import com.stackroute.keepnote.dto.LedgerStatusE;
import com.stackroute.keepnote.dto.LedgerSummaryDto;
import com.stackroute.keepnote.dto.LoanDtoList;
import com.stackroute.keepnote.dto.Mandals;
import com.stackroute.keepnote.dto.MonthlyLoansSummaryDto;
import com.stackroute.keepnote.dto.OccurranceDto;
import com.stackroute.keepnote.dto.PdfDto;
import com.stackroute.keepnote.dto.QuestionsEnum;
import com.stackroute.keepnote.dto.RoleDto;
import com.stackroute.keepnote.dto.ServiceStatus;
import com.stackroute.keepnote.dto.States;
import com.stackroute.keepnote.dto.Status;
import com.stackroute.keepnote.dto.TransactionTypeE;
import com.stackroute.keepnote.dto.UserDto;
import com.stackroute.keepnote.dto.Villages;
import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.DepositType;
import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Expenses;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.InterestType;
import com.stackroute.keepnote.model.Interests;
import com.stackroute.keepnote.model.Ledger;
import com.stackroute.keepnote.model.Login;
import com.stackroute.keepnote.model.MenuMaster;
import com.stackroute.keepnote.model.Occurrance;
import com.stackroute.keepnote.model.OtherIncome;
import com.stackroute.keepnote.model.Otp;
import com.stackroute.keepnote.model.Receipt;
import com.stackroute.keepnote.model.Recharge;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.User;
/*import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;*/
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	String msg = "";
	Customer c=null;
	Receipt tt = null;

	public static final String ACCOUNT_SID = "AC0dd6f6b2a04938b60c61734a8f62f30a";
	public static final String AUTH_TOKEN = "190aec9cfdbd86ca84f6acaab5ac0866";
	public static final String TWILIO_NUMBER = "+12056240673";

	public NoteController(userDAO dao, CustomerDao cdao, DepositTypeDAO ddao) {
		this.dao = dao;
		this.cdao = cdao;
		this.ddao = ddao;
	}

	@Autowired
	ServletContext servletContext;

	@Autowired
	userDAO dao;

	@Autowired
	CustomerDao cdao;

	@Autowired
	DepositTypeDAO ddao;

	@GetMapping("/")
	public String getAllNotes(ModelMap modelMap) {
		try {
			modelMap.addAttribute("institutionslist", dao.getInstitutions(Status.Active.getValue()));
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	// @Scheduled(fixedRate = 5000)
	@Scheduled(cron = "0 0 7 * * *")
	public void ScheduledFixedRate() {

		System.out.println("I will execute after eveyy day 6AM");
		sendScheduledMsg();

	}

	@GetMapping("/success")
	public String getSuccess(ModelMap modelMap) {
		try {
			//System.out.println("In Success page..");
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/fail")
	public String getfail(ModelMap modelMap) {
		try {
			//System.out.println("In failure page..");
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/forgotpasswordscreen")
	public String getforgotpasswordscreen(ModelMap modelMap) {
		try {
			// System.out.println("forgotpassword");
			modelMap.addAttribute("institutionslist", dao.getInstitutions(Status.Active.getValue()));
			return "forgotpassword";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/forgotpassword")
//	public String forgotpassword(@ModelAttribute("login") Login login,HttpSession session) {
//		  ModelAndView mav = new ModelAndView("forgotpassword");
//		    CommonResponse obj = dao.validateUser(login);
//		    User user=dao.getUserforforgotpassword(login);
//		    
//			if(login.getOtp() != null)
//			{
//				Otp ootp=ddao.getOtpStatus(user.getUserId(), Status.Active.getValue(), Long.valueOf(login.getInstitutionId()));
//				if(ootp !=null && ootp.getValidUpto().compareTo(LocalDateTime.now())>0)
//				{
//					mav.addObject("forgotpwdauthsuccess","forgotpwdauthsuccess");
//				}
//				//mav=getOtpmvc(mav,user, login);
//				return mav;
//			}
//			
//		
//		    
//			if(login != null && login.getUsername() == null || login.getUsername().trim().equalsIgnoreCase(""))
//			{
//				 mav.addObject("message", "Please enter Username.");
//				 mav.addObject("institutionslist",dao.getInstitutions(Status.Active.getValue()));
//				 return mav;
//			}
//			
//			if(login != null && login.getPhone() == null || login.getPhone().trim().equalsIgnoreCase(""))
//			{
//				 mav.addObject("message", "Please enter MobileNo.");
//				 mav.addObject("institutionslist",dao.getInstitutions(Status.Active.getValue()));
//				 return mav;
//			}
//
//				if( login.getFavouritGame() == null || login.getFavouritGame().trim().equalsIgnoreCase("")
//						|| login.getMotherMaidenName() == null || login.getMotherMaidenName().trim().equalsIgnoreCase("")
//						|| login.getPlaceOfBirth() == null || login.getPlaceOfBirth().trim().equalsIgnoreCase("")
//								|| login.getFirstMobileNo() == null || login.getFirstMobileNo().trim().equalsIgnoreCase("")) {
//				 mav.addObject("message", "Please answer all security questions.");
//				 mav.addObject("institutionslist",dao.getInstitutions(Status.Active.getValue()));
//				    return mav;
//				}
//				
//				
//				if(user == null )
//				{
//					 mav.addObject("message", "Please enter Username.");
//					 mav.addObject("institutionslist",dao.getInstitutions(Status.Active.getValue()));
//					 return mav;
//				}
//				else if(user != null && !user.getPhone().equalsIgnoreCase(login.getPhone()))
//				{
//					 mav.addObject("message", "Wrong mobileNo.");
//					 mav.addObject("institutionslist",dao.getInstitutions(Status.Active.getValue()));
//					 return mav;
//				}
//				else {
//					getOtpmvc(mav,user, login,session);
//					session.setAttribute("firstMobileno", user.getFirstMobileNo());
//					session.setAttribute("maidenname", user.getMotherMaidenName());
//					session.setAttribute("game", user.getFavouritGame());
//					session.setAttribute("birthplace", user.getPlaceOfBirth());
//					session.setAttribute("mobileNo", user.getPhone());
//					
//					return "redirect:/logout";
//				}
//					
//				
//			
//	}

	@GetMapping("/successTransactions")
	public String successTransactions(ModelMap modelMap, HttpSession session) {
		try {
			long custId = (long) session.getAttribute("customerId");
			// System.out.println("In Tran Success page.."+custId);
			CommonResponse list = ddao.searchOccurance(custId, (Long) session.getAttribute("institutionId"));
			CommonResponse loans = ddao.getLoanDetails(custId, (Long) session.getAttribute("institutionId"));
			CommonResponse sploans = ddao.getSpecialLoanDetails(custId, (Long) session.getAttribute("institutionId"));
			list.getBusinessMessage().addAll(loans.getBusinessMessage());
			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			modelMap.addAttribute("DepositDtoList", list.getObj());
			modelMap.addAttribute("LoanDtoList", loans.getObj());
			modelMap.addAttribute("SpecialLoanDtoList", sploans.getObj());
			session.setAttribute("businessMsgList", list);
			session.setAttribute("occuranceList", ddao.getOccuranceList((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "deposits");
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/login")
	public String loginProcess(@ModelAttribute("login") Login login, HttpSession session) {

		try {

			ModelAndView mav = new ModelAndView("index");
			CommonResponse obj = dao.validateUser(login);
			try {
				Recharge v = cdao.getValidity(Long.valueOf(login.getInstitutionId()), Status.Active.getValue());
				if(v != null) {
				session.setAttribute("validityuptoo", v.getToDate());
				session.setAttribute("version", v.getAmount() > 0 ? "PaidVersion" : "TrialVersion");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			session.setAttribute("institutionslist", dao.getInstitutions(Status.Active.getValue()));
			if (login != null && login.getUsername() == null || login.getUsername().trim().equalsIgnoreCase("")) {
				session.setAttribute("message", "Please enter Username.");
				return "redirect:/fail";
			}
			if (login != null && login.getPassword() == null || login.getPassword().trim().equalsIgnoreCase("")) {
				session.setAttribute("message", "Please enter password.");
				return "redirect:/fail";
			}

			if (login != null && login.getAuthType().equalsIgnoreCase("questions")) {
				if (login.getFavouritGame() == null || login.getFavouritGame().trim().equalsIgnoreCase("")
						|| login.getMotherMaidenName() == null
						|| login.getMotherMaidenName().trim().equalsIgnoreCase("") || login.getPlaceOfBirth() == null
						|| login.getPlaceOfBirth().trim().equalsIgnoreCase("") || login.getFirstMobileNo() == null
						|| login.getFirstMobileNo().trim().equalsIgnoreCase("")) {
					mav.addObject("message", "Please answer all security questions.");
					return "redirect:/fail";
				}
			}

			String validity = (String) obj.getObj();
			if (validity != null) {
				session.setAttribute("message",
						"Validity has been Expired, Please recharge for immediate services. Recharge can be made using phonePay/GooglePay/Paytem (8801140530).");
				return "redirect:/fail";
			}

			User user = (User) obj.getSuccessobj();
			// System.out.println("user::"+user);
			if (user != null && user.getLoggedStatus() == Status.Active.getValue()
					&& !user.getRole().getRoleName().equalsIgnoreCase("SuperAdmin")) {
				session.setAttribute("message", "User already loggedIn/improper logged out at lasttime.");
				return "redirect:/fail";
			}
			if (user != null && login.getAuthType().equalsIgnoreCase("otp")) {
				Otp ootp = ddao.getOtpStatus(user.getUserId(), Status.Active.getValue(),
						Long.valueOf(login.getInstitutionId()));
				if (ootp == null) {
					getOtpmvc(user, login, session);
					//session.setAttribute("message", "OTP sent to your registered mobile no..");
					return "redirect:/fail";
				}

				else if (ootp.getValidUpto().compareTo(LocalDateTime.now()) > 0) {
					ootp.setStatus(Status.InActive.getValue());
					ddao.updateObj(ootp);
					if (!ootp.getOtp().equalsIgnoreCase(login.getOtp())) {
						session.setAttribute("message", "OTP mis matched.");
						return "redirect:/fail";
					}
				} else {
					session.setAttribute("message", "OTP has Expired...Please try again.");
					ootp.setStatus(Status.InActive.getValue());
					ddao.updateObj(ootp);
					return "redirect:/fail";
				}

			} else if (user != null && login.getAuthType().equalsIgnoreCase("Resend")) {
				Otp ootp = ddao.getOtpStatus(user.getUserId(), Status.Active.getValue(),
						Long.valueOf(login.getInstitutionId()));
				if (ootp != null) {
					ootp.setStatus(Status.InActive.getValue());
					ddao.updateObj(ootp);
				}
				// System.out.println("Entered in Otp resend");
				getOtpmvc(user, login, session);

				return "redirect:/fail";

			}
			if (null != user) {
				List<InventoryMenu> im = null;
				try {
					im = getInventoryMenu(String.valueOf(user.getRole().getRoleUid()));
					session.setAttribute("userId", user.getUserId());
					session.setAttribute("user", user);
					session.setAttribute("menus", im);
					session.setAttribute("allMenus",
							dao.getAllMenus(Status.Active.getValue()).stream()
									.filter(e -> !e.getMenuName().equalsIgnoreCase("Institution"))
									.sorted(Comparator.comparingLong(MenuMaster::getId)).collect(Collectors.toList()));
					StringBuilder b = new StringBuilder();
					session.setAttribute("menusperuser", im.stream().filter(e -> e.getUrl() != null)
							.map(e -> e.getUrl()).collect(Collectors.joining(",")));
					// System.out.println("streammmm:"+im.stream().filter(e->e.getUrl() !=
					// null).map(e->e.getUrl()).collect(Collectors.joining(",")));
					session.setAttribute("rolesList",
							dao.getRoles(user.getRole().getRoleName(), user.getInstitution().getInstitution_uid()));
					session.setAttribute("roleList", cdao.roleList(Status.Active.getValue(),
							user.getRole().getRoleName(), user.getInstitution().getInstitution_uid()));
					session.setAttribute("roleMenuLinkList",
							cdao.getRoleMenuLinkByInstitution("Y", user.getInstitution().getInstitution_uid()));
					session.setAttribute("institutions", dao.getInstitutionList(user.getRole().getRoleName(),
							user.getInstitution().getInstitution_uid()));
					session.setAttribute("institutionuser", user.getInstitution());
					session.setAttribute("institutionId", user.getInstitution().getInstitution_uid());
					session.setAttribute("interestTypeList",
							ddao.getInterestTypeList(user.getInstitution().getInstitution_uid()));
					session.setAttribute("transactionTypes", TransactionTypeE.integers);
					session.setAttribute("statusTypes", Status.integers);
					session.setAttribute("InterestTypeEnums", InterestTypeE.integers);
					session.setAttribute("CalculationTypeEnums", CalculationTypeE.integers);
					session.setAttribute("occuranceList",
							ddao.getOccuranceList(user.getInstitution().getInstitution_uid()));
					session.setAttribute("statesList", States.integers);
					session.setAttribute("districtsList", Districts.integers);
					session.setAttribute("mandalsList", Mandals.integers);
					session.setAttribute("villagesList", Villages.integers);
					Ledger l = ddao.getActiveLedger(LedgerStatusE.Open.getValue(),
							user.getInstitution().getInstitution_uid());
					if (l != null) {
						session.setAttribute("OtherIncomeList", ddao.getOtherIncomeDetails(l.getLedger_uid(),
								user.getInstitution().getInstitution_uid()));
						session.setAttribute("ExpenseList",
								ddao.getExpenseDetails(l.getLedger_uid(), user.getInstitution().getInstitution_uid()));
					}

					Map<Double, Double> contri = new HashMap<>();
					try {
						InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
								Status.Active.getValue(), user.getInstitution().getInstitution_uid());
						double totalsahre = cdao.getTotalTransactionSummary(user.getInstitution().getInstitution_uid());

						contri.put(0.00, 0.00);
						if (t != null)
							contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
						if (totalsahre > 0)
							contri.put(totalsahre, totalsahre);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}

					session.setAttribute("InitialContribution", contri);
					Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(),
							TransactionTypeE.Deposits.getValue(), user.getInstitution().getInstitution_uid());
					session.setAttribute("activeOccurrance", o);
					if (o != null)
						session.setAttribute("monthlyLoansSummary", ddao.getMonthlyLoanSummaryList(o.getOccurranceUid(),
								user.getInstitution().getInstitution_uid()));

					if (o != null)
						session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(),
								o.getOccurranceUid(), (Long) session.getAttribute("institutionId")));

					session.setAttribute("ledgerSummaryList",
							ddao.getLedgerSummary((user.getInstitution().getInstitution_uid())));
					session.setAttribute("occuranceIds", ddao.getOccuranceIds(TransactionTypeE.Loans.getValue(),
							user.getInstitution().getInstitution_uid()));
					session.setAttribute("occuranceDepoitsIds", ddao.getOccuranceIds(
							TransactionTypeE.Deposits.getValue(), user.getInstitution().getInstitution_uid()));
					session.setAttribute("customerList",
							cdao.customerList(Status.Active.getValue(), user.getInstitution().getInstitution_uid()));

					if (user.getRole().getRoleName().equalsIgnoreCase("SuperAdmin")) {
						// System.out.println("userList::"+cdao.userList(Status.Active.getValue()));
						// System.out.println("InsttutionList::"+cdao.institutionList(Status.Active.getValue()));
						session.setAttribute("institutionList", cdao.institutionList(Status.Active.getValue()));
						session.setAttribute("userList", cdao.userList(Status.Active.getValue()));
						session.setAttribute("validityList", cdao.getValidityList(Status.Active.getValue()));
					} else {
						session.setAttribute("userList",
								cdao.userList(Status.Active.getValue()).stream().filter(e -> e.getInstitution()
										.getInstitution_uid() == user.getInstitution().getInstitution_uid())
										.collect(Collectors.toList()));
					}
					/*
					 * session.setAttribute("lastLedger", ddao.getLastLedger(0,
					 * user.getInstitution().getInstitution_uid()));
					 */

					if (o != null) {
						String date = ddao.countDownTime(o.getOccurranceDate(),
								(o != null && o.getActivehours() != null) ? Integer.parseInt(o.getActivehours()) : 0);
						if (date != "" && date != null)
							session.setAttribute("countdowndate", date);
					}

					if (!user.getRole().getRoleName().equalsIgnoreCase("SuperAdmin")) {
						user.setLoggedStatus(Status.Active.getValue());
						ddao.updateObj(user);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				session.setAttribute("menus", im);
			} else {
				session.setAttribute("message",
						"Username or Password is wrong!! or you might not have access for this institution.");
				return "redirect:/fail";
			}
			return "redirect:/success";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	public String getOtpmvc(User user, Login login, HttpSession session) {
		//System.out.println("user.getInstitution().getMessagesRequired()::sat113331hs:"+user.getInstitution());
		
		if(user.getInstitution().getMessagesRequired() == null || (user != null && user.getInstitution() != null && user.getInstitution().getMessagesRequired() != null && !user.getInstitution().getMessagesRequired().equalsIgnoreCase("YES")))
		{
			session.setAttribute("message", "Mesage alerts are not enabled.Please try to login with security questions.");
			return null;
		}
		
		char[] otp = dao.sendOTP(6);
		Otp o = new Otp();
		o.setInstitution(ddao.getInstitution(Long.valueOf(login.getInstitutionId())));
		o.setOtp(String.valueOf(otp));
		o.setStatus(Status.Active.getValue());
		o.setUser(user);
		o.setValidUpto(LocalDateTime.now().plusMinutes(2));
		long l = ddao.saveObj(o);
		if (l > 0) {
			try {
				// System.out.println("user.getPhone()::"+user.getPhone());
				if (user.getPhone() != null)
				{
					sendSMS(user.getPhone(), "Otp for login:" + String.valueOf(otp));
				    session.setAttribute("message", "OTP sent to your registered mobile no..");
				}
					
			} catch (Exception e2) {
				// TODO: handle exception
				// System.out.println("Entered Exception:::::::::::::");
				e2.printStackTrace();
			}
			
			session.setAttribute("institutionslist", dao.getInstitutions(Status.Active.getValue()));
			session.setAttribute("otp", String.valueOf(otp));
			session.setAttribute("usernamel", user.getUsername());
			session.setAttribute("passwordl", user.getPassword());
			session.setAttribute("instl", +user.getInstitution().getInstitution_uid());
			//System.out.println("Otp sent:" + String.valueOf(otp));
			return String.valueOf(otp);

		}
		return String.valueOf(otp);
	}

	@PostMapping("/userReg")
	public final String userReg(@ModelAttribute("Note") User user, ModelMap modelmap) {
		try {
			dao.saveUser(user);
			modelmap.addAttribute("noteslist", dao.getAllNotes());
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addFileCustomers")
	public final String addFileCustomers(@ModelAttribute("CustomerDto") CustomerDto cust, ModelMap modelmap,
			BindingResult result, HttpSession session) {

		String fileName = "";
		try {
			// System.out.println("cust.getCustomerFileName():::"+cust.getCustomerFile());
			modelmap.addAttribute(new CustomerDto());
			MultipartFile m = cust.getCustomerFile();
			if (m != null && !m.isEmpty()) {
				fileName = servletContext.getRealPath("/") + "resources\\ExcelFiles\\" + m.getOriginalFilename();
				try {
					// System.out.println("fileName::"+fileName);
					m.transferTo(new File(fileName));
					// System.out.println("New Excel file-----------:"+fileName);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else {
				List<BusinessMessage> list = new ArrayList<>();
				list.add(new BusinessMessage("Please Upload File."));
				CommonResponse r = new CommonResponse();
				r.setBusinessMessage(list);
				session.setAttribute("businessMsgList", r);
				return "redirect:/success";
			}

			CommonResponse list = cdao.saveExcelDto(fileName, (Long) session.getAttribute("institutionId"),
					(long) session.getAttribute("userId"));
			session.setAttribute("businessMsgList", list);
			if (list.getSuccessobj() != null) {
				session.setAttribute("lastSavedCustomerpop", "printCUstomer");
				session.setAttribute("lastSavedCustomer", list.getSuccessobj());
			}
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("customerList", list.getObj());
			session.setAttribute("currentpage", "customer");

			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), (long) session.getAttribute("institutionId"));
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);

			if (list.getObj() != null) {
				List<Customer> clist = (List<Customer>) list.getObj();
				for (Customer c : clist) {
					String msg = "Mr/Ms. " + c.getCustomerName() + " s/o " + c.getFatherName()
							+ " have been added successfully in " + c.getInstitution().getInstitutionName()
							+ " with generated customerId:" + c.getCustomerId();
					try {
						if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
							sendSMS(c.getPhoneNo(), msg);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/closeCustomer")
	public final String closeCustomer(@ModelAttribute("CustomerDto") CustomerDto cust, ModelMap modelmap,
			BindingResult result, HttpSession session) {
		try {
			if(cust == null || cust.getCustomerId() == null || cust.getCustomerId().equalsIgnoreCase(""))
			{
				return "redirect:/success";
			}
			long instId = (long) session.getAttribute("institutionId");
			Customer customer = cdao.getCustomerUid(Long.valueOf(cust.getCustomerId()), instId);
			CommonResponse r = cdao.closeCustomer(customer.getCustomer_uid(), instId);
			session.setAttribute("businessMsgList", r);
			session.setAttribute("closeCustomerId", customer.getCustomerUid());
			session.setAttribute("closeCustomerIdfordis", cust.getCustomerId());
			if (r.getSuccessobj() != null) {
				// session.setAttribute("lastSavedCustomerpop", "printCUstomer");
			}
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					instId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), instId));

			session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary(instId));
			if (r.getObj() != null)
				session.setAttribute("personalHistory", r.getObj());
			if (r.getSuccessobj() != null)
				session.setAttribute("finalshare", r.getSuccessobj());
			session.setAttribute("currentpage", "CloseCustomer");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/withdrawCustomer")
	public final String withdrawCustomer(@ModelAttribute("CustomerDto") CustomerDto cust, ModelMap modelmap,
			BindingResult result, HttpSession session) {
		try {
			CommonResponse r = cdao.withdrawCustomer(Long.valueOf(cust.getCustomerUid()),
					(long) session.getAttribute("institutionId"));
			session.setAttribute("businessMsgList", r);
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("withdrawCustomer", r.getObj());
			session.setAttribute("customerList",
					cdao.customerList(Status.Active.getValue(), (long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "customer");
			if (r.getObj() != null && r.getServiceStatus().equals(ServiceStatus.SUCCESS)) {
				Customer c = (Customer) r.getObj();
				String msg = c.getCustomerName() + " s/o " + c.getFatherName()
						+ " have been Withdrawn successfully from " + c.getInstitution().getInstitutionName()
						+ " having customerId:" + c.getCustomer_uid() + ". Share of the candidate: Rs." + c.getShare();
				try {
					if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null  && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						sendSMS(c.getPhoneNo(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				session.setAttribute("lastSavedCustomerpop", "withdrawCustomer");
			}
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addCustomer")
	public final String addCustomer(@ModelAttribute("CustomerDto") CustomerDto cust, ModelMap modelmap,
			BindingResult result, HttpSession session) {
		try {
			// System.out.println("cust.getSignature():::"+cust.getSignature());
			modelmap.addAttribute(new CustomerDto());
			MultipartFile m = cust.getPhoto();
			MultipartFile signature = cust.getSignature();
			if (m != null && !m.isEmpty()) {
				String fileName = servletContext.getRealPath("/") + "resources\\images\\" + m.getOriginalFilename();
				try {
					m.transferTo(new File(fileName));
					// System.out.println("New file-----------:"+fileName);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			// System.out.println("signature::"+signature);

			if (signature != null && !signature.isEmpty()) {
				// System.out.println("iiiii");
				String fileSignature = servletContext.getRealPath("/") + "resources\\signature\\"
						+ signature.getOriginalFilename();
				try {
					signature.transferTo(new File(fileSignature));
					// System.out.println("New file-----sig------:"+fileSignature);
				} catch (Exception e) {
					// TODO: handle exception
					// System.out.println("entred");
					e.printStackTrace();
				}
			}

			CommonResponse list = cdao.saveCustomer(cust, (long) session.getAttribute("institutionId"),
					(long) session.getAttribute("userId"));
			session.setAttribute("businessMsgList", list);
			if (list.getSuccessobj() != null) {
				Customer c = (Customer) list.getSuccessobj();
				String msg = "Mr/Ms. " + c.getCustomerName() + " s/o " + c.getFatherName()
						+ " have been added successfully in " + c.getInstitution().getInstitutionName()
						+ " with generated customerId:" + c.getCustomerId();
				try {
					if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						sendSMS(c.getPhoneNo(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				long rid = ddao.saveReceipt(c, list.getFrom(), (long) session.getAttribute("userId"),
						(Long) session.getAttribute("institutionId"));
				session.setAttribute("rid", rid);

				session.setAttribute("lastSavedCustomerpop", "printCUstomer");
				session.setAttribute("lastSavedCustomer", list.getSuccessobj());
			}
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("customerList", list.getObj());
			session.setAttribute("currentpage", "customer");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addUser")
	public final String addUser(@ModelAttribute("UserDto") UserDto cust, ModelMap modelmap, HttpSession session) {
		try {
			CommonResponse list = cdao.saveUser(cust, Long.parseLong(cust.getInstitutionId().toString()),
					(long) session.getAttribute("userId"));
			session.setAttribute("userList", list.getObj());
			session.setAttribute("businessMsgList", list);
			session.setAttribute("currentpage", "user");

			if (list.getServiceStatus().equals(ServiceStatus.SUCCESS) && list.getSuccessobj() != null) {
				User c = (User) list.getSuccessobj();
				String msg = "Mr/Ms. " + c.getUsername() + ",you have been added successfully as "
						+ c.getRole().getRoleName() + " in " + c.getInstitution().getInstitutionName()
						+ " with generated " + "userId:" + c.getUserId() + ". Your Login Credentials Username:"
						+ c.getUsername() + " password:" + c.getPassword() + " sangamName:"
						+ c.getInstitution().getInstitutionName() + " " + "SangamId:"
						+ c.getInstitution().getInstitution_uid() + " Your Security Questions:"
						+ QuestionsEnum.what_is_your_birthPlace + ":" + c.getPlaceOfBirth() + " "
						+ QuestionsEnum.what_is_your_favorite_game + ":" + c.getFavouritGame() + " "
						+ QuestionsEnum.What_is_your_firt_mobileNo + ":" + c.getFirstMobileNo() + " "
						+ QuestionsEnum.What_is_your_mother_maiden_name + ":" + c.getMotherMaidenName();
				try {
					if (c.getPhone() != null && !"".equalsIgnoreCase(c.getPhone()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						sendSMS(c.getPhone(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addInstitution")
	public final String addInstitution(@ModelAttribute("InstitutionDto") InstitutionDto institutionDto,
			ModelMap modelmap, HttpSession session) {
		try {
			CommonResponse list = cdao.saveInstitution(institutionDto, (long) session.getAttribute("userId"));
			session.setAttribute("institutions",
					dao.getInstitutionList("SuperAdmin", (long) session.getAttribute("institutionId")));
			session.setAttribute("institutionList", list.getObj());
			session.setAttribute("businessMsgList", list);
			session.setAttribute("currentpage", "institution");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addRole")
	public final String addRole(@ModelAttribute("RoleDto") RoleDto role, ModelMap modelmap, HttpSession session) {
		try {
			User u = (User) session.getAttribute("user");
			CommonResponse list = cdao.saveRole(role, (long) session.getAttribute("userId"),
					(long) session.getAttribute("institutionId"), u);
			session.setAttribute("roleList", list.getObj());
			session.setAttribute("rolesList",
					dao.getRoles(u.getRole().getRoleName(), (long) session.getAttribute("institutionId")));
			session.setAttribute("roleMenuLinkList",
					cdao.getRoleMenuLinkByInstitution("Y", (long) session.getAttribute("institutionId")));
			session.setAttribute("businessMsgList", list);
			session.setAttribute("currentpage", "Role");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addDepositType")
	public final String addDepositType(@ModelAttribute("DepositType") DepositType cust, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("idealAmount::"+cust.getAmount());
			List<DepositType> list = ddao.saveDepositType(cust, (long) session.getAttribute("userId"),
					(long) session.getAttribute("institutionId"));
			modelmap.addAttribute("depositTypeList", list);
			modelmap.addAttribute("currentpage", "DepositType");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addInterestType")
	public final String addInterestType(@ModelAttribute("InterestType") InterestType interestType, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("interestType::"+interestType.getIdealDepositAmount()+" ,
			// "+interestType.getInterestRate());
			CommonResponse list = ddao.saveInterestType(interestType, (long) session.getAttribute("institutionId"));
			session.setAttribute("interestTypeList", list.getObj());
			session.setAttribute("businessMsgList", list);
			session.setAttribute("currentpage", "InterestType");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addInterests")
	public final String addInterests(@ModelAttribute("InterestDto") InterestDto interests, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("interests::"+interests);
			List<Interests> list = ddao.saveInterests(interests, (long) session.getAttribute("institutionId"));
			modelmap.addAttribute("interestsList", list);
			modelmap.addAttribute("currentpage", "InterestRate");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addOccurance")
	public final String addOccurance(@ModelAttribute("OccurranceDto") OccurranceDto cust, ModelMap modelmap,
			HttpSession session) {
		try {

			CommonResponse list = ddao.saveOccurance(cust, (long) session.getAttribute("userId"),
					(long) session.getAttribute("institutionId"));
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			session.setAttribute("activeOccurrance", o);
			double initialSahre = 0;
			if (o != null) {
				String date = ddao.countDownTime(o.getOccurranceDate(),
						(o != null && o.getActivehours() != null) ? Integer.parseInt(o.getActivehours()) : 0);
				if (date != "" && date != null)
					session.setAttribute("countdowndate", date);
				initialSahre = o.getInterestType().getFixedInterestAmount();
			}

			// System.out.println("totalshare"+totalsahre);
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), (long) session.getAttribute("institutionId"));
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);
			session.setAttribute("occuranceList", list.getObj());
			session.setAttribute("businessMsgList", list);
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("occuranceDepoitsIds", ddao.getOccuranceIds(TransactionTypeE.Deposits.getValue(),
					(long) session.getAttribute("institutionId")));
			session.setAttribute("occuranceIds", ddao.getOccuranceIds(TransactionTypeE.Loans.getValue(),
					(long) session.getAttribute("institutionId")));
			session.setAttribute("activeOccurrance", ddao.getActiveOccurrance(Status.Active.getValue(),
					TransactionTypeE.Deposits.getValue(), (long) session.getAttribute("institutionId")));
			session.setAttribute("activeOccurrance", o);
			session.setAttribute("currentpage", "Occurance");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/rollback")
	public final String rollback(@ModelAttribute("CustomerDto") CustomerDto cust, ModelMap modelmap,
			HttpSession session) {
		CommonResponse r = new CommonResponse();
		List<BusinessMessage> list1 = new ArrayList<>();
		try {
			long institutionId = (Long) session.getAttribute("institutionId");
			Customer customer = cdao.getCustomerUid(Long.valueOf(cust.getCustomerId()), institutionId);
			if (customer == null) {
				list1.add(new BusinessMessage("Invalid Customer"));
				r.setServiceStatus(ServiceStatus.ERROR);
				r.setBusinessMessage(list1);
				;
				session.setAttribute("businessMsgList", r);
				return "redirect:/success";
			}

			if (cust.getType().equals("RollbackPayments")) {
				r = ddao.rollback(customer.getCustomer_uid(), institutionId);
			} else if (cust.getType().equals("RollbackLoans")) {
				r = ddao.rollbackLoans(customer.getCustomer_uid(), institutionId);
			} else if (cust.getType().equals("RollbackSpLoans")) {
				r = ddao.rollbackSpLoans(customer.getCustomer_uid(), institutionId);
			}
			//System.out.println("R::" + r);
			if (r != null)
				//System.out.println("buss:" + r.getBusinessMessage().size());

			session.setAttribute("businessMsgList", r);
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					institutionId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), institutionId));

			session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary(institutionId));
			session.setAttribute("currentpage", "Rollback");
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), institutionId);
				double totalsahre = cdao.getTotalTransactionSummary(institutionId);
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/rollbackLoans")
	public final String rollbackLoans(@ModelAttribute("Customer") Customer cust, ModelMap modelmap,
			HttpSession session) {
		CommonResponse r = new CommonResponse();
		try {
			long institutionId = (Long) session.getAttribute("institutionId");
			r = ddao.rollback(cust.getCustomer_uid(), institutionId);
			session.setAttribute("businessMsgList", r);
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/rollbackSpLoans")
	public final String rollbackSpLoans(@ModelAttribute("Customer") Customer cust, ModelMap modelmap,
			HttpSession session) {
		CommonResponse r = new CommonResponse();
		try {
			long institutionId = (Long) session.getAttribute("institutionId");
			r = ddao.rollback(cust.getCustomer_uid(), institutionId);
			session.setAttribute("businessMsgList", r);
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/adddeposits")
	public final String adddeposits(@ModelAttribute("DepositDtoList") DepositDtoList depositDtoList, ModelMap modelmap,
			HttpSession session) {
		try {
			List<DepositDto> deposits = depositDtoList.getDepositdto();
			List<DepositsSuccess> list = null;
			CommonResponse unlist = null;
			long instId = (Long) session.getAttribute("institutionId");
			// System.out.println("size():"+deposits.size());
			if (deposits.size() > 0) {
				list = ddao.saveDeposits(deposits, (long) session.getAttribute("userId"), instId);
				modelmap.addAttribute("Customer", cdao.getCustomer((long) session.getAttribute("customerId"), instId));

				unlist = ddao.searchOccurance(deposits.get(0).getCustomerId(), instId);
				modelmap.addAttribute("DepositDtoList", unlist.getObj());
			}
			if (list != null && list.size() > 0) {
				session.setAttribute("successpopup", "successdeposits");
				session.setAttribute("depositsSuccess", list);
                 c = null;
				 c = list.stream().findFirst().get().getCustomer();
				long rid = ddao.saveReceipt(c,
						list.stream().map(e -> String.valueOf(e.getTransactionId())).collect(Collectors.joining(",")),
						(long) session.getAttribute("userId"), instId);
				session.setAttribute("rid", rid);
				double amount = list.stream().mapToDouble(e -> e.getAmount()).sum();
				double fine = list.stream().mapToDouble(e -> e.getFine()).sum();
				 msg="";
				 tt = null;
				 Occurrance oooo = ddao.getActiveOccurrance(Status.Active.getValue(),
							TransactionTypeE.Deposits.getValue(), instId);
				 tt = ddao.searchRid(c.getCustomer_uid(), oooo.getOccurranceUid(),
							(long) session.getAttribute("institutionId"));
				 msg = msg+"Hello " + c.getCustomerName() + " id: " + c.getCustomerId()
						+ ", you have successfully paid Deposit amount " + amount + ", fine " + fine+"."
						//+ " towards months of "
						//+ list.stream().map(e -> e.getCoveredMonth()).collect(Collectors.joining(",")) + "";
				// + " followed by"
				// + " reference
				// id(s):"+list.stream().map(e->String.valueOf(e.getTransactionId())).collect(Collectors.joining(","))
						;
				//System.out.println("Deposits Msg:" + msg);
//				try {
//					if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
//						sendSMS(c.getPhoneNo(), msg);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}

			}

			session.setAttribute("businessMsgList", unlist);
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					instId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), instId));

			session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary(instId));
			session.setAttribute("currentpage", "deposits");
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), instId);
				double totalsahre = cdao.getTotalTransactionSummary(instId);
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);
			return "redirect:/searchCustomer";

//			
//			 try {
//			   		long custId=(long)session.getAttribute("customerId");
//					CommonResponse list1=ddao.searchOccurance(custId,(Long)session.getAttribute("institutionId"));
//					CommonResponse loans=ddao.getLoanDetails(custId,(Long)session.getAttribute("institutionId"));
//					CommonResponse sploans=ddao.getSpecialLoanDetails(custId,(Long)session.getAttribute("institutionId"));
//					list1.getBusinessMessage().addAll(loans.getBusinessMessage());
//					session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary((Long)session.getAttribute("institutionId")));
//					session.setAttribute("DepositDtoList",list1.getObj());
//					session.setAttribute("depositsSuccess",list1.getSuccessobj());
//					session.setAttribute("LoanDtoList",loans.getObj());
//					session.setAttribute("LoansSuccess", loans.getSuccessobj());
//					session.setAttribute("SpecialLoanDtoList", sploans.getObj());
//					session.setAttribute("spLoansSuccess", sploans.getSuccessobj());
//					session.setAttribute("businessMsgList",list1);
//					session.setAttribute("occuranceList", ddao.getOccuranceList((Long)session.getAttribute("institutionId")));
//					session.setAttribute("currentpage", "deposits");
//					return "redirect:/success";	
//					} catch (Exception e) {
//						return "redirect:/";
//					}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addloans")
	public final String addloans(@ModelAttribute("LoanDtoList") LoanDtoList loanDto, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("entered"+loanDto);

			// System.out.println("size():"+loanDto);
			CommonResponse list = ddao.saveloans(loanDto, (long) session.getAttribute("userId"),
					(Long) session.getAttribute("institutionId"));
			modelmap.addAttribute("Customer", cdao.getCustomer((long) session.getAttribute("customerId"),
					(Long) session.getAttribute("institutionId")));
			// System.out.println("list.getFrom()::::::::::"+list.getFrom());
			if (list.getFrom().equalsIgnoreCase("loansPaid")) {
				session.setAttribute("successpopup", "successdeposits");
				List<DepositsSuccess> listdd = (List<DepositsSuccess>) list.getSuccessobj();
				Optional<DepositsSuccess> cc = listdd.stream().findFirst();
				Customer c = null;
				if (cc.isPresent()) {
					c = cc.get().getCustomer();
				}
				Receipt tt = null;
				Occurrance oooo = ddao.getActiveOccurrance(Status.Active.getValue(),
						TransactionTypeE.Deposits.getValue(), (long) session.getAttribute("institutionId"));
				// System.out.println("oooo:"+oooo+" c:"+c);
				if (oooo != null && c != null) {
					tt = ddao.searchRid(c.getCustomer_uid(), oooo.getOccurranceUid(),
							(long) session.getAttribute("institutionId"));
				}
				// System.out.println("tt::"+tt);
				if (tt != null) {
					// System.out.println("tt.getReceiptsUid()::"+tt.getReceiptsUid()+"
					// "+listdd.stream().filter(e->e.getInstallmentNo()>0).map(e->String.valueOf(e.getTransactionId())).collect(Collectors.joining(",")));
					ddao.updateReceipt(tt.getReceiptsUid(),
							listdd.stream().filter(e -> e.getInstallmentNo() > 0)
									.map(e -> String.valueOf(e.getTransactionId())).collect(Collectors.joining(",")),
							"Loans", (long) session.getAttribute("institutionId"));
					// System.out.println("updated");

					double amount = listdd.stream().mapToDouble(e -> e.getAmount()).sum();
					double fine = listdd.stream().mapToDouble(e -> e.getFine()).sum();
					double interest = listdd.stream().mapToDouble(e -> e.getInterest()).sum();
					try {
						
						msg = msg+" and LT Loan paidInstallmentamount:" + amount + ", Interest: " 
							  + interest + ", fine: " + fine +" and remaining loan amount:" + listdd.stream().findFirst().get().getRemainingloanamount()+".";

//						String msg = "Hello Mr/Ms. " + c.getCustomerName() + " ID: " + c.getCustomerId()
//								+ ", You have successfully paid Loan InstallmentAmount: " + amount + " and Interst: "
//								+ interest + " fine: " + fine + " towards installents of "
//								+ listdd.stream().filter(e -> e.getInstallmentNo() > 0)
//										.map(e -> String.valueOf(e.getInstallmentNo())).collect(Collectors.joining(","))
//								+ ".Your remaining loan amount:"
//								+ listdd.stream().findFirst().get().getRemainingloanamount() 
								//+ " followed by"
								//+ " reference id(s):" + listdd.stream().map(e -> String.valueOf(e.getTransactionId()))
								//		.collect(Collectors.joining(","))
								;
						//System.out.println("Loans msg:" + msg);

//						if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
//							sendSMS(c.getPhoneNo(), msg);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				// session.setAttribute("successpopup", "successLoans");
				// session.setAttribute("LoansSuccess", list.getSuccessobj());
			} else if (list.getFrom().equalsIgnoreCase("loansInitiate")) {
				session.setAttribute("successpopup", "successLoansInitiate");
				session.setAttribute("LoansSuccessIni", list.getSuccessobj());
				try {
					List<DepositsSuccess> listdd = (List<DepositsSuccess>) list.getSuccessobj();
					DepositsSuccess d = listdd.stream().findFirst().get();
					Customer c = d.getCustomer();
					String msg = "Hello Mr/Ms. " + c.getCustomerName() + " ID: " + c.getCustomerId()
							+ ", Loan has been sanctioned for you with amount of: " + d.getAmount() + ", installments: "
							+ d.getInstallmentNo() + ", rateOfInterest: " + d.getInterest() + " and fine: "
							+ d.getFine();
					//System.out.println("Loans msg:" + msg);

					if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						sendSMS(c.getPhoneNo(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			session.setAttribute("businessMsgList", list);
			modelmap.addAttribute("LoanDtoList", list.getObj());
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "deposits");
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), (long) session.getAttribute("institutionId"));
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);

			return "redirect:/searchCustomer";

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addExpense")
	public final String addExpense(@ModelAttribute("LoanDtoList") Expenses ex, ModelMap modelmap, HttpSession session) {
		try {
			// System.out.println("entered"+ex);

			// System.out.println("size():"+ex);
			CommonResponse list = ddao.saveExpenses(ex, (long) session.getAttribute("userId"),
					(Long) session.getAttribute("institutionId"));
			if (list != null) {
				modelmap.addAttribute("successMsg", "Loan successfully saved and Message has been sent.");
			} else {
				modelmap.addAttribute("successMsg", "Error while saving the Records.");
			}
			CommonResponse loans = ddao.getLoanDetails(0, (Long) session.getAttribute("institutionId"));
			session.setAttribute("businessMsgList", list);
			session.setAttribute("ExpenseList", list.getObj());
			modelmap.addAttribute("LoanDtoList", loans.getObj());
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), (long) session.getAttribute("institutionId"));
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
				session.setAttribute("InitialContribution", contri);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			session.setAttribute("currentpage", "Expenses");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addOtherIncome")
	public final String addOtherIncome(@ModelAttribute("OtherIncome") OtherIncome ex, ModelMap modelmap,
			HttpSession session) {
		try {
			long instId = (Long) session.getAttribute("institutionId");
			CommonResponse list = ddao.saveOtherIncome(ex, (long) session.getAttribute("userId"), instId);
			if (list != null) {
				modelmap.addAttribute("successMsg", "OtherIncome details successfully saved.");
			} else {
				modelmap.addAttribute("successMsg", "Error while saving the Records.");
			}
			CommonResponse loans = ddao.getLoanDetails(0, (Long) session.getAttribute("institutionId"));
			session.setAttribute("businessMsgList", list);
			session.setAttribute("OtherIncomeList", list.getObj());
			modelmap.addAttribute("LoanDtoList", loans.getObj());

			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					instId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), instId));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), instId);
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
				session.setAttribute("InitialContribution", contri);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			session.setAttribute("currentpage", "OtherIncomes");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/updateOccurrannce")
	public final String updateOccurrannce(@ModelAttribute("OccurranceDto") OccurranceDto dto, ModelMap modelmap,
			HttpSession session) {
		try {
			CommonResponse list = ddao.updateOccurance(dto, (Long) session.getAttribute("institutionId"));
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			session.setAttribute("activeOccurrance", o);
			if (o != null) {
				String date = ddao.countDownTime(o.getOccurranceDate(),
						(o != null && o.getActivehours() != null) ? Integer.parseInt(o.getActivehours()) : 0);
				if (date != "" && date != null)
					session.setAttribute("countdowndate", date);
			}

			session.setAttribute("businessMsgList", list);
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "Occurance");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/addSpecialloans")
	public final String addSpecialloans(@ModelAttribute("LoanDtoList") LoanDtoList loanDto, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("entered"+loanDto);

			// System.out.println("size():"+loanDto);
			CommonResponse list = ddao.saveSpecialloans(loanDto, (long) session.getAttribute("userId"),
					(Long) session.getAttribute("institutionId"));
			modelmap.addAttribute("Customer", cdao.getCustomer((long) session.getAttribute("customerId"),
					(Long) session.getAttribute("institutionId")));

			 System.out.println("list.getFrom():::"+list.getFrom());
			 double amount = 0;
				double fine = 0;
				double interest = 0;

				
			if (list.getFrom().equalsIgnoreCase("SploansPaid")) {
				session.setAttribute("successpopup", "successdeposits");
				List<DepositsSuccess> listdd = (List<DepositsSuccess>) list.getSuccessobj();
				 c = listdd.stream().findFirst().get().getCustomer();
				
//				Occurrance oooo = ddao.getActiveOccurrance(Status.Active.getValue(),
//						TransactionTypeE.Deposits.getValue(), (long) session.getAttribute("institutionId"));
//				if (oooo != null)
//					tt = ddao.searchRid(c.getCustomer_uid(), oooo.getOccurranceUid(),
//							(long) session.getAttribute("institutionId"));
				if (tt != null) {
					ddao.updateReceipt(tt.getReceiptsUid(), listdd.stream()
							.map(e -> String.valueOf(e.getTransactionId())).collect(Collectors.joining(",")), "SpLoans",
							(Long) session.getAttribute("institutionId"));
					
				}

				 amount = listdd.stream().mapToDouble(e -> e.getAmount()).sum();
				 fine = listdd.stream().mapToDouble(e -> e.getFine()).sum();
				 interest = listdd.stream().mapToDouble(e -> e.getInterest()).sum();
                msg=msg+" and ST loan Paidamount:" +amount+ " and Interest " +interest+ " and fine:" +fine;
                System.out.println("msg:"+msg);
				
//				String msg = "Hello Mr/Ms. " + c.getCustomerName() + " ID: " + c.getCustomerId()
//						+ ", You have successfully paid SpecialLoanAmount: " + amount + " and Interst: " + interest
//						+ " fine: " + fine
						//+ " followed by" + " reference id(s):" + listdd.stream()
						//		.map(e -> String.valueOf(e.getTransactionId())).collect(Collectors.joining(","))
						;
				//System.out.println("SpLoans Msg:" + msg);
//				try {
//					if (c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
//						sendSMS(c.getPhoneNo(), msg);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
				// session.setAttribute("successpopup", "successspLoans");
				// modelmap.addAttribute("spLoansSuccess", list.getSuccessobj());
			} else if (list.getFrom().equalsIgnoreCase("SploansInitiate")) {
				session.setAttribute("successpopup", "spsuccessLoansInitiate");
				session.setAttribute("spLoansSuccessIni", list.getSuccessobj());

				try {
					List<DepositsSuccess> listdd = (List<DepositsSuccess>) list.getSuccessobj();
					DepositsSuccess d = listdd.stream().findFirst().get();
					Customer c1 = d.getCustomer();
					 msg = "Hello Mr/Ms. " + c1.getCustomerName() + " ID: " + c1.getCustomerId()
							+ ", Special Loan has been sanctioned for you with amount of: " + d.getAmount()
							+ ", rateOfInterest: " + d.getInterest() + " and fine: " + d.getFine();
					//System.out.println("Loans msg:" + msg);

					//if (c1.getPhoneNo() != null && !"".equalsIgnoreCase(c1.getPhoneNo()) && c1.getInstitution() != null && c1.getInstitution().getMessagesRequired() != null && c1.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						//sendSMS(c1.getPhoneNo(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
			if (tt != null) {
				msg=msg+" followed by receiptId: "+tt.getReceiptsUid();
			}
				try {
					if (c != null && c.getPhoneNo() != null && !"".equalsIgnoreCase(c.getPhoneNo()) && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
						sendSMS(c.getPhoneNo(), msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			session.setAttribute("businessMsgList", list);
			modelmap.addAttribute("SpecialLoanDtoList", list.getObj());
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "deposits");
			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), (long) session.getAttribute("institutionId"));
				double totalsahre = cdao.getTotalTransactionSummary((long) session.getAttribute("institutionId"));
				contri.put(0.00, 0.00);
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			session.setAttribute("InitialContribution", contri);

			return "redirect:/searchCustomer";

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/searchCustomer")
	public final String searchCustomer(@ModelAttribute("Customer") Customer cust, ModelMap modelmap,
			HttpSession session) {
		Customer customer = null;
		try {
			long instId = (long) session.getAttribute("institutionId");
			long custId = 0;
			if (cust.getCustomerId() <= 0) {
				custId = (long) session.getAttribute("customerId");
			} else {
				customer = cdao.getCustomerUid(cust.getCustomerId(), instId);
				if (customer != null) {
					session.setAttribute("customerName", customer.getCustomerName());
					custId = customer.getCustomer_uid();
				}
			}

			session.setAttribute("customerId", custId);
			if (customer != null)
				session.setAttribute("customerIdfordis", customer.getCustomerId());

			// System.out.println("custId:::"+custId);
			CommonResponse list = ddao.searchOccurance(custId, instId);
			CommonResponse loans = ddao.getLoanDetails(custId, instId);
			CommonResponse sploans = ddao.getSpecialLoanDetails(custId, instId);
			list.getBusinessMessage().addAll(loans.getBusinessMessage());
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					instId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), instId));

			session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary(instId));
			modelmap.addAttribute("DepositDtoList", list.getObj());
			modelmap.addAttribute("depositsSuccess", list.getSuccessobj());
			modelmap.addAttribute("LoanDtoList", loans.getObj());
			modelmap.addAttribute("LoansSuccess", loans.getSuccessobj());
			modelmap.addAttribute("SpecialLoanDtoList", sploans.getObj());
			// System.out.println("sploans.getSuccessobj():::"+sploans.getSuccessobj());
			modelmap.addAttribute("spLoansSuccess", sploans.getSuccessobj());
			CommonResponse rr = (CommonResponse) session.getAttribute("businessMsgList");

			Map<Double, Double> contri = new HashMap<>();
			try {
				InterestType t = ddao.getInterestTypeByTransactionType(TransactionTypeE.Deposits.getValue(),
						Status.Active.getValue(), instId);
				double totalsahre = cdao.getTotalTransactionSummary(instId);
				contri.put(0.00, 0.00);
				if(t != null)
				contri.put(t.getFixedInterestAmount(), t.getFixedInterestAmount());
				if (totalsahre > 0)
					contri.put(totalsahre, totalsahre);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			session.setAttribute("InitialContribution", contri);

			if (rr != null) {
				list.getBusinessMessage().addAll(rr.getBusinessMessage());
				list.setServiceStatus(rr.getServiceStatus());
			}
			session.setAttribute("businessMsgList", list);

			session.setAttribute("occuranceList", ddao.getOccuranceList(instId));
			session.setAttribute("currentpage", "deposits");
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/getOccuranceIds")
	public final String getOccuranceIds(ModelMap modelmap, HttpSession session) {
		try {

			Map<Long, String> list = ddao.getOccuranceIds(TransactionTypeE.Loans.getValue(),
					(Long) session.getAttribute("institutionId"));
			session.setAttribute("occuranceIds", list);
			session.setAttribute("currentpage", "MonthlyLoansSummary");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/getLedgerSummary")
	public final String getLedgerSummary(ModelMap modelmap, HttpSession session) {
		try {

			LedgerSummaryDto list = ddao.getLedgerSummary((Long) session.getAttribute("institutionId"));
			// System.out.println("in ledger size::"+list);
			session.setAttribute("ledgerSummaryList", list);
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("occuranceList", ddao.getOccuranceList((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "LedgerSummary");
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@GetMapping("/settle")
	public final String settle(ModelMap modelmap, HttpSession session) {
		try {
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			// System.out.println("In settle :::::::::::"+o);
			if (o != null) {
				List<PdfDto> listp = ddao.personalSummary(
						ddao.getFirstOccurance((Long) session.getAttribute("institutionId")).getOccurranceUid(),
						o.getOccurranceUid(), (Long) session.getAttribute("institutionId"));
				if (listp.size() > 0)
					listp = listp.stream().filter(e -> e.getOccurranceId() == o.getOccurranceUid())
							.collect(Collectors.toList());
				// System.out.println("entered psize()::"+listp.size());
				session.setAttribute("personalSummary", listp);
				session.setAttribute("successpopupledgerSum", "personalSummarypopup");
			}
			CommonResponse list = ddao.settle((Long) session.getAttribute("institutionId"));
			if (list.getServiceStatus().equals(ServiceStatus.SUCCESS)) {
				session.setAttribute("countdowndate", "");
			}
			session.setAttribute("businessMsgList", list);
			session.setAttribute("ledgerSummaryList", list.getObj());
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("occuranceList", ddao.getOccuranceList((Long) session.getAttribute("institutionId")));
			session.setAttribute("currentpage", "LedgerSummary");
			session.setAttribute("activeOccurrance", o);
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/mothlyLoansSummary")
	public final String check(@ModelAttribute("OccurranceDto") OccurranceDto cust, ModelMap modelmap,
			HttpSession session) {
		try {
			// System.out.println("called............"+cust.getFromOccurrance());
			List<MonthlyLoansSummaryDto> list = ddao.getMonthlyLoanSummaryList(Long.valueOf(cust.getFromOccurrance()),
					Long.valueOf(cust.getToOccurrance()), (Long) session.getAttribute("institutionId"));
			// System.out.println("list:::"+list.size());
			session.setAttribute("monthlyLoansSummary", list);
			session.setAttribute("currentpage", "MonthlyLoansSummary");
			session.setAttribute("from", cust.getFromOccurrance());
			session.setAttribute("to", cust.getToOccurrance());
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					(Long) session.getAttribute("institutionId"));
			if (o != null)
				session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(),
						(Long) session.getAttribute("institutionId")));

			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/monthlyDepositsSummary")
	public final String mothlyDepositsSummary(@ModelAttribute("OccurranceDto") OccurranceDto cust, ModelMap modelmap,
			HttpSession session) {
		List<Deposits> list = null;
		try {
			long instId = (Long) session.getAttribute("institutionId");
			list = ddao.getMonthlyDepositsSummaryList(ddao.getFirstOccurance(instId).getOccurranceUid(),
					Long.valueOf(cust.getToOccurrance()), instId);

			if (list != null)
				list = list.stream()
						.filter(e -> (e.getOccurrance().getOccurranceUid() >= Long.valueOf(cust.getFromOccurrance()) - 1
								&& e.getOccurrance().getOccurranceUid() <= Long.valueOf(cust.getToOccurrance())))
						.collect(Collectors.toList());
			session.setAttribute("monthlyDepositsSummary", list);
			session.setAttribute("currentpage", "MonthlyDepositsSummary");
			session.setAttribute("ledgerSummaryList", ddao.getLedgerSummary(instId));
			Occurrance o = ddao.getActiveOccurrance(Status.Active.getValue(), TransactionTypeE.Deposits.getValue(),
					instId);
			if (o != null)
				session.setAttribute("auditSummary",
						ddao.auditSummary(o.getOccurranceUid(), o.getOccurranceUid(), instId));

			session.setAttribute("from", cust.getFromOccurrance());
			session.setAttribute("to", cust.getToOccurrance());
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/personalSummary")
	public final String personalSummary(@ModelAttribute("OccurranceDto") OccurranceDto dto, ModelMap modelmap,
			HttpSession session) {
		try {
			if(dto != null && dto.getToOccurrance() != null &&
					!dto.getToOccurrance().equalsIgnoreCase("")) {
			List<PdfDto> list = ddao.personalSummary(
					ddao.getFirstOccurance((Long) session.getAttribute("institutionId")).getOccurranceUid(),
					Long.valueOf(dto.getToOccurrance()), (Long) session.getAttribute("institutionId"));
			if (list.size() > 0)
				list = list.stream()
						.filter(e -> (e.getOccurranceId() >= Long.valueOf(dto.getFromOccurrance()) - 1
								&& e.getOccurranceId() <= Long.valueOf(dto.getToOccurrance())))
						.collect(Collectors.toList());
			// List<PdfDto> list=ddao.personalSummary(Long.valueOf(dto.getFromOccurrance()),
			// Long.valueOf(dto.getToOccurrance()),(Long)session.getAttribute("institutionId"));
			// System.out.println("list::***:"+list.size());
			session.setAttribute("personalSummary", list);
			session.setAttribute("currentpage", "PersonalSummary");
			// session.setAttribute("ledgerSummaryList",
			// ddao.getLedgerSummary((Long)session.getAttribute("institutionId")));
			// Occurrance o=ddao.getActiveOccurrance(Status.Active.getValue(),
			// TransactionTypeE.Deposits.getValue(),
			// (Long)session.getAttribute("institutionId"));
			// if(o!=null)
			// session.setAttribute("auditSummary", ddao.auditSummary(o.getOccurranceUid(),
			// o.getOccurranceUid(),(Long)session.getAttribute("institutionId")));

			session.setAttribute("from", dto.getFromOccurrance());
			session.setAttribute("to", dto.getToOccurrance());
			}
			return "redirect:/success";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	@PostMapping("/auditSummary")
	public final String auditSummary(@ModelAttribute("OccurranceDto") OccurranceDto dto, ModelMap modelmap,
			HttpSession session) {
		try {
			if(dto != null && dto.getFromOccurrance() != null && dto.getToOccurrance() != null && !dto.getFromOccurrance().equalsIgnoreCase("") &&
					!dto.getToOccurrance().equalsIgnoreCase("")) {
			List<AuditDto> list = ddao.auditSummary(Long.valueOf(dto.getFromOccurrance()),
					Long.valueOf(dto.getToOccurrance()), (Long) session.getAttribute("institutionId"));
			// System.out.println("list::**audit*:"+list.size());
			modelmap.addAttribute("auditSummary", list);
			session.setAttribute("currentpage", "AuditSummary");
			session.setAttribute("ledgerSummaryList",
					ddao.getLedgerSummary((Long) session.getAttribute("institutionId")));
			session.setAttribute("from", dto.getFromOccurrance());
			session.setAttribute("to", dto.getToOccurrance());
			}
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/logout";
		}

	}

	public List<InventoryMenu> getInventoryMenu(String roleId) throws IOException {

		List<InventoryMenu> listOfMenus = getAllMenus();

		Role role = dao.getRole(Integer.parseInt(roleId));

		List<RoleMenuLink> listOfLinks = dao.findByRoleAndAccessible(role, "Y");
		// System.out.println("listOfLinks "+listOfLinks.size());
		List<InventoryMenu> roledMenus = checkMenuPermissions(listOfMenus, role, listOfLinks);

		// System.out.println("roledMenus::"+roledMenus.size());

		return roledMenus;

	}

	public List<InventoryMenu> getAllMenus() {

		List<InventoryMenu> menus = new ArrayList<InventoryMenu>();
		try {
			Iterable<MenuMaster> listOfMenus = dao.getmenus();

			for (MenuMaster menu : listOfMenus) {
				InventoryMenu inventoryMenu = new InventoryMenu();
				inventoryMenu.setMenuName(menu.getMenuName());
				inventoryMenu.setUpdateStatus(menu.getUpdateStatus());
				inventoryMenu.setUrl(menu.getUrl());
				inventoryMenu.setMenu_uid(String.valueOf(menu.getId()));
				inventoryMenu.setParentId(menu.getParent() != null ? String.valueOf(menu.getParent().getId()) : null);
				if (menu.getChildrens().size() > 0) {
					List<InventoryMenu> subMenus = getSubMenus(menu);
					inventoryMenu.setSubMenu(subMenus);
				}
				menus.add(inventoryMenu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}

	public List<InventoryMenu> getSubMenus(MenuMaster menuMaster) {
		List<InventoryMenu> menus = new ArrayList<InventoryMenu>();
		List<MenuMaster> listOfMenus = menuMaster.getChildrens();
		for (MenuMaster menu : listOfMenus) {
			InventoryMenu inventoryMenu = new InventoryMenu();
			inventoryMenu.setMenuName(menu.getMenuName());
			inventoryMenu.setUpdateStatus(menu.getUpdateStatus());
			inventoryMenu.setUrl(menu.getUrl());
			inventoryMenu.setParentId(String.valueOf(menu.getParent().getId()));
			if (menu.getChildrens().size() > 0) {
				List<InventoryMenu> subMenus = getSubMenus(menu);
				inventoryMenu.setSubMenu(subMenus);
			}
			menus.add(inventoryMenu);
		}
		return menus;
	}

	public List<InventoryMenu> checkMenuPermissions(List<InventoryMenu> listOfMenus, Role role,
			List<RoleMenuLink> listOfLinks) {

		List<InventoryMenu> grantedMenus = new ArrayList<InventoryMenu>();

		try {
			for (RoleMenuLink link : listOfLinks) {

				for (InventoryMenu menu : listOfMenus) {

					if (menu.getMenuName().equals(link.getInventoryMenu().getMenuName())) {
						if (menu.getSubMenu().size() > 0) {
							List<InventoryMenu> grantedsubMenus = checkSubMenuPermissions(menu.getSubMenu(), role,
									link.getInventoryMenu());
							Collections.sort(grantedsubMenus);
							menu.setSubMenu(grantedsubMenus);
						}
						if (menu.getMenuName().equalsIgnoreCase("Reports")) {
							menu.setIcon("ft-book");
						}

						menu.setOrderId(link.getDisplayOrderId());
						menu.setUpdateStatus(link.getInventoryMenu().getUpdateStatus());
						grantedMenus.add(menu);
					}
				}
			}
			Collections.sort(grantedMenus);
			return grantedMenus;
		} catch (RuntimeException re) {
			re.printStackTrace();
			return grantedMenus;
		}
	}

	public List<InventoryMenu> checkSubMenuPermissions(List<InventoryMenu> listOfSubMenus, Role role,
			MenuMaster menuMaster) {

		List<InventoryMenu> grantedsubMenus = new ArrayList<InventoryMenu>();
		try {
			List<RoleMenuLink> listOfLinks = dao.findByRoleAndInventoryMenuParentIdAndAccessible(role,
					(int) menuMaster.getId(), "Y");
			for (RoleMenuLink link : listOfLinks) {
				for (InventoryMenu subMenu : listOfSubMenus) {
					if (subMenu.getMenuName().equals(link.getInventoryMenu().getMenuName())) {
						subMenu.setUpdateStatus(link.getInventoryMenu().getUpdateStatus());
						subMenu.setOrderId(link.getDisplayOrderId());

						if (subMenu.getSubMenu().size() > 0) {
							List<InventoryMenu> subsubMenu = checkSubMenuPermissions(subMenu.getSubMenu(), role,
									link.getInventoryMenu());
							Collections.sort(subsubMenu);
							subMenu.setSubMenu(subsubMenu);
						}
						grantedsubMenus.add(subMenu);
					}
				}

			}
			return grantedsubMenus;
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			return grantedsubMenus;
		}

	}

	
//	  public void sendSMS() { 
//	  try { 
//		  
//	  TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//	  List<NameValuePair> params = new ArrayList<NameValuePair>();
//	  params.add(new BasicNameValuePair("Body","Hello, World!"));
//	  params.add(new BasicNameValuePair("To", "+918801140530"));
//	  params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
//	  
//	  MessageFactory messageFactory = client.getAccount().getMessageFactory();
//	  Message message = messageFactory.create(params);
//	  //System.out.println(message.getSid());
//	  } catch (TwilioRestException e) {
//	  //System.out.println(e.getErrorMessage()); 
//	  } 
//	  }
	 

	@GetMapping("/logout")
	public final String logout(ModelMap modelmap, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {// TODO Auto-generated method stub
		User user = (User) session.getAttribute("user");
		if (user != null && user.getRole() != null && !user.getRole().getRoleName().equalsIgnoreCase("SuperAdmin")) {
			user.setLoggedStatus(Status.InActive.getValue());
			ddao.updateObj(user);
		}

		HttpSession session1 = request.getSession(false);

		session1.removeAttribute("institutionId");
		session1.removeAttribute("userId");
		request.getSession().removeAttribute("institutionId");
		request.getSession().removeAttribute("userid");
		if (session1 != null) {
			session1.invalidate();
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
		}
		// session.invalidate();
		//System.out.println("logged out .....");
		return "redirect:/";
	}

	public void sendSMS(String toNumber, String msg) throws InterruptedException {
		 try {
		 TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("Body", msg));
	        params.add(new BasicNameValuePair("To", "+91"+toNumber)); //Add real number here
	        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

	        MessageFactory messageFactory = client.getAccount().getMessageFactory();
	        Message message = (Message) messageFactory.create(params);
	        Thread.sleep(500);
		// System.out.println(message.getStatus());
		 }
		 catch (TwilioRestException e) {
		// System.out.println(e.getErrorMessage());
		 }
	}

	public CommonResponse sendScheduledMsg() {
		List<Institution> instList = dao.getInstitutions(Status.Active.getValue());
		LocalDate cdate = LocalDate.now();
		// System.out.println(cdate);
		// System.out.println("instList.size()::"+instList.size());
		for (Institution i : instList) {
			// System.out.println("institutionName:"+i.getInstitutionName());
			// System.out.println("i.getMsgScheduledDate():"+i.getMsgScheduledDate());
			if (i.getMsgScheduledDate() > 0
					&& (LocalDate.now().withDayOfMonth(i.getMsgScheduledDate()).compareTo(LocalDate.now()) != 0)) {
				// System.out.println(LocalDate.now().withDayOfMonth(i.getMsgScheduledDate())+"
				// ,"+(i.getMsgScheduledDate()>0 &&
				// (LocalDate.now().withDayOfMonth(i.getMsgScheduledDate()).compareTo(LocalDate.now())
				// !=0)));
				continue;
			}

			// System.out.println(LocalDate.now().withDayOfMonth(cdate.lengthOfMonth())+"
			// ,"+(cdate.compareTo(LocalDate.now().withDayOfMonth(cdate.lengthOfMonth())) !=
			// 0));
			if (i.getMsgScheduledDate() == 0) {
				if (cdate.compareTo(LocalDate.now().withDayOfMonth(cdate.lengthOfMonth())) != 0)
					continue;
			}
			// System.out.println("entereddddd");
			List<Customer> clist = ddao.getCustomerList(Status.Active.getValue(), i.getInstitution_uid());
			// System.out.println("clist::"+clist.size());
			if (clist != null && clist.size() > 0) {
				String status = ddao.configureOcc(i.getInstitution_uid());
				// System.out.println("status::"+status);
				if (status != null) {
					for (Customer c : clist) {
						try {
							//System.out.println("user.getPhone()::" + c.getPhoneNo());
							if (c.getPhoneNo() != null) {
								String textt = ddao.msgText(c.getCustomerName(), c.getCustomer_uid(),
										i.getInstitution_uid());
								//System.out.println(textt);
								if (textt != null && textt != "" && c.getInstitution() != null && c.getInstitution().getMessagesRequired() != null && c.getInstitution().getMessagesRequired().equalsIgnoreCase("YES"))
									sendSMS(c.getPhoneNo(), textt);
							}
						} catch (Exception e2) {
							// TODO: handle exception
							System.out.println("Entered Exception:::::::::::::");
							// e2.printStackTrace();
						}
					}
				}

			}

		}
		return null;
	}

}
