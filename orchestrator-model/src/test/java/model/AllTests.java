package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ NodeTest.class, ServiceTest.class, ContainerTest.class,
	PortTest.class, VolumeTest.class } )
public class AllTests {

}