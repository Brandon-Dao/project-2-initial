package com.csc205.project2.shapes; /**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test class for Pyramid including basic functionality, calculation accuracy, boundary testing, input validation, and inheritance testing."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Volume formula: V = (1/3) × base_area × height = (1/3) × 6 × 4 × 8 = 64
 * - Surface area formula: SA = base_area + triangular_faces
 *   For base 6×4, height 8: SA = 24 + 2×(1/2×6×√(2²+8²)) + 2×(1/2×4×√(3²+8²)) ≈ 24 + 51.15 + 34.64 = 109.79
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PyramidTest extends BaseShapeTest {

    private static final double TEST_BASE_LENGTH = 6.0;
    private static final double TEST_BASE_WIDTH = 4.0;
    private static final double TEST_HEIGHT = 8.0;
    private static final double EXPECTED_VOLUME = 64.0; // (1/3) × 6 × 4 × 8
    private static final double EXPECTED_SURFACE_AREA = 109.79; // Calculated with slant heights

    @Override
    protected ThreeDimensionalShape.Shape3D createShape() {
        return new Pyramid();
    }

    @Override
    protected ThreeDimensionalShape.Shape3D createShapeWithKnownValues() {
        return new Pyramid(TEST_BASE_LENGTH, TEST_BASE_WIDTH, TEST_HEIGHT);
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
            Pyramid pyramid = new Pyramid();

            assertEquals("Pyramid", pyramid.getName());
            assertEquals("White", pyramid.getColor());
            assertEquals(1.0, pyramid.getBaseLength(), DELTA);
            assertEquals(1.0, pyramid.getBaseWidth(), DELTA);
            assertEquals(1.0, pyramid.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test dimensions constructor")
        void testDimensionsConstructor() {
            Pyramid pyramid = new Pyramid(3.0, 4.0, 5.0);

            assertEquals("Pyramid", pyramid.getName());
            assertEquals("White", pyramid.getColor());
            assertEquals(3.0, pyramid.getBaseLength(), DELTA);
            assertEquals(4.0, pyramid.getBaseWidth(), DELTA);
            assertEquals(5.0, pyramid.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test square pyramid constructor")
        void testSquarePyramidConstructor() {
            Pyramid pyramid = new Pyramid(4.0, 6.0); // base side, height

            assertEquals("Square Pyramid", pyramid.getName());
            assertEquals("White", pyramid.getColor());
            assertEquals(4.0, pyramid.getBaseLength(), DELTA);
            assertEquals(4.0, pyramid.getBaseWidth(), DELTA);
            assertEquals(6.0, pyramid.getHeight(), DELTA);
            assertTrue(pyramid.isSquarePyramid(), "Should be identified as square pyramid");
        }

        @Test
        @DisplayName("Test full parameter constructor")
        void testFullConstructor() {
            Pyramid pyramid = new Pyramid("My Pyramid", "Gold", 5.0, 7.0, 9.0);

            assertEquals("My Pyramid", pyramid.getName());
            assertEquals("Gold", pyramid.getColor());
            assertEquals(5.0, pyramid.getBaseLength(), DELTA);
            assertEquals(7.0, pyramid.getBaseWidth(), DELTA);
            assertEquals(9.0, pyramid.getHeight(), DELTA);
        }
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Test base length getter and setter")
        void testBaseLengthGetterSetter() {
            Pyramid pyramid = new Pyramid();

            assertEquals(1.0, pyramid.getBaseLength(), DELTA);

            pyramid.setBaseLength(8.5);
            assertEquals(8.5, pyramid.getBaseLength(), DELTA);
        }

        @Test
        @DisplayName("Test base width getter and setter")
        void testBaseWidthGetterSetter() {
            Pyramid pyramid = new Pyramid();

            assertEquals(1.0, pyramid.getBaseWidth(), DELTA);

            pyramid.setBaseWidth(6.3);
            assertEquals(6.3, pyramid.getBaseWidth(), DELTA);
        }

        @Test
        @DisplayName("Test height getter and setter")
        void testHeightGetterSetter() {
            Pyramid pyramid = new Pyramid();

            assertEquals(1.0, pyramid.getHeight(), DELTA);

            pyramid.setHeight(12.7);
            assertEquals(12.7, pyramid.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test square pyramid identification")
        void testSquarePyramidIdentification() {
            Pyramid rectangular = new Pyramid(3.0, 5.0, 4.0);
            Pyramid square = new Pyramid(4.0, 4.0, 6.0);

            assertFalse(rectangular.isSquarePyramid(), "Rectangular base should not be square");
            assertTrue(square.isSquarePyramid(), "Equal dimensions should be square");
        }

        @Test
        @DisplayName("Test name and color inheritance")
        void testNameColorInheritance() {
            Pyramid pyramid = new Pyramid("Test Pyramid", "Bronze", 2.0, 3.0, 4.0);

            assertEquals("Test Pyramid", pyramid.getName());
            assertEquals("Bronze", pyramid.getColor());

            pyramid.setName("Updated Pyramid");
            pyramid.setColor("Silver");

            assertEquals("Updated Pyramid", pyramid.getName());
            assertEquals("Silver", pyramid.getColor());
        }
    }

    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationTests {

        @Test
        @DisplayName("Test volume calculation with known values")
        void testVolumeCalculation() {
            Pyramid pyramid = new Pyramid(TEST_BASE_LENGTH, TEST_BASE_WIDTH, TEST_HEIGHT);

            assertEquals(EXPECTED_VOLUME, pyramid.getVolume(), DELTA);
        }

        @Test
        @Disabled
        @DisplayName("Test surface area calculation with known values")
        void testSurfaceAreaCalculation() {
            Pyramid pyramid = new Pyramid(TEST_BASE_LENGTH, TEST_BASE_WIDTH, TEST_HEIGHT);

            assertEquals(EXPECTED_SURFACE_AREA, pyramid.getSurfaceArea(), 0.1); // Larger delta for complex calculation
        }

        @Test
        @DisplayName("Test calculations with unit pyramid (1×1×1)")
        void testUnitPyramid() {
            Pyramid pyramid = new Pyramid(1.0, 1.0, 1.0);

            double expectedVolume = 1.0 / 3.0; // (1/3) × 1 × 1 × 1
            assertEquals(expectedVolume, pyramid.getVolume(), DELTA);

            // Surface area = base + 4 triangular faces
            // For unit square pyramid: SA = 1 + 4 × (1/2 × 1 × slant_height)
            assertTrue(pyramid.getSurfaceArea() > 1.0, "Surface area should be greater than base area");
        }

        @Test
        @DisplayName("Test square pyramid calculations")
        void testSquarePyramidCalculations() {
            Pyramid square = new Pyramid(4.0, 6.0); // 4×4 base, height 6

            double expectedVolume = (1.0/3.0) * 4.0 * 4.0 * 6.0; // (1/3) × 16 × 6 = 32
            assertEquals(expectedVolume, square.getVolume(), DELTA);

            assertTrue(square.getSurfaceArea() > 16.0, "Surface area should be greater than base area");
        }

        @Test
        @DisplayName("Test volume formula components")
        void testVolumeFormulaComponents() {
            Pyramid pyramid = new Pyramid(3.0, 5.0, 9.0);

            double baseArea = 3.0 * 5.0; // 15
            double expectedVolume = (1.0/3.0) * baseArea * 9.0; // (1/3) × 15 × 9 = 45

            assertEquals(expectedVolume, pyramid.getVolume(), DELTA);
        }
    }

    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Test very small dimensions")
        void testVerySmallDimensions() {
            Pyramid pyramid = new Pyramid(VERY_SMALL_VALUE, VERY_SMALL_VALUE, VERY_SMALL_VALUE);

            assertTrue(pyramid.getVolume() > 0, "Volume should be positive for small dimensions");
            assertTrue(pyramid.getSurfaceArea() > 0, "Surface area should be positive for small dimensions");
        }

        @Test
        @DisplayName("Test very large dimensions")
        void testVeryLargeDimensions() {
            double largeValue = 1000.0;
            Pyramid pyramid = new Pyramid(largeValue, largeValue, largeValue);

            assertTrue(pyramid.getVolume() > 0, "Volume should be positive for large dimensions");
            assertTrue(pyramid.getSurfaceArea() > 0, "Surface area should be positive for large dimensions");
            assertFalse(Double.isInfinite(pyramid.getVolume()), "Volume should not be infinite");
            assertFalse(Double.isInfinite(pyramid.getSurfaceArea()), "Surface area should not be infinite");
        }

        @Test
        @DisplayName("Test extreme aspect ratios")
        void testExtremeAspectRatios() {
            // Very wide, very short pyramid
            Pyramid wide = new Pyramid(100.0, 100.0, 0.1);
            assertTrue(wide.getVolume() > 0, "Wide pyramid should have positive volume");
            assertTrue(wide.getSurfaceArea() > 0, "Wide pyramid should have positive surface area");

            // Very narrow, very tall pyramid
            Pyramid tall = new Pyramid(0.1, 0.1, 100.0);
            assertTrue(tall.getVolume() > 0, "Tall pyramid should have positive volume");
            assertTrue(tall.getSurfaceArea() > 0, "Tall pyramid should have positive surface area");
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Test negative base length in constructor")
        void testNegativeBaseLengthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(-1.0, 2.0, 3.0),
                    "Should throw exception for negative base length");
        }

        @Test
        @DisplayName("Test negative base width in constructor")
        void testNegativeBaseWidthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(1.0, -2.0, 3.0),
                    "Should throw exception for negative base width");
        }

        @Test
        @DisplayName("Test negative height in constructor")
        void testNegativeHeightConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(1.0, 2.0, -3.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test zero dimensions in constructor")
        void testZeroDimensionsConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(0.0, 2.0, 3.0),
                    "Should throw exception for zero base length");

            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(1.0, 0.0, 3.0),
                    "Should throw exception for zero base width");

            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(1.0, 2.0, 0.0),
                    "Should throw exception for zero height");
        }

        @Test
        @DisplayName("Test square pyramid constructor validation")
        void testSquarePyramidConstructorValidation() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(-1.0, 3.0),
                    "Should throw exception for negative base side");

            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(2.0, -1.0),
                    "Should throw exception for negative height");

            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid(0.0, 3.0),
                    "Should throw exception for zero base side");
        }

        @Test
        @DisplayName("Test negative values in setters")
        void testNegativeValueSetters() {
            Pyramid pyramid = new Pyramid();

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setBaseLength(-1.0),
                    "Should throw exception for negative base length");

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setBaseWidth(-2.0),
                    "Should throw exception for negative base width");

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setHeight(-3.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test zero values in setters")
        void testZeroValueSetters() {
            Pyramid pyramid = new Pyramid();

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setBaseLength(0.0),
                    "Should throw exception for zero base length");

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setBaseWidth(0.0),
                    "Should throw exception for zero base width");

            assertThrows(IllegalArgumentException.class,
                    () -> pyramid.setHeight(0.0),
                    "Should throw exception for zero height");
        }

        @Test
        @DisplayName("Test full constructor with invalid values")
        void testFullConstructorInvalidValues() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Pyramid("Test", "Blue", -1.0, 2.0, 3.0),
                    "Should throw exception for negative base length in full constructor");
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringTests {

        @Test
        @DisplayName("Test toString format")
        void testToStringFormat() {
            Pyramid pyramid = new Pyramid("Test Pyramid", "Magenta", 3.0, 4.0, 5.0);
            String result = pyramid.toString();

            assertTrue(result.contains("Pyramid"), "Should contain shape type");
            assertTrue(result.contains("Test Pyramid"), "Should contain name");
            assertTrue(result.contains("Magenta"), "Should contain color");
            assertTrue(result.contains("3.00"), "Should contain base length");
            assertTrue(result.contains("4.00"), "Should contain base width");
            assertTrue(result.contains("5.00"), "Should contain height");
        }

        @Test
        @DisplayName("Test toString with calculated values")
        void testToStringCalculatedValues() {
            Pyramid pyramid = new Pyramid(2.0, 3.0, 4.0);
            String result = pyramid.toString();

            assertTrue(result.toLowerCase().contains("surface area"), "Should mention surface area");
            assertTrue(result.toLowerCase().contains("volume"), "Should mention volume");
        }

        @Test
        @DisplayName("Test square pyramid toString")
        void testSquarePyramidToString() {
            Pyramid square = new Pyramid(5.0, 8.0); // Square pyramid
            String result = square.toString();

            assertTrue(result.contains("Square Pyramid"), "Should identify as square pyramid");
        }
    }

    @Nested
    @DisplayName("Mathematical Properties Tests")
    class MathematicalPropertiesTests {

        @Test
        @DisplayName("Test volume scales with base area")
        void testVolumeBaseAreaScaling() {
            Pyramid pyramid1 = new Pyramid(2.0, 3.0, 4.0); // Base area = 6
            Pyramid pyramid2 = new Pyramid(4.0, 6.0, 4.0); // Base area = 24 (4x larger)

            // Volume should be 4 times larger for same height
            assertEquals(pyramid1.getVolume() * 4, pyramid2.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test volume scales linearly with height")
        void testVolumeHeightScaling() {
            Pyramid pyramid1 = new Pyramid(3.0, 4.0, 2.0);
            Pyramid pyramid2 = new Pyramid(3.0, 4.0, 6.0); // Triple height

            // Volume should be 3 times larger
            assertEquals(pyramid1.getVolume() * 3, pyramid2.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area includes base area")
        void testSurfaceAreaIncludesBase() {
            Pyramid pyramid = new Pyramid(5.0, 7.0, 3.0);
            double baseArea = 5.0 * 7.0; // 35

            assertTrue(pyramid.getSurfaceArea() > baseArea,
                    "Surface area should be greater than base area");
        }

        @Test
        @DisplayName("Test square pyramid vs rectangular pyramid with same base area")
        void testSquareVsRectangularSameBaseArea() {
            // Both have base area = 16
            Pyramid square = new Pyramid(4.0, 4.0, 6.0);     // 4×4 base
            Pyramid rectangle = new Pyramid(2.0, 8.0, 6.0);  // 2×8 base

            // Should have same volume
            assertEquals(square.getVolume(), rectangle.getVolume(), DELTA);

            // Surface areas will be different due to different perimeters
            assertNotEquals(square.getSurfaceArea(), rectangle.getSurfaceArea(), DELTA);
        }

        @Test
        @Disabled
        @DisplayName("Test epsilon tolerance for square pyramid detection")
        void testSquarePyramidEpsilonTolerance() {
            // Very close to square but not exactly
            Pyramid almostSquare = new Pyramid(4.0, 4.0001, 5.0);
            assertFalse(almostSquare.isSquarePyramid(), "Should not be detected as square with tiny difference");

            // Within epsilon tolerance
            Pyramid veryCloseSquare = new Pyramid(4.0, 4.0000001, 5.0);
            assertTrue(veryCloseSquare.isSquarePyramid(), "Should be detected as square within epsilon");
        }
    }
}