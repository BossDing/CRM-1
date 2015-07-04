package com.R72X.action;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.R72X.entity.Problemsolution;
import com.R72X.entity.User;
import com.R72X.service.ProblemSolutionService;
import com.R72X.util.PageBean;
import com.opensymphony.xwork2.ActionContext;

public class ProblemSolutionAction {
	private Integer id;
	private Integer[] ids;
	private Problemsolution problemsolution;
	private Collection<Problemsolution> problemsolutionColl;
	private PageBean pageBean; // ��ҳ����
	private ProblemSolutionService problemSolutionService;
	private String keyWord;
	private String stateWord;
	private String category;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getStateWord() {
		return stateWord;
	}

	public void setStateWord(String stateWord) {
		this.stateWord = stateWord;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Problemsolution getProblemsolution() {
		return problemsolution;
	}

	public void setProblemsolution(Problemsolution problemsolution) {
		this.problemsolution = problemsolution;
	}

	public Collection<Problemsolution> getProblemsolutionColl() {
		return problemsolutionColl;
	}

	public void setProblemsolutionColl(
			Collection<Problemsolution> problemsolutionColl) {
		this.problemsolutionColl = problemsolutionColl;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public ProblemSolutionService getProblemSolutionService() {
		return problemSolutionService;
	}

	public void setProblemSolutionService(
			ProblemSolutionService problemSolutionService) {
		this.problemSolutionService = problemSolutionService;
	}

	/**
	 * �����ͱ༭�����ݶ�ȡ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String load() throws Exception {
		if (id == null) {
			return "error";
		}
		problemsolution = problemSolutionService.loadProblemsolution(id);
		return "success";
	}

	/**
	 * ��״̬�鿴ʱ�����ݶ�ȡ����
	 * @return
	 * @throws Exception
	 */
	public String mulLoad() throws Exception {
		if (id == null) {
			return "error";
		}
		problemsolution = problemSolutionService.loadProblemsolution(id);
		if (problemsolution.getPState().equals("δ����"))
			return "un";
		if (problemsolution.getPState().equals("������"))
			return "ing";
		if(problemsolution.getPState().equals("�Ѵ���")){
			return "over";
		}
		return "error";
	}

	/**
	 * �����ͱ༭�����ݱ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		Map<String,Object> session=(Map)ActionContext.getContext().getSession();
		//session.put("loginId", 134);
		Integer loginId = (Integer)session.get("loginId");
		User u = new User();
		u.setUserId(loginId);
		problemsolution.setUserByServerId(u);
		problemsolution.setSubmitDate(getTime());
		problemsolution.setPState("δ����");
		problemSolutionService.saveProblemsolution(problemsolution);
		return "success";
	}
	
	public String changeState() throws Exception {
		Map<String,Object> session=(Map)ActionContext.getContext().getSession();
		//session.put("loginId", 125);
		Integer loginId = (Integer)session.get("loginId");
		User u = new User();
		u.setUserId(loginId);	//�õ�Ŀǰ��½�û�ID
		if(problemsolution.getPState().equals("�Ѵ���")&&problemsolution.getSolutionDesc()==null){
			return "error";
		}
		if(problemsolution.getPState().equals("δ����")){
			problemsolution.setUserByProSolverId(null);
			problemsolution.setSolutionDesc(null);
		}
		if(problemsolution.getPState().equals("������")){
			problemsolution.setUserByProSolverId(u);
			problemsolution.setSolutionDesc(null);
		}
		if(problemsolution.getPState().equals("�Ѵ���")){
			problemsolution.setUserByProSolverId(u);
			problemsolution.setSolutionDate(new Date());
		}
		problemSolutionService.saveProblemsolution(problemsolution);
		return "success";
	}
	
	/**
	 * ���������������
	 */
	public String add() throws Exception {
		return "success";
	}
	

	/**
	 * ɾ�����ݲ���
	 */
	public String remove() throws Exception {
		if(ids == null) return "success";
		for (int i = 0, size = ids.length; i < size; i++) {
			problemSolutionService.removeProblemsolution(ids[i]);
		}
		return "success";
	}

	public String search() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		if(stateWord != null){
			if(stateWord.equals("����")) stateWord = null;
			problemsolutionColl = problemSolutionService.searchstate(
					(stateWord == null) ? null : stateWord, pageBean);
			return "success";
		}
		else{
			problemsolutionColl = problemSolutionService.searchProblemsolution(
					(keyWord == null) ? null : keyWord, pageBean);
		}
		return "success";
	}
	
	/*
	public String searchState() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		if(stateWord.equals("����")) stateWord = null;
		problemsolutionColl = problemSolutionService.searchstate(
			(stateWord == null) ? null : stateWord, pageBean);
		return "success";
	}
	*/
	
	public Timestamp getTime() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		return now;
	}

}
