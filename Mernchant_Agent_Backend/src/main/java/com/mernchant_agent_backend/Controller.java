package com.mernchant_agent_backend;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.jdbc.datasource.DataSourceUtils;

@RestController
public class Controller {
    private DataSource dataSource;
    public Controller(DataSource dataSource){
        this.dataSource=dataSource;
    }
    @PostMapping("/api/product")
    public Boolean AddProduct(@RequestBody ProductDetails pro){
        String name = pro.getName();
        String description = pro.getDescription();
        String category = pro.getCategory();
        String brand = pro.getBrand();
        String color = pro.getColor();
        int price = pro.getPrice();
        double rating = pro.getRating();
        int reviewCount = pro.getReviewCount();
        try{
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String sql ="Insert INTO products (name,description,category,brand,color,price,rating,\"reviewCount\") Values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            preparedStatement.setString(3,category);
            preparedStatement.setString(4,brand);
            preparedStatement.setString(5,color);
            preparedStatement.setInt(6,price);
            preparedStatement.setDouble(7,rating);
            preparedStatement.setInt(8,reviewCount);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
