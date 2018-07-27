/*
 * @(#)BaseMapper.java 2016-3-30 下午5:57:15
 * Copyright 2016 张孟如, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yinrun.interfaces;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * <p>File：GenericMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年2月5日 下午5:12:02</p>
 * <p>Company: </p>
 * @author 张亮亮
 * @version 1.0
 */
public interface GenericMapper<T> extends MapMapper<T>, Mapper<T>, IdsMapper<T>
{
}
