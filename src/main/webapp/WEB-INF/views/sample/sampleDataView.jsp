<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">

<html>
	제조사(코드) : <input type="text" id="mfCd" value="COMMAX" disabled /> <br>
	제품명(코드) : <input type="text" id="productNm" value="CM_RFR" disabled /> <br><br>
	시리얼 넘버 : SN000001 ~ SN001000 <br>
	
	Refrigger door On / Off <br>
	Freezer door On / Off <br>
	Refrigger temperature : -20도 ~ -15도 <br>
	Freezer temperature :1 ~ 10도 사이 <br><br>
	
	시작일자 : <input type="text" id="startDate" value="2019-10-30 00:00:00" /> <br><br>
	
	<input type="button" id="createBtn" value="생성" />
	<input type="button" id="deleteBtn" value="삭제" /> <br><br>
	
	<input type="button" id="mongoDataCreateBtn" value="몽고 디비 데이터 생성" />
	
</html>
		
<!-- <button id="uploadFile">전송</button> -->
		
	

<script type="text/javascript">
	$(document).ready(function(){
		
		$("#createBtn").click(function(){
			
			var param = new Object();
			param.mfCd = $("#mfCd").val();
			param.prductNmCd = $("#productNm").val();
			param.startDate = $("#startDate").val();
			param.endDate = $("#endDate").val();
			
			$.ajax({ 
				url: "/sampleData/sampleDataInsert.do",
				data: JSON.stringify(param), 
				dataType: 'json', 
				contentType : "application/json; charset=utf-8",
				type: 'POST',
				success: function (data) {

				},error: function (jqXHR) { 
					console.log(jqXHR.responseText); 
				} 
			});
			
		});
		
		$("#mongoDataCreateBtn").click(function(){
			var param = new Object();
			param.mfCd = $("#mfCd").val();
			param.prductNmCd = $("#productNm").val();
			param.startDate = $("#startDate").val();
			param.endDate = $("#endDate").val();
			
			$.ajax({ 
				url: "/sampleData/sampleDataMongoInsert.do",
				data: JSON.stringify(param), 
				dataType: 'json', 
				contentType : "application/json; charset=utf-8",
				type: 'POST',
				success: function (data) {
					
				},error: function (jqXHR) { 
					console.log(jqXHR.responseText); 
				} 
			});
			
		});
		
		
	});
</script>