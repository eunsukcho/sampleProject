/**
 * 
 common.js
 *
 **/

/** Alert Layer **/
var alertModal = $('#myModalAlert');

/** Point Click Layer **/
var modalPopup = $(".modal-popup");

/** Alert **/
function Alert(text){
	alertModal.find('.modal-title span').text(text);
	alertModal.modal({
		backdrop: 'static',
		keyboard: false
	});
}

/** Create Layer **/
function pushLayer(id, top, left, text){
	var modalPopup = $(".modal-popup");
	var modalContentPopup = $(".modal-content-popup");
	
	modalPopup.css("display", "block");
	$("#"+id).css("display", "block");
	
	modalContentPopup.css("margin-top", top);
	modalContentPopup.css("marginLeft", left);
	
	$("#"+id).find('span').text(text);
}


