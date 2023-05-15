package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.*;
import com.jyz.service.AdminsSrevice;
import com.jyz.service.PapersSrevice;
import com.jyz.service.StudentsSrevice;
import com.jyz.service.TopicsSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    AdminsSrevice adminsSrevice;
    StudentsSrevice studentsSrevice;
    TopicsSrevice  topicsSrevice;
    PapersSrevice papersSrevice;
    @GetMapping("/getadmin")
    public Result getAdmin(@RequestParam String userid){
        Admins admins = adminsSrevice.getById(userid);
        return Result.succ(admins);
    }

    @PostMapping("/updataadminpassword")
    public Result updataTeacherPassword(@RequestBody Map<String, String> map) {
        Admins admins = new Admins();
        admins.setAdminid(map.get("userid"));
        admins.setPassword(map.get("password"));
        adminsSrevice.updateById(admins);
        return Result.succ("");
    }
    @GetMapping("/getallinfos")
    public Result getallinfos(){
        List<Students> studentsList = studentsSrevice.list();
        List<Topics> topicsList = topicsSrevice.list();
        List<Papers> papersList= papersSrevice.list();
        List<Allinfos> allinfoslist= new ArrayList<Allinfos>();
        Allinfos allinfos=new Allinfos();
        for(int i=0;i<studentsList.size();i++)
        {    String studentId=studentsList.get(i).getStudentid();
            for(int j=0;j<topicsList.size();j++)
            {
                if(studentId.equals(topicsList.get(j).getStudentid()))
            {
                for(int k=0;k<papersList.size();k++)
                  if(studentId.equals(papersList.get(k).getStudentid()))
                {
                    allinfos.setDepartment(studentsList.get(i).getDepartment());
                    allinfos.setStatus(studentsList.get(i).getStatus());
                    allinfos.setBlindscore(studentsList.get(i).getBlindscore());
                    allinfos.setStudentid(studentId);
                    allinfos.setTeacherid(studentsList.get(i).getTeacherid());
                    allinfos.setDefensescore(studentsList.get(i).getDefensescore());
                    allinfos.setSpecialty(studentsList.get(i).getSpecialty());
                    allinfos.setUsername(studentsList.get(i).getUsername());
                    allinfos.setTopictitle(topicsList.get(j).getTopictitle());
                    allinfos.setFileurl(papersList.get(k).getFileurl());
                    allinfos.setFilename(papersList.get(k).getFilename());
                    allinfos.setPaperid(papersList.get(k).getPaperid());

                }
            }
            }
            allinfoslist.add(allinfos);
        }
        return Result.succ(allinfoslist);

    }

}
