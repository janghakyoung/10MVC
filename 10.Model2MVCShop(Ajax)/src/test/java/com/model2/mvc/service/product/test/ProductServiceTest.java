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
@ContextConfiguration(locations = {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testinsertProduct() throws Exception {
		
		Product product = new Product();
		product.setProdNo(6000);
		product.setProdName("testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("testManuDate");
		product.setPrice(5000);
		product.setFileName("testFileName");
		
		productService.insertProduct(product);
		
		//product = productService.getProduct(6000);

		//==> console 확인
		System.out.println("product"+product);
		
		//==> API 확인
		Assert.assertEquals(6000, product.getProdNo());
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("testManuDate", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("testFileName", product.getFileName());
	}
	
	//@Test
	public void testFindProduct() throws Exception {
		
		Product product = new Product();
		//==> 필요하다면...
		product.setProdNo(6000);
		product.setProdName("testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("testManuDate");
		product.setPrice(5000);
		product.setFileName("testFileName");
		
		product = productService.findProduct(6000);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals("testManuDate", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals(6000, product.getProdNo());
	//	Assert.assertEquals("testRegDate", product.getRegDate());

		//Assert.assertNotNull(productService.findProduct(10001));
		//Assert.assertNotNull(productService.findProduct(10002));
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		 Product product = productService.findProduct(6000);
		Assert.assertNotNull(product);
		System.out.println("product"+product);
		
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals("testManuDate", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals(6000, product.getProdNo());

		product.setFileName("changeFileName");
		product.setManuDate("changeProductDate");
		product.setPrice(9000);
		product.setProdDetail("changeProductDetail");
		product.setProdName("changeProductName");
		product.setProdNo(100);
		
		productService.updateProduct(product);
		System.out.println("product"+product);
		
		//product = productService.findProduct(6000);
		//product = productService.findProduct(6000);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println("product"+product);
			
		//==> API 확인
		Assert.assertEquals("changeFileName", product.getFileName());
		Assert.assertEquals("changeProductDate", product.getManuDate());
		Assert.assertEquals(9000, product.getPrice());
		Assert.assertEquals("changeProductDetail", product.getProdDetail());
		Assert.assertEquals("changeProductName", product.getProdName());
		Assert.assertEquals(100, product.getProdNo());
	 }
	 
	//@Test
//	public void testCheckDuplication() throws Exception{

		//==> 필요하다면...
//		User user = new User();
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
//		
//		userService.addUser(user);
		
		//==> console 확인
//		System.out.println(userService.checkDuplication("testUserId"));
//		System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
	 	
		//==> API 확인
//		Assert.assertFalse( userService.checkDuplication("testUserId") );
//	 	Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
//	}
	
	 //==>  주석을 풀고 실행하면....
	 //@Test
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
	 	search.setSearchKeyword("10000");
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
	 
	 //@Test
	 public void testGetProductListByProductName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("연꽃");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
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