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
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.util.ArrayList;
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
     * Get the file from the database FNS_ECAS_CONTRACT_DOCUMENT table and then
     * covert it to a stream and display it to the user.
     *
     * @param rowId
     * @return
     */
    @GetMapping("/getContractDocument/{rowId}")
    public ResponseEntity getContractDocument(@PathVariable("rowId") String rowId) {
        byte[] bbn = null;
        HttpHeaders header = null;
        try {
            Timesheet uploadedDocument = null;//ecasCommonService.getEcasDocumentByRowId(rowId);
            Blob doc = uploadedDocument.getBlobMessage();
            bbn = doc.getBytes(1, (int) doc.length());
            String extn = FilenameUtils.getExtension(uploadedDocument.getDscFileName());
            String mimeType = TimesheetConstants.TIMESHEET_FILE_EXTENSION_MAP.get(extn);
            if (StringUtils.isEmpty(mimeType) || StringUtils.isBlank(mimeType)) {
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;    //Unknown file type - defaulting to stream
            }
            log.debug("Mime type detected is : " + mimeType + " for the file extn: " + extn);
            header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(mimeType));
            header.set("Content-Disposition", "inline; filename = " + uploadedDocument.getDscFileName());
            header.setContentLength(doc.length());
        } catch (Exception ex) {
            log.error("Exception while retrieving document " + ex);
        }
        if (bbn == null || bbn.length == 0) {
            return new ResponseEntity(bbn, header, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(bbn, header, HttpStatus.OK);
    }

    @GetMapping("/timesheetSummary")
    public ResponseEntity<List<Timesheet>> getTimesheetSummary(@RequestParam("employeeId") long employeeId) {
        log.info("Inside getTimesheetSummary method of TimesheetRestController:: employeeId: " + employeeId);
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        return new ResponseEntity(new ArrayList(employee.getTimesheetRecords()), new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping(value = "/getTimesheet", produces = "application/json")
    public ResponseEntity<Timesheet> getTimesheet(@RequestParam("timesheetId") long timesheetId) {
        log.info("Inside getTimesheetSummary method of TimesheetRestController:: timesheetId: " + timesheetId);
        Timesheet timesheet = timesheetService.getTimesheetByTimesheetId(timesheetId);
        return new ResponseEntity(timesheet, new HttpHeaders(), HttpStatus.OK);
    }

}
