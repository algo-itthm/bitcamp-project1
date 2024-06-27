package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.Prompt;

public class IncomeCommand {

    LinkedList incomeList = new LinkedList();

    public void executeIncomeCommand(String command) {
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


    public void addIncome() {
        Income income = new Income();
        income.setDate(Prompt.input("날짜?"));
        income.setAmount(Prompt.inputInt("금액?"));
//        income.setCategory(Prompt.input("분류?"));
        income.setContent(Prompt.input("항목?"));
        income.setNo(income.getSeqNo());
        incomeList.add(income);
        System.out.println("등록했습니다.");
    }


    public void viewIncome() {
        int incomeNo = Prompt.inputInt("날짜?");
        Income income = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (income == null) {
            System.out.println("없는 날짜입니다..");
            return;
        }
        System.out.printf("날짜 : %s\n", income.getDate());
//        System.out.printf("구분 : %s\n");
        System.out.printf("금액 : %s\n", income.getAmount());
        System.out.printf("분류 : %s\n", income.getAmount());
        System.out.printf("항목 : %s\n", income.getContent());
    }



    public void updateIncome() {
        int incomeNo = Prompt.inputInt("날짜?");
        Income income = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (income == null) {
            System.out.println("없는 날짜입니다.");
            return;
        }
//        income.setCategory(Prompt.input("분류(%s)\n", income.getCategory()));

        income.setContent(Prompt.input("항목(%s)\n", income.getContent()));

        System.out.println("변경하였습니다.");

    }

    public void deleteIncome() {
        int incomeNo = Prompt.inputInt("날짜?");
        Income deletedIncome = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (deletedIncome != null) {
            incomeList.remove(incomeList.indexOf(deletedIncome));
            System.out.println("'%s'삭제했습니다.");
        } else
            System.out.println("없는 날짜입니다.");
    }
}
