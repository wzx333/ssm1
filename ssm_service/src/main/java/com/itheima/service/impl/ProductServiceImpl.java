package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    public List<Product> findAll() {
        List<Product> productList = productDao.findAll();
        return productList;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void del(Integer id) {
        productDao.del(id);
    }

    @Override
    public PageBean findByPage(Integer pageNumber, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNum(pageNumber);
        pageBean.setPageSize(pageSize);

        /*
           4
        * 1,1,4
        * 2,5,8
        * 3,9,12
        *
        *
        * */
        pageBean.setList(productDao.findByPage((pageNumber-1)*pageSize+1, (pageNumber)*pageSize));
        Long totalCount = Long.valueOf(productDao.findCount());
        pageBean.setTotalCount(totalCount);
        Integer totalPage = (int) Math.ceil(totalCount / pageSize);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public PageInfo pageHelper(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Product> productList = productDao.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(productList,3);
        return pageInfo;
    }
}
