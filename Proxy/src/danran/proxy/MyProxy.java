package danran.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MyProxy
 * @Description TODO
 * @Date 2020/4/17 17:27
 * @Created by Jason
 */
public abstract class MyProxy {
//    换行字符串
    private final static String LN = "\r\n";
//    产生动态代理实例对象
    public static Object newProxyInstance(MyClassLoader classLoader,
                                          Class<?> [] interfaces,
                                          MyInvocationHandler handler) {
        try {
//            动态产生源代码.java文件
            String src = generateSourceFile(interfaces);
/*            java文件输出到磁盘中*/
//            MyProxy.class.getResource("");查找具有给定名称的资源。
//            用于搜索与给定类相关联的资源的规则由该类的定义class loader实现。
//            此方法委托给该对象的类加载器。 如果此对象由引导类加载器加载，
//            则该方法将委托给ClassLoader.getSystemResource(java.lang.String)
            URL url = MyProxy.class.getResource("");
            String path = url.getPath();//获取这个 URL的路径部分。
//            创建一个新的文件实例，将给定的字符串转为抽象地址路径
            File file = new File(path + "$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(src);//将生成的代码写入到文件：$Proxy0.java中
            fw.flush();
            fw.close();

           /* 将生成的Java文件编译成.class文件*/
//            获得programming language compiler编译器对象
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = javaCompiler
                    .getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);
//            Creates a future for a compilation task with the given
//            components and arguments.
            JavaCompiler.CompilationTask task = javaCompiler
                    .getTask(null,manager,null,null,null,iterable);
            task.call();//启动编译
            manager.close();

            /*将编译生成的.class文件加载到JVM中*/
//            在类路径中寻找到$Proxy0.class文件并返回对象
            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            assert proxyClass != null;
            Constructor<?> constructor = proxyClass.getConstructor(MyInvocationHandler.class);
            file.delete();//删除.java文件
//            返回字节码重组后的新的代理对象
//            选中翻译部分，右键
            return constructor.newInstance(handler);//反射创建新的代理对象
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//    创建.java源文件，传入需求要实现的接口列表 interfaces
    private static String  generateSourceFile(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package danran.proxy;" + LN);
        sb.append("import java.lang.reflect.*;" + LN);
        sb.append("import danran.proxy.Person;" + LN);
        sb.append("public class $Proxy0 implements ")
                .append(interfaces[0].getName())
                .append("{").append(LN);//实现的列表接口，目前只有一个
        sb.append("MyInvocationHandler h;" + LN);//记录被代理的对象的引用
        sb.append("public $Proxy0(MyInvocationHandler h) {" + LN);
        sb.append("this.h = h;" + LN);
        sb.append("}" + LN);
//        实现接口的方法
        for (Method method : interfaces[0].getMethods()) {
            Class<?> [] params = method.getParameterTypes();//参数类型列表
            StringBuilder paramNames = new StringBuilder();//参数名
            StringBuilder paramValues = new StringBuilder();//参数值
            StringBuilder paramClasses = new StringBuilder();//参数类名
            for (int i = 0; i < params.length; i++) {
                Class<?> clazz = params[i];
                String type = clazz.getName();
                
                System.out.println(type);
                
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                System.out.println(paramName);
                paramNames.append(type).append(" ").append(paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName()).append(".class");
                if(i > 0 && i < params.length - 1 ){
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            sb.append("public ").append(method.getReturnType().getName())
                    .append(" ").append(method.getName()).append("(")
                    .append(paramNames.toString()).append(") {").append(LN);
            sb.append("try{" + LN);
            sb.append("Method m = ").append(interfaces[0].getName())
                    .append(".class.getMethod(\"").append(method.getName())
                    .append("\",new Class[]{").append(paramClasses.toString()).append("});").append(LN);
            sb.append(hasReturnValue(method.getReturnType()) ? "return " : "")
                    .append(getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})", method.getReturnType()))
                    .append(";").append(LN);
            sb.append("}catch(Error ex){ }");
            sb.append("catch(Throwable e){" + LN);
            sb.append("throw new UndeclaredThrowableException(e);" + LN);
            sb.append("}");
            sb.append(getReturnEmptyCode(method.getReturnType()));
            sb.append("}");
        }
        sb.append("}" + LN);
        return sb.toString();
    }

    private static Map<Class , Class> mappings = new HashMap<>();
    static{
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnType) {
        if(mappings.containsKey(returnType)){
            return "return 0;";
        }else if(returnType == void.class){
            return "return;";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String s, Class<?> returnType) {
        if(mappings.containsKey(returnType)){
            return "((" + mappings.get(returnType).getName() + ")" + s + ")." + returnType.getSimpleName() + "value()";
        }
        return s;
    }

    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class ;
    }

    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;//将首字母改为小写
        return String.valueOf(chars);
    }
}
