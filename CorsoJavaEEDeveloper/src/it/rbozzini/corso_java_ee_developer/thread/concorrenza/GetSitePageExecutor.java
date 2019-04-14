package it.rbozzini.corso_java_ee_developer.thread.concorrenza;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import it.rbozzini.corso_java_ee_developer.utils.FileUtils;

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

			return FileUtils.readFileContent(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
