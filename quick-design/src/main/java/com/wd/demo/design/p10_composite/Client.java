package com.wd.demo.design.p10_composite;

/**
 * @Author: wangd
 * @Date: 2023/4/28 16:24
 */
public class Client {


    public static void main(String[] args) {
        OrganizationComponent university = new University("上海交通大学", " 中国顶级大学 ");
        OrganizationComponent computerCollege = new College("计算机学院", " 计算机学院 ");
        OrganizationComponent infoEngineercollege = new College("信息工程学院", " 信息工程学院 ");
        computerCollege.add(new Department("软件工程", " 软件工程不错 "));
        computerCollege.add(new Department("网络工程", " 网络工程不错 "));
        computerCollege.add(new Department("计算机科学与技术", " 计算机科学与技术是老牌的专业 "));
        infoEngineercollege.add(new Department("通信工程", " 通信工程不好学 "));
        infoEngineercollege.add(new Department("信息工程", " 信息工程好学 "));
        infoEngineercollege.add(new College("X研究院", " X "));
        university.add(computerCollege);
        university.add(infoEngineercollege);
        infoEngineercollege.print();
        university.print();
    }
}
