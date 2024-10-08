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
	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
			}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDao.insert(new UserModel(email, username, fullname,password,
			null,5,phone,date));
			return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);	
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
		
	}

	@Override
	public boolean checkVarification(String email, String username) {
		return userDao.checkVarification(email,username);
	}

	@Override
	public boolean resetPassword(String username, String password) {
		return userDao.resetPassword(username, password);
		
	}

	@Override
	public boolean checkMatchPassword(String password, String repassword) {
		if(!repassword.equals(password))
		{
			return false;
		}
		return true;
	}

}
