package com.project.user;

public class UserConcreteFactory extends UserAbstractFactory {
	private IUser user;
	private IUserDAO userDAO;

	public IUser createUser() {
		if (null == user) {
			user = new User();
		}
		return user;
	}

	public IUser createNewUser() {
		return new User();
	}

	public IUserDAO createUserDAO() {
		if (null == userDAO) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	public IUserDAO createNewUserDAO() {
		return new UserDAO();
	}

}
