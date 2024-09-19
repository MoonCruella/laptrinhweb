package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnection;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnection implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public List<UserModel> findAll() {
		
		String sql = "select * from users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				list.add(
						new UserModel(
								rs.getInt("id"),
								rs.getString("email"),
								rs.getString("userName"),
								rs.getString("fullName"),
								rs.getString("passWord"),
								rs.getString("avatar"),
								rs.getInt("roleid"),
								rs.getString("phone"),
								rs.getDate("createdDate")));
			}
			return list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public UserModel findById(int id) {
		
		String sql = "SELECT * FROM users WHERE id = ? ";
		try {
				conn = super.getDatabaseConnection();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user; }
		} catch (Exception e) 
		{
			e.printStackTrace(); 
		
		}
		
		return null;
	}

	
	@Override
	public UserModel findByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM users WHERE userName = ? ";
		try {
				conn = super.getDatabaseConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user; }
		} catch (Exception e) 
		{
			e.printStackTrace(); 
		
		}
		
		return null;
	}




}
