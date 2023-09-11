package com.wd.demo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Arrays;

/**
 * @Author: wangd
 * @Date: 2023/9/4 22:44
 */
public class HdfsClient {
    FileSystem fs;

    @Before
    public void prepareFs() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        // configuration.set("dfs.replication", "2");
        fs = FileSystem.get(new URI("hdfs://hadoop130:8020"), configuration, "hadoop");
    }

    @After
    public void close() throws IOException {
        fs.close();
    }


    @Test
    public void testMkdirs() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130:8020"),
                configuration, "hadoop");
        // 2 创建目录
        fs.mkdirs(new Path("/xiyou/huaguoshan/"));
        // 3 关闭资源
        fs.close();
    }

    // HDFS文件上传
    @Test
    public void testCopyFromLocal() throws URISyntaxException, IOException, InterruptedException {
        fs.copyFromLocalFile(new Path("D:/dump/sunwukong.txt"), new Path("/xiyou/huaguoshan"));
        fs.close();
    }

    // HDFS文件下载
    @Test
    public void testCopyToLocal() throws URISyntaxException, IOException, InterruptedException {
        fs.copyToLocalFile(false, new Path("/xiyou/huaguoshan/sunwukong.txt"),
                new Path("D:/dump/sunwukong2.txt"), true);
        fs.close();
    }

    // HDFS 文件更名、移动
    @Test
    public void testMvFile() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop130:8020"), configuration, "hadoop");
        fs.mkdirs(new Path("/xiyou/tiangong"));
        boolean success = fs.rename(new Path("/xiyou/huaguoshan/sunwukong.txt"), new Path("/xiyou/tiangong/bimawen.txt"));
        System.out.println("success = " + success);
        fs.close();
    }

    @Test
    public void testListFiles() throws IOException, InterruptedException,
            URISyntaxException {

        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"),
                true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("========" + fileStatus.getPath() + "=========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());
            // 获取块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }

    }


}
