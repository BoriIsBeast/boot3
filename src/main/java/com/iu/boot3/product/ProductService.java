package com.iu.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.boot3.board.BoardVO;
import com.iu.boot3.util.FileManager;
import com.iu.boot3.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private FileManager fileManager;
	
	public List<ProductVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		pager.makeNum(productMapper.getTotal(pager));
		
		return productMapper.getList(pager);
	}
	
	public int setAdd(ProductVO productVO,MultipartFile [] files)throws Exception{
		int result = productMapper.setAdd(productVO);
		
		if(files != null) {
		
		for(MultipartFile f :files) {
			
			if(f.isEmpty()) {
				continue;
			}
			
			//1. File을 hdd에 저장
			String fileName = fileManager.filesave(f, "resources/upload/product/");
			ProductFilesVO productFilesVO = new ProductFilesVO();
			productFilesVO.setProductNum(productVO.getProductNum());
			
			productFilesVO.setFileName(fileName);
			productFilesVO.setOriName(f.getOriginalFilename());
			productMapper.setFileAdd(productFilesVO);
		}		
		}
		
		return result;
	}
}
