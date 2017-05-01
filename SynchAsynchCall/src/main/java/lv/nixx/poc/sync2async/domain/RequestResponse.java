package lv.nixx.poc.sync2async.domain;

public class RequestResponse {
	public int id;
	public String request;
	public String response;

	public RequestResponse(int id, String request) {
		this.id = id;
		this.request = request;
	}

	@Override
	public String toString() {
		return "RequestResponse [id=" + id + ", request=" + request + ", response=" + response + "]";
	}

}