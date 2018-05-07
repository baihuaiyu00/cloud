package com.xupt.cloud.manager.domain.query;

import com.xupt.cloud.manager.domain.vo.File;

/**
 * Created by baihuaiyu on 2018/5/7
 */
public class FileQuery extends File{
    private Integer pn =1;
    private Integer pageSize = 10;
    private Boolean needPage = Boolean.FALSE;

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getNeedPage() {
        return needPage;
    }

    public void setNeedPage(Boolean needPage) {
        this.needPage = needPage;
    }
}
