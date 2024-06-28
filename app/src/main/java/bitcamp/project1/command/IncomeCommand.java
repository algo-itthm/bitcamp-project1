package bitcamp.project1.command;

import bitcamp.project1.util.Highlight;
import bitcamp.project1.util.LinkedList;
import bitcamp.project1.vo.Category;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Income;

public class IncomeCommand {
    CategoryCommand categoryCommand;
    LinkedList incomeList = new LinkedList();
    LinkedList categoryList;

    public IncomeCommand() {

    }
    public IncomeCommand(CategoryCommand categoryCommand) {
        this.categoryCommand = categoryCommand;
    }

    public void executeIncomeCommand(String command) {
        Highlight.menuHighlight(command, "blue");
        categoryList = categoryCommand.getIncomeCategoryList();
        switch (command) {
            case "등록":
                this.addIncome();
                break;
            case "조회":
                this.viewIncome();
                break;
            case "목록":
                this.listIncome();
                break;
            case "변경":
                this.updateIncome();
                break;
            case "삭제":
                this.deleteIncome();
                break;
        }
    }

    private void addIncome() {
        if(categoryList.size() == 0) {
            System.out.println("카테고리 등록이 필요합니다.");
            return;
        }

        Income income = new Income();
        String date = Prompt.input("날짜?");
        while(date.length() != 8) {
            System.out.println("8자리로 입력해주세요.");
            date = Prompt.input("날짜?");
        }
        income.setDate(date);


        income.setAmount(Prompt.inputInt("금액?"));

        printIncomeCategory();
        int categoryIndex = Prompt.inputInt("분류?") - 1;
        Category category = (Category) categoryList.get(categoryIndex);
        if(category == null) {
            System.out.println("없는 카테고리입니다.");
            return;
        }
        income.setCategory(category);

        income.setContent(Prompt.input("항목?"));
        income.setNo(Income.getSeqNo());
        incomeList.add(income);
        System.out.println("등록되었습니다.");
    }

    private void viewIncome() {
        String incomeDate = Prompt.input("날짜?");

        if (isValidateLength(incomeDate) == false || printIncomeDate(incomeDate) == 0) {
            return;
        }

        while (true) {
            int incomeNo = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNo == 0) {
                return;
            }

            Income income =
                    (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo, incomeDate)));
            if (income == null) {
                System.out.println("없는 수입입니다.");
                return;
            }

            System.out.printf("날짜: %s\n", income.getDate());
            System.out.printf("금액: %,d원\n", income.getAmount());
            System.out.printf("분류: %s\n", income.getCategory().getTitle());
            System.out.printf("항목: %s\n", income.getContent());
        }
    }
    private void listIncome(){
        System.out.println("번호 날짜 금액 분류 항목");
        for (Object obj : incomeList.toArray()) {
            Income income = (Income) obj;
            System.out.printf("%d %s %s %s %s\n", income.getNo(), income.getDate(), income.getAmount(), income.getCategory().getTitle(),income.getContent());
        }
    }
    private void updateIncome() {
        String incomeDate = Prompt.input("날짜?");

        if (isValidateLength(incomeDate) == false || printIncomeDate(incomeDate) == 0) {
            return;
        }

        while (true) {
            int incomeNO = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNO == 0) {
                return;
            }

            Income oldIncome =
                    (Income) incomeList.get(incomeList.indexOf(new Income(incomeNO, incomeDate)));
            Income newIncome = new Income();
            if (oldIncome == null) {
                System.out.println("없는 수입입니다.");
                return;
            }

            newIncome.setAmount(Prompt.inputInt("금액(%,d원)?", oldIncome.getAmount()));

            printIncomeCategory();
            int categoryIndex = Prompt.inputInt("분류(%s)?", oldIncome.getCategory().getTitle()) - 1;
            Category category = (Category) categoryList.get(categoryIndex);
            if(category == null) {
                System.out.println("없는 카테고리입니다.");
                return;
            }
            newIncome.setCategory(category);
            newIncome.setContent(Prompt.input("항목(%s)?", oldIncome.getContent()));

            oldIncome.setAmount(newIncome.getAmount());
            oldIncome.setCategory(newIncome.getCategory());
            oldIncome.setContent(newIncome.getContent());

            System.out.println("변경 했습니다.");
        }
    }

    private void deleteIncome() {
        String incomeDate = Prompt.input("날짜?");

        if (isValidateLength(incomeDate) == false || printIncomeDate(incomeDate) == 0) {
            return;
        }

        while (true) {
            int incomeNo = Prompt.inputInt("번호(0은 이전)?");
            if (incomeNo == 0) {
                return;
            }

            Income deletedIncome =
                    (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo, incomeDate)));

            if (deletedIncome != null) {
                incomeList.remove(incomeList.indexOf(deletedIncome));
                System.out.printf("%d번 수입을 삭제 했습니다.\n", deletedIncome.getNo());
            } else {
                System.out.println("없는 수입입니다.");
            }
        }
    }

    public LinkedList getIncomeList() {
        return incomeList;
    }

    private int printIncomeDate(String incomeDate) {
        int count = 0;
        for (Object obj : incomeList.toArray()) {
            Income income = (Income) obj;
            if (income.getDate().equals(incomeDate)) {
                if (count == 0) {
                    System.out.println("번호 항목 금액");
                }
                count++;
                System.out.printf("%d %s %,d원\n",
                        income.getNo(), income.getCategory().getTitle(), income.getAmount());
            }
        }

        if (count == 0) {
            System.out.println("없는 날짜입니다.");
        }

        return count;
    }

    private void printIncomeCategory() {
        int count = 1;
        for (Object obj : categoryList.toArray()) {
            Category category = (Category) obj;
            System.out.printf("%d. %s\n", count++, category.getTitle());
        }
    }

    private boolean isValidateLength(String incomeDate) {
        if(incomeDate.length() != 8) {
            System.out.println("8자리로 입력하세요.");
            return false;
        }
        return true;
    }
}
