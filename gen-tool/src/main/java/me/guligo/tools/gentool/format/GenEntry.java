package me.guligo.tools.gentool.format;

import java.util.List;

/**
 * Represents single entry in .gen file.
 * 
 * @author guligo
 */
class GenEntry {

	private List<String> paths;
	private String content;

	public GenEntry(List<String> paths, String content) {
		this.paths = paths;
		this.content = content;
	}

	public List<String> getPaths() {
		return paths;
	}

	public String getContent() {
		return content;
	}

}
