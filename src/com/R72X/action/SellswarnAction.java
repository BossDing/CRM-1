package com.R72X.action;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.R72X.entity.Sellswarn;
import com.R72X.service.SellswarnService;
import com.R72X.util.PageBean;
import com.opensymphony.xwork2.ActionContext;

public class SellswarnAction {
	/**
	 * Springע��Service
	 */
	private SellswarnService sellswarnService;
	
	private Integer id;
	private Integer[] ids;
	private Sellswarn sellswarn;
	
	/**
	 * �����жϵ�ǰ��ʾ�������ѵ�����
	 */
	private Boolean flag;
	private Boolean[] isWarning;
	private String keyWord;
	
	private Collection<Sellswarn> sellswarnColl;
	
	private PageBean pageBean;
	
	public void setSellswarnService(SellswarnService sellswarnService){
		this.sellswarnService = sellswarnService;
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
	public Sellswarn getSellswarn() {
		return sellswarn;
	}
	public void setSellswarn(Sellswarn sellswarn) {
		this.sellswarn = sellswarn;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Boolean[] getIsWarning() {
		return isWarning;
	}
	public void setIsWarning(Boolean[] isWarning) {
		this.isWarning = isWarning;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Collection<Sellswarn> getSellswarnColl(){
		return sellswarnColl;
	}
	public void setSellswarnColl(Collection<Sellswarn> sellswarnColl){
		this.sellswarnColl = sellswarnColl;
	}
	public PageBean getPageBean(){
		return pageBean;
	}
	public void setPageBean(PageBean pageBean){
		this.pageBean = pageBean;
	}
	
	/**
	 * ����
	 * @return
	 * @throws Exception
	 */
	public String load() throws Exception{
		if (id == null){
			return "success";
		}
		sellswarn = sellswarnService.loadSellswarn(id);
		return "success";
	}
	
	/**
	 * ���ӻ��޸�
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		sellswarnService.saveSellswarn(sellswarn);
		return "success";
	}
	
	/**
	 * ɾ��
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception{
		if (ids == null)
			return "success";
		for (int i = 0, size = ids.length; i < size; i++){
			sellswarnService.removeSellswarn(ids[i]);
		}
		return "success";
	}
	
	/**
	 * ���ݹؼ��ֲ�����ؼ�¼
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception{
		if (pageBean == null){
			pageBean = new PageBean();
		}
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		if (!session.containsKey("flag")){
			session.put("flag", true);
		}
		if (flag == null){
			flag = (Boolean)session.get("flag");
		}
		else{
			session.put("flag", flag);
			context.setSession(session);
		}
		if (flag == true) {
			//��flag==true�������δ�����������ѣ�����Ĭ��������ʾ
			sellswarnColl = sellswarnService.searchSellswarnById(
					((keyWord == null)?null:keyWord), pageBean);
		}
		else {
			//��flag==false��������ѹ����������ѣ���������ʱ�䵹����ʾ
			sellswarnColl = sellswarnService.searchSellswarnByDate(
					((keyWord == null)?null:keyWord), pageBean);
			isWarning = new Boolean[sellswarnColl.size()];
			
			//��ȡ�������ڣ�����������ʱ����бȽ�
			Date date = new Date();
			Timestamp t = new Timestamp(date.getTime());
			t.setHours(0);
			t.setMinutes(0);
			t.setSeconds(0);
			t.setNanos(0);
			int i = 0;
			//��ÿ��sellswarnColl��sellswarn��warnDate���бȽ�
			//���ѱȽϽ����¼��isWarning�У����ڸ������ѵ��쵽
			//�ڵ��������ѡ�
			for (Sellswarn s : sellswarnColl){
				if ( t.compareTo(s.getWarnDate()) == 0){
					isWarning[i] = true;
					
				}
				else
					isWarning[i] = false;
				i++;
			}
		}
		return "success";
	}
}