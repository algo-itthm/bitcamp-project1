package bitcamp.project1.command;

import bitcamp.project1.vo.Income;

import java.util.Date;
import java.util.LinkedList;


public class IncomeCommand {
    LinkedList<Income> incomeList = new LinkedList<Income>();

    public void executeIncomeCommand(String command){
        System.out.printf("[%s]\n", command);

        switch (command) {
            case "등록":
                addIncome();
                break;
            case "조회":
                viewIncome();
                break;
            case "변경":
                updateIncome();
                break;
            case "삭제":
                deleteIncome();
                break;
        }
    }
    public void addIncome(){
        Income income = new Income();
        income.setTitle(Prompt.input("제목?"));
        income.setContent(Prompt.input("내용?"));
        income.setCreatedDate(new Date());
        income.setViewCount(0);
        income.setNo(Income.getSeqNo());
        boardList.add(income);
        System.out.println("등록했습니다.");
    }
    public void viewIncome(){
        int incomeNo = Prompt.inputInt("게시판 번호?");
        Income income = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (income == null) {
            System.out.println("없는 게시판입니다.");
            return;
        }
        income.setViewCount(income.getViewCount() + 1);
        System.out.printf("제목 : %s\n", income.getTitle());
        System.out.printf("내용 : %s\n", income.getContent());
        System.out.printf("작성일 : %1$tY-%1$tm-%1$td  %1$tH : %1$tM : %1$tS\n", income.getCreatedDate());
        System.out.printf("조회수 : %s\n", income.getViewCount());
        }
    }
    public void updateIncome(){
        int incomeNo = Prompt.inputInt("게시판 번호?");
        Income income = (Income) income.get(income.indexOf(new Income(incomeNo)));
        if (income == null) {
            System.out.println("없는 게시판입니다.");
            return;
        }
        income.setViewCount(income.getViewCount() + 1);
        income.setTitle(Prompt.input("게시판명(%s)\n", income.getTitle()));
        income.setContent(Prompt.input("내용(%s)\n", income.getContent()));
        System.out.println("변경하였습니다.");

    }
    public void deleteIncome(){
        int incomeNo = Prompt.inputInt("게시판 번호?");
        Income deletedIncome = (Income) boardList.get(boardList.indexOf(new Income(incomeNo)));
        if (deletedBoard != null) {
            boardList.remove(boardList.indexOf(deletedBoard));
            System.out.printf("'%s' 게시판 삭제했습니다.\n", deletedBoard.getTitle());
        } else
            System.out.println("없는 게시판입니다.");
    }

}
