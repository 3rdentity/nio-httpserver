package com.butterfly.nioserver;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.butterfly.nioserver.util.Util;

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
		Assert.assertEquals("css", Util.getExtension(f1));
		Assert.assertEquals("", Util.getExtension(f2));
	}

	@Test
	public void testGetContentType() {
		Assert.assertEquals("text/css", Util.getContentType(f1));
		Assert.assertEquals("application/octet-stream", Util.getContentType(f2));

	}
}
