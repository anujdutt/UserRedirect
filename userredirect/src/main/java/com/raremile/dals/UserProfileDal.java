package com.raremile.dals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.raremile.common.DatabaseManager;
import com.raremile.entities.UserProfile;
import com.raremile.exception.DatabaseException;

public class UserProfileDal {
	static {
		// Load the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
		}
	}
	private static Connection CON = null;
	private static PreparedStatement PSTMT = null;
	private static final String SELECT_QUERY = "SELECT * FROM userprofile WHERE USER_ID=?;";
	private static final Logger LOG = Logger
			.getLogger(com.raremile.dals.UserProfileDal.class);

	public List<UserProfile> getUserProfile(int userId)
			throws DatabaseException {
		CON = DatabaseManager.getConnection();
		List<UserProfile> resultUserProfile = new ArrayList<UserProfile>();
		UserProfile userProfile = null;
		ResultSet result = null;
		try {
			PSTMT = CON.prepareStatement(SELECT_QUERY);
			PSTMT.setInt(1, userId);
			result = PSTMT.executeQuery();
			while (result.next()) {
				userProfile = new UserProfile();
				LOG.info("User has been found.");
				userProfile.setUserProfileId(result.getInt("USER_PROFILE_ID"));
				userProfile.setUserId(result.getInt("USER_ID"));
				userProfile.setUserRole(result.getString("USER_ROLE"));
				resultUserProfile.add(userProfile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeDBObjects(PSTMT, result, CON);
		}
		return resultUserProfile;
	}
}
