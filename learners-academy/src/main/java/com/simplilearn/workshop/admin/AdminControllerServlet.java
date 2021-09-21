package com.simplilearn.workshop.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.simplilearn.workshop.list.Student;
import com.simplilearn.workshop.list.Subject;
import com.simplilearn.workshop.list.Teacher;
import com.simplilearn.workshop.list.Class;


@WebServlet("/AdminControllerServlet")
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DbRetrieve dbRetrieve;

	private DataSource datasource;

	@Override
	public void init() throws ServletException {

		super.init();

		
		try {
			dbRetrieve = new DbRetrieve(datasource);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	
	public AdminControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			// read the "command" parameter
			String command = request.getParameter("command");

			if (command == null) {
				command = "CLASSES";
			}
			
			// if no cookeies
			
			if (!getCookies(request, response) && (!command.equals("LOGIN"))) {

				response.sendRedirect("/learners-acadamy/login.jsp");
			}

			else {

				// if there is no command, how to handle route the data
				
				switch (command) {

				case "STUDENTS":
					studentsList(request, response);
					break;

				case "TEACHERS":
					teachersList(request, response);
					break;

				


				case "LOGIN":
					login(request, response);
					break;

				default:
					classestList(request, response);

				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void studentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = dbRetrieve.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send it to the jsp view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/students-list.jsp");
		dispatcher.forward(request, response);

	}

	private void teachersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get teachers from db util
		List<Teacher> teachers = dbRetrieve.getTeachers();

		// add teachers to the request
		request.setAttribute("TEACHERS_LIST", teachers);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teachers-list.jsp");
		dispatcher.forward(request, response);

	}



	private void classestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get classes from db util
		List<Class> classes = dbRetrieve.getClasses();

		// add classes to the request
		request.setAttribute("CLASSES_LIST", classes);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.equals("admin") && password.equals("admin")) {

			Cookie cookie = new Cookie(username, password);


			// Send the cookie to the client
			
			response.addCookie(cookie);
			classestList(request, response);
		
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int classId = Integer.parseInt(request.getParameter("classId"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");

		
		List<Student> students = dbRetrieve.loadClassStudents(classId);

		// add student class to the request
		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/students-class.jsp");
		dispatcher.forward(request, response);

	}

	private boolean getCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean check = false;
		Cookie[] cookies = request.getCookies();
		
		
		for (Cookie cookie : cookies) {
			 
			if (cookie.getName().equals("admin") && cookie.getValue().equals("admin")) {
				check = true;
				break;
			} 

		}
		
		
		return check;

	}

}