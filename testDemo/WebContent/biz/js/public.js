/*绑定学段下拉*/
function bindSemester(){
	var url = KD_RRT.root + "/common/getAllBaseSemester.do";
	$.get(url , function(result){
		$("#semesterOpts").empty();
		result.unshift({"baseSemesterId":"0", "semesterName":"请选择"});
		$.each(result, function(index, json){
			$("#semesterOpts").append("<option value='" + json.baseSemesterId + "'>" + json.semesterName + "</option>");
		});
	}, "json");
}

/*绑定学段下拉*/
function bindSemesterExam(){
	var url = KD_RRT.root + "/common/getAllBaseSemesterExam.do";
	$.get(url , function(result){
		$("#semesterOpts").empty();
		result.unshift({"baseSemesterId":"0", "semesterName":"请选择"});
		$.each(result, function(index, json){
			if(index == 1){
				$("#semesterOpts").append("<option value='" + json.baseSemesterId + "' selected>" + json.semesterName + "</option>");
				bindDisciplineBySemesterExam(json.baseSemesterId);
				semesterId = json.baseSemesterId;
			}else{
				$("#semesterOpts").append("<option value='" + json.baseSemesterId + "'>" + json.semesterName + "</option>");
			}
		});
	}, "json");
}

/*根据学段绑定年级*/
function bindClassLvlBySemester(semId){
	var url = KD_RRT.root + "/common/getAllBaseClassLevelBySemester.do?baseSemesterId="+semId;
		$.get(url , function(result){
		$("#classlevelOpts").empty();
		result.unshift({"baseClasslevelId":"0", "classLevelName":"请选择"});
		$.each(result, function(index, json){
			$("#classlevelOpts").append("<option value='" + json.baseClasslevelId + "'>" + json.classLevelName + "</option>");
		});
	}, "json");
}

/*根据年级绑定学科*/
function bindDisciplineByClassLevel(classLvlId){
	var url = KD_RRT.root + "/common/getAllBaseDisciplineByClassLevel.do?baseClassLevelId="+classLvlId;
	$.get(url , function(result){
		$("#disciplineOpts").empty();
		result.unshift({"baseDisciplineId":"0", "baseDisciplineName":"请选择"});
		$.each(result, function(index, json){
			$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "'>" + json.baseDisciplineName + "</option>");
		});
	}, "json");
}

/*根据学段绑定学科*/
function bindDisciplineBySemesterExam(semId){
	var url = KD_RRT.root + "/common/getAllBaseDisciplineBySemesterExam.do?baseSemesterId="+semId;
	$.get(url , function(result){
		$("#disciplineOpts").empty();
		result.unshift({"baseDisciplineId":"0", "disciplineName":"请选择"});
		$.each(result, function(index, json){
			if(index == 1){
				$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "' selected>" + json.disciplineName + "</option>");
				 initKnowledge(semesterId,json.baseDisciplineId);//初始按化知识点选题
				 initChapter(semesterId,json.baseDisciplineId);//初始化按章节选题
				 initMyChapter(semesterId,json.baseDisciplineId);//初始化我的习题
				 searchWrongQuestions();//初始化我的错题本
				 bindChapterByVersion();//初始化智能选题
				 initAllQuestions();//获取所有的题目并显示
			}else{
				$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "'>" + json.disciplineName + "</option>");
			}
		});
	}, "json");
}

function bindDisciplineBySemester(semId){
	var url = KD_RRT.root + "/common/getAllBaseDisciplineBySemester.do?baseSemesterId="+semId;
	$.get(url , function(result){
		$("#disciplineOpts").empty();
		result.unshift({"baseDisciplineId":"0", "disciplineName":"请选择"});
		$.each(result, function(index, json){
			if(index == 1){
				$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "' selected>" + json.disciplineName + "</option>");
				if(typeof(searchWrongQuestions) != 'undefined'){
					searchWrongQuestions();
				}
			}else{
				$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "'>" + json.disciplineName + "</option>");
			}
		});
	}, "json");
}

/*根据ID值级联绑定学段年级学科*/
function bindCascadeSemesterAndClassLvlAndDiscipline(semId,clsLvlId,discpId,callback){
	//绑定学段并设置初始值
	var sem_url = KD_RRT.root + "/common/getAllBaseSemester.do";
	$.ajax({
		   type: "get",
		   url: sem_url,
		   async:false,
		   dataType:'json',
		   success: function(result){
				$("#semesterOpts").empty();
				result.unshift({"baseSemesterId":"0", "semesterName":"请选择"});
				$.each(result, function(index, json){
					$("#semesterOpts").append("<option value='" + json.baseSemesterId + "'>" + json.semesterName + "</option>");
				});
				$("#semesterOpts").val(semId);
		   }
		}); 
	//绑定年级并设置初始值
	var clsLvl_url = KD_RRT.root + "/common/getAllBaseClassLevelBySemester.do?baseSemesterId="+semId;
	$.ajax({
		   type: "get",
		   url: clsLvl_url,
		   async:false,
		   dataType:'json',
		   success: function(result){
			   $("#classlevelOpts").empty();
				result.unshift({"baseClasslevelId":"0", "classLevelName":"请选择"});
				$.each(result, function(index, json){
					$("#classlevelOpts").append("<option value='" + json.baseClasslevelId + "'>" + json.classLevelName + "</option>");
				});
				$("#classlevelOpts").val(clsLvlId);
		   }
		}); 
	//绑定学科并设置初始值   
	var disp_url = KD_RRT.root + "/common/getAllBaseDisciplineByClassLevel.do?baseClassLevelId="+clsLvlId;
	$.ajax({
		   type: "get",
		   url: disp_url,
		   async:false,
		   dataType:'json',
		   success: function(result){
			   $("#disciplineOpts").empty();
				result.unshift({"baseDisciplineId":"0", "baseDisciplineName":"请选择"});
				$.each(result, function(index, json){
					$("#disciplineOpts").append("<option value='" + json.baseDisciplineId + "'>" + json.baseDisciplineName + "</option>");
				});
				$("#disciplineOpts").val(discpId);
		   }
		});
	//后续事件执行
	 if(callback) callback(semId,clsLvlId,discpId);	
}

/*根据学科年级绑定版本*/
function bindVersionByClassLevelAndDiscipline(classLvlId,discId){
	var url = KD_RRT.root + "/common/getAllBaseVersionByClassLevelAndDiscipline.do?baseDisciplineId="+discId+"&baseClasslevelId="+classLvlId;
	$.get(url , function(result){
		$("#versionOpts").empty();
		result.unshift({"baseCatalogId":"0","baseVerClasslevelDisciplineId":"0", "catalogName":"请选择"});
		$.each(result, function(index, json){
			$("#versionOpts").append("<option value='" + json.baseVerClasslevelDisciplineId + "' dir='"+json.baseCatalogId+"'>" + json.catalogName + "</option>");
		});
	}, "json");
}

/*根据版本绑定分册*/
function bindVolumeByVersion(versionId){
	var url = KD_RRT.root + "/common/getAllBaseVolumeByVersion.do?baseVersionId="+versionId;
	$.get(url , function(result){
		$("#volumeOpts").empty();
		result.unshift({"baseCatalogId":"0", "catalogName":"请选择"});
		$.each(result, function(index, json){
			$("#volumeOpts").append("<option value='" + json.baseCatalogId + "'>" + json.catalogName + "</option>");
		});
	}, "json");
}

/*根据分册绑定章*/
function bindChapterByVolume(volumeId){
	var url = KD_RRT.root + "/common/getAllBaseChapterByVolume.do?baseVolumeId="+volumeId;
	$.get(url , function(result){
		$("#chapterOpts").empty();
		result.unshift({"baseCatalogId":"0", "catalogName":"请选择"});
		$.each(result, function(index, json){
			$("#chapterOpts").append("<option value='" + json.baseCatalogId + "'>" + json.catalogName + "</option>");
		});
	}, "json");
}

/*根据章绑定节*/
function bindSectionByChapter(chapterId){
	var url = KD_RRT.root + "/common/getAllBaseSectionByChapter.do?baseChapterId="+chapterId;
	$.get(url , function(result){
		$("#sectionOpts").empty();
		result.unshift({"baseCatalogId":"0", "catalogName":"请选择"});
		$.each(result, function(index, json){
			$("#sectionOpts").append("<option value='" + json.baseCatalogId + "'>" + json.catalogName + "</option>");
		});
	}, "json");
}

/*根据学段和学科绑定知识点*/
function bindKnowledgeBySemesterAndDiscipline(semesterId,disciplineId){
	var url = KD_RRT.root + "/common/getAllBaseKnowledgeBySemesterAndDiscipline.do?baseSemesterId="+semesterId+"&baseDisciplineId="+disciplineId;
	$.get(url , function(result){
		$("#knowledgeOpts").empty();
		result.unshift({"baseKnowledgeId":"0", "knowledgeName":"请选择"});
		$.each(result, function(index, json){
			$("#knowledgeOpts").append("<option value='" + json.baseKnowledgeId + "'>" + json.knowledgeName + "</option>");
		});
	}, "json");
}

/*根据父级获取子级知识点*/
function bindSubKnowledgeByParentId(parentId,callback){
	var url = KD_RRT.root + "/common/getSubKnowledgeByParent.do?parentId="+parentId;
	$.get(url , function(result){
		 if(callback) callback(result);
	}, "json");
}

/*根据数据绑定子知识点*/
function bindSubKnowledgeData(optObj,result){
	result.unshift({"baseKnowledgeId":"0", "knowledgeName":"请选择"});
	$(optObj).empty();
	$.each(result, function(index, json){
		$(optObj).append("<option value='" + json.baseKnowledgeId + "'>" + json.knowledgeName + "</option>");
	});
}

/*绑定习题类型*/
function bindQuestionType(){
	$("#questionTypeOpts").empty();
	$("#questionTypeOpts").append("<option value='0'>请选择</option>");
	$("#questionTypeOpts").append("<option value='1'>单选题</option>");
	$("#questionTypeOpts").append("<option value='2'>多选题</option>");
	$("#questionTypeOpts").append("<option value='3'>填空题</option>");
	$("#questionTypeOpts").append("<option value='4'>解答题</option>");
}

/*绑定难易度*/
function bindDifficulty(difficulty){
	$("#difficultyOpts").empty();
	/*$("#difficultyOpts").append("<option value='0'>请选择</option>");*/
	$("#difficultyOpts").append("<option value='1'>容易</option>");
	$("#difficultyOpts").append("<option value='2'>较易</option>");
	$("#difficultyOpts").append("<option value='3'>一般</option>");
	$("#difficultyOpts").append("<option value='4'>较难</option>");
	$("#difficultyOpts").append("<option value='5'>困难</option>");
	if(difficulty) $("#difficultyOpts").val(difficulty);
}

/*绑定错题来源*/
function bindSourceType(){
	$("#sourceTypeOpts").empty();
	$("#sourceTypeOpts").append("<option value='0'>请选择</option>");
	$("#sourceTypeOpts").append("<option value='1'>测试中心</option>");
	$("#sourceTypeOpts").append("<option value='2'>作业中心</option>");
}

/*绑定错误率起始*/
function bindErrorRateFrom(def){
	$("#errorRateFromOpts").empty();
	def = def ? def:0;
	for(var i = 0; i<=10;i++){
		if(i*10 == def){
			$("#errorRateFromOpts").append("<option selected value='"+i*10+"'>"+i*10+"%</option>");
		} else {
			$("#errorRateFromOpts").append("<option value='"+i*10+"'>"+i*10+"%</option>");
		}		
	}	
}

/*绑定错误率结束*/
function bindErrorRateTo(def){
	$("#errorRateToOpts").empty();
	def = def ? def:0;
	for(var i = 0; i<=10;i++){
		if(i*10 == def){
			$("#errorRateToOpts").append("<option selected value='"+i*10+"'>"+i*10+"%</option>");
		} else {
			$("#errorRateToOpts").append("<option value='"+i*10+"'>"+i*10+"%</option>");
		}		
	}	
}

/*转换难易度为中文*/
function switchDifficulty(key){
  	 var x="容易";
	 switch (key)
	 {
    	 case '1':
    	   x="容易";
    	   break;
    	 case '2':
    	   x="较易";
    	   break;
    	 case '3':
    	   x="一般";
    	   break;
    	 case '4':
    	   x="较难";
    	   break;
    	 case '5':
    	   x="困难";
    	   break;
	 }
	 return x;
}

/*绑定试卷类型下拉*/
function bindExamType(){
	var url = KD_RRT.root + "/teacher/onlineTest/getAllExamType.do";
	$.get(url , function(result){
		$("#examtypeOpts").empty();
		result.unshift({"examTypeId":"0", "name":"请选择"});
		$.each(result, function(index, json){
			$("#examtypeOpts").append("<option value='" + json.examTypeId + "'>" + json.name + "</option>");
		});
	}, "json");
}

/*选择题选项全局变量*/
var optionDef  = ["A","B","C","D","E","F","G","H"];
var optSplitChar = '∷';
var fillInAnswerSplitChar = '∷';

String.prototype.replacePTag = function(){
	var str = this;
	str =  str.replace(/<img /g, 'a3907d77279e4028a4603b4b5582454f').replace(/<\/img>/g,'f2ddbf4b86de4269af2ff3794757160c');
	str = filterSpecChar(str);
	str = str.replace(/a3907d77279e4028a4603b4b5582454f/g, '<img ').replace(/f2ddbf4b86de4269af2ff3794757160c/g, '</img>')
	return str;
}

function filterSpecChar(str){
	 str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
     str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
     str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
     str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
     return str; 
}

//处理Ztreehtml标签的问题
function htmlDecode(textVal){
  	 var $elem = $("<div></div>");
  	 $elem.html(textVal);
  	 var retHtml = $elem.text();
  	 $elem.remove();
  	 return retHtml;
}

//处理Ztreehtml标签的问题的Filter
function ajaxDataFilter(treeId, parentNode, responseData) {
    if (responseData) {
      for(var i =0; i < responseData.length; i++) {
        responseData[i].name = htmlDecode(responseData[i].name);
      }
    }
    return responseData;
};

function validateNum(e){
	e.value=e.value.replace(/\D/g,'');
}

function formSeq(seqIndex){
	 var x="一";
	 switch (seqIndex)
	 {
   	 case 1:
   	   x="一";
   	   break;
   	 case 2:
   	   x="二";
   	   break;
   	 case 3:
   	   x="三";
   	   break;
   	 case 4:
   	   x="四";
   	   break;
   	 case 5:
   	   x="五";
   	   break;
   	 case 6:
   	   x="六";
   	   break;
   	 case 7:
   	   x="七";
   	   break;
   	 case 8:
   	   x="八";
   	   break;
   	 case 9:
 	   x="九";
 	   break;
   	 case 10:
 	   x="十";
 	   break;
	 }
	 return x;
}