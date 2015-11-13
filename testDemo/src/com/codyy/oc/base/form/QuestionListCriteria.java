package com.codyy.oc.base.form;

import org.apache.commons.lang.StringUtils;

public class QuestionListCriteria {
	
	 private String semesterId;  /*学段*/
	
	 private String classlevelId;  /*年级*/
	 
	 private String subjectId;  /*学科*/
	 
	 private String versionId;  /*版本*/
	 
	 private String volumeId;  /*分册*/
	 
	 private String chapterId;  /*章*/
	 
	 private String questionType; /*习题类型*/
	
	private String isShelved; /*是否上架2---下架  1---上架 0 所有*/ 
	 
	 private String baseUserId; /*习题创建人*/
	 
	 private String knowledgeId;/*知识点*/
	 
	 private String difficulty;/*试题难度*/
	 
	 private String status;
	 
	 private String sourceType;/*习题创建来源后台还是前台*/

	//分页起始索引
	 private Integer start;
	 
	 private Integer end;
	 
	 public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	 public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public String getIsShelved() {
		return isShelved;
	}

	public void setIsShelved(String isShelved) {
		this.isShelved = isShelved;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getClasslevelId() {
		return classlevelId;
	}

	public void setClasslevelId(String classlevelId) {
		this.classlevelId = classlevelId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	 
	
	 public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

   //以下为习题所用的枚举和相关静态常量
	public static final String OPT_SEPERATE = "∷";
	public static final String SIGNCHOICE="SINGLE_CHOICE";
	public static final String MULITYCHOICE="MULTI_CHOICE";
	public static final String JUDGEMENT="JUDEMENT";
	public static final String ASKANSWER="ASK_ANSWER";
	public static final String FILL_IN_BLANK="FILL_IN_BLANK";
	public static final String INDEPENDENT="INDEPENDENT";
	public static final String COMBINATION="COMBINATION";
	public static final String COMPUTER="COMPUTING";
	public static final String EXTEND="EXTEND";
	public static final String TWINS="TWINS";
	public static final String MOTHER="MOTHER";
	
	
	public static final String EASY="EASY";
	public static final String LITTLEEASY="LITTLE_EASY";
	public static final String NORMAL="NORMAL";
	public static final String LITTLE_DIFFICULT="LITTLE_DIFFICULT";
	public static final String DIFFICULT="DIFFICULT";
	
	
	
	
	
	
	public static final String FILLIN_ANSWER_SEPERATE = "∷";//a3907d77279e4028a4603b4b5582454f
//	a3907d77279e4028a4603b4b5582454f
	
	public enum IsShelved {
			up("1", "上架"), down("2", "下架"), all("0","所有");

			private IsShelved(String value, String desc) {
				this.value = value;
				this.desc = desc;
			}
			private String value;
			private String desc;

			public String getValue() {
				return value;
			}

			public String getDesc() {
				return desc;
			}

			public static IsShelved getDes(String value) {
				if(StringUtils.isNotBlank(value)) {
					for(IsShelved p : IsShelved.values()) {
						if(p.getValue().equals(value)){
							return p;
						}
					}
				}
				return null;
			}
	}
	
	public enum QuestionStatus {
		normal("0", "正常"), down("1", "删除");

		private QuestionStatus(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static QuestionStatus getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(QuestionStatus p : QuestionStatus.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
}
	
	public enum QuestionType {
		single_option("SINGLE_CHOICE", "单选"), multple_option("MULTI_CHOICE", "多选"),judement("JUDEMENT","判断题"), fill_in("FILL_IN_BLANK","填空"),resolve("ASK_ANSWER","问答题"),computing("COMPUTING","计算题");

		private QuestionType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static QuestionType getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(QuestionType p : QuestionType.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	public enum SourceType {
		fontend("1", "前台"), backend("2", "后台");

		private SourceType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static SourceType getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(SourceType p : SourceType.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	public enum Difficulty {
		easy("EASY", "容易"), not_easy("LITTLE_EASY", "较易"), normal("NORMAL","一般"),hard("LITTLE_DIFFICULT","较难"),very_hard("DIFFICULT","困难");

		private Difficulty(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static Difficulty getDes(String value) {
			if(value != null) {
				for(Difficulty p : Difficulty.values()) {
					if(p.getValue() == value){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	public enum AnswerType {
		independent("INDEPENDENT", "独立答案"), combination("COMBINATION", "组合答案");

		private AnswerType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static AnswerType getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(AnswerType p : AnswerType.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
	public enum ScoreType {
		all("ALL_CORRECT", "全对给分"), partial("SCORE_BY_FILL", "按空给分");

		private ScoreType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		private String value;
		private String desc;

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		public static ScoreType getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(ScoreType p : ScoreType.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}

}
