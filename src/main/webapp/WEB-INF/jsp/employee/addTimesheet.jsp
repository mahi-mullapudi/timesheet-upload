<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="description" content="">

    <meta name="author" content="">

    <link rel="icon" href="images/favicon.ico">

    <title>TechNumen Add Timesheets</title>

    <link href="./css/external/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <!--Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">
    <!-- Icons -->
    <link href="./css/external/font-awesome.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="./css/style.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid" id="wrapper">
    <div class="row">
        <nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1">
            <h1 class="site-title"><a href="index.html"><em class="fa fa-rocket"></em> TechNumen Inc.,</a></h1>

            <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em class="fa fa-bars"></em></a>

            <ul class="nav nav-pills flex-column sidebar-nav">
                <li class="nav-item"><a class="nav-link" href="index.html"><em class="fa fa-dashboard"></em>
                    Dashboard</a></li>
                <li class="nav-item"><a class="nav-link active" href="addTimesheet.html"><em
                        class="fa fa-calendar-o"></em>
                    Upload Timesheet <span class="sr-only">(current)</span></a></li>
                <li class="nav-item"><a class="nav-link" href="reports.html"><em class="fa fa-bar-chart"></em>
                    Reports</a>
                </li>

            </ul>


        </nav>

        <main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
            <header class="page-header row justify-center">
                <div class="col-md-6 col-lg-8">
                    <h1 class="float-left text-center text-md-left"> Upload New Timesheet </h1>
                </div>

                <div class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right">
                    <a class="btn btn-stripped dropdown-toggle" href="https://example.com" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="images/profile-pic.jpg" alt="profile photo" class="circle float-left profile-photo"
                             width="50" height="auto">

                        <div class="username mt-1">
                            <h4 class="mb-1">testuser</h4>

                            <h6 class="text-muted">Employee</h6>
                        </div>
                    </a>

                    <div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;"
                         aria-labelledby="dropdownMenuLink"><a class="dropdown-item" href="#"><em
                            class="fa fa-user-circle mr-1"></em> View Profile</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-sliders mr-1"></em> Preferences</a>
                        <a class="dropdown-item" href="#"><em class="fa fa-power-off mr-1"></em> Logout</a>
                    </div>
                </div>

                <div class="clear"></div>
            </header>

            <section class="row">
                <div class="col-md-12">
                    <section class="row">
                        <div class="col-md-12">
                            <div class="jumbotron">
                                <h1 class="mb-4">Timesheet Information</h1>

                                <form>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label">Emp Name </label>
                                        <div class="col-lg-4">
                                            <span> Test User </span>
                                        </div>

                                        <label class="col-lg-2 col-form-label">Employee Id </label>
                                        <div class="col-lg-4">
                                            <span> 2323423423 </span>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-lg-2 col-form-label">Client </label>
                                        <div class="col-lg-4">
                                            <span> PayPal Inc., </span>
                                        </div>

                                        <label class="col-lg-2 col-form-label">Address </label>
                                        <div class="col-lg-4">
                                            <span> 22 S Newton Dr, San Jose, California </span>
                                        </div>
                                    </div>
                                    <br>

                                    <div class="form-group row">
                                        <label for="selectTimePeriod" class="col-lg-2 col-form-label">Select
                                            TimePeriod </label>
                                        <div class="col-lg-4">
                                            <select class="form-control" id="selectTimePeriod">
                                                <option>09/04/17 - 09/10/17</option>
                                                <option>08/28/17 - 09/03/17</option>
                                                <option>08/21/17 - 09/27/17</option>
                                                <option>08/14/17 - 08/20/17</option>
                                                <option>08/07/17 - 08/13/17</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="regularHoursText" class="col-lg-2 col-form-label">Regular
                                            Hours </label>
                                        <div class="col-lg-4">
                                            <input type="text" class="form-control-plaintext" id="regularHoursText">
                                        </div>

                                        <label for="extraHoursText" class="col-lg-2 col-form-label">Extra
                                            Hours </label>
                                        <div class="col-lg-4">
                                            <input type="text" class="form-control-plaintext" id="extraHoursText">
                                        </div>
                                    </div>

                                    <p>Please Upload your approved timesheet for the selected time period</p>

                                    <div class="form-group row">
                                        <input type="file" class="form-control-file" id="exampleFormControlFile1">
                                        <button type="submit" class="btn btn-primary">Upload Timesheet</button>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleFormControlTextarea1">Comments </label>
                                        <textarea class="form-control col-xl-8" id="exampleFormControlTextarea1"
                                                  rows="3"></textarea>
                                    </div>

                                    <div class="text-center">
                                        <input type="submit" class="btn btn-success"
                                               value="Submit For Review"/>
                                    </div>

                                </form>

                            </div>

                            <div class="card mb-4">
                                <div class="card-block">

                                    <h3 class="card-title">Timesheet Upload Process</h3>

                                    <!-- <h6 class="card-subtitle mb-2 text-muted">Vertical Layout</h6> -->

                                    <ul class="timeline">
                                        <li>
                                            <div class="timeline-badge"><em class="fa fa-camera"></em></div>

                                            <div class="timeline-panel">
                                                <div class="timeline-heading">
                                                    <h5 class="timeline-title mt-2">Save Approved Timesheet </h5>
                                                </div>

                                                <div class="timeline-body">
                                                    <p>Please save your approved timesheet in PDF format.</p>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="timeline-badge primary"><em class="fa fa-link"></em></div>

                                            <div class="timeline-panel">
                                                <div class="timeline-heading">
                                                    <h5 class="timeline-title mt-2">Select time period</h5>
                                                </div>

                                                <div class="timeline-body">
                                                    <p>Select the time period and then fill in the timesheet information
                                                        for Regular Hours and Extra Hours as per the approved timesheet.
                                                    </p>
                                                </div>
                                            </div>
                                        </li>

                                        <li>
                                            <div class="timeline-badge"><em class="fa fa-paperclip"></em></div>
                                            <div class="timeline-panel">
                                                <div class="timeline-heading">
                                                    <h5 class="timeline-title mt-2">Upload Timesheet</h5>
                                                </div>

                                                <div class="timeline-body">
                                                    <p>Upload the Approved timesheet and click on <u>Upload
                                                        Timesheet</u> button. Once all the information is filled, click
                                                        on <u>Submit For Review</u>.
                                                    </p>
                                                </div>
                                            </div>
                                        </li>

                                        <li>
                                            <div class="timeline-badge"><em class="fa fa-check"></em></div>
                                            <div class="timeline-panel">
                                                <div class="timeline-heading">
                                                    <h5 class="timeline-title mt-2">Review Timesheet</h5>
                                                </div>

                                                <div class="timeline-body">
                                                    <p>Uploaded timesheet shall be reviewed and approved by Internal
                                                        staff and process your payroll.</u>.
                                                    </p>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </section>

                    <section class="row">
                        <div class="col-12 mt-1 mb-4">Copy Right by <a href="http://www.technumen.com/">TechNumen
                            Inc.,</a></div>
                    </section>
                </div>
            </section>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/external/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="./js/external/bootstrap.min.js"></script>
<script src="./js/external/bootstrap-datepicker.js"></script>
<script src="./js/external/custom.js"></script>

</body>
</html>
