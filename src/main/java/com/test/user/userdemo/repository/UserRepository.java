package com.test.user.userdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.user.userdemo.model.UserData;

public interface UserRepository extends JpaRepository<UserData, Integer> {

	@Query("SELECT " + "    new com.test.user.userdemo.model.UserData(u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    UserData u " + " ORDER BY u.experienceLevel ASC")
	List<UserData> finduserExperience();

	@Query("SELECT "
			+ "    new com.test.user.userdemo.model.UserData(u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    UserData u " + " WHERE u.experience < 5")
	List<UserData> findByExp();

	@Query("SELECT "
			+ "    new com.test.user.userdemo.model.UserData(u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    UserData u " + " WHERE u.experience > 10")
	List<UserData> findByExp10();

	@Query("SELECT "
			+ "    new com.test.user.userdemo.model.UserData(u.userid, u.userName, u.experience, u.experienceLevel) "
			+ "FROM " + "    UserData u " + " WHERE u.experience BETWEEN 5 AND 10")
	List<UserData> findBetweenexperience();

}
