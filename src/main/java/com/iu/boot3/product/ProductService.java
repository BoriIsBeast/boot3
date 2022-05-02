package com.iu.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.boot3.board.BoardVO;
import com.iu.boot3.util.FileManager;
import com.iu.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private FileManager fileManager;
	
	public int setFileDelete(ProductFilesVO productFilesVO)throws Exception{
		
		//db에서 조회
		productFilesVO = productMapper.getFileDetail(productFilesVO);
		
		
		//hdd삭제
		//boolean result = fileManager.remove("/resources/upload/product/", productFilesVO.getFileName());
		//db삭제
		/*
		 * if(result) { productMapper.setFileDelete(productFilesVO); }
		 */
		
		
		int check = productMapper.setFileDelete(productFilesVO);
		
		if(check>0) {
			boolean result = fileManager.remove("/resources/upload/product/", productFilesVO.getFileName());
		}
		
		
		
		return check;
	}
	
	public int setUpdate(ProductVO productVO, MultipartFile [] multipartFiles)throws Exception{
		int result = productMapper.setUpdate(productVO);
		
		if(multipartFiles != null) {
			
			for(MultipartFile multipartFile : multipartFiles) {
				if(multipartFile.isEmpty()) {
					continue;
				}
				ProductFilesVO productFilesVO = new ProductFilesVO();
				String fileName = fileManager.filesave(multipartFile, "/resources/upload/product/");
				productFilesVO.setFileName(fileName);
				productFilesVO.setOriName(multipartFile.getOriginalFilename());
				productFilesVO.setProductNum(productVO.getProductNum());
				result = productMapper.setFileAdd(productFilesVO);
			}
			
			return result;
		}
		
		
		
		return productMapper.setUpdate(productVO);
	}
	
	public ProductVO getDetail(ProductVO productVO)throws Exception{
		
		return productMapper.getDetail(productVO);
	}
	
	public List<ProductVO> getManage(ProductVO productVO)throws Exception{
		
		return productMapper.getManage(productVO);
	}

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
