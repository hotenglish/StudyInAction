package com.learn.chapter8.controller;

import javax.servlet.http.HttpServletRequest;
import com.learn.chapter8.pojo.FileBean;
import com.learn.chapter8.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

    @Autowired
    private FileService fileService = null;

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public Message uploadFile(@RequestParam("title") String title, HttpServletRequest request, ModelMap model) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile imgFile = multipartRequest.getFile("imageFile");
        FileBean file = new FileBean();
        file.setTitle(title);
        Message msg = new Message();
        if (fileService.insertFile(imgFile, file)) {
            msg.setSuccess(true);
            msg.setInfo("插入成功");
        } else {
            msg.setSuccess(false);
            msg.setInfo("插入失败");
        }
        return msg;
    }


    @RequestMapping("/index")
    public ModelAndView firstPage() {
        return new ModelAndView("uploadFile");
    }


    private class Message {
        private boolean success = false;
        private String info = null;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getInfo() {
            return info;

        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

}
