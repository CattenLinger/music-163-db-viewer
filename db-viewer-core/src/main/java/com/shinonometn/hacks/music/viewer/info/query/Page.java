package com.shinonometn.hacks.music.viewer.info.query;

import java.util.Collection;

/**
 * Created by cattenlinger on 2018/2/17.
 */
public interface Page<T> {
    Integer currentPage();
    Integer totalPage();
    Integer totalElements();
    Collection<T> contents();
}
