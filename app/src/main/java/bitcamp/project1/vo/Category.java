package bitcamp.project1.vo;

import java.util.Objects;

public class Category {

  private static int seqNo;

  private int no;
  private String title;
  private String transactionType;


  public Category() {

  }

  public Category(int no) {
    this.no = no;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return no == category.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }


  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }


}
