package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.vo.Category;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.Prompt;

public class IncomeCommand {

    LinkedList incomeList = new LinkedList();
    LinkedList categoryList;
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
        String incomeDate = Prompt.input("날짜?");

        int count = 0;
        for (Object obj : incomeList.toArray()) {
            Income income = (Income) obj;
            if (income.getDate().equals(incomeDate)) {
                if (count == 0) {
                    System.out.println("번호 항목 금액");
                }
                count++;
                System.out.printf("%d %s %d\n",
                        income.getNo(), "항목", income.getAmount());
            }
        }

        if (count == 0) {
            System.out.println("없는 날짜입니다.");
            return;
        }

        while (true) {
            int incomeNo = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNo == 0) {
                return;
            }

            Income income = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
            if (income == null) {
                System.out.println("없는 수입입니다.");
                return;
            }

            System.out.printf("날짜: %s\n", income.getDate());
            System.out.printf("금액: %s\n", income.getAmount());
            System.out.printf("분류: %s\n", "테스트");
            System.out.printf("항목: %s\n", income.getContent());
        }
    }


    public void updateIncome() {
        String incomeDate = Prompt.input("날짜?");
        int count = 0;
        for (Object obj : incomeList.toArray()) {
            Income income = (Income) obj;
            if (income.getDate().equals(incomeDate)) {
                if (count == 0) {
                    System.out.println("번호 항목 금액");
                }
                count++;
                System.out.printf("%d %s %d\n",
                        income.getNo(), "항목", income.getAmount());
            }
        }
        if (count == 0) {
            System.out.println("없는 날짜입니다.");
            return;
        }

        while (true) {
            int incomeNo = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNo == 0) {
                return;
            }

            Income income = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
            if (income == null) {
                System.out.println("없는 수입입니다.");
                return;
            }

            income.setAmount(Prompt.inputInt("금액(%s)?", income.getAmount()));
//    income.setCategory(Prompt.input("분류(%s)?", income.getCategory()));
            income.setContent(Prompt.input("항목(%s)?", income.getContent()));
            System.out.println("변경 했습니다.");
        }
    }

    public void deleteIncome() {
        String incomeDate = Prompt.input("날짜?");
        int count = 0;
        for (Object obj : incomeList.toArray()) {
            Income income = (Income) obj;
            if (income.getDate().equals(incomeDate)) {
                if (count == 0) {
                    System.out.println("번호 항목 금액");
                }
                count++;
                System.out.printf("%d %s %d\n",
                        income.getNo(), "항목", income.getAmount());
            }
        }
        if (count == 0) {
            System.out.println("없는 날짜입니다.");
            return;
        }
        while (true) {
            int incomeNo = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNo == 0) {
                return;
            }

            Income deletedIncome = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
            if (deletedIncome == null) {
                System.out.println("없는 번호 입니다.");
                return;
            }

            if (deletedIncome != null) {
                incomeList.remove(incomeList.indexOf(deletedIncome));
                System.out.printf("%d번 수입을 삭제 했습니다.\n", deletedIncome.getNo());
            } else {
                System.out.println("없는 수입입니다.");
            }
        }
//    }
//    private void addCategory(Income income) {
//        while (true) {
//            int categoryNo = Prompt.inputInt("추가할 카테고리 번호?(종료:0)");
//            if (categoryNo == 0) {
//                break;
//            }
//            Category category = (Category) categoryList.get(categoryList.indexOf(new Category(categoryNo)));
//            if (category == null) {
//                System.out.println("없는 팀원입니다.");
//                continue;
//            }
//            if (income.getCategory().contains(category)) {
//                System.out.printf("%s 은 현재 카테고리입니다.\n", category.getTitle());
//                continue;
//            }
//            income.getCategory().add(category);
//            System.out.printf("%s 을 추가했습니다.\n", category.getTitle());
//        }
    }
}
