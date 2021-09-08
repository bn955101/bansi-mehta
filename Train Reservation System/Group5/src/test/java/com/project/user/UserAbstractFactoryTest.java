package com.project.user;

public abstract class UserAbstractFactoryTest {
	private static UserAbstractFactory instance = null;

	public static UserAbstractFactory instance() {
		if (null == instance) {
			instance = new UserConcreteFactory();
		}
		return instance;
	}

	public abstract IUser createUser();

	public abstract IUserDAO createUserDAO();

}
