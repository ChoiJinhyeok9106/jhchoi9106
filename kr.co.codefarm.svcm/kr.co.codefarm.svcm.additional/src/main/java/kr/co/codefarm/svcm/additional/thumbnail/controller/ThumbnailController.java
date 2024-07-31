package kr.co.codefarm.svcm.additional.thumbnail.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


    @RestController
    @RequestMapping(value = "/additional/thumbnail")
    public class ThumbnailController {

        @Value("${sysprop.uploadDir}")
        private String UPLOAD_DIR;
        @Value("${homedir}")
        private String HOME_DIR;

        @GetMapping("")
        public void getThumbnail(@RequestParam("tWidth") int width,
                                @RequestParam("tHeight") int height,
                                @RequestParam("outputQuality") float quality,
                                @RequestParam("tSrc") String sourcePath,
                                HttpServletResponse response)  throws IOException {
            String filePath = this.HOME_DIR + "/" + this.UPLOAD_DIR  + sourcePath;
            filePath =  filePath.replace("'\'", File.separator);
            File originalImage = new File(filePath);
            if (!originalImage.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            OutputStream out = response.getOutputStream();
        try{
            //1안
            // Thumbnails.of(originalImage).size(width, height).crop(Positions.CENTER).outputQuality(quality).outputFormat("png").toOutputStream(out);
            //2안
            // Thumbnails.of(originalImage).size(width, height).outputQuality(quality).outputFormat("png").toOutputStream(out);
            //3안
            Thumbnails.of(originalImage).size(width, height).keepAspectRatio(false).outputQuality(quality).outputFormat("png").toOutputStream(out);
            response.setContentType("image/png");
            response.flushBuffer();
        }catch(IOException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally{out.close();}
            
        }
    }