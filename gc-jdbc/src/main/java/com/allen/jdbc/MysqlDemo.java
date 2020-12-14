package com.allen.jdbc;

import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlDemo {
	private final static Logger logger = Logger.getLogger(MysqlDemo.class);
	
	public static final String url = "jdbc:mysql://127.0.0.1:3306/blogDemo?useUnicode=true&characterEncoding=utf-8&useSSL=false";
//    public static final String className = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "allen";  
  
    public PreparedStatement pst = null;  
	 
    private static Connection connectMysql() {
    	Connection conn=null;
//    	Statement st=null;
    	try {
    		//1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库链接
            conn=DriverManager.getConnection(url, user, password);
            //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
//            st=conn.createStatement();
            logger.info("MySQL连接成功");
		} catch (Exception e) {
			e.getMessage();
		}
//    	finally {
//			try {
//				if(conn != null) {
//					conn.close();
//				}
////				if(st != null) {
////					st.close();
////				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
    	return conn;
    }
    
    //添加
    private static void add(Connection conn) {
    	 String s="insert into test_blog(id,title,grade,teacher,createTime) values(null,'语文',90,'小名',Now())";
         PreparedStatement pst;
		try {
			pst = conn.prepareStatement(s);
			 pst.execute();
			//关闭资源        
	         pst.close();
	         conn.close();
	         logger.info("添加成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //批量添加
    private static void batchAdd(Connection conn) {
    	 String s="insert into test_blog(id,title,grade,teacher,createTime) values(?,?,?,?,Now())";
         PreparedStatement pst;
		try {
			pst = conn.prepareStatement(s);
			for (int i = 200; i < 300; i++) {
				pst.setInt(1, i);
				pst.setString(2, "数学"+i);
				pst.setInt(3, 90);
				pst.setString(4, "小明"+i);
//				pst.setString(5, "Now()");
				pst.executeUpdate();
//				pst.execute();
			}
			
//			pst.execute();
			//关闭资源        
	         pst.close();
	         conn.close();
	         logger.info("添加成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //修改
    private static void edit(Connection conn) {
    	 String s="update test_blog set title=? where id=1";
         PreparedStatement pst;
		try {
			pst = conn.prepareStatement(s);
			pst.setString(1, "英语1");
//			pst.setInt(1, 80);
			pst.execute();
			//关闭资源        
	         pst.close();
	         conn.close();
	         logger.info("修改成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //查找
    private static void query(Connection conn) {
    	String s="select * from test_blog where title=?";
        PreparedStatement pst;
		try {
			pst = conn.prepareStatement(s);
			pst.setString(1, "英语");
//			pst.setInt(1, 80);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
//				System.out.println(rs.getInt(1));
				System.out.println(rs.getString("title"));
//				System.out.println(rs.toString());
			}
			//关闭资源
			 rs.close();
	         pst.close();
	         conn.close();
	         logger.info("查找成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    //删除
    private static void delete(Connection conn) {
    	String s="delete from test_blog where id=2";
        PreparedStatement pst;
		try {
			pst = conn.prepareStatement(s);
//			pst.setString(1, "英语");
//			pst.setInt(1, 80);
			 pst.execute();
			//关闭资源
	         pst.close();
	         conn.close();
	         logger.info("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) {
		Connection conn=connectMysql();
//		add(conn);
		batchAdd(conn);
//		edit(conn);
//		query(conn);
//		delete(conn);
	}

}
