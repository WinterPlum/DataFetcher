package edu.utexas.datafetcher;

import java.io.File;

import com.google.gson.Gson;

import edu.utexas.datafetcher.model.Summary;
import edu.utexas.datafetcher.utils.FileUtil;

public class TaskPartitioner {
	public static final String RAW_JSON = "data/muse20151125.json";
//	public static final String RAW_JSON = "data/museTest.json";
	public static final String JSON = "data/musePartition.json";

	public static void main(String[] args) {
		Summary summary = readJson(RAW_JSON);
		System.out.println(summary.getTotal());
	}

	public static Summary readJson(String path) {
		String json = FileUtil.readFileToString(new File(path));
		Gson gson = new Gson();
		Summary summary = gson.fromJson(json, Summary.class);
		return summary;
	}
}
