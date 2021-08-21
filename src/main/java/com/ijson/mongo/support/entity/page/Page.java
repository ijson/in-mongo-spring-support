package com.ijson.mongo.support.entity.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/14 10:43 AM
 */
@Data
public class Page {
    private int pageNumber = 1;
    private int pageSize = 20;
    private String orderBy;
    private boolean asc;

    private Page() {
    }

    private Page(int pageNumber, int pageSize, String orderBy, boolean asc) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.asc = asc;
    }

    public static Page of() {
        return of(1, 20);
    }

    public static Page of(int pageNumber) {
        return of(pageNumber, 20);
    }

    public static Page of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, "");
    }


    public static Page of(int pageNumber, int pageSize, String orderBy) {
        return of(pageNumber, pageSize, orderBy, false);
    }

    public static Page of(int pageNumber, int pageSize, String orderBy, boolean asc) {
        return new Page(pageNumber, pageSize, orderBy, asc);
    }


}
