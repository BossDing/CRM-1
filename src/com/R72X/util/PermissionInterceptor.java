package com.R72X.util;

import java.util.Map;

import com.R72X.action.AchievementBarAction;
import com.R72X.action.AchievementLineAction;
import com.R72X.action.AchievementPieAction;
import com.R72X.action.CommonProblemAction;
import com.R72X.action.CompactAction;
import com.R72X.action.ComplaintAction;
import com.R72X.action.ContactnoteAction;
import com.R72X.action.ContactplanAction;
import com.R72X.action.CustomerAction;
import com.R72X.action.EnterpriseAction;
import com.R72X.action.EnterpriseReportBarAction;
import com.R72X.action.EnterpriseReportLineAction;
import com.R72X.action.EnterpriseReportPieAction;
import com.R72X.action.ProblemSolutionAction;
import com.R72X.action.ProductAction;
import com.R72X.action.ProductcategoryAction;
import com.R72X.action.ReportAction;
import com.R72X.action.SellerReportBarAction;
import com.R72X.action.SellerReportLineAction;
import com.R72X.action.SellerReportPieAction;
import com.R72X.action.ReportBarAction;
import com.R72X.action.ReportLineAction;
import com.R72X.action.SellsopportunityAction;
import com.R72X.action.SellswarnAction;
import com.R72X.action.UserAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PermissionInterceptor extends AbstractInterceptor{
	/*
	 * ����Action��������ط���
	 */
	public String intercept(ActionInvocation invocation) throws Exception{
		//��ȡAction����
		Object object=invocation.getAction();
		
		//��ȡActionContextʵ��
		ActionContext ac=invocation.getInvocationContext();
		Map session=ac.getSession();
		Integer loginId=(Integer)session.get("loginId");
		String loginCategory=(String)session.get("loginCategory");
		boolean isLegal=false;
		
		if(loginId!=null&&loginCategory!=null){
			int cid=getCategoryId(loginCategory);
			switch(cid){
			case 1://ϵͳ����Ա
				isLegal=object instanceof UserAction;
				break;
			case 2://���ݷ�����Ա
				isLegal=(object instanceof ReportAction)
				||(object instanceof SellerReportPieAction)
				||(object instanceof SellerReportBarAction)
				||(object instanceof SellerReportLineAction)
				||(object instanceof ReportBarAction)
				||(object instanceof ReportLineAction)
				||(object instanceof EnterpriseReportBarAction)
				||(object instanceof EnterpriseReportLineAction)
				||(object instanceof EnterpriseReportPieAction)
				||(object instanceof AchievementBarAction)
				||(object instanceof AchievementLineAction)
				||(object instanceof AchievementPieAction);
				
				break;
			case 3://������ѯ������Ա
				isLegal=object instanceof ProblemSolutionAction;
				break;
			case 4://ҵ����Ա
				isLegal=(object instanceof ProductAction)
				||(object instanceof ProductcategoryAction);
				break;
			case 5://��ͬ����Ա
				isLegal=object instanceof CompactAction;
				break;
			case 6://�ͻ�������Ա
				isLegal=(object instanceof CommonProblemAction)
				||(object instanceof ProblemSolutionAction)
				||(object instanceof ComplaintAction);
				break;
			case 7://Ͷ�ߴ�����Ա
				isLegal=object instanceof ComplaintAction;
				break;
			case 8://�ͻ�����Ա
				isLegal=object instanceof EnterpriseAction;
				break;
			case 9://������Ա
				isLegal=(object instanceof EnterpriseAction)
				||(object instanceof CustomerAction)
				||(object instanceof ContactplanAction)
				||(object instanceof ContactnoteAction)
				||(object instanceof SellsopportunityAction)
				||(object instanceof SellswarnAction)
				||(object instanceof ProductAction);
				break;			
			}
			
			if(isLegal){
				return invocation.invoke();
			}
			return "404";
		}
		else{
			ac.put("errorMessage", "����û��¼�����¼��");
			return Action.LOGIN;
		}
		
	}
	
	//public boolean isLegal(String )
	/*
	 * ��ȡ��ݶ�Ӧ�ı��
	 */
	public Integer getCategoryId(String category){
		if(category.equals("ϵͳ����Ա"))return 1;
		if(category.equals("���ݷ�����Ա"))return 2;
		if(category.equals("������ѯ������Ա"))return 3;
		if(category.equals("ҵ����Ա"))return 4;
		if(category.equals("��ͬ����Ա"))return 5;
		if(category.equals("�ͻ�������Ա"))return 6;
		if(category.equals("Ͷ�ߴ�����Ա"))return 7;
		if(category.equals("�ͻ�����Ա"))return 8;
		if(category.equals("������Ա"))return 9;
		return 0;
	}
}
