package edu.utexas.datafetcher.utils;

import java.io.File;
import java.util.List;

import com.google.gson.Gson;

import edu.utexas.datafetcher.model.Summary;

public class JsonUtil {

	public static Summary readJson(String path) {
		String json = FileUtil.readFileToString(new File(path));
		Gson gson = new Gson();
		Summary summary = gson.fromJson(json, Summary.class);
		return summary;
	}

	public static void writeJson(String path, Summary summary) {
		Gson gson = new Gson();
		String json = gson.toJson(summary);
		FileUtil.writeStringToFile(path, json);
	}

	public static void writeJsonList(String dir, List<Summary> summaries) {
		for (Summary summary : summaries) {
			String path = dir + File.separator + summary.getId() + ".json";
			writeJson(path, summary);
		}
	}
}
