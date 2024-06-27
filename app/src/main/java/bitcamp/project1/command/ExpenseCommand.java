package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Expense;

public class ExpenseCommand {
  LinkedList expenseList = new LinkedList();

  public void executeExpenseCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addExpense();
        break;
      case "조회":
//        this.viewExpense();
        System.out.println("조회");
        break;
      case "변경":
//        this.updateExpense();
        System.out.println("변경");
        break;
      case "삭제":
//        this.deleteExpense();
        System.out.println("삭제");
        break;
    }
  }

  private void addExpense() {
    Expense expense = new Expense();
    expense.setDate(Prompt.input("날짜?"));
    expense.setContent(Prompt.input("금액?"));
    expense.setContent(Prompt.input("분류?"));
    expense.setContent(Prompt.input("항목?"));
    expense.setNo(Expense.getNextSeqNo());
    expenseList.add(expense);
    System.out.println("등록되었습니다.");
  }
//
//  private void viewExpense() {
//    int boardNo = Prompt.inputInt("게시글 번호?");
//    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
//    if (board == null) {
//      System.out.println("없는 게시글입니다.");
//      return;
//    }
//
//    board.setViewCount(board.getViewCount() + 1);
//    System.out.printf("제목: %s\n", board.getTitle());
//    System.out.printf("내용: %s\n", board.getContent());
//    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
//    System.out.printf("조회수: %d\n", board.getViewCount());
//  }
//
//  private void updateExpense() {
//    int boardNo = Prompt.inputInt("게시글 번호?");
//    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
//    if (board == null) {
//      System.out.println("없는 게시글입니다.");
//      return;
//    }
//
//    board.setViewCount(board.getViewCount() + 1);
//    board.setTitle(Prompt.input("제목(%s)?", board.getTitle()));
//    board.setContent(Prompt.input("내용(%s)?", board.getContent()));
//    System.out.println("변경 했습니다.");
//  }
//
//  private void deleteExpense() {
//    int boardNo = Prompt.inputInt("게시글 번호?");
//    Board deletedBoard = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
//    if (deletedBoard != null) {
//      boardList.remove(boardList.indexOf(deletedBoard));
//      System.out.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
//    } else {
//      System.out.println("없는 게시글입니다.");
//    }
//  }
}
