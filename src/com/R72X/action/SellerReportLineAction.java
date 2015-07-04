package com.R72X.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.R72X.entity.Compact;
import com.R72X.entity.User;
import com.R72X.service.CompactService;
import com.R72X.service.UserService;
import com.R72X.util.PageBean;

public class SellerReportLineAction {

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
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Compact> getCompactList() {
		return compactList;
	}
	public void setCompactList(List<Compact> compactList) {
		this.compactList = compactList;
	}
	public CompactService getCompactService() {
		return compactService;
	}
	public void setCompactService(CompactService compactService) {
		this.compactService = compactService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
		chart = ChartFactory.createLineChart(year+"��������Ա"+user.getUserName()+"��ͬҵ��ͳ��ͼ", // ͼ�����
				year+"��������Ա"+user.getUserName()+"��ͬҵ��ͳ��ͼ", // X�����
				"��Ԫ��", // Y�����
				createDataSet(), // ��ͼ���ݼ�
				PlotOrientation.VERTICAL, // ���Ʒ���
				true, // �Ƿ���ʾͼ��
				true, // �Ƿ���ñ�׼������
				false // �Ƿ����ɳ�����
				);

		// ���ñ�������
		chart.getTitle().setFont(new Font("����", Font.ITALIC,22));
		// ����ͼ���������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.setBackgroundPaint(new Color(192, 228, 106)); // ���ñ���ɫ
		// ��ȡ��ͼ������
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(new Font("����", Font.BOLD, 14));
		// ���ú�������
		plot.getDomainAxis().setTickLabelFont(new Font("����", Font.BOLD, 14));
		// ������������ֵ����
		plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 14));
		// ������������
		plot.setBackgroundPaint(Color.WHITE);
		// ���û�ͼ������ɫ
		plot.setRangeGridlinePaint(Color.RED);
		// ����ˮƽ���򱳾�����ɫ
		plot.setRangeGridlinesVisible(true);
		// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue
		plot.setDomainGridlinePaint(Color.RED);
		// ���ô�ֱ���򱳾�����ɫ
		plot.setDomainGridlinesVisible(true);
		// �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse
		// ��ȡ���߶���
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		BasicStroke realLine = new BasicStroke(1.6f); // ����ʵ��
		float dashes[] = { 8.0f }; // ������������
		BasicStroke brokenLine = new BasicStroke(1.6f, // ������ϸ
				BasicStroke.CAP_SQUARE, // �˵���
				BasicStroke.JOIN_MITER, // �۵���
				8.f, // �۵㴦��취
				dashes, // ��������
				0.0f); // ����ƫ����
		renderer.setSeriesStroke(1, brokenLine);
		// �������߻���
		renderer.setSeriesStroke(2, brokenLine);
		// �������߻���
		renderer.setSeriesStroke(3, realLine);
		// ����ʵ�߻���
		return chart;
	}

	/**
	 * ��ȡһ����ʾ�õ�������ݼ�����
	 * 
	 * @return
	 */
	public CategoryDataset createDataSet() {
		// ͼ������
		
		
		compactList=compactService.searchCompactBySellerID(Integer.parseInt(userId),pageBean);
    	// System.out.print(compactList.get(0).getCompactId());
    	 float[]  seasonmoney=new float[4];
			for(int y=0;y<4;y++){
				seasonmoney[y]=0;
			}
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
		
		
		
		 
		
		String[] line={"������Ա"+user.getUserName()};

		 
		// ���
		String[] category={"��һ����","�ڶ�����","��������","���ļ���"};
	
		
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// ʹ��ѭ�������ݼ������������
		
		 
		  int i=0;
	
			for (int j = 0; j < category.length; j++) {
				dataSet.addValue(seasonmoney[j]*10000, line[0],
						category[j]);
			}
			i++;
		
		
		return dataSet;
	}
	
	
	
	 public String sellerLineChart(){
		 return "success";
	 }
	
	
}
