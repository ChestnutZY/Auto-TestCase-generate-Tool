package entity;

/**
 * 
* @ClassName: Type  
* @Description: TODO(用于保存类型数据)  
* @author Binver131  
* @date 2020年5月14日
 */
public class Type {
	private int typeID;			//类型ID号
	private String typename;	//类型名称
	private String typerange;	//类型名
	private int modelid;
	private String sizeString;
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	public String getSizeString() {
		return sizeString;
	}
	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTyperange() {
		return typerange;
	}
	public void setTyperange(String typerange) {
		this.typerange = typerange;
	}
	@Override
	public String toString() {
		return "Type [typeID=" + typeID + ", typename=" + typename + ", typerange=" + typerange + "]";
	}
}
