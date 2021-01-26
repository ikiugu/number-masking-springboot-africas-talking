package com.ikiugu.numbermasking.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "destinationNumber",
        "isActive",
        "callStartTime",
        "callSessionState",
        "callerNumber",
        "sessionId",
        "callerCarrierName",
        "direction",
        "callerCountryCode"
})

public class VoiceResponse {

    @JsonProperty("destinationNumber")
    private String destinationNumber;
    @JsonProperty("isActive")
    private String isActive;
    @JsonProperty("callStartTime")
    private String callStartTime;
    @JsonProperty("callSessionState")
    private String callSessionState;
    @JsonProperty("callerNumber")
    private String callerNumber;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("callerCarrierName")
    private String callerCarrierName;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("callerCountryCode")
    private String callerCountryCode;
    @JsonProperty("dtmfDigits")
    private String dtmfDigits;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("destinationNumber")
    public String getDestinationNumber() {
        return destinationNumber;
    }

    @JsonProperty("destinationNumber")
    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    @JsonProperty("isActive")
    public String getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("callStartTime")
    public String getCallStartTime() {
        return callStartTime;
    }

    @JsonProperty("callStartTime")
    public void setCallStartTime(String callStartTime) {
        this.callStartTime = callStartTime;
    }

    @JsonProperty("callSessionState")
    public String getCallSessionState() {
        return callSessionState;
    }

    @JsonProperty("callSessionState")
    public void setCallSessionState(String callSessionState) {
        this.callSessionState = callSessionState;
    }

    @JsonProperty("callerNumber")
    public String getCallerNumber() {
        return callerNumber;
    }

    @JsonProperty("callerNumber")
    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    @JsonProperty("sessionId")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("callerCarrierName")
    public String getCallerCarrierName() {
        return callerCarrierName;
    }

    @JsonProperty("callerCarrierName")
    public void setCallerCarrierName(String callerCarrierName) {
        this.callerCarrierName = callerCarrierName;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("callerCountryCode")
    public String getCallerCountryCode() {
        return callerCountryCode;
    }

    @JsonProperty("callerCountryCode")
    public void setCallerCountryCode(String callerCountryCode) {
        this.callerCountryCode = callerCountryCode;
    }

    @JsonProperty("dtmfDigits")
    public String getDtmfDigits() {
        return dtmfDigits;
    }

    @JsonProperty("dtmfDigits")
    public void setDtmfDigits(String dtmfDigits) {
        this.dtmfDigits = dtmfDigits;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}