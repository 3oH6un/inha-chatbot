$(function(){
	$("body").addClass("_msMain");
	//*** 메뉴 활성화 ********************************************//
	if($("#pathMenuSeqs").size() > 0){
		var pathMenuSeqs = $("#pathMenuSeqs").val().split(",");
		var tempClass = "";
		$(".top_k2wiz_GNB").each(function(){
			$(this).find(".li_1").each(function(i){
				for(var j=0;j<pathMenuSeqs.length;j++){
					var menuSeq = pathMenuSeqs[j];
					var activeTop = $(".top_k2wiz_GNB_"+menuSeq);
					var activeSub = $(".sub_k2wiz_GNB_"+menuSeq);
					var activeTab = $(".tab_k2wiz_GNB_"+menuSeq);

					$(activeTop).addClass("_active");
					$(activeSub).addClass("_active");
					$(activeTab).addClass("_active");

					$(activeTop).parent(".li_1").addClass("_active _menuOn");
					$(activeSub).parent("li").addClass("_active");
					$(activeTab).parent("li").addClass("_active");
				}

				if( $(this).hasClass("_menuOn") ){
					tempClass = "eQ";
					var num = i+1;
					if(num<10){
						tempClass += "0"+num;
					}else{
						tempClass += num;
					}
				}
			});
		});

		$("body").addClass(tempClass);

		var findmenuUItop = $(".menuUItop").length;
		if (findmenuUItop > 0 ) {
			$(".menuUItop").find("li").children("div").addClass("_childDiv");
			$("._childDiv").parent("li").addClass("_useChildDiv");
		} else {
		}
	}

	$(".menuUItop,.menuUIsub,.menuUItab").each(function(){
		//$(this).removeAttr("id");
		$(this).find("div,ul,li").removeAttr("id");
	});

	$(".menuUItop ul.ul_1 li.li_1 .div_2").append("<span class=\"navGab\"></span>");
});