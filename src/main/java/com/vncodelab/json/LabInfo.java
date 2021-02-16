package com.vncodelab.json;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LabInfo {

	@SerializedName("summary")
	private String summary;

	@SerializedName("prefix")
	private String prefix;

	@SerializedName("format")
	private String format;

	@SerializedName("source")
	private String source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("tags")
	private List<Object> tags;

	@SerializedName("duration")
	private int duration;

	@SerializedName("environment")
	private String environment;

	@SerializedName("theme")
	private String theme;

	@SerializedName("mainga")
	private String mainga;

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private Object category;

	@SerializedName("updated")
	private String updated;

	@SerializedName("status")
	private Object status;

	public String getSummary(){
		return summary;
	}

	public String getPrefix(){
		return prefix;
	}

	public String getFormat(){
		return format;
	}

	public String getSource(){
		return source;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public List<Object> getTags(){
		return tags;
	}

	public int getDuration(){
		return duration;
	}

	public String getEnvironment(){
		return environment;
	}

	public String getTheme(){
		return theme;
	}

	public String getMainga(){
		return mainga;
	}

	public String getId(){
		return id;
	}

	public Object getCategory(){
		return category;
	}

	public String getUpdated(){
		return updated;
	}

	public Object getStatus(){
		return status;
	}
}