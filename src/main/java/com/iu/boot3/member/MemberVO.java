package com.iu.boot3.member;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank
	private String id;
	@Size(min =3 ,max=8)
	private String pw;
	
	private String checkPw;
	
	@NotBlank
	private String name;
	@Email(message = "이메일형식으로 쓰세요")
	private String email;
	private String phone;
	
	private List<MemberFilesVO> filesVO; //
	private List<RoleVO> roleVOs;
}
