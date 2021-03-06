package com.sist.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.CenterServiceDAO;
import com.sist.news.Item;
import com.sist.news.NewsManager;
import com.sist.vo.CenterVO;
import com.sist.vo.Center_reserveVO;

import twitter4j.JSONArray;
	
@Controller
@RequestMapping("center/")
public class CenterServiceController {
	@Autowired
	private CenterServiceDAO dao;
	
	@RequestMapping("service.do")
	public String board_list(Model model, String data,HttpServletRequest request)
	{
		if (data == null) {
			System.out.println("data은 NULL");
		}
		System.out.println("Json" + data);
		//=====================================
		
		System.out.println("========Model 업데이트 부분 ========");
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} // 한글 디코딩
		
		//=====================================
		List<CenterVO> list = dao.center_hospital();
		JSONObject hospital_data = new JSONObject();
		org.json.simple.JSONArray js = new org.json.simple.JSONArray();
		for(CenterVO vo:list)
		{
//			System.out.println("좌표"+vo.getWgs84_x());
//			System.out.println("좌표"+vo.getWgs84_y());
			
			JSONObject center_hospital = new JSONObject();
			center_hospital.put("NO",""+vo.getNo()+"");
			center_hospital.put("SHELTER_NAME",vo.getName());
			center_hospital.put("CITY",vo.getCity()); 
			center_hospital.put("CAPACITY",vo.getCapacity()); 
			center_hospital.put("REMINDER",vo.getReminder()); 
			center_hospital.put("TEL",vo.getTel());
			center_hospital.put("REPRESENTATIVE", vo.getRepresentative());
			center_hospital.put("POST",""+vo.getPost()+"");
			center_hospital.put("POSTER",vo.getPoster());
			center_hospital.put("LOTNO_ADDR",vo.getLotno_addr());
			center_hospital.put("ROADNO_ADDR",vo.getRoadno_addr());
			center_hospital.put("WGS84_X",vo.getWgs84_x());
			center_hospital.put("WGS84_Y",vo.getWgs84_y());
			js.add(center_hospital);
		}
		hospital_data.put("datas",js.toString());
		System.out.println("치료병원데이터"+hospital_data);
		String result = hospital_data.toString().replaceAll("\"\\[" ,"\\[").replaceAll("\\]\"" ,"\\]").replaceAll("\\\\" ,"");
		
		System.out.println("JSON설정후"+result);
		//석주형  경로
		File path = new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\hospital2.json");
		
		//승구 경로
//		File path = new File("C:\\SpringDev\\SpringStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\hospital2.json");
		try {
			//C:\SpringDev\SpringStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Hometail
            FileWriter file = new FileWriter("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\hospital2.json");
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.getPath()),"UTF-8"));
            output.write(result);
            output.close();
//            file.write(result);
//            file.flush();
//            file.close();
     } catch(IOException e) {
            e.printStackTrace();
     }	
		
		return "../center/service";
	}	
	@RequestMapping("service_map.do")
	public String center_service_map(Model model, CenterVO vo,String rday,String time,HttpSession session) {
		
		Center_reserveVO rvo = new Center_reserveVO();
		String id = String.valueOf(session.getAttribute("id"));
		String name= String.valueOf(session.getAttribute("name"));
		System.out.println("위치값" + vo.getCity());
		System.out.println("전화번호" + vo.getTel());
		System.out.println("지번 주소" + vo.getLotno_addr());	
		System.out.println("도로명 주소 " + vo.getRoadno_addr());
		System.out.println("날짜 "+rday);
		System.out.println("시간 "+time);
		System.out.println("병원명"+vo.getName());
		rvo.setId(id);
		rvo.setName(name);
		rvo.setLoc(vo.getCity());
		rvo.setTel(vo.getTel());
		rvo.setLotno_addr(vo.getLotno_addr());
		rvo.setRoadno_addr(vo.getRoadno_addr());
		rvo.setHospital_name(vo.getName());
		rvo.setDb_reserve_day(rday);
		rvo.setTime(time);
		dao.center_reserve(rvo);
		return "redirect:../main/main.do";
	}	
	@RequestMapping("msg_detail.do")
	public String msg_detail(Model model)
	{		  
		System.out.println("호출");
		String result="hi";
		return result;
	}		  
	@RequestMapping("center_date.do")
	public String center_date(Model model,String strYear,String strMonth,String tno)
	{		  
		System.out.println("=======================");
			  if(tno==null)
			  	  tno="1";
			  
			  Date date=null;
				try {
					date = new Date();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  	}
			  // 2020-10
			  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
			  // MM dd (X)  M d 01 ~ 09 10 11 12   09
			  // 1 2 3 .... 10 11 12
			  String today=sdf.format(date);
			  StringTokenizer st=new StringTokenizer(today,"-");
			  
			  if(strYear==null)
			  {
				  strYear=st.nextToken();// yyyy
			  }
			  
			  if(strMonth==null)
			  {
				  strMonth=st.nextToken();
			  }
			  
			  int day=Integer.parseInt(st.nextToken());// 화면 
			  int year=Integer.parseInt(strYear);
			  int month=Integer.parseInt(strMonth);
			  
			  Calendar cal=Calendar.getInstance();// Calendar클래스 생성 
			  cal.set(Calendar.YEAR,year);
			  cal.set(Calendar.MONTH,month-1);// month=>0~11
			  cal.set(Calendar.DATE,1);
			  
			  // 요일을 구한다
			  int week=cal.get(Calendar.DAY_OF_WEEK);// 1~7 ==> week-1
			  int lastday=cal.getActualMaximum(Calendar.DATE);
			  String[] strWeek={"일","월","화","수","목","금","토"};
			  System.out.println("요일:"+strWeek[week-1]);
			  System.out.println("마지막날:"+lastday);
			  
			  // DB => 예약날짜 읽기
			  System.out.println("tno="+tno);
//			  String rday=MovieDAO.theaterReserveData(Integer.parseInt(tno));
//			  System.out.println("rday="+rday);
			  int[] days=new int[32];
//			  StringTokenizer st2=new StringTokenizer(rday,",");
			  //int i=0;
//			  while(st2.hasMoreTokens())
//			  {
//			  	  String d=st2.nextToken();
//			  	  days[Integer.parseInt(d)]=Integer.parseInt(d);
//			  }
		 	  
			  for(int k:days)
			  {
//			  	  System.out.println("k="+k);
			  }
			  
			  model.addAttribute("rdays", days);
			  // jsp로 전송 
			  model.addAttribute("year", year);
			  model.addAttribute("month", month);
			  model.addAttribute("day", day);
			  model.addAttribute("week", week-1);
			  model.addAttribute("strWeek", strWeek);
			  model.addAttribute("lastday", lastday);
			  // 1일자의 요일 
		  return"reserve_date";
	}
	@RequestMapping("center_time.do")
	public String ceter_time()
	{
		
		return "reserve_time";
	}
	
	@RequestMapping("shelter.do")
	public String shelter(Model model)
	{ 
		List<CenterVO> list = new ArrayList<CenterVO>();
		JSONObject shelter_data = new JSONObject();
		
		
		list = dao.shelter_data();
		for(CenterVO vo:list)
		{
			if(vo.getRepresentative()==null)
			{
				vo.setRepresentative("미정");
			}
			if(vo.getReminder()==null)
			{
				vo.setReminder("* 사항 없음");
			}
			
		}
		System.out.println("list 사이즈:"+list.size());
		org.json.simple.JSONArray js = new org.json.simple.JSONArray();
		for(CenterVO vo:list)
		{
//			System.out.println("좌표"+vo.getWgs84_x());
//			System.out.println("좌표"+vo.getWgs84_y());
			
			JSONObject shelter = new JSONObject();
			shelter.put("NO",""+vo.getNo()+"");
			shelter.put("SHELTER_NAME",vo.getName());
			shelter.put("CITY",vo.getCity()); 
			shelter.put("CAPACITY",vo.getCapacity()); 
			shelter.put("REMINDER",vo.getReminder()); 
			shelter.put("TEL",vo.getTel());
			shelter.put("REPRESENTATIVE", vo.getRepresentative());
			shelter.put("POST",""+vo.getPost()+"");
			shelter.put("POSTER",vo.getPoster());
			shelter.put("LOTNO_ADDR",vo.getLotno_addr());
			shelter.put("ROADNO_ADDR",vo.getRoadno_addr());
			shelter.put("WGS84_X",vo.getWgs84_x());
			shelter.put("WGS84_Y",vo.getWgs84_y());
			js.add(shelter);
		}
		shelter_data.put("datas",js.toString());
		System.out.println("나오려나"+shelter_data);
		String result = shelter_data.toString().replaceAll("\"\\[" ,"\\[").replaceAll("\\]\"" ,"\\]").replaceAll("\\\\" ,"");
		
		System.out.println("JSON설정후"+result);
		File path = new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\shelter2.json");
//		File path = new File("C:\\SpringDev\\SpringStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\shelter2.json");
		try {
            FileWriter file = new FileWriter("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\shelter2.json");
//            FileWriter file = new FileWriter("C:\\SpringDev\\SpringStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Hometail\\center\\shelter2.json");
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.getPath()),"UTF-8"));
            output.write(result);
            output.close();
//            file.write(result);
//            file.flush();
//            file.close();
     } catch(IOException e) {
            e.printStackTrace();
     }
		return "../center/shelter";
	}
	
	@RequestMapping("shelter_add.do")
	public String shelter_add()
	{	
		
		return "shelter_find";
	}	
		
	@RequestMapping("shelter_insert.do")
	public String shelter_insert(CenterVO vo)
	{	
//		<option value="1">신문지</option>
//		<option value="2">통조림</option>
//		<option value="3">사료</option>
//		<option value="4">헌옷</option>
//		<option value="5">이불</option>
		String arr[] = {"newspaper_marker.png","canned-food_marker.png","meat_marker.png","clothes_marker.png","blanket_marker.png"};
		int poster_number = Integer.parseInt(vo.getPoster());
		vo.setPoster(arr[poster_number]);
		System.out.println("shelter_insert.do 호출");
		System.out.println("요청 보호소 이름"+vo.getName());
		System.out.println("요청 보호소 번호 "+vo.getNo());
		System.out.println("요청 품목 사진"+vo.getPoster());
		dao.shelter_icon(vo);
		
		return "redirect:../center/shelter.do";	
//		return "redirect:../main/main.do";
	}
	
	//새로운 보호소 저장
	@RequestMapping("shelter_add_data.do")
	public String shelter_add_data(CenterVO vo)
	{
		System.out.println("번호="+vo.getNo());
		System.out.println("이름="+vo.getCity());
		System.out.println("이름="+vo.getName());
		System.out.println("전화번호="+vo.getTel());
		System.out.println("대표="+vo.getRepresentative());
		System.out.println("수용력="+vo.getCapacity());
		System.out.println("비고사항="+vo.getReminder());
		System.out.println("주소="+vo.getRoadno_addr());
		vo.setLotno_addr("데이터 없음");
		System.out.println("주소="+vo.getLotno_addr());
		String temp_x = vo.getWgs84_x().toString();
		String temp_y = vo.getWgs84_y().toString();
		temp_x = temp_x.substring(0,temp_x.indexOf('.')+9);
		temp_y = temp_y.substring(0,temp_y.indexOf('.')+9);
		vo.setWgs84_x(Double.parseDouble(temp_x));
		vo.setWgs84_y(Double.parseDouble(temp_y));
		System.out.println("x="+vo.getWgs84_x());
		System.out.println("y="+vo.getWgs84_y());
		String arr[] = {"newspaper_marker.png","canned-food_marker.png","meat_marker.png","clothes_marker.png","blanket_marker.png"};
		int poster_number = Integer.parseInt(vo.getPoster());
		vo.setPoster(arr[poster_number]);
		System.out.println("사진"+vo.getPoster());
		System.out.println("msg="+vo.getMessage());
		
		dao.shelter_insertdata(vo);
		
		return "redirect:../center/shelter.do";
	}
	
	
	@RequestMapping("graph.do")
	public String center_graph(String city,Model model)
	{
		System.out.println("city는 :"+city);
		int count_center = dao.count_center(city);
		int count_center_hospital = dao.count_center_hospital(city);
		int count_clinic = dao.count_clinic(city);
		
		model.addAttribute("shelter",count_center);
		model.addAttribute("hospital",count_center_hospital);
		model.addAttribute("clinic",count_clinic);
		
		return "center_graph";
	}
	

}
