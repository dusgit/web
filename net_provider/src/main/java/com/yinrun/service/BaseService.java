package com.yinrun.service;

public interface BaseService<T>
{
    /*
     * 根据主键查询
     */
    public T findById(Long id);

    /*
     * 新增一条记录
     */
    public Integer insert(T item);

    /*
     * 更新内容
     */
    public Integer update(T item);

    /*
     * 根据主键删除
     */
    public Integer deleteById(Long id);

}
