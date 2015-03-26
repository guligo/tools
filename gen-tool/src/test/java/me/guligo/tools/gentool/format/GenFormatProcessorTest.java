package me.guligo.tools.gentool.format;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import me.guligo.tools.gentool.format.GenEntry;
import me.guligo.tools.gentool.format.GenFormatProcessor;

/**
 * Test case class for {@link GenFormatProcessor}.
 * 
 * @author guligo
 */
public class GenFormatProcessorTest {

	@Test
	public void testGenFormatProcessor() throws IOException {
		GenFormatProcessor genProcessor = new GenFormatProcessor();
		List<GenEntry> genEntries = genProcessor.getEntries(IOUtils.toString(GenFormatProcessorTest.class.getResourceAsStream("/test-gen-format-processor.gen")));

		Assert.assertNotNull(genEntries);
		Assert.assertEquals(2, genEntries.size());
		Assert.assertEquals(1, genEntries.get(0).getPaths().size());
		Assert.assertEquals("Foobar", genEntries.get(0).getPaths().get(0));
		Assert.assertEquals("Barfoo", genEntries.get(0).getContent());
		Assert.assertEquals(3, genEntries.get(1).getPaths().size());
		Assert.assertEquals("Foo", genEntries.get(1).getPaths().get(0));
		Assert.assertEquals("Few", genEntries.get(1).getPaths().get(1));
		Assert.assertEquals("Fwo", genEntries.get(1).getPaths().get(2));
		Assert.assertEquals("Bar" + System.lineSeparator() + System.lineSeparator() + "Baz" + System.lineSeparator() + "Buz", genEntries.get(1).getContent());
	}

}
