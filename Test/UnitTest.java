package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import GuiPanels.GUI;

public class UnitTest {

	@Test
	public void test() {
		//test button output
		GUI obj1 = new GUI();
		obj1.action(null, obj1);
	}

}
