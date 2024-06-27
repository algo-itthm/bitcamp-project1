package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Category;
import bitcamp.project1.vo.Expense;

public class ExpenseCommand {
  LinkedList expenseList = new LinkedList();
  LinkedList categoryList;

  public ExpenseCommand() {
  }

  public ExpenseCommand(LinkedList categoryList) {
    this.categoryList = categoryList;
  }

  public void executeExpenseCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addExpense();
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
    Expense expense = new Expense();
    expense.setDate(Prompt.input("날짜?"));
    expense.setAmount(Prompt.inputInt("금액?"));

//    for (Object obj : categoryList.toArray()) {
//      Category category = (Category) obj;
//        if(category.get)
//    }

//    expense.setCategory();
    expense.setContent(Prompt.input("항목?"));
    expense.setNo(Expense.getNextSeqNo());
    expenseList.add(expense);
    System.out.println("등록되었습니다.");
  }

  private void viewExpense() {
    String expenseDate = Prompt.input("날짜?");
    System.out.println("번호 항목 금액");

    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if(expense.getDate().equals(expenseDate)) {
        System.out.printf("%d %s %d\n",
            expense.getNo(), "테스트", expense.getAmount());
      }
    }

    int expenseNo = Prompt.inputInt("번호(0은 이전)?");
    if(expenseNo == 0) {
      return;
    }

    Expense expense = (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo)));
    if (expense == null) {
      System.out.println("없는 지출입니다.");
      return;
    }

    System.out.printf("날짜: %s\n", expense.getDate());
    System.out.printf("금액: %s\n", expense.getAmount());
    System.out.printf("분류: %s\n", "테스트");
    System.out.printf("항목: %s\n", expense.getContent());
  }

  private void updateExpense() {
    String expenseDate = Prompt.input("날짜?");
    System.out.println("번호 항목 금액");

    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if(expense.getDate().equals(expenseDate)) {
        System.out.printf("%d %s %d\n",
            expense.getNo(), "테스트", expense.getAmount());
      }
    }

    int expenseNo = Prompt.inputInt("번호(0은 이전)?");
    if(expenseNo == 0) {
      return;
    }

    Expense expense = (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo)));
    if (expense == null) {
      System.out.println("없는 지출입니다.");
      return;
    }

    expense.setAmount(Prompt.inputInt("금액(%s)?", expense.getAmount()));
//    expense.setCategory(Prompt.input("분류(%s)?", expense.getCategory()));
    expense.setContent(Prompt.input("항목(%s)?", expense.getContent()));
    System.out.println("변경 했습니다.");
  }

  private void deleteExpense() {

    String expenseDate = Prompt.input("날짜?");
    System.out.println("번호 항목 금액");

    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if(expense.getDate().equals(expenseDate)) {
        System.out.printf("%d %s %d\n",
            expense.getNo(), "테스트", expense.getAmount());
      }
    }

    int expenseNo = Prompt.inputInt("번호(0은 이전)?");
    if(expenseNo == 0) {
      return;
    }

    Expense deletedExpense = (Expense) expenseList.get(expenseList.indexOf(new Expense(expenseNo)));
    if (deletedExpense == null) {
      System.out.println("없는 지출입니다.");
      return;
    }

    if (deletedExpense != null) {
      expenseList.remove(expenseList.indexOf(deletedExpense));
      System.out.printf("%d번 지출을 삭제 했습니다.\n", deletedExpense.getNo());
    } else {
      System.out.println("없는 지출입니다.");
    }
  }
}
