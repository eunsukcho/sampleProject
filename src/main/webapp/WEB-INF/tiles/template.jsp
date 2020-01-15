<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>제목</title>
    <script src="/resources/common/js/jquery-3.4.1.min.js"></script>
    <script src="/resources/common/js/jquery-ui.js"></script>
    <script src="/resources/common/js/jquery-ui.min.js"></script>
    <script src="/resources/common/js/common.js"></script>
    <link rel="stylesheet" href="/resources/common/css/jquery-ui.css">
    
    <script src="/resources/common/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/common/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="/resources/common/css/font.css">
	<link rel="stylesheet" href="/resources/common/css/common.css">
</head>
<body>
	<!-- Modal -->
	<div class="modal fade" id="myModalAlert" tabindex="-1" role="dialog">
		<div class="modal-dialog">
		Modal content
		<div class="modal-content" id="myModalAlert2">
			<div class="modal-header">
				<h6 class="modal-title">
				<span class="text-success"></span></h6>
			</div>
							
			<div class="modal-body">
			</div>
			
			<div class="modal-footer">
				<button id="checkBtn" type="button" class="btn btn_point" data-dismiss="modal">확인</button>
			</div>
		</div>
		</div>
	</div> 
		  
	<!-- The Modal -->
    <div id="myModal" class="modal-popup">
		<div class="modal-content-popup">
		      
		<!-- 제품 정보 선택 div -->
		<div class="popup" id="mainContent">
			<span class="modalText"></span>	
		</div>
		      
		</div>
	</div>
		  
    <div style="width:100%; height:100%;">
	    <div id="templateHeader"><tiles:insertAttribute name="header" /></div>
	    <div id="templateMain"><tiles:insertAttribute name="body" /></div>    
	    <div id="templateFooter"><tiles:insertAttribute name="footer" /></div>
	</div>
 
    <script type="text/javascript">
        $(function() {
 
        });    
    </script>    
</body>
</html>
