package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    void save(Product product);

    Product findById(Integer id);

    void update(Product product);

    void del(Integer id);

    PageBean findByPage(Integer pageNumber, Integer pageSize);

    public PageInfo pageHelper(Integer pageNumber, Integer pageSize);
}
