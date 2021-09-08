package com.project.user;

public class UserConcreteFactoryTest extends UserAbstractFactoryTest {
	UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
	IUserDAO userDAO = userAbstractFactory.createUserDAO();
	IUser user = userAbstractFactory.createUser();

	@Override
	public IUser createUser() {
		if (null == user) {
			user = new User();
		}
		return user;
	}

	@Override
	public IUserDAO createUserDAO() {
		if (null == userDAO) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

}
