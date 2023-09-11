package com.wd.demo.jvm.gc;

/**
 * @Author: wangd
 * @Date: 2023/5/13 21:31
 */
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JMXBeanExample {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("VM Name: " + runtimeMXBean.getVmName());
        System.out.println("VM Vendor: " + runtimeMXBean.getVmVendor());
        System.out.println("VM Version: " + runtimeMXBean.getVmVersion());
        System.out.println("Class Path: " + runtimeMXBean.getClassPath());
        System.out.println("Boot Class Path: " + runtimeMXBean.getBootClassPath());
        System.out.println("Library Path: " + runtimeMXBean.getLibraryPath());
    }
}

