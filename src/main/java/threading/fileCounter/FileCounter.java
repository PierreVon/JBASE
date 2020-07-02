package threading.fileCounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Queue;

public class FileCounter {
    private String path = ".";
    private int maxSize = Integer.MIN_VALUE;
    private int minSize = Integer.MAX_VALUE;
    private int aveSize = 0;
    private int totalSize = 0;
    private int totalNumberOfFiles = 0;
    private boolean fileStat = false;

    FileCounter(String path) {
        this.path = path;
    }

    FileCounter() {
    }

    /**
     * print all folders and files with its size.
     */
    public void scan() {
        File file = new File(path);
        String prefix = "";
        scanDfs(file, prefix);
    }

    private void scanDfs(File file, String prefix) {
        if (file == null) return;
        if (file.isDirectory()) {
            System.out.println(prefix + file.getName());
            for (File f : file.listFiles()) {
                scanDfs(f, "  " + prefix);
            }
        } else {
            System.out.printf("%s %dKB\n", prefix + file.getName(), file.length());
        }
    }

    /**
     * print number of files beneath a folder.
     */
    public void count() {
        File file = new File(path);
        countDfs(file);
    }

    private void countDfs(File file) {
        if (file == null || file.isFile()) return;
        System.out.printf("%s: %d\n", file.getPath(), file.list().length);
        for (File f : file.listFiles()) countDfs(f);
    }

    /**
     * print max size, min size, average size and number of files.
     */
    public void fileStat() {
        if (fileStat) {
            fileStatPrint();
        } else {
            fileStatDfs(new File(path));
            aveSize = totalSize / totalNumberOfFiles;
            fileStat = true;
            fileStatPrint();
        }
    }

    private void fileStatDfs(File file) {
        if (file == null) return;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) fileStatDfs(f);
        } else {
            maxSize = (int) Math.max(maxSize, file.length());
            minSize = (int) Math.min(minSize, file.length());
            totalSize += file.length();
            totalNumberOfFiles++;
        }
    }

    private void fileStatPrint() {
        System.out.println("------------file stat------------");
        System.out.printf("Max Size:               %dKB\n", maxSize);
        System.out.printf("Min Size:               %dKB\n", minSize);
        System.out.printf("Ave Size:               %dKB\n", aveSize);
        System.out.printf("Total Size:             %dKB\n", totalSize);
        System.out.printf("Total number of files:  %d\n", totalNumberOfFiles);
        System.out.println("---------------------------------");
    }

    public void watch() {
        WatchService ws = null;
        try {
            ws = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Paths.get(path).register(ws,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            WatchKey key = null;
            try {
                key = ws.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WatchEvent<?> e : key.pollEvents()) {
                System.out.println(e.context() + "---" + e.kind() + "---" + e.count());
            }
            if(!key.reset()){
                break;
            }
        }
    }

    public static void main(String[] args) {
        FileCounter fc = new FileCounter();
        fc.scan();
        fc.count();
        fc.fileStat();
        fc.watch();
    }
}
