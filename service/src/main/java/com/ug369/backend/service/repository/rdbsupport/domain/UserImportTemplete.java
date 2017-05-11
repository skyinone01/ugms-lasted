package com.ug369.backend.service.repository.rdbsupport.domain;

public class UserImportTemplete {

    public static final String[] STATS_TEMPLETE = new String[]{"用户名", "年龄", "性别", "手机号", "终端", "访问时长","注册时间","城市"};

    public static final String[] STATS_USER_AGE = new String[]{"用户总数",	"男",	"女",	"30岁以下",	"30~40岁",	"40~50岁",	"50~60岁",	"60~70岁",	"70~80岁",	"80以上"};


    public static final String[] STATS_MODULE_ACC = new String[]{"首页","生活日记","健康评测","健康报告","自愈系统","营养品", "慢病管理","快捷统计",
                                                                     "我的画像","中医养生","工具","婴幼儿","我的病历","饮食","运动","工作",
                                                                     "心情","睡眠","排泄","起居","体感","用药记录"};

    public static Integer indexOfAge(String key){
        return indexOf(STATS_USER_AGE, key);
    }
    public static Integer indexOfModuleAcc(String key){
        return indexOf(STATS_MODULE_ACC, key);
    }

    public static Integer indexOf(String[] templete, String key){
        for(int index=0; index<templete.length; index++){
            if(templete[index].equals(key)){
                return index;
            }
        }
        return null;
    }

}
