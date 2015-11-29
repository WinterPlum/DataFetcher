package edu.utexas.datafetcher;

import java.io.File;
import java.util.List;

import edu.utexas.datafetcher.model.Project;
import edu.utexas.datafetcher.model.Summary;
import edu.utexas.datafetcher.utils.JsonUtil;

public class DataFetcher {
	public static void main(String[] args) {
		String path = "data/0.json";
		Summary summary = JsonUtil.readJson(path);
		System.out.println(summary.getTotal() + " projects in " + path);
		List<Project> projects = summary.getResults();
		Project proj = projects.get(1);
		System.out.println(proj.getPath());
		System.out.println(new File(proj.getPath()).getParentFile().getName());
		System.out.println(path + " done.");
	}
}
