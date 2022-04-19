package com.iu.boot3.util;

import lombok.Data;

@Data
public class Pager {

	// DB에서 몇개씩 조회
	private Integer perPage;
	//DB에서 조회할 시작 인덱스 번호
	private Integer startRow;
	//마지막 번호
	private Integer lastRow;
	
	//페이지 번호(파라미터의 값)
	private Integer pn;
	
	//------------------검색 사용 변수
	private String search;
	private String kind;
	
	//----------------------------jsp 사용변수
		private Long startNum;
		private Long lastNum;
		
		private boolean pre;
		private boolean next;
		
		
		public void makeNum(Long totalCount) {
			
			//전체 Page의 갯수 구하기
			Long totalPage = totalCount/this.getPerPage();
			if(totalCount%this.getPerPage() != 0) {
				totalPage++;
			}
			
			
			//블럭당 갯수
			Long perBlock=5L;
			
			// 전체 Block의 갯수 구하기
			Long totalBlock = totalPage/perBlock;
			if(totalPage%perBlock !=0) {
				totalBlock++;
			}
			
			Long curBlock = this.getPn()/perBlock;
			if(this.getPn()%perBlock != 0) {
				curBlock++;
			}
			
			this.startNum = (curBlock-1)*perBlock+1;
			this.lastNum = curBlock*perBlock;
			
			// 이전, 다음 블럭 유무
			
			this.pre=false;
			if(curBlock>1) {
				//현재 block이 2, 3, 4 ....
				this.pre=true;
			}
			
			this.next=false;
			if(totalBlock > curBlock) {
				this.next=true;
			}
			
			//현재 블럭이 마지막 블럭번호와 같다면
			if(curBlock == totalBlock) {
				this.lastNum=totalPage;
			}
			
			//검색결과가 없어서 Total이 0일때
			if(totalCount==0) {
				this.lastNum=0L;
			}
			
		}
	
	public void makeRow() {
		//pn : 1, perPage : 10, startRow : 0
		//pn : 2, perPage : 10, startRow : 10 
		//pn : 3, perPage : 10, startRow : 20 
		this.startRow = (this.getPn()-1)*this.getPerPage();
		this.lastRow = this.getPn()*this.getPerPage();
	}
	
	public Integer getPn() {
		if(this.pn == null || this.pn<1) {
			this.pn = 1;
		}
		return this.pn;
	}
	
	public Integer getPerPage() {
		if(this.perPage == null||this.perPage< 1 ) {
			this.perPage = 10;
			
		}
		return this.perPage;
	}
	
	public String getSearch() {
		
		if(this.search == null) {
			this.search = "";
		}
		
		return search;
	}
}
