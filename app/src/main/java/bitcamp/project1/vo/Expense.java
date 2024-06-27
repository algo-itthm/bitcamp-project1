package bitcamp.project1.vo;

import java.util.Objects;

public class Expense {
  private int no;
  private String date;
  private int amount;
  private Category category;
  private String content;

  public Expense() {

  }

  public Expense(int no) {
    this.no = no;
  }

  private static int seqNo;

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Expense expense = (Expense) o;
    return no == expense.no;
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, date);
  }
}
