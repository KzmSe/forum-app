<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Forum :: Topic</title>

        <!-- imports -->
        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/imports.jsp"></c:import>

    </head>
    <body class="topic">

        <div class="container-fluid">

            <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/page-header.jsp"></c:import>

            <section class="content">
               <br><br>


                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-md-8">

                            <!-- MAIN POST -->
                            <div class="post beforepagination panel-primary">
                                <div class="topwrap">
                                    <div class="userinfo pull-left">
                                        <div class="avatar">
                                            <img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="${topic.user.firstname} ${topic.user.lastname}" title="${topic.user.firstname} ${topic.user.lastname}"/>
                                        </div>

                                    </div>
                                    <div class="posttext pull-left">
                                        <h2>${topic.title}</h2>
                                        <p>${topic.description}</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="postinfobot">

                                    <div class="posted pull-left"><i class="fa fa-clock-o"></i> Posted on : ${topic.shareDate}</div>
                                    <div class="posted pull-left"> Posted by : ${topic.user.firstname} ${topic.user.lastname}</div>
                                    <div class="posted pull-left"><i class="fa fa-trash-o"></i></div>

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            <!-- MAIN POST -->

                  

                            <c:forEach var="comment" items="${topic.commentList}">
                                <!-- COMMENT -->
                                <div class="post">
                                    <div class="topwrap">
                                        <div class="userinfo pull-left">
                                            <div class="avatar">
                                                <img src="${pageContext.request.contextPath}/resources/images/avatar2.jpg" alt="${comment.user.firstname} ${comment.user.lastname}" title="${comment.user.firstname} ${comment.user.lastname}"/>
                                            </div>

                                        </div>
                                        <div class="posttext pull-left">
                                            <p>${comment.description}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="postinfobot">

                                        <div class="posted pull-left"><i class="fa fa-clock-o"></i> Posted on : ${comment.writeDate}</div>
                                        <div class="posted pull-left"> ${comment.user.firstname} ${comment.user.lastname}</div>
                                        <div class="posted pull-left"><i class="fa fa-trash-o"></i></div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                                <!-- COMMENT -->
                            </c:forEach>



                            <br><br>

                            <c:if test="${sessionScope.user ne null}">
                                <!-- REPLY -->
                                <div class="post">
                                    <form action="#" class="form" method="post">
                                        <div class="topwrap">
                                            <div class="userinfo pull-left">
                                                <div class="avatar">
                                                    <img src="${pageContext.request.contextPath}/resources/images/avatar4.jpg" alt="" />
                                                </div>

                                            </div>
                                            <div class="posttext pull-left">
                                                <div class="textwraper">
                                                    <div class="postreply">Post a Reply</div>
                                                    <textarea name="reply" id="reply" placeholder="Type your message here"></textarea>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="postinfobot">


                                            <div class="pull-right postreply">
                                                <div class="pull-left"><button type="submit" class="btn btn-primary">Post Reply</button></div>
                                                <div class="clearfix"></div>
                                            </div>


                                            <div class="clearfix"></div>
                                        </div>
                                    </form>
                                </div>
                                <!-- REPLY -->
                            </c:if>


                        </div>

                        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/page-right-menu.jsp"></c:import>

                    </div>
                </div>

                <br><br>


            </section>

            <footer>
                <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/page-footer.jsp"></c:import>
            </footer>
        </div>

    </body>

<!-- Mirrored from forum.azyrusthemes.com/topic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 16 Oct 2018 05:39:38 GMT -->
</html>