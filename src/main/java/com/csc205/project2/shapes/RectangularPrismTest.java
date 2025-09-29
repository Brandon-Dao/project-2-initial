/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test class for RectangularPrism including basic functionality, calculation accuracy, boundary testing, input validation, and inheritance testing."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Volume formula: V = l × w × h, for l=3, w=4, h=5: V = 60
 * - Surface area formula: SA = 2(lw + lh + wh), for l=3, w=4, h=5: SA = 94
 */

package com.csc205.project2.shapes;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class RectangularPrismTest extends BaseShapeTest {

    private static final double TEST_LENGTH = 3.0;
    private static final double TEST_WIDTH = 4.0;
    private static final double TEST_HEIGHT = 5.0;
    private static final double EXPECTED_VOLUME = 60.0; // 3 × 4 × 5
    private static final double EXPECTED_SURFACE_AREA = 94.0; // 2(3×4 + 3×5 + 4×5) = 2(12+15+20) = 94

    @Override
    protected Shape3D createShape() {
        return new RectangularPrism();
    }

    @Override
    protected Shape3D createShapeWithKnownValues() {
        return new RectangularPrism(TEST_LENGTH, TEST_WIDTH, TEST_HEIGHT);
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
            RectangularPrism prism = new RectangularPrism();

            assertEquals("Rectangular Prism", prism.getName());
            assertEquals("White", prism.getColor());
            assertEquals(1.0, prism.getLength(), DELTA);
            assertEquals(1.0, prism.getWidth(), DELTA);
            assertEquals(1.0, prism.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test dimensions constructor")
        void testDimensionsConstructor() {
            RectangularPrism prism = new RectangularPrism(2.5, 3.5, 4.5);

            assertEquals("Rectangular Prism", prism.getName());
            assertEquals("White", prism.getColor());
            assertEquals(2.5, prism.getLength(), DELTA);
            assertEquals(3.5, prism.getWidth(), DELTA);
            assertEquals(4.5, prism.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test full parameter constructor")
        void testFullConstructor() {
            RectangularPrism prism = new RectangularPrism("My Prism", "Purple", 6.0, 7.0, 8.0);

            assertEquals("My Prism", prism.getName());
            assertEquals("Purple", prism.getColor());
            assertEquals(6.0, prism.getLength(), DELTA);
            assertEquals(7.0, prism.getWidth(), DELTA);
            assertEquals(8.0, prism.getHeight(), DELTA);
        }
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Test length getter and setter")
        void testLengthGetterSetter() {
            RectangularPrism prism = new RectangularPrism();

            assertEquals(1.0, prism.getLength(), DELTA);

            prism.setLength(9.5);
            assertEquals(9.5, prism.getLength(), DELTA);
        }

        @Test
        @DisplayName("Test width getter and setter")
        void testWidthGetterSetter() {
            RectangularPrism prism = new RectangularPrism();

            assertEquals(1.0, prism.getWidth(), DELTA);

            prism.setWidth(7.3);
            assertEquals(7.3, prism.getWidth(), DELTA);
        }

        @Test
        @DisplayName("Test height getter and setter")
        void testHeightGetterSetter() {
            RectangularPrism prism = new RectangularPrism();

            assertEquals(1.0, prism.getHeight(), DELTA);

            prism.setHeight(4.8);
            assertEquals(4.8, prism.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test name and color inheritance")
        void testNameColorInheritance() {
            RectangularPrism prism = new RectangularPrism("Test Prism", "Orange", 1.0, 2.0, 3.0);

            assertEquals("Test Prism", prism.getName());
            assertEquals("Orange", prism.getColor());

            prism.setName("Updated Prism");
            prism.setColor("Pink");

            assertEquals("Updated Prism", prism.getName());
            assertEquals("Pink", prism.getColor());
        }
    }

    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationTests {

        @Test
        @DisplayName("Test volume calculation with known values")
        void testVolumeCalculation() {
            RectangularPrism prism = new RectangularPrism(TEST_LENGTH, TEST_WIDTH, TEST_HEIGHT);

            assertEquals(EXPECTED_VOLUME, prism.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area calculation with known values")
        void testSurfaceAreaCalculation() {
            RectangularPrism prism = new RectangularPrism(TEST_LENGTH, TEST_WIDTH, TEST_HEIGHT);

            assertEquals(EXPECTED_SURFACE_AREA, prism.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with unit cube (1×1×1)")
        void testUnitCube() {
            RectangularPrism prism = new RectangularPrism(1.0, 1.0, 1.0);

            assertEquals(1.0, prism.getVolume(), DELTA); // 1 × 1 × 1
            assertEquals(6.0, prism.getSurfaceArea(), DELTA); // 2(1×1 + 1×1 + 1×1) = 6
        }

        @Test
        @DisplayName("Test calculations with different dimensions")
        void testDifferentDimensions() {
            // 2×3×4 prism
            RectangularPrism prism = new RectangularPrism(2.0, 3.0, 4.0);

            double expectedVolume = 24.0; // 2 × 3 × 4
            double expectedSurfaceArea = 52.0; // 2(2×3 + 2×4 + 3×4) = 2(6+8+12) = 52

            assertEquals(expectedVolume, prism.getVolume(), DELTA);
            assertEquals(expectedSurfaceArea, prism.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test surface area formula breakdown")
        void testSurfaceAreaBreakdown() {
            RectangularPrism prism = new RectangularPrism(2.0, 3.0, 5.0);

            // Manual calculation: 2(lw + lh + wh) = 2(2×3 + 2×5 + 3×5) = 2(6+10+15) = 62
            double expectedSurfaceArea = 2.0 * (2.0*3.0 + 2.0*5.0 + 3.0*5.0);

            assertEquals(expectedSurfaceArea, prism.getSurfaceArea(), DELTA);
        }
    }

    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Test very small dimensions")
        void testVerySmallDimensions() {
            RectangularPrism prism = new RectangularPrism(VERY_SMALL_VALUE, VERY_SMALL_VALUE, VERY_SMALL_VALUE);

            assertTrue(prism.getVolume() > 0, "Volume should be positive for small dimensions");
            assertTrue(prism.getSurfaceArea() > 0, "Surface area should be positive for small dimensions");
        }

        @Test
        @DisplayName("Test very large dimensions")
        void testVeryLargeDimensions() {
            double largeValue = 1000.0; // Using reasonable large value to avoid overflow
            RectangularPrism prism = new RectangularPrism(largeValue, largeValue, largeValue);

            assertTrue(prism.getVolume() > 0, "Volume should be positive for large dimensions");
            assertTrue(prism.getSurfaceArea() > 0, "Surface area should be positive for large dimensions");
            assertFalse(Double.isInfinite(prism.getVolume()), "Volume should not be infinite");
            assertFalse(Double.isInfinite(prism.getSurfaceArea()), "Surface area should not be infinite");
        }

        @Test
        @DisplayName("Test extreme aspect ratios")
        void testExtremeAspectRatios() {
            // Very long, thin prism
            RectangularPrism longThin = new RectangularPrism(1000.0, 0.01, 0.01);
            assertTrue(longThin.getVolume() > 0, "Long thin prism should have positive volume");
            assertTrue(longThin.getSurfaceArea() > 0, "Long thin prism should have positive surface area");

            // Very flat prism
            RectangularPrism flat = new RectangularPrism(10.0, 10.0, 0.001);
            assertTrue(flat.getVolume() > 0, "Flat prism should have positive volume");
            assertTrue(flat.getSurfaceArea() > 0, "Flat prism should have positive surface area");
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Test negative length in constructor")
        void testNegativeLengthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(-1.0, 2.0, 3.0),
                    "Should throw exception for negative length");
        }

        @Test
        @DisplayName("Test negative width in constructor")
        void testNegativeWidthConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(1.0, -2.0, 3.0),
                    "Should throw exception for negative width");
        }

        @Test
        @DisplayName("Test negative height in constructor")
        void testNegativeHeightConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(1.0, 2.0, -3.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test zero dimensions in constructor")
        void testZeroDimensionsConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(0.0, 2.0, 3.0),
                    "Should throw exception for zero length");

            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(1.0, 0.0, 3.0),
                    "Should throw exception for zero width");

            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism(1.0, 2.0, 0.0),
                    "Should throw exception for zero height");
        }

        @Test
        @DisplayName("Test negative values in setters")
        void testNegativeValueSetters() {
            RectangularPrism prism = new RectangularPrism();

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setLength(-1.0),
                    "Should throw exception for negative length");

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setWidth(-2.0),
                    "Should throw exception for negative width");

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setHeight(-3.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test zero values in setters")
        void testZeroValueSetters() {
            RectangularPrism prism = new RectangularPrism();

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setLength(0.0),
                    "Should throw exception for zero length");

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setWidth(0.0),
                    "Should throw exception for zero width");

            assertThrows(IllegalArgumentException.class,
                    () -> prism.setHeight(0.0),
                    "Should throw exception for zero height");
        }

        @Test
        @DisplayName("Test full constructor with invalid values")
        void testFullConstructorInvalidValues() {
            assertThrows(IllegalArgumentException.class,
                    () -> new RectangularPrism("Test", "Blue", -1.0, 2.0, 3.0),
                    "Should throw exception for negative length in full constructor");
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringTests {

        @Test
        @DisplayName("Test toString format")
        void testToStringFormat() {
            RectangularPrism prism = new RectangularPrism("Test Prism", "Cyan", 2.0, 3.0, 4.0);
            String result = prism.toString();

            assertTrue(result.contains("Rectangular Prism"), "Should contain shape type");
            assertTrue(result.contains("Test Prism"), "Should contain name");
            assertTrue(result.contains("Cyan"), "Should contain color");
            assertTrue(result.contains("2.00"), "Should contain length");
            assertTrue(result.contains("3.00"), "Should contain width");
            assertTrue(result.contains("4.00"), "Should contain height");
        }

        @Test
        @DisplayName("Test toString with calculated values")
        void testToStringCalculatedValues() {
            RectangularPrism prism = new RectangularPrism(1.0, 2.0, 3.0);
            String result = prism.toString();

            assertTrue(result.toLowerCase().contains("surface area"), "Should mention surface area");
            assertTrue(result.toLowerCase().contains("volume"), "Should mention volume");
        }
    }

    @Nested
    @DisplayName("Mathematical Properties Tests")
    class MathematicalPropertiesTests {

        @Test
        @DisplayName("Test volume scales with each dimension")
        void testVolumeScaling() {
            RectangularPrism original = new RectangularPrism(2.0, 3.0, 4.0);

            // Double the length
            RectangularPrism doubleLength = new RectangularPrism(4.0, 3.0, 4.0);
            assertEquals(original.getVolume() * 2, doubleLength.getVolume(), DELTA);

            // Double the width
            RectangularPrism doubleWidth = new RectangularPrism(2.0, 6.0, 4.0);
            assertEquals(original.getVolume() * 2, doubleWidth.getVolume(), DELTA);

            // Double the height
            RectangularPrism doubleHeight = new RectangularPrism(2.0, 3.0, 8.0);
            assertEquals(original.getVolume() * 2, doubleHeight.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test when rectangular prism becomes a cube")
        void testCubeSpecialCase() {
            RectangularPrism cubePrism = new RectangularPrism(3.0, 3.0, 3.0);

            // Should behave like a cube
            assertEquals(27.0, cubePrism.getVolume(), DELTA); // 3³
            assertEquals(54.0, cubePrism.getSurfaceArea(), DELTA); // 6 × 3²
        }

        @Test
        @DisplayName("Test commutativity of dimensions")
        void testDimensionCommutativity() {
            // Volume and surface area should be the same regardless of dimension order
            RectangularPrism prism1 = new RectangularPrism(2.0, 3.0, 5.0);
            RectangularPrism prism2 = new RectangularPrism(3.0, 5.0, 2.0);
            RectangularPrism prism3 = new RectangularPrism(5.0, 2.0, 3.0);

            assertEquals(prism1.getVolume(), prism2.getVolume(), DELTA);
            assertEquals(prism1.getVolume(), prism3.getVolume(), DELTA);
            assertEquals(prism1.getSurfaceArea(), prism2.getSurfaceArea(), DELTA);
            assertEquals(prism1.getSurfaceArea(), prism3.getSurfaceArea(), DELTA);
        }
    }
}