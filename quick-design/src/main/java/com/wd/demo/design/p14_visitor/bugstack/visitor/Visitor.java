package com.wd.demo.design.p14_visitor.bugstack.visitor;

import com.wd.demo.design.p14_visitor.bugstack.user.impl.Teacher;
import com.wd.demo.design.p14_visitor.bugstack.user.impl.Student;

/**
 * @Author: wangd
 * @Date: 2023/5/2 17:26
 */
public interface Visitor {
    void visit(Student student);


    void visit(Teacher teacher);
}
