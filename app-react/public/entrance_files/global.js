var k2Func = (function () {
  //	console.log('gnb3');

  /*******************************************************
   * Common - Remove Trash CSS
   *******************************************************/
  var removeCss = function () {
    $("link[rel=stylesheet][href*='/itc-proxy/Web-home/content/skin/skin0/style.css']").remove();
    $("link[rel=stylesheet][href*='/itc-proxy/Web-home/_SITES/css/common/common.css']").remove();
    $("link[rel=stylesheet][href*='/itc-proxy/Web-home/_UI/css/lang/common_ko.css']").remove();
    $("link[rel=stylesheet][href*='/itc-proxy/Web-home/_UI/css/common/normalize.css']").remove();
  };

  /*******************************************************
   * Common - Sub Menu Nav
   *******************************************************/
  var setMenuText = function () {
    //		setTimeout(function() {
    var $getText1 = $.trim($(".head_navi .a_1._active").text());
    $(".wrap_sub_visual .container .visual_intro strong").text($getText1);
    //			console.log($getText1);
    //		}, 200);
  };

  var getMenuText = function () {
    setTimeout(function () {
      var $getText1 = $.trim($(".head_navi .a_1._active").text());
      var $getText2 = $.trim($(".head_navi .a_2._active").text());
      var $getText3 = $.trim($(".head_navi .a_3._active").text());
      var $pageTitle1 = $("#pagetitle1");
      var $pageTitle2 = $("#pagetitle2");
      var $pageTitle3 = $("#pagetitle3");

      if (jQuery.type($getText1) !== "undefined" && $getText1 != "") {
        $pageTitle1.append(
          '<button class="pageNavigationBtn" title="펼치기">' +
            $getText1 +
            '</button><ul class="pageNavigation"></ul>',
        );
      } else {
        $pageTitle1.remove();
      }

      if (jQuery.type($getText2) !== "undefined" && $getText2 != "") {
        $pageTitle2.append(
          '<button class="pageNavigationBtn" title="펼치기">' +
            $getText2 +
            '</button><ul class="pageNavigation"></ul>',
        );
      } else {
        $pageTitle2.remove();
      }

      if (jQuery.type($getText3) !== "undefined" && $getText3 != "") {
        $pageTitle3.append(
          '<button class="pageNavigationBtn" title="펼치기">' +
            $getText3 +
            '</button><ul class="pageNavigation"></ul>',
        );
      } else {
        $pageTitle3.remove();
      }
    }, 100);
  };

  var getMenuList = function () {
    setTimeout(function () {
      var getMenuList1 = function () {
        var $getMenuResult1 = $(".head_navi .a_1").each(function (index, item) {
          $("#pagetitle1 > ul").append(
            '<li><a href="' +
              $(this).attr("href") +
              '" target="' +
              $(this).attr("target") +
              '">' +
              $.trim($(this).text()) +
              "</a></li>",
          );
        });
      };

      var getMenuList2 = function () {
        var $getMenuResult2 = $(".head_navi .li_1._active .a_2").each(function (index, item) {
          $("#pagetitle2 > ul").append(
            '<li><a href="' +
              $(this).attr("href") +
              '" target="' +
              $(this).attr("target") +
              '">' +
              $.trim($(this).text()) +
              "</a></li>",
          );
        });
      };

      var getMenuList3 = function () {
        var $getMenuResult3 = $(".head_navi .li_1._active .a_2._active")
          .siblings("div")
          .find(".a_3")
          .each(function (index, item) {
            $("#pagetitle3 > ul").append(
              '<li><a href="' +
                $(this).attr("href") +
                '" target="' +
                $(this).attr("target") +
                '">' +
                $.trim($(this).text()) +
                "</a></li>",
            );
          });
      };

      getMenuList1();
      getMenuList2();
      getMenuList3();
    }, 100);
  };

  var setMenuList = function () {
    setTimeout(function () {
      var $subNavTitle = $(".sub_navi > ul > li > button");
      var $subNavDiv = $(".sub_navi > ul > li > ul");

      //			$subNavTitle.click(function () {
      $subNavTitle.on("click", function () {
        if ($(this).next("ul").css("display") == "none") {
          $(this).attr("title", "접기");
          $(this).next("ul").slideDown();
        } else {
          $(this).attr("title", "펼치기");
          $(this).next("ul").slideUp();
        }
      });
      //			$subNavDiv.mouseleave(function () {
      $subNavDiv.on("mouseleave", function () {
        $subNavDiv.slideUp();
        $(this).siblings("button").attr("title", "펼치기");
      });
    }, 200);
  };

  /*******************************************************
   * Common - SNS
   *******************************************************/
  var snsToggle = function () {
    var $snsOpenBtn = $("button.sub_share");
    var $snsCloseBtn = $(".box_sub_share button.close");
    var $snsBox = $(".box_sub_share");
    $snsOpenBtn.on("click", function () {
      $snsBox.slideDown("fast");
      return false;
    });
    $snsCloseBtn.on("click", function () {
      $snsBox.slideUp("fast");
      return false;
    });
  };

  var snsShare = function () {
    var $snsFaceBook = $(".box_sub_share .fb");
    var $snsTwitter = $(".box_sub_share .tw");
    var $snsPinter = $(".box_sub_share .pin");
    var $snsKakao = $(".box_sub_share .kakao");
    var $snsFile = $(".box_sub_share .addr");

    if ($snsFaceBook.length > 0) {
      $snsFaceBook.bind("click", function () {
        /*
                var thisTitle = $("title").html();
                popUrl = "http://www.facebook.com/sharer.php?t=" + thisTitle + "&u=" + encodeURIComponent(document.location.href);
                window.open(popUrl, "FACEBOOK");
                */
        shareFacebook();
      });
    }

    if ($snsTwitter.length > 0) {
      $snsTwitter.bind("click", function () {
        /*
                var thisTitle = $("title").html();
                popUrl = "http://twitter.com/share?text=" + encodeURIComponent(thisTitle) + "&url=" + encodeURIComponent(document.location.href);
                window.open(popUrl, "TWITTER");
                */
        shareTwitter();
      });
    }

    if ($snsPinter.length > 0) {
      $snsPinter.bind("click", function () {
        /*
                var url = encodeURI(encodeURIComponent(document.location.href));
                var title = encodeURI($("title").html());
                popUrl = "http://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
                window.open(popUrl, "BLOG");
                */
        sharePinterest();
      });
    }

    if ($snsKakao.length > 0) {
      $snsKakao.bind("click", function () {
        shareKakaoStory();
      });
    }

    if ($snsFile.length > 0) {
      $snsFile.bind("click", function () {
        copy_to_clipboard();
      });
    }
  };

  /*******************************************************
   * Common - Table Scroll
   *******************************************************/
  var tableScroll = function () {
    setTimeout(function () {
      var tableIcon = $(".con-table");
      tableIcon.on("click", function () {
        $(".con-table").niceScroll({
          cursorcolor: "rgba(1,120,221,1)",
          cursorwidth: "1px",
          cursorborder: "0px solid rgba(2,81,148,1)",
          cursorborderradius: 0,
          cursoropacitymin: 0,
          autohidemode: "leave",
          scrollspeed: 0,
          zindex: 100,
          mousescrollstep: 30,
          railpadding: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
          },
        });
        $(this).addClass("on");
      });
    }, 100);
  };

  /*******************************************************
   * Common - Random Sub Visual
   *******************************************************/
  var subVisual = function () {
    setTimeout(function () {
      var subVisual = $("#wrap-visual #visual");
      var active1Depth = $("#gnb .li_1.eQ01");
      var active2Depth = $("#gnb .li_1.eQ02");
      var active3Depth = $("#gnb .li_1.eQ03");
      var active4Depth = $("#gnb .li_1.eQ04");
      var active5Depth = $("#gnb .li_1.eQ05");

      subVisual.removeClass();

      if (active1Depth.hasClass("_menuOn")) {
        subVisual.addClass("m1");
      } else if (active2Depth.hasClass("_menuOn")) {
        subVisual.addClass("m2");
      } else if (active3Depth.hasClass("_menuOn")) {
        subVisual.addClass("m3");
      } else if (active4Depth.hasClass("_menuOn")) {
        subVisual.addClass("m4");
      } else if (active5Depth.hasClass("_menuOn")) {
        subVisual.addClass("m5");
      } else {
        subVisual.addClass("none");
      }
    }, 300);
  };

  /*******************************************************
   * Common - Sub Tab Length
   *******************************************************/
  var tabSize = function () {
    var tabLi = $(".wrap-contents .tab ul li");
    var tabLen = tabLi.length;
    var tabPer = 100 / tabLen + "%";
    if (tabLen < 4) {
      tabLi.css("width", tabPer);
    } else {
      tabLi.css("width", "25%");
    }
  };

  /*******************************************************
   * Common - Sub Util
   *******************************************************/
  var subUtil = function () {
    var $favoBtn = $(".sub_util .sub_favo");
    var $favoBox = $(".sub_util .box_sub_favo");
    var $favoClose = $(".sub_util .box_sub_favo .close");
    var $favoAddBtn = $(".sub_util .add");
    var $favoResetBtn = $(".sub_util .reset");

    // 즐겨찾기 창 열기 //
    $favoBtn.on("click", function () {
      $favoBox.slideDown();
    });

    // 즐겨찾기 창 닫기 //
    $favoClose.on("click", function () {
      $favoBox.slideUp();
    });

    // 즐겨찾기 추가 //
    $favoAddBtn.on("click", function () {
      var pathMenuSeqs = $("#pathMenuSeqs").val().split(",");
      var menu = "";
      for (var i = 1; i < pathMenuSeqs.length; i++) {
        if (i == pathMenuSeqs.length - 1) {
          menu += "<strong>" + $("#top_menuTitle_" + pathMenuSeqs[i]).val() + "</strong>";
        } else {
          menu += "<span>" + $("#top_menuTitle_" + pathMenuSeqs[i]).val() + " > " + "</span>";
        }
      }
      favoriteSetCookie(menu);
      $favoBtn.focus(); // ie11 때문에 포커스를 강제로 이동
    });

    // 즐겨찾기 초기화 //
    $favoResetBtn.on("click", function () {
      favoriteRemoveCookieAll();
    });
  };

  /*******************************************************
   * Common - Sub Tab Mobile Munu List
   *******************************************************/
  var subMenuList = function () {
    setTimeout(function () {
      var tabEle = $(".wrap_contents .tab .tab_div");
      var d4Active = $(".wrap_contents .tab ul li._active a");
      var d4Clone = d4Active.clone();
      tabEle.prepend(d4Clone);

      var d4Click = $(".wrap_contents .tab .tab_div > a");
      d4Click.on("click", function (e) {
        e.preventDefault();
        $(this).toggleClass("open");
        $(this).siblings().toggleClass("open");
      });
    }, 200);

    setTimeout(function () {
      var tabEle = $(".tab2");
      var d5Active = $(".wrap_contents .tab2 > ul > li._on > a");
      var d5Clone = d5Active.clone();
      tabEle.prepend(d5Clone);

      var d5Click = $(".wrap_contents .tab2 > a");
      d5Click.on("click", function (e) {
        e.preventDefault();
        $(this).toggleClass("open");
        $(this).siblings().toggleClass("open");
      });
    }, 200);
  };

  /*******************************************************
   * Common - Page Arrow
   *******************************************************/
  var pageArrow = function () {
    if ($("body").hasClass("sub")) {
      setTimeout(function () {
        var activePage = $(".wrap_header .head_navi .a_1._active");
        var activePrev = activePage.parent().prev().children("a");
        var activeNext = activePage.parent().next().children("a");
        var movePrev = $(".wrap_sub_visual a.prev");
        var moveNext = $(".wrap_sub_visual a.next");
        var href = "",
          txt = "",
          target = "";

        if (activePrev.length > 0) {
          href = activePrev.attr("href");
          target = activePrev.attr("target");
          txt = $.trim(activePrev.text());
          movePrev.attr("href", href).attr("target", target).text(txt).show();
        } else {
          movePrev.hide();
        }

        if (activeNext.length > 0) {
          href = activeNext.attr("href");
          target = activeNext.attr("target");
          txt = $.trim(activeNext.text());
          moveNext.attr("href", href).attr("target", target).text(txt).show();
        } else {
          moveNext.hide();
        }
      }, 200);
    }
  };

  /*******************************************************
   * Common - Go To Top
   *******************************************************/
  var gotoTop = function () {
    var btnTop = $(".wrap_page_top");

    btnTop.on("click", function () {
      $("html, body").stop().animate({ scrollTop: "0" }, 680);
    });

    $(window).scroll(function () {
      if ($(window).scrollTop() + $(window).height() == $(document).height()) {
        btnTop.addClass("on");
      } else {
        btnTop.removeClass("on");
      }

      var scrollValue = $(document).scrollTop();

      if (scrollValue > 200) {
        btnTop.addClass("active");
      } else {
        btnTop.removeClass("active");
      }
    });

    /*
        $(window).scroll(function () {
            var footerPos = $('.wrap-footer .container').offset().top - 1000;

            var scroll = $(window).scrollTop();
            if (scroll > footerPos) {
                btnTop.addClass('on');
            } else {
                btnTop.removeClass('on');
            }
        });
        */
  };

  /*******************************************************
   * Common - Print
   *******************************************************/
  var printElement = function () {
    var btnPrint = $(".sub_print");
    btnPrint.on("click", function () {
      print();
    });
  };

  return {
    //Remove Css
    removeCss: removeCss(),

    // Sub Menu Text
    setMenuText: setMenuText(),

    // Sub Menu Nav
    getMenuText: getMenuText(),
    getMenuList: getMenuList(),
    setMenuList: setMenuList(),

    //Sns Toggle
    snsToggle: snsToggle(),

    //Sns Share
    snsShare: snsShare(),

    //Table Scroll
    //        tableScroll: tableScroll(),

    //Sub Visual
    //		subVisual: subVisual(),

    //Tab Size
    //		tabSize: tabSize(),

    //Sub Util
    subUtil: subUtil(),

    //Sub Menu List
    //		subMenuList: subMenuList(),

    //Page Arrow
    pageArrow: pageArrow(),

    //Go to top
    gotoTop: gotoTop(),

    //Print
    printElement: printElement(),

    //GNB
    gnb: function () {
      //			console.log('gnb1');
      setMenuText();
    },
  };
})();

window.addEventListener("load", function (e) {
  k2Func.gnb();
});
//window.addEventListener('resize', function (e) { k2Func.gnb(); });

$(document).ready(function () {
  // 편집모드 //
  if ($(location).attr("href").indexOf("contentsHtmlView.do") > -1) {
    $("body").addClass("editMode");
  }

  // 개발기간중에만 사용하는 모바일화면 보기 버튼 //
  $("#viewMobileIt").on("click", function (e) {
    e.preventDefault();
    var top = (window.screen.availHeight - 768) / 2;
    var left = (window.screen.availWidth - 420) / 2;
    if (top < 0) top = 0;
    if (left < 0) left = 0;
    window.open(
      $(location).attr("href"),
      "_blank",
      "toolbar=yes, scrollbars=yes, resizable=no, top=" +
        top +
        ", left=" +
        left +
        ", width=420, height=768",
    );
  });

  // div_2 감싸기 //
  $(".div_2").wrapInner('<div class="div_2_inner"/>');

  /* 하단 주요시스템 바로가기 */
  var scFst = "";
  scFst += '<li><a href="https://portal.inhatc.ac.kr" title="새창" target="_blank">포털</a></li>';
  scFst +=
    '<li><a href="https://icims.inhatc.ac.kr/intra/" title="새창" target="_blank">종합정보</a></li>';
  scFst +=
    '<li><a href="https://ncs.inhatc.ac.kr" title="새창" target="_blank">NCS학사운영</a></li>';
  scFst += '<li><a href="https://cyber.inhatc.ac.kr" title="새창" target="_blank">이러닝</a></li>';
  scFst +=
    '<li><a href="https://attend.inhatc.ac.kr" title="새창" target="_blank">전자출결</a></li>';
  scFst +=
    '<li><a href="https://job.inhatc.ac.kr" title="새창" target="_blank">일자리(취업지원)</a></li>';
  scFst +=
    '<li><a href="https://gohanway.inhatc.ac.kr" title="새창" target="_blank">한웨이</a></li>';
  scFst +=
    '<li><a href="https://library.inhatc.ac.kr" title="새창" target="_blank">도서관</a></li>';
  scFst +=
    '<li><a href="https://ums.inhatc.ac.kr" title="새창" target="_blank">통합메시징</a></li>';
  scFst +=
    '<li><a href="https://inhatc.rndbiz.co.kr" title="새창" target="_blank">산단회계관리</a></li>';
  scFst +=
    '<li><a href="https://mretailweb.tmapparking.co.kr/home" title="새창" target="_blank">주차비정산</a></li>';
  scFst +=
    '<li><a href="https://elive.inhatc.ac.kr" title="새창" target="_blank">ITC-eLIVE</a></li>';
  scFst +=
    '<li><a href="https://cloud.inhatc.ac.kr" title="새창" target="_blank">클라우드</a></li>';
  scFst += '<li><a href="https://dorm.inhatc.ac.kr" title="새창" target="_blank">생활관</a></li>';
  $("._fst > ul").append(scFst);

  /* 하단 학과 바로가기 */
  var scSnd = "";
  scSnd += "<li>";
  scSnd += "<div>기계공학부</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="https://mecheng.inhatc.ac.kr" title="새창" target="_blank">기계공학과</a></li>';
  scSnd +=
    '<li><a href="https://md.inhatc.ac.kr" target="_blank" title="새창">기계설계공학과</a></li>';
  scSnd +=
    '<li><a href="https://mecha.inhatc.ac.kr" target="_blank" title="새창">메카트로닉스공학과</a></li>';
  scSnd +=
    '<li><a href="https://maintenance.inhatc.ac.kr/" title="새창" target="_blank">반도체기계정비학과</a></li>';
  scSnd +=
    '<li><a href="https://sos.inhatc.ac.kr" target="_blank" title="새창">조선기계공학과</a></li>';
  scSnd +=
    '<li><a href="https://ame.inhatc.ac.kr" target="_blank" title="새창">항공기계공학과</a></li>';
  scSnd +=
    '<li><a href="https://auto.inhatc.ac.kr" target="_blank" title="새창">자동차공학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>IT융합공학부</div>";
  scSnd += "<ul>";
  scSnd += '<li><a href="https://ee.inhatc.ac.kr" target="_blank" title="새창">전기공학과</a></li>';
  scSnd += '<li><a href="https://er.inhatc.ac.kr" target="_blank" title="새창">전자공학과</a></li>';
  scSnd +=
    '<li><a href="https://inc.inhatc.ac.kr" target="_blank" title="새창">정보통신공학과</a></li>';
  scSnd +=
    '<li><a href="https://cs.inhatc.ac.kr" target="_blank" title="새창">컴퓨터정보공학과</a></li>';
  scSnd +=
    '<li><a href="https://cse.inhatc.ac.kr" target="_blank" title="새창">컴퓨터시스템공학과</a></li>';
  scSnd +=
    '<li><a href="https://dgmarketing.inhatc.ac.kr" title="새창" target="_blank">디지털마케팅공학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>지구환경신소재공학부</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="https://civil.inhatc.ac.kr" target="_blank" title="새창">건설환경공학과</a></li>';
  scSnd +=
    '<li><a href="https://gisup.inhatc.ac.kr" target="_blank" title="새창">공간정보빅데이터학과</a></li>';
  scSnd +=
    '<li><a href="https://chem.inhatc.ac.kr" target="_blank" title="새창">화학생명공학과</a></li>';
  scSnd +=
    '<li><a href="https://mse.inhatc.ac.kr" target="_blank" title="새창">재료공학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>건축디자인학부</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="https://archi.inhatc.ac.kr" target="_blank" title="새창">건축학과</a></li>';
  scSnd +=
    '<li><a href="https://interior.inhatc.ac.kr" target="_blank" title="새창">실내건축학과</a></li>';
  scSnd +=
    '<li><a href="https://industrydesign.inhatc.ac.kr" target="_blank" title="새창">산업디자인학과</a></li>';
  scSnd +=
    '<li><a href="https://fashion.inhatc.ac.kr" target="_blank" title="새창">패션디자인학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>서비스경영학부</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="https://cbncrew.inhatc.ac.kr" target="_blank" title="새창">항공운항과</a></li>';
  scSnd +=
    '<li><a href="https://asm.inhatc.ac.kr" target="_blank" title="새창">항공경영학과</a></li>';
  scSnd +=
    '<li><a href="https://tour.inhatc.ac.kr" target="_blank" title="새창">관광경영학과</a></li>';
  scSnd +=
    '<li><a href="https://secretary.inhatc.ac.kr" target="_blank" title="새창">경영비서학과</a></li>';
  scSnd +=
    '<li><a href="https://hotel.inhatc.ac.kr" target="_blank" title="새창">호텔경영학과</a></li>';
  scSnd +=
    '<li><a href="https://logis.inhatc.ac.kr/" title="새창" target="_blank">물류시스템학과</a></li>';
  scSnd +=
    '<li><a href="https://sports.inhatc.ac.kr/" title="새창" target="_blank">스포츠헬스케어학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>학사학위전공심화</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="/kr/199/subview.do" title="새창" target="_blank">컴퓨터시스템공학과</a></li>';
  scSnd +=
    '<li><a href="/kr/200/subview.do" title="새창" target="_blank">컴퓨터정보공학과</a></li>';
  scSnd += '<li><a href="/kr/201/subview.do" title="새창" target="_blank">건축학과</a></li>';
  scSnd += '<li><a href="/kr/202/subview.do" title="새창" target="_blank">실내건축학과</a></li>';
  scSnd += '<li><a href="/kr/203/subview.do" title="새창" target="_blank">항공기계공학과</a></li>';
  scSnd += '<li><a href="/kr/204/subview.do" title="새창" target="_blank">자동차공학과</a></li>';
  scSnd += '<li><a href="/kr/205/subview.do" title="새창" target="_blank">건설환경공학과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";
  scSnd += "<div>계약학과</div>";
  scSnd += "<ul>";
  scSnd +=
    '<li><a href="/kr/481/subview.do" title="새창" target="_blank">스마트기계설계과</a></li>';
  scSnd +=
    '<li><a href="/kr/482/subview.do" title="새창" target="_blank">첨단스마트자동차과</a></li>';
  scSnd += "</ul>";
  scSnd += "</li>";
  scSnd += "<li>";

  $("._snd > ul").append(scSnd);

  /* 하단 부속시관 바로가기 */
  var scLast = "";
  scLast +=
    '<li><a href="https://library.inhatc.ac.kr" title="새창" target="_blank">도서관</a></li>';
  scLast +=
    '<li><a href="https://dorm.inhatc.ac.kr" title="새창" target="_blank">생활관(기숙사)</a></li>';
  scLast +=
    '<li><a href="https://lifelong.inhatc.ac.kr" title="새창" target="_blank">평생교육원</a></li>';
  scLast +=
    '<li><a href="https://oia.inhatc.ac.kr" title="새창" target="_blank">국제교류원</a></li>';
  scLast +=
    '<li><a href="https://itcenter.inhatc.ac.kr" title="새창" target="_blank">전산정보원</a></li>';
  scLast +=
    '<li><a href="https://iacf.inhatc.ac.kr" title="새창" target="_blank">산학협력단</a></li>';
  scLast +=
    '<li><a href="https://slrc.inhatc.ac.kr" title="새창" target="_blank">학생상담ㆍ인권센터</a></li>';
  scLast +=
    '<li><a href="https://startup.inhatc.ac.kr" title="새창" target="_blank">창업지원센터</a></li>';
  scLast +=
    '<li><a href="https://icee.inhatc.ac.kr" title="새창" target="_blank">공학기술교육센터</a></li>';
  scLast +=
    '<li><a href="https://caid.inhatc.ac.kr" title="새창" target="_blank">인공지능ㆍ빅데이터센터</a></li>';
  scLast +=
    '<li><a href="https://cstem.inhatc.ac.kr" title="새창" target="_blank">신산업융합기술지원센터</a></li>';
  scLast +=
    '<li><a href="https://emu.inhatc.ac.kr" title="새창" target="_blank">e-MU운영센터</a></li>';
  scLast +=
    '<li><a href="https://ptech.inhatc.ac.kr" title="새창" target="_blank">일학습병행공동훈련센터</a></li>';
  scLast +=
    '<li><a href="https://itcc.inhatc.ac.kr" title="새창" target="_blank">산업전환공동훈련센터</a></li>';
  scLast +=
    '<li><a href="https://bach.inhatc.ac.kr" title="새창" target="_blank">학사학위운영센터</a></li>';
  //scLast += '<li><a href="https://cms.itc.ac.kr/site/Inha-Inno/main.do" title="새창" target="_blank">대학혁신사업단</a></li>';
  scLast +=
    '<li><a href="https://snbs.inhatc.ac.kr" title="새창" target="_blank">학보방송사</a></li>';
  scLast +=
    '<li><a href="https://itcsc.inhatc.ac.kr" title="새창" target="_blank">총학생회</a></li>';
  //  scLast += '<li><a href="https://assembly.inhatc.ac.kr" title="새창" target="_blank">대의원회</a></li>';
  scLast +=
    '<li><a href="https://ipa.inhatc.ac.kr" title="새창" target="_blank">교수협의회</a></li>';
  $("._last > ul").append(scLast);

  // 통합검색 //
  $(".openSearch").on("click", function (e) {
    e.preventDefault();
    $(".wrap_search").slideDown();
  });
  $(".closeSearch").on("click", function (e) {
    e.preventDefault();
    $(".wrap_search").slideUp();
    $(".openSearch").focus();
  });

  // 주요사이트 //
  $(".open_site").on("click", function (e) {
    e.preventDefault();
    $(this).next(".site_list").slideToggle();
    $(this).toggleClass("active");
    if ($(this).hasClass("active")) $(this).attr("title", "접기");
    else $(this).attr("title", "펼치기");
  });
});

$(window).scroll(function () {
  var scrollCurrent = $(document).scrollTop();
  if (scrollCurrent > 0) $("body").addClass("fix");
  else $("body").removeClass("fix");
});

// 즐겨찾기 //
function favoriteGetCookie(url) {
  var allCookies = decodeURIComponent(document.cookie);
  var pos = allCookies.indexOf(url); // pos가 -1 이면 해당 쿠키가 없다.
  var ret = true;
  if (pos == -1) {
    ret = false;
    return ret;
  } else {
    ret = true;
    return ret;
  }
}
function favoriteSetCookie(navi) {
  var currentUrl = location.href;
  var ret = favoriteGetCookie(currentUrl);

  if (ret) {
    alert("이미 즐겨찾는 메뉴로 등록되어 있습니다.");
    favoriteGetCookieList();
  } else {
    var menuCnt = favoriteGetMenuCookieNumber();
    if (menuCnt > 5) {
      alert("더 이상 등록 할수 없습니다.");
    } else {
      var menu = "menu" + menuCnt;
      var name = navi + "@";
      var todayDate = new Date();
      todayDate.setDate(todayDate.getDate() + 1);
      document.cookie =
        encodeURIComponent(menu) +
        "=" +
        encodeURIComponent(name + currentUrl) +
        "; path=/; expires=" +
        todayDate.toGMTString() +
        ";";
      favoriteGetCookieList();
    }
  }
}
function favoriteGetCookieList() {
  var currentPage = location.href;
  var ret = favoriteGetCookie(currentPage);
  //	var favoriteTitleButton = document.getElementById("favoriteTitleButton");

  if (ret) {
    $(".favoriteTitle").html(
      "<strong>이미 즐겨찾는 메뉴로 등록되어 있습니다.</strong>(즐겨찾는 메뉴는 최근 등록한 5개 메뉴가 노출됩니다)",
    );
    $(".box_sub_favo .control button.add").hide();
  } else {
    $(".favoriteTitle").html(
      "<strong>현재 페이지를 즐겨찾는 메뉴로 등록하시겠습니까?</strong>(즐겨찾는 메뉴는 최근 등록한 5개 메뉴가 노출됩니다)",
    );
    $(".box_sub_favo .control button.add").show();
  }

  var allCookies = decodeURIComponent(document.cookie);
  var strCnt = 4;
  var favoListHtml = "";

  for (var i = 1; i < 6; i++) {
    var name = "";
    name = "menu" + i;
    var pos = allCookies.indexOf(name + "="); // pos가 -1 이면 해당 쿠키가 없다.
    if (pos > -1) {
      var start = pos + strCnt + 1;
      var end = allCookies.indexOf(";", start);
      if (end == -1) end = allCookies.length;
      var value = allCookies.substring(start, end);

      var idx = value.indexOf("@", 0);
      var menu = value.substring(1, idx);
      var menuUrl = value.substring(idx + 1, value.length);

      //			var html = '<a href="'+ menuUrl + '">' + menu + '</a><a href="javascript:favoriteRemoveCookie(\'menu' + i + '\');" class="delF">삭제</a>';
      //			$('#menu' + i).html(html);

      favoListHtml +=
        '<li id="menu' +
        i +
        '"><a href="' +
        menuUrl +
        '">' +
        menu +
        '</a><button type="button" onclick="favoriteRemoveCookie(\'menu' +
        i +
        '\');" class="del">삭제</button></li>';
    }
  }

  if (favoListHtml != "") {
    if ($("#favoriteList").length < 1) {
      favoListHtml = '<ul id="favoriteList">' + favoListHtml + "</ul>";
      $(".box_sub_favo .control").before(favoListHtml);
    } else {
      $("#favoriteList").empty().append(favoListHtml);
    }
    $(".box_sub_favo .control button.reset").show();
  } else {
    $("#favoriteList").remove();
    $(".box_sub_favo .control button.reset").hide();
  }
}
function favoriteGetMenuCookieNumber() {
  var allCookies = decodeURIComponent(document.cookie);
  var cnt = 1;
  var name = "menu" + cnt;
  var strCnt = name.length;

  for (var i = 1; i < 6; i++) {
    var pos = allCookies.indexOf(name + "="); // pos가 -1 이면 해당 쿠키가 없다.
    if (pos <= -1) {
      if (i == 1) {
        return 1;
      } else {
        return cnt;
      }
    } else {
      cnt++;
      name = "";
      name = "menu" + cnt;
    }
  }
  return cnt;
}
function favoriteRemoveCookie(menu) {
  document.cookie = encodeURIComponent(menu) + "=" + " " + "; path=/; max-age=" + 0;
  //	document.getElementById(menu).innerHTML = "";
  $("#" + menu).remove();
  favoriteGetCookieList();
  $(".btnFavorite").focus(); // ie11 때문에 포커스를 강제로 이동
}
function favoriteRemoveCookieAll() {
  var name = "menu";
  for (var i = 1; i < 6; i++) {
    name += i;
    document.cookie = encodeURIComponent(name) + "=" + " " + "; path=/; max-age=" + 0;
    name = "menu";
  }

  //	$('#menu1, #menu2, #menu3, #menu4, #menu5').html("");
  $(".favoriteTitle").html(
    "<strong>현재 페이지를 즐겨찾는 메뉴로 등록하시겠습니까?</strong>(즐겨찾는 메뉴는 최근 등록한 5개 메뉴가 노출됩니다)",
  );
  $("#favoriteList").remove();
  $(".box-sub-favo .control button.reset").hide();
  $(".box-sub-favo .control button.add").show();
}

// SNS //
function shareFacebook() {
  var s = location.href;
  var popUrl =
    "http://www.facebook.com/sharer.php?t=" +
    encodeURIComponent(document.title) +
    "&u=" +
    encodeURIComponent(s);
  window.open(popUrl, "facebook");
}
function shareTwitter() {
  var s = location.href;
  var popUrl =
    "http://twitter.com/share?text=" +
    encodeURIComponent(document.title) +
    "&url=" +
    encodeURIComponent(s);
  window.open(popUrl, "twitter");
}
function sharePinterest() {
  var s = location.href;
  var popUrl =
    "http://www.pinterest.com/pin/create/button/?url=" +
    encodeURIComponent(s) +
    "&description=" +
    encodeURIComponent(document.title);
  window.open(popUrl, "pinterest");
}
function shareNaver() {
  var s = location.href;
  var popUrl =
    "http://blog.naver.com/openapi/share?url=" +
    encodeURIComponent(s) +
    "&title=" +
    encodeURIComponent(document.title);
  window.open(popUrl, "naver");
}
function shareKakaoStory() {
  var s = location.href;
  var popUrl =
    "https://story.kakao.com/share?url=" +
    encodeURIComponent(s) +
    "&text=" +
    encodeURIComponent(document.title);
  window.open(popUrl, "facebook");
}
function copy_to_clipboard() {
  var copyUrl = "";
  if (copyUrl == "") {
    copyUrl = location.href;
  }
  var IE = document.all ? true : false;
  if (IE) {
    window.clipboardData.setData("Text", copyUrl);
    alert(copyUrl + " 복사되었습니다.");
  } else {
    temp = prompt(" Ctrl+c 를 눌러 복사하십시오. ", copyUrl);
  }
}

// COOKIE //
function setCookie(name, value, expiredays) {
  var todayDate = new Date();
  todayDate.setDate(todayDate.getDate() + expiredays);
  document.cookie =
    name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}
function getCookie(name) {
  var nameOfCookie = name + "=";
  var x = 0;
  while (x <= document.cookie.length) {
    var y = x + nameOfCookie.length;
    if (document.cookie.substring(x, y) == nameOfCookie) {
      if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)
        endOfCookie = document.cookie.length;
      return unescape(document.cookie.substring(y, endOfCookie));
    }
    x = document.cookie.indexOf(" ", x) + 1;
    if (x == 0) break;
  }
  return;
}
