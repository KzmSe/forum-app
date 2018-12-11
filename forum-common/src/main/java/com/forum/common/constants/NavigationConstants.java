package com.forum.common.constants;

public class NavigationConstants {

    //prefixes
    private static final String PAGE_PREFIX_VIEW = "/WEB-INF/view/";
    private static final String PAGE_PREFIX_FRAGMENT = "/WEB-INF/fragment/";

    //forum-main actions
    public static final String ACTION_ADD_COMMENT = "addComment";
    public static final String ACTION_GET_COMMENTS = "getComments";
    public static final String ACTION_NEW_TOPIC = "newTopic";
    public static final String ACTION_TOPIC = "topic";
    public static final String ACTION_REGISTER = "register";
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_LOGOUT = "logout";
    public static final String ACTION_ADD_TOPIC = "addTopic";
    public static final String ACTION_GET_POPULAR_TOPICS = "getPopularTopics";
    public static final String ACTION_GET_ALL_ACTIVE_TOPICS = "getAllActiveTopics";
    public static final String ACTION_GET_SIMILAR_TOPICS = "getSimilarTopics";
    public static final String ACTION_DO_REGISTER = "doRegister";
    public static final String ACTION_DO_LOGIN = "doLogin";
    public static final String ACTION_ACTIVATE = "activate";

    //forum-main pages
    public static final String PAGE_INDEX = PAGE_PREFIX_VIEW + "index.jsp";
    public static final String PAGE_LOGIN_COMMON = PAGE_PREFIX_VIEW + "login.jsp";
    public static final String PAGE_NEW_ACCOUNT = PAGE_PREFIX_VIEW + "new-account.jsp";
    public static final String PAGE_NEW_TOPIC = PAGE_PREFIX_VIEW + "new-topic.jsp";
    public static final String PAGE_TOPIC = PAGE_PREFIX_VIEW + "topic.jsp";

    //forum-main fragments
    public static final String FRAGMENT_SIMILAR_POST = PAGE_PREFIX_FRAGMENT +"fragment-similar-post.jsp";
    public static final String FRAGMENT_COMMENTS = PAGE_PREFIX_FRAGMENT + "fragment-comments.jsp";





    //forum-admin-actions


    //forum-admin pages
    public static final String PAGE_INBOX = PAGE_PREFIX_VIEW + "inbox.jsp";
    public static final String PAGE_ACTIVE_TOPICS = PAGE_PREFIX_VIEW + "active-topics.jsp";
    public static final String PAGE_MAIL_COMPOSE = PAGE_PREFIX_VIEW + "mail_compose.jsp";
    public static final String PAGE_MAIL_VIEW = PAGE_PREFIX_VIEW + "mail_view.jsp";
    public static final String PAGE_PENDING_TOPICS = PAGE_PREFIX_VIEW + "pending-topics.jsp";
    public static final String PAGE_ACTIVE_USERS = PAGE_PREFIX_VIEW + "active-users.jsp";
    public static final String PAGE_BLOCKED_USERS = PAGE_PREFIX_VIEW + "blocked-users.jsp";

    //forum-admin fragments
    public static final String FRAGMENT_TOPIC = PAGE_PREFIX_FRAGMENT + "topic.jsp";

}