package com.R72X.action;

import java.awt.Font;
import java.util.Collection;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.R72X.entity.Compact;
import com.R72X.entity.User;
import com.R72X.service.CompactService;
import com.R72X.service.UserService;
import com.R72X.util.PageBean;

public class SellerReportPieAction {
	private UserService userService;  //ͨ��userService���������Ա�б�
	private Collection<User> userColl; //
	private PageBean pageBean; // ��ҳ����
	private JFreeChart chart;
	private String year;//��������
	private String userId;//������Ա��id
	 private List<Compact> compactList;
	 private CompactService compactService;
	 private User user;
	private String inputyear;
    private String showtype1;
    private String showtype2;
    private String showtype3;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CompactService getCompactService() {
		return compactService;
	}

	public void setCompactService(CompactService compactService) {
		this.compactService = compactService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Collection<User> getUserColl() {
		return userColl;
	}

	public void setUserColl(Collection<User> userColl) {
		this.userColl = userColl;
	}
	
	public String searchSeller() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		userColl=userService.searchUserseByCompact("������Ա", null);
		
		return "success";
	}
	
	
	public List<Compact> getCompactList() {
		return compactList;
	}

	public void setCompactList(List<Compact> compactList) {
		this.compactList = compactList;
	}

	public String getShowtype1() {
		return showtype1;
	}

	public void setShowtype1(String showtype1) {
		this.showtype1 = showtype1;
	}

	public String getShowtype2() {
		return showtype2;
	}

	public void setShowtype2(String showtype2) {
		this.showtype2 = showtype2;
	}

	public String getShowtype3() {
		return showtype3;
	}

	public void setShowtype3(String showtype3) {
		this.showtype3 = showtype3;
	}

	public JFreeChart getChart() {
		user=userService.loadUser(Integer.parseInt(userId));
		
		chart = ChartFactory.createPieChart(year+"��������Ա"+user.getUserName()+"��ͬҵ��ͳ��ͼ", // ͼ�����
				getDataSet(), // ����
				true, // �Ƿ���ʾͼ��
				false, // �Ƿ���ʾ������ʾ
				false // �Ƿ����� URL
				);
		// ��������ͼ����⣬�ı�����
		chart.setTitle(new TextTitle(year+"��������Ա"+user.getUserName()+"��ͬҵ��ͳ��ͼ", new Font("����", Font.ITALIC,
						22)));
		// ȡ��ͳ��ͼ��ĵ�һ��ͼ��
		LegendTitle legend = chart.getLegend(0);
		// �޸�ͼ��������
		legend.setItemFont(new Font("����", Font.BOLD, 14));
		// ��ñ�ͼ�� Plot ����
		PiePlot plot = (PiePlot) chart.getPlot();
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));	//����1--��ֵ,2--�ٷֱ�
		// ���ñ�ͼ�����ֵı�ǩ����
		plot.setLabelFont(new Font("����", Font.BOLD, 18));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));	//������ֵ
		// �趨����͸���ȣ�0-1.0 ֮�䣩
		plot.setBackgroundAlpha(0.9f);
		// �趨ǰ��͸���ȣ�0-1.0 ֮�䣩
		plot.setForegroundAlpha(0.50f);
		return chart;
	}
	
	
	// ��ȡ����ͳ��ͼ�� Dataset
	private DefaultPieDataset getDataSet() {
	  
  
		

		
		//��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		

		
		
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		 compactList=compactService.searchCompactBySellerID(Integer.parseInt(userId),pageBean);
	    	// System.out.print(compactList.get(0).getCompactId());
	    	 float[]  seasonmoney=new float[4];
				for(int y=0;y<4;y++){
					seasonmoney[y]=0;
				}
		//	 	 Integer inputyearparse = Integer.parseInt(inputyear)-1900;	
			for(int i=0;i<compactList.size();i++){
				if(Integer.parseInt(compactList.get(i).getLastTime())<0){continue;}

				long a1=compactList.get(i).getEffectTime().getTime()/1000;//seconds
				long a2=a1/3600;//hours
				long a3=a2/24;//days
				long a4=a3/365;//years
				if((Integer.parseInt(year)-1970)==a4){
					int month=compactList.get(i).getEffectTime().getMonth()+1;
					
					if(month>=1&&month<=3)
					{seasonmoney[0]+=compactList.get(i).getSignMoney();}
					if(month>=4&&month<=6)
					{seasonmoney[1]+=compactList.get(i).getSignMoney();}
					if(month>=7&&month<=9)
					{seasonmoney[2]+=compactList.get(i).getSignMoney();}
					if(month>=10&&month<=12)
					{seasonmoney[3]+=compactList.get(i).getSignMoney();}
					
				}
				
				
				
			}
			dataset.setValue("��һ����",seasonmoney[0]*10000);
			dataset.setValue("�ڶ�����",seasonmoney[1]*10000);
			dataset.setValue("��������",seasonmoney[2]*10000);	
			dataset.setValue("���ļ���",seasonmoney[3]*10000);

			return dataset;
	
	
	}
	
	
	 public String sellerPieChart(){
		 return "success";
	 }
	
	
}
