package com.model2.mvc.service.purchase.impl;


import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDao purchaseDao;
	private Purchase purchase;
	private ProductDao productDao;
	
    public PurchaseServiceImpl() {
}

	public Purchase addPurchase(Purchase purchase) throws Exception{
		return purchase;
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		return purchase;
	}
	
	public Map<String, Object> getPurchaseList(Search search, String string ) throws Exception{
		return new HashMap<String, Object>();
	}
	

	public Purchase updatePurchase(Purchase purchase) throws Exception{
		return purchase;
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
	}
	
	
	
	
}