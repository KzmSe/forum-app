package com.step.forum.constants;

public class NavigationConstants {

    //actions
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

    //prefixes
    private static final String PAGE_PREFIX_VIEW = "/WEB-INF/view/";
    private static final String PAGE_PREFIX_FRAGMENT = "/WEB-INF/fragment/";

    //pages
    public static final String PAGE_INDEX = PAGE_PREFIX_VIEW + "index.jsp";
    public static final String PAGE_LOGIN = PAGE_PREFIX_VIEW + "login.jsp";
    public static final String PAGE_NEW_ACCOUNT = PAGE_PREFIX_VIEW + "new-account.jsp";
    public static final String PAGE_NEW_TOPIC = PAGE_PREFIX_VIEW + "new-topic.jsp";
    public static final String PAGE_TOPIC = PAGE_PREFIX_VIEW + "topic.jsp";

    //fragments
    public static final String FRAGMENT_HEADER = PAGE_PREFIX_FRAGMENT + "fragment-header.jsp";
    public static final String FRAGMENT_FOOTER = PAGE_PREFIX_FRAGMENT + "fragment-footer.jsp";
    public static final String FRAGMENT_IMPORTS = PAGE_PREFIX_FRAGMENT + "fragment-imports.jsp";
    public static final String FRAGMENT_RIGHT_MENU = PAGE_PREFIX_FRAGMENT + "fragment-right-menu.jsp";
    public static final String FRAGMENT_SIMILAR_POST = PAGE_PREFIX_FRAGMENT +"fragment-similar-post.jsp";
    public static final String FRAGMENT_COMMENTS = PAGE_PREFIX_FRAGMENT + "fragment-comments.jsp";

}