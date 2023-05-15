package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.Students;
import com.jyz.entity.Teachers;
import com.jyz.entity.Topics;
import com.jyz.service.StudentsSrevice;
import com.jyz.service.TopicsSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsSrevice studentsSrevice;
    @Autowired
    TopicsSrevice topicsSrevice;
//    学生用户获取自己的个人信息
    @GetMapping("/getstudent")
    public Result getStudent(@RequestParam String userid){
        Students students = studentsSrevice.getById(userid);
        return Result.succ(students);
    }

//    获取全部学生列表
    @GetMapping("/getallstudents")
    public Result getAllStudents(){
        List<Students> studentsList = studentsSrevice.list();
        return Result.succ(studentsList);
    }

    //    通过学生id查找学生
    @GetMapping("/getstudentbyid")
    public Result getMyStudentsById(@RequestParam("studentid") String studentid){
        Students students = studentsSrevice.getById(studentid);
        return Result.succ(students);
    }


    //通过 老师id 获取 学生 列表
    @GetMapping("/getstudentsbyteacherid")
    public Result getMyStudentsByTeacherId(@RequestParam("userid") String teacherid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("teacherid",teacherid);
        List<Students> studentsList = studentsSrevice.listByMap(columnMap);
        return Result.succ(studentsList);
    }

//    老师绑定学生
    @PostMapping("/bindstudent")
    public Result BindStudent(@RequestBody Students students){
        studentsSrevice.updateById(students);
        return  Result.succ("添加成功","");
    }

//    老师解绑学生
    @PostMapping("/unbindstudent")
    public Result unBindStudent(@RequestBody Students students){
        students.setTeacherid("");
        studentsSrevice.updateById(students);
        return  Result.succ("删除成功","");
    }

//    学生修改密码
    @PostMapping("/updatastudentpassword")
    public Result updataStudent(@RequestBody Map<String,String> map){
        Students students =  new Students();
        students.setStudentid(map.get("userid"));
        students.setPassword(map.get("password"));
        studentsSrevice.updateById(students);
        return Result.succ("");
    }

    @PostMapping("/updatastudent")
    public Result updataTeacher(@RequestBody Students students) {
        studentsSrevice.updateById(students);
        return Result.succ("修改成功", "");
    }

//    通过id删除学生
    @GetMapping("/deletestudentbyid")
    public Result deleteStudentById(@RequestParam("studentid") String studentid){
        boolean result = studentsSrevice.removeById(studentid);
        if (result){
            return Result.succ("删除成功","");
        }
        return Result.fail("删除失败");
    }


    //    添加学生账户
    @PostMapping("/addstudent")
    public Result addStudent(@RequestBody Students students){

        if (studentsSrevice.getById(students) == null){
            if (students.getPassword()==null||students.getPassword().equals("")){
                String password = "stu"+students.getStudentid();
                students.setPassword(password);
            }
            studentsSrevice.save(students);
            return Result.succ("添加成功","");
        }
        return Result.fail("已有此用户");
    }
    //获取学生确认状态

    @PostMapping("/studentready")
    public Result studentready(@RequestBody Students studentid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("studentid",studentid);
        Students students =  studentsSrevice.getById(studentid);
        students.setStatus(1);
        studentsSrevice.updateById(students);
        return Result.succ("信息确认成功", "");

    }
    //获取学生确认状态
    @GetMapping("/getnotreadystudent")
    public Result getnotreadystudent(){
        List<Students> studentsList = studentsSrevice.list();
        int k=0;
        for(int i=0;i<studentsList.size();i++)
            if(studentsList.get(i).getStatus()!=0)
                studentsList.remove(i);
        return Result.succ(studentsList);
    }

    @PostMapping("/getblindstudent")
    public Result getblindstudent(@RequestBody Students studentid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("studentid",studentid);
        Students students =  studentsSrevice.getById(studentid);
        students.setStatus(2);
        studentsSrevice.updateById(students);
        return Result.succ("盲审确认成功", "");

    }
    @PostMapping("/getdefensestudent")
    public Result getdefensestudent(@RequestBody Students studentid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("studentid",studentid);
        Students students =  studentsSrevice.getById(studentid);
        students.setStatus(3);
        studentsSrevice.updateById(students);
        return Result.succ("答辩确认成功", "");

    }
    //获取学生盲审成绩
    @GetMapping("/getblindscore")
    public Result getblindscore(){
        List<Students> studentsList = studentsSrevice.list();
        int k=0;
        for(int i=0;i<studentsList.size();i++)
            if(studentsList.get(i).getStatus()!=1)
                studentsList.remove(i);
        return Result.succ(studentsList);
    }
    //获取学生答辩成绩
    @GetMapping("/getdefensescore")
    public Result getdefensescore(){
        List<Students> studentsList = studentsSrevice.list();
        int k=0;
        for(int i=0;i<studentsList.size();i++)
            if(studentsList.get(i).getStatus()!=2)
                studentsList.remove(i);
        return Result.succ(studentsList);
    }



    @PostMapping("/blindready")
    public Result blindready(@RequestBody Map<String,String> map){

                   List<Topics> topicsList =topicsSrevice.list();
                   List<Students> studentsList=studentsSrevice.list();
                   String studentid="";
                   String topictitle = map.get("topictitle");
                     String Blindscore = map.get("blindscore");
                    System.out.println(topictitle);
                    System.out.println(Blindscore);
                   int  blindscore=Integer.parseInt(Blindscore);

                   for(int i=0;i<topicsList.size();i++)
                       if(topicsList.get(i).getTopictitle().equals(topictitle))
                           studentid=topicsList.get(i).getStudentid();
                   for(int i=0;i<studentsList.size();i++)
                       if(studentsList.get(i).getStudentid().equals(studentid))
                       {
                           studentsList.get(i).setBlindscore(blindscore);
                           studentsList.get(i).setStatus(2);
                           studentsSrevice.updateById(studentsList.get(i));
                       }


        return  Result.succ("添加成功","");
    }
    @PostMapping("/defenseready")
    public Result defenseready(@RequestBody String studentid,int defensescore){


        List<Students> studentsList=studentsSrevice.list();

        for(int i=0;i<studentsList.size();i++)
            if(studentsList.get(i).getStudentid().equals(studentid))
            {
                studentsList.get(i).setDefensescore(defensescore);
                studentsSrevice.updateById(studentsList.get(i));
            }


        return  Result.succ("添加成功","");
    }
}
