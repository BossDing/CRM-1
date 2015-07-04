package com.R72X.service;

import java.util.List;

import com.R72X.entity.Compact;
import com.R72X.entity.CompactDAO;
import com.R72X.entity.Contactnote;
import com.R72X.util.PageBean;
import com.R72X.util.PageService;

public class CompactService {
	private CompactDAO compactDAO;

	public void setCompactDAO(CompactDAO compactDAO) {
		this.compactDAO = compactDAO;
	}

	/*
	 * ��������ID���Ҷ���
	 */
	public Compact loadCompact(Integer id) {
		return compactDAO.findById(id);
	}

	/*
	 * ִ������������޸Ķ���ı������
	 */
	public void saveCompact(Compact compact) {
		compactDAO.merge(compact);
	}

	/*
	 * ��������IDɾ�����ݿ��ж���
	 */
	public void removeCompact(Integer id) {
		compactDAO.delete(compactDAO.findById(id));
	}

	/*
	 * ��������ѯ�ͷ�ҳ֧��
	 * 
	 * ���ݻ�Ա����ִ��ģ��ƥ����ң�ͨ��Page����ִ�з�ҳ��
	 * 
	 * @loginName����Ա��¼�˺�
	 * 
	 * @page:��ҳ����
	 */

	public List<Compact> searchCompact(String keyword, PageBean page) {
		Object[] params = null;
		String selectHql = null;
		if ((keyword == null) || (keyword.equals(""))) {
			selectHql = "from Compact as r where r.lastTime > 0 and r.compactDesc not like '#��ͬ�ѵ���#%'";
		} else {
			selectHql = "from Compact as r where r.lastTime > 0 and r.compactDesc not like '#��ͬ�ѵ���#%' and r.enterprise.enterpriseName like '%"
					+ keyword + "%'";
		}

		String countHql = "select count(*) " + selectHql;

		List<Compact> list = PageService.searchByPage(selectHql, countHql,
				params, page, compactDAO);
		return list;
	}

	public List<Compact> searchOverdueCompact(String keyword, PageBean page) {
		Object[] params = null;
		String selectHql = null;

		if ((keyword == null) || (keyword.equals(""))) {
			selectHql = "from Compact as r where r.lastTime > 0 and r.compactDesc like '#��ͬ�ѵ���#%'";
		} else {
			selectHql = "from Compact as r where r.lastTime > 0 and r.compactDesc like '#��ͬ�ѵ���#%' and r.enterprise.enterpriseName like '%"
					+ keyword + "%'";
		}

		String countHql = "select count(*) " + selectHql;

		List<Compact> list = PageService.searchByPage(selectHql, countHql,
				params, page, compactDAO);
		return list;
	}

	public List<Compact> searchEffectCompact(String keyword, PageBean page) {
		Object[] params = null;
		String selectHql = null;

		if ((keyword == null) || (keyword.equals(""))) {
			selectHql = "from Compact as r where r.lastTime > 0 order by r.effectTime desc";
		} else {
			selectHql = "from Compact as r where r.lastTime > 0 order by r.effectTime desc";
		}

		String countHql = "select count(*) " + selectHql;

		List<Compact> list = PageService.searchByPage(selectHql, countHql,
				params, page, compactDAO);
		return list;
	}

	public List<Compact> searchCompactByProductID(Integer integer, PageBean page) {
		Object[] params = null;

		// ����HQL��ѯ���
		String selectHql = null;
		if ((integer == null) || (integer.equals(""))) {
			selectHql = "from Compact";
		} else {
			selectHql = "from Compact as r where r.product.productId like '%"
					+ integer + "%'";
		}

		// ������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;

		// ִ�а�������ѯ�ͷ�ҳ֧��
		List<Compact> compactList = PageService.searchByPage(selectHql,
				countHql, params, page, compactDAO);
		return compactList;
	}

	public List<Compact> searchCompactBySellerID(Integer integer, PageBean page) {
		Object[] params = null;
		String selectHql = null;
		if ((integer == null) || (integer.equals(""))) {
			selectHql = "from Compact ";
		} else {
			selectHql = "from Compact as r where  r.userBySellerId.userId like '%"
					+ integer + "%'";
		}

		String countHql = "select count(*) " + selectHql;

		List<Compact> list = PageService.searchByPage(selectHql, countHql,
				params, page, compactDAO);
		return list;
	}

}
