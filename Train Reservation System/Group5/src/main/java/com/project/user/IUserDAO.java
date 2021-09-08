package com.project.user;

public interface IUserDAO {

	IUser getUserByUsername(String username);

	void saveUser(IUser user);

	boolean isUserExists(String username);

	boolean isUserPresentWithSameQuestionAndAnswer(IUser user);

	boolean updatePassword(IUser user);

}
