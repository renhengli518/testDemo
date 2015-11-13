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
                <c:if test="${(SESSION_USER.userType ne 'TEACHER') or (SESSION_USER.createEva eq true) }">
                	<a class="nav_item ml20 <c:if test="${eva=='fq'}">nav_item_clicked</c:if>" href="${root }/eva/index.html">${namemap['front.workspace.disucss.launch']['textContent']}</a>
                </c:if>
				<c:if test="${SESSION_USER.userType eq 'TEACHER' }">
					<a class="nav_item ml20 <c:if test="${eva=='cy'}">nav_item_clicked</c:if>" href="${root }/eva/teacherPart.html">${namemap['front.workspace.disucss.participation']['textContent']}</a>
					<a class="nav_item ml20 <c:if test="${eva=='zj'}">nav_item_clicked</c:if>" href="${root }/eva/teacherSpeak.html">${namemap['front.workspace.disucss.spack']['textContent']}</a>
				</c:if>
				<c:if test="${SESSION_USER.userType eq 'SCHOOL_USR' }">
					<a class="nav_item ml20 <c:if test="${eva=='sy'}">nav_item_clicked</c:if>" href="${root }/eva/schoolInvited.html">${namemap['front.workspace.disucss.invited']['textContent']}</a>
					<a class="nav_item ml20 <c:if test="${eva=='bxjs'}">nav_item_clicked</c:if>" href="${root }/eva/schoolTeacher.html">${namemap['front.workspace.disucss.steach']['textContent']}</a>
					<a class="nav_item ml20 <c:if test="${eva=='bxzj'}">nav_item_clicked</c:if>" href="${root }/eva/schoolTeachermain.html">${namemap['front.workspace.disucss.sspack']['textContent']}</a>
				</c:if>
				<c:if test="${SESSION_USER.userType eq 'AREA_USR' }">
					<a class="nav_item ml20 <c:if test="${eva=='xqfwn'}">nav_item_clicked</c:if>" href="${root }/eva/areaChildren.html">${namemap['front.workspace.disucss.area']['textContent']}</a>
				</c:if>
                </nav>
            </li>
        </ul>
    </div>
</div>

