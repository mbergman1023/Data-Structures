package xmlvalidator;

import java.util.*;
import java.util.regex.*;

import org.apache.commons.lang3.*;

public class BasicXmlValidator implements XmlValidator {
	List<String> results = new ArrayList<String>();
	Pattern p1 = Pattern.compile("<[^<>]+>");

	BasicXmlTagStack tagName = new BasicXmlTagStack();


	@Override
	public List<String> validate(String xmlDocument) {
		Matcher m1 = p1.matcher(xmlDocument);
		while (m1.find()) {
			String match = m1.group();
			if ((match.contains("!") || match.contains("?")))
				continue;

			var tag = match.split(" ");
			var thisTag = tag[0].substring(1).replace(">", "");

			if (!thisTag.startsWith("/")) {
				String sub = xmlDocument.substring(0, m1.start());
				int lineNumber = StringUtils.countMatches(sub, "\n") + 1;
				tagName.push(new XmlTag(thisTag, lineNumber));

			} else {
				String close = thisTag.substring(1);
				if (tagName.getCount() == 0) {
					results.add("Orphan closing tag");
					results.add(close);
					String sub = xmlDocument.substring(0, m1.start());
					int lineNumber = StringUtils.countMatches(sub, "\n") + 1;
					results.add(Integer.toString(lineNumber));
					return results;
				}
				XmlTag peek = tagName.peek(0);

				if (close.equals(peek.name)) {
					tagName.pop();
				} else {
					results.add("Tag mismatch");
					results.add(peek.name);
					results.add(Integer.toString(peek.index));
					results.add(close);
					String sub = xmlDocument.substring(0, m1.start());
					int lineNumber = StringUtils.countMatches(sub, "\n") + 1;
					results.add(Integer.toString(lineNumber));
					return results;
				}
			}
		}
		if (tagName.getCount() != 0) {
			results.add("Unclosed tag at end");
			results.add(tagName.peek(0).name);
			results.add(Integer.toString(tagName.peek(0).index));
			return results;
		}
		// TODO Auto-generated method stub
		return null;
	}

}
