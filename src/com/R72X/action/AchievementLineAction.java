package com.R72X.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.R72X.entity.Compact;
import com.R72X.service.CompactService;

public class AchievementLineAction {
	private CompactService compactService;
	private Map<String,Float> mapcompact = new HashMap<String,Float>();
    private ArrayList<Compact> listcompact = new ArrayList<Compact>();
	private String inputyear;
    private String showtype1;
    private String showtype2;
    private String showtype3;
	public CompactService getCompactService() {
		return compactService;
	}
	public void setCompactService(CompactService compactService) {
		this.compactService = compactService;
	}
	public Map<String, Float> getMapcompact() {
		return mapcompact;
	}
	public void setMapcompact(Map<String, Float> mapcompact) {
		this.mapcompact = mapcompact;
	}
	public ArrayList<Compact> getListcompact() {
		return listcompact;
	}
	public void setListcompact(ArrayList<Compact> listcompact) {
		this.listcompact = listcompact;
	}
	public String getInputyear() {
		return inputyear;
	}
	public void setInputyear(String inputyear) {
		this.inputyear = inputyear;
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
    
	private JFreeChart chart;

	// �����ṩ getChart() ���������ɸ÷������� JFreeChart ����
	public JFreeChart getChart() {
		chart = ChartFactory.createLineChart(inputyear+"��"+"��˾ҵ�����ͳ��ͼ", // ͼ�����
				inputyear+"��"+"��˾ҵ�����ͳ��ͼ", // X�����
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
		
		 float[] month=new float[12];
			for(int y=0;y<12;y++){
				month[y]=0;
			}
		
 	 listcompact = (ArrayList)compactService.searchEffectCompact(null, null);
 	 Integer inputyearparse = Integer.parseInt(inputyear)-1900;
 	 for(int i=0;i<listcompact.size();i++){
 	 
 		 if((listcompact.get(i).getEffectTime().getYear() == inputyearparse)){
 			 if(listcompact.get(i).getEffectTime().getMonth()==0)
 			 {
 				 month[0]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==1)
 			 {
 				 month[1]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==2)
 			 {
 				 month[2]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==3)
 			 {
 				 month[3]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==4)
 			 {
 				 month[4]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==5)
 			 {
 				 month[5]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==6)
 			 {
 				 month[6]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==7)
 			 {
 				 month[7]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==8)
 			 {
 				 month[8]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==9)
 			 {
 				 month[9]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==10)
 			 {
 				 month[10]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()==11)
 			 {
 				 month[11]+=listcompact.get(i).getSignMoney();
 			 }
 		 }
 		 
 	 }

		
		
		
		 
		
		String[] line={inputyear+"��"+"��˾ҵ��"};

		
		 
		// �·�
		String[] category={"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��"};
	
		
		
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// ʹ��ѭ�������ݼ������������
		

		  int i=0;
	
			for (int j = 0; j < category.length; j++) {
				dataSet.addValue(month[j]*10000, line[0],
						category[j]);
			}
			i++;
		
		
		return dataSet;
	}
	
	
	public String achievementLineChart() throws Exception { //���ɱ�״ͼ�ķ���
    	
   	
		 
		   return "success";
		
	}
    
}
