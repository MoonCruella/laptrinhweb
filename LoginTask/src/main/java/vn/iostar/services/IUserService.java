package vn.iostar.services;

import vn.iostar.models.UserModel;

public interface IUserService {
	UserModel login(String username,String password);
	UserModel findByUserName(String username);
}
