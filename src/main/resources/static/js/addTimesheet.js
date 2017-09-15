$(document).ready(function () {
    //To activate Tooltip for this page
    $('[data-toggle="tooltip"]').tooltip();
    getTimesheetDetails();
    //On successful submit display the Modal
    if ($('#summarymodalDisplayFlag').val() === 'yes') {
        $('#successModal').modal('show');
    }
});

/**
 *  Disaply or Hide the Auditing information for Submission, Approval and Rejection based on the Timesheet status.
 */
function showHideAuditInfoBasedOnStatus() {
    console.log('Timesheet Status: ' + $('#timesheetStatus').html());
    //Check Timesheet Status and change the Display configurations
    if (!$('#timesheetStatus').html()) {
        $('#submissionInfoDiv').hide();
        $('#approvalInfoDiv').hide();
        $('#uploadedTimesheet').hide();
    } else if ($('#timesheetStatus').html() === 'SUBMITTED') {
        $('#submissionInfoDiv').show();
        $('#uploadedTimesheet').show();
        $('#approvalInfoDiv').hide();
        enableComponents();
    } else if ($('#timesheetStatus').html() === 'APPROVED') {
        $('#submissionInfoDiv').show();
        $('#uploadedTimesheet').show();
        $('#approvalInfoDiv').show();
        disableComponents();
    } else if ($('#timesheetStatus').html() === 'REJECTED') {
        $('#submissionInfoDiv').show();
        $('#uploadedTimesheet').show();
        $('#approvalInfoDiv').hide();
        enableComponents();
    } else {
        console.log("Error Status");
    }
}

/**
 * Get the Timesheet Information and populate the fields based on the information.
 */
function getTimesheetDetails() {
    var endDate = $('#selectTimePeriod').val();
    console.log("Selected End Date: " + endDate);
    $.ajax({
        type: 'GET',
        url: '/timesheetApp/api/getTimesheetByEndDate?endDate=' + endDate,
        success: function (responsedata) {
            console.log("Inside success function after getting the timesheet info.");
            populateTimesheetInfo(responsedata);
            showHideAuditInfoBasedOnStatus();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            console.log(jqXHR);
        }
    });
}

/**
 * Populate the Timesheet information into the fields.
 * @param timesheetObj
 */
function populateTimesheetInfo(timesheetObj) {

    $('#regularHoursText').val(timesheetObj.regularHours);
    $('#extraHoursText').val(timesheetObj.extraHours);
    $('#dscCommentsText').val(timesheetObj.dscComments);

    $('#timesheetStatus').html(timesheetObj.timesheetStatus !== null ? timesheetObj.timesheetStatus : '');
    $('#submitterName').html(timesheetObj.nameCreated !== null ? timesheetObj.nameCreated : '');
    $('#submittedDate').html(timesheetObj.dateCreated !== null ? timesheetObj.dateCreated : '');
    $('#approverName').html(timesheetObj.nameApproved !== null ? timesheetObj.nameApproved : '');
    $('#approvalDate').html(timesheetObj.dateApproved !== null ? timesheetObj.dateApproved : '');
    //Timesheet Upload Information
    $('#uploadedTimesheetName').html(timesheetObj.dscFileName !== null ? timesheetObj.dscFileName : '');
    $('#uploadTimesheetLink').attr('href', timesheetObj.timesheetId !== null ? ('/timesheetApp/api/getUploadedTimesheet?timesheetId=' + timesheetObj.timesheetId) : '#');
}

/**
 * Disable form fields and other form components.
 */
function disableComponents() {
    $('#addTimesheetForm').find('input, textarea, datepicker').attr('readonly', 'readonly');
    $('#addTimesheetForm').find('input, textarea, checkbox, datepicker').attr('disabled', 'disabled');
}

/**
 * Enable form fields and other form components by removing the readonly and disabled attributes.
 */
function enableComponents() {
    $('#addTimesheetForm').find('input, textarea, datepicker').removeAttr('readonly');
    $('#addTimesheetForm').find('input, textarea, checkbox, datepicker').removeAttr('disabled');
}