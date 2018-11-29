<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="comment" items="${comments}">


    <div class="post">
        <div class="topwrap">
            <div class="userinfo pull-left">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/uploads/${comment.user.imagePath}"
                         alt="${comment.user.firstname} ${comment.user.lastname}"
                         title="${comment.user.firstname} ${comment.user.lastname}"/>
                </div>

            </div>
            <div class="posttext pull-left">
                <p>${comment.description}</p>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="postinfobot">

            <div class="posted pull-left"><i class="fa fa-clock-o"></i> Posted on
                : ${comment.writeDate}</div>
            <div class="posted pull-left"> ${comment.user.firstname} ${comment.user.lastname}</div>
            <div class="posted pull-left"><i class="fa fa-trash-o"></i></div>
            <div class="clearfix"></div>
        </div>
    </div>

</c:forEach>