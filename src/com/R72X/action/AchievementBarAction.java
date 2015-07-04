package com.R72X.action;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.R72X.entity.Compact;

import com.R72X.service.CompactService;


public class AchievementBarAction {
	private CompactService compactService;
	private Map<String,Float> mapcompact = new HashMap<String,Float>();
	private Map<String,Float> mapfinalcompact = new HashMap<String,Float>();
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
	
	public Map<String, Float> getMapfinalcompact() {
		return mapfinalcompact;
	}
	public void setMapfinalcompact(Map<String, Float> mapfinalcompact) {
		this.mapfinalcompact = mapfinalcompact;
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
	public JFreeChart getChart() throws Exception {
		chart = ChartFactory.createBarChart(inputyear+"��"+"��˾ҵ�����ͳ��ͼ", 
                "����",
                "��Ԫ��",
                getDataSet(),
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

		//ͼ�����
		Font font = new Font("����", Font.BOLD, 16);
		TextTitle title = new TextTitle(inputyear+"��"+"��˾ҵ�����ͳ��ͼ", new Font("����", Font.ITALIC,22));
		chart.setTitle(title); //����

		CategoryPlot plot = chart.getCategoryPlot();
		
		//��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		//Ĭ�ϵ�������ʾ�������У�ͨ����������ɵ������ֵ���ʾ
		//ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ����������
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		plot.setRenderer(renderer);
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		//X�������ϵ�����
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 14));
		//X���������
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 14));
		
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		//Y�������ϵ�����
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 14));
		//Y���������
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 14));
		//ͼ��ײ�����
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 14));
		
		return chart;
	}
	
	
	


	 private CategoryDataset getDataSet() throws Exception { 
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();

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
 		dataset.setValue(month[0]*10000,"1��","1��");
		dataset.setValue(month[1]*10000,"2��","2��");
		dataset.setValue(month[2]*10000,"3��","3��");	
		dataset.setValue(month[3]*10000,"4��","4��");
 		dataset.setValue(month[4]*10000,"5��","5��");
		dataset.setValue(month[5]*10000,"6��","6��");
		dataset.setValue(month[6]*10000,"7��","7��");	
		dataset.setValue(month[7]*10000,"8��","8��");
 		dataset.setValue(month[8]*10000,"9��","9��");
		dataset.setValue(month[9]*10000,"10��","10��");
		dataset.setValue(month[10]*10000,"11��","11��");	
		dataset.setValue(month[11]*10000,"12��","12��");

         return dataset; 
     } 	
	 
	 
	 public String achievementBarChart() throws Exception { //���ɱ�״ͼ�ķ���
	    	

		 
		   return "success";
		
	}
}


