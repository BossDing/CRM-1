package com.R72X.action;

import java.util.Collection;

import com.R72X.entity.Commonproblem;
import com.R72X.service.CommonProblemService;
import com.R72X.util.PageBean;

public class CommonProblemAction {
	private Integer id;
	private Integer[] ids;
	private Commonproblem commonproblem;
	private Collection<Commonproblem> commonproblemColl;
	private PageBean pageBean; // ��ҳ����
	private CommonProblemService commonproblemService;
	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public CommonProblemService getCommonproblemService() {
		return commonproblemService;
	}

	public void setCommonproblemService(
			CommonProblemService commonproblemService) {
		this.commonproblemService = commonproblemService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Collection<Commonproblem> getCommonproblemColl() {
		return commonproblemColl;
	}

	public void setCommonproblemColl(Collection<Commonproblem> commonproblemColl) {
		this.commonproblemColl = commonproblemColl;
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

	public Commonproblem getCommonproblem() {
		return commonproblem;
	}

	public void setCommonproblem(Commonproblem commonproblem) {
		this.commonproblem = commonproblem;
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
		commonproblem = commonproblemService.loadCommonproblem(id);
		return "success";
	}
	
	/**
	 * ���������������
	 */
	public String add() throws Exception {
		return "success";
	}

	/**
	 * �����ͱ༭�����ݱ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		commonproblemService.saveCommonproblem(commonproblem);
		return "success";
	}

	/**
	 * ɾ�����ݲ���
	 */
	public String remove() throws Exception {
		if(ids.length == 0) return "success";
		for (int i = 0, size = ids.length; i < size; i++) {
			commonproblemService.removeCommonproblem(ids[i]);
		}
		return "success";
	}

	public String search() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		commonproblemColl = commonproblemService.searchCommonProblem(
				(keyWord == null) ? null : keyWord, pageBean);
		return "success";
	}
}
