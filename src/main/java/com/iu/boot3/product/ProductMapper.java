package com.iu.boot3.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iu.boot3.util.Pager;

@Mapper
public interface ProductMapper {
	
	public Long getTotal(Pager pager)throws Exception;

	public List<ProductVO> getList(Pager pager)throws Exception;
	
	public int setAdd(ProductVO productVO)throws Exception;
	
	public int setFileAdd(ProductFilesVO productFilesVO)throws Exception;
	
	
}
