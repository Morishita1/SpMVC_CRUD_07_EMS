package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.biz.ems.model.EmailVO;

public interface EmailDao {

	@Select(" SELECT * FROM tbl_ems ")
	public List<EmailVO> selectAll();
	public EmailVO findBySeq(long ems_seq);
	
	public List<EmailVO> fileByFrom(String ems_from_email);
	public List<EmailVO> fileByTo(String ems_to_email);
	
	/*
	 * 매개변수가 2개 이상일 경우는
	 * 반드시 @Param으로 변수 이름을 명시해 주어야 한다.
	 */
	public List<EmailVO> fileByFromAndTo(@Param("ems_from_email") String ems_from_email,@Param("ems_to_email")  String ems_to_email);
	
	@SelectKey(keyProperty = "ems_seq", statement = " SELECT SEQ_EMS.NEXTVAL FROM DUAL ", resultType = Long.class,before = true)
	@InsertProvider(type = EmailSQL.class, method = "Email_insert_sql")
	public int insert(EmailVO emailVO);
	
	@InsertProvider(type = EmailSQL.class, method = "Email_update_sql")
	public int update(EmailVO emailVO);
	public int delete(long ems_seq);
	
	@Select(" SELECT * FROM tbl_ems WHERE ems_seq = #{ems_seq}")
	public EmailVO findBySeqems(long ems_seq);
	
	
}
