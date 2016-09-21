package org.windwant.designpattern.creation.singleton;

/**
 * Created by aayongche on 2016/9/19.
 */
public class SingletonWithSynchronizedMethod {

    private static SingletonWithSynchronizedMethod singletonWithSynchronizedMethod;

    /* ˽�й��췽������ֹ��ʵ���� */
    private SingletonWithSynchronizedMethod(){}

    /* �˴�ʹ��һ���ڲ�����ά������ */
    private static synchronized void initInstance(){
        if(singletonWithSynchronizedMethod == null) {
            singletonWithSynchronizedMethod = new SingletonWithSynchronizedMethod();
        }
    }

    /* ��ȡʵ�� */
    public static SingletonWithSynchronizedMethod getInstance(){
        if(singletonWithSynchronizedMethod == null){
            initInstance();
        }
        return singletonWithSynchronizedMethod;
    }

    /* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */
    public Object readResolve(){
        return getInstance();
    }

    public void test(){
        System.out.println("singleton method test");
    }
}
