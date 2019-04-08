package it.corsojava.concorrenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetSitePage extends Thread {

	private String url;
	private String content;

	public GetSitePage(String url) {
		super();
		this.url = url;
	}

	@Override
	public void run() {
		try {
			URL u = new URL(url);

			URLConnection connection = u.openConnection();

			InputStream is = connection.getInputStream();

			setContent(readFileContent(is));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
