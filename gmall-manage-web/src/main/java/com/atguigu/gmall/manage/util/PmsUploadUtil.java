package com.atguigu.gmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {
    public static String uoloadImage(MultipartFile multipartFile) {
        String imgUrl = "";

        //上传图片的服务器
        //获得配置文件的路径,获得fdfs的全局链接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        try {
            //获得上传的二进制对象
            byte[] bytes = multipartFile.getBytes();
            //获得上传文件的后缀名
            String originalFilename = multipartFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);

            ClientGlobal.init(tracker);
            TrackerClient trackerClient = new TrackerClient();

            TrackerServer trackerServer = trackerClient.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, null);

            String[] uploadInfo = storageClient.upload_file(bytes, extName, null);
            imgUrl = "http://192.168.31.200/" + uploadInfo[0] + "/" + uploadInfo[1];
            System.out.println(imgUrl);

            return imgUrl;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
