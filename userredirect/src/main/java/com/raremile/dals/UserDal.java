package com.raremile.dals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.raremile.common.DatabaseManager;
import com.raremile.entities.User;
import com.raremile.exception.DatabaseException;

public class UserDal {
	static {
		// Load the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
	}
	private static Connection CON = null;
	private static PreparedStatement PSTMT = null;
	private static final String SELECT_QUERY = "SELECT * FROM user WHERE USER_NAME LIKE ? && USER_PASSWORD LIKE ?;";
	private static final Logger LOG = Logger
			.getLogger(com.raremile.dals.UserDal.class);

	public User getUser(String userName, String userPassword)
			throws DatabaseException {
		CON = DatabaseManager.getConnection();
		User resultUser = new User();
		ResultSet result = null;
		try {
			PSTMT = CON.prepareStatement(SELECT_QUERY);
			PSTMT.setString(1, userName);
			PSTMT.setString(2, userPassword);
			result = PSTMT.executeQuery();
			if (result.first()) {
				LOG.info("User has been found.");
				resultUser.setUserId(result.getInt("USER_ID"));
				resultUser.setUserName(result.getString("USER_NAME"));
				resultUser.setUserPassword(result.getString("USER_PASSWORD"));
			} else {
				LOG.error("No such user found in database.");
				throw new DatabaseException("No such User found in database.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			LOG.info("Closing the connection.");
			DatabaseManager.closeDBObjects(PSTMT, result, CON);
		}
		return resultUser;
	}
}
