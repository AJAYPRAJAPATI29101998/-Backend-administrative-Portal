package com.simplilearn.workshop.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.simplilearn.workshop.list.Student;
import com.simplilearn.workshop.list.Teacher;
import com.simplilearn.workshop.list.Subject;
import com.simplilearn.workshop.list.Class;

public class DbRetrieve {

	private DataSource dataSource;

	public DbRetrieve(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.students";
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				
				int id = myResultset.getInt("id");
				String firstName = myResultset.getString("fname");
				String lastName = myResultset.getString("lname");
				int age = myResultset.getInt("age");
				int aclass =  myResultset.getInt("class");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age,aclass);

				// add it to the list of students
				students.add(tempStudent);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return students;

	}

	public List<Teacher> getTeachers() {

		List<Teacher> teachers = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.teachers";
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				String firstName = myResultset.getString("fname");
				String lastName = myResultset.getString("lname");
				int age = myResultset.getInt("age");

				// create new teacher object
				Teacher temp = new Teacher(id, firstName, lastName, age);

				// add it to the list of teachers
				teachers.add(temp);

			}

		} catch (Exception e) {
			
		} finally {
			
			// close JDBC objects
			close(myConnection, myStatement, myResultset);
		}
		return teachers;

	}

	public List<Subject> getSubjects() {

		List<Subject> subjects = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.subjects";
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				String name = myResultset.getString("name");
				String shortcut = myResultset.getString("shortcut");

				// create new subject object
				Subject temp = new Subject(id, name,shortcut);

				// add it to the list of subjects
				subjects.add(temp);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return subjects;

	}

	public List<Class> getClasses() {

		List<Class> classes = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.classes";
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				int section = myResultset.getInt("section");
				int subject = myResultset.getInt("subject");
				int teacher = myResultset.getInt("teacher");
				String time = myResultset.getString("time");
				

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

				// create new class object
				Class temp = new Class(id, section, teacher_name, tempSubject.getName(), time);

				// add it to the list of classes
				classes.add(temp);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return classes;

	}

	public Teacher loadTeacher(int teacherId) {

		Teacher theTeacher = null;

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.teachers WHERE id = " + teacherId;
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				String fname = myResultset.getString("fname");
				String lname = myResultset.getString("lname");
				int age = myResultset.getInt("age");
				theTeacher = new Teacher(id, fname, lname, age);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return theTeacher;

	}

	public Subject loadSubject(int subjectId) {

		Subject theSubject = null;

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.subjects WHERE id = " + subjectId;
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				String name = myResultset.getString("name");
				String shortcut = myResultset.getString("shortcut");

				theSubject = new Subject(id, name,shortcut);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return theSubject;

	}

	public Class loadClass(int classId) {

		Class theClass = null;

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.clasess WHERE id = " + classId;
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				int section = myResultset.getInt("section");
				int subject = myResultset.getInt("subject");
				int teacher = myResultset.getInt("teacher");
				String time = myResultset.getString("time");
				

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return theClass;

	}

	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;

		try {

			// get a connection
			myConnection = DBConnection.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM `administrative-portal`.students WHERE class = " + classId;
			myStatement = myConnection.createStatement();

			// execute query
			myResultset = myStatement.executeQuery(sql);

			// process result
			while (myResultset.next()) {

				// retrieve data from result set row
				int id = myResultset.getInt("id");
				String firstName = myResultset.getString("fname");
				String lastName = myResultset.getString("lname");
				int age = myResultset.getInt("age");
				int aclass = myResultset.getInt("class");
				

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age,aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			
		} finally {
			
			close(myConnection, myStatement, myResultset);
		}
		return students;

	}

	private void close(Connection myConnection, Statement myStatement, ResultSet myResultset) {

		try {
			if (myResultset != null) {
				myResultset.close();
			}
			if (myStatement != null) {
				myStatement.close();
			}
			if (myConnection != null) {
				myConnection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}