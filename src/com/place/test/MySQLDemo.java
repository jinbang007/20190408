package com.place.test;
import java.sql.*;

public class MySQLDemo {


    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC";
    

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "jb1995";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT ID, name, MajorCat,Cat,LaAndLo,Region FROM place";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.print("[");
            System.out.print("\n");
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                String MajorCat= rs.getString("MajorCat");
                String Cat= rs.getString("Cat");
                String LaAndLo= rs.getString("LaAndLo");
                String Region= rs.getString("Region");


                // 输出数据

                System.out.print("{"+'"'+"ID "+'"'+":" +'"'+ ID+'"');
                System.out.print("{"+'"'+"名称 "+'"'+":" +'"'+ name+'"');
                System.out.print("{"+'"'+"地名大类"+'"'+":" +'"'+ MajorCat+'"');
                System.out.print("{"+'"'+"地名小类"+'"'+":" +'"'+ Cat+'"');
                System.out.print("{"+'"'+"经纬度 "+'"'+":" +'"'+ LaAndLo+'"');
                System.out.print("{"+'"'+"所在行政区 "+'"'+":" +'"'+ Region+'"'+"}"+",");
                System.out.print("\n");
            }
            System.out.print("]");
            System.out.print("\n");
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

    }
}