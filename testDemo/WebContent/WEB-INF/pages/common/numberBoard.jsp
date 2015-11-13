<%@page import="com.codyy.commons.HostConfig"%>
<%@page import="com.codyy.commons.context.SpringContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



			          
			          <c:set var="t1" value="0"></c:set>
				      <c:set var="t2" value="0"></c:set>
				      <c:set var="t3" value="0"></c:set>
				      <c:set var="t4" value="0"></c:set>
				      <c:set var="t5" value="0"></c:set>
				      <c:set var="t6" value="0"></c:set>
				      
				      
			          
			       <c:forEach var="q" items="${examQuestions}" varStatus="status">
			         
			         <c:if test="${q.type == 'SINGLE_CHOICE'}">
			           
			              <c:if test="${t1 == 0}">
			                <div class="quesNav">
			                 <c:set var="t1" value="1"></c:set>
                             <h5 class="quesKind2">一、单选题 </h5>
                             <ul class="quesNavUl clearfix" id="singleNo">
                          </c:if>
			              <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
			         </c:if>
			          
			         <c:if test="${q.type == 'MULTI_CHOICE'}">
			           
			             <c:if test="${t2 == 0 }">
			               </div>
			              <div class="quesNav">
                           <c:set var="t2" value="1"></c:set>
                            <h5 class="quesKind2">
                            <c:if test="${t1 == 0 }">一</c:if>
						    <c:if test="${t1 == 1 }">二</c:if>
                                                                                  、多选题</h5>
                           <ul class="quesNavUl clearfix">
                        </c:if>
			             <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
                                                                        
                     </c:if>
                     
                     <c:if test="${q.type == 'FILL_IN_BLANK'}">
                       
                        <c:if test="${t3 == 0}">
                         </div>
                         <div class="quesNav">
                            <c:set var="t3" value="1"></c:set>
                            <h5 class="quesKind2">
                             <c:if test="${(t1 + t2) == 0 }">一</c:if>
						     <c:if test="${(t1 + t2) == 1 }">二</c:if>
						     <c:if test="${(t1 + t2) == 2 }">三</c:if>
						        、填空题</h5>
                          <ul class="quesNavUl clearfix">
                        </c:if>
			            <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
                     </c:if>
                    
                    
                     <c:if test="${q.type == 'JUDEMENT'}">
                       <c:if test="${t4 == 0}">
                        </div>
			           <div class="quesNav">
                        <c:set var="t4" value="1"></c:set>
                        <h5 class="quesKind2">
                           <c:if test="${(t1 + t2 + t3) == 0 }">一</c:if>
						   <c:if test="${(t1 + t2 + t3) == 1 }">二</c:if>
						   <c:if test="${(t1 + t2 + t3) == 2 }">三</c:if>
						   <c:if test="${(t1 + t2 + t3) == 3 }">四</c:if>
						 、判断题</h5>
                         <ul class="quesNavUl clearfix">
                         </c:if>
			             <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
                     
                   </c:if>
			       <c:if test="${q.type == 'ASK_ANSWER'}">
			         <c:if test="${t5 == 0}">
			          </div>
			           <div class="quesNav">
                        <c:set var="t5" value="1"></c:set>
                        <h5 class="quesKind2">
                          <c:if test="${(t1 + t2 + t3 + t4) == 0 }">一</c:if>
						  <c:if test="${(t1 + t2 + t3 + t4) == 1 }">二</c:if>
						  <c:if test="${(t1 + t2 + t3 + t4) == 2 }">三</c:if>
						  <c:if test="${(t1 + t2 + t3 + t4) == 3 }">四</c:if>
						  <c:if test="${(t1 + t2 + t3 + t4) == 4 }">五</c:if>
						 、问答题
                        </h5>
                        <ul class="quesNavUl clearfix">
                         </c:if>
			             <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
                    </c:if>
			        
			          <c:if test="${q.type == 'COMPUTING'}">
			            <c:if test="${t6 == 0}">
			             </div>
			               <div class="quesNav">
			               <c:set var="t6" value="1"></c:set>
					       <h5 class="quesKind2">
						   <c:if test="${(t1 + t2 + t3 + t4 + t5) == 0 }">一</c:if>
						   <c:if test="${(t1 + t2 + t3 + t4 + t5) == 1 }">二</c:if>
						   <c:if test="${(t1 + t2 + t3 + t4 + t5) == 2 }">三</c:if>
						   <c:if test="${(t1 + t4 + t3 + t4 + t5) == 3 }">四</c:if>
						   <c:if test="${(t1 + t2 + t3 + t4 + t5) == 4 }">五</c:if>
						   <c:if test="${(t1 + t2 + t3 + t4 + t5) == 5 }">六</c:if>
						           、计算题</h5>
				           <ul class="quesNavUl clearfix">
                        </c:if>
			            <li id="_${status.count}" class="_${q.examQuestionId}"><a href="#${status.count}">${status.count}</a></li>
				     
			           </c:if>
                       			        
			         </c:forEach>
			      
