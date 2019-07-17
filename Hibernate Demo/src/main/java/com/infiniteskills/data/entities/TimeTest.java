package com.infiniteskills.data.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Session;

import com.infiniteskills.data.HibernateUtil;

@Entity
@Table(name = "time_test")
public class TimeTest {

	@Id
	@GeneratedValue
	@Column(name = "TIME_TEST_ID")
	private Long timeTestId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIME_COLUMN")
	private Date datetimeColumn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_COLUMN")
	private Date timestampColumn;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_COLUMN")
	private Date dateColumn;

	@Temporal(TemporalType.TIME)
	@Column(name = "TIME_COLUMN")
	private Date timeColumn;

	@Column(name = "SQL_DATETIME_COLUMN")
	private Timestamp sqlDatetimeColumn;

	@Column(name = "SQL_TIMESTAMP_COLUMN")
	private Timestamp sqlTimestampColumn;

	@Column(name = "SQL_DATE_COLUMN")
	private java.sql.Date sqlDateColumn;

	@Column(name = "SQL_TIME_COLUMN")
	private Time sqlTimeColumn;

	public TimeTest() {

	}

	public TimeTest(Date date) {

		datetimeColumn = date;
		timestampColumn = date;
		dateColumn = date;
		timeColumn = date;

		sqlDatetimeColumn = new Timestamp(date.getTime());
		sqlTimestampColumn = new Timestamp(date.getTime());
		sqlDateColumn = new java.sql.Date(date.getTime());
		sqlTimeColumn = new Time(date.getTime());

	}

	@Override
	public String toString() {
		StringBuilder t = new StringBuilder();
		t.append("\ndatetimeColumn = " + datetimeColumn);
		t.append("\ntimestampColumn = " + timestampColumn);
		t.append("\ndateColumn = " + dateColumn);
		t.append("\ntimeColumn = " + timeColumn);
		t.append("\nsqlDatetimeColumn = " + sqlDatetimeColumn);
		t.append("\nsqlTimestampColumn = " + sqlTimestampColumn);
		t.append("\nsqlDateColumn = " + sqlDateColumn);
		t.append("\nsqlTimeColumn = " + sqlTimeColumn);
		return t.toString();
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			session.getTransaction().begin();

			TimeTest test = new TimeTest(new Date());
			session.save(test);

			session.getTransaction().commit();

			session.refresh(test);

			System.out.println(test.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}

	}
	/*
	 * - Stampa senza @Temporal - 
	 * datetimeColumn = 2019-05-19 17:36:17.0
	 * timestampColumn = 2019-05-19 17:36:17.0 
	 * dateColumn = 2019-05-19 02:00:00.0
	 * timeColumn = 1970-01-01 16:36:17.0 
	 * sqlDatetimeColumn = 2019-05-19 17:36:17.0
	 * sqlTimestampColumn = 2019-05-19 17:36:17.0 
	 * sqlDateColumn = 2019-05-19
	 * sqlTimeColumn = 16:36:16
	 * 
	 * - Stampa con @Temporal -
	 * datetimeColumn = 2019-05-19 17:42:40.0 
	 * timestampColumn = 2019-05-19 17:42:40.0 
	 * dateColumn = 2019-05-19 
	 * timeColumn = 16:42:39 
	 * sqlDatetimeColumn = 2019-05-19 17:42:40.0 
	 * sqlTimestampColumn = 2019-05-19 17:42:40.0
	 * sqlDateColumn = 2019-05-19 
	 * sqlTimeColumn = 16:42:39
	 */

	public Long getTimeTestId() {
		return timeTestId;
	}

	public void setTimeTestId(Long timeTestId) {
		this.timeTestId = timeTestId;
	}

	public Date getDatetimeColumn() {
		return datetimeColumn;
	}

	public void setDatetimeColumn(Date datetimeColumn) {
		this.datetimeColumn = datetimeColumn;
	}

	public Date getTimestampColumn() {
		return timestampColumn;
	}

	public void setTimestampColumn(Date timestampColumn) {
		this.timestampColumn = timestampColumn;
	}

	public Date getDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(Date dateColumn) {
		this.dateColumn = dateColumn;
	}

	public Date getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(Date timeColumn) {
		this.timeColumn = timeColumn;
	}

	public Timestamp getSqlDatetimeColumn() {
		return sqlDatetimeColumn;
	}

	public void setSqlDatetimeColumn(Timestamp sqlDatetimeColumn) {
		this.sqlDatetimeColumn = sqlDatetimeColumn;
	}

	public Timestamp getSqlTimestampColumn() {
		return sqlTimestampColumn;
	}

	public void setSqlTimestampColumn(Timestamp sqlTimestampColumn) {
		this.sqlTimestampColumn = sqlTimestampColumn;
	}

	public java.sql.Date getSqlDateColumn() {
		return sqlDateColumn;
	}

	public void setSqlDateColumn(java.sql.Date sqlDateColumn) {
		this.sqlDateColumn = sqlDateColumn;
	}

	public Time getSqlTimeColumn() {
		return sqlTimeColumn;
	}

	public void setSqlTimeColumn(Time sqlTimeColumn) {
		this.sqlTimeColumn = sqlTimeColumn;
	}

}
