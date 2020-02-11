package com.cts.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.Dao.UserAccountRepository;
import com.cts.service.UserAccount;


	@Controller
	//@RequestMapping(path = "/userAccount")
	public class UserAccountController {

	    @Autowired
	    UserAccountRepository userAccountRepository;
	    @GetMapping(path = "/add")
	    @ResponseBody
	    public String addUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

	        UserAccount userAccount = new UserAccount();
	        userAccount.setUsername(userName);
	        userAccount.setPassword(password);
	        userAccount.setEmail(email);

	        userAccountRepository.save(userAccount);

	        String ret = "User account has been added, user name = " + userName + ", password = " + password + ", email = "
	                + email;

	        return ret;

	    }

	
	    @GetMapping(path = "/findAll")
	    @ResponseBody
	    public String findAllUser() {

	        StringBuffer retBuf = new StringBuffer();

	        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findAll();

	        if (userAccountList != null) {
	            for (UserAccount userAccount : userAccountList) {
	                retBuf.append("user name = ");
	                retBuf.append(userAccount.getUsername());
	                retBuf.append(", password = ");
	                retBuf.append(userAccount.getPassword());
	                retBuf.append(", email = ");
	                retBuf.append(userAccount.getEmail());
	                retBuf.append("\r\n");
	            }
	        }

		/*
		 * if (retBuf.length() == 0) { retBuf.append("No record find."); } 
		 * else {
		 * retBuf.insert(0, "<pre>"); retBuf.append("</pre>"); }
		 */

	        return retBuf.toString();
	    }

	    
	    @GetMapping(path = "/findByName")
	    @ResponseBody
	    public String findByName(@RequestParam String userName) {

	        StringBuffer retBuf = new StringBuffer();

	        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findByUsername(userName);

	        if (userAccountList != null) {
	            for (UserAccount userAccount : userAccountList) {
	                retBuf.append("user name = ");
	                retBuf.append(userAccount.getUsername());
	                retBuf.append(", password = ");
	                retBuf.append(userAccount.getPassword());
	                retBuf.append(", email = ");
	                retBuf.append(userAccount.getEmail());
	                retBuf.append("\r\n");
	            }
	        }

	        if (retBuf.length() == 0) {
	            retBuf.append("No record find.");
	        }

	        return retBuf.toString();
	    }

	   
	/*
	 * @GetMapping(path = "/findByNameAndPassword")
	 * 
	 * @ResponseBody public String findByNameAndPassword(@RequestParam String
	 * userName, @RequestParam String password) {
	 * 
	 * StringBuffer retBuf = new StringBuffer();
	 * 
	 * List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository
	 * .findByUsernameAndPassword(userName, password);
	 * 
	 * if (userAccountList != null) { for (UserAccount userAccount :
	 * userAccountList) { retBuf.append("user name = ");
	 * retBuf.append(userAccount.getUsername()); retBuf.append(", password = ");
	 * retBuf.append(userAccount.getPassword()); retBuf.append(", email = ");
	 * retBuf.append(userAccount.getEmail()); retBuf.append("<br/>"); } }
	 * 
	 * if (retBuf.length() == 0) { retBuf.append("No record find."); }
	 * 
	 * return retBuf.toString(); }
	 */

	    
	    @GetMapping(path = "/updateUser")
	    @ResponseBody
	    public String updateUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

	        StringBuffer retBuf = new StringBuffer();

	        List<UserAccount> userAccountList = userAccountRepository.findByUsername(userName);

	        if (userAccountList != null) {
	            for (UserAccount userAccount : userAccountList) {
	                userAccount.setUsername(userName);
	                userAccount.setPassword(password);
	                userAccount.setEmail(email);
	                userAccountRepository.save(userAccount);
	            }
	        }

	        retBuf.append("User data update successfully.");

	        return retBuf.toString();
	    }

	    
	    @GetMapping(path = "/deleteByUserName")
	    @ResponseBody
	    public String deleteByUserName(@RequestParam String userName) {

	        StringBuffer retBuf = new StringBuffer();

	        userAccountRepository.deleteByUsername(userName);

	        retBuf.append("User data has been deleted successfully.");

	        return retBuf.toString();
	    }

	   
	    @GetMapping(path = "/deleteByUserNameAndPassword")
	    @ResponseBody
	    public String deleteByUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {

	        StringBuffer retBuf = new StringBuffer();

	        userAccountRepository.deleteByUsernameAndPassword(userName, password);

	        retBuf.append("User data has been deleted successfully.");

	        return retBuf.toString();
	    }

	}
