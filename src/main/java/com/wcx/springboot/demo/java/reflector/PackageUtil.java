package com.wcx.springboot.demo.java.reflector;

import com.wcx.springboot.demo.java.generic.IBox;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PackageUtil {
    /**
     * 获取包下的所有类，进行反射
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static ArrayList getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    private Class[] getOperators(File operatorFile) throws MalformedURLException, ClassNotFoundException {
        ClassLoader operatorsLoader = new URLClassLoader(new URL[]{operatorFile.toURI().toURL()});

        File[] files = operatorFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });
        ArrayList<Class> operators = new ArrayList<>();
        for (File file : files) {
            String className = file.getName().substring(0, file.getName().length() - 6);
            Class<?> clazz = operatorsLoader.loadClass(className);
            operators.add(clazz);
        }
        return operators.toArray(new Class[operators.size()]);
    }

    /**
     * 扫描一个包，并对其中的类初始化
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            ArrayList<Class> types = PackageUtil.getClasses("com.ncs.vit.vms.file.service.controller");
            for (Class type : types) {
                if (IBox.class.isAssignableFrom(type)) {
                    try {
                        Constructor constructor = type.getConstructor();
                        constructor.setAccessible(true);
                        IBox instance = (IBox) constructor.newInstance();
                        Method init = type.getDeclaredMethod("init");
                        if (init != null) {
                            init.setAccessible(true);
                            init.invoke(instance);
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
