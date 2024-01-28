package com.zs.androidappfw;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JavaLangUnitTest {
    class A {
        public void a() {}
        void aa() {}
    }

    class B extends A {

    }

    @Test
    public void print() throws NoSuchMethodException {
        System.out.println(Integer.toHexString(A.class.getDeclaredMethod("a").getModifiers()));
        System.out.println(Integer.toHexString(A.class.getDeclaredMethod("aa").getModifiers()));
        System.out.println(String.class.getSuperclass());
        System.out.println(Object.class.getSuperclass());
        System.out.println(B.class.getSuperclass());
        System.out.println(B.class.getSuperclass().getSuperclass());
        System.out.println(B.class.getSuperclass().getSuperclass().getSuperclass());

        Method[] methods = Object.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getReturnType() + " //// " + method.getGenericReturnType());
            System.out.println(method.getReturnType().equals(void.class) + " --Void-- " + method.getGenericReturnType().equals(Void.TYPE));
            System.out.println(method.getReturnType().equals(String.class) + " --String-- ");
        }

        Properties properties = System.getProperties();
        for (String key : properties.stringPropertyNames()) {
            System.out.println("Properties:  " + key + " = " + properties.getProperty(key, ""));
        }

        Map<String, String> map = System.getenv();
        for (String key : map.keySet()) {
            System.out.println("Environment:  " + key + " = " + map.getOrDefault(key, ""));
        }
    }
    
    @Test
    public void printGetResources() {
        String[] classArray = {
                "java/lang/Class.class",
                "com/zs/androidappfw/JavaLangUnitTest.class",

                "java/lang",
                "java/lang/reflect",
                "com/zs/androidappfw",
        };

        Class clazz = Class.class;
        System.out.println(clazz.isPrimitive());
        ClassLoader clazzLoader = clazz.getClassLoader();
        Class myClass = JavaLangUnitTest.class;
        ClassLoader myClassLoader = myClass.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println();
        for (String str : classArray) {
            System.out.println(str + ", " + clazz.getResource(str));
            System.out.println(str + ", " + myClass.getResource(str));
            System.out.println(str + ", " + myClassLoader.getResource(str));
            System.out.println(str + ", " + systemClassLoader.getResource(str));
            System.out.println(str + ", " + contextClassLoader.getResource(str));

            Enumeration<URL> myClassLoaderResources = null;
            try {
                myClassLoaderResources = myClassLoader.getResources(str);
                System.out.println(str + ", " + myClassLoaderResources.hasMoreElements());
                while (myClassLoaderResources.hasMoreElements()){
                    URL url = myClassLoaderResources.nextElement();
                    System.out.println("    " + url);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Enumeration<URL> systemClassLoaderResources = null;
            try {
                systemClassLoaderResources = systemClassLoader.getResources(str);
                System.out.println(str + ", " + systemClassLoaderResources.hasMoreElements());
                while (systemClassLoaderResources.hasMoreElements()){
                    URL url = systemClassLoaderResources.nextElement();
                    System.out.println("    " + url);

                    String packageName = str.replace('/', '.');
                    List<Class<?>> classes = new ArrayList<>();
                    System.out.println("    " + url + ", " + url.toURI()
                            + ", getAuthority=" + url.getAuthority()
                            + ", getFile=" + url.getFile()
                            + ", getRef=" + url.getRef()
                            + ", getProtocol=" + url.getProtocol()
                            + ", getHost=" + url.getHost()
                            + ", getContent=" + url.getPort()
                            + ", getPath=" + url.getPath()
                            + ", getQuery=" + url.getQuery()
                            + ", getUserInfo=" + url.getUserInfo()
                            + ", getContent=" + url.getContent()
                    );
                    File directory = new File(url.getFile());
                    if (url.toString().endsWith(".class")) {
                        continue;
                    }
                    System.out.println("    --->" + directory);
                    findAndAddClassesInDirectory(directory, packageName, true, classes);

                    System.out.println("    size = " + classes.size());

                    for (Class<?> clazzR : classes) System.out.println("        " + clazzR.getCanonicalName());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            Enumeration<URL> contextClassLoaderResources = null;
            try {
                contextClassLoaderResources = contextClassLoader.getResources(str);
                System.out.println(str + ", " + contextClassLoaderResources.hasMoreElements());
                while (contextClassLoaderResources.hasMoreElements()){
                    URL url = contextClassLoaderResources.nextElement();
                    System.out.println("    " + url);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        }
    }

    @Test
    public void printJavaLangUnitTest() throws Exception {

        System.out.println();
        List<Class<?>> list = getClassesInPackage("com.zs.androidappfw");
        for (Class clazz : list) {
            System.out.println(clazz.getCanonicalName());
        }
        System.out.println("size=" + list.size() + ", " + String.class.getPackageName());
    }

    private static List<Class<?>> getClassesInPackage(String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        Package[] packages = Package.getPackages();
        for (Package pkg : packages) {
            System.out.println(pkg);
            if (pkg.getName().equalsIgnoreCase(packageName)) {
                String path = pkg.getName().replace('.', '/');
                System.out.println(path);
                try {
                    Enumeration<URL> resources = Thread.currentThread()
                            .getContextClassLoader().getResources(path);
                    System.out.println(resources + ", more=" + resources.hasMoreElements());


                    while (resources.hasMoreElements()) {
                        URL resourceUrl = resources.nextElement();
                        System.out.println("---> " + resourceUrl);
                        File directory = new File(resourceUrl.toURI());
                        findAndAddClassesInDirectory(directory, packageName, true, classes);
                    }
                } catch (URISyntaxException | IOException e) {
                    System.out.println(e.toString());
                    throw new RuntimeException(e);
                }
            }
        }

        return classes;
    }

    private static void findAndAddClassesInDirectory(File directory, String packageName, boolean recursive, List<Class<?>> classes) throws ClassNotFoundException {
        if (!directory.exists()) {
            System.out.println("file not exist");
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".class"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println(file.getName() + ", " + packageName);
                if (file.isDirectory() && recursive) {
                    findAndAddClassesInDirectory(file, packageName + "." + file.getName(), recursive, classes);
                } else {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().lastIndexOf('.'));
                    System.out.println(className);
                    classes.add(Class.forName(className));
                }
            }
        }
    }
}
