package com.R72X.filter;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

/**
 * SSH����ʱHibernate�ύ��BUG�޸�
 * 
 * �˹�������֤���û�ÿ��HTTP����ʱ��Hibernate��Sessionһֱ�򿪣������Զ��������ύ����
 * 
 * @author zzy
 * 
 */
public class FixOpenSessionInViewFilter extends OpenSessionInViewFilter {

	public FixOpenSessionInViewFilter() {
		super.setFlushMode(FlushMode.AUTO);
	}

	protected void closeSession(Session session, SessionFactory sessionFactory) {
		// ˢ������
		session.flush();

		// �ύ����
		try {
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// e.printStackTrace();
		}

		// �ر�Hibernate Session
		super.closeSession(session, sessionFactory);
	}
}
