package com.wd.demo.design.p14_visitor.bugstack.visitor.impl;

import com.wd.demo.design.p14_visitor.bugstack.user.impl.Teacher;
import com.wd.demo.design.p14_visitor.bugstack.user.impl.Student;
import com.wd.demo.design.p14_visitor.bugstack.visitor.Visitor;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:32
 */
public class Parent implements Visitor {

    @Override
    public void visit(Student student) {
        System.out.printf("学生信息 姓名：%s 班级：%s 排名：%s\n", student.name, student.clazz, student.ranking());

    }

    @Override
    public void visit(Teacher teacher) {
        System.out.printf("老师信息 姓名：%s 班级：%s 级别：%s\n", teacher.name, teacher.clazz, teacher.identity);

    }


}
