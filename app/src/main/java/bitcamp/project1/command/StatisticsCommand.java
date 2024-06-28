package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatisticsCommand {

  LinkedList incomeList;
  LinkedList expenseList;

  public StatisticsCommand(LinkedList incomeList, LinkedList expenseList) {
    this.incomeList = incomeList;
    this.expenseList = expenseList;
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

    int incomeSum = getIncomeSum(formattedNow);
    int expenseSum = getExpenseSum(formattedNow);

    System.out.printf("총수입 : %d원\n", incomeSum);
    System.out.printf("총지출 : %d원\n", expenseSum);
    System.out.printf("합계 : %d원\n", incomeSum - expenseSum);
    System.out.println("-------------------------");
    System.out.println("일자 구분 항목 금액");

    printIncomeByDate(formattedNow);
    printExpenseByDate(formattedNow);
  }

  public void getTransactionByPeriod(String format, int len) {
    String date = Prompt.input(format);

    while(date.length() != len) {
      System.out.printf("%d자리로 입력해주세요.\n", len);
      date = Prompt.input(format);
    }

    int incomeSum = getIncomeSum(date);
    int expenseSum = getExpenseSum(date);

    System.out.printf("총수입 : %,d원\n", incomeSum);
    System.out.printf("총지출 : %,d원\n", expenseSum);
    System.out.printf("합계 : %,d원\n", incomeSum - expenseSum);
    System.out.println("-------------------------");
    System.out.println("일자 구분 항목 금액");

    printIncomeByDate(date);
    printExpenseByDate(date);
  }

  public void printIncomeByDate(String date) {
    for (Object obj : incomeList.toArray()) {
      Income income = (Income) obj;
      if (income.getDate().startsWith(date)) {
        System.out.printf("%s %s %s %,d원\n",
            income.getDate(), "수입", "테스트", income.getAmount());
      }
    }
  }

  public int getIncomeSum(String date) {
    int sum = 0;

    for (Object obj : incomeList.toArray()) {
      Income income = (Income) obj;
      if (income.getDate().startsWith(date)) {
        sum += income.getAmount();
      }
    }

    return sum;
  }

  public void printExpenseByDate(String date) {
    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if (expense.getDate().startsWith(date)) {
        System.out.printf("%s %s %s %,d원\n",
            expense.getDate(), "지출", expense.getCategory().getTitle(), expense.getAmount());
      }
    }
  }

  public int getExpenseSum(String date) {
    int sum = 0;

    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if (expense.getDate().startsWith(date)) {
        sum += expense.getAmount();
      }
    }

    return sum;
  }
}
