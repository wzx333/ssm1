package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("/findAll1")
    public ModelAndView findAll1(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAll();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");
        System.out.println("-------------------------");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        System.out.println("1111111");
        return "redirect:/product/findAll";
    }
    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id){
        Product product = productService.findById(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String departurerstr = format.format(product.getDepartureTime());
        product.setDepartureTimeStr(departurerstr);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/del")
    public String del(Integer id){
        productService.del(id);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/findAll2")
    public ModelAndView findAll2(
           @RequestParam(name = "pageNumber",required = false,defaultValue = "1") Integer pageNumber,
           @RequestParam(name = "pageSize", required = false,defaultValue = "3") Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        PageBean pageBean = productService.findByPage(pageNumber,pageSize);
        System.out.println(pageBean.getList());
        modelAndView.addObject("pageBean",pageBean);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(name = "pageNumber",required = false,defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false,defaultValue = "3") Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo pageInfo = productService.pageHelper(pageNumber, pageSize);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
}
