package bitcamp.project1.command;

import bitcamp.project1.util.LinkedList;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Category;

public class CategoryCommand {

  LinkedList boardList = new LinkedList();

  public void executeBoardCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addBoard();
        break;
      case "조회":
        this.viewBoard();
        break;
      case "목록":
        this.listBoard();
        break;
      case "변경":
        this.updateBoard();
        break;
      case "삭제":
        this.deleteBoard();
        break;
    }
  }

  private void deleteBoard() {
    int boardNo = Prompt.inputInt("카테고리 번호?");
    Category deletedCategory = (Category) boardList.get(boardList.indexOf(new Category(boardNo)));
    if (deletedCategory != null) {
      boardList.remove(boardList.indexOf(deletedCategory));
      System.out.printf("%d번 카테고리을 삭제 했습니다.\n", deletedCategory.getNo());
    } else {
      System.out.println("없는 카테고리입니다.");
    }
  }

  private void updateBoard() {
    int boardNo = Prompt.inputInt("카테고리 번호?");
    Category category = (Category) boardList.get(boardList.indexOf(new Category(boardNo)));
    if (category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }

    category.setTitle(Prompt.input("카테고리명(%s)?", category.getTitle()));
    System.out.println("변경 했습니다.");
  }

  private void viewBoard() {
    int boardNo = Prompt.inputInt("카테고리 번호?");
    Category category = (Category) boardList.get(boardList.indexOf(new Category(boardNo)));
    if (category == null) {
      System.out.println("없는 카테고리입니다.");
      return;
    }

    System.out.printf("카테고리명 : %s\n", category.getTitle());
  }

  private void listBoard() {
    System.out.println("번호 카테고리명");
    for (Object obj : boardList.toArray()) {
      Category category = (Category) obj;
      System.out.printf("%d %s\n",
          category.getNo(), category.getTitle());
    }
  }

  private void addBoard() {
    Category category = new Category();
    category.setTitle(Prompt.input("카테고리명?"));
    category.setNo(Category.getNextSeqNo());
    boardList.add(category);
  }

}
