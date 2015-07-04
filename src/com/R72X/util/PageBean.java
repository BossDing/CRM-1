package com.R72X.util;

public class PageBean {
	private int totalRow; // ���з�ҳ���м�¼��������
	private int pageSize = 10; // ÿҳ��ʾ��������
	private int currentPage = 1; // ��ǰҳ�ţ�Ĭ��Ϊ1
	private int totalPage; // ��ҳ�����ҳ��

	public PageBean() {
		
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalRow(int totalRows) {
		this.totalRow = totalRows;
		// ���¼�����ҳ��
		calcTotalPage();
	}

	public void setTotalPage(int totalPages) {
		this.totalPage = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		// ���¼�����ҳ��
		calcTotalPage();
	}

	public int getTotalRow() {
		return totalRow;
	}

	/*
	 * ������ҳ��
	 */
	private void calcTotalPage() {
		totalPage = totalRow / pageSize;
		int mod = totalRow % pageSize;
		if (mod > 0) {
			totalPage++; // ���һҳ��¼����������ҳ������һҳ
		}
		// ���Ƶ�ǰҳֵ��1����ҳ��֮��
		if (currentPage > totalPage) {
			this.currentPage = totalPage;
		}
		if (currentPage <= 0) {
			this.currentPage = 1;
		}
	}

}
