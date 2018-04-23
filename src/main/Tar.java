package main;

import java.io.*;
import java.util.List;

public class Tar {
    private List<File> fileList;
    private String outFile = "";

    public Tar(List<File> fileList) {
        this.fileList = fileList;
    }

    public Tar(List<File> fileList, String outName) {
        this.fileList = fileList;
        this.outFile = outName;
    }

    public void weld() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            for (File elem : fileList) {
                bw.write(elem.getName());
                bw.newLine();
                BufferedReader br = new BufferedReader(new FileReader(elem));
                String curr;
                while ((curr = br.readLine()) != null) {
                    bw.write(curr);
                    bw.newLine();
                }
                bw.write("#!++");
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unWeld() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileList.get(0)));
            String curr;
            while ((curr = br.readLine()) != null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(curr));
                while (!(curr = br.readLine()).equals("#!++")) {
                    bw.write(curr);
                    bw.newLine();
                }
                bw.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
