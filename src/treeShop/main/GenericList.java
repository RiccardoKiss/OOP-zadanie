package treeShop.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Genericka trieda na vytvaranie zoznamov zamestnancov a zakaznikov
 * @author Riccardo Kiss
 *
 */
public class GenericList<T> {
	private List<T> list = new ArrayList<T>();
	
	public T getObject(T t) {
		return t;
	}
	
	public void add(T t) {
		System.out.println(getObject(t));
		list.add(t);
	}
	
	public List<T> getList() {
		return list;
	}
}
