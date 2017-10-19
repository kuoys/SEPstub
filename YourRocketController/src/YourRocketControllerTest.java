import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class YourRocketControllerTest {
	MockRocketSystem mock;
	YourRocketController cus;


	@Before
	public void setUp() throws Exception {
		mock=new MockRocketSystem();
		cus=new YourRocketController(mock);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test  // test for normal situation
	public void test() {
		String control_string="IGNITE 0 1000 AT 0\n"+"IGNITE 1 500 AT 2\n"+"SHUTOFF 0 AT 5\n"+"SHUTOFF 1 AT 6\n";



		try {
			mock.startTime=System.currentTimeMillis();
			cus.fireUp(control_string);
		} catch (ERROR_STRINGFORMAT e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals("IGNITE 0 1000 AT 0",mock.record.get(0));
		assertEquals("IGNITE 1 500 AT 2",mock.record.get(1));
		assertEquals("SHUTOFF 0 AT 5",mock.record.get(2));
		assertEquals("SHUTOFF 1 AT 6",mock.record.get(3));

	}
	@Test //test for invalid string format
	public void test2(){
		String control_string="IGNITE 0  1000 AT 0\n"+"IGNITE 1 500 AT 2\n"+"SHUTOFF 0 AT 5\n"+"SHUTOFF 1 AT 6\n";
		try {
			mock.startTime=System.currentTimeMillis();
			cus.fireUp(control_string);
		} catch (ERROR_STRINGFORMAT e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals("ERROR_STRINGFORMAT",e.getMessage());
		}
		
		//test for nozzle out of bound
		control_string="IGNITE 4 1000 AT 0\n"+"IGNITE 1 500 AT 2\n"+"SHUTOFF 0 AT 5\n"+"SHUTOFF 1 AT 6\n";
		try {
			mock.startTime=System.currentTimeMillis();
			cus.fireUp(control_string);
		} catch (ERROR_STRINGFORMAT e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals("ERROR_STRINGFORMAT",e.getMessage());
		}
		


	}
	@Test //test for unorder string
	public void test3(){
		String control_string="IGNITE 0 1000 AT 3\n"+"IGNITE 1 500 AT 1\n"+"SHUTOFF 0 AT 2\n"+"SHUTOFF 1 AT 0\n";
		try {
			mock.startTime=System.currentTimeMillis();
			cus.fireUp(control_string);
		} catch (ERROR_STRINGFORMAT e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			assertEquals("ERROR_STRINGFORMAT",e.getMessage());
		}
		assertEquals("IGNITE 0 1000 AT 3",mock.record.get(3));
		assertEquals("IGNITE 1 500 AT 1",mock.record.get(1));
		assertEquals("SHUTOFF 0 AT 2",mock.record.get(2));
		assertEquals("SHUTOFF 1 AT 0",mock.record.get(0));

	}


}

class MockRocketSystem implements RocketSystem{
	public long startTime;
	public int nozzle,pressure;
	public ArrayList<String> record;

	MockRocketSystem(){
		record=new ArrayList<String>();

	}
	//TODO  change the print to something assertable
	@Override
	public void ignite(int nozzle, int pressure) {
		// TODO Auto-generated method stub

		String ins="IGNITE "+nozzle+" "+pressure+" AT "+(System.currentTimeMillis()-startTime)/1000;
		record.add(ins);

	}

	@Override
	public void shutoff(int nozzle) {
		// TODO Auto-generated method stub
		String ins="SHUTOFF "+nozzle+" AT "+(System.currentTimeMillis()-startTime)/1000;
		record.add(ins);

	}


}
