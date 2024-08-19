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

        return boardRepository.findAll();
    }
}
