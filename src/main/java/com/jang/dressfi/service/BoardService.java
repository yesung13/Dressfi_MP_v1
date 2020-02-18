package com.jang.dressfi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jang.dressfi.model.AttFileVO;
import com.jang.dressfi.model.BoardLikeVO;
import com.jang.dressfi.model.BoardVO;
import com.jang.dressfi.model.BoardViewVO;
import com.jang.dressfi.model.ReplyLikeVO;
import com.jang.dressfi.model.ReplyVO;
import com.jang.dressfi.model.SearchVO;

public interface BoardService {

	StringBuffer getPageUrl(SearchVO seachVO);

	List<BoardVO> getBoardList(SearchVO searchVO); // 글 목록조회

	List<BoardVO> getADBoardList();
	
	BoardVO getArticle(int bno); // 글조회

	int writeArticle(BoardVO board); // 글저장

	int updateArticle(BoardVO board); // 글수정

	void deleteArticle(int bno); // 글삭제

	int getTotalRow(SearchVO searchVO); // 전체글 수 조회

	int incrementViewCnt(int bno); // 조회수 증가

	int incrementGoodCnt(int bno); // 좋아요 추가

	int incrementReplyCnt(int bno); // 댓글 수 증가

	List<ReplyVO> getReplyList(int bno); // 댓글 목록조회

	ReplyVO getReply(int rno); // 댓글 조회

	int writeReply(ReplyVO reply); // 댓글저장

	int updateReply(ReplyVO reply); // 댓글수정

	void deleteReply(int rno); // 댓글삭제

	void deletReplyBubno(int bno); // 원글에 소속된 댓글 전체 삭제

	int incReplyGoodCnt(int rno); // 댓글 좋아요 증가

	List<AttFileVO> getFileList(int bno); // 첨부파일 목록 조회

	String getFileName(int fno); // 첨부파일 이용 조회

	int insertFile(AttFileVO file); // 첨부파일 저장

	void deleteFile(int fno); // 첨부파일 삭제

	void deleteFileBybno(int bno); // 원글에 소속된 첨부파일 전체 삭제

	int addBoardLike(BoardLikeVO boardLike); // id-특정글 좋아요 기록 저장

	int getBoardLike(BoardLikeVO boardLike); // id-특정글 좋아요 기록 조회

	int addReplyLike(ReplyLikeVO replyLike); // id-특정댓글 좋아요 기록 저장

	int getReplyLike(ReplyLikeVO replyLike); // id-특정댓글 좋아요 기록 조회

	int addBoardView(BoardViewVO boardView); // id-특정글 조회이력 기록

	int getBoardView(BoardViewVO boardView); // id-특정글 조회이력 읽기
	
	int increaseViewCnt(int bno);

	void deleteReplyBybno(int bno);

	ArrayList<Map<String, Object>> selectKnowhow(Map<String, Object> item);

}
