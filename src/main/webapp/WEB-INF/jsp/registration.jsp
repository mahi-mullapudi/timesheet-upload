<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">
    <title>TechNumen Registration</title>
    <!-- Bootstrap core CSS -->
    <link href="css/external/bootstrap.min.css" rel="stylesheet">
    <!--Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">
    <!-- Icons -->
    <link href="css/external/font-awesome.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
<div class="container" id="wrapper">
    <main class="col-md-12">
        <section class="row">
            <div class="col-sm-12">
                <section class="row">
                    <div class="form-signin">
                        <br><img src="images/technumen-logo.png">
                        <hr>
                        <h2>Registration Details</h2>
                    </div>
                    <div class="col-md-12">
                        <spring:url value="/register" var="registrationActionUrl"/>
                        <form:form method="POST" modelAttribute="employee"
                                   action="${registrationActionUrl}" class="form-signin">

                            <div class="form-group row">
                                <spring:bind path="employeeEmailId">
                                    <div class="form-group row ${status.error ? 'has-error' : ''}">
                                        <label for="emailId" class="col-sm-2 col-form-label">Email</label>
                                        <div class="col-sm-4">
                                            <form:input path="employeeEmailId" type="email" class="control-label"
                                                        id="emailId"
                                                        placeholder="Email"/>
                                            <form:errors path="employeeEmailId" class="control-label"/>
                                        </div>
                                    </div>
                                </spring:bind>

                                <spring:bind path="assignedEmployeeId">
                                    <div class="form-group row ${status.error ? 'has-error' : ''}">
                                        <label for="empId" class="col-sm-2 col-form-label">Employee Id</label>
                                        <div class="col-sm-4">
                                            <form:input path="assignedEmployeeId" class="form-control" id="empId"
                                                        placeholder="Employee Id"/>
                                            <form:errors path="employeeEmailId" class="control-label"/>
                                        </div>
                                    </div>
                                </spring:bind>
                            </div>

                            <%--<div class="form-group row">
                                <label for="fName" class="col-sm-2 col-form-label">First Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="fName"
                                           placeholder="First Name">
                                </div>

                                <label for="lName" class="col-sm-2 col-form-label">Last Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="lName"
                                           placeholder="Last Name">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="clientName" class="col-sm-2 col-form-label">Client Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="clientName"
                                           placeholder="Client Name">
                                </div>

                                <label for="clientAddress" class="col-sm-2 col-form-label">Client Address</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="clientAddress"
                                           placeholder="Client Address">
                                </div>
                            </div> --%>

                            <div class="form-signin">
                                <button class="btn btn-lg btn-success btn-block" type="submit">Register</button>
                            </div>

                        </form:form>
                        <hr>
                        <div class="form-signin">
                            <a href="login.html">Login into your Existing TechNumen account</a>
                        </div>
                    </div>
                </section>
            </div>
        </section>
    </main>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/external/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="js/external/bootstrap.min.js"></script>

</body>
</html>
