/**  
* @Title: Model.java  
* @Package entity  
* @Description: TODO(��һ�仰�������ļ���ʲô)  
* @author Binver131  
* @date 2020��5��12��  
* @version V1.0  
*/  
package entity;


import java.util.ArrayList;
import java.util.List;


/**  
 * @ClassName: Model  
 * @Description: TODO(��������ģ��������Ϣ)  
 * @author Binver131  
 * @date 2020��5��12��    
 */
public class Model {
	private DataBase parent;
	private String dbId;				//���ݿ��е�����
	private String ID;
	private String name;
	private String modelClass;			//����ģ�͵ȼ�
	private String text;
	private List<Requirement> children;//�����
	public Model(String ID) {
		this.ID = ID;
		children = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addChild(Requirement child) {
		children.add(child);
		child.setParent(this);
	}
	public void removeChild(Requirement child) {
		children.remove(child);
		child.setParent(null);
	}
	public Requirement[] getChildren() {
		return (Requirement[]) children.toArray(new Requirement[children.size()]);
	}
	public boolean hasChildren() {
		return children.size()>0;
	}
	public void setParent(DataBase workSpace) {
		this.parent = workSpace;
	}
	public DataBase getParent() {
		return parent;
	}
	public String toString() {
		return getName();
	}
	/**
	 * @return the modelClass
	 */
	public String getModelClass() {
		return modelClass;
	}
	/**
	 * @param modelClass the modelClass to set
	 */
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
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
