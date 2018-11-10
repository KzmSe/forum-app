<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row similarposts">
    <div class="col-lg-10"><i class="fa fa-info-circle"></i>
        <p>Similar Posts according to your Topic Title.</p></div>
</div>

<c:forEach var="topic" items="${similarTopics}">
    <!-- POST -->
    <div class="post">
        <div class="wrap-ut pull-left">
            <div class="userinfo pull-left">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/resources/images/avatar.jpg" alt="${topic.user.firstname} ${topic.user.lastname}" title="${topic.user.firstname} ${topic.user.lastname}"/>
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