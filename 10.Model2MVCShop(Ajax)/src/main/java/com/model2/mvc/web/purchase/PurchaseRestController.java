package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


@Controller
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	private Product product;
	
	///Constructor
	public PurchaseRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	//@RequestMapping("/addProduct.do")
	//public String addProduct() throws Exception {
	@RequestMapping( value="json/addProduct", method=RequestMethod.GET )
	public String addProduct() throws Exception {
		System.out.println("[  insertProduct.do() start.......]");
		
		return  "forward:/product/AddProduct.jsp";
	}
	
	@RequestMapping( value="json/addProduct", method=RequestMethod.POST )
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
		System.out.println("[  insertProduct.do() start.......]");
		
		return  "forward:/product/AddProduct.jsp";
	}
	
	//@RequestMapping("/GetProduct.do")
	//public String GetProduct(@RequestParam("prodNo") int prodNo , Model model ) throws Exception {
		@RequestMapping( value="json/GetProduct", method=RequestMethod.GET )
		public String getUser( @RequestParam("prodNo") int prodNo , Model model ) throws Exception {
		System.out.println("[  GetProduct() start.......]");
		
		Product product = productService.findProduct(prodNo);
		
		model.addAttribute("product", product);
		
		System.out.println("[ GetProduct() end.............]\n");
		return "forward:/product/getProduct.jsp";
	}
			
	//@RequestMapping("/updateProductView.do")
	//public String updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
		@RequestMapping( value="json/updateProductView", method=RequestMethod.GET )
		public String updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
			
		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.findProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	//@RequestMapping("/updateProduct.do")
	//public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{
		@RequestMapping( value="json/updateProduct", method=RequestMethod.GET )
		public String updateProduct() throws Exception{
		System.out.println("/product/updateProduct : GET");
		//Business Logic
		productService.updateProduct(product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
		@RequestMapping( value="json/updateProduct", method=RequestMethod.POST )
		public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{
		System.out.println("/product/updateProduct : POST");
		//Business Logic
		productService.updateProduct(product);
		
		return "forward:/product/updateProduct.jsp";
	}
		
	//@RequestMapping("/listProduct.do")
//	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{


		@RequestMapping( value="json/listProduct")
		public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
				
		System.out.println("/product/listProduct : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		} 
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
	
	
}