package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: Requirement  
* @Description: TODO(存储需求数据信息)  
* @author Binver131  
* @date 2020年5月12日
 */
public class Requirement {
	private String dbId;				//数据库中的主键
	private String requirementId;		//需求标识符
	private String requirementName;		//需求名称
	private String requirementText;		//需求描述文本
	private Model model;				//需求所属的需求模型
	private List<Variables> preConditionVars;	//前置条件变量列表
	private List<Variables> inputVars;			//输入变量列表
	private List<Variables> outputVars;			//输出变量列表
	private List<TestCase> testcases;			//测试用例列表
	public Requirement() {
		preConditionVars = new ArrayList<>();
		inputVars = new ArrayList<>();
		outputVars = new ArrayList<>();
		testcases = new ArrayList<>();
	}
	
	public void addPreConVar(Variables var) {
		preConditionVars.add(var);
	}
	
	public void addInputVar(Variables var) {
		inputVars.add(var);
	}
	
	public void addOutputVar(Variables var) {
		outputVars.add(var);
	}
	
	public void addTestcases(TestCase var) {
		testcases.add(var);
	}
	
	public TestCase[] getTestcases() {
		return (TestCase[]) testcases.toArray(new TestCase[testcases.size()]);
	}
	
	public Variables[] getOutputVars() {
		return (Variables[]) outputVars.toArray(new Variables[outputVars.size()]);
	}
	public Variables[] getInputVars() {
		return (Variables[]) inputVars.toArray(new Variables[outputVars.size()]);
	}
	public Variables[] getPreConVars() {
		return (Variables[]) preConditionVars.toArray(new Variables[outputVars.size()]);
	}
	
	
	public Boolean hasVars(int type) {
		Boolean result = false;
		switch (type) {
		case 1:
			result = preConditionVars.isEmpty();
			break;
		case 2:
			result = inputVars.isEmpty();
			break;
		case 3:
			result = outputVars.isEmpty();
			break;
		default:
			break;
		}
		
		return result;
	}
	
	public String getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}
	public String getRequirementName() {
		return requirementName;
	}
	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}
	public String getRequirementText() {
		return requirementText;
	}
	public void setRequirementText(String requirementText) {
		this.requirementText = requirementText;
	}
	
	/**
	 * 树形结构时需要回调此函数来生成标签文本
	 */
	@Override
	public String toString() {
		return requirementName;
	}
	
	/**
	 * 树型结构中使用的回调函数，设置父节点（需求模型）
	 * @param model 所属的需求模型
	 */
	public void setParent(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
	}
	
	/**  
	* @Title: getParent  
	* @Description: TODO(获取父节点)  
	* @param @return    参数  
	* @return Object    返回类型  
	* @throws  
	*/  
	public Model getParent() {
		// TODO Auto-generated method stub
		return model;
	}
	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}
	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
}
