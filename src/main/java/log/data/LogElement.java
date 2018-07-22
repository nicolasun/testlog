package log.data;

public class LogElement {
    private String id,type,host;
    private int duration;
    private boolean alert;
    
    public LogElement(String id, int duration, String type, String host, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }
    public LogElement() {}

    @Override
    public String toString() {
        return String.format(
                "LogElement[id=%s, duration='%d', type='%s', host='%s', alert='%b']",
                id, duration, type, host, alert);
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
    
}
