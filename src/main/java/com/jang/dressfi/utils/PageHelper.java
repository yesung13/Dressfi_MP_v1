package com.jang.dressfi.utils;

public class PageHelper {

	private int pageSize = 10; //
	private int blockSize = 10; //

	public int getPageSize() {
		return pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public StringBuffer getPageUrl(int currentPage, int totalRow) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int endPage = 0;
		startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		endPage = startPage + blockSize - 1;
		int totalPage = totalRow / pageSize + 1;
		int totalBlock = totalPage / blockSize + 1;
		int currentBlock = currentPage / blockSize + 1;

		if (endPage > (totalRow / pageSize)) {
			endPage = (totalRow / pageSize) + 1;
		}
		// 현재 블럭이 2 이상인 경우 [이전] 표시
		if (currentBlock > 1) {
			pageHtml.append("<span><a href=\"list.do?page=" + (currentPage - 1) + "\"><이전></a>&nbsp;&nbsp;");
		} else {
			pageHtml.append("<span>");
		}
		// 현재 블럭안에 포함될 페이지 표시
		for (int i = startPage; i <= endPage; i++) {
			if (i == currentPage) {
				pageHtml.append(".&nbsp;<strong>");
				pageHtml.append(i);
				pageHtml.append("&nbsp;</strong>");
			} else {
				pageHtml.append(".&nbsp;<a href=\"list.do?page=" + i + "\" class=\"page\">" + i + "</a>&nbsp;");
			}
		}
		// 현재 블록 안이 전체 블럭 보다 작으면 [다음] 표시
		if (currentBlock < totalBlock) {
			pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage + 1) + "\"><다음></a></span>");
		} else {
			pageHtml.append(".</span>");
		}
		return pageHtml;
	}
}