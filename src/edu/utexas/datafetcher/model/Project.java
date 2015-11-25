package edu.utexas.datafetcher.model;

import java.util.List;

public class Project {
	protected String name;
	protected List<String> language;
	protected String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLanguage() {
		return language;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
