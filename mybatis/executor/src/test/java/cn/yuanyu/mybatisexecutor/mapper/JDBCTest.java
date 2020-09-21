package cn.yuanyu.mybatisexecutor.mapper;

import org.junit.Test;

import java.sql.*;

public class JDBCTest {
    /**
     * 获取Connection
     */
    public static Connection getConn() throws SQLException {
        String url = "jdbc:mysql://121.36.33.154:45000/mybatis_bash_db?characterEncoding=utf-8&useUnicode=true&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }
    /**
     * 清空表
     */
    public void clearTable() throws SQLException {
        Statement statement = getConn().createStatement();
        String sql = "truncate table mybatis_bash_db.user";
        statement.executeUpdate(sql);
    }

    public void test_1(int count) throws SQLException {
        Connection conn = getConn();
        Statement statement = conn.createStatement();
        for (int i = 1; i <= count; i++) {
            String sql = "insert into mybatis_bash_db.user(id, username) " +
                    "values (" + i + ",'蔡徐坤')";
            int res = statement.executeUpdate(sql);
        }
    }


    public void test_2(int count) throws SQLException {
        Connection conn = getConn();
        String sql = "insert into mybatis_bash_db.user(id, username) " +
                "values (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= count; i++) {
            pstmt.setInt(1, i);
            pstmt.setString(2, "乔碧萝");
            int res = pstmt.executeUpdate();
        }
    }

    public void test_3(int count) throws SQLException {
        String sql = "insert into mybatis_bash_db.user(id, username) " +
                "values (1,'蔡徐坤')";
        Connection conn = getConn();
        PreparedStatement pstmt = conn.prepareStatement("insert into mybatis_bash_db.user(id, username) " +
                "values (?,?)");
        for (int i = 1; i <= count; i++) {
            pstmt.setInt(1, i);
            pstmt.setString(2, "卢本伟");
            pstmt.addBatch();
        }
        int[] resArr = pstmt.executeBatch();
    }

    @Test
    public void test() throws SQLException {
        int count = 10;
        clearTable();
        long start = System.currentTimeMillis();
        test_1(count);
        //test_2(count);
        //test_3(count);
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) + "(ms)");
        clearTable();
    }

}
