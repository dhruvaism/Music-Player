package com.codespeedy.projectMP.Model;

public class ModelFeedback {
    String deviceName;
    String feedbackMessage;

    public ModelFeedback(String deviceName, String feedbackMessage) {
        this.deviceName = deviceName;
        this.feedbackMessage = feedbackMessage;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }
}
