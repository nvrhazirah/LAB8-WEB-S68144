
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Application</title>
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/lfQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZwlT"
              crossorigin="anonymous">


    </head>
    <body>
        <header> 
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="" class="navbar-brand"> Employee Management App </a>

                </div>

                <ul class ="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"class="nav-link">Employees</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">

                    <c:if test ="${employee !=null}">

                        <form action="update" method="post">
                        </c:if>

                        <c:if test="${employee == null}">
                            <form action="insert" method="post">
                        </c:if>

                            <h2>
                                <c:if test="${employee != null}">
                                    Edit Employee
                                </c:if>

                                <c:if test="${employee == null}">
                                    Add New Employee
                                </c:if>

                            </h2>

                            <c:if test="${employee!=null}">
                                <input type="hidden" name ="id" value="<c:out value='${employee.id}'/>"/>

                            </c:if>

                            <fieldset class="form-group">
                                <label>Employee Name</label>
                                <input type="text" value="<c:out value='${employee.name}'/>"
                                       class="form-control" name="name" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Employee Email</label>
                                <input type="text" value="<c:out value='${employee.email}'/>"
                                       class="form-control" name="email">
                            </fieldset>

                                       <fieldset class ="form-group">
                                           <label> Employee Position </label>
                                           <input type="text" value="<c:out value='${employee.position}'/>" class="form-control" readonly><!-- comment -->
                                           <input list="positionList" id="position" class="form-control" name="position"><!-- comment -->
                                           <datalist id="positionList">
                                               <option value="Manager "></option>
                                               <option value="Head of Dept "></option><!-- comment -->
                                               <option value="Supervisor "></option><!-- comment -->
                                               <option value="Director "></option>
                                           </datalist>
                                           
                                       </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>

                </div>
            </div>  



        </div>


    </body>
</html>
