package hello.hello_spring.Controller;

import hello.hello_spring.entity.Board;
import hello.hello_spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //게시글 상세 페이지 조회
    @GetMapping("/board/list")
    public String boardList(Model model) { // 모델객체는 데이터를 뷰파일로 넘겨주는 역할을 한다.

        // Model은 스프링이 지원하는 기능으로써 , key와 value로 이루어져 있는 HashMap 이다.
        // model.addAttribute()를 통해 view에 전달할 데이터를 저장한다.
        // 모델 객체에 첫번째 파라미터 이름으로 2번째 파라미터를 저장한다
        // mode.addAttribute()는 return에 명시되어있는 뷰파일에 list 데이터를 넘겨준다.
        // boardlist.html 에서 ${list} 라는 영역에 저 데이터를 넣어준다는 것이다.
        // 즉 첫번째 인자랑 뷰파일이랑 사용하는 변수명(?)이 무조건 일치해야 넘겨줄 수 있다.
        model.addAttribute("list",boardService.boardList());

        return "boardlist";
    }

    @GetMapping("/board/view") // /board/view?id=1 로 들어오면 이 메서드에 id 인자값에 1이 들어감
    public String boardView(Model model, @RequestParam(name= "id") Integer id) {
            //public String boardView(Model model, Integer id) 참고 코드는 이렇게만 해도 실행이 되었으나
            //White label 오류가 나와 구글링 후 현재처럼 리퀘스트파라미터 어노테이션을 사용했더니 되었음.
            //개발환경 버전에 따라 컴파일러가 잘못 매칭시키거나 못찾는 경우가 많으니 어노테이션으로 명확히 파라미터를 맵핑시켜주는게 좋다.
        //RequestParam 어노테이션은 괄호 안에 name 속성을 이용한다. 클라이언트가 맵핑된 URL로 들어온 후 인자값으로 id를 넘겨준다
        //클라이언트가 게시글을 클릭하면 boardlist.html 에서 th:href 기능으로 인해 /board/view?id=1 이런식으로 넘어온다.
        //URL에서 '?'의 의미는 쿼리문자열이라는 것을 뜻하며 간단하게 view URL에서 추가적인 파라미터를 넘긴다고 보면 된다.


        model.addAttribute("board",boardService.boardView(id));
        return "boardview";
    }

}