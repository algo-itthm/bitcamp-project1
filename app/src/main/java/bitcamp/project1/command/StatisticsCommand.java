package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Category;
import bitcamp.project1.vo.Expense;
import bitcamp.project1.vo.Income;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatisticsCommand {

  LinkedList incomeList;
  LinkedList expenseList;
  CategoryCommand categoryCommand;

  public StatisticsCommand(LinkedList incomeList, LinkedList expenseList, CategoryCommand categoryCommand) {
    this.incomeList = incomeList;
    this.expenseList = expenseList;
    this.categoryCommand = categoryCommand;
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

  private void getTransactionByCategory() {
    LinkedList incomeCategoryList = categoryCommand.getIncomeCategoryList();
    LinkedList expenseCategoryList = categoryCommand.getExpenseCategoryList();
    int[][] sumPerIncomeCategory = new int[incomeCategoryList.size()][2];
    int[][] sumPerExpenseCategory = new int[expenseCategoryList.size()][2];
    int incomeSum = 0;
    int expenseSum = 0;

    for(int i = 0; i < incomeList.size(); i++) {
      Income income = (Income) incomeList.get(i);
      int index = incomeCategoryList.indexOf(income.getCategory());
      sumPerIncomeCategory[index][0]++;
      sumPerIncomeCategory[index][1] += income.getAmount();
      incomeSum += income.getAmount();
    }

    for(int i = 0; i < expenseList.size(); i++) {
      Expense expense = (Expense) expenseList.get(i);
      int index = expenseCategoryList.indexOf(expense.getCategory());
      sumPerExpenseCategory[index][0]++;
      sumPerExpenseCategory[index][1] += expense.getAmount();
      expenseSum += expense.getAmount();
    }

    printHeaders(incomeSum, expenseSum, "구분 카테고리 건수 금액 (비중)");

    if(incomeSum == 0) {
      incomeSum = 1;
    }
    if(expenseSum == 0) {
      expenseSum = 1;
    }

    for(int i = 0; i < incomeCategoryList.size(); i++) {
      Category category = (Category) incomeCategoryList.get(i);
      System.out.printf("수입 %s %d건 %,d원 (%.1f%%)\n", category.getTitle(), sumPerIncomeCategory[i][0],
          sumPerIncomeCategory[i][1], (double) sumPerIncomeCategory[i][1] / incomeSum * 100);
    }
    for(int i = 0; i < expenseCategoryList.size(); i++) {
      Category category = (Category) expenseCategoryList.get(i);
      System.out.printf("지출 %s %d건 %,d원 (%.1f%%)\n", category.getTitle(), sumPerExpenseCategory[i][0],
          sumPerExpenseCategory[i][1], (double) sumPerExpenseCategory[i][1] / expenseSum * 100);
    }
  }

  private void getTransactionThisMonth() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    String thisMonth = now.format(formatter);

    int incomeSum = getIncomeSum(thisMonth);
    int expenseSum = getExpenseSum(thisMonth);

    printHeaders(incomeSum, expenseSum, "일자 구분 항목 금액");

    printIncomeByDate(thisMonth);
    printExpenseByDate(thisMonth);
  }

  private void getTransactionByPeriod(String format, int len) {
    String date = Prompt.input(format);

    while(date.length() != len) {
      System.out.printf("%d자리로 입력해주세요.\n", len);
      date = Prompt.input(format);
    }

    int incomeSum = getIncomeSum(date);
    int expenseSum = getExpenseSum(date);

    printHeaders(incomeSum, expenseSum, "일자 구분 항목 금액");

    printIncomeByDate(date);
    printExpenseByDate(date);
  }

  private void printIncomeByDate(String date) {
    for (Object obj : incomeList.toArray()) {
      Income income = (Income) obj;
      if (income.getDate().startsWith(date)) {
        System.out.printf("%s %s %s %,d원\n",
            income.getDate(), "수입", "테스트", income.getAmount());
      }
    }
  }

  private int getIncomeSum(String date) {
    int sum = 0;

    for (Object obj : incomeList.toArray()) {
      Income income = (Income) obj;
      if (income.getDate().startsWith(date)) {
        sum += income.getAmount();
      }
    }

    return sum;
  }

  private void printExpenseByDate(String date) {
    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if (expense.getDate().startsWith(date)) {
        System.out.printf("%s %s %s %,d원\n",
            expense.getDate(), "지출", expense.getCategory().getTitle(), expense.getAmount());
      }
    }
  }

  private int getExpenseSum(String date) {
    int sum = 0;

    for (Object obj : expenseList.toArray()) {
      Expense expense = (Expense) obj;
      if (expense.getDate().startsWith(date)) {
        sum += expense.getAmount();
      }
    }

    return sum;
  }

  private void printHeaders(int incomeSum, int expenseSum, String titles) {
    System.out.printf("총수입 : %,d원\n", incomeSum);
    System.out.printf("총지출 : %,d원\n", expenseSum);
    System.out.printf("합계 : %,d원\n", incomeSum - expenseSum);
    System.out.println("-------------------------");
    System.out.println(titles);
  }
}
