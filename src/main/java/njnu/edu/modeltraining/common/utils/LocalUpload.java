package njnu.edu.modeltraining.common.utils;

import njnu.edu.modeltraining.common.exception.MyException;
import njnu.edu.modeltraining.common.result.ResultEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/07/18/9:57
 * @Description:
 */
public class LocalUpload {

    public static void UploadFile(MultipartFile multipartFile, String name, String dir) {
        File file = new File(dir);
        if(!file.exists()) {
            file.mkdirs();
        }
        InputStream ins = null;
        FileOutputStream outs = null;
        try {
            ins = multipartFile.getInputStream();
//            outs = new FileOutputStream(dir + "\\" + name);
            outs = new FileOutputStream(dir + "/" + name);
            int len;
            byte[] bytes = new byte[1024];
            while((len = ins.read(bytes)) != -1) {
                outs.write(bytes, 0, len);
            }
            outs.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
                if(outs != null) {
                    outs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(ResultEnum.DEFAULT_EXCEPTION);
            }
        }
    }

    public static int merge(String from, String to, int total) {
        FileInputStream in = null;
        FileChannel inChannel = null;
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            out = new FileOutputStream(new File(to), true);
            outChannel = out.getChannel();
            long start = 0;
            for (int i = 0; i < total; i++) {
//                File file = new File(from + "\\" + i);
                File file = new File(from + "/" + i);
                in = new FileInputStream(file);
                inChannel = in.getChannel();
                outChannel.transferFrom(inChannel, start, file.length());
                start += file.length();
                in.close();
                inChannel.close();
            }
            out.close();
            outChannel.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
                if(inChannel != null) {
                    inChannel.close();
                }
                if(outChannel != null) {
                    outChannel.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    public static boolean deleteFolder(String path) {
        File file = new File(path);
        if(!file.exists()) {
            return false;
        } else {
            if(file.isFile()) {
                return deleteFile(path);
            } else {
                return deleteDirectory(path);
            }
        }
    }

    private static boolean deleteFile(String path) {
        File file = new File(path);
        if(file.exists() && file.isFile()) {
            file.delete();
            return true;
        }
        return false;
    }

    private static boolean deleteDirectory(String path) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for(File f : files) {
            if(f.isFile()) {
                flag = deleteFile(f.getAbsolutePath());
                if(!flag) break;
            } else {
                flag = deleteDirectory(f.getAbsolutePath());
                if(!flag) break;
            }
        }
        if(!flag) return false;

        if(dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
