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
import org.windwant.designpattern.structure.adapter.ChinaVoltage;
import org.windwant.designpattern.structure.adapter.classadapter.ChinaVoltageClassAdapter;
import org.windwant.designpattern.structure.adapter.HongkongVoltage;
import org.windwant.designpattern.structure.adapter.PowerVoltage;
import org.windwant.designpattern.structure.adapter.interfaceadapter.ManyMethodInterface;
import org.windwant.designpattern.structure.adapter.interfaceadapter.MyMethod;
import org.windwant.designpattern.structure.adapter.objectadapter.ChinaVoltageObjectAdapter;
import org.windwant.designpattern.structure.decorator.Component;
import org.windwant.designpattern.structure.decorator.DecoratedIconTextComponent;
import org.windwant.designpattern.structure.decorator.TextComponent;
import org.windwant.designpattern.structure.facade.ComputerFacade;
import org.windwant.designpattern.structure.proxy.MovieStar;
import org.windwant.designpattern.structure.proxy.MovieStarProxy;
import org.windwant.designpattern.structure.proxy.Star;
import org.windwant.designpattern.structure.proxy.TVStar;

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

    public void testClassAdapter(){
        PowerVoltage standard = new HongkongVoltage();
        standard.givePower();
        PowerVoltage cus = new ChinaVoltageClassAdapter();
        cus.givePower();
    }

    public void testObjectAdapter(){
        PowerVoltage standard = new HongkongVoltage();
        standard.givePower();
        PowerVoltage cus = new ChinaVoltageObjectAdapter(new ChinaVoltage());
        cus.givePower();
    }

    public void testInterfaceAdapter(){
        ManyMethodInterface manyMethodInterface = new MyMethod();
        manyMethodInterface.methodOne();
        manyMethodInterface.methodTwo();
    }

    public void testDecorate(){
        Component tcom = new TextComponent();
        tcom.operate();
        Component itcom = new DecoratedIconTextComponent(tcom);
        itcom.operate();
    }

    public void testProxy(){
        MovieStar movieStar = new MovieStar();
        movieStar.movieShow(10000);
        movieStar.tvShow(20000);
        Star movieStarProxy = new MovieStarProxy(movieStar);
        movieStarProxy.movieShow(10000);
        movieStarProxy.movieShow(20000);
        movieStarProxy.tvShow(10000);
        movieStarProxy.tvShow(20000);

        TVStar tvStar = new TVStar();
        tvStar.movieShow(10000);
        tvStar.tvShow(10000);
        Star tvStarProxy = new MovieStarProxy(tvStar);
        tvStarProxy.movieShow(10000);
        tvStarProxy.movieShow(20000);
        tvStarProxy.tvShow(10000);
        tvStarProxy.tvShow(20000);
    }

    public void testFacade(){
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.startup();
        computerFacade.run();
        computerFacade.shutdown();
    }
}
