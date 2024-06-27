package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatisticsCommand {

  LinkedList incomeList;
  LinkedList expenseList;
  LinkedList categoryList;

  public StatisticsCommand(LinkedList expenseList) {
//    this.incomeList = incomeList;
    this.expenseList = expenseList;
//    this.categoryList = categoryList;
  }

  public void executeExpenseCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "이번달 조회":
        getTransactionthisMonth();
        break;
      case "월별 조회":
        getTransactionByPeriod("연월?");
        break;
      case "연도별 조회":
        getTransactionByPeriod("연도?");
        break;
      case "카테고리별 조회":
        this.getTransactionByCategory();
        break;
    }
  }

  public void getTransactionByCategory() {
    System.out.println("카테고리별 조회입니다.");
  }

  public void getTransactionthisMonth() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String formattedNow = now.format(formatter);

    printExpenseByDate(formattedNow);
  }

  public void getTransactionByPeriod(String format) {
    String date = Prompt.input(format);
    printExpenseByDate(date);
  }

  public void printIncomeByDate(String date) {

  }

  public void printExpenseByDate(String date) {
    System.out.println("번호 일자 구분 항목 금액");
    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if(expense.getDate().startsWith(date)) {
        System.out.printf("%d %s %s %s %,d원\n",
            expense.getNo(), expense.getDate(), "지출", "테스트", expense.getAmount());
      }
    }
  }
}
