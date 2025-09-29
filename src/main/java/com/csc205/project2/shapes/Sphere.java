/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a Sphere class that extends Shape3D with radius property, proper constructors with validation,
 * implements abstract calculation methods, and overrides toString() with shape-specific formatting."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 * I  changed the extended class to match the new interface.
 * Formula Verification:
 * - Volume formula verified against: V = (4/3)πr³
 * - Surface area formula verified against: SA = 4πr²
 */

package com.csc205.project2.shapes;

public class Sphere extends ThreeDimensionalShape.Shape3D {

    private double radius;

    // Default constructor
    public Sphere() {
        super("Sphere", "White");
        this.radius = 1.0;
    }

    // Parameterized constructor with validation
    public Sphere(String name, String color, double radius) {
        super(name, color);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    // Constructor with just radius
    public Sphere(double radius) {
        super("Sphere", "White");
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    // Implement abstract method for surface area calculation
    @Override
    protected double calculateSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    // Implement abstract method for volume calculation
    @Override
    protected double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    // Getter and setter for radius
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    // Shape-specific toString method
    @Override
    public String toString() {
        return String.format("Sphere: %s (%s) - Radius: %.2f, Surface Area: %.2f, Volume: %.2f",
                getName(), getColor(), radius, getSurfaceArea(), getVolume());
    }
}