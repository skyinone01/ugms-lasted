package com.ug369.backend.service.repository.rdbsupport.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UgmsPage<T> implements Serializable {
    private static final long serialVersionUID = -7846965154924905005L;
    //总页数
    private int pageSum = 1;
    //当前页
    private int pageCurrent = 1;
    //总记录数
    private int rowSum;
    //每一页记录数
    private int pageRows = 5;
    //当前页记录数
    private int rowsCurrent;

    private List<T> rowDatas;

    public UgmsPage() {
    }

    public UgmsPage(Map<String, Object> parameter) {
        if (parameter.containsKey("pageRows")) {
            String pageRows = (String) parameter.get("pageRows");
            if ("".equals(pageRows) == false) {
                setPageRows(Integer.parseInt(pageRows));
            }
        }
        if (parameter.containsKey("pageCurrent")) {
            String pageCurrent = (String) parameter.get("pageCurrent");
            if ("".equals(pageCurrent) == false) {
                setPageCurrent(Integer.parseInt(pageCurrent));
            }
        }
        if (parameter.containsKey("rowSum")) {
            String rowSum = (String) parameter.get("rowSum");
            if ("".equals(rowSum) == false) {
                setRowSum(Integer.parseInt(rowSum));
            }
        }
//		if (parameter.containsKey("pageGroupBy")) {
//			String pageGroupBy = (String) parameter.get("pageGroupBy");
//			if ("".equals(pageGroupBy) == false) {
//				setPageGroupBy(Integer.parseInt("pageGroupBy"));
//			}
//		}
    }

    public void setRowSum(int rowSum) {
        this.rowSum = rowSum;
        if (rowSum != 0) {
            if (pageRows == 0) {
                pageSum = 1;
            } else {
                pageSum = (rowSum % pageRows) == 0 ? rowSum / pageRows : rowSum
                        / pageRows + 1;
                if (pageCurrent < 1) {
                    pageCurrent = 1;
                } else if (pageCurrent > pageSum) {
                    pageCurrent = pageSum;
                }
            }
        } else {
            pageSum = 1;
            pageCurrent = 1;
        }
        if (pageRows == 0) {
            rowsCurrent = rowSum;
        } else {
            if (pageCurrent >= pageSum) {
                rowsCurrent = (rowSum % pageRows) == 0 ? pageRows : rowSum
                        % pageRows;
            } else {
                rowsCurrent = pageRows;
            }
        }
//		if (pageCurrent % pageGroupBy == 0) {
//			pageFrom = pageCurrent - pageGroupBy + 1;
//		} else {
//			pageFrom = pageCurrent - pageCurrent % pageGroupBy + 1;
//		}
//		if (pageFrom < 1) {
//			pageFrom = 1;
//		}
//		if (pageFrom + pageGroupBy - 1 > pageSum) {
//			pageTo = pageSum;
//		} else {
//			pageTo = pageFrom + pageGroupBy - 1;
//		}
    }

    public void setPageRows(int pageRows) {
        if (pageRows < 0) {
            return;
        }
        this.pageRows = pageRows;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getRowsCurrent() {
        return rowsCurrent;
    }

    public void setRowsCurrent(int rowsCurrent) {
        this.rowsCurrent = rowsCurrent;
    }

    public List<T> getRowData() {
        return rowDatas;
    }

    public void setRowDatas(List<T> rowDatas) {
        this.rowDatas = rowDatas;
    }

    public int getRowSum() {
        return rowSum;
    }

    public int getPageRows() {
        return pageRows;
    }

//	public int getPageFrom() {
//		return pageFrom;
//	}
//
//	public void setPageFrom(int pageFrom) {
//		this.pageFrom = pageFrom;
//	}
//
//	public int getPageTo() {
//		return pageTo;
//	}
//
//	public void setPageTo(int pageTo) {
//		this.pageTo = pageTo;
//	}
//
//	public int getPageGroupBy() {
//		return pageGroupBy;
//	}
//
//	public void setPageGroupBy(int pageGroupBy) {
//		this.pageGroupBy = pageGroupBy;
//	}
}
