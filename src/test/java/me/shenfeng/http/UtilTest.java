package me.shenfeng.http;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	private File f1;
	private File f2;

	@Before
	public void setUp() {
		f1 = new File("/path/to/what.css");
		f2 = new File("/path/to/what");
	}

	@Test
	public void testGetExtension() {
		assertEquals("css", Util.getExtension(f1));
		assertEquals("", Util.getExtension(f2));
	}

	@Test
	public void testGetContentType() {
		assertEquals("text/css", Util.getContentType(f1));
		assertEquals("application/octet-stream", Util.getContentType(f2));
	}

	@Test
	public void testSubArray() {
		byte[] s = { 72, 69, 65, 68 };
		byte[] f = { 71, 69, 84, 32 };

		byte[] data1 = { 71, 69, 84, 72, 69, 65, 68 };
		assertEquals(Util.subArray(data1, s, 0), 3);
		assertEquals(Util.subArray(data1, f, 0), data1.length);

		assertEquals(Util.subArrayFromEnd(data1, s, 0), 3);
		assertEquals(Util.subArrayFromEnd(data1, f, 0), data1.length);
	}
}
