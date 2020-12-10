package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ClinicVO;
import com.sist.vo.ReplyVO;
import com.sist.vo.ReserveVO;
public interface ClinicMapper {
@Select("SELECT clno,title,addr,poster,num "
		+ "FROM (SELECT clno,title,addr,poster,rownum as num "
		+ "FROM (SELECT clno,title,addr,poster "
		+ "FROM clinic ORDER BY poster)) "
		+ "WHERE num BETWEEN #{start} AND #{end}")
public List<ClinicVO> clinicListData(Map map);

@Select("SELECT CEIL(COUNT(*)/6.0) FROM clinic")
public int clinicTotalPage();

@Select("SELECT clno,title,tel,addr,post,poster FROM clinic WHERE clno=#{clno}")
public ClinicVO clinicDetailData(int clno);

@Select("SELECT clno,addr,title,rownum "
		 +"FROM clinic "
		 +"WHERE rownum<=6 AND "
		 +"addr LIKE '%'||#{gu}||'%'")	
 public List<ClinicVO> clinicLocationFindData(String gu);

@Insert("INSERT INTO reply(cate,replyno,clno,id,content,regdate) VALUES(1,(SELECT NVL(MAX(replyno)+1,1) FROM reply),#{clno},#{id},#{content},SYSDATE)")
public void clinicReplyInsert(ReplyVO vo);

@Select("SELECT replyno,clno,id,content,regdate FROM reply WHERE clno=#{clno}")
public List<ReplyVO> clinicReplyList(int clno);

@Update("UPDATE reply SET content=#{content} WHERE replyno=#{replyno} AND clno=#{clno}")
public void clinicReplyUpdate(ReplyVO vo);

@Delete("DELETE FROM reply WHERE replyno=#{replyno} and cate=1")
public void clinicReplyDelete(ReplyVO vo);

@Insert("INSERT INTO reserve(resno,clno,id,resdate,owner,pname,content,name) VALUES((SELECT NVL(MAX(resno)+1,1) FROM reserve),#{clno},#{id},#{resdate},#{owner},#{pname},#{content},#{time})")
public void clinicReserveInsert(ReserveVO vo);

@Select("SELECT resno,clno,id,resdate,owner,pname,content,name FROM reserve WHERE resno=#{resno}")
public List<ReserveVO> clinicReserveList(int resno);
}
