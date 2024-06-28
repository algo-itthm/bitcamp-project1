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
        getTransactionThisMonth();
        break;
      case "월별 조회":
        getTransactionByPeriod("연월?", 6);
        break;
      case "연도별 조회":
        getTransactionByPeriod("연도?", 4);
        break;
      case "카테고리별 조회":
        this.getTransactionByCategory();
        break;
    }
  }

  public void getTransactionByCategory() {
    System.out.println("카테고리별 조회입니다.");
  }

  public void getTransactionThisMonth() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String formattedNow = now.format(formatter);

    printExpenseByDate(formattedNow);
  }

  public void getTransactionByPeriod(String format, int len) {
    String date = Prompt.input(format);

    while(date.length() != len) {
      System.out.printf("%d자리로 입력해주세요.\n", len);
      date = Prompt.input(format);
    }

    printExpenseByDate(date);
  }

  public void printIncomeByDate(String date) {

  }

  public void printExpenseByDate(String date) {
    int count = 0;
    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if (expense.getDate().startsWith(date)) {
        if (count == 0) {
          System.out.println("일자 구분 항목 금액");
        }
        count++;
        System.out.printf("%s %s %s %,d원\n",
            expense.getDate(), "지출", "테스트", expense.getAmount());
      }
    }

    if (count == 0) {
      System.out.println("없는 날짜입니다.");
    }
  }
}
