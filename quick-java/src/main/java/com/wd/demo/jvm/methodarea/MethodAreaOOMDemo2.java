package com.wd.demo.jvm.methodarea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @Author: wangd
 * @Date: 2023/5/12 14:16
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class MethodAreaOOMDemo2 extends ClassLoader {
    public static void main(String[] args) {

        int j = 0;
        try {
            MethodAreaOOMDemo2 demo = new MethodAreaOOMDemo2();
            for (int i = 0; i < 10000; i++) {
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class"+i, null, "java/lang/Object", null);
                byte[] code = classWriter.toByteArray();
                demo.defineClass("Class"+i, code, 0, code.length);
                j++;
            }
        } finally {
            System.out.println("j = " + j);
        }
    }
}
