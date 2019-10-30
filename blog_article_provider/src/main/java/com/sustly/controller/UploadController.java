package com.sustly.controller;

import com.sustly.dto.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liyue
 * @date 2019/7/1 14:14
 */
@Controller
@Slf4j
public class UploadController {
    /**
     * 文件上传
     * @param image image
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg uploadFile(MultipartFile image) {

        Map<String, Object> map = new HashMap<>(2);
        //本地使用,上传位置
        String rootPath = null;
        if(System.getProperty("os.name").startsWith("Windows")) {
            rootPath = "D:\\Users\\Documents\\uploads\\";
        }else if (System.getProperty("os.name").startsWith("Linux")){
            rootPath = "/usr/local/upLoads/";
        }

        //文件的完整名称,如spring.jpeg
        String filename = image.getOriginalFilename();
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));

        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));

        //目标文件
        File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);

        String newFilename = UUID.randomUUID() + suffix;
        String parentPath = descFile.getParent();
        descFile = new File(parentPath + File.separator + newFilename);

        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }

        //将内存中的数据写入磁盘
        try {
            image.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，cause:{}", e);
            map.put("result", false);
            return ResponseMsg.onFail("上传失败");
        }
        //完整的url
        String fileUrl = "/api/blog/getImg?url=" + descFile;
        log.info(fileUrl);
        return ResponseMsg.onOk(fileUrl);
    }

    @RequestMapping(value = "/getImg", method = RequestMethod.GET)
    public void getFile(@RequestParam("url")String url,
                        HttpServletResponse response) throws IOException {
        File file = new File(url);
        log.info(url);
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];

        while (fileInputStream.read(bytes) != -1){
            outputStream.write(bytes);
        }

        outputStream.flush();
        fileInputStream.close();
        outputStream.close();
    }
}
