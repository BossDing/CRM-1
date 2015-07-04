package com.R72X.service;
import java.util.List;



import com.R72X.entity.Product;
import com.R72X.entity.ProductDAO;
import com.R72X.util.PageBean;
import com.R72X.util.PageService;


public class ProductService {

	 private ProductDAO productDAO;
	 
	 /*  public ProductDAO getProductDAO() {
	  *	 return productDAO;
		 }
      */ 
		public void setProductDAO(ProductDAO productDAO) {
			this.productDAO = productDAO;	
			}	
   


  /*
   * ��������ID���Ҷ���
   */

    public  Product loadProduct(Integer id){
    	return  productDAO.findById(id);
    }

	/*
	 * ִ������������޸Ķ���ı������
	 */
    
    public void saveProduct(Product product){
    	 productDAO.merge(product);
    }
    
    
	/*
	 * ��������IDɾ�����ݿ��ж���
	 */
    
    public void removeProduct(Integer id){
    	productDAO.delete(productDAO.findById(id));
    }
    
    
    /*
	 * ��������ѯ�ͷ�ҳ֧��
	 * 
	 * ���ݲ�Ʒ����ִ��ģ��ƥ����ң�ͨ��Page����ִ�з�ҳ��
	 * 
	 * @productName:��Ʒ����
	 * @page:��ҳ����
	 * 
	 */
    
    
    public List<Product> searchProduct(String  string, PageBean page){
        Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((string == null) || (string.equals(""))) {
			selectHql = "from Product as r where r.productDesc not like '#��Ʒ��ɾ��#%'";
		} else {
			selectHql = "from Product as r where r.productName like '%" + string + "%' and r.productDesc not like '#��Ʒ��ɾ��#%' ";
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Product> productList = PageService.searchByPage(selectHql, countHql, params, page, productDAO);
		return productList;			
    }
    
    
    public List<Product> searchProductByCompact(Integer integer, PageBean page) {
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((integer == null) || (integer.equals(""))) {
			selectHql = "from Product as r where  r.productDesc not like '#��Ʒ��ɾ��#%' ";
		} else {
			selectHql = "from Product as r where  r.productDesc not like '#��Ʒ��ɾ��#%' and  r.productcategory.productCategoryId = "+integer;
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Product> productList = PageService.searchByPage(selectHql, countHql, params, page, productDAO);
		return productList;
}
    
    public List<Product> searchProductByProductcategoryID(Integer integer, PageBean page) {
		Object[] params = null;
		
		//����HQL��ѯ���
		String selectHql = null;
		if((integer == null) || (integer.equals(""))) {
			selectHql = "from Product as r where  r.productDesc not like '#��Ʒ��ɾ��#%' ";
		} else {
			selectHql = "from Product as r where  r.productDesc not like '#��Ʒ��ɾ��#%' and  r.productcategory.productCategoryId = "+integer;
		}
		
		//������ȡHQL���ؼ�¼���������
		String countHql = "select count(*) " + selectHql;
		
		//ִ�а�������ѯ�ͷ�ҳ֧��
		List<Product> productList = PageService.searchByPage(selectHql, countHql, params, page, productDAO);
		return productList;
}
    
}