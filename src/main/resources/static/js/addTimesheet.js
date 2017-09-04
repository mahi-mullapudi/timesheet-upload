$(document).ready(function () {
    var timesheetData = {};
    //Validating for decimal values only being entered
    $('.decimal').keypress(function (e) {
        if (e.which !== 46 && e.which !== 8 && e.which !== 0 && (e.which < 48 || e.which > 57)) {
            alert("Numbers only");
            return false;
        }
    });

    //Validating for numeric values only being entered
    $('.numeric').keypress(function (e) {
        if (e.which !== 8 && e.which !== 0 && (e.which < 48 || e.which > 57)) {
            alert("Numbers only");
            return false;
        }
    });

    $.getJSON('js/user.json', function (data) {
        console.log(JSON.stringify(data.userDetails[0].name));
        $("#Name").text(data.userDetails[0].value);
        $("#EmpId").text(data.userDetails[0].empId);
        $("#Associate").text(data.userDetails[0].title);
        $("#department").text(data.userDetails[0].department);
        $("#reportsTo").text(data.userDetails[0].reportsTo);

        var now = new Date();
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today = now.getFullYear() + "-" + (month) + "-" + (day);

        $('#endDtae').val(today);
        var curr = new Date; // get current date
        var first = curr.getDate() - curr.getDay(); // First day is the day of the month - the day of the week
        var last = first + 6; // last day is the first day + 6
        var firstday = new Date(curr.setDate(first));
        var lastday = new Date(curr.setDate(last));
        var weekStartday = (firstday.getMonth() + 1) + '/' + firstday.getDate() + '/' + firstday.getFullYear();
        var weekEndday = (lastday.getMonth() + 1) + '/' + lastday.getDate() + '/' + lastday.getFullYear();
        $("#timePeriod").text(weekStartday + "-" + weekEndday);
        $("#subimtted").text(data.userDetails[0].submitted);
    });
});

function checkRtLimit(pointer, rtValue) {
    console.log("Pointer ID:" + pointer.id);
    if (rtValue > 8) {
        pointer.focus();
        $("#alert").show();
        $("#errorMessage").text("Regular Time cannot be greater than 8 Hours a day");
        //alert("Regular Time cannot be greater than 8 Hours a day");
    }
}

function submitApproval() {
    console.log(timesheetData);
    setTimesheetData();
}

function setTimesheetData() {
    for (var i = 0; i < timesheetData.timesheetDetailsList.length; i++) {
        switch (timesheetData.timesheetDetailsList[i].workWeekDay) {
            case "Mon":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtMon").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otMon").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toMon").val();
                break;
            case "Tue":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtTue").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otTue").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toTue").val();
                break;
            case "Wed":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtWed").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otWed").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toWed").val();
                break;
            case "Thu":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtThu").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otThu").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toThu").val();
                break;
            case "Fri":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtFri").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otFri").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toFri").val();
                break;
            case "Sat":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtSat").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otSat").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toSat").val();
                break;
            case "Sun":
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtSun").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otSun").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toSun").val();
                break;
            default:
                timesheetData.timesheetDetailsList[i].workDayRtHours = $("#rtSun").val();
                timesheetData.timesheetDetailsList[i].workDayOtHours = $("#otSun").val();
                timesheetData.timesheetDetailsList[i].workDayTimeOffHours = $("#toSun").val();
                break;
        }

    }
}

function updateEndDate(endDate) {
    //make an ajax call
    $.getJSON('js/timesheet.json', function (data) {
        //timesheet diabled
        timesheetData = data;
        if (data.timesheetStatus === "SUBMITTED" || data.timesheetStatus === "APPROVED") {
            var timesheetInf = $('#timesheetInformation');
            timesheetInf.find("input").prop('readonly', true);
            $('#submitApproval').attr('disabled', true);
            $('#savelater').attr('disabled', true);

        }

        $("#timesheetComment").val(data.comments);
        for (var i = 0; i < data.timesheetDetailsList.length; i++) {
            switch (data.timesheetDetailsList[i].workWeekDay) {
                case "Mon":
                    $("#rtMon").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otMon").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toMon").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Tue":
                    $("#rtTue").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otTue").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toTue").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Wed":
                    $("#rtWed").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otWed").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toWed").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Thu":
                    $("#rtThu").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otThu").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toThu").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Fri":
                    $("#rtFri").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otFri").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toFri").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Sat":
                    $("#rtSat").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otSat").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toSat").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                case "Sun":
                    $("#rtSun").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otSun").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toSun").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
                default:
                    $("#rtSun").val(data.timesheetDetailsList[i].workDayRtHours);
                    $("#otSun").val(data.timesheetDetailsList[i].workDayOtHours);
                    $("#toSun").val(data.timesheetDetailsList[i].workDayTimeOffHours);
                    break;
            }

        }

    });
    /* $.ajax({
     url: 'http://localhost:8080/timesheetApp/api/getTimesheet?empId=123124&endDate=04/17/2017',
     type: 'get', // performing a POST request
     data : {
     data1 : 'endDate' // will be accessible in $_POST['data1']
     },
     dataType: 'json',
     success: function(data)
     {
     console.log(data);
     }
     });*/
    /* var timesheetInf=$('#timesheetInformation');
     timesheetInf.find("input").prop('readonly', true);*/
}

function checkOtLimit(pointer, otValue) {
    console.log("Pointer ID:" + pointer.id);
    if (otValue > 4) {
        pointer.focus();
        $("#alert").show();
        $("#errorMessage").text("Over Time cannot be greater than 4 Hours a day");
        //alert("Over Time cannot be greater than 4 Hours a day");
    }
}

function checkTimeOffLimit(pointer, timeOff) {
    console.log("Pointer ID:" + pointer.id);
    if (timeOff != 4 || timeOff != 8) {
        pointer.focus();
        $("#alert").show();
        $("#errorMessage").text("Time off should be either 4 Hours or 8 Hours");
    }
}

function calcTotalRt() {
    var sum = calcSumByClassName("rtinput");
    $("#totalRT").val(sum);
    calcWeekTotals();
    calcDayTotals();
}

function calcTotalOt() {
    var sum = calcSumByClassName("otinput");
    $("#totalOT").val(sum);
    calcWeekTotals();
    calcDayTotals();
}

function calcTotalOtAppr() {
    var sum = calcSumByClassName("otapprinput");
    $("#totalOTApproved").val(sum);
    calcWeekTotals();
    calcDayTotals();
}

function calcTotalTimeOff() {
    var sum = calcSumByClassName('timeoffinput');
    $("#totalTimeOff").val(sum);
    calcWeekTotals();
    calcDayTotals();
}

function calcDayTotals() {
    $('#monInput').val(calcSumByClassName("monInput"));
    $('#tueInput').val(calcSumByClassName("tueInput"));
    $('#wedInput').val(calcSumByClassName("wedInput"));
    $('#thuInput').val(calcSumByClassName("thuInput"));
    $('#friInput').val(calcSumByClassName("friInput"));
    $('#satInput').val(calcSumByClassName("satInput"));
    $('#sunInput').val(calcSumByClassName("sunInput"));
}

function calcWeekTotals() {
    var sum = 0;
    var val1 = $("#totalRT").val();
    var val2 = $("#totalOT").val();

    if (val1) {
        val1 = parseFloat(val1.replace(/^\$/, ""));
        val1 = !isNaN(val1) ? val1 : 0;
    }

    if (val2) {
        val2 = parseFloat(val2.replace(/^\$/, ""));
        val2 = !isNaN(val2) ? val2 : 0;
    }

    var sum = val1 + val2;
    $('#totalWeekHours').val(sum);
}

function calcSumByClassName(className) {
    var sum = 0;
    var x = document.getElementsByClassName(className);
    for (var i = 0; i < x.length; i++) {
        var val = $.trim(x[i].value);
        if (val) {
            val = parseFloat(val.replace(/^\$/, ""));
            sum += !isNaN(val) ? val : 0;
            console.log("Sum: " + sum);
        }
    }

    return sum;
}
