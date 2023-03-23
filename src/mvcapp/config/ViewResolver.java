package mvcapp.config;

public class ViewResolver {
    private static String prefix = "/WEB-INF/views";
    private static String suffix = ".jsp";

    public static String resolve(String viewName){ // 뷰의 이름만 받음   /board/list
        // /WEB-INF/views/board/list.jsp
        return prefix+viewName+suffix;
    }
}