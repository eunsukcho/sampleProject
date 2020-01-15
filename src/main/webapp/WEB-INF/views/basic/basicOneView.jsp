<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<svg id="svgTag" width="1850" height="1080" xmlns="http://w3.org/2000/svg" version="1.1" viewbox="0 0 1850 1080">
		<image xlink:href="/resources/common/img/worldMap.svg" width="1850" height="800" x="0" y="0" />
		<circle id="circle1" cx="400" cy="200" r="10" style="fill:red; stroke:white; stroke-width:3px"></circle>
	</svg>
	
	<div id="dialog" title="Sample">
		아메리카
	</div>
	
<script type="text/javascript">

$(document).ready(function(){
	var svg = document.getElementById("svgTag");
	
	var circleElement = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
	circleElement.setAttributeNS(null, 'cx', 1200);
	circleElement.setAttributeNS(null, 'cy', 200);
	circleElement.setAttributeNS(null, 'r', 10);
	circleElement.setAttributeNS(null, 'fill', "red");
	circleElement.setAttributeNS(null, 'stroke', "white");
	circleElement.setAttributeNS(null, 'stroke-width', "3px");
	circleElement.setAttributeNS(null, "id", "createCircle2");
	svg.appendChild(circleElement);
	
	$("#circle1").on("click", function(e){
		console.log(e.currentTarget);
		
		pushLayer("mainContent", $(this).offset().top, $(this).offset().left, "아메리카");
	});
	
	$("#createCircle2").on("click", function(e){
		console.log(e.currentTarget);
		
		pushLayer("mainContent", $(this).offset().top, $(this).offset().left, "아시아");
		
	});
	
});
</script>