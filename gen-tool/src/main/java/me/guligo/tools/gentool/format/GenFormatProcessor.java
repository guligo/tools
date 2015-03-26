package me.guligo.tools.gentool.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import me.guligo.tools.gentool.utils.Utils;

/**
 * Responsible for conversion of .gen format into internal data structure.
 * 
 * @author guligo
 */
class GenFormatProcessor {

	public final static String SPLITTER_ENTRY = "@@@" + System.lineSeparator();
	public final static String SPLITTER_INNER = "%%%" + System.lineSeparator();

	public List<GenEntry> getEntries(String genContent) throws IOException {
		List<GenEntry> genEntries = new ArrayList<GenEntry>();
		for (String genEntryAsString : genContent.split(SPLITTER_ENTRY)) {
			genEntries.add(getGenEntry(genEntryAsString));
		}
		return genEntries;
	}

	private GenEntry getGenEntry(String genEntryAsString) throws IOException {
		String[] genEntrySections = genEntryAsString.split(SPLITTER_INNER);
		return new GenEntry(getPaths(genEntrySections[0]), getContent(genEntrySections[1]));
	}

	private List<String> getPaths(String genEntryPathsSection) throws IOException {
		List<String> genEntryPaths = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new StringReader(genEntryPathsSection))) {
			String line;
			while ((line = reader.readLine()) != null && !line.equals(SPLITTER_INNER)) {
				genEntryPaths.add(line);
			}
		}
		return genEntryPaths;
	}

	private String getContent(String genEntryContentSection) throws IOException {
		StringBuilder genEntryContent = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new StringReader(genEntryContentSection))) {
			String line;
			while ((line = reader.readLine()) != null) {
				genEntryContent.append(line).append(System.lineSeparator());
			}
		}
		return Utils.getInstance().removeLastLine(genEntryContent.toString());
	}

}
