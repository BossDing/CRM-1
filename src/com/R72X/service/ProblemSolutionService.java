package com.R72X.service;

import java.util.List;

import com.R72X.entity.Problemsolution;
import com.R72X.entity.ProblemsolutionDAO;
import com.R72X.util.PageBean;
import com.R72X.util.PageService;

public class ProblemSolutionService {
	private ProblemsolutionDAO problemsolutionDAO;

	public ProblemsolutionDAO getProblemsolutionDAO() {
		return problemsolutionDAO;
	}

	public void setProblemsolutionDAO(ProblemsolutionDAO problemsolutionDAO) {
		this.problemsolutionDAO = problemsolutionDAO;
	}
	
	public Problemsolution loadProblemsolution(Integer id){
		return problemsolutionDAO.findById(id);
	}
	
	public void saveProblemsolution(Problemsolution problemsolution){
		problemsolutionDAO.merge(problemsolution);
	}
	
	public void removeProblemsolution(Integer id){
		problemsolutionDAO.delete(problemsolutionDAO.findById(id));
	}
	
	public List<Problemsolution> searchstate(String stateWord,PageBean page){
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((stateWord == null) || (stateWord.equals(""))){
			selectHql = " from Problemsolution";
		}
		else {
			selectHql = " from Problemsolution as p where p.PState like '%" + stateWord + "%'"; 
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*)" + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Problemsolution> PS_List = PageService.searchByPage(selectHql, countHql, params, page, problemsolutionDAO);
		return PS_List;
	}
	
	public List<Problemsolution> searchProblemsolution(String problemSolutionName,PageBean page){
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((problemSolutionName == null) || (problemSolutionName.equals(""))){
			selectHql = " from Problemsolution";
		}
		else {
			selectHql = " from Problemsolution as p where p.problemName like '%" + problemSolutionName + "%'"; 
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*)" + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Problemsolution> PS_List = PageService.searchByPage(selectHql, countHql, params, page, problemsolutionDAO);
		return PS_List;
	}
}
