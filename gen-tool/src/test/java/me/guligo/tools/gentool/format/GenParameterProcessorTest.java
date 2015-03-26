package me.guligo.tools.gentool.format;

import me.guligo.tools.gentool.format.GenParameterProcessor;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test case class for {@link GenParameterProcessor}.
 * 
 * @author guligo
 */
public class GenParameterProcessorTest {

	@Test
	public void testGenParameterProcessor() {
		GenParameterProcessor genParamProcessor = new GenParameterProcessor();
		genParamProcessor.setParameter("a", "foo");
		genParamProcessor.setParameter("ab", "bar");
		genParamProcessor.setParameter("abc", "baz");
		String genContent = genParamProcessor.replaceParameters("${a} is a cool ${ab}\n\rhowever ${abc} is not");

		Assert.assertNotNull(genContent);
		Assert.assertEquals("foo is a cool bar\n\rhowever baz is not", genContent);
	}

}
