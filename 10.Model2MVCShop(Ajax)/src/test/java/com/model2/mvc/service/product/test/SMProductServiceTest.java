package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class SMProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testInsertProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("testProductName");
		product.setProdDetail("testProductDetail");
		product.setManuDate("2022-08-01");
		product.setPrice(50000);
		product.setFileName("testProduct.image");
				
		productService.insertProduct(product);
		
		//product = productService.findProduct(10050);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("2022-08-01", product.getManuDate());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals("testProduct.image", product.getFileName());
	}
	
	//@Test
	public void testFindProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("testProductName");
		product.setProdDetail("testProductDetail");
		product.setManuDate("2022-08-01");
		product.setPrice(50000);
		product.setFileName("testProduct.image");
		
		product = productService.findProduct(10059);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("2022-08-01", product.getManuDate());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals("testProduct.image", product.getFileName());

		//Assert.assertNotNull(productService.findProduct(10001));
		//Assert.assertNotNull(productService.findProduct(10007));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.findProduct(10059);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProductDetail", product.getProdDetail());
		Assert.assertEquals("2022-08-01", product.getManuDate());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals("testProduct.image", product.getFileName());

		product.setProdName("changeProductName");
		product.setProdDetail("changeProductDetail");
		product.setManuDate("2022-08-02");
		product.setPrice(55555);
		product.setFileName("changeFileName.image");
		
		productService.updateProduct(product);
		
		product = productService.findProduct(10059);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println(product);
			
		//==> API 확인
		Assert.assertEquals("changeProductName", product.getProdName());
		Assert.assertEquals("changeProductDetail", product.getProdDetail());
		Assert.assertEquals("2022-08-02", product.getManuDate());
		Assert.assertEquals(55555, product.getPrice());
		Assert.assertEquals("changeFileName.image", product.getFileName());

	 }
	
	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testFindProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProductNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("admin");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 @Test
	 public void testGetProductListByProductName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("자전거");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}