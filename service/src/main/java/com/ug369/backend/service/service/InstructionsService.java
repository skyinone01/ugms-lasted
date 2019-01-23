//package com.ug369.backend.service.service;
//
//import com.ug369.backend.bean.base.request.PageRequest;
//import com.ug369.backend.bean.result.PagedResult;
//import com.ug369.backend.service.entity.mysql.Content;
//import com.ug369.backend.service.entity.mysql.Instructions;
//import com.ug369.backend.service.entity.mysql.InstructionsXH;
//import com.ug369.backend.service.repository.mysql.InstructionsRepository;
//
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//
//@Service
//public class InstructionsService {
//    @Autowired
//    private InstructionsRepository instructionsRepository;
//
//    public PagedResult<InstructionsXH> selectXH(PageRequest pageRequest) {
//    	PagedResult<InstructionsXH> Instructions = instructionsRepository.getDataPageBatch("Instructions.selectXH", "Instructions.getCount", new HashMap<>(),pageRequest);
////        List<InstructionsXH> list=instructionsRepository.getData("Instructions.selectXH", null);
//        List<InstructionsXH> retlist=new ArrayList<>();
//        String serialNumber="";
//        for(InstructionsXH instructionsXH : Instructions.getItems()){
//            serialNumber="";
//            serialNumber=getSerialNumber(instructionsXH.getPaths().toString(),instructionsXH.getOrderIndex().toString(),Instructions.getItems());
//            instructionsXH.setSerialnumber(serialNumber);
//            retlist.add(instructionsXH);
//        }
//        Collections.sort(retlist,
//                new Comparator<InstructionsXH>(){
//                    @Override
//                    public int compare(InstructionsXH o1, InstructionsXH o2) {
//                        return o1.getSerialnumber().toString().compareTo(o2.getSerialnumber().toString());
//                    }
//                });
//        Instructions.getItems().clear();
//        for(InstructionsXH instructionsXH : retlist){
//        	Instructions.getItems().add(instructionsXH);
//        }
//        return Instructions;
//    }
//    private String getSerialNumber(String paths,String orderIndex,List<InstructionsXH> list){
//        String xh="";
//        String[] strArr=paths.split(",");
//        for(int i=0;i<strArr.length;i++){
//            if(!strArr[i].equals("0")&&!strArr[i].equals("")){
//                if(!xh.equals("")){
//                    xh=xh+"."+getOrderIndex(strArr[i],list);
//                }else {
//                    xh=getOrderIndex(strArr[i],list);
//                }
//            }
//        }
//        if(!xh.equals(""))
//            xh=xh+"."+orderIndex;
//        else
//            xh=orderIndex;
//        return xh;
//    }
//
//    private String getOrderIndex(String id,List<InstructionsXH> list){
//        String orderIndex="";
//        for(InstructionsXH map : list ){
//            if(map.getId() == Long.parseLong(id)){
//                orderIndex=map.getOrderIndex().toString();
//                break;
//            }
//        }
//        return orderIndex;
//    }
//
//    @Transactional
//    public void delete(long id) {
//    	instructionsRepository.deleteData("Instructions.delete",id);
//    }
//
//    @Transactional
//    public void save(Instructions instructions) {
//    	instructionsRepository.insertData("Instructions.add", instructions);
//    }
//
//    @Transactional
//    public void update(Instructions instructions) {
//    	instructionsRepository.updateData("Instructions.update", instructions);
//    }
//
//    public Instructions findOne(long id) {
//    	Instructions one = instructionsRepository.getObject("Instructions.findOne", id);
//        return one;
//    }
//}
