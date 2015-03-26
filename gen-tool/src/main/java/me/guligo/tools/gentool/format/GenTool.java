package me.guligo.tools.gentool.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/**
 * Responsible for processing file of .gen format.
 * 
 * @author guligo
 */
public class GenTool {

	private Path getFilePath;
	private Path contentRoot;
	private GenParameterProcessor genParamProcessor;
	private GenFormatProcessor genFormatProcessor;

	public GenTool(String genFilePath, String contentRoot) {
		this.getFilePath = Paths.get(genFilePath);
		this.contentRoot = Paths.get(contentRoot);
		this.genParamProcessor = new GenParameterProcessor();
		this.genFormatProcessor = new GenFormatProcessor();
	}

	public void process() throws IOException {
		String genFileContent = new String(FileUtils.readFileToString(getFilePath.toFile()));
		String genContent = genParamProcessor.replaceParameters(genFileContent);
		for (GenEntry genEntry : genFormatProcessor.getEntries(genContent)) {
			for (String genEntryPath : genEntry.getPaths()) {
				createContent(contentRoot.resolve(genEntryPath), genEntry.getContent());
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
