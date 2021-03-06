package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.CenterServiceMapper;
import com.sist.vo.CenterVO;
import com.sist.vo.Center_reserveVO;

@Repository
public class CenterServiceDAO {
	
	@Autowired
	private CenterServiceMapper mapper;
	
	public Center_reserveVO center_reserve(Center_reserveVO vo)
	{
		return mapper.center_reserve(vo);
	}
	public void check_update(Center_reserveVO vo)
	{
		System.out.println("DAO출력"+vo.getNo());
		System.out.println("DAO출력"+vo.getId());
		mapper.check_update(vo);
	}
	
	public int msg_check(String id)	
	{
		return mapper.msg_check(id);
	}
	public List<Center_reserveVO> msg_data(String id)
	{
		return mapper.msg_data(id);
	}
	
	public List<CenterVO> shelter_data()
	{
		return mapper.shelter_data();
	}
	
	public void shelter_icon(CenterVO vo)
	{
		mapper.shelter_icon(vo);
	}
	public void shelter_insertdata(CenterVO vo)
	{
		mapper.shelter_insertdata(vo);
	}
	
	public List<CenterVO> center_hospital()
	{
		return mapper.center_hospital();
	}
	
	
	
	//=============그래프 구하기 위한 카운트 가져오기 =============
	public int count_center(String city)
	{
		return mapper.count_center(city);
	}
	
	public int count_center_hospital(String city)
	{
		return mapper.count_center_hospital(city);
	}
	
	public int count_clinic(String city)
	{
		return mapper.count_clinic(city);
	}
	//================================================
	
	
	// 메인에 띄울 그래프
	public List<CenterVO> poster_count(String poster)
	{
		return mapper.poster_count(poster);
	}
	
	public List<Center_reserveVO> reserve_shelter_count()
	{
		return mapper.reserve_shelter_count();
	}
	public List<Center_reserveVO> reserve_center_count()
	{
		return mapper.reserve_center_count();
	}
	
	//보호소 센터 총 갯수.
	public List<CenterVO> total_shelter_count()
	{
		return mapper.total_shelter_count();
	}
	
	public int total_center_count()
	{
		return mapper.total_center_count();
	}
}	
