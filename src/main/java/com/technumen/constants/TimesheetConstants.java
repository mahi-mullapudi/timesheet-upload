package com.technumen.constants;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class TimesheetConstants implements Serializable {

    public static final String REGISTRATION_STATUS_ACTIVE = "ACTIVE";

    public static final String REGISTRATION_STATUS_INACTIVE = "INACTIVE";

    public static final String TIMESHEET_STATUS_APPROVED = "APPROVED";

    public static final String TIMESHEET_STATUS_SUBMITTED = "SUBMITTED";

    public static final String TIMESHEET_STATUS_REJECTED = "REJECTED";

    public static final String TIMESHEET_TYPE_WEEKLY = "WEEKLY";

    public static final String TIMESHEET_TYPE_BIWEEKLY = "BIWEEKLY";

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
