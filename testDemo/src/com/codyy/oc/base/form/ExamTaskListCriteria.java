package com.codyy.oc.base.form;

import org.apache.commons.lang.StringUtils;


public class ExamTaskListCriteria {

	public enum ExamTaskEnum {
		self("SELF", "自测组卷"),assign("ASSIGN","老师布置");

		private ExamTaskEnum(String value, String desc) {
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

		public static ExamTaskEnum getDes(String value) {
			if(StringUtils.isNotBlank(value)) {
				for(ExamTaskEnum p : ExamTaskEnum.values()) {
					if(p.getValue().equals(value)){
						return p;
					}
				}
			}
			return null;
		}
	}
	
}
