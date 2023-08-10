package com.fssa.proplan.logger;

import org.junit.jupiter.api.Test;

 class TestLogger {

	@Test
	 void TestInfo() {

		String s = "barath";

		Logger logger = new Logger();

		logger.info(s);
	}
	
	@Test
	 void TestDebug() {

		String s = "barath";

		Logger logger = new Logger();

		logger.debug(s);
	}

}
