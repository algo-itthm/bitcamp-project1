package bitcamp.project1.vo;

import java.util.Objects;

public class Income {
    private int no;
    private String date; //날짜
    private int amount; //금액
    private Category category; //분류
    private String content; //항목
    private static int seqNo;

    public static int getSeqNo() {
        return ++seqNo;
    }

    public static void setSeqNo(int seqNo) {
        Income.seqNo = seqNo;
    }

    public Income() {

    }

    public Income(int no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Income income = (Income) object;
        return no == income.no && amount == income.amount && Objects.equals(category, income.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, amount, category);
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


}
