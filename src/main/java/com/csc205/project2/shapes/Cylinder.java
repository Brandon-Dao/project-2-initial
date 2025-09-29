/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a Cylinder class that extends Shape3D with radius and height properties,
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
 * - Volume formula verified against: V = πr²h
 * - Surface area formula verified against: SA = 2πr² + 2πrh (two circular bases + lateral surface)
 */

package com.csc205.project2.shapes;

public class Cylinder extends ThreeDimensionalShape.Shape3D {

    private double radius;
    private double height;

    // Default constructor
    public Cylinder() {
        super("Cylinder", "White");
        this.radius = 1.0;
        this.height = 1.0;
    }

    // Parameterized constructor with validation
    public Cylinder(String name, String color, double radius, double height) {
        super(name, color);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.radius = radius;
        this.height = height;
    }

    // Constructor with just radius and height
    public Cylinder(double radius, double height) {
        super("Cylinder", "White");
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.radius = radius;
        this.height = height;
    }

    // Implement abstract method for surface area calculation
    @Override
    protected double calculateSurfaceArea() {
        // Surface area = 2πr² (two circular bases) + 2πrh (lateral surface)
        return 2 * Math.PI * radius * radius + 2 * Math.PI * radius * height;
    }

    // Implement abstract method for volume calculation
    @Override
    protected double calculateVolume() {
        return Math.PI * radius * radius * height;
    }

    // Getters and setters
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
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
        return String.format("Cylinder: %s (%s) - Radius: %.2f, Height: %.2f, Surface Area: %.2f, Volume: %.2f",
                getName(), getColor(), radius, height, getSurfaceArea(), getVolume());
    }
}