$(document).ready(function () {
    var table = $('#timesheetSummary').DataTable({
        ajax: {
            url: '/timesheetApp/api/timesheetSummary?employeeId=' + $('#employeeId').val(),
            dataSrc: ''
        },
        columns: [
            {
                data: 'timesheetId'
            },
            {
                data: 'fromDate'
            },
            {
                data: 'toDate'
            },
            {
                data: 'regularHours'
            },
            {
                data: 'extraHours'
            },
            {
                data: 'timesheetStatus'
            },
            {
                data: 'timesheetId',
                mRender: function (data, type, row) {
                    var link = '';
                    link = '<button class="btn-link" onclick="getTimesheet(' + data + ')">View Timesheet</button>';
                    console.log("Link: " + link);
                    return link;
                }
            }
        ]
    })
});

/*This function will get the Timesheet JSON object to populate the fields */
function getTimesheet(timesheetId) {
    console.log("Inside getTimesheet method:: timesheetId: " + timesheetId);
    $.ajax({
        type: 'GET',
        url: '/timesheetApp/api/getTimesheet?timesheetId=' + timesheetId,
        success: function (responsedata) {
            console.log("Inside success function after getting the timesheet info.");
            populateViewTimesheet(responsedata);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
            console.log(jqXHR);
        }
    });
}

function populateViewTimesheet(timesheetObj) {
    console.log("Inside populateViewTimesheet method ::");
    $('#selectedTimePeriod').html(timesheetObj.fromDate + ' - ' + timesheetObj.toDate);
    $('#timesheetStatus').html(timesheetObj.timesheetStatus);
    $('#regularHours').html(timesheetObj.regularHours);
    $('#extraHours').html(timesheetObj.extraHours);
    $('#totalHours').html(timesheetObj.regularHours + timesheetObj.extraHours);
    $('#submitterName').html(timesheetObj.nameCreated);
    $('#submittedDate').html(timesheetObj.dateCreated);
    $('#approvalDate').html(timesheetObj.dateApproved);
    $('#timesheetComments').html(timesheetObj.dscComments);
}