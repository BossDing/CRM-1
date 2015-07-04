package com.R72X.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
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
import com.R72X.entity.Product;
import com.R72X.entity.Productcategory;
import com.R72X.service.CompactService;
import com.R72X.service.ProductService;
import com.R72X.service.ProductcategoryService;
import com.R72X.util.PageBean;

public class ReportLineAction {

	
	private String showtype1;
    private String showtype2;
    private String showtype3;
    private String year;
    private String productcategoryName;
    
    
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
    
	public String getProductcategoryName() {
		return productcategoryName;
	}
	public void setProductcategoryName(String productcategoryName) {
		this.productcategoryName = productcategoryName;
	}





	//��Ʒ���ID
	   private String categoryId;
	   
	   
	   
	   public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
	
	
	
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
		
		
		
		
		 private JFreeChart chart;

			// �����ṩ getChart() ���������ɸ÷������� JFreeChart ����
			public JFreeChart getChart() {
				 productcategory=productcategoryService.loadProductcategory(Integer.parseInt(categoryId)); 
				chart = ChartFactory.createLineChart(year+"���"+productcategory.getCategoryName()+"���Ʒ������ȱ���", // ͼ�����
						"��Ʒ����", // X�����
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
				
				
				
				productList=productService.searchProductByProductcategoryID(Integer.parseInt(categoryId), pageBean);
//				for(int e=0;e<productList.size();e++){
//					System.out.println(productList.get(e).getProductName());
//				}
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
						totalmoney[i]+=compactList.get(j).getSignMoney()*10000;
						productcategoryName = compactList.get(j).getProduct().getProductcategory().getCategoryName();
						}
					}
				}
				String[] line={"��Ʒ����"};
				ArrayList<String> category=new ArrayList<String>();
				for(int m=0,size3=productList.size();m<size3;m++){
					 category.add(productList.get(m).getProductName());
				}
				DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
				
				for(int m=0,size3=productList.size();m<size3;m++){
					
				         for (int j = 0; j < category.size(); j++) {
					             dataSet.addValue(totalmoney[j], line[0],category.get(j));
					         }
				   }
				
				return dataSet;
		}
		
		
		
		
		public String productsaleLineChart() {
			return "success";
		}
		
}
