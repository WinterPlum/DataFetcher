package edu.utexas.datafetcher;

import java.io.File;
import java.util.List;

import edu.utexas.datafetcher.model.Project;
import edu.utexas.datafetcher.model.Summary;
import edu.utexas.datafetcher.utils.JsonUtil;

public class DataFetcher {
	public static final String CMD = "./maketar.sh";
	public static void main(String[] args) throws Exception {
		String chunk = args[0];
		String jsonPath = "data/" + chunk + ".json";
		Summary summary = JsonUtil.readJson(jsonPath);
		System.out.println(summary.getTotal() + " projects in " + jsonPath);
		List<Project> projects = summary.getResults();
		for (Project project : projects) {
			String dataPath = new File(project.getPath()).getParent();
			System.out.println("Working on " + dataPath);
			Process proc = Runtime.getRuntime().exec(CMD + " " + dataPath + " " + chunk);
			proc.waitFor();
			proc.destroy();
		}
		System.out.println(jsonPath + " done.");
	}
}
