package danran.proxy;

import java.io.*;

/**
 * @Classname MyClassLoader
 * @Description TODO
 * @Date 2020/4/17 17:29
 * @Created by ASUS
 */
public class MyClassLoader extends ClassLoader{
    private File classPathFile;
    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath) ;
    }
    /**
     *功能描述：
     *@param  Name the name of the class
     *@Author Jason
     *Date 2020-04-17 20:47
     */
    public Class<?> findClass(String Name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName() + "." + Name ;
        if(classPathFile != null) {
            File classFile = new File(classPathFile, Name.replaceAll("\\.","/") + ".class") ;
            if(classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream os = null;
                try {
                    in = new FileInputStream(classFile);
                    os = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        os.write(buffer,0,len);
                    }
//                    将字节数组转为类实例对象返回
                    return defineClass(className,os.toByteArray(),0,os.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(null != in){
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(os != null){
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
