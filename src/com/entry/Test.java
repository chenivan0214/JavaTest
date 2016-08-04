package com.entry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.TreeMap;

import com.db.model.BasicAddressesModel;
import com.db.implement.BasicAddressesImp;

public class Test {

    public static void main(String[] args) {
        Map<String, String> regionMap = new TreeMap<String, String>();
        List<BasicAddressesModel> sectionList = null;
        List<BasicAddressesModel> streetList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        File rootPath = Paths.get(".").toAbsolutePath().normalize().toFile();
        String outputPath = rootPath.getPath() + "/output/";
        String context = null;
        
        regionMap.put("A", "台北市");
        regionMap.put("B", "新北市");
        regionMap.put("C", "基隆市");
        regionMap.put("D", "桃園市");
        regionMap.put("E", "新竹市");
        regionMap.put("F", "新竹縣");
        regionMap.put("G", "苗栗縣");
        regionMap.put("H", "台中市");
        regionMap.put("I", "彰化縣");
        regionMap.put("J", "南投縣");
        regionMap.put("K", "雲林縣");
        regionMap.put("L", "嘉義市");
        regionMap.put("M", "嘉義縣");
        regionMap.put("N", "台南市");
        regionMap.put("O", "高雄市");
        regionMap.put("P", "屏東縣");
        regionMap.put("Q", "台東縣");
        regionMap.put("R", "花蓮縣");
        regionMap.put("S", "宜蘭縣");
        regionMap.put("T", "澎湖縣");
        regionMap.put("U", "金門縣");
        regionMap.put("V", "連江縣");
        
        BasicAddressesImp basicAddressesImp = new BasicAddressesImp();
        
        for (Entry<String, String> entry : regionMap.entrySet()) {
            Map<String, String> sectionMap = new TreeMap<String, String>();
            String key = entry.getKey();
            String value = entry.getValue();
            
            sectionList = basicAddressesImp.getSection(key);
            
            if (sectionList != null) {
                for (BasicAddressesModel _BasicAddressesModel : sectionList) {
                    Map<String, String> streetMap = new TreeMap<String, String>();
                    sectionMap.put(_BasicAddressesModel.getAddrCode(), _BasicAddressesModel.getAddrName());
                    
                    streetList = basicAddressesImp.getStreet(_BasicAddressesModel.getAddrCode());
                    
                    if (sectionList != null) {
                        for (BasicAddressesModel __BasicAddressesModel : streetList) {
                            streetMap.put(__BasicAddressesModel.getAddrCode(), __BasicAddressesModel.getAddrName());
                        }
                        
                        try{
                            context = objectMapper.writeValueAsString(streetMap);
                            writeToFile(outputPath + _BasicAddressesModel.getAddrCode() + ".json", context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                try{
                    context = objectMapper.writeValueAsString(sectionMap);
                    writeToFile(outputPath + key + ".json", context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                //try {
                //    context = objectMapper.writeValueAsString(sectionList);
                //    writeToFile(outputPath + key + ".json", context);
                //} catch (Exception e) {
                //    e.printStackTrace();
                //}
                
                //for (BasicAddressesModel _BasicAddressesModel : sectionList) {
                //    streetList = basicAddressesImp.getStreet(_BasicAddressesModel.getAddrCode());
                //    
                //    if (streetList != null) {
                //        try {
                //            context = objectMapper.writeValueAsString(streetList);
                //            writeToFile(outputPath + _BasicAddressesModel.getAddrCode() + ".json", context);
                //        } catch (Exception e) {
                //            e.printStackTrace();
                //        }
                //    }
                //}
                
            }
        }
    }
    
    public static void writeToFile(String _targetPath, String _context) {
        Charset.forName("UTF-8").newEncoder();
       
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(_targetPath);
            Writer writer = new OutputStreamWriter(fileOutputStream, "UTF8");
            writer.write(_context);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
