package com.R72X.service;
import java.util.List;


import com.R72X.entity.ProductDAO;
import com.R72X.entity.Productcategory;
import com.R72X.util.PageBean;
import com.R72X.util.PageService;
import com.R72X.entity.ProductcategoryDAO;



public class ProductcategoryService {
    private ProductcategoryDAO productcategoryDAO;
    
    
    public void setProductcategoryDAO(ProductcategoryDAO productcategoryDAO) {
		this.productcategoryDAO = productcategoryDAO;	
		}	

   
   
    
    
    /*
     * ��������ID���Ҷ���
     */
    
    public Productcategory loadProductcategory(Integer id){
    	return  productcategoryDAO.findById(id);
    }
	

	/*
	 * ִ������������޸Ķ���ı������
	 */
    
    public void saveProductcategory(Productcategory productcategory){
    	productcategoryDAO.merge(productcategory);
    }
    
    
    
    /*
	 * ��������IDɾ�����ݿ��ж���
	 */
    
    public void removeProductcategory(Integer id){
    	
    	
    	productcategoryDAO.delete(productcategoryDAO.findById(id));
    }
    

    /*
	 * ��������ѯ�ͷ�ҳ֧��
	 * 
	 * ���ݲ�Ʒ�������ִ��ģ��ƥ����ң�ͨ��Page����ִ�з�ҳ��
	 * 
	 * @categoryName:��Ʒ�������
	 * @page:��ҳ����
	 * 
	 */
    
    
    public   List<Productcategory> searchProductcategory(String string ,PageBean page){
    	 Object[] params = null;
 		
 		//����HQL��ѯ���
 		String selectHql = null;
 		if((string == null) || (string.equals(""))) {
 			selectHql = "from Productcategory as r where r.productCategoryDesc not like '#��Ʒ�����ɾ��#%' ";
 		} else {
			selectHql = "from Productcategory as r where r.categoryName like '%" + string + "%' and r.productCategoryDesc not like '#��Ʒ�����ɾ��#%' " ;
		}
 		
 		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;
 		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Productcategory> productcategoryList = PageService.searchByPage(selectHql, countHql, params, page, productcategoryDAO);
		return productcategoryList;
 		
    }
    
    public List<Productcategory> searchProductcategoryByCompact(Integer integer, PageBean page) {
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((integer == null) || (integer.equals(""))) {
			selectHql = "from Productcategory as r where r.productCategoryDesc not like '#��Ʒ�����ɾ��#%' ";
		} else {
			selectHql = "from Productcategory as r where r.productCategoryDesc not like '#��Ʒ�����ɾ��#%' ";
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Productcategory> productcategoryList = PageService.searchByPage(selectHql, countHql, params, page, productcategoryDAO);
		return productcategoryList;
  }
    
    
    
    
}
