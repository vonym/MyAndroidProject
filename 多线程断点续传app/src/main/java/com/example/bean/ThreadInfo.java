package com.example.bean;

public class ThreadInfo {

	//哪一个任务中的编号为多少线程
	private int thread_Id;
	private String url;
	
	//记录该条线程的下载区间（从多少下到多少）
	private int start;
	private int end;
	
	//该条线程的暂停位置
	private int finished;
	
	public ThreadInfo() {}

	public ThreadInfo(int thread_Id, String url, int start, int end,
			int finished) {
		this.thread_Id = thread_Id;
		this.url = url;
		this.start = start;
		this.end = end;
		this.finished = finished;
	}

	public int getThread_Id() {
		return thread_Id;
	}

	public void setThread_Id(int thread_Id) {
		this.thread_Id = thread_Id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	@Override
	public String toString() {
		return "ThreadInfo [thread_Id=" + thread_Id + ", url=" + url
				+ ", start=" + start + ", end=" + end + ", finished="
				+ finished + "]";
	}
	
	
}
