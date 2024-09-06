package hello.hello_spring.service;

import hello.hello_spring.entity.Board;
import hello.hello_spring.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired // 디펜던시 인젝션
    private BoardRepository boardRepository;


    public void write(Board board) {

        boardRepository.save(board);
    }

    public List<Board> boardList() {

        return boardRepository.findAll(); // 연결된 DB에 있는 데이터를 모두 해당 타입의 List 형태로 반환하는 함수.
    }

    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }
}
