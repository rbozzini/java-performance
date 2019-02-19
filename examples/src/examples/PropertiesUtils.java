package examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

	Properties props = new Properties();

	public PropertiesUtils() throws IOException {
		InputStream in = new FileInputStream("resources/props.properties");
		props.load(in);
	}

	public Map<String, String> getProps(String section) {
		Map<String, String> sectionProps = new HashMap<String, String>();

		props.forEach((key, val) -> {
			if (key.toString().startsWith(section)) {
				sectionProps.put(key.toString().substring(section.length() + 1), val.toString());
			}
		});

		return sectionProps;
	}

	public static void main(String[] args) {
		PropertiesUtils test = null;
		try {
			test = new PropertiesUtils();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> section1Props = test.getProps("pgp1");
		System.out.println(section1Props);
	}

}
