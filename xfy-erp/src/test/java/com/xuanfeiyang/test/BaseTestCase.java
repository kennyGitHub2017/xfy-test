package com.xuanfeiyang.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/applicationContext.xml" })
public class BaseTestCase {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before
	public void before() {
		logger.debug("-------------------------------" + this.getClass()
				+ "----------------------------------");
	}

	@After
	public void after() {
		logger.debug("-------------------------------" + this.getClass()
				+ "----------------------------------");
	}

	// a test method
	@Test
	public void _test() {
		Assert.assertTrue(true);
	}
}
