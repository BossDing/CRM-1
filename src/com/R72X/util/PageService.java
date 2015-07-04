package com.R72X.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PageService {

	/*
	 * ִ�з�ҳ��ѯ
	 * 
	 * @selectHql��HQL��ѯ���
	 * 
	 * @countHql��HQL��ѯ����ܼ�¼��ͳ��
	 * 
	 * @params����ѯ����
	 * 
	 * @page����ҳ����
	 * 
	 * @dao�����ݿ����DAO
	 */
	public static List searchByPage(final String selectHql,
			final String countHql, final Object[] params, final PageBean page,
			HibernateDaoSupport dao) {
		// û�з�ҳ���󣬱�ʾ����ҳ��ѯ���м�¼
		if (page == null) {
			return dao.getHibernateTemplate().find(selectHql);
		}

		/*
		 * ��ҳ��ѯ����
		 */
		// ��ò�ѯ���ܼ�¼���������÷�ҳ����
		getTotalCount(countHql, params, page, dao);

		// ִ�з�ҳ��ѯ
		List list = dao.getHibernateTemplate().executeFind(
		// Hibernate�ص�����
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(selectHql);
						// ���ò���
						if (params != null) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						// ���÷�ҳ��Ϣ�������ز�ѯ���
						List result = query.setFirstResult(
								(page.getCurrentPage() - 1)
										* page.getPageSize()).setMaxResults(
								page.getPageSize()).list();
						return result;
					}
				});
		return list;
	}

	/*
	 * ���㵱ǰHQL���󷵻ص��ܼ�¼����Ϊ��ҳ��׼�� HQL��ʽ��"select count(*) " + selectHql
	 */
	private static void getTotalCount(final String countHql,
			final Object[] params, final PageBean page, HibernateDaoSupport dao) {
		List list = dao.getHibernateTemplate().find(countHql, params);
		Long count = (Long) list.get(0);
		page.setTotalRow(count.intValue());
	}
}
