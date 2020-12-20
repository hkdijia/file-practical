package com.gotkx.filerename.work;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class ReName {

    // 检测路径下的全部文件   主路径
    public static final String fileMainPath = "H:\\对外援助\\菜菜\\2020-12-20\\测试数据\\市刀剪厂PDF";
    //public static final String fileMainPath = "H:\\对外援助\\菜菜\\2020-12-20\\副本一\\test2";
    public static AtomicInteger count = new AtomicInteger();

    // 文件夹策略
    public static void main(String[] args) {
        File mainfile = new File(fileMainPath.toString());
        File[] listMainFiles = mainfile.listFiles();
        System.out.println("主文件的数量为：" + listMainFiles.length);

        for (File mainFile : listMainFiles) {
            //  是文件夹继续遍历
            if (mainFile.isDirectory()){
                File file = new File(mainFile.toString());
                File[] listFiles = file.listFiles();

                for (File File : listFiles) {
                    if (File.isDirectory()){
                        File single = new File(File.toString());
                        File[] listSingle = single.listFiles();
                        // 真实 文件名称
                        String realFileName = null;
                        String pdfRealPath = null;
                        for (java.io.File singleFile : listSingle) {
                            String singleFileName = singleFile.getName();
                            String res = singleFileName.substring(singleFileName.lastIndexOf(".") + 1).toLowerCase();
                            if("txt".equals(res)){
                                // 前缀名称
                                String singleFilePre = singleFileName.substring(0, singleFileName.lastIndexOf("."));
                                realFileName = singleFilePre;
                            }

                            if("pdf".equals(res)){
                                pdfRealPath = singleFile.toString();
                            }
                            count.incrementAndGet();
                        }

                        // 处理循环后的名称交换
                        reName(pdfRealPath,realFileName);
                    }
                }

            }
        }

        System.out.println("pdf 和 txt 总数" + count);

    }

    // pdf 文件的绝对路径
    static void reName(String pdfRealPath, String realFileName){
        String realPath = pdfRealPath.substring(0,pdfRealPath.lastIndexOf("\\"));
        String realName = realFileName + ".pdf";
        String allName = realPath + "\\" +realName;

        File file = new File(pdfRealPath);
        file.renameTo(new File(allName));
    }

}
