package me.guligo.tools.gentool;

import java.io.IOException;

import me.guligo.tools.gentool.format.GenTool;

/**
 * Main runnable class of the tool.
 * 
 * @author guligo
 */
public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length < 2 || args.length % 2 != 0) {
			System.out.println("Usage:");
			System.out.println("java -jar gen-tool.jar <gen file path> <content dir path> <key 1> <value 1> <key 2> <value 2> ...");
			System.exit(1);
		}

		GenTool genFileProcessor = new GenTool(args[0], args[1]);
		for (int i = 2; i < args.length; i++) {
			genFileProcessor.setParameter(args[i++], args[i]);
		}
		genFileProcessor.process();
	}

}
