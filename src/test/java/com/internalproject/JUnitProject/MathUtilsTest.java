package com.internalproject.JUnitProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;
//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/* To display the title of the tests */
@DisplayName("Testing MathUtils")

class MathUtilsTest {
	MathUtils mathUtils;

//	@BeforeAll
//	void beforeAllInit() {
//		System.out.println("This needs to run before all");
//	}

	@BeforeEach
	void init() {
		mathUtils = new MathUtils();
	}

	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up...");
	}

	/* @DisplayName demo */
	@Test
	@DisplayName("Add method")
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, "Should return the right Sum");
//		OR
//		assertEquals(expected, actual, () -> "Should return Sum" + expected + "but returned" + actual);
	}

	/* assertAll demo */
	@Test
	@DisplayName("Multiply method")
	void testMultiply() {
//		assertEquals(4, mathUtils.multiply(2,2), "Should return the right product");
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)), 
				() -> assertEquals(0, mathUtils.multiply(2, 0)),
				() -> assertEquals(-2, mathUtils.multiply(2, -1))
				);
	}

	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "Divide by 0 should throw");
	}

	@Test
	void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return Circle area");
	}

	/* @Disabled demo */
	@Test
	@Tag("Smoke")
	@DisplayName("TDD method shouldn't run")
	@Disabled
	void testDisabled() {
		fail("This test should be disabled");
	}

	/* @EnabledOnOs demo */
	@Test
	@EnabledOnOs(OS.MAC)
	void testConditionalExecution() {
		System.out.println("To test the conditional execution-will be skipped");
	}

	/* AssumeTrue demo---Not Working */
	@Test
	void testAssumeTrue() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		System.out.println("Test the Assume true");
	}

	/* @Nested demo */
	@Nested
	class SubtractTests {
		
		@Test
		@DisplayName("Subtracting from a smaller numbers")
		void testPositive() {
			assertEquals(1, mathUtils.subtract(4, 3),"should return the right difference");
		}

		@Test
		@DisplayName("Subtracting from a larger numbers")
		void testNegative() {
			assertEquals(-1, mathUtils.subtract(3, 4),"should return the right difference");
		}

		@Test
		@DisplayName("Subtracting two same numbers")
		void testZero() {
			assertEquals(0, mathUtils.subtract(2, 2),"should return the right difference");
		}
	}
}
