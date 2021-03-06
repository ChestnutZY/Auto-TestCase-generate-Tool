package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.DataBase;
import entity.Model;
import entity.Requirement;
import entity.TestCase;
import entity.Type;
import entity.Variables;
 
public class ConnectHelper {
	public static List<Type> typeList = new ArrayList<>();
	public static List<Requirement> requirementList = new ArrayList<>();
	public static List<TestCase> testcaseList = new ArrayList<>();
	public static List<Variables> variableList = new ArrayList<>();
	
    public static DataBase connectHelper() {
    	DataBase database = new DataBase();
        // 声明Connection对象
        Connection con;
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名 test
        String url = "jdbc:mysql://localhost:3306/atg?useSSL=false&allowPublicKeyRetrieval=true";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "123456789";
        // 遍历查询结果集
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("成功以 " + user + " 身份连接到数据库");
 
            // 2.ResultSet类，用来存放获取的结果集！！
            Statement statement = con.createStatement();
            String getModelTable = "select * from models";
            ResultSet rs = statement.executeQuery(getModelTable);
            
            
            String sql1 = "select * from typetable";
            Statement st1=con.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            while(rs1.next()) {
            	Type type = new Type();
            	type.setModelid(rs1.getInt("model"));
            	type.setSizeString(rs1.getString("size"));
            	type.setTypeID(rs1.getInt("type_id"));
            	type.setTypename(rs1.getString("type_name"));
            	type.setTyperange(rs1.getString("type_range"));
            	typeList.add(type);
            }

            //创建DataBase类
            while (rs.next()) {
            	Model model = new Model(rs.getString("model_id"));
            	model.setDbId(rs.getString("No"));
            	model.setName(rs.getString("model_name"));
            	model.setText(rs.getString("model_text"));
            	model.setModelClass(rs.getString("Model_Class"));
            	System.out.println(model.getName());
            	
            	//提取需求类
            	String sql2 = "select * from requirementtable where model="+rs.getString("No");
            	Statement requireStatement = con.createStatement();
            	ResultSet requirementResult = requireStatement.executeQuery(sql2);
            	 while (requirementResult.next()) {
                     Requirement requirement = new Requirement();
                     requirement.setDbId(requirementResult.getString("No"));
                     requirement.setParent(model);
                     requirement.setRequirementId(requirementResult.getString("requirement_id"));
                     requirement.setRequirementName(requirementResult.getString("requirement_name"));
                     requirement.setRequirementText(requirementResult.getString("requirement_text"));
                     String[] preConditionVars = requirementResult.getString("requirement_condition").split(",");
                     String[] inputVars = requirementResult.getString("requirement_input").split(",");
                     String[] outputVars = requirementResult.getString("requirement_output").split(",");
                     for (String ID : preConditionVars) {
						
                    	 String preConStr = "select * from variablestable where variables_id="+ID;
                    	 Statement preConVarsStatement = con.createStatement();
                    	 ResultSet result = preConVarsStatement.executeQuery(preConStr);
                    	 Variables var = new Variables();
                    	 while (result.next()) {
                    		 
                    		 var.setVariablesID(Integer.parseInt(ID, 10));
                    		 var.setVariablesName(result.getString("variables_name"));
                    		 var.setVariablesTypeID(result.getInt("variables_type"));
                    	 }
                    	 
                    	 
                    	 requirement.addPreConVar(var);
                    	 preConVarsStatement.close();
					}
                     
                     for (String ID : inputVars) {
 						
                    	 String inputStr = "select * from variablestable where variables_id="+ID;
                    	 Statement inputStatement = con.createStatement();
                    	 ResultSet result = inputStatement.executeQuery(inputStr);
                    	 Variables var = new Variables();
                    	 while (result.next()) {
                    		 
                    		 var.setVariablesID(Integer.parseInt(ID, 10));
                    		 var.setVariablesName(result.getString("variables_name"));
                    		 var.setVariablesTypeID(result.getInt("variables_type"));
                    	 }
                    	 
                    	 requirement.addInputVar(var);
                    	 inputStatement.close();
                    	 
					}
                     
                     for (String ID : outputVars) {
 						
                    	 String outputStr = "select * from variablestable where variables_id="+ID;
                    	 Statement outputStatement = con.createStatement();
                    	 ResultSet result = outputStatement.executeQuery(outputStr);
                    	 Variables var = new Variables();
                    	 while (result.next()) {
                    		 
                    		 var.setVariablesID(Integer.parseInt(ID, 10));
                    		 var.setVariablesName(result.getString("variables_name"));
                    		 var.setVariablesTypeID(result.getInt("variables_type"));
                    	 }
                    	 requirement.addOutputVar(var);
                    	 outputStatement.close();
					}
                     
                    String testcaseStr = "select * from testcasetable where requirementid="+requirement.getDbId();
                 	Statement testCaseStatement = con.createStatement();
                 	ResultSet testCaseResult = testCaseStatement.executeQuery(testcaseStr);
                    
                 	while(testCaseResult.next()) {
                 		TestCase testcase = new TestCase();
                 		testcase.setTestcaseID(testCaseResult.getInt("testcase_id"));
                 		testcase.setTestcaseInput(testCaseResult.getString("testcase_input"));
                 		testcase.setTestcaseOutput(testCaseResult.getString("testcase_output"));
                 		testcase.setTestcaseCondition(testCaseResult.getString("testcase_condition"));
                 		testcase.setTestcaseType(testCaseResult.getString("testcase_type"));
                 		testcase.setTestcaseEvaluate(testCaseResult.getString("testcase_evaluate"));
 
                 		requirement.addTestcases(testcase);
                 		
                 	}
                 	
                     
                     //TODO: 其他信息
                     
                     model.addChild(requirement);
                 }
            	requireStatement.close();
            	
                database.addChild(model);
            }
            System.out.println(typeList);
            
            
            
            rs.close();
            con.close();
        }  
        catch (ClassNotFoundException e) {
            // 数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally {
            System.out.println("获取数据库数据完毕！");
        }
		return database;
    }
}