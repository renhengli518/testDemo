package com.codyy.oc.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.CommonsConstant;
import com.codyy.oc.base.dao.BaseCatalogDao;
import com.codyy.oc.base.dao.CommonsMapper;
import com.codyy.oc.base.entity.BaseArea;
import com.codyy.oc.base.entity.BaseCatalog;
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
import com.codyy.oc.base.view.VCSelectModel;
import com.codyy.oc.questionlib.view.ChapterView;
import com.codyy.oc.questionlib.view.SectionView;
import com.codyy.oc.questionlib.view.SubjectView;
import com.codyy.oc.questionlib.view.VersionParmView;
import com.codyy.oc.questionlib.view.VersionView;
import com.codyy.oc.questionlib.view.VolumeParm;
import com.codyy.oc.questionlib.view.VolumeView;

@Service
public class CommonsService {

	@Autowired
	private CommonsMapper commonsMapper;

	@Autowired
	BaseCatalogDao baseCatalogDao;

	/**
	 * 根据parentId获取区域列表，如果parentId为空，则获取第一级区域列表
	 */
	public List<SelectModel> getAreaListByParentId(String parentId) {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("parentId", parentId);
		return commonsMapper.getAreaListByParentId(map);
	}

	/**
	 * 根据区域id号获取区域信息
	 * 
	 * @param id
	 * @return
	 */
	public BaseAreaView getAreaById(String id) {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("id", id);
		return commonsMapper.getAreaById(map);
	}

	/**
	 * 查询区域和级别
	 */
	public List<BaseAreaView> getAreasAndLevelByParentId(String parentId) {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("parentId", parentId);
		return commonsMapper.getAreasAndLevelByParentId(map);
	}

	/**
	 * 根据level的级别号，查询对应的名称
	 * 
	 * @param levelId
	 * @return
	 */
	public BaseConfigAreaLevel getAreaLevelByLevelId(Integer levelId) {
		Map<Object, Object> map = new HashMap<Object, Object>(1);
		map.put("levelId", levelId);
		return commonsMapper.getAreaLevelByLevelId(map);
	}

	/**
	 * 查询学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	public List<SelectModel> getSchoolByAreaId(String areaId) {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("areaId", areaId);
		return commonsMapper.getSchoolByAreaId(map);
	}

	/**
	 * 查询多区域下多学校<br/>
	 * 1、区域为空时，表示查询所有学校<br/>
	 * 2、有区域id号时，查询当前区域下的学校<br/>
	 * 
	 * @param areaId
	 * @return
	 */
	public List<SelectModel> getSchoolsByAreaId(String areaId) {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("areaId", areaId);
		return commonsMapper.getSchoolsByAreaId(map);
	}

	/**
	 * 获取所有年级
	 * 
	 * @return
	 */
	public List<SelectModel> getAllClasslevels() {
		return commonsMapper.getAllClasslevels();
	}

	/**
	 * 获取所有版本信息
	 * 
	 * @return
	 */
	public List<SelectModel> getAllVersions() {
		return commonsMapper.getAllVersions();
	}

	/**
	 * getAllSubjects:(获取所有的学科信息)
	 * 
	 * @return 所有的学科信息
	 */
	public List<SelectModel> getAllSubjects() {
		return commonsMapper.getAllSubjects();
	}

	/**
	 * getAllVolumes:(获取所有的分册信息)
	 * 
	 * @return 所有的学科信息
	 */
	public List<SelectModel> getAllVolumes() {
		return commonsMapper.getAllVolumes();
	}

	/**
	 * getAreaMaxLevel:(获取行政区域最大层级)
	 * 
	 * @return 行政区域最大层级
	 */
	public int getAreaMaxLevel() {
		return commonsMapper.getAreaMaxLevel();
	}

	/**
	 * getBaseAreaByParentId:(根据父级ID获取下级子区域)
	 * 
	 * @param parentId
	 * @return 获取子区域
	 */
	public List<BaseArea> getBaseAreaByParentId(String parentId) {
		return commonsMapper.getBaseAreaByParentId(parentId);
	}

	/**
	 * 
	 * getBaseAreaById:(根据ID获取区域对象)
	 * 
	 * @param areaId
	 * @return
	 */
	public BaseArea getBaseAreaById(String areaId) {
		return commonsMapper.getBaseAreaById(areaId);
	}

	/**
	 * 
	 * getAllAreaLevelConfig:(获取所有的区域层级信息)
	 * 
	 * @return
	 */
	public List<BaseConfigAreaLevel> getAllAreaLevelConfig() {
		return commonsMapper.getAllAreaLevelConfig();
	}

	/**
	 * 
	 * getAllAreaLevelConfigMap:(获取所有的区域层级信息MAP格式)
	 * 
	 * @return
	 */
	public Map<String, String> getAllAreaLevelConfigMap() {
		List<BaseConfigAreaLevel> li = commonsMapper.getAllAreaLevelConfig();
		Map<String, String> map = new HashMap<String, String>();
		if (li != null) {
			for (BaseConfigAreaLevel lvl : li) {
				map.put(lvl.getAreaLevel().toString(), lvl.getLevelTitle());
			}
		}
		return map;
	}

	/**
	 * 
	 * getClassLevelInfoBySchoolId:(根据学校id获取学校的所有年级信息)
	 * 
	 * @author yangyongwu
	 * @param schoolId
	 * @return
	 */
	public List<SelectModel> getClassLevelInfoBySchoolId(String schoolId) {
		return commonsMapper.getClassLevelInfoBySchoolId(schoolId);
	}

	/**
	 * 
	 * getSubjectByClasslevelId:(根据年级id获取该年级下对应的学科信息)
	 * 
	 * @author yangyongwu
	 * @param classLevelId
	 * @return
	 */
	public List<SelectModel> getSubjectByClasslevelId(String classLevelId) {
		return commonsMapper.getSubjectByClasslevelId(classLevelId);
	}

	/**
	 * 
	 * getAreaName:(截取获取区域信息)
	 *
	 * @param areaName
	 * @param areaLevel
	 * @return
	 * @author zhangtian
	 */
	public String getAreaName(String areaName, Integer areaLevel) {
		Queue<String> queue = new LinkedList<String>();
		String tempName = "";
		if (StringUtils.isNotBlank(areaName)) {
			queue.addAll(Arrays.asList(areaName.split(CommonsConstant.AREA_SPLIT)));
			if (areaLevel > 0) {
				for (int i = 0; i < areaLevel; i++) {
					queue.poll();
				}
			}
		}

		if (queue.size() > 0) {
			Integer length = queue.size();
			for (int i = 0; i < length; i++) {
				if (StringUtils.isNotBlank(tempName)) {
					tempName = tempName + CommonsConstant.AREA_SPLIT + queue.poll();
				} else {
					tempName = queue.poll();
				}
			}
		}
		return tempName;
	}

	/**
	 * getAllSemester:(获取所有的学段)
	 * 
	 * @return
	 */
	public List<BaseSemester> getAllSemester() {
		return commonsMapper.getAllSemester();
	}

	/**
	 * getAllClasslevelBySemesterId:(根据学段获取年级)
	 * 
	 * @return 年级列表
	 */
	public List<BaseClasslevel> getAllClasslevelBySemesterId(String semesterId) {
		return commonsMapper.getAllClasslevelBySemesterId(semesterId);
	}

	/**
	 * getAllSubjectByClslevelId:(根据年级ID获取对应学科)
	 * 
	 * @return 学科列表
	 */
	public List<BaseClslevelSubjectView> getAllSubjectByClslevelId(String classlevelId) {
		return commonsMapper.getAllSubjectByClslevelId(classlevelId);
	}

	/**
	 * getAllVersionByClslevelSubjectId:(根据年级学科ID获取版本列表)
	 * 
	 * @return 版本列表
	 */
	public List<BaseClasslevelSubjectVer> getAllVersionByClslevelSubjectId(String classlevelSubjectId) {
		return commonsMapper.getAllVersionByClslevelSubjectId(classlevelSubjectId);
	}

	/**
	 * getAllVolumneByClslevelSubjectVerId:(根据年级学科版本关系ID获取分册列表)
	 * 
	 * @return 分册列表
	 */
	public List<BaseVolume> getAllVolumeByClslevelSubjectVerId(String classlevelSubjectVersionId) {
		return commonsMapper.getAllVolumeByClslevelSubjectVerId(classlevelSubjectVersionId);
	}

	/**
	 * 根据年级学科和版本ID获取所有的分册
	 * 
	 * @param classlevelId
	 * @param subjectId
	 * @param versionId
	 * @return
	 */
	public List<BaseVolumeView> getAllVolumeByClslevelSubjectVerionId(String classlevelId, String subjectId, String versionId) {
		return commonsMapper.getAllVolumeByClslevelSubjectVerionId(classlevelId, subjectId, versionId);
	}

	/**
	 * 根据年级学科和版本ID获取所有的分册
	 * 
	 * @param classlevelId
	 * @param subjectId
	 * @param versionId
	 * @return
	 */
	public List<VCSelectModel> getAllVCBySubjectVerionId(String subjectId, String versionId) {
		return commonsMapper.getAllVCBySubjectVerionId(subjectId, versionId);
	}

	/**
	 * 根据分册ID获取对应章信息
	 * 
	 * @param volumeId
	 * @return
	 */
	public List<BaseChapterView> getAllChapterViewByVolumeId(String volumeId) {
		return commonsMapper.getAllChapterViewByVolumeId(volumeId);
	}

	/**
	 * 根据学段学科获取根级知识点
	 * 
	 * @param semesterId
	 * @param subjectId
	 * @return
	 */
	public List<BaseKnowledgeView> getRootKnowledgeViewBySemsterSubjectId(String semesterId, String subjectId) {
		return commonsMapper.getRootKnowledgeViewBySemsterSubjectId(semesterId, subjectId);
	}

	/**
	 * 根据父级ID获取子知识点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<BaseKnowledgeView> getChildKnowledgeViewByParentId(String parentId) {
		return commonsMapper.getChildKnowledgeViewByParentId(parentId);
	}

	/**
	 * getAllChapterByVolumeId:(根据分册ID获取章列表)
	 * 
	 * @return 章列表
	 */
	public List<BaseChapter> getAllChapterByVolumeId(String volumeId) {
		return commonsMapper.getAllChapterByVolumeId(volumeId);
	}

	/**
	 * getAllSectionByChapterId:(根据章ID获取节列表)
	 * 
	 * @return 节列表
	 */
	public List<BaseSection> getAllSectionByChapterId(String chapterId) {
		return commonsMapper.getAllSectionByChapterId(chapterId);
	}

	/**
	 * getRootKnowledgeBySemeterSubjectId:(根据学段和学科ID获取根级知识点)
	 * 
	 * @param semeterId
	 * @param subjectId
	 * @return 知识点列表
	 */
	public List<BaseKnowledge> getRootKnowledgeBySemesterSubjectId(String semesterId, String subjectId) {
		return commonsMapper.getRootKnowledgeBySemesterSubjectId(semesterId, subjectId);
	}

	/**
	 * getChildKnowledgeByParentId:(根据父级ID获取下级子知识点)
	 * 
	 * @return 下级子知识点列表
	 */
	public List<BaseKnowledge> getChildKnowledgeByParentId(String parentId) {
		return commonsMapper.getChildKnowledgeByParentId(parentId);
	}

	/**
	 * 根据ids查询用户
	 * 
	 * @param baseUserIds
	 * @return
	 */
	public List<BaseUser> getBaseUserListByIds(List<String> baseUserIds) {
		return commonsMapper.getBaseUserListByIds(baseUserIds);
	}

	/**
	 * 根据年级id获取班级
	 * @param user_schoolId 
	 * 
	 * @param baseUserIds
	 * @return
	 */
	public List<SelectModel> getClassByClasslevelId(String classlevelId, String user_schoolId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classlevelId", classlevelId);
		map.put("schoolId", user_schoolId);
		return commonsMapper.getClassByClasslevelId(map);
	}

	public List<BaseCatalog> getByIntelligence(String baseSemesterId, String baseDisciplineId, String baseVersionId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("baseSemesterId", baseSemesterId);
		map.put("baseDisciplineId", baseDisciplineId);
		map.put("baseVersionId", baseVersionId);
		return baseCatalogDao.getByIntelligence(map);
	}

	/**
	 * 获取老师所教学科
	 * 
	 * @param userId
	 * @return
	 */
	public List<IdNameView> getTeacherSubjects(String userId) {
		return commonsMapper.getTeacherSubjects(userId);
	}

	/**
	 * @author lichen
	 * @Title: classLevelBySemId
	 * @Description: TODO(根据学段的id来获取对应年级的集合)
	 * @param @param semesterId
	 * @param @return 设定文件
	 * @return List<BaseClasslevel> 返回类型
	 * @throws
	 */
	public List<BaseClasslevel> classLevelBySemId(String semesterId) {
		return commonsMapper.classLevelBySemId(semesterId);
	}

	/**
	 * @author lichen
	 * @Title: subjByclassId
	 * @Description: TODO(学科级联年级)
	 * @param @param classLevelId
	 * @param @return 设定文件
	 * @return List<SubjectView> 返回类型
	 * @throws
	 */
	public List<SubjectView> subjByclassId(String classLevelId) {

		return commonsMapper.subjByclassId(classLevelId);
	}

	/**
	 * @author lichen
	 * @Title: columnVersionByVerId
	 * @Description: TODO(分册级联版本)
	 * @param @param baseVersionId
	 * @param @return 设定文件
	 * @return List<VolumeView> 返回类型
	 * @throws
	 */
	public List<VolumeView> volumeVersionByVerId(VolumeParm parm) {

		return commonsMapper.volumeVersionByVerId(parm);
	}

	/**
	 * @author lichen
	 * @Title: chaptervolumeBycId
	 * @Description: TODO(章级联分册)
	 * @param @param volumeId
	 * @param @return 设定文件
	 * @return List<ChapterView> 返回类型
	 * @throws
	 */
	public List<ChapterView> chaptervolumeBycId(VolumeParm parm) {

		return commonsMapper.chaptervolumeBycId(parm);
	}

	/**
	 * @author lichen
	 * @Title: versionBySuClaId
	 * @Description: TODO(版本级联学科)
	 * @param @param versionParm
	 * @param @return 设定文件
	 * @return List<VersionView> 返回类型
	 * @throws
	 */
	public List<VersionView> versionBySuClaId(VersionParmView versionParm) {
		return commonsMapper.versionBySuClaId(versionParm);
	}

	/**
	 * @author lichen
	 * @Title: sectionChapBycId
	 * @Description: TODO(节级联章)
	 * @param @param parm
	 * @param @return 设定文件
	 * @return List<SectionView> 返回类型
	 * @throws
	 */
	public List<SectionView> sectionChapBycId(VolumeParm parm) {
		return commonsMapper.sectionChapBycId(parm);
	}

	/**
	 * 根据老师查询学生
	 * 
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getSelectStudentByTeacher(String userId) {
		List<SelectStudentView> list = commonsMapper.getSelectStudentByTeacher(userId);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(list)) {
			String classlevelId = null;
			String classId = null;
			Map<String, Object> classlevelMap = null;
			Map<String, Object> classMap = null;
			List<Map<String, Object>> classList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
			for (SelectStudentView studentView : list) {
				if (!studentView.getClasslevelId().equals(classlevelId)) {
					classlevelId = studentView.getClasslevelId();
					classId = studentView.getClassId();
					
					classlevelMap = new HashMap<String, Object>();
					classlevelMap.put("id", classlevelId);
					classlevelMap.put("name", studentView.getClasslevelName());
					result.add(classlevelMap);
					classList = new ArrayList<Map<String, Object>>();
					classlevelMap.put("children", classList);

					classMap = new HashMap<String, Object>();
					classMap.put("id", classId);
					classMap.put("name", studentView.getClassName());
					classList.add(classMap);
					userList = new ArrayList<Map<String, Object>>();
					classMap.put("children", userList);
				} else if (!studentView.getClassId().equals(classId)) {
					classId = studentView.getClassId();
					
					classMap = new HashMap<String, Object>();
					classMap.put("id", classId);
					classMap.put("name", studentView.getClassName());
					classList.add(classMap);
					userList = new ArrayList<Map<String, Object>>();
					classMap.put("children", userList);
				}
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("id", studentView.getUserId());
				userMap.put("name", studentView.getName());
				userMap.put("classId", studentView.getClassId());
				userList.add(userMap);
			}
		}
		return result;
	}
	
	
	/**
	 * 根据学校查询班级
	 * 
	 * @param userId
	 * @return
	 */
	public List<TreeBean> getSelectClassBySchool(String userId) {
		List<TreeBean> list = commonsMapper.getSelectClassBySchool(userId);
//		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		if (CollectionUtils.isNotEmpty(list)) {
//			String classlevelId = null;
//			String classId = null;
//			Map<String, Object> classlevelMap = null;
//			Map<String, Object> classMap = null;
//			List<Map<String, Object>> classList = new ArrayList<Map<String, Object>>();
//			List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
//			for (SelectStudentView studentView : list) {
//				if (!studentView.getClasslevelId().equals(classlevelId)) {
//					classlevelId = studentView.getClasslevelId();
//					classId = studentView.getClassId();
//					
//					classlevelMap = new HashMap<String, Object>();
//					classlevelMap.put("id", classlevelId);
//					classlevelMap.put("name", studentView.getClasslevelName());
//					result.add(classlevelMap);
//					classList = new ArrayList<Map<String, Object>>();
//					classlevelMap.put("children", classList);
//
//					classMap = new HashMap<String, Object>();
//					classMap.put("id", classId);
//					classMap.put("name", studentView.getClassName());
//					classList.add(classMap);
//					userList = new ArrayList<Map<String, Object>>();
//					classMap.put("children", userList);
//				} else if (!studentView.getClassId().equals(classId)) {
//					classId = studentView.getClassId();
//					
//					classMap = new HashMap<String, Object>();
//					classMap.put("id", classId);
//					classMap.put("name", studentView.getClassName());
//					classList.add(classMap);
//					userList = new ArrayList<Map<String, Object>>();
//					classMap.put("children", userList);
//				}
//				Map<String, Object> userMap = new HashMap<String, Object>();
//				userMap.put("id", studentView.getUserId());
//				userMap.put("name", studentView.getName());
//				userMap.put("classId", studentView.getClassId());
//				userList.add(userMap);
//			}
//		}
		return list;
	}

	/**
	 * 获取所有试卷类型
	 * 
	 * @return
	 */
	public List<SelectModel> getAllExamType() {
		return commonsMapper.getAllExamType();
	}

	/**
	 * @author lichen
	 * @Title: getSubKnowledgeByParent
	 * @Description: TODO(根据父级获得子级知识点)
	 * @param @param parentId
	 * @param @return 设定文件
	 * @return List<BaseKnowledge> 返回类型
	 * @throws
	 */
	public List<BaseKnowledge> getSubKnowledgeByParent(String parentId) {
		return commonsMapper.getSubKnowledgeByParent(parentId);
	}

	/**
	 * @author lichen
	 * @Title: getAllBaseKnowledgeBySemesterAndDiscipline
	 * @Description: TODO(根据学段和学科获取首节知识点)
	 * @param @param baseSemesterId
	 * @param @param baseDisciplineId
	 * @param @return 设定文件
	 * @return List<BaseKnowledge> 返回类型
	 * @throws
	 */
	public List<BaseKnowledge> getAllBaseKnowledgeBySemesterAndDiscipline(String baseSemesterId, String baseDisciplineId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("baseSemesterId", baseSemesterId);
		map.put("baseDisciplineId", baseDisciplineId);
		return commonsMapper.getAllBaseKnowledgeBySemesterAndDiscipline(map);
	}

	/**
	 * @author lichen
	 * @Title: selecSemById
	 * @Description: TODO(根据学段的id获得对应的学段名)
	 * @param @param baseSemId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecSemById(String baseSemId) {
		return commonsMapper.selecSemById(baseSemId);
	}

	/**
	 * @author lichen
	 * @Title: selecClassById
	 * @Description: TODO(根据年级的id获得对应的年级名称)
	 * @param @param baseClassId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecClassById(String baseClassId) {
		return commonsMapper.selecClassById(baseClassId);
	}

	/**
	 * @author lichen
	 * @Title: selecSubjectById
	 * @Description: TODO(根据学科的id来获得对应的学科的名字)
	 * @param @param baseSubjId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecSubjectById(String baseSubjId) {
		return commonsMapper.selecSubjectById(baseSubjId);
	}

	/**
	 * @author lichen
	 * @Title: selecVerNameById
	 * @Description: TODO(根据版本号获得对应的版本名)
	 * @param @param baseVersionId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecVerNameById(String baseVersionId) {
		return commonsMapper.selecVerNameById(baseVersionId);
	}

	/**
	 * @author lichen
	 * @Title: selecVoluNameById
	 * @Description: TODO(根据分册的id获得对应的分册名)
	 * @param @param baseVolumId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecVoluNameById(String baseVolumId) {
		return commonsMapper.selecVoluNameById(baseVolumId);
	}

	/**
	 * @author lichen
	 * @Title: selecChapNameById
	 * @Description: TODO(根据章获得对应的章的名字)
	 * @param @param chapterId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecChapNameById(String chapterId) {
		return commonsMapper.selecChapNameById(chapterId);
	}

	/**
	 * @author lichen
	 * @Title: selecSectNameById
	 * @Description: TODO(根据节的id获得对应的节的名字)
	 * @param @param sectionId
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String selecSectNameById(String sectionId) {
		return commonsMapper.selecSectNameById(sectionId);
	}
	
	/**
	 * 
	* @Title: selectStuInfoById
	* @Description: 根据学生ID获取学生学段年级ID
	* @param @param id
	* @param @return
	* @return SelectStudentView    
	* @throws
	 */
	public SelectStudentView selectStuInfoById(String id){
		return commonsMapper.selectStuInfoById(id);
	}
}
