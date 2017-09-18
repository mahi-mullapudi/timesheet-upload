$(document).ready(function () {
    loadDataTable();
});

function loadDataTable() {
    var table = $('#timesheetSummary').DataTable({
        ajax: {
            url: '/timesheetApp/api/timesheetSummaryStaff?fromDate='
            + $('#selectFromDate').val() + '&toDate=' + $('#selectToDate').val()
            + '&timesheetStatus=' + $('#timesheetStatus').val(),
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
                    link = '<button class="btn-link" onclick="getTimesheetById(' + data + ')">View Timesheet</button>';
                    return link;
                }
            }
        ],
        order: [[2, 'desc'], [3, 'desc'], [0, desc]]
    })
}