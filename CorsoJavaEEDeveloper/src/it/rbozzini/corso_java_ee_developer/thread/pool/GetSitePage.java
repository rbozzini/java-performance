package it.rbozzini.corso_java_ee_developer.thread.pool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import it.rbozzini.corso_java_ee_developer.utils.FileUtils;

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

			System.out.println("***********************************************");
			System.out.println("CONTENUTO DELL APAGINA WEB: " + url);
			setContent(FileUtils.readFileContent(is));
			System.out.println(content);
			System.out.println("***********************************************");

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
