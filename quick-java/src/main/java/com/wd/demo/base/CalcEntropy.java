package com.wd.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/6/26 14:03
 */
public class CalcEntropy {
    public  void main(String[] args) {
        // public double fileSizeEntropy(long targetFileSize) {
        //     double fse = 0;
        //     for (DataFile file : dataFiles) {
        //         fse += Math.pow(targetFileSize - Math.min(file.fileSizeInBytes(), targetFileSize), 2);
        //     }
        //
        //     if (dataFiles.size() > 0) {
        //         fse /= (dataFiles.size() * 1.0D);
        //     }
        //     return fse;
        // }
        long targetFileSize = 4 * 1024 * 1024;
        double fse = 0;
        List<Double> dataFiles = new ArrayList<>();
        dataFiles.add(3.0*1024*1024);
        dataFiles.add(3.0*1024*1024);
        dataFiles.add(3.0*1024*1024);
        dataFiles.add(3.0*1024*1024);
        dataFiles.add(3.0*1024*1024);
        // dataFiles.add(1.0*1024);
        // dataFiles.add(1.0*1024);
        // dataFiles.add(1.0*1024);
        // dataFiles.add(1.0*1024);
        // dataFiles.add(1.0*1024);
        for (Double fileSizeBytes : dataFiles) {
            fse += Math.pow(targetFileSize - Math.min(fileSizeBytes, targetFileSize), 2);

        }
            if (dataFiles.size() > 0) {
                fse /= (dataFiles.size() * 1.0D);
            }
        System.out.println("fse = " + fse);

    }
}
