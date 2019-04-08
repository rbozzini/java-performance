package it.rbozzini.corso_java_ee_developer.concorrenza;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import it.rbozzini.corsojvaeedeveloper.utils.FileUtils;

public class GetSitePageThreads extends Thread {

	private String url;
	private String content;

	public GetSitePageThreads(String url) {
		super();
		this.url = url;
	}

	@Override
	public void run() {
		try {
			URL u = new URL(url);

			URLConnection connection = u.openConnection();

			InputStream is = connection.getInputStream();

			setContent(FileUtils.readFileContent(is));

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

}
