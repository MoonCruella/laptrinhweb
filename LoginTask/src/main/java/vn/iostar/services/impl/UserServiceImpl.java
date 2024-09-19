package vn.iostar.services.impl;

import java.util.ArrayList;
import java.util.List;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;

public class UserServiceImpl implements IUserService {

	static // Lay toan bo ham trong tang Dao cua user
	IUserDao userDao = new UserDaoImpl();
	@Override
	public UserModel login(String username, String password) {
		
		UserModel user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}
	
	public static void main(String[] args) {
		
		IUserService userService = new UserServiceImpl();
		List<UserModel> userModel = new ArrayList<>();
		try {
			userModel = userDao.findAll();
			for (UserModel user : userModel)
			{
				System.out.println(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
