package it.rbozzini.corso_java_ee_developer.concorrenza;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.RecursiveTask;

import it.rbozzini.corsojvaeedeveloper.utils.FileUtils;

public class GetSitePageForkJoin extends RecursiveTask<String> {

	private String url;

	public GetSitePageForkJoin(String url) {
		super();
		this.url = url;
	}

	@Override
	protected String compute() {
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
