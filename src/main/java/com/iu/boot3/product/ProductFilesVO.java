package com.iu.boot3.product;

import com.iu.boot3.board.BoardFilesVO;
import com.iu.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductFilesVO extends FileVO {
	
	
	private Long productNum;
	
}
