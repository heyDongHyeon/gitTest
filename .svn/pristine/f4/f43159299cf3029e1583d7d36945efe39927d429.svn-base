var agent = null;


$(document).ready(function (){

	/* 180517 이은규
	 * 아이콘 로딩되는 화면이 지저분해서 모두 로딩되면 화면이 보이도록 변경
	 */
	$("#wrap").show();

	//브라우저 체크
	agent = navigator.userAgent.toLowerCase();

	getIconList();

	//console.log(iconList);
});

/* 탭 클릭 이벤트 입니다. */
$("#wrap").find("button.iconTab").click(function(){
	$("#wrap").find("button.iconTab").removeAttr("active");
	$(this).attr("active", "active");

	var _url = $(this).attr("url");
	var _mode = $(this).attr("mode");
	var _supPath = $(this).attr("subpath");
	if(_url != null){// && _mode != null && _supPath != null
		var _param = {};
		if(_mode=="mng"){
			_param['limit'] = 10;
			_param['offset'] = 0;
		} else {
			_param['symGrp'] = _mode;
			_param['subPath'] = _supPath;
		}

		_common.callAjax(_url, _param, function(view) {
			//$(".bpopup").remove();
			$("#"+parentView).find(".bpopup").remove();
			$("#overlay-west-contents").html(view);
		});
	}
    //$(".searchWrapper").find("#searchBtn").click();
});

$('#wrap').find('#content').find('img').click(function(){

	$(this).prev().click();

});

$('#wrap').find('#editPopBtn').click(function(){

	var chkVal = $(':radio[name="selectIcon"]:checked').val();
	var iconTyp = $(':radio[name="selectIcon"]:checked').attr('iconTyp');

	if (chkVal == undefined){
		alert("아이콘을 선택하여 주십시오.");
		return false;
	} else {
		$('#icon_edit_wrap').find('#iconTyp').val(iconTyp);
		$('#icon_edit_wrap').find('#gbnCd').val(chkVal);
		$('#icon_edit_wrap').bPopup({
			appendTo: $('#'+parentView),
			onOpen: function(){
				$('#icon_edit_wrap').find('table').find('.target').find('.iconlist').html('');
				var length = iconList.length;
				if(length > 0){
					var _str = '';
					for(var i=0; i<length; i++){
						if(i%3==0){
							_str += '<div>';
						}
						_str += '		<input type="radio" class="editIcon" name="editIcon" value="'+iconList[i]+'">';// gbn="'+subPath+'"
						_str += '		<img src="'+ctxPath+'/res/sym/'+subPath+'/'+iconList[i]+'" width="40px">';
						if(i%3==2 || (i == length-1)){
							_str += '</div>';
						}
		            }
					$('#icon_edit_wrap').find('table').find('.target').find('.iconlist').html(_str);
				}
				//이미지 클릭 시 라디오 버튼이 선택된다.
				$('.iconlist').find('img').click(function(){
					$(this).prev().click();
				});
			},
			onClose: function(){
				$('#icon_edit_wrap').find('#iconTyp').val('');
				$('#icon_edit_wrap').find('#gbnCd').val('');
				$('#icon_edit_wrap').find('table').find('.target').find('.iconlist').html('');
			}
		});
	}
});

$('#wrap').find('.bpopClose').click(function(){
	/*
	 * 180523 이은규
	 * alert나 confirm이 활성화 되있을시엔 작동하지 않는다.
	 */
	var chkNotice = $('.notice').length;
	if(chkNotice == 0){
		$('#'+$(this).attr('popup')).bPopup().close();
	}
});

$('#icon_edit_wrap').find('#saveBtn').click(function(){
	/*
	 * 180523 이은규
	 * alert나 confirm이 활성화 되있을시엔 작동하지 않는다.
	 */
	var chkNotice = $('.notice').length;
	if(chkNotice == 0){
		var chkVal = $(':radio[name="editIcon"]:checked').val();
		if (chkVal == undefined){
			alert("아이콘을 선택하여 주십시오.");
			return false;
		} else {
			confirm("이미지를 변경하시겠습니까?", function(){
				var _param = _common.utils.collectSendData("#icon_edit_wrap");
				_param['symGrp']= symGrp;
				_param['fileNm']= $(':radio[name="editIcon"]:checked').val();

				_common.callAjax("/symIcon/editSymIcon.json", _param, function(json) {
					if(json.result){
						alert('변경되었습니다.');
						refresh('icon_edit_wrap');
					}
				}, false);
			}, function(){
				$(this).val("");
			});
		}
	}
});

function getIconList(){

	_common.callAjax("/sysMng/getIconList.json", {'subPath':subPath}, function(json) {
		if(json.result){
			iconList = json.iconlist;
		}
	}, false);

}


//등록 팝업 관련
$('#wrap').find('#regPopBtn').click(function(){
	$('#icon_reg_wrap').bPopup({
		appendTo: $('#'+parentView),
		onOpen: function(){
			$('#icon_reg_wrap').find('table').find('.target').find('.iconlist').html('');
			var length = iconList.length;
			if(length > 0){
				var _str = '';
				for(var i=0; i<length; i++){
					if(i%3==0){
						_str += '<div>';
					}
					_str += '		<input type="radio" class="regIcon" name="regIcon" value="'+iconList[i]+'">';// gbn="'+subPath+'"
					_str += '		<img src="'+ctxPath+'/res/sym/'+subPath+'/'+iconList[i]+'" width="40px">';
					if(i%3==2 || (i == length-1)){
						_str += '</div>';
					}
	            }
				$('#icon_reg_wrap').find('table').find('.target').find('.iconlist').html(_str);
			}
			//이벤트 중복 발생 방지
			$('#icon_reg_wrap').find('.iconlist').find('img').off('click');
			//이미지 클릭 시 라디오 버튼이 선택된다.
			$('#icon_reg_wrap').find('.iconlist').find('img').click(function(){
				$(this).prev().click();
			});
		},
		onClose: function(){
			$("#icon_reg_wrap").find("#regGbnCd").val('').prop("selected", true);
			$("#icon_reg_wrap").find("#regIconTyp").val('').prop("selected", true);
			$('#icon_reg_wrap').find('table').find('.target').find('.iconlist').html('');
		}
	});
});

$("#icon_reg_wrap").find("#regBtn").click(function(){
	/*
	 * 180523 이은규
	 * alert나 confirm이 활성화 되있을시엔 작동하지 않는다.
	 */
	var chkNotice = $('.notice').length;
	if(chkNotice == 0){
		confirm("등록하시겠습니까?", function(){
			var fileNm = $(':radio[name="regIcon"]:checked').val();
			if (fileNm == undefined){
				alert("아이콘을 선택하여 주십시오.");
				return false;
			} else {

				var _param = {};
				_param['symGrp'] = symGrp;
				_param['gbnCd'] = $('#regGbnCd').val();
				_param['iconTyp'] = $('#regIconTyp').val();
				_param['fileNm']= fileNm;

				_common.callAjax("/symIcon/addSymIcon.json", _param, function(json){
					if(json.result){
						alert('등록되었습니다.');
						refresh('icon_reg_wrap');
					}
				});
			}
		}, function(){
			$(this).val("");
		});
	}
});

function refresh(str){
	if(str != 'none'){
		$('#'+str).bPopup().close();
	}
	$('.bpopup').remove();
	var _url = '';
	var _mode = '';
	var _subPath = '';
	$('.iconTab').each(function() {
		var chkActive = $(this).attr("active");
		if (chkActive !== undefined && chkActive == "active"){
			_url = $(this).attr("url");
			_mode = $(this).attr("mode");
			_subPath = $(this).attr("subpath");
		}
	});
	if(_url != ''){
		var _param = {};
		_param['subPath'] = _subPath;
		_param['symGrp'] = _mode;
		_common.callAjax(_url, _param, function(view){
			$("#overlay-west-contents").html(view);
		});
	}
}

//삭제 관련 이벤트
$('#wrap').find('#delBtn').click(function(){

	var chkVal = $(':radio[name="selectIcon"]:checked').val();
	var iconTyp = $(':radio[name="selectIcon"]:checked').attr('iconTyp');

	if (chkVal == undefined){
		alert("아이콘을 선택하여 주십시오.");
		return false;
	} else {
		//TODO 삭제 해야지.
		confirm("심벌을 삭제하시겠습니까?", function(){
			var _param = {};
			_param['symGrp']= symGrp;
			_param['gbnCd']= chkVal;
			_param['iconTyp'] = iconTyp;

			_common.callAjax("/symIcon/delSymIcon.json", _param, function(json) {
				if(json.result){
					alert('삭제되었습니다.');
					refresh('none');
				}
			}, false);
		}, function(){
			$(this).val("");
		});
	}
});















//아이콘 관리 팝업 관련
$('#wrap').find('#iconMngPopBtn').click(function(){
	$('#icon_mng_wrap').bPopup({
		appendTo: $('#'+parentView),
		onOpen: function(){
			mngWrapInit();
		},
		onClose: function(){
			//TODO 필요한거 있으면 넣기.
		}
	});
});

$("#icon_mng_wrap").find("#btnUpload").click(function(e) {

	$("#icon_mng_wrap").find("#hiddenForm").find("#uploadImg").click();

});

$('#icon_mng_wrap').find("#uploadImg").change(function() {
	var ext = $(this).val().split('.').pop();
	if (ext != "png"){
		alert('png 확장자만 등록 가능합니다.');
		return false;
	} else {
		if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
		     // ie일 경우
			$("#icon_reg_wrap").find("#uploadImg").replaceWith($("#icon_reg_wrap").find("#uploadImg").clone(true));
		}else{
		     // ie가 아닐 경우
			$("#icon_reg_wrap").find("#uploadImg").val('');
		}
		readURL(this);
	}
});

$("#icon_mng_wrap").find(".iconUploadBtn").click(function(){
	/*
	 * 180523 이은규
	 * alert나 confirm이 활성화 되있을시엔 작동하지 않는다.
	 */
	var chkNotice = $('.notice').length;
	if(chkNotice == 0){
		confirm("등록하시겠습니까?", function(){
			_common.formSubmit("/sysMng/uploadIcon.json", $("#icon_mng_wrap").find("#hiddenForm"), function(json){
				if(json.result) {
					alert('등록되었습니다.');
					$('#icon_mng_wrap').find('#upFile').html('');
					getIconList();
					mngWrapInit();
				}else{
					alert(json.error);
				}
			});
		}, function(){
			$(this).val("");
		});
	}
});

$("#icon_mng_wrap").find(".iconDelBtn").click(function(){
	/*
	 * 180523 이은규
	 * alert나 confirm이 활성화 되있을시엔 작동하지 않는다.
	 */
	var chkNotice = $('.notice').length;
	if(chkNotice == 0){
		confirm("삭제하시겠습니까?", function(){

			var $chk = $('.chk_del.on');
			var tot = $chk.length;
			var saveChk = true;

			if ( tot != 0 ) {
				var arr = new Array();
				$chk.each(function(){
					arr.push($(this).attr('key'));
				});
				var _param = {};
				_param['subPath'] = subPath;
				_param['delList'] = arr.join('||');
				_common.callAjax("/symIcon/delIcon.json", _param, function(json) {
					if(json.result){
						alert('삭제되었습니다.');
						getIconList();
						mngWrapInit();
					}
				}, false);

			} else {
				alert('선택된 아이콘이 없습니다.');
			}

			/*_common.formSubmit("/sysMng/uploadIcon.json", $("#icon_reg_wrap").find("#hiddenForm"), function(json){
				if(json.result) {
					alert('등록되었습니다.');
					getIconList();
					mngWrapInit();
				}else{
					alert(json.error);
				}
			});*/
		}, function(){
			$(this).val("");
		});
	}
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			//$('#after').attr('src', e.target.result);
			var _str = '';
			_str += '<img src="'+e.target.result+'">';
			$('#icon_mng_wrap').find('#upFile').html('');
			$('#icon_mng_wrap').find('#upFile').html(_str);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function mngWrapInit(){
	$('#icon_mng_wrap').find('table').find('.target').find('.iconlist').html('');
	var length = iconList.length;
	if(length > 0){
		var _str = '';
		for(var i=0; i<length; i++){
			if(i%3==0){
				_str += '<div>';
			}
			_str += '		<input type="checkbox" key="'+iconList[i]+'" class="chk_del" />';
			//_str += '		<input type="radio" class="regIcon" name="regIcon" value="'+iconList[i]+'">';// gbn="'+subPath+'"
			_str += '		<img src="'+ctxPath+'/res/sym/'+subPath+'/'+iconList[i]+'" width="40px">';
			if(i%3==2 || (i == length-1)){
				_str += '</div>';
			}
        }
		$('#icon_mng_wrap').find('table').find('.target').find('.iconlist').html(_str);
	}
	//이벤트 중복 발생 방지
	$('#icon_mng_wrap').find('.chk_del').off('click');
	//체크박스 클릭 이벤트를 생성한다.
	$('#icon_mng_wrap').find('.chk_del').click(function() {
		var chk_a = $(this).is(':checked');
		if (chk_a == false) {
			$(this).removeClass('on');
			$(this).attr('checked', false);
		} else {
			$(this).addClass('on');
			$(this).attr('checked', true);
		}
	});
	//이벤트 중복 발생 방지
	$('#icon_mng_wrap').find('.iconlist').find('img').off('click');
	//이미지 클릭 시 체크박스가 선택된다.
	$('#icon_mng_wrap').find('.iconlist').find('img').click(function(){
		$(this).prev().click();
	});
}