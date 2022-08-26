package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public  class ProductServiceImpl implements ProductService{
	///Field
		@Autowired
		@Qualifier("productDaoImpl")	
	private ProductDao productDao;
		public void setProductrDao(ProductDao productDao) {
			this.productDao = productDao;
		}
	
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}

	public void insertProduct(Product product) throws Exception {
		productDao.insertProduct(product);
	}

	public Product findProduct(int ProdNo) throws Exception {
		return productDao.findProduct(ProdNo);
	}

	public Map<String,Object> getProductList(Search search) throws Exception {
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}

	public boolean checkDuplication(int prodNo) throws Exception {
		boolean result=true;
		Product product=productDao.findProduct(prodNo);
		if(product != null) {
			result=false;
		}
		return result;
	}

	

}