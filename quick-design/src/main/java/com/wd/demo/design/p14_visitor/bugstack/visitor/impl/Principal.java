package com.wd.demo.design.p14_visitor.bugstack.visitor.impl;

import com.wd.demo.design.p14_visitor.bugstack.user.impl.Student;
import com.wd.demo.design.p14_visitor.bugstack.user.impl.Teacher;
import com.wd.demo.design.p14_visitor.bugstack.visitor.Visitor;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:40
 */
public class Principal implements Visitor {
    @Override
    public void visit(Student student) {
        System.out.printf("学生信息 班级：%s, 人数 %s\n", student.clazz, student.count());
    }

    @Override
    public void visit(Teacher teacher) {
        System.out.printf("老师信息 姓名：%s 班级：%s 升学率： %s\n", teacher.name, teacher.clazz, teacher.entranceRatio());
    }
}
