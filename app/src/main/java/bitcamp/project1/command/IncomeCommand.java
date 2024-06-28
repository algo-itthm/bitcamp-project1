package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.vo.Category;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;
import bitcamp.project1.util.Prompt;

public class IncomeCommand {

    LinkedList incomeList = new LinkedList();
    LinkedList categoryList;

    public IncomeCommand(LinkedList category) {
        this.categoryList =  category;
    }

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
        addCategories(income);
        income.setContent(Prompt.input("항목?"));
        income.setNo(Expense.getNextSeqNo());
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
                        income.getNo(), income.getContent(), income.getAmount());
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
            System.out.printf("금액: %d\n", income.getAmount());
            System.out.print("분류: ");
            for (Category category : income.getCategories()) {
                System.out.printf("%s ", category.getTitle());
            }
            System.out.println();
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
                        income.getNo(), income.getContent(), income.getAmount());
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

            income.setAmount(Prompt.inputInt("금액(%d)원?", income.getAmount()));
            addCategories(income);
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
                        income.getNo(), income.getContent(), income.getAmount());
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

            incomeList.remove(incomeList.indexOf(deletedIncome));
            System.out.printf("%d번 수입을 삭제 했습니다.\n", deletedIncome.getNo());
        }
    }

    public LinkedList getIncomeList() {
        return incomeList;
    }

    private void addCategories(Income income) {
        for (Object obj : categoryList.toArray()) {
            Category category = (Category) obj;
            System.out.printf("%d %s %s\n",
                    category.getNo(), category.getTitle(), category.getTransactionType());
        }
        while (true) {
            int categoryNo = Prompt.inputInt("추가할 카테고리 번호?(종료:0)");
            if (categoryNo == 0) {
                break;
            }
            Category category = (Category) categoryList.get(categoryList.indexOf(new Category(categoryNo)));
            if (category == null) {
                System.out.println("없는 카테고리입니다.");
                continue;
            }

            if (income.getCategories().contains(category)) {
                System.out.printf("%s 은 현재 카테고리입니다.\n", category.getTitle());
                continue;
            }else{
                income.getCategories().clear();
                income.getCategories().add(category);
                System.out.printf("%s 을 추가했습니다.\n", category.getTitle());
                break;
            }

        }
    }
}
