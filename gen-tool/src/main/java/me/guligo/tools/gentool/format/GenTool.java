package me.guligo.tools.gentool.format;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * Responsible for processing file of .gen format.
 * 
 * @author guligo
 */
public class GenTool {

	private String genContent;
	private GenParameterProcessor genParamProcessor;
	private GenFormatProcessor genFormatProcessor;
	private Path resultRoot;

	public GenTool(Path genFile, Path resultRoot) throws IOException {
		this(FileUtils.readFileToString(genFile.toFile()), resultRoot);
	}

	public GenTool(InputStream genStream, Path resultRoot) throws IOException {
		this(IOUtils.toString(genStream), resultRoot);
	}

	public GenTool(String genContent, Path resultRoot) {
		this.genContent = genContent;
		this.genParamProcessor = new GenParameterProcessor();
		this.genFormatProcessor = new GenFormatProcessor();
		this.resultRoot = resultRoot;
	}

	public void process() throws IOException {
		String genContent = genParamProcessor.replaceParameters(this.genContent);
		for (GenEntry genEntry : genFormatProcessor.getEntries(genContent)) {
			for (String genEntryPath : genEntry.getPaths()) {
				createContent(resultRoot.resolve(genEntryPath), genEntry.getContent());
			}
		}
	}

	public void createContent(Path contentRoot, String genEntryContent) throws IOException {
		if (contentRoot != null) {
			Files.createDirectories(contentRoot.getParent());
		}
		Files.write(contentRoot, genEntryContent.getBytes());
	}

	public void setParameter(String key, String value) {
		genParamProcessor.setParameter(key, value);
	}

}
