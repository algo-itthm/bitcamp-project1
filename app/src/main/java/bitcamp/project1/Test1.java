package bitcamp.project1;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    static void m1() {
        System.out.println("오호라!!!!");
    }

    static void m3(String[] mainMenus) {
        System.out.println("오호라!!!");
        for(String menu : mainMenus) {
            System.out.println(menu);
        }
    }

    //"income(수입)" or "expense(지출)”
    String[] mainMenus = new String[]{"수입", "지출", "수입/지출 조회", "종료"};

    String[][] subMenus = {
            {"등록", "목록", "변경", "삭제", "이전"},
            {"등록", "목록", "변경", "삭제", "이전"},
            {/*  Ansi Code 사용. 수입과 지출 글씨 색 다르게 표현
                TotlaPrice(수입-지출) 출력 */},
            {}
    };




}
