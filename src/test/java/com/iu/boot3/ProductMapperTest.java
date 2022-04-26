package com.iu.boot3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.boot3.product.ProductMapper;
import com.iu.boot3.product.ProductVO;
@SpringBootTest
class ProductMapperTest {

	@Autowired
	private ProductMapper productMapper;
	
	@Test
	void setAddTest() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProductName("Name4");
		productVO.setProductPrice(10000);
		productVO.setProductCount(1);
		productVO.setProductDetail("Detail");
		
		int result = productMapper.setAdd(productVO);
		
		assertEquals(1, result);
	}

}
