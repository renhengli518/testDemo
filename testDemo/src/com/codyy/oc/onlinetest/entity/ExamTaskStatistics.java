package com.codyy.oc.onlinetest.entity;

/**
 * 班级统计详情
 * @author zhangshuangquan
 *
 */
public class ExamTaskStatistics {

    private String examTaskId;
    
    private String classId;       //班级ID
  
    private String className;    //班级名
    
    private String classlevelId;  //年级ID
    
    private String classlevelName; //年级名
	
	private String commitCnt;    //答题人数
	
	private String highestScore;  //最高得分
	 
	private String lowestScore;   //最低得分
	
	private String avgScore;      //平均得分
	
	private String passRate;      //通过率
	
	private String myScore;       //我的得分
	
	private String rightRate;     //正确率
	
	private String mistakeCnt;    //错题数     

	public String getExamTaskId() {
		return examTaskId;
	}

	public void setExamTaskId(String examTaskId) {
		this.examTaskId = examTaskId;
	}
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClasslevelName() {
		return classlevelName;
	}

	public void setClasslevelName(String classlevelName) {
		this.classlevelName = classlevelName;
	}

	public String getClasslevelId() {
		return classlevelId;
	}

	public void setClasslevelId(String classlevelId) {
		this.classlevelId = classlevelId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCommitCnt() {
		return commitCnt;
	}

	public void setCommitCnt(String commitCnt) {
		this.commitCnt = commitCnt;
	}

	public String getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(String highestScore) {
		this.highestScore = highestScore;
	}

	public String getLowestScore() {
		return lowestScore;
	}

	public void setLowestScore(String lowestScore) {
		this.lowestScore = lowestScore;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getMyScore() {
		return myScore;
	}

	public void setMyScore(String myScore) {
		this.myScore = myScore;
	}

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

	public String getMistakeCnt() {
		return mistakeCnt;
	}

	public void setMistakeCnt(String mistakeCnt) {
		this.mistakeCnt = mistakeCnt;
	}
		
}
