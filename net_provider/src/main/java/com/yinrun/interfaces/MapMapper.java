/*
 * @(#)PageMapper.java 2016-3-30 下午5:58:07
 * Copyright 2016 张孟如, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yinrun.interfaces;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>File：MapMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年2月5日 下午5:11:44</p>
 * <p>Company: </p>
 * @author 张亮亮
 * @version 1.0
 */
public interface MapMapper<T>
{
    /**
     * 根据Map键值对返回Map集合
     * @param param
     * @return
     * @author 张亮亮
     */
    List<Map<String, Object>> getListMap(Map<String, Object> param);
}
