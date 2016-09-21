package org.windwant.designpattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.windwant.designpattern.creation.builder.MyRobustBuilder;
import org.windwant.designpattern.creation.builder.Robust;
import org.windwant.designpattern.creation.builder.RobustDirector;
import org.windwant.designpattern.creation.factory.Sender;
import org.windwant.designpattern.creation.factory.factory.Factory;
import org.windwant.designpattern.creation.factory.factory.MailSenderFactory;
import org.windwant.designpattern.creation.factory.factory.SMSSenderFactory;
import org.windwant.designpattern.creation.factory.virtualfactory.MailVirtualFactory;
import org.windwant.designpattern.creation.factory.virtualfactory.SMSVirtualFactory;
import org.windwant.designpattern.creation.factory.virtualfactory.VirtualFactory;
import org.windwant.designpattern.creation.prototype.ProtoTypePattern;
import org.windwant.designpattern.creation.singleton.SingletonWithInnerClass;
import org.windwant.designpattern.creation.singleton.SingletonWithSynchronizedMethod;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class PatternTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PatternTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PatternTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSingleton() {
        SingletonWithInnerClass.getInstance().test();
        SingletonWithSynchronizedMethod.getInstance().test();
    }

    public void testProtoType() throws IOException, ClassNotFoundException, CloneNotSupportedException {
        ProtoTypePattern protoTypePattern = new ProtoTypePattern();
        protoTypePattern.getList().add("NJL");
        System.out.println("origin: " + protoTypePattern.getList());

        ProtoTypePattern protoTypePatternSimple = (ProtoTypePattern) protoTypePattern.clone();
        protoTypePatternSimple.getList().add("NIE");
        System.out.println("origin: " + protoTypePattern.getList());
        System.out.println("simple: " + protoTypePatternSimple.getList());

        ProtoTypePattern protoTypePatternCopy = (ProtoTypePattern) protoTypePattern.deepClone();
        protoTypePatternCopy.getList().add("WINDWANT");
        System.out.println("origin: " + protoTypePattern.getList());
        System.out.println("copy: " + protoTypePatternCopy.getList());
    }

    public void testFactory(){
        Factory mailFactory = new MailSenderFactory();
        Sender mailSender = mailFactory.produce();
        mailSender.send();
        Factory smsFactory = new SMSSenderFactory();
        Sender smsSender = smsFactory.produce();
        smsSender.send();
    }

    public void testVirtualFactory(){
        VirtualFactory mail = new MailVirtualFactory();
        mail.produceSender().send();
        mail.produceReceiver().receive();
        VirtualFactory sms = new SMSVirtualFactory();
        sms.produceSender().send();
        sms.produceReceiver().receive();
    }

    public void testBuilder(){
        MyRobustBuilder myRobustBuilder = new MyRobustBuilder();
        RobustDirector robustDirector = new RobustDirector(myRobustBuilder);
        Robust robust = robustDirector.buildRobust("robust-head", "robust-body");
        System.out.println(robust);
    }
}
