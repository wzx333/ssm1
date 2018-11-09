package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ProductDao {
    @Select("select * from product")
    public List<Product> findAll();
    @Insert("insert into product " +
            "values(product_seq.nextval,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Select("select *from product where id = #{id}")
    Product findById(Integer id);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    void update(Product product);

    @Delete("delete from product where id =#{a}")
    void del(Integer id);

    @Select("select count(1) from product")
    Integer findCount();

    @Select("select n.* from (select e.* ,rownum rn from product e)n where n.rn between #{param1} and #{param2}")
    List<Product> findByPage(Integer beignum, Integer endnum);
}