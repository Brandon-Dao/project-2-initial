/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create comprehensive JUnit 5 test classes for 3D shape classes including basic functionality, calculation accuracy, boundary testing, input validation, and inheritance testing."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Test values verified against mathematical formulas and online calculators
 */

package com.csc205.project2.shapes;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Base test class providing common testing utilities and inheritance tests
 */
public abstract class BaseShapeTest {

    protected static final double DELTA = 0.001; // Tolerance for floating point comparisons
    protected static final double VERY_SMALL_VALUE = 0.0001;
    protected static final double VERY_LARGE_VALUE = 1000000.0;

    /**
     * Abstract method to be implemented by concrete test classes
     * @return A new instance of the shape being tested
     */
    protected abstract ThreeDimensionalShape.Shape3D createShape();

    /**
     * Abstract method to be implemented by concrete test classes
     * @return A new instance with known dimensions for calculation testing
     */
    protected abstract ThreeDimensionalShape.Shape3D createShapeWithKnownValues();

    /**
     * Abstract method to return expected volume for known values
     */
    protected abstract double getExpectedVolume();

    /**
     * Abstract method to return expected surface area for known values
     */
    protected abstract double getExpectedSurfaceArea();

    @Test
    @DisplayName("Test ThreeDimensionalShape interface implementation")
    void testInterfaceImplementation() {
        Shape3D shape = createShape();
        assertInstanceOf(ThreeDimensionalShape.class, shape,
                "Shape should implement ThreeDimensionalShape interface");
    }

    @Test
    @DisplayName("Test polymorphic behavior")
    void testPolymorphicBehavior() {
        ThreeDimensionalShape shape = createShape();

        // Test that interface methods work polymorphically
        assertDoesNotThrow(() -> shape.getVolume(),
                "Should be able to call getVolume() polymorphically");
        assertDoesNotThrow(() -> shape.getSurfaceArea(),
                "Should be able to call getSurfaceArea() polymorphically");

        assertTrue(shape.getVolume() >= 0, "Volume should be non-negative");
        assertTrue(shape.getSurfaceArea() >= 0, "Surface area should be non-negative");
    }

    @Test
    @DisplayName("Test common properties (name and color)")
    void testCommonProperties() {
        ThreeDimensionalShape.Shape3D shape = createShape();

        // Test that name and color are not null
        assertNotNull(shape.getName(), "Name should not be null");
        assertNotNull(shape.getColor(), "Color should not be null");

        // Test setters
        shape.setName("Test Shape");
        shape.setColor("Test Color");

        assertEquals("Test Shape", shape.getName(), "Name should be updated");
        assertEquals("Test Color", shape.getColor(), "Color should be updated");
    }

    @Test
    @DisplayName("Test toString method contains essential information")
    void testToString() {
        ThreeDimensionalShape.Shape3D shape = createShapeWithKnownValues();
        String toString = shape.toString();

        assertNotNull(toString, "toString should not return null");
        assertFalse(toString.trim().isEmpty(), "toString should not be empty");
        assertTrue(toString.contains(shape.getName()), "toString should contain shape name");
        assertTrue(toString.contains(shape.getColor()), "toString should contain shape color");
    }
}