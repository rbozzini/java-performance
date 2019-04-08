package it.corsojava.concorrenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class GetSitePageExecutor implements Callable<String> {

	private String url;

	public GetSitePageExecutor(String url) {
		super();
		this.url = url;
	}

	@Override
	public String call() throws Exception {
		try {
			URL u = new URL(url);

			URLConnection connection = u.openConnection();

			InputStream is = connection.getInputStream();

			return readFileContent(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String readFileContent(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		br = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

}
