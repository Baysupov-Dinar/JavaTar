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
            }
            if (args[i].equals("-out")) {
                if (!fileList.isEmpty()) throw new IllegalArgumentException();
                String outName = "";
                for (int j = 0; j < args.length; j++) {
                    if (args[j].equals("-out")) {
                        j++;
                        outName = args[j];
                    } else {
                        File tmpFile = new File(args[j]);
                        if (tmpFile.exists()) fileList.add(tmpFile);
                    }
                }
                new Tar(fileList, outName).weld();
            }
        }
        if (fileList.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
