package com.csc205.project2.shapes.project2;
/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test class for Sphere including basic functionality,
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
 * - Volume formula: V = (4/3)πr³, for r=3: V = 113.097
 * - Surface area formula: SA = 4πr², for r=3: SA = 113.097
 */


import com.csc205.project2.shapes.Sphere;
import com.csc205.project2.shapes.ThreeDimensionalShape;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest extends BaseShapeTest {

    private static final double TEST_RADIUS = 3.0;
    private static final double EXPECTED_VOLUME = 113.097; // (4/3) * π * 3³
    private static final double EXPECTED_SURFACE_AREA = 113.097; // 4 * π * 3²

    @Override
    protected ThreeDimensionalShape.Shape3D createShape() {
        return new Sphere();
    }

    @Override
    protected ThreeDimensionalShape.Shape3D createShapeWithKnownValues() {
        return new Sphere(TEST_RADIUS);
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
            Sphere sphere = new Sphere();

            assertEquals("Sphere", sphere.getName());
            assertEquals("White", sphere.getColor());
            assertEquals(1.0, sphere.getRadius(), DELTA);
        }

        @Test
        @DisplayName("Test radius constructor")
        void testRadiusConstructor() {
            Sphere sphere = new Sphere(5.0);

            assertEquals("Sphere", sphere.getName());
            assertEquals("White", sphere.getColor());
            assertEquals(5.0, sphere.getRadius(), DELTA);
        }

        @Test
        @DisplayName("Test full parameter constructor")
        void testFullConstructor() {
            Sphere sphere = new Sphere("My Sphere", "Red", 2.5);

            assertEquals("My Sphere", sphere.getName());
            assertEquals("Red", sphere.getColor());
            assertEquals(2.5, sphere.getRadius(), DELTA);
        }
    }

    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {

        @Test
        @DisplayName("Test radius getter and setter")
        void testRadiusGetterSetter() {
            Sphere sphere = new Sphere();

            // Test initial value
            assertEquals(1.0, sphere.getRadius(), DELTA);

            // Test setter
            sphere.setRadius(7.5);
            assertEquals(7.5, sphere.getRadius(), DELTA);
        }

        @Test
        @DisplayName("Test name and color inheritance")
        void testNameColorInheritance() {
            Sphere sphere = new Sphere("Test Sphere", "Blue", 1.0);

            assertEquals("Test Sphere", sphere.getName());
            assertEquals("Blue", sphere.getColor());

            sphere.setName("Updated Sphere");
            sphere.setColor("Green");

            assertEquals("Updated Sphere", sphere.getName());
            assertEquals("Green", sphere.getColor());
        }
    }

    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationTests {

        @Test
        @DisplayName("Test volume calculation with known values")
        void testVolumeCalculation() {
            Sphere sphere = new Sphere(TEST_RADIUS);

            assertEquals(EXPECTED_VOLUME, sphere.getVolume(), DELTA);
        }

        @Test
        @DisplayName("Test surface area calculation with known values")
        void testSurfaceAreaCalculation() {
            Sphere sphere = new Sphere(TEST_RADIUS);

            assertEquals(EXPECTED_SURFACE_AREA, sphere.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with radius = 1")
        void testUnitSphere() {
            Sphere sphere = new Sphere(1.0);

            double expectedVolume = (4.0/3.0) * Math.PI; // ≈ 4.189
            double expectedSurfaceArea = 4.0 * Math.PI;   // ≈ 12.566

            assertEquals(expectedVolume, sphere.getVolume(), DELTA);
            assertEquals(expectedSurfaceArea, sphere.getSurfaceArea(), DELTA);
        }

        @Test
        @DisplayName("Test calculations with radius = 0.5")
        void testHalfUnitSphere() {
            Sphere sphere = new Sphere(0.5);

            double expectedVolume = (4.0/3.0) * Math.PI * 0.125; // ≈ 0.524
            double expectedSurfaceArea = 4.0 * Math.PI * 0.25;   // ≈ 3.142

            assertEquals(expectedVolume, sphere.getVolume(), DELTA);
            assertEquals(expectedSurfaceArea, sphere.getSurfaceArea(), DELTA);
        }
    }

    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {

        @Test
        @DisplayName("Test very small radius")
        void testVerySmallRadius() {
            Sphere sphere = new Sphere(VERY_SMALL_VALUE);

            assertTrue(sphere.getVolume() > 0, "Volume should be positive for small radius");
            assertTrue(sphere.getSurfaceArea() > 0, "Surface area should be positive for small radius");
        }

        @Test
        @DisplayName("Test very large radius")
        void testVeryLargeRadius() {
            Sphere sphere = new Sphere(VERY_LARGE_VALUE);

            assertTrue(sphere.getVolume() > 0, "Volume should be positive for large radius");
            assertTrue(sphere.getSurfaceArea() > 0, "Surface area should be positive for large radius");
            assertFalse(Double.isInfinite(sphere.getVolume()), "Volume should not be infinite");
            assertFalse(Double.isInfinite(sphere.getSurfaceArea()), "Surface area should not be infinite");
        }
    }

    @Nested
    @DisplayName("Input Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Test negative radius in constructor")
        void testNegativeRadiusConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere(-1.0),
                    "Should throw exception for negative radius");
        }

        @Test
        @DisplayName("Test zero radius in constructor")
        void testZeroRadiusConstructor() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere(0.0),
                    "Should throw exception for zero radius");
        }

        @Test
        @DisplayName("Test negative radius in setter")
        void testNegativeRadiusSetter() {
            Sphere sphere = new Sphere();

            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(-1.0),
                    "Should throw exception for negative radius");
        }

        @Test
        @DisplayName("Test zero radius in setter")
        void testZeroRadiusSetter() {
            Sphere sphere = new Sphere();

            assertThrows(IllegalArgumentException.class,
                    () -> sphere.setRadius(0.0),
                    "Should throw exception for zero radius");
        }

        @Test
        @DisplayName("Test full constructor with negative radius")
        void testFullConstructorNegativeRadius() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Sphere("Test", "Blue", -2.0),
                    "Should throw exception for negative radius in full constructor");
        }
    }

    @Nested
    @DisplayName("String Representation Tests")
    class StringTests {

        @Test
        @DisplayName("Test toString format")
        void testToStringFormat() {
            Sphere sphere = new Sphere("Test Sphere", "Red", 2.0);
            String result = sphere.toString();

            assertTrue(result.contains("Sphere"), "Should contain shape type");
            assertTrue(result.contains("Test Sphere"), "Should contain name");
            assertTrue(result.contains("Red"), "Should contain color");
            assertTrue(result.contains("2.00"), "Should contain radius");
        }

        @Test
        @DisplayName("Test toString with calculated values")
        void testToStringCalculatedValues() {
            Sphere sphere = new Sphere(1.0);
            String result = sphere.toString();

            // Should contain volume and surface area
            assertTrue(result.toLowerCase().contains("surface area"), "Should mention surface area");
            assertTrue(result.toLowerCase().contains("volume"), "Should mention volume");
        }
    }
}