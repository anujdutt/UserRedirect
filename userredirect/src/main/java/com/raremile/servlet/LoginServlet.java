package com.raremile.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raremile.dals.UserDal;
import com.raremile.dals.UserProfileDal;
import com.raremile.entities.User;
import com.raremile.entities.UserProfile;
import com.raremile.exception.DatabaseException;
import com.raremile.methods.CalculatePriority;

/**
 * Servlet to authenticate login and print messages.
 * 
 * @author AnujD
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger
			.getLogger(com.raremile.servlet.LoginServlet.class);

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		UserDal userDal = new UserDal();
		UserProfileDal userProfileDal = new UserProfileDal();
		List<UserProfile> userProfileList = new ArrayList<UserProfile>();
		User user = new User();
		try {
			user = userDal.getUser(userName, userPassword);
			userProfileList = userProfileDal.getUserProfile(user.getUserId());
			LOG.info("User found. Fetching user profile.");
			// Find out the highest priority role of the User.
			int priority = CalculatePriority.calculatePriority(userProfileList);
			if (priority == 3) {
				request.setAttribute("message", "You are an Administrator.");
			} else if (priority == 2) {
				request.setAttribute("message", "You are a Project manager.");
			} else {
				request.setAttribute("message", "You are a Developer.");
			}
			request.setAttribute("roles", userProfileList);
			request.setAttribute("username", "Welcome " + user.getUserName());
		} catch (DatabaseException e) {
			LOG.info("User not found.");
			request.setAttribute("message", "You are a Nobody. Go away.");
			request.setAttribute("roles", "No roles found.");
			request.setAttribute("username", "Access denied");
		} finally {
			LOG.info("Forwarding to showMessage.jsp.");
			// Forwarding to the second jsp to print the messages.
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("WEB-INF/view/showMessage.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
