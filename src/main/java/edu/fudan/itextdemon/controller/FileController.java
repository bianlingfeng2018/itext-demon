package edu.fudan.itextdemon.controller;

import edu.fudan.itextdemon.beans.Accident;
import edu.fudan.itextdemon.beans.vo.FileUploadVo;
import edu.fudan.itextdemon.repository.AccidentRepository;
import edu.fudan.itextdemon.utils.ItextPdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class FileController {
    @Autowired
    AccidentRepository accidentRepository;

    @RequestMapping(value = "/db/submit")
    public String submit(String filePath, String name, String reason, String[] checkboxValue, String summary,
                         String content,
                         HttpServletResponse response) {
        System.out.println("name : " + name);
        System.out.println("reason : " + reason);
        boolean picExtract = checkboxValue != null;  // picExtract
        System.out.println("checkboxValue : " + picExtract);
        System.out.println("summary : " + summary);
        System.out.println("filePath : " + filePath);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        Accident accident = new Accident();  // 存入 es
        accident.setId(UUID.randomUUID().toString());
        accident.setCheckboxValue(picExtract);
        accident.setFilePath(filePath);
        accident.setName(name);
        accident.setReason(reason);
        accident.setSummary(summary);
        accident.setContent(content);
        accidentRepository.index(accident);
        return "ok";
    }

    @RequestMapping(value = "/db/upload")
    public FileUploadVo upload(MultipartFile file,
                               HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        System.out.println(file.getOriginalFilename() + " : " + file.getSize());
        File f = new File("/Users/bianlingfeng/VscodeProject/vue-db/src/files/before/" + file.getOriginalFilename());
        if (f.exists()) {
            f.delete();
        }
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        String fPath = f.getPath();  // 抽取图像和文字
        ItextPdfUtil itextPdfUtil = new ItextPdfUtil();
        itextPdfUtil.imageTest(fPath);
        List<String> imgPaths = itextPdfUtil.getImgPaths();
        String textPath = itextPdfUtil.getTextPath();
        String content = itextPdfUtil.getContent();
        FileUploadVo vo = new FileUploadVo();
        vo.setImgPaths(imgPaths);
        vo.setTextPath(textPath);
        vo.setOriginalPath(fPath);
        vo.setContent(content);
        return vo;
    }
}
