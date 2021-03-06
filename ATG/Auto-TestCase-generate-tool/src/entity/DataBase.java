package entity;
/**
 * 整个的数据库内容。
 * @author 汪文轩
 *
 */
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	private List<Model> models;
	public DataBase() {
		models = new ArrayList<>();
	}
	public void addChild(Model child) {
		models.add(child);
		child.setParent(this);
	}
	public void removeChild(Model child) {
		models.remove(child);
		child.setParent(null);
	}
	public Model[] getChildren() {
		return (Model[]) models.toArray(new Model[models.size()]);
	}
	public boolean hasChildren() {
		return models.size()>0;
	}
}

