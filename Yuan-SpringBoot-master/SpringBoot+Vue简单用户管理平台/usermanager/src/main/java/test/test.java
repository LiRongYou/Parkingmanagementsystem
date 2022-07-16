package test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黎荣右
 * @date 2022/7/10 16:44
 */
public class test {
    @Test
    public void testRedis1() {
        /**
         * 测试redis
         */
        Jedis jedis = new Jedis("127.0.0.1", 6379, 60000);
        jedis.set("name", "Tom");
        jedis.set("age", "18");
        System.out.println(jedis.get("name") + "  "+jedis.get("age"));
    }

    @Test
    public void testtime(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse("2004-03-26 13:30:24");
            Date d2 = df.parse("2004-03-26 11:30:24");
            long diff = d1.getTime() - d2.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = diff / (1000 * 60 * 60 * 24*24);
            System.out.println("时间相差"+days+"   "+hours);
            System.out.println(diff);

            System.out.println("时间戳转换成小时"+diff/ 1000 / (60 * 60) );
            System.out.println("小时转换成时间戳"+2 *1000*(60*60));

            System.out.println(" 时间戳（转分钟）"+diff/ 1000 / 60);
            System.out.println(" 时间戳（转秒）"+diff/ 1000);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
