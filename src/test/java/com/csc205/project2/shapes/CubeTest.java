package com.csc205.project2.shapes;
/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test class for Cube including basic functionality,
 * calculation accuracy, boundary testing, input validation, and inheritance testing."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Volume formula: V = s³, for s=4: V = 64
 * - Surface area formula: SA = 6s², for s=4: SA = 96
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CubeTest extends BaseShapeTest {

    private static final double TEST_SIDE_LENGTH = 4.0;
    private static final double EXPECTED_VOLUME = 64.0; // 4³
    private static final double EXPECTED_SURFACE_AREA = 96.0; // 6 * 4²

    @Override
    protected ThreeDimensionalShape.Shape3D createShape() {
        return new Cube();
    }

    @Override
    protected ThreeDimensionalShape.Shape3D createShapeWithKnownValues() {
        return new Cube(TEST_SIDE_LENGTH);
    }

    @Override
    protected double getExpectedVolume() {
        return EXPECTED_VOLUME;
    }

    @Override
    protected double getExpectedSurfaceArea() {
        return EXPECTED_SURFACE_AREA;
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Test default constructor")
        void testDefaultConstructor() {
            Cube cube = new Cube();

            assertEquals("Cube", cube.getName());
            assertEquals("White", cube.getColor());
            assertEquals(1.0, cube.getSideLength(), DELTA);
        }

        @Test
        @DisplayName("Test side length constructor")
        void testSideLengthConstructor() {
            Cube cube = new Cube(5.5);

            assertEquals("Cube", cube.getName());
            assertEquals("White", cube.getColor());
            assertEquals(5.5, cube.getSideLength(), DELTA);
        }

        @Test
        @DisplayName("Test full parameter constructor")
        void testFullConstructor() {
            Cube cube = new Cube("My Cube", "Blue", 3.0);

            assertEquals("My Cube", cube.getName());
            assertEquals("Blue", cube.getColor());
            assertEquals(3.0, cube.getSideLength(), DELTA);
        }
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Test side length getter and setter")
        void testSideLengthGetterSetter() {
            Cube cube = new Cube();

            // Test initial value
            assertEquals(1.0, cube.getSideLength(), DELTA);

            // Test setter
            cube.setSideLength(8.0);
            assertEquals(8.0, cube.getSideLength(), DELTA);
        }

        @Test
        @DisplayName("Test name and color inheritance")
        void testNameColorInheritance() {
            Cube cube = new Cube("Test Cube", "Red", 2.0);

            assertEquals("Test Cube", cube.getName());
            assertEquals("Red", cube.getColor());

            cube.setName("Updated Cube");
            cube.setColor("Yellow");

            assertEquals("Updated Cube", cube.getName());
            assertEquals("Yellow", cube.getColor());
        }
    }

    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationTests {

        @Test
        @DisplayName("Test volume calculation with known values")
        void testVolumeCalculation() {
            Cube cube = new Cube(TEST_SIDE_LENGTH);

            assertEquals(EXPECTED_VOLUME, cube.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area calculation with known values")
        void testSurfaceAreaCalculation() {
            Cube cube = new Cube(TEST_SIDE_LENGTH);

            assertEquals(EXPECTED_SURFACE_AREA, cube.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with side length = 1 (unit cube)")
        void testUnitCube() {
            Cube cube = new Cube(1.0);

            assertEquals(1.0, cube.getVolume(), DELTA);
            assertEquals(6.0, cube.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with side length = 2")
        void testSideLength2() {
            Cube cube = new Cube(2.0);

            assertEquals(8.0, cube.getVolume(), DELTA);      // 2³ = 8
            assertEquals(24.0, cube.getSurfaceArea(), DELTA); // 6 * 2² = 24
        }

        @Test
        @DisplayName("Test calculations with fractional side length")
        void testFractionalSideLength() {
            Cube cube = new Cube(0.5);

            assertEquals(0.125, cube.getVolume(), DELTA);    // (0.5)³ = 0.125
            assertEquals(1.5, cube.getSurfaceArea(), DELTA);  // 6 * (0.5)² = 1.5
        }
    }

    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Test very small side length")
        void testVerySmallSideLength() {
            Cube cube = new Cube(VERY_SMALL_VALUE);

            assertTrue(cube.getVolume() > 0, "Volume should be positive for small side length");
            assertTrue(cube.getSurfaceArea() > 0, "Surface area should be positive for small side length");
        }

        @Test
        @DisplayName("Test very large side length")
        void testVeryLargeSideLength() {
            Cube cube = new Cube(VERY_LARGE_VALUE);

            assertTrue(cube.getVolume() > 0, "Volume should be positive for large side length");
            assertTrue(cube.getSurfaceArea() > 0, "Surface area should be positive for large side length");
            assertFalse(Double.isInfinite(cube.getVolume()), "Volume should not be infinite");
            assertFalse(Double.isInfinite(cube.getSurfaceArea()), "Surface area should not be infinite");
        }

        @Test
        @DisplayName("Test edge case: side length close to zero")
        void testNearZeroSideLength() {
            double nearZero = 1e-10;
            Cube cube = new Cube(nearZero);

            assertTrue(cube.getVolume() > 0, "Volume should still be positive for very small values");
            assertTrue(cube.getSurfaceArea() > 0, "Surface area should still be positive for very small values");
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Test negative side length in constructor")
        void testNegativeSideLengthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cube(-1.0),
                    "Should throw exception for negative side length");
        }

        @Test
        @DisplayName("Test zero side length in constructor")
        void testZeroSideLengthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cube(0.0),
                    "Should throw exception for zero side length");
        }

        @Test
        @DisplayName("Test negative side length in setter")
        void testNegativeSideLengthSetter() {
            Cube cube = new Cube();

            assertThrows(IllegalArgumentException.class,
                    () -> cube.setSideLength(-2.0),
                    "Should throw exception for negative side length");
        }

        @Test
        @DisplayName("Test zero side length in setter")
        void testZeroSideLengthSetter() {
            Cube cube = new Cube();

            assertThrows(IllegalArgumentException.class,
                    () -> cube.setSideLength(0.0),
                    "Should throw exception for zero side length");
        }

        @Test
        @DisplayName("Test full constructor with negative side length")
        void testFullConstructorNegativeSideLength() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cube("Test", "Green", -3.0),
                    "Should throw exception for negative side length in full constructor");
        }

        @Test
        @DisplayName("Test setter maintains object state after exception")
        void testSetterMaintainsStateAfterException() {
            Cube cube = new Cube(5.0);
            double originalSideLength = cube.getSideLength();

            assertThrows(IllegalArgumentException.class,
                    () -> cube.setSideLength(-1.0));

            assertEquals(originalSideLength, cube.getSideLength(), DELTA,
                    "Side length should remain unchanged after invalid setter call");
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringTests {

        @Test
        @DisplayName("Test toString format")
        void testToStringFormat() {
            Cube cube = new Cube("Test Cube", "Purple", 3.0);
            String result = cube.toString();

            assertTrue(result.contains("Cube"), "Should contain shape type");
            assertTrue(result.contains("Test Cube"), "Should contain name");
            assertTrue(result.contains("Purple"), "Should contain color");
            assertTrue(result.contains("3.00"), "Should contain side length");
        }

        @Test
        @DisplayName("Test toString with calculated values")
        void testToStringCalculatedValues() {
            Cube cube = new Cube(2.0);
            String result = cube.toString();

            // Should contain volume and surface area
            assertTrue(result.toLowerCase().contains("surface area"), "Should mention surface area");
            assertTrue(result.toLowerCase().contains("volume"), "Should mention volume");
        }

        @Test
        @DisplayName("Test toString consistency")
        void testToStringConsistency() {
            Cube cube = new Cube("Consistent Cube", "Orange", 1.5);
            String result1 = cube.toString();
            String result2 = cube.toString();

            assertEquals(result1, result2, "toString should be consistent between calls");
        }
    }

    @Nested
    @DisplayName("Mathematical Properties Tests")
    class MathematicalPropertiesTests {

        @Test
        @DisplayName("Test volume scales cubically")
        void testVolumeScalesCubically() {
            Cube cube1 = new Cube(2.0);
            Cube cube2 = new Cube(4.0); // Double the side length

            // Volume should be 8 times larger (2³)
            assertEquals(cube1.getVolume() * 8, cube2.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area scales quadratically")
        void testSurfaceAreaScalesQuadratically() {
            Cube cube1 = new Cube(3.0);
            Cube cube2 = new Cube(6.0); // Double the side length

            // Surface area should be 4 times larger (2²)
            assertEquals(cube1.getSurfaceArea() * 4, cube2.getSurfaceArea(), DELTA);
        }
    }
}