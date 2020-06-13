package com.test.user.userdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.test.user.userdemo.error.UserNotFoundException;
import com.test.user.userdemo.model.UserData;
import com.test.user.userdemo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;

	public List<UserData> getAllUsers() {
		// TODO Auto-generated method stub
		List<UserData> user = new ArrayList<UserData>();
		repository.findAll().forEach(b -> user.add(b));
		return user;

	}

	public UserData createorUpdateuser(UserData user) {
		Optional<UserData> userid = repository.findById(user.getUserid());

		if (userid.isPresent()) {
			UserData newbookid = userid.get();
			newbookid.setExperience(user.getExperience());
			newbookid.setUserName(user.getUserName());
			newbookid = repository.save(newbookid);
			return newbookid;
		} else {
			user = repository.save(user);
			return user;
		}

	}

	public void deleteUserId(int userid) throws UserNotFoundException {
		Optional<UserData> employee = repository.findById(userid);

		if (employee.isPresent()) {
			repository.deleteById(userid);
		} else {
			throw new UserNotFoundException("No user record exist for given id:"+userid);
		}
	}

	public Slice<UserData> getAllItemCategoriesByPageable(PageRequest page) {
		// TODO Auto-generated method stub
		Slice<UserData> u =repository.findAll((org.springframework.data.domain.Pageable) page);
		if(u.isEmpty()) {
			throw new UserNotFoundException("No user record exist for given page:"+page);
		}
		return u;
	}

	public List<UserData> getEmployeesByOrder() {
		List<UserData> userlist = repository.findAll(Sort.by(Sort.Direction.ASC, "experience"));
		return userlist;
	}
	
	public List<UserData> findbelowfiveexperience() {
		List<UserData> result = repository.findByExp();
		if(result.isEmpty()) {
			throw new UserNotFoundException("No user record found");
		}
		return result;
	}

	public List<UserData> findabovetenexperience() {
		List<UserData> result = repository.findByExp10();
		if(result.isEmpty()) {
			throw new UserNotFoundException("No user record found");
		}
		return result;
	}
	
	public List<UserData> getByExpbetween() {
		List<UserData> result = repository.findBetweenexperience();
		if(result.isEmpty()) {
			throw new UserNotFoundException("No user record found");
		}
		return result;
	}


}
