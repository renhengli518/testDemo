package com.codyy.oc.homework.dao;

import java.util.List;

import com.codyy.oc.homework.entity.Subject;

public interface SubjectMapper {
	//根据教师角色查询出对应的学科
	public List<Subject> findSubject(String userId);
	//根据班级角色查询出对应的学科
	public List<Subject> selectSubjectByClassId(String classId);
	//根据学生角色查询出对应的学科
	public List<Subject> selectSubjectByStuId(String stuId);
	//根据家长角色查询出对应的学科
	public List<Subject> selectSubjectByParentId(String parentId);
}
