/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a Cube class that extends Shape3D with sideLength property, proper constructors with validation,
 * implements abstract calculation methods, and overrides toString() with shape-specific formatting."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 * -- I just changed the extended class to match the new interface.
 *
 * Formula Verification:
 * - Volume formula verified against: V = s³
 * - Surface area formula verified against: SA = 6s²
 */

package com.csc205.project2.shapes;

public class Cube extends ThreeDimensionalShape.Shape3D {

    private double sideLength;

    // Default constructor
    public Cube() {
        super("Cube", "White");
        this.sideLength = 1.0;
    }

    // Parameterized constructor with validation
    public Cube(String name, String color, double sideLength) {
        super(name, color);
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        this.sideLength = sideLength;
    }

    // Constructor with just side length
    public Cube(double sideLength) {
        super("Cube", "White");
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        this.sideLength = sideLength;
    }

    // Implement abstract method for surface area calculation
    @Override
    public double calculateSurfaceArea() {
        return 6 * sideLength * sideLength;
    }

    // Implement abstract method for volume calculation
    @Override
    public double calculateVolume() {
        return sideLength * sideLength * sideLength;
    }

    // Getter and setter for side length
    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("Side length must be positive");
        }
        this.sideLength = sideLength;
    }

    // Shape-specific toString method
    @Override
    public String toString() {
        return String.format("Cube: %s (%s) - Side Length: %.2f, Surface Area: %.2f, Volume: %.2f",
                getName(), getColor(), sideLength, getSurfaceArea(), getVolume());
    }
}