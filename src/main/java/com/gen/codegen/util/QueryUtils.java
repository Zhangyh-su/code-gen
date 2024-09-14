 package com.gen.codegen.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;


/**
 * @author 59203
 */
public class QueryUtils {

    private static final String ASC = "+";
    private static final String DESC = "-";

    public static QueryWrapper dynamicSort(String sortBy, QueryWrapper query) {
        if (StringUtils.isNotEmpty(sortBy)) {
            sortBy = StringUtils.trim(sortBy);
            if (StringUtils.startsWith(sortBy, ASC)) {
                query.orderByAsc(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(StringUtils.removeStart(sortBy, ASC)));
            } else {
                if (StringUtils.startsWith(sortBy, DESC)) {
                    query.orderByDesc(com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(StringUtils.removeStart(sortBy, DESC)));
                }
            }
        }
        return query;
    }
}
