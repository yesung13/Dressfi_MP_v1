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
        // ���� ���� 2 �̻��� ��� [����] ǥ��
        if (currentBlock > 1) {
            pageHtml.append("<span><a href=\"list.do?page=" + (currentPage - 1) + "\"><����></a>&nbsp;&nbsp;");
        } else {
            pageHtml.append("<span>");
        }
        // ���� ���ȿ� ���Ե� ������ ǥ��
        for (int i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
                pageHtml.append(".&nbsp;<strong>");
                pageHtml.append(i);
                pageHtml.append("&nbsp;</strong>");
            } else {
                pageHtml.append(".&nbsp;<a href=\"list.do?page=" + i + "\" class=\"page\">" + i + "</a>&nbsp;");
            }
        }
        // ���� ��� ���� ��ü �� ���� ������ [����] ǥ��
        if (currentBlock < totalBlock) {
            pageHtml.append(".&nbsp;&nbsp;<a href=\"list.do?page=" + (currentPage + 1) + "\"><����></a></span>");
        } else {
            pageHtml.append(".</span>");
        }
        return pageHtml;
    }
}