import java.util.Date;

public class TimeStat {
    private String login;
    private double taskId;
    private double microtasks;
    private Date assignedTime;
    private Date closedTime;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getTaskId() {
        return taskId;
    }

    public void setTaskId(double taskId) {
        this.taskId = taskId;
    }

    public double getMicrotasks() {
        return microtasks;
    }

    public void setMicrotasks(double microtasks) {
        this.microtasks = microtasks;
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Date getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Date closedTime) {
        this.closedTime = closedTime;
    }

    @Override
    public String toString() {
        return "TimeStat{" +
                "login='" + login + '\'' +
                ", taskId=" + taskId +
                ", microtasks=" + microtasks +
                ", assignedTime=" + assignedTime +
                ", closedTime=" + closedTime +
                '}';
    }
}
