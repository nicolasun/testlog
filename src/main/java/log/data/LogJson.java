package log.data;

public class LogJson {
	private String id;
	private String state;
	private String type;
	private String host;
	private long timestamp;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public LogJson() {}
	
	public LogJson(String id, String state, Integer timestamp, String type, String host) {
		this.id = id;
		this.state = state;
		this.timestamp = timestamp;
		this.type = type;
		this.host = host;
	}
	
    @Override
    public String toString() {
        return String.format(
                "LogJson[id=%s, state='%s', timestamp='%d', type='%s', host='%s']",
                id, state, timestamp, type, host);
    }
}
