package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void main(String[] args) {
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                fileList.add(new File(args[i + 1]));
                new Tar(fileList).unWeld();
                break;
            }
            if (args[i].equals("-out")) {
                if (fileList.isEmpty()) throw new IllegalArgumentException();
                String outName=args[i+1];
                new Tar(fileList, outName).weld();
                break;
            }
            else{
                File tmpFile=new File(args[i]);
                if(tmpFile.exists())fileList.add(tmpFile);
                else throw new IllegalArgumentException("Some files does not exist");
            }

        }
        if (fileList.isEmpty()) {
            System.out.println("Error, illegal arguments");
        }
    }
}
