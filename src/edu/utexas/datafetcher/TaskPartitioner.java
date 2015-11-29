package edu.utexas.datafetcher;

import java.util.ArrayList;
import java.util.List;

import edu.utexas.datafetcher.model.Project;
import edu.utexas.datafetcher.model.Summary;
import edu.utexas.datafetcher.utils.JsonUtil;

public class TaskPartitioner {
	public static final String RAW_JSON = "data/muse20151125.json";
	public static final String JSON = "data/musePartition.json";
	public static final String PATH = "data";
	public static final int PART_NUMBER = 100;

	public static void main(String[] args) {
		Summary summary = JsonUtil.readJson(RAW_JSON);
		System.out.println(summary.getTotal() + " projects in total.");
		List<Summary> partitions = partition(summary, PART_NUMBER);
		JsonUtil.writeJsonList(PATH, partitions);
		System.out.println("Partition done.");
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

}
