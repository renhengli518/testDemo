package com.codyy.oc.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.page.Page;
import com.codyy.oc.base.dao.CommonsMapper;
import com.codyy.oc.base.view.IdNameView;
import com.codyy.oc.base.view.StudentClassView;
import com.codyy.oc.homework.dao.WorkDocMapper;
import com.codyy.oc.homework.dao.WorkHomeworkMapper;
import com.codyy.oc.homework.dao.WorkQueFillInAnswerMapper;
import com.codyy.oc.homework.dao.WorkQuestionMapper;
import com.codyy.oc.homework.dao.WorkQuestionRKnowledgeMapper;
import com.codyy.oc.homework.dao.WorkReadOverStuMapper;
import com.codyy.oc.homework.dao.WorkRecStuQueAnswerMapper;
import com.codyy.oc.homework.dao.WorkReceiveDocMapper;
import com.codyy.oc.homework.dao.WorkReceiveStuMapper;
import com.codyy.oc.homework.entity.WorkDoc;
import com.codyy.oc.homework.entity.WorkHomework;

@Service
public class WorkHomeworkService {

	@Autowired
	protected WorkHomeworkMapper homeworkMapper;

	@Autowired
	protected CommonsMapper commonsMapper;

	@Autowired
	protected WorkReceiveStuMapper receiveStuMapper;

	@Autowired
	protected WorkReadOverStuMapper overStuMapper;

	@Autowired
	protected WorkDocMapper docMapper;

	@Autowired
	protected WorkQuestionMapper workQuestionMapper;

	@Autowired
	protected WorkQueFillInAnswerMapper workQueFillInAnswerMapper;

	@Autowired
	protected WorkQuestionRKnowledgeMapper workKnowledgeMapper;

	@Autowired
	protected WorkRecStuQueAnswerMapper workRecStuQueAnswerMapper;

	@Autowired
	protected WorkReceiveDocMapper receiveDocMapper;

	public WorkHomework getHomeworkById(String id) {
		return homeworkMapper.selectByPrimaryKey(id);
	}

	public List<WorkDoc> getWorkDocByHomework(String homeworkId) {
		return docMapper.selectByHomework(homeworkId);
	}

	public List<StudentClassView> getWorkReceiveStuByHomework(String homeworkId) {
		return receiveStuMapper.selectUserByHomework(homeworkId);
	}

	public List<StudentClassView> getReadOverStuByHomework(String homeworkId) {
		return overStuMapper.selectByHomework(homeworkId);
	}

	public List<IdNameView> getHomeworkClass(String homeworkId) {
		return homeworkMapper.selectHomeworkClass(homeworkId);
	}

	public void getReadOverClassStudent(Page page) {
		page.setData(receiveStuMapper.selectReadOverClassStudentPageList(page));
	}

	public WorkDoc getWorkDocById(String workDocId){
		WorkDoc workDoc=docMapper.selectByPrimaryKey(workDocId);
		return workDoc;
	}
	
}
