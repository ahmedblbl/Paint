package eg.egu.alexu.csd.oop.draw.cs07_cs59.gui;

import java.io.InputStream;

public class resource {
	public static InputStream lo (String path) {
		InputStream inputStream = resource.class.getResourceAsStream(path);
		if (inputStream == null) {
			inputStream = resource.class.getResourceAsStream("/" + path);
		}
		System.out.println(inputStream.toString());
		return inputStream;
	}
}
