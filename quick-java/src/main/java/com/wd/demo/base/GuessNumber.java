package com.wd.demo.base;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @Author: wangd
 * @Date: 2023/6/29 11:17
 */
public class GuessNumber {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("D:\\zhaopin\\demo.txt")) {
            // fileWriter.append(content);

            int num = new Random().nextInt(1000000);
            System.out.println("num = " + num);
            fileWriter.append(String.format("num = " + num));

            while (true) {
                System.out.print("输入数字: ");
                Scanner scanner = new Scanner(System.in);
                int userNum = scanner.nextInt();
                fileWriter.append((char) userNum);
                if (userNum < num) {
                    fileWriter.append("小了！");
                } else if (userNum > num) {
                    fileWriter.append(("大了！"));
                } else {
                    fileWriter.append(("猜对了"));
                    break;
                }
            }
            fileWriter.append(("游戏结束！"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
