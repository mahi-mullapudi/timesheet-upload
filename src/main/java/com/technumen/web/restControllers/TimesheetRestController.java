package com.technumen.web.restControllers;

import com.technumen.constants.TimesheetConstants;
import com.technumen.models.Timesheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;

import org.apache.commons.io.*;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/api/common")
@Slf4j
public class TimesheetRestController {

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
            Blob doc = uploadedDocument.getBlob_message();
            bbn = doc.getBytes(1, (int) doc.length());
            String extn = FilenameUtils.getExtension(uploadedDocument.getDsc_file_name());
            String mimeType = TimesheetConstants.TIMESHEET_FILE_EXTENSION_MAP.get(extn);
            if (StringUtils.isEmpty(mimeType) || StringUtils.isBlank(mimeType)) {
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;    //Unknown file type - defaulting to stream
            }
            log.debug("Mime type detected is : " + mimeType + " for the file extn: " + extn);
            header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(mimeType));
            header.set("Content-Disposition", "inline; filename = " + uploadedDocument.getDsc_file_name());
            header.setContentLength(doc.length());
        } catch (Exception ex) {
            log.error("Exception while retrieving document " + ex);
        }
        if (bbn == null || bbn.length == 0) {
            return new ResponseEntity(bbn, header, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(bbn, header, HttpStatus.OK);
    }

}
