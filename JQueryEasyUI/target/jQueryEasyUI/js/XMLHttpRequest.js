var xmlHttp

function GetXmlHttpObject() {
	var XMLHttp=null;
	try {
		XMLHttp=new ActiveXObject("Msxml2.XMLHTTP");
	} catch(e) {
 		try  {
 			XMLHttp=new ActiveXObject("Microsoft.XMLHTTP");
 		} catch (EE) {}
	}
	if (XMLHttp==null) {
		XMLHttp=new XMLHttpRequest();
	}
	return XMLHttp;
}
function doCheckBoxRef(name, val, opt) {

}
function buildPOST(theFormName) {
	theForm = document.forms[theFormName];
	var qs = ''
	for (e=0;e<theForm.elements.length;e++) {
		if (theForm.elements[e].name!='') {
			var name = theForm.elements[e].name;
			qs+=(qs=='')?'':'&'
			qs+= name+'='+escape(theForm.elements[e].value);
		}
	}
	qs+="\n";
	return qs
}
function selectOnDemand(theAttrId, theVal, typeInVal) {
  if(typeInVal.length < 1)
  	return;
	var url="../../jsp/common/autoSelect.jsp";
	para ="aid="+theAttrId + "&val=" +  (theVal) + "&sid=" + Math.random() + "&t=" +  (typeInVal);
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
				document.getElementById("S_" + theAttrId).innerHTML= xmlHttpS.responseText;
			}
	   }
	 xmlHttpS.open("POST",url,false);
         xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
 	 xmlHttpS.send(para);
}
function selectOnDemandEmployee(extTag,  theVal, typeInVal) {
  	if(typeInVal.length < 1)
  		return;
  	var url="../../jsp/common/autoSelectEmp.jsp";
  	var para ="tag=" + extTag + "&val=" +  (theVal) + "&sid=" + Math.random() + "&t=" +  (typeInVal);
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			var vvv= xmlHttpS.responseText;
			document.getElementById("S_" + extTag).innerHTML= vvv;
		}
   }
   xmlHttpS.open("POST",url,false);
   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
   xmlHttpS.send(para);
}
function checkUniqueKeyOne(objTypeName, uniqueKeyDefId, curInstId,existVal, others, dispName) {
	if(uniqueKeyDefId.length <2)
		return true;
	if(existVal.length < 1)
  		return true; 	
  	otherInfo = "";
  	if(others.length >2) {
  		var xother = others.split("|");
  		for(i =0; i< xother.length; i++) {
  			if(i > 0)
  				otherInfo += "|";
  			cc = Trim(eval("document.aform." + xother[i]).value);
  			
  			if(cc.length < 1)
  			 	otherInfo += "1";
  			else otherInfo += cc;	
   		}
  	}
  	var url="../../../servlet/CommonServlet";
  	var para ="cmd=checkUniqueKey&uniqueKeyDefId="+uniqueKeyDefId +  "&existVal=" +  Trim(existVal) + "&TYPE_NAME=" + objTypeName + "&PRI_KEY=" + curInstId + "&others=" + otherInfo;
	xmlHttpS=GetXmlHttpObject();
	OK = false;
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			var rrs = Trim(xmlHttpS.responseText);
			if(!isNaN(rrs)) {
				var vvv= parseInt(rrs);
				if(vvv < 1) {
				     OK = true;
				}
				else {
				   alert(dispName + "必须是唯一的, 您填写的   " + existVal + " 已占用,请您输入别的" + dispName);
				   OK = false;	
				}
			}
			return OK;
		}
	   }
	   xmlHttpS.open("POST",url,false);
	   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	   xmlHttpS.send(para);
	   //if(!OK)
		   //alert(dispName + "必须是唯一的, 您填写的   " + existVal + " 已占用,请您输入别的" + dispName);
	   return OK;
   
}
function selectWithAction(nextActionName, theDefId, valuex) {
	if(valuex.length < 1)
  		return;
  	var url="../../../servlet/CommonServlet";
  	var para ="cmd=selectWithAction&defId="+theDefId +  "&val=" +  valuex + "&sid=" + Math.random();
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			var vvv= Trim(xmlHttpS.responseText);
			 var infos = vvv.split("|");
		 	var i;
			var elSel = eval("document.aform." +nextActionName);
			for (i = elSel.length - 1; i>=0; i--) {
			     if (elSel.options[i].selected) {
			       elSel.remove(i);
			     }
			 }
			for(m=0; m< infos.length; m++) {
				var minfo = infos[m].split(":");
				eval("document.aform." + nextActionName)[m] = new Option(minfo[1], minfo[0]);
			}
			 
		}
	   }
	   xmlHttpS.open("POST",url,false);
	   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=UTF-8");
	   xmlHttpS.send(para);
}

function selectOnDemandMutil(extTag, theAttrId, theVal, typeInVal) {
  	if(typeInVal.length < 1)
  		return;
  	var url="../../jsp/common/autoSelectTag.jsp";
  	var para ="aid="+theAttrId + "&tag=" + extTag + "&val=" +  (theVal) + "&sid=" + Math.random() + "&t=" +  (typeInVal);
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			var vvv= xmlHttpS.responseText;
			document.getElementById(extTag + "_" + theAttrId).innerHTML= vvv;
		}
   }
   xmlHttpS.open("POST",url,false);
   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=UTF-8");
   xmlHttpS.send(para);
}
function selectOnDemandAddWithAddition(extTag, theAttrId, theVal, typeInVal, additionURL) {
 	if(typeInVal.length < 1)
  		return;
  	
  	var url="../../jsp/common/autoSelectTagAdd.jsp";
  	var para ="aid="+theAttrId + "&tag=" + extTag + "&val=" +  (theVal) + "&sid=" + Math.random() + "&t=" +  (typeInVal) + additionURL;
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			var vvv= xmlHttpS.responseText;
			selectOnDemandAjax(extTag,theAttrId, theVal, vvv);
		}
   }
   xmlHttpS.open("POST",url,true);
   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=UTF-8");
   xmlHttpS.send(para);
}
function selectOnDemandAdd(extTag, theAttrId, theVal, typeInVal) {
	selectOnDemandAddWithAddition(extTag, theAttrId, theVal, typeInVal, "");
}
function selectOnDemandAjax(extTag, theAttrId, theVal, searchRs1) {
	if(searchRs1 == null || searchRs1 == 'undefined')
		return;
	searchRs = searchRs1.replace(/\r|\n|\r\n/g, "");
	if(searchRs.length ==0)
		return;
 	var infos = searchRs.split("`");
	var rowinfos = new Array();
 
	for(i=0; i< infos.length; i++) {
		var aa = new Array("", "");
		ar_f = infos[i].split("!");
		if(ar_f.length >1) {
			aa[0] = ar_f[0];
			aa[1] = ar_f[1];
			rowinfos[i] = aa;
		}
	}
	//rowinfos.sort();
	var selObj = document.getElementById (extTag);
	if(selObj.length > 0) {
	     selObj.length =0;
	}
	var j=0;
	for(j=0; j< rowinfos.length; j++) {
	       var xin = rowinfos[j];
	       if(xin.length > 1) {
	       	selObj.options[j] = new Option (xin[1], xin[0]);
	       }
	}
      	selObj.options[j] = new Option ("", "");

	selObj.selectedIndex = 0;
}
function sizeFrame(frameId) {
	var F = document.getElementById(frameId);
	if(F.contentDocument) 
		F.height = F.contentDocument.documentElement.scrollHeight+30; //FF 3.0.11, Opera 9.63, and Chrome
	else 
		F.height = F.contentWindow.document.body.scrollHeight+30; //IE6, IE7 and Chrome
}


function selectOnDemandWithDomain(theAttrId, theVal, typeInVal,domain, theResultList) {
  if(typeInVal.length < 1)
  	return;
	var url="../../jsp/common/autoSelectDomain.jsp";
	var  para ="aid="+theAttrId + "&val=" +  (theVal) + "&sid=" + Math.random() + "&t=" +  (typeInVal) + "&rs=" + theResultList + "&dm=" + domain;
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
			document.getElementById("S_" + theAttrId).innerHTML= xmlHttpS.responseText;
		}
   }
   xmlHttpS.open("POST",url,false);
   xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
   
   xmlHttpS.send(para);
}
function autoSuggestionDem(aid, theName, theVal) {
   if(theVal.length >1) {
	var url="../../jsp/common/autoSuggestDem.jsp";
	var para="aid="+aid + "&theName=" + theName + "&theVal=" + theVal + "&sid=" + Math.random();
	xmlHttpSa=GetXmlHttpObject();
	xmlHttpSa.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpSa.readyState==4 || xmlHttpSa.readyState=="complete") {
			document.getElementById("A_" + theName).innerHTML= xmlHttpSa.responseText;
		}
	   }
	   xmlHttpSa.open("POST",url,false);
  	    xmlHttpSa.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	   xmlHttpSa.send(para);
   }
}
function fetchMeasureChkListRS(typeName, checkListId) {
   if(checkListId.length > 1) {
	var url="../../med/common/checkListSPCMeasure.jsp?cmd=view&opt=1&cid="+(checkListId) + "&t=" + (typeName) + "&sid=" + Math.random();
       if(typeName.indexOf("MEDADD_")==0) {
  		 url="../../med/common/checkListSPC.jsp?cmd=view&opt=1&cid="+(checkListId) + "&t=" + (typeName) + "&sid=" + Math.random();    
       }
       else if(typeName.indexOf("TL_")==0) {
  		 url="../../med/common/checkListTOOLSPC.jsp?cmd=view&opt=1&cid="+(checkListId) + "&t=" + (typeName) + "&sid=" + Math.random();    
       }
      openWindowWithName(url, 'ctb', 800, 500);
   }
}

function fetchChkListRS(typeName, checkListId) {
   if(checkListId.length > 1) {
	var url="../../medmes/common/checkListSPC.jsp?cmd=view&opt=1&cid="+(checkListId) + "&t=" + (typeName) + "&sid=" + Math.random();
	openWindowWithName(url, 'ctb', 800, 500);
   }
}
function XMLHttpUtil(spanTag, url, paraStr){
	xmlHttpm=GetXmlHttpObject();
	xmlHttpm.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpm.readyState==4 || xmlHttpm.readyState=="complete") {
			var rs = xmlHttpm.responseText;
			document.getElementById(spanTag).innerHTML=rs;
	 	}
   	}
	xmlHttpm.open("POST",url,false);
 	xmlHttpm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
 	xmlHttpm.send(paraStr);
}
function XMLHttpUtilAsyn(spanTag, url, paraStr){
	document.getElementById(spanTag).innerHTML= "正在计算..."+"<image src='../../images/jindu.gif'/>";
	xmlHttpm=GetXmlHttpObject();
	xmlHttpm.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpm.readyState==4 || xmlHttpm.readyState=="complete") {
			document.getElementById(spanTag).innerHTML= xmlHttpm.responseText;
		}
   	}
	xmlHttpm.open("POST",url,true);
 	xmlHttpm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
 	xmlHttpm.send(paraStr);
}
function XMLHttpUtilAsyn2(spanTag, url, para){
	document.getElementById(spanTag).innerHTML= "正在计算...请稍候";
	xmlHttpm=GetXmlHttpObject();
	xmlHttpm.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpm.readyState==4 || xmlHttpm.readyState=="complete") {
			document.getElementById(spanTag).innerHTML= xmlHttpm.responseText;
		}
   	}
	xmlHttpm.open("POST",url,true);
   	xmlHttpm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=UTF-8");
	xmlHttpm.send(para);
}
function getURLValue(url){
	var theVal = "";
	var xls = url.split("?");
	xmlHttpm=GetXmlHttpObject();
	xmlHttpm.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpm.readyState==4 || xmlHttpm.readyState=="complete") {
			return xmlHttpm.responseText;
		}
   	}
	xmlHttpm.open("POST",xls[0],false);
 	xmlHttpm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	if(xls.length > 1) {
 		xmlHttpm.send(xls[1]);
 	}
 	else
		xmlHttpm.send(null);
}
function reloadMessageList(TYPE_NAME, PRI_KEY) {
	XMLHttpUtil("MessagerInfo_" + TYPE_NAME + "_" +  PRI_KEY, "../../jsp/common/doAction.jsp", "a=1&cmd=messagerBarView&TYPE_NAME=" + TYPE_NAME + "&PRI_KEY=" + PRI_KEY);
}

function XMLHttpUtilInput(document_form_col, url, para){
	xmlHttpm=GetXmlHttpObject();
	xmlHttpm.onreadystatechange= function stateChangedFile()  {
		if (xmlHttpm.readyState==4 || xmlHttpm.readyState=="complete") {
			eval(document_form_col).value= xmlHttpm.responseText;
		}
   	}
	xmlHttpm.open("POST",url,false);
   	xmlHttpm.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;; charset=UTF-8");
 	xmlHttpm.send(para);
}
function moreSearchShow(objectTypeName) {
	XMLHttpUtil("MORE_SEARCH_COLS_" + objectTypeName, "../../jsp/common/moreSearchPage.jsp", "cmd=more&OBJ_TYPE=" + objectTypeName);
}

function viewFAMILY(fid, spanid)
{
	url = "../../his/JKDA101/viewFamily.jsp?cmd=showFID&fid=" + fid ;
	openWindowWithName(url, 'xctb', 800, 500);
	//XMLHttpUtil('ccFZH_x', url);	
}

function setCookie (name, value) {
         var argv = setCookie.arguments;
         var argc = setCookie.arguments.length;
         var expires = (argc > 2) ? argv[2] : null;
         var path = (argc > 3) ? argv[3] : null;
         var domain = (argc > 4) ? argv[4] : null;
         var secure = (argc > 5) ? argv[5] : false;
         document.cookie = name + "=" + escape (value) +
         ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
         ((path == null) ? "" : ("; path=" + path)) +
         ((domain == null) ? "" : ("; domain=" + domain)) +
         ((secure == true) ? "; secure" : "");
}
function getCookieVal(offset) {
   var endstr = document.cookie.indexOf (";", offset);
   if (endstr == -1) endstr = document.cookie.length;
   return unescape (document.cookie.substring(offset, endstr));
}
function getCookie(name) {
   var arg = name+"=";
   var alen = arg.length;
   var clen = document.cookie.length;
   var i = 0;
   while (i < clen) {
      var j = i + alen;
      if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
      i = document.cookie.indexOf(" ", i) + 1;
      if (i == 0) break;
   }
   return null;
}

function deleteCookie( name, path, domain ) {
  if ( getCookie( name ) ) document.cookie = name + "=" +
    ( ( path ) ? ";path=" + path : "") +
    ( ( domain ) ? ";domain=" + domain : "" ) +
    ";expires=Thu, 01-Jan-1970 00:00:01 GMT";
}

/**
 * 异步刷新div
 * @param {Object} sUrl
 * @param {Object} divId
 * @param {Object} defaultStr
 */
function updateDivContent( sUrl , divId) {
	var url = sUrl ; 
	xmlHttpS=GetXmlHttpObject();
	xmlHttpS.onreadystatechange= function stateChangedFile()  {
																				if (xmlHttpS.readyState==4 || xmlHttpS.readyState=="complete") {
																						document.getElementById(divId).innerHTML= xmlHttpS.responseText;
																					}
																			   }
	xmlHttpS.open("POST",url,false);
    xmlHttpS.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
 	xmlHttpS.send();
}

