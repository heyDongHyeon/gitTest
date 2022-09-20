var timer = null;
var delta = 300;

$(document).ready(function (){
	//setMaskChk();
	resizeDone();

	createSliderType();


	 /*   $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
	      " - $" + $( "#slider-range" ).slider( "values", 1 ) );*/
});

$( window ).on( 'resize', function( ) {
    clearTimeout( timer );
    timer = setTimeout( resizeDone, delta );
} );

function resizeDone() {

	$('#wrap').css('height', $(window).height()-$('#layout-north').height()-70);

}

function createSliderType(){

	$('.sys_slider_bar').each(function(){
		var $obj = $('#'+$(this).attr('id'));
		var values = $obj.attr('value');
		var maxNum = 220;
		var idStr = $(this).attr('id')
		if ( idStr === 'snowLimit') maxNum = 350;
		$obj.slider({
		   range: true,

		   min: 0,
		   max: maxNum,

		   values: [values.split(',')[0], values.split(',')[1]],
		   slide: function( event, ui, e ) {
			   $('#'+$(event.target).attr('id')+"Min").val( ui.values[ 0 ] );
			   $('#'+$(event.target).attr('id')+"Max").val( ui.values[ 1 ] );
			   $(event.target).attr('value', ui.values[ 0 ] + "," + ui.values[ 1 ] );
		   }
		});

		$( "#"+idStr+"Min, #"+idStr+"Max" ).on('keyup', function(){

			var min = $('#'+idStr+'Min').val();
			var max = $('#'+idStr+'Max').val();

			if ( Number( min ) > Number( max ) ) min = max;

			console.log(idStr+'////');

			$obj.slider( "option", "values", [ min, max ] );

		});
	});
}

function chkListToMap($obj) {
	var str = '{';
	$obj.each(function(i){
		var key = $(this).attr('key');
		var value = $(this).prop("checked");
		if ( i != 0 ) {
			str += ',';
		};
		if ( value ) {
			value = 'Y';
		} else {
			value = 'N';
		}

		str += key + ':' +value;
	});

	str += '}';
	return str ;
}

$("#" + parentView).find('#wrap').find('#btn_save').click(function(){
	var _sysParam = _common.utils.collectSendData('#'+parentView+' #wrap');

	if ( $('#bellEvt').prop('checked') ) {

		_sysParam['bellEvt'] = 'Y';
	} else {
		_sysParam['bellEvt'] = 'N';
	}

	_sysParam['ivcpEvt'] = chkListToMap($("#" + parentView).find('.ivcpEvt'));
	_sysParam['cctvEvt'] =chkListToMap($("#" + parentView).find('.cctvEvt'));
	_sysParam['ndpsEvt'] =chkListToMap($("#" + parentView).find('.ndpsEvt'));
	_sysParam['bellLed'] =chkListToMap($("#" + parentView).find('.bellLed'));
	_sysParam['snowLimit'] = $('#snowLimit').attr('value');
	_sysParam['fullrainLimit'] = $('#fullrainLimit').attr('value');

	_common.callAjax("/sysMng/editSysParam.json", _sysParam, function(json) {
		if (json.result){
			$(".searchWrapper").find("#evtTable").find('tbody').html('');
			WIDGET.getPastEventListWidget();
			alert('* 저장되었습니다.');
		}
	});

});

$("#" + parentView).find('#wrap').find('#btn_reset').click(function(){

	var _sysParam = {};

	_sysParam['adminSmsList'] = 'geomex';
	_sysParam['aviPlayCnt'] = '20';
	_sysParam['aviPlayDat'] = '30';
	_sysParam['aviPlayTime'] = '1';
	_sysParam['previewAvi'] = 'Y';
	_sysParam['eviPlayCnt'] = '60';
	_sysParam['fileDownCnt'] = '5';
	_sysParam['lastSmsDat'] = '8';
	_sysParam['loginLockCnt'] = '5';
	_sysParam['previewPhoto'] = 'Y';
	_sysParam['renewPlayCnt'] = '40';
	_sysParam['renewPlayDat'] = '50';
	_sysParam['rqstLockCnt'] = '10';
	_sysParam['netDistLimit'] = '0';

	confirm("환경설정 값을 초기화 하시곘습니까?", function(){
		_common.callAjax("/sysMng/editSysParam.json", _sysParam, function(json) {
			if (json.result){
				_common.callAjax("/sysMng/getEnvSetView.do", null, function(view){
					$("#" + parentView).find("#overlay-west-contents").html(view);
				});
			}
		});
	}, function(){
		$(this).val("");
	});

});

/*$('#wrap').find('#maskingYn').change(function(){
	setMaskChk();
});

function setMaskChk(){

	var maskingYn =$('#maskingYn').prop("checked");

	if(maskingYn) $('#wrap').find('.mask_chk').show();
	else $('#wrap').find('.mask_chk').hide();
}*/