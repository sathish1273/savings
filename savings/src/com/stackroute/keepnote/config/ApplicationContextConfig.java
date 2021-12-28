package com.stackroute.keepnote.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.keepnote.dto.ExcelDto;
import com.stackroute.keepnote.model.Address;
import com.stackroute.keepnote.model.BankDeposits;
import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Expenses;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.InterestType;
import com.stackroute.keepnote.model.Ledger;
import com.stackroute.keepnote.model.LoanHistory;
import com.stackroute.keepnote.model.LoanType;
import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.Login;
import com.stackroute.keepnote.model.MenuMaster;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.Occurrance;
import com.stackroute.keepnote.model.OtherIncome;
import com.stackroute.keepnote.model.Otp;
import com.stackroute.keepnote.model.PendingDeposits;
import com.stackroute.keepnote.model.Receipt;
import com.stackroute.keepnote.model.Recharge;
import com.stackroute.keepnote.model.Role;
import com.stackroute.keepnote.model.RoleMenuLink;
import com.stackroute.keepnote.model.SpLoanHistory;
import com.stackroute.keepnote.model.SpecialLoan;
import com.stackroute.keepnote.model.User;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *  
 *                  
 * */

@Configuration
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	public ApplicationContextConfig(){}
	@Bean
	public DataSource getdatasource()
	{
		BasicDataSource datasource=new BasicDataSource();
			  datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			  datasource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" +
			  System.getenv("MYSQL_DATABASE")
			  +"?createDatabaseIfNotExist=true&verifyServerCertificate=false&useSSL=false&requireSSL=false");
			  datasource.setUsername(System.getenv("MYSQL_USER"));
			  datasource.setPassword(System.getenv("MYSQL_PASSWORD"));
			 
		return datasource;
	}
	
	@Bean
	public LocalSessionFactoryBean getsession(DataSource datasource) throws IOException
	{
		//oLjeCMugKm6uy_3pEN0yA_r8uysuaCY9IneTTLjv
		LocalSessionFactoryBean localsession=new LocalSessionFactoryBean();
		localsession.setDataSource(datasource); //
		Properties prop=new Properties();
		prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		prop.put("hibernate.hbm2ddl.auto","none");
		prop.put("hibernate.show_sql", "none");
		localsession.setHibernateProperties(prop);
	  	localsession.setAnnotatedClasses(Note.class,User.class,Address.class,RoleMenuLink.class,Role.class,Login.class,MenuMaster.class,Institution.class
	  			,BankDeposits.class,Customer.class,Deposits.class,Occurrance.class,InterestType.class
	  			,Loans.class,LoanHistory.class,LoanType.class,Ledger.class,SpecialLoan.class,Expenses.class,SpLoanHistory.class
	  			,PendingDeposits.class,ExcelDto.class,Recharge.class,Otp.class,Receipt.class,OtherIncome.class
	  			//PendingTransactions.class
	  			//PendingLoans.class,PendingSPLoans.class
	  			//,GroupLedger.class,DepositType.class,,Interests.class
	  			//LoanType.class,Groups.class,
	  			);
		localsession.afterPropertiesSet();
		return localsession;
	}
	
	@Bean
	public HibernateTransactionManager gethibertransact(SessionFactory sessfact)
	{
		HibernateTransactionManager hibermanager=new HibernateTransactionManager();
		hibermanager.setSessionFactory(sessfact);
		return hibermanager;
		
	}
}
