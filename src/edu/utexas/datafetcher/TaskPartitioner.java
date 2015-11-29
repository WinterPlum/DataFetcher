package edu.utexas.datafetcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import edu.utexas.datafetcher.model.Project;
import edu.utexas.datafetcher.model.Summary;
import edu.utexas.datafetcher.utils.FileUtil;

public class TaskPartitioner {
	public static final String RAW_JSON = "data/muse20151125.json";
	public static final String JSON = "data/musePartition.json";
	public static final String PATH = "data";
	public static final int PART_NUMBER = 100;

	public static void main(String[] args) {
		Summary summary = readJson(RAW_JSON);
		System.out.println(summary.getTotal() + " projects in total.");
		List<Summary> partitions = partition(summary, PART_NUMBER);
		writeJsonList(partitions);
		System.out.println("Partition done.");
	}

	public static Summary readJson(String path) {
		String json = FileUtil.readFileToString(new File(path));
		Gson gson = new Gson();
		Summary summary = gson.fromJson(json, Summary.class);
		return summary;
	}

	public static List<Summary> partition(Summary summary, int num) {
		List<Summary> results = new ArrayList<Summary>();
		List<Project> projects = new ArrayList<Project>(summary.getResults());
		int total = summary.getTotal();
		int div = total / num;
		int mod = total % num;
		int k = 0;
		for (int i = 0; i < mod; ++i) {
			Summary s = new Summary();
			List<Project> ps = new ArrayList<Project>();
			for (int j = 0; j <= div; ++j) {
				ps.add(projects.get(k++));
			}
			s.setId(i);
			s.setTotal(div+1);
			s.setResults(ps);
			results.add(s);
		}
		for (int i = mod; i < num; ++i) {
			Summary s = new Summary();
			List<Project> ps = new ArrayList<Project>();
			for (int j = 0; j < div; ++j) {
				ps.add(projects.get(k++));
			}
			s.setId(i);
			s.setTotal(div);
			s.setResults(ps);
			results.add(s);
		}
		return results;
	}

	public static void writeJson(String path, Summary summary) {
		Gson gson = new Gson();
		String json = gson.toJson(summary);
		FileUtil.writeStringToFile(path, json);
	}

	public static void writeJsonList(List<Summary> summaries) {
		for (Summary summary : summaries) {
			String path = PATH + File.separator + summary.getId() + ".json";
			writeJson(path, summary);
		}
	}
}
