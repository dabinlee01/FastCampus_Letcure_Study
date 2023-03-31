public class ViewResolver {
    // view이름을 입력받아 실제 JSP 파일의 경로를 반환하는 클래스.
    private static String prefix= "/WEB-INF/views";
    private static String suffix = ".jsp";

    // 입력받은 뷰 이름에 prefix와 suffix를 붙인 문자열을 반환
    public static String resolve(String viewName) {
        return prefix+viewName+suffix;  // /WEB-INF/views/board/list.jsp 반환
    }
}