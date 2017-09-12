package com.technumen.constants;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class TimesheetConstants implements Serializable {

    //Enumeration which maintain the status of timesheets.
    public enum Status {
        SAVED,
        SUBMITTED,
        APPROVED,
        REJECTED
    }

    public static final String SALT = "technumen";

    // All anticipated document types.
    public static final Map<String, String> TIMESHEET_FILE_EXTENSION_MAP = ImmutableMap.<String, String>builder()
            //Images
            .put("bmp", "image/bmp")
            .put("jpg", "image/jpeg")
            .put("jpeg", "image/jpeg")
            .put("png", "image/png")
            //pdf
            .put("pdf", "application/pdf")
            .build();

    public static final String INIT_VECTOR_RANDOM_KEY = "RandomInitVector"; // 16 bytes IV any random String with 16 characters can be used.

}
