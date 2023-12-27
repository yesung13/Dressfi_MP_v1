package com.jang.dressfi.controller;

import com.jang.dressfi.model.*;
import com.jang.dressfi.service.BoardService;
import com.jang.dressfi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired // @Resource(name = "boardService") ?��존�?�? 주입
    private BoardService boardService;// ?��존�?�? ?��?��

    @Autowired //
    private LoginService loginService; //
    // file upload path
    private String uploadPath = "C:\\Users\\Admin\\Desktop\\Dressfi_MP(1022)\\src\\main\\webapp\\resources\\img";


    @RequestMapping(value = "/adminboard.do", method = RequestMethod.GET)
    public String board(Model model) throws Exception {
        List<BoardVO> board = this.boardService.getADBoardList();
        model.addAttribute("board", board);
        return "/board/admin_Boardlist";
    }

    @RequestMapping("/deleteb.do")
    public String adminDelete(@RequestParam(value = "bno") int bno, RedirectAttributes rea, BoardVO boardVO) {
        BoardVO board = boardService.getArticle(bno);
        List<ReplyVO> reply = boardService.getReplyList(bno);
        if (reply.size() > 0) {
            boardService.deleteReplyBybno(bno);
        }
        List<AttFileVO> files = boardService.getFileList(bno);
        if (files.size() > 0) {
            for (AttFileVO filedel : files) {
                String f_stor_all = filedel.getOfilename();
            }
            boardService.deleteFileBybno(bno);
        }
        boardService.deleteArticle(bno);
        return "redirect:/admin/adminboard.do";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    // 받아?�� url값을 searchVO�? �??��?��?��
    public String listPage(@ModelAttribute("searchVO") SearchVO searchVO, Model model, HttpSession session) throws Exception {

        List<BoardVO> blist = boardService.getBoardList(searchVO);
        model.addAttribute("boardList", blist);

        StringBuffer pageUrl = boardService.getPageUrl(searchVO);
        model.addAttribute("pageHttp", pageUrl);

        model.addAttribute("userId", session.getAttribute("userId"));// 추후 개발?���? ?��?��
        model.addAttribute("searchVO", searchVO);// 추후 개발?���? ?��?��

        return "board/list";
    }

    @RequestMapping(value = "/write.do", method = RequestMethod.GET)
    public String boardWrite() {
        return "/board/write";
    }

    // �??���?
    @RequestMapping(value = "/write.do", method = RequestMethod.POST)
    public String boardWriteProc(@ModelAttribute("Board") BoardVO board, MultipartHttpServletRequest request) {
        // ?�� �????��
        String content = board.getContent().replaceAll("\r\n", "<br />");// java?���? 코드 HTML줄바꾸기�?
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("\"", "&quot;");

        board.setContent(content);
        boardService.writeArticle(board);
        int bno = board.getBno(); // ???��?�� ?��?��?�� ?���?번호 xml ?��?��insert ?��?�� keyProperty="bno" ?��
        // ?��?��?�� ?��?��?��

        AttFileVO file = new AttFileVO();
        String uploadPath = "C:\\Users\\Admin\\Desktop\\Dressfi_MP(1022)\\src\\main\\webapp\\resources\\img\\";
        List<MultipartFile> fileList = request.getFiles("file");

        for (MultipartFile mf : fileList) {
            if (!mf.isEmpty()) {
                String originFileName = mf.getOriginalFilename(); // ?���? ?��?�� �?
                long fileSize = mf.getSize(); // ?��?�� ?��?���?

                System.out.println("originFileName : " + originFileName);
                System.out.println("fileSize : " + fileSize);

                //				file.setBno(bno);
                file.setOfilename(originFileName);
                file.setSfilename(originFileName);
                file.setFilesize(fileSize);

                boardService.insertFile(file);// ?��?��블에 ?��?�� ?���? ???��

                String safeFile = uploadPath + originFileName; // ?��?��?��?�� ?��?�� ???��

                try {
                    mf.transferTo(new File(safeFile)); // ?��?��?��?�� ?��?�� ???��
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:list.do";
    }

    // �?보기
    @RequestMapping("/view")
    public String boardView(@RequestParam(value = "bno", required = false, defaultValue = "0") int bno, HttpSession session, Model model) throws Exception {

        String userId = (String) session.getAttribute("userId");

        BoardViewVO boardViewVO = new BoardViewVO(); // �?조회 ?��?�� ?��
        boardViewVO.setBno(bno);

        if (userId != null) { // ?��?��?�� 경우
            boardViewVO.setUserId(userId);
            boardViewVO.setMem_yn('y'); // ?��?��
            if (boardService.getBoardView(boardViewVO) == 0) {// ?��?�� 번호 �??�� ?��?? 기록?�� ?��?���?
                boardService.increaseViewCnt(bno); // 조회?�� 갱신
                boardService.addBoardView(boardViewVO); // ?��?�� 조회 ?���?
            }
        } else { // 비회?��?�� 경우
            boardViewVO.setUserId(session.getId()); // ?��?��id�? ?��?�� id�? ?���?
            boardViewVO.setMem_yn('n'); // 비회?��
            if (boardService.getBoardView(boardViewVO) == 0) {
                {
                    // ?��?�� 번호 �??�� ?��?? 기록?�� ?��?���?
                    boardService.increaseViewCnt(bno); // 조회?�� 갱신
                    boardService.addBoardView(boardViewVO);// 비회?�� 조회?�� ?���?
                }
            }
        }
        BoardVO board = boardService.getArticle(bno); // get selected article model

        List<ReplyVO> reply = boardService.getReplyList(bno); // ?���? 목록 ?��?�� ?���? ?? list
        List<AttFileVO> fileList = boardService.getFileList(bno); // 첨�??��?�� 목록 ?��?�� ?���?-list

        model.addAttribute("board", board);
        model.addAttribute("replyList", reply);
        model.addAttribute("fileList", fileList);

        return "board/view";
    }

    @RequestMapping(value = "/modify.do", method = RequestMethod.GET)
    public String boardModify(HttpServletRequest request, HttpSession session, Model model) {

        String userId = (String) session.getAttribute("userId");
        int bno = Integer.parseInt(request.getParameter("bno"));

        BoardVO board = boardService.getArticle(bno);
        // <br /> tag change to new line code
        String content = board.getContent().replaceAll("<br />", "\r\n");
        board.setContent(content);

        if (!userId.equals(board.getWriterId())) { // 비회?�� �??��?�� 불�?
            model.addAttribute("errCode", "1");
            model.addAttribute("bno", bno);
            return "redirect:view.do";
        } else {// ?��?�� �??��?��
            List<AttFileVO> fileList = boardService.getFileList(bno); // 첨�??��?�� ?��?�� ?���? - list

            model.addAttribute("board", board);
            model.addAttribute("fileList", fileList);
            return "/board/modify";
        }
    }

    @RequestMapping(value = "/modify.do", method = RequestMethod.POST) // 게시?�� �? ?��?��
    public String boardModifyProc(@ModelAttribute("Board") BoardVO board, MultipartHttpServletRequest request, RedirectAttributes rea) {

        String content = board.getContent().replaceAll("\r\n", "<br />"); // java ?���? 코드 HTML줄바꾸기�?
        board.setContent(content);
        boardService.updateArticle(board);
        int bno = board.getBno();
        // 체크?�� ?��?��?�� ?��?��블과 ?��?��?��?��?�� ?��?��?��?��.
        String[] fileno = request.getParameterValues("fileno");
        if (fileno != null) {
            for (String fn : fileno) {
                int fno = Integer.parseInt(fn);
                String oFileName = boardService.getFileName(fno);
                String safeFile = uploadPath + oFileName;
                File removeFile = new File(safeFile);// remove disk uploaded file
                removeFile.delete();
                boardService.deleteFile(fno); // remove table uploaded file
            }
        }
        AttFileVO file = new AttFileVO();
        // ?��첨�? ?��?�� 목록 ?��?��?���?
        List<MultipartFile> fileList = request.getFiles("file");
        for (MultipartFile mf : fileList) {
            if (!mf.isEmpty()) {
                String originFileName = mf.getOriginalFilename(); // ?��첨�??��?�� ?���? ?��?�� �?
                long fileSize = mf.getSize(); // ?��?�� ?��?���?
                //				file.setBno(bno);
                file.setFilesize(fileSize);
                file.setOfilename(originFileName);
                file.setSfilename(originFileName);
                boardService.insertFile(file); // ?��?��블에 ?��?�� ???��
                String safeFile = uploadPath + originFileName;
                try {
                    mf.transferTo(new File(safeFile)); // ?��?��?��?�� ?��?�� ???��
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        rea.addAttribute("bno", board.getBno());
        return "redirect:/board/view.do";
    }

    @RequestMapping("/delete.do")
    public String boardDelete(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
        String userId = (String) session.getAttribute("userId"); // login 개발?�� ?��?��
        int bno = Integer.parseInt(request.getParameter("bno"));

        BoardVO board = boardService.getArticle(bno);

        String setView = "";
        if (userId.equals(board.getWriterId())) { // ?���? ?��?��
            List<ReplyVO> reply = boardService.getReplyList(bno);
            if (reply.size() > 0) {
                boardService.deleteReplyBybno(bno);
            }
            // 첨�? ?��?���? ?��?��, ?��?�� ?��?�� ?��?��
            List<AttFileVO> files = boardService.getFileList(bno);
            if (files.size() > 0) {
                // ???��?�� ?��?�� ?��?�� ?��?��
                for (AttFileVO filedel : files) {
                    String f_stor_all = filedel.getOfilename();
                    File f = new File(session.getServletContext().getRealPath("/") + f_stor_all);
                    f.delete();
                }
                boardService.deleteFileBybno(bno); // ?��?��블에?�� ?��?�� 번호 �? 첨�? file ?���? ?��?��
            } // board ?��?��
            boardService.deleteArticle(bno);
            setView = "redirect:list.do";
        } else {
            rea.addAttribute("errCode", "1");// it's forbidden connection
            rea.addAttribute("bno", bno);
            setView = "redirect:view.do";
        }
        return setView;
    }

    // �? 추천
    @RequestMapping("/recommend.do")
    public String updateRecommendCnt(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
        int bno = Integer.parseInt(request.getParameter("bno"));
        String userId = (String) session.getAttribute("userId");

        if (userId == null) { // 비회?��?�� 경우
            rea.addAttribute("bno", bno);
            rea.addAttribute("errCode", "4"); // 추천 x
            return "redirect:/board/view.do";
        }
        BoardLikeVO boardLike = new BoardLikeVO();
        boardLike.setBno(bno);
        boardLike.setUserId(userId);

        BoardVO board = boardService.getArticle(bno);

        if (board.getWriterId().equals(userId)) {
            rea.addAttribute("errCode", "3"); // 본인 �??? 추천 불�?
        } else {
            if (boardService.getBoardLike(boardLike) == 0) {
                // ?���? 추천?�� 기록?�� ?��?���?
                boardService.incrementGoodCnt(bno); // ???
                boardService.addBoardLike(boardLike); // 추천 기록 ?���?
            } else {
                rea.addAttribute("errCode", "2"); // ?���? 추천?��?�� �??���? 추천 불�?
            }
        }
        rea.addAttribute("bno", bno);
        return "redirect:/board/view.do";
    }

    // ?���? ?��?��
    @RequestMapping("/writeReply.do")
    public String replyWriteProc(@ModelAttribute("reply") ReplyVO reply, RedirectAttributes rea) {
        if (reply.getContent().isEmpty()) {
            rea.addAttribute("errCode", "1");
        } else {
            String content = reply.getContent().replaceAll("<", "&lt;");
            content = reply.getContent().replaceAll(">", "&gt;");
            content = reply.getContent().replaceAll("&", "&amp;");
            content = reply.getContent().replaceAll("\"", "&quot;");
            content = reply.getContent().replaceAll("\r\n", "<br />");
            reply.setContent(content);
            boardService.writeReply(reply);
        }
        rea.addAttribute("bno", reply.getBno());
        return "redirect:view.do";
    }

    // ?���? ?��?��
    @RequestMapping("/deleteReply.do")
    public String commentDelete(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
        int rno = Integer.parseInt(request.getParameter("rno"));
        int bno = Integer.parseInt(request.getParameter("bno"));
        String userId = (String) session.getAttribute("userId");
        ReplyVO reply = boardService.getReply(rno);
        if (!userId.equals(reply.getWriterId())) {
            rea.addAttribute("errCode", "1");
        } else {
            boardService.deleteReply(rno);
        }
        rea.addAttribute("bno", bno); // move back to the article
        return "redirect:view.do";
    }

    // ?���? 추천
    @RequestMapping("/recommendReply.do")
    public String RecommendRely(HttpServletRequest request, HttpSession session, RedirectAttributes rea) {
        int bno = Integer.parseInt(request.getParameter("bno"));
        int rno = Integer.parseInt(request.getParameter("rno"));
        String userId = (String) session.getAttribute("userId");

        if (userId == null) { // 로그?�� x?�� 추천 x
            rea.addAttribute("bno", bno);
            rea.addAttribute("errCode", "4");
            return "redirect:/board/view.do";
        }

        ReplyLikeVO replyLike = new ReplyLikeVO();
        replyLike.setRno(rno);
        replyLike.setUserId(userId);

        ReplyVO reply = boardService.getReply(rno);

        System.out.println(userId);
        System.out.println(reply.getWriterId());

        if (reply.getWriterId().equals(userId)) { // ?��?��?�� �??�� 경우
            rea.addAttribute("errCode", "3"); // ?��?��?�� �??? 추천 x
        } else { // ?��?��?�� �??�� ?��?�� 경우
            if (boardService.getReplyLike(replyLike) == 0) {
                // ?���? 추천?�� 기록?�� ?��?�� 경우
                boardService.incReplyGoodCnt(rno);
                boardService.addReplyLike(replyLike);
            } else { // ?���? 추천?�� ?�� 경우
                rea.addAttribute("errCode", "2");
            }
        }
        rea.addAttribute("bno", bno);
        return "redirect:/board/view.do";
    }

    @RequestMapping(value = "filedown")
    @ResponseBody
    public byte[] downProcess(HttpServletResponse response, @RequestParam String fileName) throws IOException {
        File file = new File("C:\\Users\\Admin\\Desktop\\jaubbs19\\src\\main\\webapp\\resources\\img\\" + fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(file); // SPRING 5.0 ?��?��

        String fn = new String(file.getName().getBytes("utf-8"), "iso_8859_1");

        response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
        response.setContentLength(bytes.length);
        return bytes;
    }

    // Community 카테고리 부분
    @RequestMapping(value = "/knowhow.do", method = RequestMethod.GET)
    public String knowhow(Model model, HttpSession session) throws Exception {
        // model.addAttribute("user", new User());
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            UserVO loginuser = this.loginService.getUser(userId);
            String mno = loginuser.getMno(); // 뷰 테이블 mno 변수 받기
            String mmno = mno.substring(0, 1);

            model.addAttribute("userId", loginuser.getUserId());
        } else {
            return "redirect:/member/login.do";
        }
        return "/community/knowhow";
    }

    @RequestMapping(value = "/getKnowhow.do", method = RequestMethod.POST)
    public @ResponseBody ArrayList<Map<String, Object>> getKnowhow(@Valid UserVO userVO, BindingResult bindingResult, HttpSession session, @RequestParam Map<String, Object> item) {

        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        result = boardService.selectKnowhow(item);
        return result;
    }

    @RequestMapping(value = "/qanda.do", method = RequestMethod.GET)
    public String qanda(Model model) throws Exception {
        // model.addAttribute("user", new User());
        return "/community/qanda";
    }


}