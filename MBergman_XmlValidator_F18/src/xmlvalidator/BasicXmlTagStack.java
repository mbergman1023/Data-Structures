package xmlvalidator;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.util.*;

import org.apache.commons.lang3.*;

public class BasicXmlTagStack implements XmlTagStack {
	private int count;
	private List<XmlTag> items = new ArrayList<XmlTag>();


	@Override
	public void push(XmlTag item) {
		items.add(item);
		count++;
	}


	@Override
	public XmlTag pop() {
		if (count > 0) {
			var x = items.remove(count - 1);
			count--;
			return x;
		} else
			return null;
	}


	@Override
	public XmlTag peek(int position) {

		return items.get(items.size() - (position + 1));
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
