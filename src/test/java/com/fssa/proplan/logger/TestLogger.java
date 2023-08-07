package com.fssa.proplan.logger;

import org.junit.jupiter.api.Test;

public class TestLogger {

	@Test
	public void TestInfo() {

		String s = "barath";

		Logger logger = new Logger();

		logger.info(s);
	}
	
	@Test
	public void TestDebug() {

		String s = "barath";

		Logger logger = new Logger();

		logger.debug(s);
	}

}
