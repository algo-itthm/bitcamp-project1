package bitcamp.project1.command;

import bitcamp.project1.util.Highlight;
import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Category;
import bitcamp.project1.vo.Expense;

public class ExpenseCommand {
    CategoryCommand categoryCommand;
    LinkedList expenseList = new LinkedList();
    LinkedList categoryList;

    public ExpenseCommand(CategoryCommand categoryCommand) {
        this.categoryCommand = categoryCommand;
    }

    public void executeExpenseCommand(String command) {
        Highlight.menuHighlight(command, "blue");
        categoryList = categoryCommand.getExpenseCategoryList();
        switch (command) {
            case "등록":
                this.addExpense();
                break;
            case "목록":
                this.listExpense();
                break;
            case "조회":
                this.viewExpense();
                break;
            case "변경":
                this.updateExpense();
                break;
            case "삭제":
                this.deleteExpense();
                break;
        }
    }

    private void addExpense() {
        if(categoryList.size() == 0) {
            System.out.println("카테고리 등록이 필요합니다.");
            return;
        }

        Expense expense = new Expense();

        String date = Prompt.input("날짜?");
        while(date.length() != 8) {
            System.out.println("8자리로 입력해주세요.");
            date = Prompt.input("날짜?");
        }
        expense.setDate(date);

        expense.setAmount(Prompt.inputInt("금액?"));

        printExpenseCategory();
        int categoryIndex = Prompt.inputInt("분류?") - 1;
        Category category = (Category) categoryList.get(categoryIndex);
        if(category == null) {
            System.out.println("없는 카테고리입니다.");
            return;
        }
        expense.setCategory(category);

        expense.setContent(Prompt.input("항목?"));
        expense.setNo(Expense.getNextSeqNo());
        expenseList.add(expense);
        System.out.println("등록되었습니다.");
    }

    private void listExpense() {
        System.out.println("번호  날짜\t\t금액\t\t분류\t항목");
        for (Object obj : expenseList.toArray()) {
            Expense expense = (Expense) obj;
            System.out.printf("%d\t  %s\t%,d원\t%s\t%s\n", expense.getNo(), expense.getDate(),
                expense.getAmount(), expense.getCategory().getTitle(), expense.getContent());
        }
    }

    private void viewExpense() {
        String expenseDate = Prompt.input("날짜?");

        if (isValidateLength(expenseDate) == false || printExpensesByDate(expenseDate) == 0) {
            return;
        }

        while (true) {
            int expenseNo = Prompt.inputInt("번호(0은 이전)?");
            if (expenseNo == 0) {
                return;
            }

            Expense expense =
                (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo, expenseDate)));
            if (expense == null) {
                System.out.println("없는 지출입니다.");
                return;
            }

            System.out.printf("날짜: %s\n", expense.getDate());
            System.out.printf("금액: %,d원\n", expense.getAmount());
            System.out.printf("분류: %s\n", expense.getCategory().getTitle());
            System.out.printf("항목: %s\n", expense.getContent());
        }
    }

    private void updateExpense() {
        String expenseDate = Prompt.input("날짜?");

        if (isValidateLength(expenseDate) == false || printExpensesByDate(expenseDate) == 0) {
            return;
        }

        while (true) {
            int expenseNo = Prompt.inputInt("번호(0은 이전)?");
            if (expenseNo == 0) {
                return;
            }

            Expense oldExpense =
                (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo, expenseDate)));
            Expense newExpense = new Expense();
            if (oldExpense == null) {
                System.out.println("없는 지출입니다.");
                return;
            }

            newExpense.setAmount(Prompt.inputInt("금액(%,d원)?", oldExpense.getAmount()));

            printExpenseCategory();
            int categoryIndex = Prompt.inputInt("분류(%s)?", oldExpense.getCategory().getTitle()) - 1;
            Category category = (Category) categoryList.get(categoryIndex);
            if(category == null) {
                System.out.println("없는 카테고리입니다.");
                return;
            }
            newExpense.setCategory(category);
            newExpense.setContent(Prompt.input("항목(%s)?", oldExpense.getContent()));

            oldExpense.setAmount(newExpense.getAmount());
            oldExpense.setCategory(newExpense.getCategory());
            oldExpense.setContent(newExpense.getContent());

            System.out.println("변경 했습니다.");
        }
    }

    private void deleteExpense() {
        String expenseDate = Prompt.input("날짜?");

        if (isValidateLength(expenseDate) == false || printExpensesByDate(expenseDate) == 0) {
            return;
        }

        while (true) {
            int expenseNo = Prompt.inputInt("번호(0은 이전)?");
            if (expenseNo == 0) {
                return;
            }

            Expense deletedExpense =
                (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo, expenseDate)));

            if (deletedExpense != null) {
                expenseList.remove(expenseList.indexOf(deletedExpense));
                System.out.printf("%d번 지출을 삭제 했습니다.\n", deletedExpense.getNo());
            } else {
                System.out.println("없는 지출입니다.");
            }
        }
    }

    public LinkedList getExpenseList() {
        return expenseList;
    }

    private int printExpensesByDate(String expenseDate) {
        int count = 0;
        for (Object obj : expenseList.toArray()) {
            Expense expense = (Expense) obj;
            if (expense.getDate().equals(expenseDate)) {
                if (count == 0) {
                    System.out.println("번호\t항목\t\t금액");
                }
                count++;
                System.out.printf("%d\t\t%s\t\t%,d원\n",
                    expense.getNo(), expense.getCategory().getTitle(), expense.getAmount());
            }
        }

        if (count == 0) {
            System.out.println("없는 날짜입니다.");
        }

        return count;
    }

    private void printExpenseCategory() {
        int count = 1;
        for (Object obj : categoryList.toArray()) {
            Category category = (Category) obj;
            System.out.printf("%d. %s\n", count++, category.getTitle());
        }
    }

    private boolean isValidateLength(String expenseDate) {
        if(expenseDate.length() != 8) {
            System.out.println("8자리로 입력하세요.");
            return false;
        }
        return true;
    }
}
