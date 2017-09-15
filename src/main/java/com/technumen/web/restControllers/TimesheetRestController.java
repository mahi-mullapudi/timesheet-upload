package com.technumen.web.restControllers;

import com.technumen.constants.TimesheetConstants;
import com.technumen.models.Employee;
import com.technumen.models.Timesheet;
import com.technumen.services.EmployeeService;
import com.technumen.services.TimesheetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class TimesheetRestController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    TimesheetService timesheetService;

    /**
     * Get the file from the database TIMESHEET table and then
     * covert it to a stream and display it to the user.
     *
     * @param timesheetId
     * @return
     */
    @GetMapping("/getUploadedTimesheet")
    public ResponseEntity getContractDocument(@RequestParam("timesheetId") long timesheetId) {
        log.info("Inside getContractDocument method of TimesheetRestController:: timesheetId: " + timesheetId);
        byte[] doc = null;
        HttpHeaders header = null;
        try {
            Timesheet timesheetObj = timesheetService.getTimesheetByTimesheetId(timesheetId);
            log.info("After getting the timesheetObj");
            if (timesheetObj != null) {
                doc = timesheetObj.getBlobContent();
                String extn = FilenameUtils.getExtension(timesheetObj.getDscFileName());
                log.info("File Extension: " + extn);
                String mimeType = TimesheetConstants.TIMESHEET_FILE_EXTENSION_MAP.get(extn);
                if (StringUtils.isEmpty(mimeType) || StringUtils.isBlank(mimeType)) {
                    mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;    //Unknown file type - defaulting to stream
                }
                log.info("Mime type detected is : " + mimeType + " for the file extn: " + extn);
                header = new HttpHeaders();
                header.setContentType(MediaType.valueOf(mimeType));
                header.set("Content-Disposition", "inline; filename = " + timesheetObj.getDscFileName());
                header.setContentLength(doc.length);
            } else {
                log.error("No Timesheet found for the given timesheetId.");
                return new ResponseEntity(doc, header, HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            log.error("Exception while retrieving document " + ex);
        }
        if (doc == null || doc.length == 0) {
            return new ResponseEntity(doc, header, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(doc, header, HttpStatus.OK);
    }

    @GetMapping("/timesheetSummary")
    public ResponseEntity<List<Timesheet>> getTimesheetSummary(@RequestParam("employeeId") long employeeId) {
        log.info("Inside getTimesheetSummary method of TimesheetRestController:: employeeId: " + employeeId);
        try {
            Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
            return new ResponseEntity(new ArrayList(employee.getTimesheetRecords()), new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception while getting Timesheet Summary: " + ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getTimesheetById")
    public ResponseEntity<Timesheet> getTimesheetByTimesheetId(@RequestParam("timesheetId") long timesheetId) throws Exception {
        log.info("Inside getTimesheetByTimesheetId method of TimesheetRestController:: timesheetId: " + timesheetId);
        try {
            Timesheet timesheet = timesheetService.getTimesheetByTimesheetId(timesheetId);
            return new ResponseEntity(timesheet, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception while getting Timesheet By TimesheetId: " + ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getTimesheetByEndDate")
    public ResponseEntity<Timesheet> getTimesheetByEndDate(@RequestParam("endDate") Date endDate) throws Exception {
        log.info("Inside getTimesheetByEndDate method of TimesheetRestController:: endDate: " + endDate);
        try {
            Timesheet timesheet = timesheetService.getTimesheetByEndDate(endDate);
            return new ResponseEntity(timesheet, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception while getting Timesheet By EndDate: " + ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
