package com.R72X.action;

import java.awt.Font;
import java.util.Collection;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.R72X.entity.Compact;
import com.R72X.entity.Product;
import com.R72X.entity.Productcategory;
import com.R72X.service.CompactService;
import com.R72X.service.ProductService;
import com.R72X.service.ProductcategoryService;
import com.R72X.util.PageBean;

public class ReportAction {
	   //���
	   private String year;
	   
	 
	
	   public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	//��Ʒ���ID
	   private String categoryId;
	   
	   
	   
	   public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	//��Ʒ����ͬ�б�
	   private List<Product> productList;
	   private List<Compact> compactList;
	   
	   //��ͬ��Service
	   private CompactService compactService;
	   
	   public CompactService getCompactService() {
			return compactService;
		}

		public void setCompactService(CompactService compactService) {
			this.compactService = compactService;
		}
	   
	   private Productcategory productcategory;
		
	   public Productcategory getProductcategory() {
			return productcategory;
		}

		public void setProductcategory(Productcategory productcategory) {
			this.productcategory = productcategory;
		}

		//��Ʒ��Service
	    private ProductService productService;
		
		public void setProductService (ProductService productService){
			this.productService=productService;
		}
	   
	   //��Ʒ����Service
	   private ProductcategoryService productcategoryService;
       
	   public void setProductcategoryService (ProductcategoryService productcategoryService){
		   this.productcategoryService=productcategoryService;
	   }
	   
	    //��Ʒ����Collection
	    private Collection<Productcategory> productcategoryColl;
		
		public void setProductcategoryColl(Collection<Productcategory> productcategoryColl){
			this.productcategoryColl=productcategoryColl;
		}
		
		public Collection<Productcategory> getProductcategoryColl(){
			return productcategoryColl;
		}
	   
		//�ҳ����еĲ�Ʒ���
		private PageBean pageBean; // ��ҳ����
		public PageBean getPageBean() {
			return pageBean;
		}
		
		public String searchcategory() throws Exception {
			if (pageBean == null) {
				pageBean = new PageBean();
			}
			productcategoryColl=productcategoryService.searchProductcategory(null,null);
			return "success";
		}
	   
		//��Ʒ������ȱ����ͼ
		private JFreeChart chart;
		public JFreeChart getChart() {
			  productcategory=productcategoryService.loadProductcategory(Integer.parseInt(categoryId)); 
			
			chart = ChartFactory.createPieChart(year+"��"+productcategory.getCategoryName()+"���Ʒ������ȱ���", // ͼ�����
					getDataSet(), // ����
					true, // �Ƿ���ʾͼ��
					false, // �Ƿ���ʾ������ʾ
					false // �Ƿ����� URL
					);
			// ��������ͼ����⣬�ı�����
			chart.setTitle(new TextTitle(year+"���"+productcategory.getCategoryName()+"���Ʒ������ȱ���", new Font("����", Font.ITALIC,
							22)));
			// ȡ��ͳ��ͼ��ĵ�һ��ͼ��
			LegendTitle legend = chart.getLegend(0);
			// �޸�ͼ��������
			legend.setItemFont(new Font("����", Font.BOLD, 14));
			
//			LegendTitle legend2 =chart.getLegend(1);
//			legend2.setItemFont(new Font("����", Font.BOLD, 14));
			
			// ��ñ�ͼ�� Plot ����
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));	//������ֵ
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));	//����1--��ֵ,2--�ٷֱ�

			// ���ñ�ͼ�����ֵı�ǩ����
			plot.setLabelFont(new Font("����", Font.BOLD, 18));
			// �趨����͸���ȣ�0-1.0 ֮�䣩
			plot.setBackgroundAlpha(0.9f);
			// �趨ǰ��͸���ȣ�0-1.0 ֮�䣩
			plot.setForegroundAlpha(0.50f);
			return chart;
		}
		
		
		private DefaultPieDataset getDataSet() {
			DefaultPieDataset dataset = new DefaultPieDataset();
			productList=productService.searchProductByProductcategoryID(Integer.parseInt(categoryId), pageBean);
			float[]  totalmoney=new float[365];
			for(int y=0;y<365;y++){
				totalmoney[y]=0;
			}
	//		productList=productService.searchProductByProductcategoryID(categoryId, pageBean);
			for(int i=0,size=productList.size();i<size;i++){
				//System.out.print(productList.get(i).getProductId());
				compactList=compactService.searchCompactByProductID(productList.get(i).getProductId(), pageBean);
				for(int j=0,size2=compactList.size();j<size2;j++){
					if(Integer.parseInt(compactList.get(j).getLastTime())<0){continue;}
					long a1=compactList.get(j).getEffectTime().getTime()/1000;//seconds
					long a2=a1/3600;//hours
					long a3=a2/24;//days
					long a4=a3/365;//years
					if((Integer.parseInt(year)-1970)==a4){
					totalmoney[i]+=compactList.get(j).getSignMoney()*10000;}
				}
			}
			
			for(int m=0,size3=productList.size();m<size3;m++){
				dataset.setValue(productList.get(m).getProductName(), totalmoney[m]);
			}
			return dataset;
		}
		
		
		public String productsaleChart() {
			return "success";
		}
		
		
}
