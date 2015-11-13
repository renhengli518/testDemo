package com.codyy.oc.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseChapter;
import com.codyy.oc.base.entity.BaseClasslevel;
import com.codyy.oc.base.entity.BaseClasslevelSubjectVer;
import com.codyy.oc.base.entity.BaseConfigAreaLevel;
import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.base.entity.BaseSection;
import com.codyy.oc.base.entity.BaseSemester;
import com.codyy.oc.base.entity.BaseUser;
import com.codyy.oc.base.entity.BaseVolume;
import com.codyy.oc.base.entity.TreeBean;
import com.codyy.oc.base.view.BaseAreaView;
import com.codyy.oc.base.view.BaseChapterView;
import com.codyy.oc.base.view.BaseClslevelSubjectView;
import com.codyy.oc.base.view.BaseKnowledgeView;
import com.codyy.oc.base.view.BaseVolumeView;
import com.codyy.oc.base.view.IdNameView;
import com.codyy.oc.base.view.SelectModel;
import com.codyy.oc.base.view.SelectStudentView;
import com.codyy.oc.base.view.StudentClassView;
import com.codyy.oc.base.view.VCSelectModel;
import com.codyy.oc.questionlib.view.ChapterView;
import com.codyy.oc.questionlib.view.SectionView;
import com.codyy.oc.questionlib.view.SubjectView;
import com.codyy.oc.questionlib.view.VersionParmView;
import com.codyy.oc.questionlib.view.VersionView;
import com.codyy.oc.questionlib.view.VolumeParm;
import com.codyy.oc.questionlib.view.VolumeView;

public interface CommonsMapper {

	/**
	 * 
	 * getAreaListByParentId:(根据parentId获取该parentId下的所有直属行政区，若parentId为空，
	 * 则获取所有第一级行政区 )
	 * 
	 * @author yangyongwu
	 * @param map(parentId)
	 * @return
	 */
	public List<SelectModel> getAreaListByParentId(Map<String, String> map);
	
	public BaseAreaView getAreaById(Map<String, String> map);
	
	public List<BaseAreaView> getAreasAndLevelByParentId(Map<String, String> map);
	
	public BaseConfigAreaLevel getAreaLevelByLevelId(Map<Object, Object> map);
	
	/**
	 * 
	 * getSelectModelByAreaCode:(根据区域code获取)
	 * @author yangyongwu 
	 * @param areaCode
	 * @return
	 */
	SelectModel getSelectModelByAreaCode(String areaCode);

	/**
	 * 查询学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	public List<SelectModel> getSchoolByAreaId(Map<String, String> map);
	
	/**
	 * 查询多学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	public List<SelectModel> getSchoolsByAreaId(Map<String, String> map);

	/**
	 * 获取所有年级
	 * 
	 * @return
	 */
	public List<SelectModel> getAllClasslevels();
	
	/**
	 * 获取所有版本信息
	 * @return
	 */
	public List<SelectModel> getAllVersions();

	/**
	 * 获取所有的学科信息
	 * 
	 * @return
	 */
	public List<SelectModel> getAllSubjects();
	
	/**
	 * 获取所有的分册信息
	 * @return
	 */
	public List<SelectModel> getAllVolumes();
	
	public int getAreaMaxLevel();
	
	/**
	 * 
	 * getBaseAreaByParentId:(根据父级ID获取子区域)
	 * @param parentId
	 * @return
	 */
	public List<BaseArea> getBaseAreaByParentId(@Param(value="parentId") String parentId);
	
	/**
	 * getBaseAreaById:(根据ID获取区域对象)
	 * @param areaId
	 * @return
	 */
	public BaseArea getBaseAreaById(String areaId);
	/**
	 * getClassLevelInfoBySchoolId:(根据学校id获取学校的所有年级信息)
	 * @author yangyongwu 
	 * @param schoolId
	 * @return
	 */
	public List<SelectModel> getClassLevelInfoBySchoolId(String schoolId);
	
	
	public List<BaseConfigAreaLevel> getAllAreaLevelConfig();
	/**
	 * 
	 * getSubjectByClasslevelId:(根据年级id获取该年级下对应的学科信息)
	 * @author yangyongwu 
	 * @param classLevelId
	 * @return
	 */
	public List<SelectModel> getSubjectByClasslevelId(String classLevelId);
	
	
	/**
	 * 
	 * getAllSemester:(获取所有的学段)
	 * @return 获取所有的学段
	 */
	public List<BaseSemester> getAllSemester();
	
	/**
	 * getAllClasslevelBySemesterId:(根据学段获取年级)
	 * @return 年级列表
	 */
	public List<BaseClasslevel> getAllClasslevelBySemesterId(String semesterId);
	
	/**
	 * getAllSubjectByClslevelId:(根据年级ID获取对应学科)
	 * @return 学科列表
	 */
	public List<BaseClslevelSubjectView> getAllSubjectByClslevelId(String classlevelId);
	
	/**
	 * getAllVersionByClslevelSubjectId:(根据年级学科ID获取版本列表)
	 * @return 版本列表
	 */
	public List<BaseClasslevelSubjectVer> getAllVersionByClslevelSubjectId(String classlevelSubjectId);
	
	/**
	 * getAllVolumneByClslevelSubjectVerId:(根据年级学科版本关系ID获取分册列表)
	 * @return 分册列表
	 */
	public List<BaseVolume> getAllVolumeByClslevelSubjectVerId(String classlevelSubjectVersionId);
	
	/**
	 * 根据年级学科和版本ID获取所有的分册
	 * @param classlevelId
	 * @param subjectId
	 * @param versionId
	 * @return
	 */
	public List<BaseVolumeView> getAllVolumeByClslevelSubjectVerionId(@Param(value="classlevelId")String classlevelId,@Param(value="subjectId")String subjectId,@Param(value="versionId")String versionId);
	
	/**
	 * 根据学科和版本ID获取所有的分册和章节
	 * @param subjectId
	 * @param versionId
	 * @return
	 */
	public List<VCSelectModel> getAllVCBySubjectVerionId(@Param(value="subjectId")String subjectId,@Param(value="versionId")String versionId);
	
	
	/**
	 * 根据分册获取章信息
	 * @param volumeId
	 * @return
	 */
	public List<BaseChapterView> getAllChapterViewByVolumeId(String volumeId);	
	
	/**
	 * 根据学段和学科获取根级知识点
	 * @param semesterId
	 * @param subjectId
	 * @return
	 */
	public List<BaseKnowledgeView> getRootKnowledgeViewBySemsterSubjectId(@Param(value="semesterId") String semesterId,@Param(value="subjectId") String subjectId);
	
	/**
	 * 根据父级ID获取子知识点
	 * @param parentId
	 * @return
	 */
	public List<BaseKnowledgeView> getChildKnowledgeViewByParentId(String parentId);
	
	/**
	 * getAllChapterByVolumeId:(根据分册ID获取章列表)
	 * @return 章列表
	 */
	public List<BaseChapter> getAllChapterByVolumeId(String volumeId);
	
	/**
	 * getAllSectionByChapterId:(根据章ID获取节列表)
	 * @return 节列表
	 */
	public List<BaseSection> getAllSectionByChapterId(String chapterId);
	
	/**
	 * getRootKnowledgeBySemeterSubjectId:(根据学段和学科ID获取根级知识点)
	 * @param semeterId
	 * @param subjectId
	 * @return 知识点列表
	 */
	public List<BaseKnowledge> getRootKnowledgeBySemesterSubjectId(@Param(value="semesterId") String semesterId,@Param(value="subjectId") String subjectId);
	
	/**
	 * getChildKnowledgeByParentId:(根据父级ID获取下级子知识点)
	 * @return 下级子知识点列表
	 */
	public List<BaseKnowledge> getChildKnowledgeByParentId(String parentId);
	
	/**
	 * 根据ids查询用户信息
	 * getBaseUserListByIds
	 * @param baseUserIds
	 * @return List<BaseUser>
	 */
	public List<BaseUser> getBaseUserListByIds(List<String> baseUserIds);
    
	/**
	 * 根据年级id查询班级
	 * @param map
	 * @return List<SelectModel>
	 */
	public List<SelectModel> getClassByClasslevelId(Map<String, Object> map);
	
	/**
	 * 获取老师所教学科
	 * @param userId
	 * @return
	 */
	List<IdNameView> getTeacherSubjects(String userId);
	
	/**
	 * 获取老师所教学生
	 * @param userId
	 */
	List<StudentClassView> getStudentByTeacher(String userId);
	
	/**
	 * @author lichen
	* @Title: classLevelBySemId
	* @Description: TODO(根据选中学段的id来获取对应的年级集合)
	* @param @param semesterId
	* @param @return    设定文件
	* @return List<BaseClasslevel>    返回类型
	* @throws
	 */
	public List<BaseClasslevel> classLevelBySemId(String semesterId);
	
	/**
	 * @author lichen
	* @Title: subjByclassId
	* @Description: TODO(学科级联年级)
	* @param @param classLevelId
	* @param @return    设定文件
	* @return List<SubjectView>    返回类型
	* @throws
	 */
	public List<SubjectView> subjByclassId(String classLevelId);
	
	/**
	 * @author lichen
	* @Title: columnVersionByVerId
	* @Description: TODO(分册级联版本)
	* @param @param baseVersionId
	* @param @return    设定文件
	* @return List<VolumeView>    返回类型
	* @throws
	 */
	public List<VolumeView> volumeVersionByVerId(VolumeParm Parm);
	
	/**
	 * @author lichen
	* @Title: chaptervolumeBycId
	* @Description: TODO(章级联分册)
	* @param @param volumeId
	* @param @return    设定文件
	* @return List<ChapterView>    返回类型
	* @throws
	 */
	public List<ChapterView> chaptervolumeBycId( VolumeParm parm);
	
	/**
	 * @author lichen
	* @Title: versionBySuClaId
	* @Description: TODO(根据学科和年级确定版本)
	* @param @param versionParm
	* @param @return    设定文件
	* @return List<VersionView>    返回类型
	* @throws
	 */
	public List<VersionView> versionBySuClaId(VersionParmView versionParm);
	
	/**
	 * @author lichen
	* @Title: sectionChapBycId
	* @Description: TODO(根据节级联章)
	* @param @param parm
	* @param @return    设定文件
	* @return List<SectionView>    返回类型
	* @throws
	 */
	public List<SectionView> sectionChapBycId(VolumeParm parm);
	
	
	/**
	 * 根据老师查询学生
	 * @param userId
	 * @return
	 */
	List<SelectStudentView> getSelectStudentByTeacher(String userId);
	
	/**
	 * 根据学校查询班级
	 * @param userId
	 * @return
	 */
	List<TreeBean> getSelectClassBySchool(String userId);
	
	/**
	 * @author renhengli
	 * 获取所有试卷类型
	 * @return
	 */
	List<SelectModel> getAllExamType();
	
	/**
	 * @author lichen
	* @Title: getSubKnowledgeByParent
	* @Description: TODO(根据父知识点获得子知识点)
	* @param @param parentId
	* @param @return    设定文件
	* @return List<BaseKnowledge>    返回类型
	* @throws
	 */
	List<BaseKnowledge> getSubKnowledgeByParent(String parentId);
	
	/**
	 * @author lichen
	* @Title: getAllBaseKnowledgeBySemesterAndDiscipline
	* @Description: TODO(根据学段学科获得首节知识点)
	* @param @param map
	* @param @return    设定文件
	* @return List<BaseKnowledge>    返回类型
	* @throws
	 */
	List<BaseKnowledge> getAllBaseKnowledgeBySemesterAndDiscipline(Map<String, String> map);
	
	/**
	 * @author lichen
	* @Title: selecSemById
	* @Description: TODO(根据学段的id来获取对应的学段名)
	* @param @param baseSemId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecSemById(String baseSemId);
	
	/**
	 * @author lichen
	* @Title: selecClassById
	* @Description: TODO(根据年级的id来获得对应的年级名称)
	* @param @param baseClassId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecClassById(String baseClassId);
	
	/**
	 * @author lichen
	* @Title: selecSubjectById
	* @Description: TODO(根据学科的id来获得对应的学科名字)
	* @param @param baseSubjId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecSubjectById(String baseSubjId);
	
	/**
	 * @author lichen
	* @Title: selecVerNameById
	* @Description: TODO(根据版本号来获得对应的版本内容)
	* @param @param baseVersionId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecVerNameById(String baseVersionId);
	
	/**
	 * @author lichen
	* @Title: selecVoluNameById
	* @Description: TODO(根据分册的id来获得对应的分册名)
	* @param @param baseVolumId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecVoluNameById(String baseVolumId);
	
	/**
	 * @author lichen
	* @Title: selecChapNameById
	* @Description: TODO(根据对应的章id来获得对应的章)
	* @param @param chapterId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecChapNameById(String chapterId);
	
	/**
	 * @author lichen
	* @Title: selecSectNameById
	* @Description: TODO(根据节的id获得对应的节)
	* @param @param sectionId
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	String selecSectNameById(String sectionId);
	
	/**
	 * 
	* @Title: selectStuIdsByClassIds
	* @Description: 根据班级IDs获取学生IDs 
	* @param @param stuIds
	* @param @return
	* @return List<String>    
	* @throws
	 */
	List<String> selectStuIdsByClassIds(List<String> stuIds);
	
	/**
	 * 
	* @Title: selectStuInfoById
	* @Description: 根据学生ID获取学段，年级ID
	* @param @param id
	* @param @return
	* @return SelectStudentView    
	* @throws
	 */
	SelectStudentView selectStuInfoById(String id);
	
}
