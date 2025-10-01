package com.csc205.project2.shapes;
/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test class for Cylinder including basic functionality,
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
 * - Volume formula: V = πr²h, for r=2, h=5: V = 62.832
 * - Surface area formula: SA = 2πr² + 2πrh, for r=2, h=5: SA = 87.965
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CylinderTest extends BaseShapeTest {

    private static final double TEST_RADIUS = 2.0;
    private static final double TEST_HEIGHT = 5.0;
    private static final double EXPECTED_VOLUME = 62.832; // π * 2² * 5
    private static final double EXPECTED_SURFACE_AREA = 87.965; // 2π * 2² + 2π * 2 * 5

    @Override
    protected ThreeDimensionalShape.Shape3D createShape() {
        return new Cylinder();
    }

    @Override
    protected ThreeDimensionalShape.Shape3D createShapeWithKnownValues() {
        return new Cylinder(TEST_RADIUS, TEST_HEIGHT);
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
            Cylinder cylinder = new Cylinder();

            assertEquals("Cylinder", cylinder.getName());
            assertEquals("White", cylinder.getColor());
            assertEquals(1.0, cylinder.getRadius(), DELTA);
            assertEquals(1.0, cylinder.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test radius and height constructor")
        void testRadiusHeightConstructor() {
            Cylinder cylinder = new Cylinder(3.5, 7.2);

            assertEquals("Cylinder", cylinder.getName());
            assertEquals("White", cylinder.getColor());
            assertEquals(3.5, cylinder.getRadius(), DELTA);
            assertEquals(7.2, cylinder.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test full parameter constructor")
        void testFullConstructor() {
            Cylinder cylinder = new Cylinder("My Cylinder", "Green", 2.5, 4.0);

            assertEquals("My Cylinder", cylinder.getName());
            assertEquals("Green", cylinder.getColor());
            assertEquals(2.5, cylinder.getRadius(), DELTA);
            assertEquals(4.0, cylinder.getHeight(), DELTA);
        }
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Test radius getter and setter")
        void testRadiusGetterSetter() {
            Cylinder cylinder = new Cylinder();

            // Test initial value
            assertEquals(1.0, cylinder.getRadius(), DELTA);

            // Test setter
            cylinder.setRadius(6.0);
            assertEquals(6.0, cylinder.getRadius(), DELTA);
        }

        @Test
        @DisplayName("Test height getter and setter")
        void testHeightGetterSetter() {
            Cylinder cylinder = new Cylinder();

            // Test initial value
            assertEquals(1.0, cylinder.getHeight(), DELTA);

            // Test setter
            cylinder.setHeight(8.5);
            assertEquals(8.5, cylinder.getHeight(), DELTA);
        }

        @Test
        @DisplayName("Test name and color inheritance")
        void testNameColorInheritance() {
            Cylinder cylinder = new Cylinder("Test Cylinder", "Blue", 1.5, 3.0);

            assertEquals("Test Cylinder", cylinder.getName());
            assertEquals("Blue", cylinder.getColor());

            cylinder.setName("Updated Cylinder");
            cylinder.setColor("Red");

            assertEquals("Updated Cylinder", cylinder.getName());
            assertEquals("Red", cylinder.getColor());
        }
    }

    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationTests {

        @Test
        @DisplayName("Test volume calculation with known values")
        void testVolumeCalculation() {
            Cylinder cylinder = new Cylinder(TEST_RADIUS, TEST_HEIGHT);

            assertEquals(EXPECTED_VOLUME, cylinder.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area calculation with known values")
        void testSurfaceAreaCalculation() {
            Cylinder cylinder = new Cylinder(TEST_RADIUS, TEST_HEIGHT);

            assertEquals(EXPECTED_SURFACE_AREA, cylinder.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with unit cylinder (r=1, h=1)")
        void testUnitCylinder() {
            Cylinder cylinder = new Cylinder(1.0, 1.0);

            double expectedVolume = Math.PI; // π * 1² * 1
            double expectedSurfaceArea = 4.0 * Math.PI; // 2π * 1² + 2π * 1 * 1 = 4π

            assertEquals(expectedVolume, cylinder.getVolume(), DELTA);
            assertEquals(expectedSurfaceArea, cylinder.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with different proportions")
        void testDifferentProportions() {
            // Tall, narrow cylinder
            Cylinder tallCylinder = new Cylinder(1.0, 10.0);
            double tallVolume = Math.PI * 1.0 * 1.0 * 10.0; // π * r² * h
            double tallSurfaceArea = 2.0 * Math.PI * 1.0 * 1.0 + 2.0 * Math.PI * 1.0 * 10.0; // 2πr² + 2πrh

            assertEquals(tallVolume, tallCylinder.getVolume(), DELTA);
            assertEquals(tallSurfaceArea, tallCylinder.getSurfaceArea(), DELTA);

            // Short, wide cylinder
            Cylinder wideCylinder = new Cylinder(5.0, 1.0);
            double wideVolume = Math.PI * 25.0 * 1.0; // π * 5² * 1
            double wideSurfaceArea = 2.0 * Math.PI * 25.0 + 2.0 * Math.PI * 5.0 * 1.0; // 2π*25 + 2π*5*1

            assertEquals(wideVolume, wideCylinder.getVolume(), DELTA);
            assertEquals(wideSurfaceArea, wideCylinder.getSurfaceArea(), DELTA);
        }
    }

    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Test very small dimensions")
        void testVerySmallDimensions() {
            Cylinder cylinder = new Cylinder(VERY_SMALL_VALUE, VERY_SMALL_VALUE);

            assertTrue(cylinder.getVolume() > 0, "Volume should be positive for small dimensions");
            assertTrue(cylinder.getSurfaceArea() > 0, "Surface area should be positive for small dimensions");
        }

        @Test
        @DisplayName("Test very large dimensions")
        void testVeryLargeDimensions() {
            // Use smaller large values to avoid overflow
            double largeValue = 1000.0;
            Cylinder cylinder = new Cylinder(largeValue, largeValue);

            assertTrue(cylinder.getVolume() > 0, "Volume should be positive for large dimensions");
            assertTrue(cylinder.getSurfaceArea() > 0, "Surface area should be positive for large dimensions");
            assertFalse(Double.isInfinite(cylinder.getVolume()), "Volume should not be infinite");
            assertFalse(Double.isInfinite(cylinder.getSurfaceArea()), "Surface area should not be infinite");
        }

        @Test
        @DisplayName("Test extreme aspect ratios")
        void testExtremeAspectRatios() {
            // Very tall, very thin
            Cylinder tallThin = new Cylinder(0.001, 1000.0);
            assertTrue(tallThin.getVolume() > 0, "Tall thin cylinder should have positive volume");
            assertTrue(tallThin.getSurfaceArea() > 0, "Tall thin cylinder should have positive surface area");

            // Very short, very wide
            Cylinder shortWide = new Cylinder(1000.0, 0.001);
            assertTrue(shortWide.getVolume() > 0, "Short wide cylinder should have positive volume");
            assertTrue(shortWide.getSurfaceArea() > 0, "Short wide cylinder should have positive surface area");
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Test negative radius in constructor")
        void testNegativeRadiusConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder(-1.0, 5.0),
                    "Should throw exception for negative radius");
        }

        @Test
        @DisplayName("Test negative height in constructor")
        void testNegativeHeightConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder(2.0, -3.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test zero radius in constructor")
        void testZeroRadiusConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder(0.0, 5.0),
                    "Should throw exception for zero radius");
        }

        @Test
        @DisplayName("Test zero height in constructor")
        void testZeroHeightConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder(2.0, 0.0),
                    "Should throw exception for zero height");
        }

        @Test
        @DisplayName("Test negative radius in setter")
        void testNegativeRadiusSetter() {
            Cylinder cylinder = new Cylinder();

            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setRadius(-1.0),
                    "Should throw exception for negative radius");
        }

        @Test
        @DisplayName("Test negative height in setter")
        void testNegativeHeightSetter() {
            Cylinder cylinder = new Cylinder();

            assertThrows(IllegalArgumentException.class,
                    () -> cylinder.setHeight(-2.0),
                    "Should throw exception for negative height");
        }

        @Test
        @DisplayName("Test full constructor with negative values")
        void testFullConstructorNegativeValues() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("Test", "Blue", -1.0, 5.0),
                    "Should throw exception for negative radius in full constructor");

            assertThrows(IllegalArgumentException.class,
                    () -> new Cylinder("Test", "Blue", 2.0, -3.0),
                    "Should throw exception for negative height in full constructor");
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringTests {

        @Test
        @DisplayName("Test toString format")
        void testToStringFormat() {
            Cylinder cylinder = new Cylinder("Test Cylinder", "Yellow", 3.0, 6.0);
            String result = cylinder.toString();

            assertTrue(result.contains("Cylinder"), "Should contain shape type");
            assertTrue(result.contains("Test Cylinder"), "Should contain name");
            assertTrue(result.contains("Yellow"), "Should contain color");
            assertTrue(result.contains("3.00"), "Should contain radius");
            assertTrue(result.contains("6.00"), "Should contain height");
        }

        @Test
        @DisplayName("Test toString with calculated values")
        void testToStringCalculatedValues() {
            Cylinder cylinder = new Cylinder(2.0, 4.0);
            String result = cylinder.toString();

            // Should contain volume and surface area
            assertTrue(result.toLowerCase().contains("surface area"), "Should mention surface area");
            assertTrue(result.toLowerCase().contains("volume"), "Should mention volume");
        }
    }

    @Nested
    @DisplayName("Mathematical Properties Tests")
    class MathematicalPropertiesTests {

        @Test
        @DisplayName("Test volume relationship with radius")
        void testVolumeRadiusRelationship() {
            Cylinder cylinder1 = new Cylinder(2.0, 5.0);
            Cylinder cylinder2 = new Cylinder(4.0, 5.0); // Double radius, same height

            // Volume should be 4 times larger (radius is squared in formula)
            assertEquals(cylinder1.getVolume() * 4, cylinder2.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test volume relationship with height")
        void testVolumeHeightRelationship() {
            Cylinder cylinder1 = new Cylinder(3.0, 2.0);
            Cylinder cylinder2 = new Cylinder(3.0, 6.0); // Same radius, triple height

            // Volume should be 3 times larger (height is linear in formula)
            assertEquals(cylinder1.getVolume() * 3, cylinder2.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area components")
        void testSurfaceAreaComponents() {
            Cylinder cylinder = new Cylinder(3.0, 4.0);

            double baseArea = Math.PI * 3.0 * 3.0; // πr²
            double lateralArea = 2.0 * Math.PI * 3.0 * 4.0; // 2πrh
            double expectedSurfaceArea = 2 * baseArea + lateralArea; // Two bases + lateral surface

            assertEquals(expectedSurfaceArea, cylinder.getSurfaceArea(), DELTA);
        }
    }
}