package com.cts.Dao;	
	import java.util.List;

	import org.springframework.data.repository.CrudRepository;
	import org.springframework.transaction.annotation.Transactional;

import com.cts.service.UserAccount;

	

	public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

	    
	    List<UserAccount> findByUsername(String username);

	  
	    List<UserAccount> findByUsernameAndPassword(String username, String password);

	    @Transactional
	    void deleteByUsernameAndPassword(String username, String password);

	    @Transactional
	    void deleteByUsername(String username);

	}


