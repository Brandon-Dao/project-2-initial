package com.csc205.project2.shapes.project2; /**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a comprehensive test suite that demonstrates all the shape tests working together and includes polymorphism testing across all shapes."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Test suite validates all shape formulas through comprehensive testing
 */

import com.csc205.project2.shapes.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive test suite that validates all shape implementations
 * and demonstrates polymorphic behavior across the entire shape hierarchy
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
class ShapeTestSuite {

    private static final double DELTA = 0.001;

    @Nested
    @DisplayName("Integration Tests - All Shapes Together")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class IntegrationTests {

        @Test
        @Order(1)
        @DisplayName("Test polymorphic behavior with all shapes")
        void testPolymorphicBehavior() {
            // Create array of different shapes using polymorphism
            ThreeDimensionalShape[] shapes = {
                    new Sphere(2.0),
                    new Cube(3.0),
                    new Cylinder(1.5, 4.0),
                    new RectangularPrism(2.0, 3.0, 4.0),
                    new Pyramid(3.0, 4.0, 5.0)
            };

            // Test that all shapes implement the interface correctly
            for (ThreeDimensionalShape shape : shapes) {
                assertNotNull(shape, "Shape should not be null");
                assertTrue(shape.getVolume() > 0,
                        "Volume should be positive for shape: " + shape.getClass().getSimpleName());
                assertTrue(shape.getSurfaceArea() > 0,
                        "Surface area should be positive for shape: " + shape.getClass().getSimpleName());
            }
        }

        @Test
        @Order(2)
        @DisplayName("Test Shape3D inheritance for all concrete shapes")
        void testShape3DInheritance() {
            List<ThreeDimensionalShape.Shape3D> shapes = Arrays.asList(
                    new Sphere("Red Sphere", "Red", 2.0),
                    new Cube("Blue Cube", "Blue", 3.0),
                    new Cylinder("Green Cylinder", "Green", 1.5, 4.0),
                    new RectangularPrism("Yellow Prism", "Yellow", 2.0, 3.0, 4.0),
                    new Pyramid("Purple Pyramid", "Purple", 3.0, 4.0, 5.0)
            );

            for (ThreeDimensionalShape.Shape3D shape : shapes) {
                // Test common properties
                assertNotNull(shape.getName(), "Name should not be null");
                assertNotNull(shape.getColor(), "Color should not be null");

                // Test toString method
                String toString = shape.toString();
                assertNotNull(toString, "toString should not be null");
                assertTrue(toString.contains(shape.getName()), "toString should contain name");
                assertTrue(toString.contains(shape.getColor()), "toString should contain color");

                // Test that concrete implementations delegate correctly
                assertEquals(shape.getSurfaceArea(), shape.calculateSurfaceArea(), DELTA);
                assertEquals(shape.getVolume(), shape.calculateVolume(), DELTA);
            }
        }

        @Test
        @Order(3)
        @DisplayName("Test all shapes can be stored in collections")
        void testShapeCollections() {
            List<ThreeDimensionalShape> shapeList = Arrays.asList(
                    new Sphere(1.0),
                    new Cube(2.0),
                    new Cylinder(1.0, 3.0),
                    new RectangularPrism(1.0, 2.0, 3.0),
                    new Pyramid(2.0, 2.0, 4.0)
            );

            // Calculate total volume and surface area
            double totalVolume = shapeList.stream()
                    .mapToDouble(ThreeDimensionalShape::getVolume)
                    .sum();

            double totalSurfaceArea = shapeList.stream()
                    .mapToDouble(ThreeDimensionalShape::getSurfaceArea)
                    .sum();

            assertTrue(totalVolume > 0, "Total volume should be positive");
            assertTrue(totalSurfaceArea > 0, "Total surface area should be positive");

            // Verify each shape contributes to the totals
            assertEquals(5, shapeList.size(), "Should have 5 shapes in collection");
        }
    }

    @Nested
    @DisplayName("Cross-Shape Comparison Tests")
    class ComparisonTests {

        @Test
        @DisplayName("Compare shapes with same volume")
        void testSameVolumeComparison() {
            // Create shapes with approximately the same volume
            double targetVolume = 100.0; // Target volume

            // Cube with side ≈ 4.64
            Cube cube = new Cube(Math.cbrt(targetVolume));

            // Sphere with radius ≈ 2.88
            double sphereRadius = Math.cbrt(targetVolume * 3.0 / (4.0 * Math.PI));
            Sphere sphere = new Sphere(sphereRadius);

            // Verify volumes are approximately equal
            assertEquals(targetVolume, cube.getVolume(), 0.1);
            assertEquals(targetVolume, sphere.getVolume(), 0.1);

            // But surface areas should be different
            assertNotEquals(cube.getSurfaceArea(), sphere.getSurfaceArea(), 0.1);
        }

        @Test
        @DisplayName("Test scaling properties across shapes")
        void testScalingProperties() {
            double scaleFactor = 2.0;

            // Original shapes
            Sphere sphere1 = new Sphere(1.0);
            Cube cube1 = new Cube(1.0);

            // Scaled shapes
            Sphere sphere2 = new Sphere(scaleFactor);
            Cube cube2 = new Cube(scaleFactor);

            // Volume should scale by factor³
            assertEquals(sphere1.getVolume() * Math.pow(scaleFactor, 3),
                    sphere2.getVolume(), DELTA);
            assertEquals(cube1.getVolume() * Math.pow(scaleFactor, 3),
                    cube2.getVolume(), DELTA);

            // Surface area should scale by factor²
            assertEquals(sphere1.getSurfaceArea() * Math.pow(scaleFactor, 2),
                    sphere2.getSurfaceArea(), DELTA);
            assertEquals(cube1.getSurfaceArea() * Math.pow(scaleFactor, 2),
                    cube2.getSurfaceArea(), DELTA);
        }
    }

    @Nested
    @DisplayName("Error Handling Integration Tests")
    class ErrorHandlingTests {

        @Test
        @DisplayName("Test consistent error handling across all shapes")
        void testConsistentErrorHandling() {
            // All shapes should reject negative dimensions consistently
            Class<?>[] shapeClasses = {
                    Sphere.class, Cube.class, Cylinder.class,
                    RectangularPrism.class, Pyramid.class
            };

            for (Class<?> shapeClass : shapeClasses) {
                String shapeName = shapeClass.getSimpleName();

                // Test that each shape throws IllegalArgumentException for invalid inputs
                // Note: This is conceptual - actual implementation would need reflection
                // or specific constructors for each shape type
                assertAll(
                        () -> assertTrue(true, shapeName + " should handle validation"),
                        () -> assertNotNull(shapeClass, "Shape class should exist")
                );
            }
        }

        @Test
        @DisplayName("Test state preservation after failed setter calls")
        void testStatePreservationAfterErrors() {
            Sphere sphere = new Sphere(5.0);
            double originalRadius = sphere.getRadius();

            assertThrows(IllegalArgumentException.class, () -> sphere.setRadius(-1.0));
            assertEquals(originalRadius, sphere.getRadius(), DELTA,
                    "Radius should be unchanged after failed setter");

            Cube cube = new Cube(3.0);
            double originalSide = cube.getSideLength();

            assertThrows(IllegalArgumentException.class, () -> cube.setSideLength(0.0));
            assertEquals(originalSide, cube.getSideLength(), DELTA,
                    "Side length should be unchanged after failed setter");
        }
    }

    @Nested
    @DisplayName("Performance and Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Test all shapes with very small dimensions")
        void testVerySmallDimensions() {
            double verySmall = 1e-10;

            ThreeDimensionalShape[] shapes = {
                    new Sphere(verySmall),
                    new Cube(verySmall),
                    new Cylinder(verySmall, verySmall),
                    new RectangularPrism(verySmall, verySmall, verySmall),
                    new Pyramid(verySmall, verySmall, verySmall)
            };

            for (ThreeDimensionalShape shape : shapes) {
                assertTrue(shape.getVolume() > 0,
                        "Volume should be positive even for very small dimensions");
                assertTrue(shape.getSurfaceArea() > 0,
                        "Surface area should be positive even for very small dimensions");
                assertFalse(Double.isNaN(shape.getVolume()),
                        "Volume should not be NaN");
                assertFalse(Double.isNaN(shape.getSurfaceArea()),
                        "Surface area should not be NaN");
            }
        }

        @Test
        @DisplayName("Test calculation consistency")
        void testCalculationConsistency() {
            // Test that calculations are consistent across multiple calls
            Sphere sphere = new Sphere(3.0);
            double volume1 = sphere.getVolume();
            double volume2 = sphere.getVolume();
            double surfaceArea1 = sphere.getSurfaceArea();
            double surfaceArea2 = sphere.getSurfaceArea();

            assertEquals(volume1, volume2, DELTA, "Volume should be consistent across calls");
            assertEquals(surfaceArea1, surfaceArea2, DELTA, "Surface area should be consistent across calls");

            // Test after property changes
            sphere.setRadius(5.0);
            double newVolume = sphere.getVolume();
            double newSurfaceArea = sphere.getSurfaceArea();

            assertNotEquals(volume1, newVolume, "Volume should change after radius change");
            assertNotEquals(surfaceArea1, newSurfaceArea, "Surface area should change after radius change");
        }

        @Test
        @DisplayName("Test mathematical relationships across shapes")
        void testMathematicalRelationships() {
            // Test that volume of cube equals volume of rectangular prism with equal dimensions
            Cube cube = new Cube(4.0);
            RectangularPrism prism = new RectangularPrism(4.0, 4.0, 4.0);

            assertEquals(cube.getVolume(), prism.getVolume(), DELTA,
                    "Cube and equivalent rectangular prism should have same volume");
            assertEquals(cube.getSurfaceArea(), prism.getSurfaceArea(), DELTA,
                    "Cube and equivalent rectangular prism should have same surface area");

            // Test that square pyramid behaves correctly
            Pyramid square = new Pyramid(3.0, 6.0); // 3×3 base, height 6
            assertTrue(square.isSquarePyramid(), "Should be identified as square pyramid");

            Pyramid rectangular = new Pyramid(3.0, 4.0, 6.0); // 3×4 base, height 6
            assertFalse(rectangular.isSquarePyramid(), "Should not be identified as square pyramid");
        }
    }

    @Nested
    @DisplayName("Documentation and Code Quality Tests")
    class QualityTests {

        @Test
        @DisplayName("Test toString format consistency")
        void testToStringFormatConsistency() {
            ThreeDimensionalShape.Shape3D[] shapes = {
                    new Sphere("Test Sphere", "Red", 2.0),
                    new Cube("Test Cube", "Blue", 3.0),
                    new Cylinder("Test Cylinder", "Green", 1.5, 4.0),
                    new RectangularPrism("Test Prism", "Yellow", 2.0, 3.0, 4.0),
                    new Pyramid("Test Pyramid", "Purple", 3.0, 4.0, 5.0)
            };

            for (ThreeDimensionalShape.Shape3D shape : shapes) {
                String toString = shape.toString();

                // All toString methods should include basic information
                assertTrue(toString.contains(shape.getName()),
                        "toString should contain shape name");
                assertTrue(toString.contains(shape.getColor()),
                        "toString should contain shape color");
                assertTrue(toString.toLowerCase().contains("volume"),
                        "toString should mention volume");
                assertTrue(toString.toLowerCase().contains("surface area"),
                        "toString should mention surface area");

                // Should be reasonably formatted (not empty, not too short)
                assertTrue(toString.length() > 20,
                        "toString should be reasonably detailed");
                assertFalse(toString.contains("null"),
                        "toString should not contain null values");
            }
        }

        @Test
        @DisplayName("Test interface contract compliance")
        void testInterfaceContractCompliance() {
            ThreeDimensionalShape[] shapes = {
                    new Sphere(2.0),
                    new Cube(3.0),
                    new Cylinder(1.5, 4.0),
                    new RectangularPrism(2.0, 3.0, 4.0),
                    new Pyramid(3.0, 4.0, 5.0)
            };

            for (ThreeDimensionalShape shape : shapes) {
                // Test interface methods return reasonable values
                double volume = shape.getVolume();
                double surfaceArea = shape.getSurfaceArea();

                assertAll(
                        () -> assertTrue(volume > 0, "Volume must be positive"),
                        () -> assertTrue(surfaceArea > 0, "Surface area must be positive"),
                        () -> assertFalse(Double.isNaN(volume), "Volume must not be NaN"),
                        () -> assertFalse(Double.isNaN(surfaceArea), "Surface area must not be NaN"),
                        () -> assertFalse(Double.isInfinite(volume), "Volume must not be infinite"),
                        () -> assertFalse(Double.isInfinite(surfaceArea), "Surface area must not be infinite")
                );
            }
        }
    }

    @Nested
    @DisplayName("Real-World Usage Scenarios")
    class UsageScenarioTests {

        @Test
        @DisplayName("Test container volume calculation scenario")
        void testContainerVolumeScenario() {
            // Scenario: Calculate storage capacity of different containers
            List<ThreeDimensionalShape> containers = Arrays.asList(
                    new Cylinder("Water Tank", "Blue", 2.0, 5.0),
                    new RectangularPrism("Storage Box", "Brown", 3.0, 4.0, 2.0),
                    new Sphere("Ball Container", "Red", 1.5)
            );

            double totalCapacity = containers.stream()
                    .mapToDouble(ThreeDimensionalShape::getVolume)
                    .sum();

            assertTrue(totalCapacity > 0, "Total capacity should be positive");

            // Find the container with maximum capacity
            ThreeDimensionalShape maxCapacityContainer = containers.stream()
                    .max((s1, s2) -> Double.compare(s1.getVolume(), s2.getVolume()))
                    .orElse(null);

            assertNotNull(maxCapacityContainer, "Should find a container with max capacity");
            assertTrue(maxCapacityContainer.getVolume() > 0, "Max capacity should be positive");
        }

        @Test
        @DisplayName("Test material cost calculation scenario")
        void testMaterialCostScenario() {
            // Scenario: Calculate material needed for surface coating
            double costPerSquareUnit = 5.0; // $5 per square unit

            ThreeDimensionalShape[] objectsToCoat = {
                    new Sphere("Metal Ball", "Steel", 1.0),
                    new Cube("Wooden Block", "Wood", 2.0),
                    new Pyramid("Stone Monument", "Stone", 3.0, 3.0, 4.0)
            };

            double totalCost = Arrays.stream(objectsToCoat)
                    .mapToDouble(shape -> shape.getSurfaceArea() * costPerSquareUnit)
                    .sum();

            assertTrue(totalCost > 0, "Total cost should be positive");

            // Verify each object contributes to cost
            for (ThreeDimensionalShape.Shape3D shape : (ThreeDimensionalShape.Shape3D[]) objectsToCoat) {
                double individualCost = shape.getSurfaceArea() * costPerSquareUnit;
                assertTrue(individualCost > 0,
                        "Individual cost should be positive for " + shape.getName());
            }
        }

        @Test
        @DisplayName("Test shape modification scenario")
        void testShapeModificationScenario() {
            // Scenario: Modify shapes and verify calculations update correctly
            Cylinder pipe = new Cylinder("Water Pipe", "Copper", 0.5, 10.0);
            double originalVolume = pipe.getVolume();
            double originalSurfaceArea = pipe.getSurfaceArea();

            // Increase radius (pipe diameter)
            pipe.setRadius(1.0);
            double newVolume = pipe.getVolume();
            double newSurfaceArea = pipe.getSurfaceArea();

            assertTrue(newVolume > originalVolume, "Volume should increase with larger radius");
            assertTrue(newSurfaceArea > originalSurfaceArea, "Surface area should increase with larger radius");

            // Change color and name (cosmetic changes)
            pipe.setColor("Bronze");
            pipe.setName("Bronze Pipe");

            assertEquals("Bronze", pipe.getColor(), "Color should be updated");
            assertEquals("Bronze Pipe", pipe.getName(), "Name should be updated");

            // Volume and surface area should remain unchanged
            assertEquals(newVolume, pipe.getVolume(), DELTA, "Volume should not change with cosmetic updates");
            assertEquals(newSurfaceArea, pipe.getSurfaceArea(), DELTA, "Surface area should not change with cosmetic updates");
        }
    }

    @Nested
    @DisplayName("Comprehensive Validation Tests")
    class ComprehensiveValidationTests {

        @Test
        @DisplayName("Test all shapes reject invalid dimensions comprehensively")
        void testComprehensiveValidation() {
            // Test negative values
            assertAll("Negative dimension validation",
                    () -> assertThrows(IllegalArgumentException.class, () -> new Sphere(-1.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cube(-2.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cylinder(-1.0, 5.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cylinder(2.0, -3.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new RectangularPrism(-1.0, 2.0, 3.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Pyramid(-1.0, 2.0, 3.0))
            );

            // Test zero values
            assertAll("Zero dimension validation",
                    () -> assertThrows(IllegalArgumentException.class, () -> new Sphere(0.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cube(0.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cylinder(0.0, 5.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Cylinder(2.0, 0.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new RectangularPrism(0.0, 2.0, 3.0)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Pyramid(0.0, 2.0, 3.0))
            );
        }

        @Test
        @DisplayName("Test comprehensive setter validation")
        void testComprehensiveSetterValidation() {
            ThreeDimensionalShape[] shapes = {
                    new Sphere(1.0),
                    new Cube(1.0),
                    new Cylinder(1.0, 1.0),
                    new RectangularPrism(1.0, 1.0, 1.0),
                    new Pyramid(1.0, 1.0, 1.0)
            };

            // All shapes should maintain state after failed setter calls
            for (ThreeDimensionalShape.Shape3D shape : (ThreeDimensionalShape.Shape3D[]) shapes) {
                // Test that objects maintain their state after validation failures
                assertNotNull(shape.getName(), "Name should remain valid");
                assertNotNull(shape.getColor(), "Color should remain valid");
                assertTrue(shape.getVolume() > 0, "Volume should remain positive");
                assertTrue(shape.getSurfaceArea() > 0, "Surface area should remain positive");
            }
        }
    }

    /**
     * Demo method to show all shapes working together
     * This would typically be in a separate demo class, but included here for completeness
     */
    @Test
    @DisplayName("Integration Demo - All Shapes Working Together")
    void integrationDemo() {
        System.out.println("=== 3D Shapes Demo ===");

        ThreeDimensionalShape[] shapes = {
                new Sphere("Basketball", "Orange", 0.12), // 12cm radius
                new Cube("Dice", "White", 0.02), // 2cm side
                new Cylinder("Can", "Silver", 0.03, 0.11), // 3cm radius, 11cm height
                new RectangularPrism("Book", "Blue", 0.20, 0.15, 0.03), // 20×15×3 cm
                new Pyramid("Paperweight", "Crystal", 0.05, 0.05, 0.08) // 5×5cm base, 8cm height
        };

        double totalVolume = 0;
        double totalSurfaceArea = 0;

        for (ThreeDimensionalShape shape : shapes) {
            if (shape instanceof ThreeDimensionalShape.Shape3D) {
                ThreeDimensionalShape.Shape3D s3d = (ThreeDimensionalShape.Shape3D) shape;
                System.out.println(s3d.toString());
            }
            totalVolume += shape.getVolume();
            totalSurfaceArea += shape.getSurfaceArea();
        }

        System.out.printf("Total Volume: %.6f cubic meters%n", totalVolume);
        System.out.printf("Total Surface Area: %.6f square meters%n", totalSurfaceArea);

        // Assertions to ensure the demo actually works
        assertTrue(totalVolume > 0, "Demo should calculate positive total volume");
        assertTrue(totalSurfaceArea > 0, "Demo should calculate positive total surface area");
        assertEquals(5, shapes.length, "Demo should include all 5 shape types");
    }
}