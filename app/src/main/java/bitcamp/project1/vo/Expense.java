package bitcamp.project1.vo;

public class Expense {
  private int no;
  private String date;
  private int amount;
//  private Category category;
  private String content;

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

//  public Category getCategory() {
//    return category;
//  }
//
//  public void setCategory(Category category) {
//    this.category = category;
//  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
