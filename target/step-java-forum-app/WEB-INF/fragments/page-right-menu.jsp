<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '/ts?action=getPopularTopics',
            type: 'GET',
            dataType: 'json',
            success: function (topicList) {
                $('#idPopularTopics').empty();
                topicList.forEach(function (topic) {
                    $('#idPopularTopics').append('<li><a href="/ns?action=topic&id='+topic.id+'">'+topic.title+'<span class="badge pull-right">'+topic.commentsCount+'</span></a></li>');
                })
            }
        });

        $.ajax({
            url: '/ts?action=getAllActiveTopics',
            type: 'GET',
            dataType: 'json',
            success: function (topicList) {
                topicList.forEach(function (topic) {
                    $('#idDivActiveThreads').empty();
                    $('#idDivActiveThreads').append('<div class="divline"></div>');
                    $('#idDivActiveThreads').append('<div class="blocktxt">\n' +
                        '<a href="/ns?action=topic&id='+topic.id+'">'+topic.title+'</a>\n' +
                        '</div>');
                })
            }
        });

    });
</script>

<div class="col-lg-4 col-md-4">

    <!-- -->
    <div class="sidebarblock">
        <h3 class="bg-primary">Popular Topics</h3>
        <div class="divline"></div>
        <div class="blocktxt">
            <ul class="cats" id="idPopularTopics">
                <li><span>Loading popular topics..</span></li>
            </ul>
        </div>
    </div>

    <!-- -->
    <c:choose>
        <c:when test="${sessionScope.user ne null}">
            <div class="sidebarblock">
                <h3 class="bg-primary">My Active Threads</h3>
                <div id="idDivActiveThreads">

                </div>
            </div>
        </c:when>
    </c:choose>


</div>