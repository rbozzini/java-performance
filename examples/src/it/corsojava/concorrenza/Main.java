package it.corsojava.concorrenza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

	private void esempioConcorrenzaThread() throws InterruptedException {

		GetSitePage sp1 = new GetSitePage("http://www.paolopreite.it");
		GetSitePage sp2 = new GetSitePage("http://www.google.it");

		sp1.start();
		sp2.start();

		sp1.join();
		sp2.join();

		System.out.println(sp1.getContent());

		System.out.println(sp2.getContent());
	}

	private void esempioConcorrenzaExecutor() throws InterruptedException, ExecutionException {
		List<Callable<String>> list = new ArrayList<Callable<String>>();
		list.add(new GetSitePageExecutor("http://www.paolopreite.it"));
		list.add(new GetSitePageExecutor("http://www.google.it"));

		ExecutorService service = Executors.newSingleThreadExecutor();
		List<Future<String>> ret = service.invokeAll(list);

		for (Future<String> future : ret) {
			System.out.println(future.get());
		}

		service.shutdown();
	}

	private void esempioConcorrenzaForkJoin() {
		ForkJoinPool fjp = new ForkJoinPool();

		System.out.println(fjp.invoke(new GetSitePageForkJoin("http://www.paolopreite.it")));
		System.out.println(fjp.invoke(new GetSitePageForkJoin("http://www.google.it")));

		fjp.shutdown();
	}

	public static void main(String[] args) {

		Main m = new Main();

		System.out.println("\n\n--- sempioConcorrenzaThread ---\n\n");

		try {
			m.esempioConcorrenzaThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n--- sempioConcorrenzaThread ---\n\n");

		try {
			m.esempioConcorrenzaExecutor();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("\n\n--- sempioConcorrenzaThread ---\n\n");

		m.esempioConcorrenzaForkJoin();
	}
}
