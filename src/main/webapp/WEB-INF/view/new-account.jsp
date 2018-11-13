<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Forum :: New account</title>

        <!-- imports -->
        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-imports.jsp"></c:import>

    </head>
    <body class="newaccountpage">

        <div class="container-fluid">

            <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-header.jsp"></c:import>

            <section class="content">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 breadcrumbf">
                            <p>Create New account</p>
                        </div>
                    </div>
                </div>


                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-8">



                            <!-- POST -->
                            <div class="post">
                                <form action="us?action=doRegister" class="form newtopic" method="POST" enctype="multipart/form-data">
                                    <div class="postinfotop">
                                        <h2>Create New Account</h2>
                                    </div>

                                    <!-- acc section -->
                                    <div class="accsection">
                                        <div class="acccap">
                                            <div class="userinfo pull-left">&nbsp;</div>
                                            <div class="posttext pull-left"><h3>Required Fields</h3></div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="topwrap">
                                            <div class="userinfo pull-left">
       
                                            </div>
                                            <div class="posttext pull-left">
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6">
                                                        <input type="text" placeholder="First Name" class="form-control" name="firstname"/>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <input type="text" placeholder="Last Name" class="form-control" name="lastname"/>
                                                    </div>
                                                </div>
                                                <div>
                                                    <input type="text" placeholder="Email" class="form-control" name="email"/>
                                                </div>
                                                <div>
                                                    <input type="file" class="form-control" name="image" accept="image/jpeg, image/jpg, image/png">
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6">
                                                        <input type="password" placeholder="Password" class="form-control" name="password"/>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6">
                                                        <input type="password" placeholder="Retype Password" class="form-control" name="rePassword"/>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="clearfix"></div>
                                        </div>  
                                    </div>
                                    <!-- acc section END -->

                                    <div class="postinfobot">

                                        <div class="pull-right postreply">
                                            <div class="pull-left"><button type="submit" class="btn btn-primary">Sign Up</button></div>
                                            <div class="clearfix"></div>
                                        </div>


                                        <div class="clearfix"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- POST -->






                        </div>

                        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-right-menu.jsp"></c:import>

                    </div>
                </div>

            </section>

              <footer>
                  <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-footer.jsp"></c:import>
            </footer>
        </div>

        <script type="text/javascript">
            $(function () {
                <c:if test="${message ne null}">
                alert('${message}');
                </c:if>
            });
        </script>

    </body>

</html>