package com.example.xiyan.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyan.testapplication.bean.Person;
import com.example.xiyan.testapplication.widget.Prompt;
import com.supconit.azpt.db.DBUtil.Condition;
import com.supconit.azpt.db.DBUtil.DBUtil;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZListener;
import com.supconit.azpt.ioc.annotation.AZView;
import com.supconit.azpt.ioc.constant.ListenerType;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@AZActivity(R.layout.activity_db)
public class DBActivity extends AppCompatActivity {

    @AZView
    private Button updatebt,save,deletebt,deleteTabbt,createbt,searchbt,searchdoub;

    @AZView
    private TextView id,name,age,sex,other,birth,search,searchage;


    @AZView
    private EditText idvalue,namevalue,agevalue,sexvalue,othervalue,birthvalue,searchvalue,searchagevalue;

    public static final String BUTTON1_TEST = "BUTTON_TEST_KEY";
    public static final String BUTTON1_SAVE = "BUTTON_SAVE_KEY";
    public static final String BUTTON1_CONNET = "BUTTON_CONNET_KEY";

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @AZView
    private Prompt prompt;

    private DBUtil dbUtil;

    @AZAfter
    public void init() {
        try {
            dbUtil = new DBUtil(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //按钮button事件onclick
    @AZListener(value = {R.id.updatebt,R.id.deletebt,R.id.save,R.id.deleteTabbt,R.id.createbt,R.id.searchbt, R.id.searchdoub}, type= ListenerType.Click)
    public void buttonClick(View view) {
        if(view.getId() == R.id.save) {
            Person people = new Person();
            people.setName(namevalue.getText().toString().trim());
            people.setSex(sexvalue.getText().toString().trim());
            people.setAge(agevalue.getText().toString().trim());
            people.setOther(othervalue.getText().toString().trim());


            Date dd;
            try {
                dd=dateFormat.parse(birthvalue.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
                dd=new Date();
            }
            people.setBirth(dd);

            dbUtil.save(people);
            //Cache.getInstance(this).getAsString(BUTTON1_KEY);
            String msg ="使用方法\n" +
                    "dbUtil.save(people)\n "+
                    findAll();
            prompt.show("保存数据", msg);


        }else if (view.getId() == R.id.searchbt){
            Person selectPer = new Person();
            selectPer.setName(searchvalue.getText().toString().trim());
            try {
                //String[] column = {"name","age"};
                //List<Person> findPer = dbUtil.find(selectPer,column);
                //List<Person> findPer = dbUtil.find(selectPer,null);
                selectPer.setAge(null);
                selectPer.setSex(null);
                selectPer.setOther(null);
                selectPer.setBirth(null);
                List<Person> findPer = dbUtil.find(selectPer);
                String str = "";
                for (Person fson: findPer){
                    str += "Id："+fson.getId()+" \t name："+fson.getName()+" \t age："+fson.getAge()+"  \t sex："+fson.getSex()+ "  \t birth："+dateFormat.format(fson.getBirth())+  " \t other："+fson.getOther()+ " \n";
                }
                String deleteIdMsg ="使用方法\n" +
                        "dbUtil.find(selectPer,column)\n返回结果\n" +
                        str;
                prompt.show("单条件查询", deleteIdMsg);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        }else if (view.getId() == R.id.searchdoub){

            Person selectPer = new Person();
            selectPer.setName(searchvalue.getText().toString().trim());
            selectPer.setAge(searchagevalue.getText().toString().trim().equals("") ? null : searchagevalue.getText().toString().trim());
            selectPer.setSex(null);
            selectPer.setOther(null);
            selectPer.setBirth(null);
            try {
                //String[] column = {"name","age"};
                //List<Person> findPer = dbUtil.find(selectPer,column);
                List<Person> findPer = dbUtil.find(selectPer);
                String str = "";
                for (Person fson: findPer){
                    str += "Id："+fson.getId()+" \t name："+fson.getName()+" \t age："+fson.getAge()+"  \t sex："+fson.getSex()+ "  \t birth："+dateFormat.format(fson.getBirth())+  " \t other："+fson.getOther()+ " \n";
                }
                String deleteIdMsg ="使用方法\n" +
                        "dbUtil.find(selectPer, column)\n返回结果\n" +
                        str;
                prompt.show("两个条件查询", deleteIdMsg);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


        }else if (view.getId() == R.id.updatebt){

            Person selectPer = new Person();
            selectPer.setName(namevalue.getText().toString().trim());
            selectPer.setSex(sexvalue.getText().toString().trim());
            selectPer.setAge(agevalue.getText().toString().trim());
            selectPer.setOther("修改备注信息");

            String updateage = agevalue.getText().toString().trim();
            if(!TextUtils.isEmpty(updateage)){
                Condition condition = new Condition();
                condition.put(Condition.Logic.NULL, "age", Condition.Relation.EQUAL, Integer.valueOf(updateage));
                boolean b = dbUtil.update(selectPer, condition);
            }else{
                Toast.makeText(this, "Id不能为空", Toast.LENGTH_SHORT).show();
            }

            String updateMsg ="使用方法\n" +
                    "dbUtil.change(son)\n返回结果\n" +
                    findAll();
            prompt.show("修改数据", updateMsg);

        }else if (view.getId() == R.id.deletebt){
            Person selectPer = new Person();
            boolean delete = false;
            String deletename = namevalue.getText().toString().trim();
            String deleteage =agevalue.getText().toString().trim();
            if(!TextUtils.isEmpty(deletename)){
                Condition condition = new Condition();
                condition.put(Condition.Logic.NULL, "name", Condition.Relation.EQUAL, deletename);
                condition.put(Condition.Logic.AND, "age", Condition.Relation.EQUAL, deleteage);
                delete = dbUtil.delete(Person.class, condition);
            }else{
                Toast.makeText(this, "name不能为空",Toast.LENGTH_SHORT).show();
            }
            String deleteMsg ="使用方法\n" +
                    "dbUtil.delete(new Son(), condition)\n返回结果\n" +
                    delete;
            prompt.show("删除数据", deleteMsg);


        }else if (view.getId() == R.id.deleteTabbt){
            dbUtil.dropTable(Person.class);
            String deleteMsg ="使用方法\n" +
                    "dbUtil.dropTable(Person.class)\n";
            prompt.show("删除数据表", deleteMsg);


        }else if (view.getId() == R.id.createbt){
            dbUtil.createTable(Person.class);
            String deleteMsg ="使用方法\n" +
                    "dbUtil.createTable()\n";
            prompt.show("新建数据表", deleteMsg);
        }


    }



    private String findAll(){
        String str = "返回字段数值：\n";
        String[] column = {"id","name","age"};
        Condition condition = new Condition();
        condition.put(Condition.Logic.NULL, "id", Condition.Relation.NOTEQUAL, 1);
        try {
            //List<Person> persons = dbUtil.findCondition(Person.class, condition, null);
//            List<Person> persons = dbUtil.findCondition(Person.class, null, column);
            //List<Person> persons = dbUtil.findCondition(Person.class, condition, column);
           List<Person> persons = dbUtil.findCondition(Person.class, null, column);
            for (Person son: persons){
                str += "Id："+son.getId()+" \t name："+son.getName()+" \t age："+son.getAge()+"  \t sex："+son.getSex()+ "  \t birth："+dateFormat.format(son.getBirth())+  " \t other："+son.getOther()+ " \n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

}
