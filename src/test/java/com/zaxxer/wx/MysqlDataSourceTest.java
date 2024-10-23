package com.zaxxer.wx;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author wuxin
 * @date 2024/10/22 14:47:55
 */

public class MysqlDataSourceTest {



   DataSource dataSource;

   /**
    * 编译需要执行 JavassistProxyFactory mian 方法，无法通过maven 来编译
    */

   @Before
   public void init(){
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setJdbcUrl("jdbc:mysql://192.168.5.66:33106/test");
      hikariConfig.setUsername("root");
      hikariConfig.setPassword("zd199578");
//      hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 会自己去找
      this.dataSource = new HikariDataSource(hikariConfig);
   }


   @Test
   public void valid() throws SQLException {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet= statement.executeQuery("select * from user where id = 1");
      while (resultSet.next()){
         int id = resultSet.getInt("id");
         String name = resultSet.getString("name");
         System.out.println("id：" + id +"名字:" + name);
      }
      resultSet.close();

   }

}
