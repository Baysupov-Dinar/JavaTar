package main;

import java.io.*;
import java.util.List;

public class Tar {
    private List<File> fileList;
    private String outFile = "";
    private String key = "#!++";

    public Tar(List<File> fileList) {
        this.fileList = fileList;
    }

    public Tar(List<File> fileList, String outName) {
        this.fileList = fileList;
        this.outFile = outName;
    }

    public void weld() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            Boolean check=false;
            while(!check){
                if(checkKey()) check=true;
            }
            for (File elem : fileList) {
                bw.write(key);
                bw.newLine();
                bw.write(elem.getName());
                bw.newLine();
                BufferedReader br = new BufferedReader(new FileReader(elem));
                String curr;
                while ((curr = br.readLine()) != null) {
                    bw.write(curr);
                    bw.newLine();
                }
                br.close();
            }
            bw.write(key);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can't reach files");
        }
    }

    public void unWeld() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileList.get(0)))){
            String curr;
            String finalKey=br.readLine();
            while ((curr = br.readLine()) != null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(curr));
                while (!(curr = br.readLine()).equals(finalKey)) {
                    bw.write(curr);
                    bw.newLine();
                }
                bw.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean checkKey()throws IOException{
        for (File elem : fileList){
            BufferedReader br = new BufferedReader(new FileReader(elem));
            String curr;
            while((curr=br.readLine())!=null){
                if(curr.contains(key)){
                    key=key.concat("№");
                    return false;
                }
            }

        }
        return true;
    }
}
