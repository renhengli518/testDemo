package com.codyy.oc.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codyy.commons.image.ImageUtil;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseChapter;
import com.codyy.oc.base.entity.BaseClasslevel;
import com.codyy.oc.base.entity.BaseClasslevelSubjectVer;
import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.base.entity.BaseSection;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.entity.BaseUser;
import com.codyy.oc.base.entity.BaseVolume;
import com.codyy.oc.base.entity.TreeBean;
import com.codyy.oc.base.service.CommonsService;
import com.codyy.oc.base.view.BaseAreaView;
import com.codyy.oc.base.view.BaseChapterView;
import com.codyy.oc.base.view.BaseClslevelSubjectView;
import com.codyy.oc.base.view.BaseKnowledgeView;
import com.codyy.oc.base.view.BaseVolumeView;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.base.view.VCSelectModel;
import com.codyy.oc.questionlib.view.ChapterView;
import com.codyy.oc.questionlib.view.SectionView;
import com.codyy.oc.questionlib.view.SubjectView;
import com.codyy.oc.questionlib.view.VersionParmView;
import com.codyy.oc.questionlib.view.VersionView;
import com.codyy.oc.questionlib.view.VolumeParm;
import com.codyy.oc.questionlib.view.VolumeView;

@Controller
@RequestMapping("commons")
public class CommonsController {

	@Autowired
	private CommonsService commonsService;

	/**
	 * 查询区域
	 */
	@RequestMapping("getAreasByParentId")
	@ResponseBody
	public List<SelectModel> getAreasByParentId(String parentId) {
		return commonsService.getAreaListByParentId(parentId);
	}
	
	/**
	 * 根据id号获取区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getAreaById")
	@ResponseBody
	public BaseAreaView getAreaById(String id){
		return commonsService.getAreaById(id);
	}
	
	/**
	 * 查询区域和级别
	 */
	@RequestMapping("getAreasAndLevelByParentId")
	@ResponseBody
	public List<BaseAreaView> getAreasAndLevelByParentId(String parentId) {
		List<BaseAreaView> lists = commonsService.getAreasAndLevelByParentId(parentId);
		return lists;
	}

	/**
	 * 查询学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getSchoolByAreaId")
	public List<SelectModel> getSchoolByAreaId(String areaId) {
		return commonsService.getSchoolByAreaId(areaId);
	}
	
	/**
	 * 查询多区域下多学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getSchoolsByAreaId")
	public List<SelectModel> getSchoolsByAreaId(String areaId) {
		return commonsService.getSchoolsByAreaId(areaId);
	}

	/**
	 * 获取所有年级
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllClasslevels")
	public List<SelectModel> getAllClasslevels() {
		return commonsService.getAllClasslevels();
	}

	/**
	 * 获取所有学科
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllSubjects")
	public List<SelectModel> getAllSubjects() {
		return commonsService.getAllSubjects();
	}

	@ResponseBody
	@RequestMapping("getBaseAreaByParentId")
	public List<BaseArea> getBaseAreaByParentId(@RequestParam(required=true,value="parentId") String parentId){
		return commonsService.getBaseAreaByParentId(parentId);
	}
	
	@ResponseBody
	@RequestMapping("getBaseAreaById")
	public BaseArea getBaseAreaById(@RequestParam(required=true,value="areaId") String areaId){
		return commonsService.getBaseAreaById(areaId);
	}

	@RequestMapping("getClassLevelBySchoolId")
	@ResponseBody
	public List<SelectModel> getClassLevelBySchoolId(String schoolId){
		return commonsService.getClassLevelInfoBySchoolId(schoolId);
	}
	
	@RequestMapping("getSubjectByClasslevelId")
	@ResponseBody
	public List<SelectModel> getSubjectByClasslevelId(String classLevelId){
		return commonsService.getSubjectByClasslevelId(classLevelId);
	}
	
	@ResponseBody
	@RequestMapping("getAllSemester")
	public List<BaseSemester> getAllSemester(){
		return commonsService.getAllSemester();
	}
	
	@ResponseBody
	@RequestMapping("getAllClasslevelBySemesterId")
	public List<BaseClasslevel> getAllClasslevelBySemesterId(String semesterId){
		return commonsService.getAllClasslevelBySemesterId(semesterId);
	}
	
	@ResponseBody
	@RequestMapping("getAllSubjectByClslevelId")
	public List<BaseClslevelSubjectView> getAllSubjectByClslevelId(String classlevelId){
		return commonsService.getAllSubjectByClslevelId(classlevelId);
	}
	
	@ResponseBody
	@RequestMapping("getAllVersionByClslevelSubjectId")
	public List<BaseClasslevelSubjectVer> getAllVersionByClslevelSubjectId(String classlevelSubjectId){
		return commonsService.getAllVersionByClslevelSubjectId(classlevelSubjectId);
	}
	
	@ResponseBody
	@RequestMapping("getAllVolumeByClslevelSubjectVerId")
	public List<BaseVolume> getAllVolumeByClslevelSubjectVerId(String classlevelSubjectVersionId){
		return commonsService.getAllVolumeByClslevelSubjectVerId(classlevelSubjectVersionId);
	}
	
	@ResponseBody
	@RequestMapping("getAllVolumeByClslevelSubjectVerionId")
	public List<BaseVolumeView> getAllVolumeByClslevelSubjectVerionId(String classlevelId,String subjectId,String versionId){
		return commonsService.getAllVolumeByClslevelSubjectVerionId(classlevelId,subjectId,versionId);
	}
	
	@ResponseBody
	@RequestMapping("getAllChapterViewByVolumeId")
	public List<BaseChapterView> getAllChapterViewByVolumeId(String volumeId){
		return commonsService.getAllChapterViewByVolumeId(volumeId);
	}
	
	@ResponseBody
	@RequestMapping("getRootKnowledgeViewBySemsterSubjectId")
	public List<BaseKnowledgeView> getRootKnowledgeViewBySemsterSubjectId(String semesterId,String subjectId){
		return commonsService.getRootKnowledgeViewBySemsterSubjectId(semesterId,subjectId);
	}
	
	@ResponseBody
	@RequestMapping("getChildKnowledgeViewByParentId")
	public List<BaseKnowledgeView> getChildKnowledgeViewByParentId(String parentId){
		return commonsService.getChildKnowledgeViewByParentId(parentId);
	}
	
	@ResponseBody
	@RequestMapping("getAllChapterByVolumeId")
	public List<BaseChapter> getAllChapterByVolumeId(String volumeId){
		return commonsService.getAllChapterByVolumeId(volumeId);
	}
	
	@ResponseBody
	@RequestMapping("getAllSectionByChapterId")
	public List<BaseSection> getAllSectionByChapterId(String chapterId){
		return commonsService.getAllSectionByChapterId(chapterId);
	}
	
	@ResponseBody
	@RequestMapping("getRootKnowledgeBySemesterSubjectId")
	public List<BaseKnowledge> getRootKnowledgeBySemesterSubjectId(String semesterId,String subjectId){
		return commonsService.getRootKnowledgeBySemesterSubjectId(semesterId,subjectId);
	}
	
	@ResponseBody
	@RequestMapping("getChildKnowledgeByParentId")
	public List<BaseKnowledge> getChildKnowledgeByParentId(String parentId){
		return commonsService.getChildKnowledgeByParentId(parentId);
	}
	
	/**
	 * 根据ids获取用户信息
	 * @param baseUserIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getBaseUserListByIds")
	public List<BaseUser> getBaseUserListByIds(String baseUserIds) {
		
		List<BaseUser> baseUserList = new ArrayList<BaseUser>();
		
		if(!StringUtils.isEmpty(baseUserIds)) {
			final String[] baseUserIdArray = baseUserIds.split(",");
			final List<String> baseUserIdList = new ArrayList<String>();
			Collections.addAll(baseUserIdList, baseUserIdArray);
			baseUserList = commonsService.getBaseUserListByIds(baseUserIdList);
		}
		
		return baseUserList;
		
	}
	
	/**
	 * 根据年级id获取班级信息
	 * @param classlevelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getClassByClasslevelId")
	public List<SelectModel> getClassByClasslevelId(String classlevelId, String user_schoolId){
		return commonsService.getClassByClasslevelId(classlevelId, user_schoolId);
	}
	
	/**
	 * @author lichen
	* @Title: classLevelBySemId
	* @Description: TODO(根据学段的id来获取对应的年级集合)
	* @param @param semesterId
	* @param @return    设定文件
	* @return List<BaseClasslevel>    返回类型
	* @throws
	 */
	/*@RequestMapping("classlevelbysemid")
	public List<BaseClasslevel> classLevelBySemId(String semesterId){
		
		
	}*/
	
	/**
	 * @author lichen
	* @Title: classLevelBySemId
	* @Description: TODO(根据学段的id来获取对应的年级集合(级联))
	* @param @param semesterId
	* @param @return    设定文件
	* @return List<BaseClasslevel>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("classLevelBySemId")
	public List<BaseClasslevel> classLevelBySemId(String semesterId){
		return commonsService.classLevelBySemId(semesterId);
	}
	
	/**
	 * @author lichen
	* @Title: subjByclassId
	* @Description: TODO(学科级联年级)
	* @param @param classLevelId
	* @param @return    设定文件
	* @return List<SubjectView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("subjbyclassid")
	public List<SubjectView> subjByclassId(String classLevelId){
		
		return commonsService.subjByclassId(classLevelId);
	}
	
	/**
	 * @author lichen
	* @Title: columnVersionByVerId
	* @Description: TODO(分册级联版本)
	* @param @param baseVersionId
	* @param @return    设定文件
	* @return List<VolumeView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("volumeversionbyverId")
	public List<VolumeView> volumeVersionByVerId(VolumeParm parm){
		
		return commonsService.volumeVersionByVerId(parm);
	}
	
	/**
	 * @author lichen
	* @Title: chaptervolumeBycId
	* @Description: TODO(章级联分册)
	* @param @param volumeId
	* @param @return    设定文件
	* @return List<ChapterView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("chaptervolumebycid")
	public List<ChapterView> chaptervolumeBycId(VolumeParm parm){
		
		return commonsService.chaptervolumeBycId(parm);
	}
	
	
	/**
	 * @author lichen
	* @Title: versionBySuClaId
	* @Description: TODO(版本级联学科)
	* @param @param versionParm
	* @param @return    设定文件
	* @return List<VersionView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("versionbysuclaid")
	public List<VersionView> versionBySuClaId(VersionParmView versionParm){
		
		return commonsService.versionBySuClaId(versionParm);
	}
	
	/**
	 * @author lichen
	* @Title: sectionChapBycId
	* @Description: TODO(节级联章)
	* @param @param parm
	* @param @return    设定文件
	* @return List<SectionView>    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("sectionchapbycid")
	public List<SectionView> sectionChapBycId(VolumeParm parm){
		
		return commonsService.sectionChapBycId(parm);
	}
	
	/**
	 * 获取老师学生
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectStudentByTeacher")
	public List<Map<String, Object>> selectStudentByTeacher(@RequestParam(required = true) String userId){
		return commonsService.getSelectStudentByTeacher(userId);
	}
	
	
	/**
	 * 获取老师学生
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectClassBySchool")
	public List<TreeBean> selectClassBySchool(@RequestParam(required = true) String userId){
		return commonsService.getSelectClassBySchool(userId);
	}
	
	
	/**
	 * 获取所有试卷类型
	 * @return
	 */
	@RequestMapping("getAllExamType")
	@ResponseBody
	public List<SelectModel> getAllExamType(){
		return commonsService.getAllExamType();
	}
	
	
    /**
     * 根据父级获取子级知识点
     */
    @ResponseBody
   	@RequestMapping("getSubKnowledgeByParent")
    public List<BaseKnowledge> getSubKnowledgeByParent(String parentId){
    	return commonsService.getSubKnowledgeByParent(parentId);
    }
    
    
    /**
     * 根据学段学科获取首节知识点
     * 
     */
     
    @ResponseBody
	@RequestMapping("getAllBaseKnowledgeBySemesterAndDiscipline")
   public List<BaseKnowledge> getAllBaseKnowledgeBySemesterAndDiscipline(String baseSemesterId, String baseDisciplineId){
    	return commonsService.getAllBaseKnowledgeBySemesterAndDiscipline(baseSemesterId, baseDisciplineId);
    }
    
    
    /**
     * 根据学科版本获取分册和章节信息
     * 
     */
	@ResponseBody
	@RequestMapping("getAllVCByClslevelSubjectVerionId")
	public List<VCSelectModel> getAllVCBySubjectVerionId(String subjectId,String versionId){
		return commonsService.getAllVCBySubjectVerionId(subjectId,versionId);
	}
}
