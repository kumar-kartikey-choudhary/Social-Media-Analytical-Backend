package com.socialanalyzer.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Platform {

    TWITTER("twitter"),
    FACEBOOK("facebook"),
    INSTAGRAM("instagram"),
    LINKEDIN("linkedin"),
    YOUTUBE("youtube"),
    UNKNOWN("unknown");



    private final String value;

    Platform(String value)
    {
        this.value = value;
    }

    @JsonValue
    public String getValue(String value)
    {
        return value;
    }

    @JsonCreator
    public static Platform fromValues(String value)
    {
        for(Platform platform : Platform.values())
        {
            if(platform.value.equalsIgnoreCase(value))
            {
                return platform;
            }
        }
        return UNKNOWN;
    }

}
