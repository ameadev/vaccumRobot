package environment;

import java.util.ArrayList;

public class Element {
	protected ArrayList<Integer> content = new ArrayList<Integer>();
	public void addContent(int newcontent)
	{
		this.content.add(newcontent);
	}
	public int getSize()
	{
		return this.content.size();
	}
	public ArrayList<Integer> getContent()
	{
		return this.content;
	}
}
