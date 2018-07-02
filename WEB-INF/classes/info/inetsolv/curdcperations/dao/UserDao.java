package info.inetsolv.curdcperations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import info.inetsolv.curdoperations.model.UserModel;
import info.inetsolv.curdoperations.utils.DBUtils;

public class UserDao {

	/**
	 * @abstract This method is used to retrieve all users information
	 * @return
	 */
	public List<UserModel> getAllUsers() {
		Connection con = null;
		ArrayList<UserModel> usersList = new ArrayList<UserModel>();
		UserModel user = null;
		try {
			con = DBUtils.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER_MASTER");
			while (rs.next()) {
				user = new UserModel();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setAddress(rs.getString(4));
				usersList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usersList;
	}

	/**
	 * This method is used to insert user record
	 * @param model
	 * @return
	 */
	public boolean insertUser(UserModel model) {
		Connection con = null;
		boolean flag = false;
		int userId = 0;
		try {
			con = DBUtils.getConnection();
			String sql = "INSERT INTO USER_MASTER VALUES(?,?,?,?)";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(USER_ID) FROM USER_MASTER");
			if(rs.next()){
				userId = rs.getInt(1);
			}
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId + 1);
			pstmt.setString(2, model.getUserName());
			pstmt.setString(3, model.getEmail());
			pstmt.setString(4, model.getAddress());

			int no = pstmt.executeUpdate();
			if (no > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * This method is used to Retrieve user record based on User_ID
	 * @param id
	 * @return
	 */
	public UserModel getUserById(int id) {
		Connection con = null;
		UserModel user = null;
		try {
			con = DBUtils.getConnection();
			String sql = "SELECT * FROM USER_MASTER where USER_ID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserModel();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setAddress(rs.getString(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * This method is used to delete User record based on User_id
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id) {
		Connection con = null;
		boolean flag = false;
		try {
			con = DBUtils.getConnection();
			String sql = "DELETE FROM USER_MASTER WHERE USER_ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int no = pstmt.executeUpdate();
			if (no > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * This method is used to update user record based on user_id
	 * @param model
	 * @return
	 */
	public boolean updateUser(UserModel model) {
		Connection con = null;
		boolean flag = false;
		try {
			con = DBUtils.getConnection();
			String sql = "UPDATE USER_MASTER SET NAME=?,EMAIL=?,ADDRESS=? where user_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, model.getUserName());
			pstmt.setString(2, model.getEmail());
			pstmt.setString(3, model.getAddress());
			pstmt.setInt(4, model.getUserId());
			int no = pstmt.executeUpdate();
			if (no > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
