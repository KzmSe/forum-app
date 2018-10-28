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
                    //TODO: prob
                    $('#idPopularTopics').append('<li><a href="/ns?action=topic&id='+topic.id+'">topic.title<span class="badge pull-right">'+topic.commentsCount+'</span></a></li>');
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
                <div class="divline"></div>
                <div class="blocktxt">
                    <a href="#">This Dock Turns Your iPhone Into a Bedside Lamp</a>
                </div>
                <div class="divline"></div>
                <div class="blocktxt">
                    <a href="#">Who Wins in the Battle for Power on the Internet?</a>
                </div>
                <div class="divline"></div>
                <div class="blocktxt">
                    <a href="#">Sony QX10: A Funky, Overpriced Lens Camera for Your Smartphone</a>
                </div>
                <div class="divline"></div>
                <div class="blocktxt">
                    <a href="#">FedEx Simplifies Shipping for Small Businesses</a>
                </div>
                <div class="divline"></div>
                <div class="blocktxt">
                    <a href="#">Loud and Brave: Saudi Women Set to Protest Driving Ban</a>
                </div>
            </div>
        </c:when>
    </c:choose>


</div>