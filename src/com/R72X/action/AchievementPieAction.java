package com.R72X.action;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.R72X.entity.Compact;
import com.R72X.service.CompactService;

public class AchievementPieAction {

	
	private CompactService compactService;
	private ArrayList<Compact> listcompact = new ArrayList<Compact>();
	private Map<String,Float> mapcompact = new HashMap<String,Float>();
	private Map<String,Float> mapfinalcompact = new HashMap<String,Float>();
	private String inputyear;
	public CompactService getCompactService() {
		return compactService;
	}
	public void setCompactService(CompactService compactService) {
		this.compactService = compactService;
	}
	public ArrayList<Compact> getListcompact() {
		return listcompact;
	}
	public void setListcompact(ArrayList<Compact> listcompact) {
		this.listcompact = listcompact;
	}
	public Map<String, Float> getMapcompact() {
		return mapcompact;
	}
	public void setMapcompact(Map<String, Float> mapcompact) {
		this.mapcompact = mapcompact;
	}
	public String getInputyear() {
		return inputyear;
	}
	public void setInputyear(String inputyear) {
		this.inputyear = inputyear;
	}
	
	
	
	
	
	public Map<String, Float> getMapfinalcompact() {
		return mapfinalcompact;
	}
	public void setMapfinalcompact(Map<String, Float> mapfinalcompact) {
		this.mapfinalcompact = mapfinalcompact;
	}





	private JFreeChart chart;

	// �����ṩ getChart() ���������ɸ÷������� JFreeChart ����
	public JFreeChart getChart() {
	
		chart = ChartFactory.createPieChart(inputyear+"��"+"��˾ҵ�����ͳ��ͼ", // ͼ�����
				getDataSet(), // ����
				true, // �Ƿ���ʾͼ��
				false, // �Ƿ���ʾ������ʾ
				false // �Ƿ����� URL
				);
		// ��������ͼ����⣬�ı�����
		chart.setTitle(new TextTitle(inputyear+"��"+"��˾ҵ�����ͳ��ͼ", new Font("����", Font.ITALIC,
						22)));
		// ȡ��ͳ��ͼ��ĵ�һ��ͼ��
		LegendTitle legend = chart.getLegend(0);
		// �޸�ͼ��������
		legend.setItemFont(new Font("����", Font.BOLD, 14));
		// ��ñ�ͼ�� Plot ����
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));	//������ֵ
		// ���ñ�ͼ�����ֵı�ǩ����
		plot.setLabelFont(new Font("����", Font.BOLD, 18));
		// �趨����͸���ȣ�0-1.0 ֮�䣩
		plot.setBackgroundAlpha(0.9f);
		// �趨ǰ��͸���ȣ�0-1.0 ֮�䣩
		plot.setForegroundAlpha(0.50f);
		return chart;
	}
	
	
	// ��ȡ����ͳ��ͼ�� Dataset
	private DefaultPieDataset getDataSet() {
	  
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		 float[] season=new float[12];
			for(int y=0;y<4;y++){
				season[y]=0;
			}
		
 	 listcompact = (ArrayList)compactService.searchEffectCompact(null, null);
 	 Integer inputyearparse = Integer.parseInt(inputyear)-1900;
 	 for(int i=0;i<listcompact.size();i++){
 	 
 		 if((listcompact.get(i).getEffectTime().getYear() == inputyearparse)){
 			 if(listcompact.get(i).getEffectTime().getMonth()>=0&&listcompact.get(i).getEffectTime().getMonth()<=2)
 			 {
 				 season[0]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()>=3&&listcompact.get(i).getEffectTime().getMonth()<=5)
 			 {
 				 season[1]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()>=6&&listcompact.get(i).getEffectTime().getMonth()<=8)
 			 {
 				 season[2]+=listcompact.get(i).getSignMoney();
 			 }
 			 if(listcompact.get(i).getEffectTime().getMonth()>=9&&listcompact.get(i).getEffectTime().getMonth()<=11)
 			 {
 				 season[3]+=listcompact.get(i).getSignMoney();
 			 }
 			
 		 }
 		 
 	 }
 	dataset.setValue("��һ����",season[0]*10000);
	dataset.setValue("�ڶ�����",season[1]*10000);
	dataset.setValue("��������",season[2]*10000);
	dataset.setValue("���ļ���",season[3]*10000);

		return dataset;
	}
	
	
	public String achievementpieChart() throws Exception {
		 
		
		   return "success";		
	}
	public String doNothing()
	{
		return "success";
	}
	

}
