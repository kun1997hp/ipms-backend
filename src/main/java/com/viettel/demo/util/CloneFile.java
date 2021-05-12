package com.viettel.demo.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloneFile {

    private void tableNameToFile(){
        try {
            String tables =
                    "SL_CORE_UNKNOWN\n" +
                    "SL_CORE_PS\n" +
                    "SL_CORE_MPBN\n"+
                    "SL_CORE_METRO_CTCV\n" +
                    "SL_CORE_METRO_AGG\n" +
                    "SL_CORE_IPTH\n" +
                    "SL_CORE_IPBN\n" +
                    "SL_CORE_DCN\n" +
                    "SL_ACCESS_UNKNOWN\n" +
                    "SL_ACCESS_METRO_KV1\n" +
                    "SL_ACCESS_METRO_KV2\n" +
                    "SL_ACCESS_METRO_KV3\n" +
                    "SL_ACCESS_KV3\n" +
                    "SL_ACCESS_KV2\n" +
                    "SL_ACCESS_KV1\n"
                    ;
            String baseTable="";
            String baseModel="";
            String baseUrl="";
            String baseController="";
            String baseService="";
            String baseRepository="";
            String[] tableNames = tables.split("\n");
            ArrayList<String> bases=new ArrayList<>();
            for (int i = 0; i < tableNames.length; i++) {
                if (i == 0) {
                    baseTable = tableNames[i].trim();
                    baseUrl = baseTable.replaceAll("_", "-").toLowerCase();
                    baseModel = tableNameToModelName(baseTable);
                    baseController = baseModel + "Controller";
                    baseService = baseModel + "Service";
                    baseRepository = baseModel + "Repository";
                    bases = new ArrayList<>(
                            Arrays.asList(baseTable,baseUrl,baseModel,baseController,baseService,baseRepository));
                    System.out.println(baseUrl);
                    continue;
                }

                String tableName = tableNames[i].trim();
                String url = tableName.replaceAll("_", "-").toLowerCase();
                System.out.println(url);
                String model = tableNameToModelName(tableName);
                String controller = model + "Controller";
                String service = model + "Service";
                String repository = model + "Repository";
                ArrayList<String> news = new ArrayList<>(
                        Arrays.asList(tableName,url,model,controller,service,repository));

                String path = "F:\\ipms-backend\\src\\main\\java\\com\\viettel\\demo\\";
                String pathModel = "model\\entity\\";
                String pathController = "controller\\";
                String pathService = "service\\";
                String pathRepository = "repository\\";

                String pathBaseModelFile = path + pathModel + baseModel + ".java";
                String pathBaseControllerFile = path + pathController + baseController + ".java";
                String pathBaseServiceFile = path + pathService + baseService + ".java";
                String pathBaseRepositoryFile = path + pathRepository + baseRepository + ".java";

                String pathModelFile = path + pathModel + model + ".java";
                String pathControllerFile = path + pathController + controller + ".java";
                String pathServiceFile = path + pathService + service + ".java";
                String pathRepositoryFile = path + pathRepository + repository + ".java";

                checkAndCopyFile( pathBaseModelFile,  pathModelFile);
                checkAndCopyFile( pathBaseControllerFile,  pathControllerFile);
                checkAndCopyFile( pathBaseServiceFile,  pathServiceFile);
                checkAndCopyFile( pathBaseRepositoryFile,  pathRepositoryFile);

                replace(pathModelFile, bases, news);
                replace(pathControllerFile, bases, news);
                replace(pathServiceFile, bases, news);
                replace(pathRepositoryFile, bases, news);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkAndCopyFile(String baseFile, String newFile){
        try {
            File f = new File(newFile);
            if (!f.exists()) {
                Files.copy(Paths.get(baseFile),
                        Paths.get(newFile));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void replace(String pathReplaceFile, List<String> bases, List<String> news){
        try {
            Charset charset = StandardCharsets.UTF_8;
            Path path = Paths.get(pathReplaceFile);
            String content = new String(Files.readAllBytes(path), charset);
            for(int i=0; i<bases.size();i++){
                content = content.replaceAll(bases.get(i).substring(1), news.get(i).substring(1));
            }
            Files.write(path, content.getBytes(charset));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String tableNameToModelName(String tableName){
        List<String> tableNames = Arrays.asList(tableName.split("_"));
        String modelName = "";

        for(String name : tableNames){
            modelName +=name.substring(0,1)+name.substring(1).toLowerCase();
        }
        return modelName;
    }

    public static void main(String[] args) throws IOException {
        new CloneFile().tableNameToFile();
    }
}
