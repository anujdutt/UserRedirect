package com.raremile.methods;

import java.util.List;

import com.raremile.entities.UserProfile;

/**
 * Class to calculate the highest priority role of a given user.
 * 
 * @author AnujD
 *
 */
public class CalculatePriority {
	/**
	 * Static method to perform the actual computation.
	 * 
	 * @author AnujD
	 *
	 */

	public static int calculatePriority(List<UserProfile> userProfileList) {
		int priority = 0;
		for (UserProfile userProfile : userProfileList) {
			if (userProfile.getUserRole().equals("ADMINISTRATOR")) {
				priority = 3;
			}
			if ((userProfile.getUserRole().equals("PROJECTMANAGER"))
					&& (priority < 3)) {
				priority = 2;
			}
			if ((userProfile.getUserRole().equals("DEVELOPER"))
					&& (priority < 2)) {
				priority = 1;
			}
		}

		return priority;
	}
}
