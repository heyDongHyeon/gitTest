if(!window.user) user = {};
var idChkStat = false;

user.valid = {
	signIn : function(){
		if($("#userId").val() == ""){
			alert("아이디를 입력하여 주십시오!");
			$("#userId").focus();
			return false;
		}

		if($("#userPwd").val() == ""){
			alert("비밀번호를  입력하여 주십시오!");
			$("#userPwd").focus();
			return false;
		}

		user.login.signIn();
	},

	reg : function(){
		if($("#userId").val() == ""){
			alert("아이디를 입력하여 주십시오!");
			$("#userId").focus();
			return false;
		}

		if($("#userId").val().length < 6){
			alert("아이디는 6자리 이상 입력하여 주십시오!");
			$("#userId").focus();
			return false;
		}

		if($("#userPwd").val().length < 8){
			alert("패스워드는 8자리 이상 입력하여 주십시오!");
			$("#userPwd").focus();
			return false;
		}

		if(!$("#userPwd").val().match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)){
			alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 9~16자리로 입력해주세요.");
			$("#userPwd").focus();
			return false;
		}

		if($("#userPwd").val() != $("#userPwdRe").val()){
			alert("패스워드가 같지 않습니다.");
			$("#userPwd").focus();
			return false;
		}

		if($("#userNm").val() == ""){
			alert("사용자명을 입력하여 주십시오!");
			$("#userNm").focus();
			return false;
		}

		if($("#orgMgrNo").val() == ""){
			alert("소속기관을 선택하여 주십시오!");
			$("#orgMgrNo").focus();
			return false;
		}

		if($("#departNm").val() == ""){
			alert("부서명을 입력하여 주십시오!");
			$("#departNm").focus();
			return false;
		}

		if($("#posNm").val() == ""){
			alert("직급(직책)을 입력하여 주십시오!");
			$("#posNm").focus();
			return false;
		}

		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;

		if(filter.test($('#email').val()) != true){
			alert("이메일 형식이 아닙니다!");
			$('#email').focus();
			return false;
		}

		if($("#telNum").val() == ""){
			alert("사무실 전화번호를 입력하여 주십시오!");
			$("#telNum").focus();
			return false;
		}

		if($("#mobileNum").val() == ""){
			alert("휴대폰 번호를 입력하여 주십시오!");
			$("#mobileNum").focus();
			return false;
		}

	    if($('#file').val() === undefined || $('#file').val() == ""){
	    	alert("서약서를 첨부하여 주십시오!");
			$("#file").focus();
			return false;
	    }

		user.reg.add();
	},

	find : function(mode){
		if(mode == "pw"){
			if($("#userId").val() == ""){
				alert("아이디를 입력하여 주십시오!");
				$("#userId").focus();
				return false;
			}
			if($("#userId").val().length < 6){
				alert("아이디는 6자리 이상 입력하여 주십시오!");
				$("#userId").focus();
				return false;
			}
		}

		if($("#userNm").val() == ""){
			alert("사용자명을 입력하여 주십시오!");
			$("#userNm").focus();
			return false;
		}

		if($("#orgMgrNo").val() == ""){
			alert("소속기관을 선택하여 주십시오!");
			$("orgMgrNo").focus();
			return false;
		}

		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;

		if(filter.test($('#email').val()) != true){
			alert("이메일 형식이 아닙니다!");
			$('#email').focus();
			return false;
		}

		if($("#telNum").val() == ""){
			alert("휴대전화번호를 입력하여 주십시오!");
			$("#telNum").focus();
			return false;
		}

		if($("#birthDay").val() == ""){
			alert("생년월일을 입력하여 주십시오!");
			$("#birthDay").focus();
			return false;
		}

		user.find.inquiry(mode);
	},

	edit : function(){

		if($("#userPwd").val().length < 8){
			alert("패스워드는 8자리 이상 입력하여 주십시오!");
			$("#userPwd").focus();
			return false;
		}

		if(!$("#userPwd").val().match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)){
			alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 9~16자리로 입력해주세요.");
			$("#userPwd").focus();
			return false;
		}

		if($("#userPwd").val() != $("#userPwdRe").val()){
			alert("패스워드가 같지 않습니다.");
			$("#userPwd").focus();
			return false;
		}

		if($("#userNm").val() == ""){
			alert("사용자명을 입력하여 주십시오!");
			$("#userNm").focus();
			return false;
		}

		if($("#orgMgrNo").val() == ""){
			alert("소속기관을 선택하여 주십시오!");
			$("#orgMgrNo").focus();
			return false;
		}

		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;

		if(filter.test($('#email').val()) != true){
			alert("이메일 형식이 아닙니다!");
			$('#email').focus();
			return false;
		}

		if($("#telNum").val() == ""){
			alert("휴대전화번호를 입력하여 주십시오!");
			$("#telNum").focus();
			return false;
		}

		if($("#birthDay").val() == ""){
			alert("생년월일을 입력하여 주십시오!");
			$("#birthDay").focus();
			return false;
		}

		user.UE.edit();
	},

	editPassword : function(){

		if($("#newUserPwd").val().length < 8){
			alert("패스워드는 8자리 이상 입력하여 주십시오!");
			$("#newUserPwd").focus();
			return false;
		}

		if(!$("#newUserPwd").val().match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)){
			alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 9~16자리로 입력해주세요.");
			$("#newUserPwd").focus();
			return false;
		}

		if($("#newUserPwd").val() != $("#newUserPwdRe").val()){
			alert("패스워드가 같지 않습니다.");
			$("#newUserPwd").focus();
			return false;
		}

		user.UE.editPassword();
	},

	reset : function(){
		$("input").val("");
	}
};

/*
 로그인
 */
user.login = {
	signIn : function(){
		var TEAKey = _CipherManager.fn.generateTEAKey();

		var _PARAMETER = {
			userId : _CipherManager.fn.encryptTEA(TEAKey, $("#userId").val()),
			userPwd : _CipherManager.fn.encryptTEA(TEAKey, $("#userPwd").val()),
			key : _CipherManager.fn.encryptRSA($("#Modulus").val(), $("#Exponent").val(), TEAKey)
		};

		_common.callAjax("/user/signIn.json", _PARAMETER, function(json){
			if(json.RSAError || json.error){
				sysAlert("안전한 로그인을 위해 서버에 암호화 키를 다시 요청합니다.");
				location.reload();
			}else if(json.result == null){
				alert("계정 또는 비밀번호를 다시한번 확인해 주세요.");
				return false;
			}else{
				if(json.result.useYn == "N"){
					alert("접속권한이 존재하지 않습니다.\n관리자에게 문의하여 주십시오.");
				}else{

					var $parent = $(parent.document).contents();
					//location.href = _common.context() + "/map/view.do";
					$parent.find("#loginView").animate({
						top: $(window).height()
					}, 1000, function(){
						$parent.find("#loginView").hide();

					});

					$parent.find("#welcomView").show();
					$parent.find("#welcomView").find("div").eq(0).center().slideDown(1200);
					$parent.find("#btn-logout").removeClass("hidden");

					/*setTimeout(function(){
						var duration = 200;
						for(var i=0; i<7; i++){
							$parent.find("#main-menu-group").find(".menu-button").animate({"color" : "white"}, duration);
							$parent.find("#main-menu-group").find(".menu-button").animate({"color" : "#aaaaaa"}, duration);
						}
					}, 1200);*/
					console.log($('#main-menu-group').find(".menu-button").length);
					$parent.find("#main-menu-group .menu-button").each(function(){
							var key = $(this).attr('id');
							var $obj = $(this);
							console.log(key);

							_common.callAjax("/auth/hasAuth.json", { "authData" : key }, function(authJson){
								console.log(authJson);
									if( authJson.result ) {
										$obj.show();
									}
							}, false);
							//$('.viewWrap').hide();
							//$('#'+$(this).attr("target")).show();
						});
				};
			}
		}, false);
	}
};

/*
 사용자등록신청
 */
user.reg = {
	idChk : function(){
		if($("#userId").val() == "" || $("#userId").val() == null){
			alert("아이디를 입력하여 주십시오!");
			$("#userId").focus();
			return false;
		}

		if($("#userId").val().length < 6){
			alert("아이디는 6자리 이상 입력하여 주십시오!");
			$("#userId").focus();
			return false;
		}

		_common.callAjax("/user/getItem.json", {"userId" : $("#userId").val()}, function(json){
			if(json.result == null){
				alert("사용하셔도 좋습니다.\n나머지 항목을 입력해 주세요.");
				$("#userPwd").focus();
				idChkStat = true;
			}else{
				if(json.result.userId != ""){
					alert("입력하신 계정이 이미 존재합니다.\n다른 계정을 입력하여 주십시오.");
					$("#userId").focus();
					idChkStat = false;
				}
			}
		});
	},

	add : function(){
		if(!idChkStat){
			alert("계정 중복확인은 필수사항 입니다.");
			$("#idChk").focus();
			return false;
		}else{
			/*_common.callAjax("/user/add.json", _common.utils.collectSendData(), function(json){
				if(json.result == true){
					alert("사용자 등록을 완료했습니다.\n로그인 페이지로 이동합니다.");
					location.href = _common.context() + '/';
				}else{
					alert(json.result);
					return false;
				}
			});*/
			_common.formSubmit("/user/add.json", $("#sendForm"), function(json){
				if(json.result == true){
					//alert("사용자 등록을 완료했습니다.");//\n로그인 페이지로 이동합니다.
					confirm("사용자 등록을 완료했습니다.\r\n로그인페이지로 이동하시겠습니까?", function(){
						location.href = _common.context() + "/user/login.do";
					});
					/*location.href = _common.context() + '/';*/
				}else{
					alert(json.result);
					return false;
				}
			});
		}
	}
};

/**
 * 사용자계정문의
 */
user.find = {
	inquiry : function(mode){
		var _param = _common.utils.collectSendData();
		var _url = "/user/findAndEidt.json";
		if(mode == "id"){
			delete _param['userId'];
			_url = "/user/findId.json";
		}
		_common.callAjax(_url, _param, function(json){
			if(mode == "id"){
				if(json.result == null){
					alert("입력하신 정보와 일치하는 계정이 존재하지 않습니다.\r\n계정정보 확인 후, 다시 시도하여 주십시오.");
				}else{
					alert("입력정보와 일치하는 아이디는 "+json.result+" 입니다.");
					/*location.href = _common.context() + '/';*/
				}
			} else if(mode == "pw") {
				confirm("임시 비밀번호가 생성되었습니다.\r\n아래의 번호를 기억하신 뒤 로그인하여 주십시오.\r\n임시 비밀번호 : [ "+json.result+" ]\r\n보안을 위하여 로그인 후 비밀번호를 변경하여 주십시오.", function(){
					location.href = _common.context() + "/user/login.do";
				});
			}
		});
	}
};

/**
 * 사용자정보수정
 */
user.UE = {
	edit : function(){
		/*_common.callAjax("/user/edit.json", _common.utils.collectSendData(), function(json){
			if(json.result == null){
				alert(json.error);
			}else{
				alert("사용자 정보가 변경되었습니다.\n로그인 페이지로 이동합니다.");
				location.href = _common.context() + "/user/signOut.do";
			}
		});*/
		_common.formSubmit("/user/edit.json", $("#sendForm"), function(json){
			if(json.result == null){
				alert(json.error);
			}else{
				alert("사용자 정보가 변경되었습니다.");//\n로그인 페이지로 이동합니다.
				//location.href = _common.context() + "/user/signOut.do";
			}
		});
	},
	editPassword : function(){
		confirm("비밀번호를 변경하시겠습니까?", function(){
			_common.callAjax("/user/editPassword.json", {"nowUserPwd" : $("#nowUserPwd").val(), "newUserPwd" : $("#newUserPwd").val()}, function(json){
				if(json.result == null){
					confirm(json.error);
				}else{
					if(json.result){
						alert("비밀번호를 변경하였습니다.");
						location.reload();
					}
				}
			});
		});
	}
};