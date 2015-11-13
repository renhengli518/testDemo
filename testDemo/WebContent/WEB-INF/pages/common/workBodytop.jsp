<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${ques.type == 'SINGLE_CHOICE' &&  title1 == 0}">
										<c:set var="title1" value="1"></c:set>
										<h4 class="quesKind">一、单选题</h4>
									</c:if>
									<c:if test="${ques.type == 'MULTI_CHOICE' && title2 == 0}">
										<c:set var="title2" value="1"></c:set>

										<h4 class="quesKind">
											<c:if test="${title1 == 0 }">一</c:if>
											<c:if test="${title1 == 1 }">二</c:if>
											、多选题
										</h4>
									</c:if>
									<c:if test="${ques.type == 'JUDEMENT' && title3 == 0 }">
										<c:set var="title3" value="1"></c:set>

										<h4 class="quesKind">
											<c:if test="${(title1 + title2) == 0 }">一</c:if>
											<c:if test="${(title1 + title2) == 1 }">二</c:if>
											<c:if test="${(title1 + title2) == 2 }">三</c:if>
											、判断题
										</h4>
									</c:if>
									<c:if test="${ques.type == 'FILL_IN_BLANK' && title4 == 0}">
										<c:set var="title4" value="1"></c:set>

										<h4 class="quesKind">
											<c:if test="${(title1 + title2 + title3) == 0 }">一</c:if>
											<c:if test="${(title1 + title2 + title3) == 1 }">二</c:if>
											<c:if test="${(title1 + title2 + title3) == 2 }">三</c:if>
											<c:if test="${(title1 + title2 + title3) == 3 }">四</c:if>
											、填空题
										</h4>
									</c:if>
									<c:if test="${ques.type == 'ASK_ANSWER' && title5 == 0}">
										<c:set var="title5" value="1"></c:set>

										<h4 class="quesKind">
											<c:if test="${(title1 + title2 + title3 + title4) == 0 }">一</c:if>
											<c:if test="${(title1 + title2 + title3 + title4) == 1 }">二</c:if>
											<c:if test="${(title1 + title2 + title3 + title4) == 2 }">三</c:if>
											<c:if test="${(title1 + title2 + title3 + title4) == 3 }">四</c:if>
											<c:if test="${(title1 + title2 + title3 + title4) == 4 }">五</c:if>
											、问答题
										</h4>
									</c:if>
									<c:if test="${ques.type == 'COMPUTING' && title6 == 0}">
										<c:set var="title6" value="1"></c:set>

										<h4 class="quesKind">
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 0 }">一</c:if>
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 1 }">二</c:if>
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 2 }">三</c:if>
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 3 }">四</c:if>
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 4 }">五</c:if>
											<c:if
												test="${(title1 + title2 + title3 + title4 + title5) == 5 }">六</c:if>
											、计算题
										</h4>
									</c:if>