package sampleData.sample.controller;

import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import sampleData.sample.service.SampleService;
import sampleData.sample.vo.MongoVo;

@Controller
public class SampleController {

	private MongoClient mongo;
	private MongoOperations mongoOps;
	
	private final int MONGO_PORT = 27017;
	private final String MONGO_HOST = "*";
	private final String DB_NAME = "*";
	
	@Autowired
	private SampleService service;
	
	@RequestMapping("/sampleDataView.do")
	public String sampleDataView(){
		
		return "/sample/sampleDataView";
	}
	
	public MongoClient ConnectionInit(){
		String host = MONGO_HOST;
		int port = MONGO_PORT;
		String databaseName = DB_NAME;
		String username = "*";
		String password = "*";

		MongoCredential credential = MongoCredential.createCredential(username, databaseName, password.toCharArray());
		MongoClientOptions options = new MongoClientOptions.Builder().build();
		mongo = new MongoClient(new ServerAddress(host, port), credential, options);
		
		return mongo;
	}
	
	@RequestMapping("/sampleDataMongoInsert.do")
	@ResponseBody
	public String sampleDataMongoInsert(@RequestBody Map<String, Object> map) throws ParseException, ClassNotFoundException, SQLException, SocketTimeoutException{
		mongo = ConnectionInit();
		mongoOps = new MongoTemplate(mongo, DB_NAME);
		
		MongoVo vo = new MongoVo();
		
		String startFrom = (String) map.get("startDate");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTo = transFormat.parse(startFrom);
		
		int addMin = 5; // 5분씩 증가
		int deviceCount = 10; // 디바이스 갯수
		
		loop:
		for (int i = 1; i <= deviceCount; i++) {
			String serialNum = "SN"+String.format("%06d", i);
			vo.setSERIAL_NUM(serialNum);
			vo.setPRODUCT_CODE((String) map.get("prductNmCd"));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTo);
			Date targetInputDate = cal.getTime(); //사용자가 입력한 시간
			System.out.println("targetInputDate : " + targetInputDate);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startTo);
			cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DATE) + 1);
			
			Date todayDate = cal2.getTime(); // 비교할 시간 -> 사용자가 입력한 시간  + 24시간
			
			int j = 0;
			while (true) {  // 시간 계산
				try{
					Thread.sleep(500);
				}catch (Exception e) {
					// TODO: handle exception
				}
				int refriggerTemp = randomRange(15, 20);
				String refriggerTempStr = "-" + String.valueOf(refriggerTemp);
				
				int setFreezerTemp = Integer.parseInt(refriggerTempStr);
				int setRefriggerTemp = randomRange(1, 10);
				
				int refreggerDoor = 0;
				int freezerDoor = 0;
				
				if(j % 2 == 0){
					refreggerDoor = 1;
					freezerDoor = 1;
				}
				
				vo.setREFRIGGER_DOOR(refreggerDoor);
				vo.setFREEZER_DOOR(freezerDoor);
				vo.setREFRIGGER_TEMP(setRefriggerTemp);
				vo.setFREEZER_TEMP(setFreezerTemp);
				vo.setREFRIGGER_DOOR_CREATE_DT(cal.getTimeInMillis());
				vo.setFREEZER_DOOR_CREATE_DT(cal.getTimeInMillis());
				vo.setREFRIGGER_TEMP_CREATE_DT(cal.getTimeInMillis());
				vo.setFREEZER_TEMP_CREATE_DT(cal.getTimeInMillis());
				
				try{
					mongoOps.insert(vo, "VIEW_REFRIGGERATOR_TBL");
				}catch (Exception e) {
					System.out.println("exception");
					mongoOps.insert(vo, "VIEW_REFRIGGERATOR_TBL");
				}
				
				cal.add(Calendar.MINUTE, addMin);
				targetInputDate = cal.getTime(); 
				if(todayDate.equals(targetInputDate)){
					System.out.println("break");
					break;
				}
				
				j++;
			}
			
			if(todayDate.equals(targetInputDate)){
				System.out.println("break");
				continue loop;
			}
			
		}
		
		return "Success";
		
	}
	
	@RequestMapping("/sampleDataInsert.do")
	@ResponseBody
	public String sampleDataInsert(@RequestBody Map<String, Object> map) throws ParseException, ClassNotFoundException, SQLException, SocketTimeoutException{
		System.out.println("map : " + map);
		
		String startFrom = (String) map.get("startDate");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTo = transFormat.parse(startFrom);
		
		int addMin = 5; // 5분씩 증가
		int deviceCount = 1000; // 디바이스 갯수
		
		loop:
		for (int i = 1; i <= deviceCount; i++) {
			String serialNum = "SN"+String.format("%06d", i);
			map.put("serialNum", serialNum);
			map.put("productCd", (String) map.get("prductNmCd"));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTo);
			Date targetInputDate = cal.getTime(); //사용자가 입력한 시간
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(startTo);
			cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DATE) + 1);
			
			Date todayDate = cal2.getTime(); // 비교할 시간 -> 사용자가 입력한 시간  + 24시간
			
			int j = 0;
			while (true) {  // 시간 계산
				try{
					Thread.sleep(500);
				}catch (Exception e) {
					// TODO: handle exception
				}
				int refriggerTemp = randomRange(15, 20);
				String refriggerTempStr = "-" + String.valueOf(refriggerTemp);
				
				int setFreezerTemp = Integer.parseInt(refriggerTempStr);
				int setRefriggerTemp = randomRange(1, 10);
				
				int refreggerDoor = 0;
				int freezerDoor = 0;
				
				if(j % 2 == 0){
					refreggerDoor = 1;
					freezerDoor = 1;
				}
				
				map.put("refriggerDoor", refreggerDoor);
				map.put("freezerDoor", freezerDoor);
			    map.put("refriggerTemp", setRefriggerTemp);
				map.put("freezerTemp", setFreezerTemp);
				map.put("refriggerDoorCreateDt", targetInputDate);
				map.put("freezerDoorCreateDt", targetInputDate);
				map.put("refriggerTempCreateDt", targetInputDate);
				map.put("freezerDoorTempCreateDt", targetInputDate);
				
				try{
					service.sampleDateInsert(map);
				}catch (Exception e) {
					System.out.println("exception");
					service.sampleDateInsert(map);
				}
				
				cal.add(Calendar.MINUTE, addMin);
				targetInputDate = cal.getTime(); 
				if(todayDate.equals(targetInputDate)){
					System.out.println("break");
					break;
				}
				
				j++;
			}
			
			if(todayDate.equals(targetInputDate)){
				System.out.println("break");
				continue loop;
			}
			
		}
		
		return "Success";
	}
	
	public int randomRange(int n1, int n2){
	
		return (int) (Math.random() * (n2 - n1 + 1)) + n1; 
	}
	
}
