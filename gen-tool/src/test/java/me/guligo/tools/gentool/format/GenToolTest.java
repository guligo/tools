package me.guligo.tools.gentool.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case class for {@link GenTool}.
 * 
 * @author guligo
 */
public class GenToolTest {

	private Path tmpDirectory;

	@Before
	public void setUp() throws IOException {
		tmpDirectory = Files.createTempDirectory("test-gen-tool-");
	}

	@After
	public void tearDown() {
		try {
			FileUtils.deleteDirectory(tmpDirectory.toFile());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Test
	public void testGenTool1() throws IOException {
		FileUtils.writeStringToFile(tmpDirectory.resolve("test-gen-tool.gen").toFile(),
				IOUtils.toString(GenToolTest.class.getResourceAsStream("/test-gen-tool.gen")));

		GenTool genTool = new GenTool(tmpDirectory.resolve("test-gen-tool.gen"), tmpDirectory);
		genTool.setParameter("tag", "Foo");
		genTool.process();

		Assert.assertTrue(Files.exists(tmpDirectory.resolve("src/main/java/me/guligo/beans/impl/FooBean.java")));
		Assert.assertTrue(Files.exists(tmpDirectory.resolve("src/main/java/me/guligo/tests/beans/impl/FooBeanTest.java")));
		Assert.assertTrue(Files.exists(tmpDirectory.resolve("pom.xml")));
	}

	@Test
	public void testGenTool2() throws IOException {
		GenTool genTool = new GenTool(GenToolTest.class.getResourceAsStream("/test-gen-tool.gen"), tmpDirectory);
		genTool.setParameter("tag", "Foo");
		genTool.process();

		Assert.assertTrue(Files.exists(tmpDirectory.resolve("src/main/java/me/guligo/beans/impl/FooBean.java")));
		Assert.assertTrue(Files.exists(tmpDirectory.resolve("src/main/java/me/guligo/tests/beans/impl/FooBeanTest.java")));
		Assert.assertTrue(Files.exists(tmpDirectory.resolve("pom.xml")));
	}

}
