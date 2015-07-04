package com.R72X.action;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.R72X.entity.Compact;
import com.R72X.entity.Product;
import com.R72X.entity.Productcategory;
import com.R72X.service.ProductService;
import com.R72X.service.ProductcategoryService;

import com.R72X.util.PageBean;
import com.R72X.util.PageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProductAction {

	private ProductService productService;
	
	public void setProductService (ProductService productService){
		this.productService=productService;
	}
	
	
	private ProductcategoryService productcategoryService;
	   
	   
	   public void setProductcategoryService (ProductcategoryService productcategoryService){
		   this.productcategoryService=productcategoryService;
	   }
	
	/*---------------------------------------------------------------
	 * 
	 * �����������Ҫ��Ӧ��get/set����
	 * 
	 * --------------------------------------------------------------
	 */
	
	//�Ե����������ʱ��ʾ�����Ψһ�Ա�ʶ�����ݿ�������
	private Integer id;
	//��һ��������ʱ��ʾ��ѡ�����Ψһ�Ա�ʶ�����ݿ������������Զ���������
	private Integer[] ids;
	private Product product;
    private String KeyWord;
    
    ArrayList<Product> productAL = new ArrayList<Product>();
    ArrayList<Product> productALDelete = new ArrayList<Product>();
    ArrayList<Product> productALNotDelete = new ArrayList<Product>();
	
	public ArrayList<Product> getProductAL() {
		return productAL;
	}

	public void setProductAL(ArrayList<Product> productAL) {
		this.productAL = productAL;
	}

	public ArrayList<Product> getProductALDelete() {
		return productALDelete;
	}

	public void setProductALDelete(ArrayList<Product> productALDelete) {
		this.productALDelete = productALDelete;
	}

	public ArrayList<Product> getProductALNotDelete() {
		return productALNotDelete;
	}

	public void setProductALNotDelete(ArrayList<Product> productALNotDelete) {
		this.productALNotDelete = productALNotDelete;
	}

	public void setKeyWord(String KeyWord){
		this.KeyWord=KeyWord;
	}
	
	public String getKeyWord(){
		return KeyWord;
	}
	
	//private String selectbeans;	//��Ʒ�����ʾ��
	
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
	
	public Product getProduct(){
		return product;
	}
	
	public void setProduct(Product product){
		this.product=product;
	}
	
	private Collection<Productcategory> productcategoryColl;
	
	public void setProductcategoryColl(Collection<Productcategory> productcategoryColl){
		this.productcategoryColl=productcategoryColl;
	}
	
	public Collection<Productcategory> getProductcategoryColl(){
		return productcategoryColl;
	}
	
	// ��ѯ������ϣ����ڽ�����ʾ����ONGL���ʽ�п�ֱ�����ñ�����
	private Collection<Product> productColl;
	
	public Collection<Product> getProductColl(){
		return productColl;
	}
	
	public void setProductColl(Collection<Product> productColl){
		this.productColl=productColl;
	}
	
	
	/*-----------------------------------------------------------------
	 * 
	 * ��ɾ��Ĵ���
	 * 
	 * ----------------------------------------------------------------
	 */
	  
	// �����ͱ༭�����ݶ�ȡ����
	public String load() throws Exception {
		if (id == null) {
			searchcategory();
			return "success";
		}
		searchcategory();
		product=productService.loadProduct(id);
		
		return "success";
	}
	
	// �����ͱ༭�����ݱ������
	public String save() throws Exception {
		if((product.getProductTime()==null)||(product.getProductTime().equals("")))
		{product.setProductTime(new Timestamp(System.currentTimeMillis()));}
		
		productService.saveProduct(product);
		return "success";
		
	}
	
	
	// ɾ�����ݲ���
	public String remove() throws Exception {
		if(ids==null){return "success";}
		String b="#��Ʒ��ɾ��#";
		for (int i = 0, size = ids.length; i < size; i++) {
			product=productService.loadProduct(ids[i]);
			b+=product.getProductDesc();
			product.setProductDesc(b);
			productService.saveProduct(product);
			//productService.removeProduct(ids[i]);	
		}
		return "success";
	}
	
	
	/*--------------------------------------------------------------------
	 * 
	 * �ۺϲ�ѯ��֧�ֲ�ѯ��������ҳ
	 * 
	 * -------------------------------------------------------------------
	 */

	
	
	
	/*
	 * ��ѯ��������
	 */
	
	
	
	private PageBean pageBean; // ��ҳ����
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	/*
	 * ��ѯ��Ʒ��Ϣ��֧�������ͷ�ҳ
	 */
	
	
	public String search() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		
		String  a;
		productColl=productService.searchProduct((KeyWord==null)?null:KeyWord,pageBean);
//		productAL= (ArrayList)productColl;
//		for(int i=0;i<productAL.size();i++){
//			a=productAL.get(i).getProductDesc();
//			if(a.compareTo("#��Ʒ��ɾ��#")==0){productAL.remove(i);}
		
		
		
		
		
		
		return "success";
	}
	
	public String searchcategory() throws Exception {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		productcategoryColl=productcategoryService.searchProductcategory(null,null);
		return "success";
	}
	
	
}
