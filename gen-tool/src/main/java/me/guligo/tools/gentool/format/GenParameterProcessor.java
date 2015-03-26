package me.guligo.tools.gentool.format;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for processing parameters in content of .gen format.
 * 
 * @author guligo
 */
class GenParameterProcessor {

	private final static String PLACEHOLDER = "\\$\\{%s\\}";

	private Map<String, String> parameters;

	public GenParameterProcessor() {
		this.parameters = new HashMap<String, String>();
	}

	public String replaceParameters(String content) {
		for (String key : parameters.keySet()) {
			content = replaceParameter(content, key, parameters.get(key));
		}
		return content;
	}

	private String replaceParameter(String content, String key, String value) {
		return content.replaceAll(String.format(PLACEHOLDER, key), value);
	}

	public void setParameter(String key, String value) {
		parameters.put(key, value);
	}

}
