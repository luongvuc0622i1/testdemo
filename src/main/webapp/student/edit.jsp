<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <style>
        .gradient-custom {
            /* fallback for old browsers */
            background: #f093fb;
            /* Chrome 10-25, Safari 5.1-6 */
            background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
            /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
            background: linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1))
        }
        .card-registration .select-input.form-control[readonly]:not([disabled]) {
            font-size: 1rem;
            line-height: 2.15;
            padding-left: .75em;
            padding-right: .75em;
        }
        .card-registration .select-arrow {
            top: 13px;
        }
    </style>
</head>
<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit Form</h3>
                        <form method="post" action="/students?action=edit">
                            <c:if test="${student != null}">
                                <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
                            </c:if>
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">Name
                                        <input type="text" id="firstName" class="form-control form-control-lg"
                                               value="${student.name}" name="name">
                                        <label class="form-label" for="firstName"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">Date Of Birth
                                        <input type="text" id="dateOfBirth" class="form-control form-control-lg"
                                               value="${student.dateOfBirth}" name="dateOfBirth">
                                        <label class="form-label" for="firstName"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">Address
                                        <input type="text" id="address" class="form-control form-control-lg"
                                               value="${student.address}" name="address">
                                        <label class="form-label" for="firstName"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">Phone Number
                                        <input type="text" id="phoneNumber" class="form-control form-control-lg"
                                               value="${student.phone}" name="phone">
                                        <label class="form-label" for="firstName"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">Email
                                        <input type="text" id="email" class="form-control form-control-lg"
                                               value="${student.email}" name="email">
                                        <label class="form-label" for="firstName"></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4 pb-2">

                                    <div class="form-outline">Class
                                        <select name="classroom" class="form-control">
                                            <c:forEach var="classroom" items="${classes}">
                                                <option value="${classroom.class_id}">${classroom.class_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="mt-4 pt-2">
                                <input class="btn btn-primary btn-lg" type="submit" value="Submit"/>
                            </div>
                            <p><c:if test="${message != null}">
                                <span>${message}</span>
                            </c:if>
                            </p>
                            <a href="/students">Back to list</a>


                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>