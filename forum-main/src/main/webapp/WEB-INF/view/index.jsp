<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Forum :: Home Page</title>

        <!-- imports -->
        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/fragment-imports.jsp"></c:import>

    </head>
    <body>

        <div class="container-fluid">

            <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/fragment-header.jsp"></c:import>

            <section class="content">

                <div class="container">
                	<h1>All Topics</h1>
                    <div class="row">
                        <div class="col-lg-8 col-md-8">

                            <c:forEach var="topic" items="${topicList}">
                                <!-- POST -->
                                <div class="post">
                                    <div class="wrap-ut pull-left">
                                        <div class="userinfo pull-left">
                                            <div class="avatar">
                                                <img src="${pageContext.request.contextPath}/uploads/${topic.user.imagePath}" alt="${topic.user.firstname} ${topic.user.lastname}" title="${topic.user.firstname} ${topic.user.lastname}"/>
                                            </div>
                                        </div>
                                        <div class="posttext pull-left">
                                            <h2><a href="${pageContext.request.contextPath}/ns?action=topic&id=${topic.id}">${topic.title}</a></h2>
                                            <p>${topic.description}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="postinfo pull-left">
                                        <div class="comments">
                                            <div class="commentbg">
                                                ${topic.commentList.size()}
                                                <div class="mark"></div>
                                            </div>

                                        </div>
                                        <div class="views"><i class="fa fa-eye"></i>${topic.viewCount}</div>
                                        <div class="time"><i class="fa fa-clock-o"></i> ${topic.getAgeOf()}</div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <!-- POST -->
                            </c:forEach>

                        </div>

                        <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/fragment-right-menu.jsp"></c:import>

                    </div>
                </div>

                <br><br>

            </section>

            <footer>
                <c:import url="${pageContext.request.contextPath}/WEB-INF/fragment/fragment-footer.jsp"></c:import>
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

<!-- Mirrored from forum.azyrusthemes.com/index.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 16 Oct 2018 05:38:53 GMT -->
</html>