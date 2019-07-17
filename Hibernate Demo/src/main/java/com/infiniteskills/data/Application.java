package com.infiniteskills.data;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import com.infiniteskills.data.entities.User;

public class Application {

	public static void main(String[] args) {

		/* Obtain Session and Call Persistence Methods */
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Insert user:
		session.beginTransaction();

		User user = new User();
		user.setFirstName("Rossella");
		user.setLastName("Bozzini");
		user.setBirthDate(getMyBirthday());
		user.setEmailAddress("rossellabozzini@gmail.com");
		user.setLastUpdatedDate(new Date());
		user.setLastUpdatedBy("rossella");
		user.setCreatedDate(new Date());
		user.setCreatedBy("rossella");

		session.save(user);

		session.getTransaction().commit();

//		// Modify user:
//
//		session.beginTransaction();
//
//		// Select user by id:
//		User dbUser = (User) session.get(User.class, user.getUserId());
//		dbUser.setFirstName("Ross");
//
//		session.update(dbUser);
//		session.getTransaction().commit();

		// @Formula: calculate age:
		// Need to refresh entity:
		session.refresh(user);
		System.out.println(user.getFirstName() + " ha " + user.getAge() + " anni!");

		session.close();
	}

	private static Date getMyBirthday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1973);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.DATE, 25);
		return cal.getTime();
	}
}
