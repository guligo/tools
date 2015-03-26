package me.guligo.tools.gentool;


import java.io.IOException;

public class MainTwo {

	private final static int LEVELS = 4;
	private final static int MODULES_PER_LEVEL = 4;

	public static void main(String[] args) throws IOException {
		// Files.createDirectory(ROOT);
		doMagic(LEVELS, MODULES_PER_LEVEL);
	}

	public static void doMagic(int level, int modulesPerLevel) {
		doMagic(level, "dummy", "", modulesPerLevel);
	}
	
	public static void doMagic(int level, String path, String name, int modulesPerLevel) {
		if (level > 0) {
			System.out.println(path);
			for (int i = 0 + 'A'; i < modulesPerLevel + 'A'; i++) {
				String newName = name + (char) i;
				doMagic(level - 1, path + "/" + newName, newName, modulesPerLevel);
			}
		}
	}

}
