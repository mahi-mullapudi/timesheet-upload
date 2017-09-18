$(document).ready(function () {
    setSummaryTable();
});

/**
 * Check for the validations in the input and calls the function to create and display the DataTable.
 * @returns {boolean}
 */
function setSummaryTable() {
    var fromDate = $('#selectFromDate').val();
    var toDate = $('#selectToDate').val();
    var status = $('#selectStatus').val();
    console.log("Inside setSummaryTable:: fromDate: " + fromDate + " toDate: " + toDate + " status: " + status);
    //Check if the FromDate is greater than the ToDate
    if (fromDate >= toDate) {
        alert("Timesheet week Start date selected should be before the selected End Date. ");
        return false;
    }
    //Fetch Data from the service.
    fetchData(fromDate, toDate, status);
    return false; //This is just avoid the reload of the page.
}

/**
 * Retrieve the Data from the table and
 * @param fromDate
 * @param toDate
 * @param status
 */
function fetchData(fromDate, toDate, status) {
    console.log("Inside the loadDataTable method of dashboard-staff");
    if ($.fn.dataTable.isDataTable('#timesheetSummary')) {
        // Table Already Exists - dispose it and recreate.
        var table = $('#timesheetSummary').DataTable();
        table.destroy();
    }

    var table = $('#timesheetSummary').DataTable({
        ajax: {
            url: '/timesheetApp/api/timesheetSummaryStaff?fromDate='
            + fromDate + '&toDate=' + toDate
            + '&timesheetStatus=' + status,
            dataSrc: ''
        },
        columns: [
            {
                data: 'employeeName'
            },
            {
                data: 'clientName'
            },
            {
                data: 'fromDate',
                mRender: function (data) {
                    return moment(data).format("MM/DD/YYYY");
                }
            },
            {
                data: 'toDate',
                mRender: function (data) {
                    return moment(data).format("MM/DD/YYYY");
                }
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
                    link = '<button class="btn-link" onclick="getTimesheetInfoById(' + data + ')">View Timesheet</button>';
                    return link;
                }
            }
        ],
        order: [[2, 'desc'], [3, 'desc'], [0, 'desc']]
    })
}

/**
 * Retrieve Timesheet information based on given timesheetId and populate the View timesheet fields on dashboard page.
 * @param timesheetId
 */
function getTimesheetInfoById(timesheetId) {
    console.log("Inside getTimesheet method:: timesheetId: " + timesheetId);
    $.ajax({
        type: 'GET',
        url: '/timesheetApp/api/getTimesheetById?timesheetId=' + timesheetId,
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

/**
 * Populates the Timesheet Information into the View Timesheet fields.
 * @param timesheetObj
 */
function populateViewTimesheet(timesheetObj) {
    console.log("Inside populateViewTimesheet method ::");
    $('#selectedTimePeriod').html(moment(timesheetObj.fromDate).format("MM/DD/YYYY") + ' - '
        + moment(timesheetObj.toDate).format("MM/DD/YYYY"));
    $('#timesheetStatus').html(timesheetObj.timesheetStatus);
    $('#employeeName').html(timesheetObj.employeeName);
    $('#employeeTitle').html(); //TODO put in the information for Employee Title.
    $('#clientName').html(timesheetObj.clientName);
    $('#regularHours').html(timesheetObj.regularHours);
    $('#extraHours').html(timesheetObj.extraHours);
    $('#totalHours').html(timesheetObj.regularHours + timesheetObj.extraHours);
    $('#submitterName').html(timesheetObj.nameCreated);
    $('#submittedDate').html(timesheetObj.dateCreated);
    $('#approvalDate').html(timesheetObj.dateApproved);
    $('#timesheetComments').html(timesheetObj.dscComments);
    $('#uploadedTimesheetName').html(timesheetObj.dscFileName);
    $('#uploadTimesheetLink').attr('href', '/timesheetApp/api/getUploadedTimesheet?timesheetId=' + timesheetObj.timesheetId);
}