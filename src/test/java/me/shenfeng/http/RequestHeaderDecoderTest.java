package me.shenfeng.http;

import me.shenfeng.http.RequestHeaderDecoder.Verb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestHeaderDecoderTest {

	private static String HOST = "www.google.com";
	private static String ACCEPT = "image/png,image/*;q=0.8,*/*;q=0.5";
	private static String END = "\r\n\r\n";
	private String GET = "GET /\r\n" + "Host: " + HOST + "\r\n" + "Accept: "
			+ ACCEPT + END;

	private RequestHeaderDecoder decoder;

	@Before
	public void setup() {
		decoder = new RequestHeaderDecoder();
	}

	@Test
	public void testDecodeGet() {
		byte[] bytes = GET.getBytes();
		boolean b = decoder.appendSegment(bytes);
		Assert.assertEquals(b, true);
		Assert.assertEquals("/", decoder.getResouce());
		Assert.assertEquals(HOST, decoder.getHeader("Host"));
		Assert.assertEquals(ACCEPT, decoder.getHeader("Accept"));
		Assert.assertEquals(Verb.GET, decoder.getVerb());
	}

}
