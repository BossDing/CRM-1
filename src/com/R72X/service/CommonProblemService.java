package com.R72X.service;

import java.util.List;

import com.R72X.entity.Commonproblem;
import com.R72X.entity.CommonproblemDAO;
import com.R72X.util.PageBean;
import com.R72X.util.PageService;

public class CommonProblemService {
	private CommonproblemDAO commonproblemDAO;
	

	public CommonproblemDAO getCommonproblemDAO() {
		return commonproblemDAO;
	}

	public void setCommonproblemDAO(CommonproblemDAO commonproblemDAO) {
		this.commonproblemDAO = commonproblemDAO;
	}

	/**
	 * �����������Ҷ���
	 * @param id
	 * @return
	 */
	public Commonproblem loadCommonproblem(Integer id){
			return commonproblemDAO.findById(id);
	}
	
	/**
	 * ����һ��CommonProblem������
	 * @param commonProblem
	 */
	public void saveCommonproblem(Commonproblem commonProblem){
		commonproblemDAO.merge(commonProblem);
	}
	
	/**
	 * ����Idɾ����CommonProblem
	 * @param id
	 */
	public void removeCommonproblem(Integer id){
		commonproblemDAO.delete(commonproblemDAO.findById(id));
	}
	
	/**
	 * ���������ķ�ҳ��ѯ��������ѯ
	 * @param commonProblemName
	 * @param page
	 * @return
	 */
	public List<Commonproblem> searchCommonProblem(String keyWord,PageBean page){
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((keyWord == null) || (keyWord.equals(""))){
			selectHql = "from Commonproblem";
		}
		else {
			selectHql = "from Commonproblem as c where c.commonProblemDesc like '%" + keyWord + "%'"; 
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*)" + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Commonproblem> com_problemList = PageService.searchByPage(selectHql, countHql, params, page, commonproblemDAO);
		return com_problemList;
	}
}
