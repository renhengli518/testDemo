<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="header_down_wrap">
    <div class="header_down_inner_wrap">
        <ul class="_header">
            <li class="header_item header_item_first">
                <div class="title_wrap">
                    <div class="logo"><img src="${root }/images/${namemap['front.workspace.in.disucss'].imagePath }" width="90" height="90"></div>
                    <span class="title">${namemap['front.workspace.in.disucss']['textContent'] }</span>
                </div>
            </li>
            <li class="header_item header_item_last">
                <nav class="nav ml50" id="nav">
                    <a class="nav_item ml20 <c:if test="${evaStandard=='private'}">nav_item_clicked</c:if>" href="javascript:viewPrivateStandards();">我的评估标准</a> 
					<a class="nav_item ml20 <c:if test="${evaStandard=='public'}">nav_item_clicked</c:if>" href="javascript:viewPublicStandards();">公开的评估标准</a>
                </nav>
			</li>
        </ul>
    </div>
</div>

