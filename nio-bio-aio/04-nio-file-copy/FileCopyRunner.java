import java.io.File;

/**
 * 文件拷贝接口
 */
public interface FileCopyRunner {
    /**
     * 拷贝文件
     *
     * @param source 源文件
     * @param target 目标文件
     */
    void copyFile(File source, File target);
}