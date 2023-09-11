package com.wd.demo.jvm.dump.memoryleak;

import java.io.*;

/**
 * @Author: wangd
 * @Date: 2023/5/16 17:06
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class Demo04_IOStream {

    static class MyFileInputStream extends FileInputStream {
        public MyFileInputStream(File file) throws FileNotFoundException {
            super(file);
        }

        /**
         * 重写了 finalize 方法，把父类的 finalize 中的方法复制到此处，并且在其中添加打印信息
         *
         * @throws IOException 异常
         */
        @Override
        protected void finalize() throws IOException {
            if ((this.getFD() != null) && (this.getFD() != FileDescriptor.in)) {
                /* if fd is shared, the references in FileDescriptor
                 * will ensure that finalizer is only called when
                 * safe to do so. All references using the fd have
                 * become unreachable. We can call close()
                 */
                close();
                System.out.println("finalize and close");
            } else {
                System.out.println("only call finalize");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(() -> {
            try {
                ioClosed();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "IOClose").start();

        // 在FullGC之前，debug查看idea堆栈Memory,可以看到1000个FileInputStream
        Thread.sleep(1000);

        System.gc();

        Thread.sleep(1000);

        System.out.println();
    }

    private static void ioClosedNew() throws IOException {
        for (int i = 0; i < 100; i++) {
            File file = new File("example.txt");
            try (FileInputStream fis = new MyFileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String content = new String(data, "UTF-8");
                System.out.print(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void ioClosed() throws IOException {
        FileInputStream fis = null;
        for (int i = 0; i < 100; i++) {
            File file = new File("example.txt");
            try {
                fis = new MyFileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String content = new String(data, "UTF-8");
                System.out.print(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }

    // unclosedCase
    public static void main1(String[] args) throws InterruptedException, IOException {
        FileInputStream fis = null;
        try {
            for (int i = 0; i < 100; i++) {
                File file = new File("example.txt");
                fis = new MyFileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String content = new String(data, "UTF-8");
                System.out.print(content);
                // 没有关闭流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在FullGC之前，debug查看idea堆栈Memory,可以看到1000个FileInputStream
        Thread.sleep(1000);

        // 未关闭的FileInputStream也可以被FullGC回收, 因为FIS重写了finalize方法
        // 但并不能保证完成，所以仍有风险
        // System.gc();

        Thread.sleep(2000);

        System.out.println();
    }


}
