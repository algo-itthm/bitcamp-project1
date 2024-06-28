package bitcamp.project1.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Income {
    private int no;
    private String date; // 날짜
    private int amount; // 금액
    private String content; // 항목
    private static int seqNo;
    private Category category;

    public static int getSeqNo() {
        return ++seqNo;
    }

    public static void setSeqNo(int seqNo) {
        Income.seqNo = seqNo;
    }

    public Income() {}

    public Income(int no, String date) {
        this.no = no;
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Income income = (Income) object;
        return no == income.no;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
