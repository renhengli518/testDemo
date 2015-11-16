package com.codyy.oc.questionlib.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codyy.commons.HostConfig;
import com.codyy.commons.context.SpringContext;
import com.codyy.commons.page.Page;
import com.codyy.commons.servlet.ImageUploadServlet;
import com.codyy.commons.utils.DateUtils;
import com.codyy.commons.utils.StringUtils;
import com.codyy.commons.utils.UUIDUtils;
import com.codyy.oc.base.dao.BaseKnowledgeDao;
import com.codyy.oc.base.dao.BaseSemesterDao;
import com.codyy.oc.base.entity.BaseKnowledge;
import com.codyy.oc.base.form.QuestionListCriteria;
import com.codyy.oc.base.form.QuestionListResult;
import com.codyy.oc.base.parse.ByteToInputStream;
import com.codyy.oc.base.parse.ErrorInfo;
import com.codyy.oc.base.parse.PictureInfo;
import com.codyy.oc.base.parse.QuestionInfo;
import com.codyy.oc.questionlib.dao.QueFavoriteMapper;
import com.codyy.oc.questionlib.dao.QueQuestionMapper;
import com.codyy.oc.questionlib.dao.QueQuestionRKnowledgeMapper;
import com.codyy.oc.questionlib.entity.QueFavorite;
import com.codyy.oc.questionlib.entity.QueQuestion;
import com.codyy.oc.questionlib.entity.QueQuestionRChapter;
import com.codyy.oc.questionlib.entity.QueQuestionRKnowledge;
import com.codyy.oc.questionlib.form.QuestionFormFields;
import com.codyy.oc.questionlib.view.UpDateMothTimeView;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class QueQuestionService {
	
	
	@Autowired
	private QueQuestionMapper queQuestionMapper;

	@Autowired
	private QueQuestionRKnowledgeMapper queQuestionRKnowledgeMapper;

	@Autowired
	private BaseKnowledgeDao baseKnowledgeDao;

	@Autowired
	private QueFavoriteMapper queFavoriteMapper;
	
	@Autowired
    BaseSemesterDao baseSemesterDao;
	
	@Autowired
	private CreateQuestionLibService createQuestionLibService;//进行习题的导入
	
    private static final Log logger = LogFactory.getLog(QueQuestionService.class);

	/**
	 * 获取共享习题列表
	 * 
	 * @param page
	 * @return
	 */
	public Page getShareQuePageList(Page page, Map<String, Object> map) {
		List<QuestionListResult> data = queQuestionMapper.getShareQuePageList(page);
		// 封装知识点（多个用字符串拼接）,并加上收藏标记
		if (data.size() > 0) {
			for (QuestionListResult dto : data) {
				combineKnowledges(dto);
				// 加上收藏标记
				map.put("queQuestionId", dto.getQueQuestionId());
				QueFavorite queFavorite = queFavoriteMapper.selectByQesIdAndUserId(map);
				if (queFavorite == null) {
					dto.setQueFavoriteId(null);
				} else {
					dto.setQueFavoriteId(queFavorite.getQueFavoriteId());
				}

			}
		}
		page.setData(data);
		return page;
	}
	
	/**
	 * 获取教师出题列表
	 * 
	 * @param page
	 * @return
	 */
	public Page getTeaQuePageList(Page page, Map<String, Object> map) {
		List<QuestionListResult> data = queQuestionMapper.getTeaQuePageList(page);
		// 封装知识点（多个用字符串拼接）,并加上收藏标记
		if (data.size() > 0) {
			for (QuestionListResult dto : data) {
				combineKnowledges(dto);
				// 加上收藏标记
				map.put("queQuestionId", dto.getQueQuestionId());
				QueFavorite queFavorite = queFavoriteMapper.selectByQesIdAndUserId(map);
				if (queFavorite == null) {
					dto.setQueFavoriteId(null);
				} else {
					dto.setQueFavoriteId(queFavorite.getQueFavoriteId());
				}

			}
		}
		page.setData(data);
		return page;
	}

	/**
	 * 根据Id 获取question
	 * 
	 * @param queQuestionId
	 * @return
	 */
	public QueQuestion getQuestionById(String queQuestionId) {
		return queQuestionMapper.selectByPrimaryKey(queQuestionId);
	}

	/**
	 * @author lichen
	 * @Title: toEditQuestion
	 * @Description: TODO(跳转到编辑习题的页面(跳转之前根据习题id获取习题的所有信息))
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public QueQuestion selectQuesById(String questionId) {
		QueQuestion queQuestion = queQuestionMapper.queryQuestionById(questionId);
		return queQuestion;
	}

	public QuestionListResult formQuestionDetailResult(QuestionListResult originObj) {
		// 组装末节知识点部分
		List<QueQuestionRKnowledge> rknowledgesList = originObj.getrKnowledges();
		QueQuestionRKnowledge rknowledge = null;
		if (rknowledgesList != null && rknowledgesList.size() > 0) {
			StringBuilder knowledgeIds = new StringBuilder();
			String kId = "";
			List<String> kIds = new ArrayList<String>();
			// 形成知识点IDs
			for (int j = 0; j < rknowledgesList.size(); j++) {
				rknowledge = rknowledgesList.get(j);
				kId = rknowledge.getBaseEndKnowledgeId();
				kIds.add(kId);
				if (j != (rknowledgesList.size() - 1)) {
					knowledgeIds.append("'" + kId + "',");
				} else {
					knowledgeIds.append("'" + kId + "'");
				}
			}
			originObj.setKnowledgeEndIds(knowledgeIds.toString());
			// 根据IDs形成末节名称
			StringBuilder knowledgeNames = new StringBuilder();
			List<BaseKnowledge> knowledgeList = baseKnowledgeDao.findKnowledgeListByIds(kIds);// knowledgeIds.toString().split("\\,")
			originObj.setKnowledgeEndList(knowledgeList);
			if (knowledgeList != null && knowledgeList.size() > 0) {
				BaseKnowledge knowledge = null;
				for (int k = 0; k < knowledgeList.size(); k++) {
					knowledge = knowledgeList.get(k);
					if (k != (knowledgeList.size() - 1)) {
						knowledgeNames.append(knowledge.getKnowledgeName() + ",");
					} else {
						knowledgeNames.append(knowledge.getKnowledgeName());
					}
				}
			}
			originObj.setKnowledgeEndNames(knowledgeNames.toString());
		}
		return originObj;
	}
	

	/**
	 * 更新审核状态
	 * 
	 * @param map
	 * @return
	 */
	public QueQuestion updateAuditStatusByKey(String queQuestionId, String auditStatus) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("queQuestionId", queQuestionId);
			map.put("auditStatus", auditStatus);
			queQuestionMapper.updateAuditStatusByKey(map);
			QueQuestion question = queQuestionMapper.selectByPrimaryKey(queQuestionId); 
			combineKnowledges(question);
			return question;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * 查询我的习题列表
	 * 
	 * @param page
	 * @return
	 */
	public Page getOwnQuePageList(Page page) {
		List<QuestionListResult> data = queQuestionMapper.getOwnQuePageList(page);
		if (data.size() > 0) {
			for (QuestionListResult dto : data) {
				combineKnowledges(dto);
			}
		}
		page.setData(data);
		return page;
	}
	
	/**
	 * 组装知识点
	 * @param question
	 * @return
	 */
	public QueQuestion combineKnowledges(QueQuestion question){
		List<String> endKonwledgeNames = queQuestionRKnowledgeMapper.getEndKnowledgeNames(question.getQueQuestionId());
		String str = "";
		if (endKonwledgeNames.size() > 0) {
			for (int i = 0; i < endKonwledgeNames.size(); i++) {
				if (i != endKonwledgeNames.size() - 1) {
					str += endKonwledgeNames.get(i) + ",";
				} else {
					str += endKonwledgeNames.get(i);
				}
			}
		}
		question.setEndKonwledgeNames(str);
		return question;
	}

	/**
	 * 删除我的习题
	 * @param queQuestionId
	 * @return
	 */
	public Boolean deleteOwnQuestion(String queQuestionId) {
		if (StringUtils.isBlank(queQuestionId)) {
			return false;
		}
		try {
			QueQuestion queQuestion = queQuestionMapper.selectByPrimaryKey(queQuestionId);
			queQuestionMapper.deleteByPrimaryKey(queQuestionId);
			if(queQuestion!=null){
				if ("MOTHER".equals(queQuestion.getRelationalIndicator())) {// 同时删除对应的子题
					queQuestionMapper.deleteByMotherId(queQuestionId);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 获取当前年10年以内年份
	 * @return
	 */
	public List<String> getNearTenYears(){
		List<String> years = new ArrayList<String>();
		Date date = new Date();
		String year = DateUtils.format(date, "yyyy");
		for(int i=0;i<10;i++){
			years.add(String.valueOf((Long.valueOf(year)-i)));
		}
		return years;
	}
	
	
	
	//***************************************************word导入习题部分开始*****************************************************

	  /**
	   * 批量上传题目
	   * @param list
	   * @param errorList
	   * @return
	   */
	  public Map<String,Object> uploadQuestion(List<QuestionInfo> list, List<ErrorInfo> errorList,String userId,String schoolId, String areaId,String userType,boolean isexam ){
		  Map<String,Object> result = new HashMap<String, Object>();
		  if(!getFlag(errorList)){
			  result.put("flag", getFlag(errorList));
			  result.put("errormeg", errorList);
			  result.put("resultmsg", "导入失败");
			  return result;
		  }else{
			//解析试题
			List<QueQuestion> qs = turntoQuestionFormFields(list,errorList,userId,schoolId,areaId,userType);
		
			//没有错误时保存试题
			  if(getFlag(errorList)){
				  if(saveQuestions(qs)){
					  result.put("flag", true);
					  result.put("errormeg", errorList);
					  result.put("resultmsg", "成功导入"+list.size()+"条题目");
					  //如果是组卷则将试卷id返回
					  if(isexam){
						  result.put("questions", qs);
					  }
				  }else{
					  result.put("flag", false);
					  errorList.get(0).addErrorInfo("保存数据时出错");
					  result.put("errormeg", errorList);
					  result.put("resultmsg", "保存数据时出错");
				  }
			  }else{
				  result.put("flag", false);
				  result.put("errormeg", errorList);
				  result.put("resultmsg", "导入失败");
			  }
			  
			  return result;
		  }
	  }
	  
	  /**
	   * 
	   * @return    将图片上传到服务器上
	   */
	  private String uploadimgs(List<PictureInfo> pics){
		  StringBuffer sb = new StringBuffer("");
		  if(pics == null || pics.size()<1){
			  //如果没有空字符串则返回空
		  }else{
			  for (PictureInfo pic : pics) {
				  InputStream in = ByteToInputStream.byte2Input(pic.getData());
				  String originalFilename = pic.getFileName();
				  
				  
				  //String original = multiFile.getOriginalFilename();
				  originalFilename = (originalFilename == null) ? "" : originalFilename;
					String suffix = "";
					if (originalFilename != null) {
						int x = originalFilename.lastIndexOf('.');
						if (x >= 0) {
							suffix = originalFilename.substring(x);
						}
					}
					try {
						File file = ImageUploadServlet.createFile(suffix);//构建本文件上传位置的路径
						FileOutputStream fop = new FileOutputStream(file.getPath());
						 int bytesRead = 0;
						 byte[] buffer = new byte[1024];
						 int c;
						  while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
							   fop.write(buffer, 0, bytesRead);// 将文件写入服务器
							   }
						      fop.flush();
						      fop.close();
							  in.close();
						//获得上传到服务器端的源文件名
							  String filePath=file.getPath();
							  Integer posiIndex =filePath.lastIndexOf('\\');//获得字符串中最后一个反斜杠的下标
							  String fileName=file.getPath().substring(posiIndex+1);
							  
						
							//HostConfig config = SpringContext.getBean(HostConfig.class);
						String url =SpringContext.getBean(HostConfig.class).getLocal()+ "/images/" + fileName;
						sb.append("<img alt=\"");
						sb.append(originalFilename);
						sb.append("\" style=\"max-width:300px;max-height:200px;\" src=\"");
						sb.append(url);
						sb.append("\" title=\"");
						sb.append(originalFilename);
						sb.append("\"/>");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
				    /*DB db = template.getDb();
					GridFS gfs = new GridFS(db, "images");
					String filename = createFilename(gfs, originalFilename);*/
					/*GridFSInputFile file = gfs.createFile(in, filename);
					file.save();*/
					
//					String src = "<img alt=\"Hydrangeas.jpg\" style=\"max-width:300px;max-height:200px;\" src=\"http://sso.rrt.com/images/eac46624-4636-4205-a326-41b93af1f5df.jpg\" title=\"Hydrangeas.jpg\"/>";
					
			}
		  }
		  
		  
		  
		  return sb.toString();
	  }
	  
	//构建随机的文件名
		private static String createFilename(GridFS gfs, String filename){
			String suffix = "";
			if (StringUtils.isNotBlank(filename)){
				int p = filename.lastIndexOf('.');
				if ( p >= 0 ){
					suffix = filename.substring(p);
				}
			}
			String randFilename = UUID.randomUUID().toString() + suffix;
			GridFSDBFile file = gfs.findOne(randFilename);
			if (file == null){
				logger.debug("generate random image filename:" + randFilename);
				return randFilename;
			} else 
				return createFilename(gfs, filename);
		}
		
		
	  //批量保存    进行习题的导入
	  private boolean saveQuestions(List<QueQuestion> qs){
		 try{
			//进行习题的导入操作
			 List<QueQuestionRChapter> queQuestList= new ArrayList<QueQuestionRChapter>();
			 List<QueQuestionRKnowledge> knowList= new ArrayList<QueQuestionRKnowledge>();
			 
			 createQuestionLibService.createQuestion(qs, queQuestList, knowList,"import");
			 return true;
		 }catch(Exception e){
			 e.printStackTrace();
			 return false;
		 }
	  }
	  /**
	   * 是否有错误
	   * @param errorList
	   * @return
	   */
	  public boolean getFlag(List<ErrorInfo> errorList){
		  boolean flag = true;
			 
		  for (ErrorInfo ei : errorList) {
				if(!ei.isFlag()){
					flag = false;
					break;
				}
			  }
		  return flag;
	  }
	  /**
	   * 将word中的数据转换为表单提交数据，并记录错误信息
	   * @param list
	   * @param errorList（返回值进行修改）
	   * @return
	   */
	  private List<QueQuestion> turntoQuestionFormFields(List<QuestionInfo> list, List<ErrorInfo> errorList,String userId,String schoolId,String areaId,String userType){
		  List<QuestionFormFields> resultls = new ArrayList<QuestionFormFields>();
		  String[] dif = {"容易","较易","一般","较难","困难"};
		  String[] type = {"单选题","多选题","判断题","问答题","计算题"};
		  
		  for (int i = 0 ;i<list.size();i++) {
			  QuestionInfo q  = list.get(i);
			  ErrorInfo e = errorList.get(i);
			  QuestionFormFields f = new QuestionFormFields();
			  
			     String[] scdArr=q.getChapterName().split(";");
			     String scds="";
			     for(int k=0; k<scdArr.length; k++){
			    	 String csdAll="";
			    	 String sd=scdArr[k];
			    	 String[] scd=sd.split(">");
			    	 if(scd==null||scd.length<3){
						  //年级学段学科没写全
						  e.addErrorInfo("请检查第"+(i+1)+"题学段年级学科");
					  }else{
						  List<String> scdids = baseSemesterDao.getscdIds(scd[0], scd[1], scd[2]);
						  if(scdids==null||scdids.size()<3){
							  //年级学段学科没有填写正确
							  e.addErrorInfo("请检查第"+(i+1)+"题学段年级学科");
						  }else{
							  f.setSemesterOpts(scdids.get(0));//小学
							  f.setClasslevelOpts(scdids.get(1));//年级
							  f.setDisciplineOpts(scdids.get(2));//学科
						  }
					  } 
			    	 
					  if(scd==null||scd.length>7){
						  //版本章节信息过多
						  e.addErrorInfo("请检查第"+(i+1)+"题章节");
					  }else if(scd.length>3){
						  //如果有正确的年级学段学科则校验章节部分
						  if(StringUtils.isNotBlank(f.getSemesterOpts())){
							  String version = scd.length>3?scd[3]:"";
							  boolean versionflag = scd.length>3;
							  String one = scd.length>4?scd[4]:"";
							  boolean oneflag = scd.length>4;
							  String two = scd.length>5?scd[5]:"";
							  boolean twoflag = scd.length>5;
							  String three = scd.length>6?scd[6]:"";
							  boolean threeflag = scd.length>6;
							  List<String> chapts = baseSemesterDao.getchapterIds(f.getClasslevelOpts(), f.getDisciplineOpts(), version, one, two, three);
							  if(versionflag&&"0".equals(chapts.get(0))){
								  //版本信息错误
								  e.addErrorInfo("请检查第"+(i+1)+"题版本信息");
							  }
							  if(oneflag&&"0".equals(chapts.get(1))){
								  //分册信息错误
								  e.addErrorInfo("请检查第"+(i+1)+"题分册信息");
							  }
							  if(twoflag&&"0".equals(chapts.get(2))){
								  //章信息错误
								  e.addErrorInfo("请检查第"+(i+1)+"题章信息");
							  }
							  if(threeflag&&"0".equals(chapts.get(3))){
								  //节信息错误
								  e.addErrorInfo("请检查第"+(i+1)+"题节信息");
							  }
							  
							  csdAll=chapts.get(0)+":"+chapts.get(1)+":"+chapts.get(2)+":"+chapts.get(3);
							  if("".equals(scds)){
								  scds +=csdAll;
								}else{
								  scds +=";"+csdAll;
								}
							  
						  }
					  }  
			     }
			  
			     if(null!=scds){
			    	 
			    	 f.setCh_inputs(scds);//將多個章節進行封裝
			     }
 
				  //解析难度
				  for (int j = 0; j < dif.length; j++) {
					if(dif[j].equals(q.getDifficulty())){
						f.setDifficultyOpts(j+1+"");
					}
				  }
				  
				  //解析类型
				  for (int j = 0; j < type.length; j++) {
					if(type[j].equals(q.getQuestionType())){
						f.setQuestionTypeOpts(j+1+"");
					}
				  }
				  
				  //解析知识点
				  //在年级学段学科检测成功的情况下
				  if(StringUtils.isNotBlank(q.getKnowledge())&&StringUtils.isNotBlank(f.getSemesterOpts())){
					  
					  String[] kls = q.getKnowledge().split(";");
					  String kids = "";
					  for (int j = 0 ;j<kls.length;j++) {
						String kidall = "";
						String k = kls[j];
						String[] knames=k.split(">");
						//知识点最多6节
							String k1 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>0?knames[0]:"", "-1");
							String k2 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>1?knames[1]:"", StringUtils.isNotEmpty(k1)?k1:"");
							String k3 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>2?knames[2]:"", StringUtils.isNotEmpty(k2)?k2:"");
							String k4 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>3?knames[3]:"", StringUtils.isNotEmpty(k3)?k3:"");
							String k5 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>4?knames[4]:"", StringUtils.isNotEmpty(k4)?k4:"");
							String k6 = baseSemesterDao.getKnowledgeid(f.getSemesterOpts(), f.getDisciplineOpts(), knames.length>5?knames[5]:"", StringUtils.isNotEmpty(k5)?k5:"");
						kidall = (StringUtils.isNotEmpty(k1)?k1:"0")
								+":"+(StringUtils.isNotEmpty(k2)?k2:"0")
								+":"+(StringUtils.isNotEmpty(k3)?k3:"0")
								+":"+(StringUtils.isNotEmpty(k4)?k4:"0")
								+":"+(StringUtils.isNotEmpty(k5)?k5:"0")
								+":"+(StringUtils.isNotEmpty(k6)?k6:"0");
						
						if("".equals(kids)){
							kids +=kidall;
						}else{
							kids +=";"+kidall;
						}
						
						if(knames.length>0 && StringUtils.isEmpty(k1)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第一节数据");
						}
						if(knames.length>1 && StringUtils.isEmpty(k2)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第二节数据");
						}
						if(knames.length>2 && StringUtils.isEmpty(k3)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第三节数据");
						}
						if(knames.length>3 && StringUtils.isEmpty(k4)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第四节数据");
						}
						if(knames.length>4 && StringUtils.isEmpty(k5)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第五节数据");
						}
						if(knames.length>5 && StringUtils.isEmpty(k6)){
							e.addErrorInfo("请检查第"+(i+1)+"题第"+(j+1)+"个知识点第六节数据");
						}
					}
					 f.setK_inputs(kids);//封装拼接好的知识点信息
				  }
				  
				  
				  
				  f.setEdt_content(q.getQuestionTitle()+uploadimgs(q.getTitlePictureList()));//题干
				  //验证题干的内容大小
				  int contentTxtSize=(q.getQuestionTitle().length())-5;
				  int imgSize=q.getTitlePictureList().size();
				  Integer size=contentTxtSize+imgSize;
				  //System.out.println("size="+size);
				  if(size>1000){
					  e.addErrorInfo("第"+(i+1)+"题的题干内容不得超过1000字符");
				  }
				  f.setEdt_resolve(q.getAnalysis()+uploadimgs(q.getAnalysisPictureList()));//解析
				  
//				  f.setEdt_content(q.getQuestionTitle());//题干
//				  f.setEdt_resolve(q.getAnalysis());//解析
				  f.setFillInAnswerCnt("0");
				  f.setQuestionId("");
				  f.setOptCnt(q.getOptionSize()+"");//选项数
				  f.setRdo_answerType("1");
				  f.setRdo_scoreType("1");
				  f.setRdo_singleOpt(q.getAnswer());//正确答案
				  f.setResolveVideo("");
				//单选题
			  if("单选题".equals(q.getQuestionType())){
				  
			  }
			  //多选题
			  if("多选题".equals(q.getQuestionType())){
				  
				  
			  }
			  //解答题
			  if("解答题".equals(q.getQuestionType())){
				  
				  
			  }
			  resultls.add(f);
		  }
		  
		  return turntoQuestions(resultls,list,userId,schoolId,areaId,userType);//转Question
				  
	  }
	  
	  /**
	   * 将QuestionFormFields 转换为Question
	   * @param qfs
	   * @param qis
	   * @return
	   */
	  private List<QueQuestion> turntoQuestions(List<QuestionFormFields> qfs,List<QuestionInfo> qis,String userId,String schoolId,String areaId,String userType){
		  List<QueQuestion> result = new ArrayList<QueQuestion>();
		  for (int i = 0; i < qfs.size(); i++) {
			  QuestionFormFields qstForm = qfs.get(i);
			  
			//设置所有题型公共属性
			  QueQuestion question =  new QueQuestion();
			    String questionId = UUIDUtils.getUUID();
			    question.setQueQuestionId(questionId);
				question.setBaseUserId(userId);
				question.setClsSchoolId(schoolId);
				question.setAreaId(areaId);
				if("SCHOOL_USR".equals(userType) || "AREA_USR".equals(userType)){//学校或省市县用户 不需审核
					 question.setAuditStatus("PASSED");//默认创建的题为未审核
				}else{//非学校用户创建的习题需要进行审核操作
					question.setAuditStatus("INIT");//初始化状态
				}
				
				question.setBaseSemesterId(qstForm.getSemesterOpts());
				question.setBaseClasslevelId(qstForm.getClasslevelOpts());
				question.setBaseSubjectId(qstForm.getDisciplineOpts());
				if("1".equals(qstForm.getDifficultyOpts())){
					question.setDifficulty(QuestionListCriteria.EASY);
				}else if("2".equals(qstForm.getDifficultyOpts())){
					question.setDifficulty(QuestionListCriteria.LITTLEEASY);
				}else if("3".equals(qstForm.getDifficultyOpts())){
					question.setDifficulty(QuestionListCriteria.NORMAL);
				}else if("4".equals(qstForm.getDifficultyOpts())){
					question.setDifficulty(QuestionListCriteria.LITTLE_DIFFICULT);
				}else if("5".equals(qstForm.getDifficultyOpts())){
					question.setDifficulty(QuestionListCriteria.DIFFICULT);
				}
				
				if("1".equals(qstForm.getQuestionTypeOpts())){//单选题
					question.setType(QuestionListCriteria.SIGNCHOICE);
				}else if("2".equals(qstForm.getQuestionTypeOpts())){
					question.setType(QuestionListCriteria.MULITYCHOICE);
				}else if("3".equals(qstForm.getQuestionTypeOpts())){
					question.setType(QuestionListCriteria.JUDGEMENT);
				}else if("4".equals(qstForm.getQuestionTypeOpts())){
					question.setType(QuestionListCriteria.ASKANSWER);
				}else if("5".equals(qstForm.getQuestionTypeOpts())){
					question.setType(QuestionListCriteria.COMPUTER);
				}
				
				
				question.setContent(filterSpecChar(qstForm.getEdt_content()));
				question.setResolve(filterSpecChar(qstForm.getEdt_resolve()));
				question.setResolveVideo(qstForm.getResolveVideo());
				question.setCreateTime( new Date());
				question.setUpdateTime(new Date());
				
				question.setRelationalIndicator(QuestionListCriteria.MOTHER);//默认导入的都是母题
				question.setUsableType("PRIVATE");//默认私有
				question.setScore(NumberUtils.createInteger(qis.get(i).getScore()));
				//question.setResolveVideo(qstForm.getVideoOrgName());
				qstForm.setQuestionId(questionId);
				
			
/*Notices*/				
				
				//获取相关章节信息
				List<QueQuestionRChapter> rChapterLi = this.obtainChapterList(qstForm);
				question.setrChapters(rChapterLi);
				
				//获取相关知识点信息
				 List<QueQuestionRKnowledge>  rKnowledgeLi = this.obtainKnowledgeList(qstForm);
				question.setrKnowledges(rKnowledgeLi);
				
				
				//获取选项(单选或者多选)
				if(qstForm.getQuestionTypeOpts().equals("1") || qstForm.getQuestionTypeOpts().equals("2") || qstForm.getQuestionTypeOpts().equals("3")){
					question.setOptions(filterSpecChar(obtainOptions(qis.get(i))));
					question.setOptionsTxt(filterSpecChar(obtainOptionsTxt(qis.get(i))));
					//获取单选答案或者多选答案
					question.setAnswer(obtainAnswer(qis.get(i)));
				}
				
				result.add(question);
		  }
		
		  return result;
		  
	  }
	  
	  
	  private String filterSpecChar(String oldChar){
			return (oldChar == null ? "":oldChar.replaceAll("<br>", "").replaceAll("\r\n", " "));
	  }
	  
	  
	  /**
		 * 获取选项的内容（只有选择与多选有）
		 * 
		 * @return
		 */
		private String obtainOptions(QuestionInfo qis) {
			return qis.getOption()
			/*.replace("A.", "")
			.replace("B.", "")
			.replace("C.", "")
			.replace("D.", "")
			.replace("E.", "")
			.replace("F.", "")
			.replace("G.", "")
			.replace("H.", "")*/
			.replace(";",QuestionListCriteria.OPT_SEPERATE)
			.replace("；",QuestionListCriteria.OPT_SEPERATE);
//			// 二个选项内容
//			char c = 'A';
//			StringBuilder sb = new StringBuilder();
//			String prefix = "textarea_opt";
//			int optCnt = Integer.parseInt(qstForm.getOptCnt());
//			for (int i = 0; i < optCnt; i++) {
//				String option = request.getParameter(prefix + c);
//				if(i != (optCnt -1)){
//					sb.append(option).append(QuestionListCriteria.OPT_SEPERATE);
//				}else {
//					sb.append(option);
//				}	
//				c++;
//			}
//	       return sb.toString();
		}
		
		/**
		 * 获取选项的内容（只有选择与多选有）
		 * 
		 * @return
		 */
		private String obtainOptionsTxt(QuestionInfo qis) {
//			// 二个选项内容
//			char c = 'A';
//			StringBuilder sb = new StringBuilder();
//			String prefix = "textarea_txt_opt";
//			int optCnt = Integer.parseInt(qstForm.getOptCnt());
//			for (int i = 0; i < optCnt; i++) {
//				String option = request.getParameter(prefix + c);
//				if(i != (optCnt -1)){
//					sb.append(option).append(QuestionListCriteria.OPT_SEPERATE);
//				}else {
//					sb.append(option);
//				}	
//				c++;
//			}
//	       return sb.toString();
			return qis.getOption()
					/*.replace("A.", "")
					.replace("B.", "")
					.replace("C.", "")
					.replace("D.", "")
					.replace("E.", "")
					.replace("F.", "")
					.replace("G.", "")
					.replace("H.", "")*/
					.replace(";",QuestionListCriteria.OPT_SEPERATE)
					.replace("；",QuestionListCriteria.OPT_SEPERATE);
		}
		
		/**
		 * 返回单选题或者多选题的正确答案
		 * @param request
		 * @return
		 */
	   private String obtainAnswer(QuestionInfo qis){
		   return qis.getAnswer().replace(";","")
					.replace(";","");
	   }
	   
	   private List<QueQuestionRChapter> obtainChapterList(QuestionFormFields qstForm) {
			String rChapterStr = qstForm.getCh_inputs();
			if (StringUtils.isBlank(rChapterStr)) {
				return null;
			}
			List<QueQuestionRChapter> li = new ArrayList<QueQuestionRChapter>();
			QueQuestionRChapter rChapterObj = null;
			String[] rChapterArr = rChapterStr.split(";");
			for(int i = 0; i< rChapterArr.length; i++){
				rChapterObj = new QueQuestionRChapter();
				rChapterObj.setQueQuestionId(qstForm.getQuestionId());
				rChapterObj.setQueQuestionRChapterId(UUIDUtils.getUUID());
				String[] rChapterIds = rChapterArr[i].split(":");
				rChapterObj.setBaseVersionId(rChapterIds[0]);
				rChapterObj.setBaseVolumeId(rChapterIds[1]);
				rChapterObj.setBaseChapterId(rChapterIds[2]);
				rChapterObj.setBaseSectionId(rChapterIds[3]);				
				li.add(rChapterObj);
			}
			return li;
	}
	   
		private List<QueQuestionRKnowledge> obtainKnowledgeList(QuestionFormFields qstForm) {
			String rKnowledgeStr = qstForm.getK_inputs(); //request.getParameter("kn_input");
			if (StringUtils.isBlank(rKnowledgeStr)) {
				return null;
			}
			List<QueQuestionRKnowledge> li = new ArrayList<QueQuestionRKnowledge>();
			QueQuestionRKnowledge rKnowledgeObj = null;
			String[] rKnowledgeArr = rKnowledgeStr.split(";");
			for(int i = 0; i< rKnowledgeArr.length; i++){
				rKnowledgeObj = new QueQuestionRKnowledge();
				rKnowledgeObj.setQueQuestionId(qstForm.getQuestionId());
				rKnowledgeObj.setQueQuestionRKnowledgeId(UUIDUtils.getUUID());
				String[] rKnowledgeIds = rKnowledgeArr[i].split(":");
				String baseKnowledgeId = rKnowledgeIds[0];
				String baseKnowledge1Id = rKnowledgeIds[1];
				String baseKnowledge2Id = rKnowledgeIds[2];
				String baseKnowledge3Id = rKnowledgeIds[3];
				String baseKnowledge4Id = rKnowledgeIds[4];
				String baseKnowledge5Id = rKnowledgeIds[5];				
				rKnowledgeObj.setBaseKnowledgeId(baseKnowledgeId);
				rKnowledgeObj.setBaseSubKnowledge1Id(baseKnowledge1Id);
				rKnowledgeObj.setBaseSubKnowledge2Id(baseKnowledge2Id);
				rKnowledgeObj.setBaseSubKnowledge3Id(baseKnowledge3Id);
				rKnowledgeObj.setBaseSubKnowledge4Id(baseKnowledge4Id);
				rKnowledgeObj.setBaseSubKnowledge5Id(baseKnowledge5Id);
				String endKnowledgeId = baseKnowledgeId;
				if(!StringUtils.isBlank(baseKnowledge5Id) && !baseKnowledge5Id.equals("0")){
					endKnowledgeId = baseKnowledge5Id;
				} else if(!StringUtils.isBlank(baseKnowledge4Id) && !baseKnowledge4Id.equals("0")){
					endKnowledgeId = baseKnowledge4Id;
				} else if(!StringUtils.isBlank(baseKnowledge3Id) && !baseKnowledge3Id.equals("0")){
					endKnowledgeId = baseKnowledge3Id;
				} else if(!StringUtils.isBlank(baseKnowledge2Id) && !baseKnowledge2Id.equals("0")){
					endKnowledgeId = baseKnowledge2Id;
				} else if(!StringUtils.isBlank(baseKnowledge1Id) && !baseKnowledge1Id.equals("0")){
					endKnowledgeId = baseKnowledge1Id;
				}
				rKnowledgeObj.setBaseEndKnowledgeId(endKnowledgeId);
				li.add(rKnowledgeObj);
			}
			return li;
	}
	  //***************************************************word导入习题部分结束*****************************************************
	
		/**
		 * @author lichen
		* @Title: countMotherSon
		* @Description: TODO(获得当前编辑的母题的个数)
		* @param @param questionId
		* @param @return    设定文件
		* @return Integer    返回类型
		* @throws
		 */
		public Integer countMotherSon(String questionId){
			
			return queQuestionMapper.countMotherSon(questionId);
		}
		
		/**
		 * @author LICHEN
		* @Title: updateMaotherTime
		* @Description: TODO(编辑子题的时候更新母题的时间)
		* @param @param updateTime
		* @param @return    设定文件
		* @return int    返回类型
		* @throws
		 */
		public int updateMotherTime(UpDateMothTimeView updateTime){
			
			return queQuestionMapper.updateMotherTime(updateTime);
		}
}
