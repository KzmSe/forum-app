<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Forum :: New topic</title>

    <!-- imports -->
    <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-imports.jsp"></c:import>

    <script type="text/javascript">
        $(function () {
            $('#idInputTitle').blur(function () {
                var title = $('#idInputTitle').val();
                $.ajax({
                    url: '/ts?action=getSimilarTopics',
                    method: 'GET',
                    dataType: 'html',
                    data: 'title=' + title,
                    success: function (data) {
                        $('#idDivSimilarPost').html(data);
                    }
                });
            });

            <c:if test="${message ne null}">
            alert('${message}');
            </c:if>
        });
    </script>

</head>
<body>

<div class="container-fluid">

    <c:import url="${pageContext.request.contextPath}/WEB-INF/fragments/fragment-header.jsp"></c:import>

    <section class="content">
        <br><br>


        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-8">


                    <!-- POST -->
                    <div class="post">
                        <form action="${pageContext.request.contextPath}/ts?action=addTopic" class="form newtopic"
                              method="post">
                            <div class="topwrap">
                                <div class="userinfo pull-left">
                                    <div class="avatar">
                                        <img src="${pageContext.request.contextPath}/resources/images/avatar4.jpg"
                                             alt=""/>
                                    </div>
                                </div>
                                <div class="posttext pull-left">

                                    <div>
                                        <input type="text" placeholder="Enter Topic Title" name="title"
                                               id="idInputTitle"
                                               class="form-control" required/>
                                    </div>


                                    <div>
                                        <textarea name="description" placeholder="Description" class="form-control"
                                                  required></textarea>
                                    </div>

                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="postinfobot">

                                <div class="pull-right postreply">
                                    <div class="pull-left">
                                        <button type="submit" class="btn btn-primary">Post</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>


                                <div class="clearfix"></div>
                            </div>
                        </form>
                    </div>
                    <!-- POST -->

                    <!-- POST -->
                    <div id="idDivSimilarPost">
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
</body>

<!-- Mirrored from forum.azyrusthemes.com/new-topic.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 16 Oct 2018 07:34:53 GMT -->
</html>