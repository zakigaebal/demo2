package com.example.demo.controller;

import com.example.demo.domain.CommentVO;
import com.example.demo.domain.Reply;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService mCommentService;


//    @RequestMapping("/list") //댓글 리스트
//    @ResponseBody
//    private List<CommentVO> mCommentServiceList(Model model) throws Exception{
//
//        return mCommentService.commentListService();
//    }

    @RequestMapping("/list") // comment list
    @ResponseBody
    private List<CommentVO> mcommentServiceList(int bno) throws Exception {
        return mCommentService.commentListService(bno);
    }

    @RequestMapping("/insert") //댓글 작성
    @ResponseBody
    private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content) throws Exception{

        CommentVO comment = new CommentVO();
        comment.setBno(bno);

        comment.setContent(content);
        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
        comment.setWriter("test");

        return mCommentService.commentInsertService(comment);
    }

    @PostMapping("/cinsert")
    public String writeReply(
            @RequestParam int bno, @RequestParam("replyIdx")int replyIdx, @RequestParam("content")String content) throws Exception {
            CommentVO comment= new CommentVO();
            comment.setBno(bno);
            comment.setContent(content);
            comment.setReplyIdx(replyIdx);
            comment.setWriter("test2");
            mCommentService.commentInsertService(comment);
        return "redirect:/detail/" + bno;
    }


    @RequestMapping("/update") //댓글 수정
    @ResponseBody
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{

        CommentVO comment = new CommentVO();
        comment.setCno(cno);
        comment.setContent(content);

        return mCommentService.commentUpdateService(comment);
    }
    @RequestMapping("/delete/{cno}") //댓글 삭제
    @ResponseBody
    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
        return mCommentService.commentDeleteService(cno);
    }

}


