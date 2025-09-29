/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a RectangularPrism class that extends Shape3D with length, width, and height properties,
 * proper constructors with validation, implements abstract calculation methods,
 * and overrides toString() with shape-specific formatting."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 * I  changed the extended class to match the new interface.
 * Formula Verification:
 * - Volume formula verified against: V = l × w × h
 * - Surface area formula verified against: SA = 2(lw + lh + wh)
 */

package com.csc205.project2.shapes;

public class RectangularPrism extends ThreeDimensionalShape.Shape3D {

    private double length;
    private double width;
    private double height;

    // Default constructor
    public RectangularPrism() {
        super("Rectangular Prism", "White");
        this.length = 1.0;
        this.width = 1.0;
        this.height = 1.0;
    }

    // Parameterized constructor with validation
    public RectangularPrism(String name, String color, double length, double width, double height) {
        super(name, color);
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Constructor with just dimensions
    public RectangularPrism(double length, double width, double height) {
        super("Rectangular Prism", "White");
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Implement abstract method for surface area calculation
    @Override
    protected double calculateSurfaceArea() {
        // Surface area = 2(lw + lh + wh)
        return 2 * (length * width + length * height + width * height);
    }

    // Implement abstract method for volume calculation
    @Override
    protected double calculateVolume() {
        return length * width * height;
    }

    // Getters and setters
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    // Shape-specific toString method
    @Override
    public String toString() {
        return String.format("Rectangular Prism: %s (%s) - Length: %.2f, Width: %.2f, Height: %.2f, Surface Area: %.2f, Volume: %.2f",
                getName(), getColor(), length, width, height, getSurfaceArea(), getVolume());
    }
}