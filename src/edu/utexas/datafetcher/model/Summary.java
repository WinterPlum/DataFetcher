package edu.utexas.datafetcher.model;

import java.util.List;

public class Summary {
	protected int total;
	protected List<Project> results;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Project> getResults() {
		return results;
	}

	public void setResults(List<Project> results) {
		this.results = results;
	}
}
