package hello.hello_spring.Controller;

import hello.hello_spring.entity.Board;
import hello.hello_spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost : 8080/board/write 이 주소로 접속하면 아래 메서드 매핑
    public String boardwriteForm() {
            return "boardwrite"; // return "" 이란 어떤 View 파일로 보내줄거냐.

    }

    @PostMapping("board/writepro")
    public String boardWritePro(Board board) {

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list",boardService.boardList());

        return "boardlist";
    }

}