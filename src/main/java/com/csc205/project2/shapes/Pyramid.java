/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create a Pyramid class that extends Shape3D with base length/width and height properties, proper constructors with validation, implements abstract calculation methods, and overrides toString() with shape-specific formatting."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 * I  changed the extended class to match the new interface.
 * Formula Verification:
 * - Volume formula verified against: V = (1/3) × base_area × height = (1/3) × l × w × h
 * - Surface area formula verified against: SA = base_area + 4 × triangular_face_area
 *   For rectangular base: SA = lw + l×√((w/2)² + h²) + w×√((l/2)² + h²)
 */

package com.csc205.project2.shapes;

public class Pyramid extends ThreeDimensionalShape.Shape3D {

    private double baseLength;
    private double baseWidth;
    private double height;

    // Default constructor
    public Pyramid() {
        super("Pyramid", "White");
        this.baseLength = 1.0;
        this.baseWidth = 1.0;
        this.height = 1.0;
    }

    // Parameterized constructor with validation
    public Pyramid(String name, String color, double baseLength, double baseWidth, double height) {
        super(name, color);
        if (baseLength <= 0) {
            throw new IllegalArgumentException("Base length must be positive");
        }
        if (baseWidth <= 0) {
            throw new IllegalArgumentException("Base width must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.height = height;
    }

    // Constructor with just dimensions
    public Pyramid(double baseLength, double baseWidth, double height) {
        super("Pyramid", "White");
        if (baseLength <= 0) {
            throw new IllegalArgumentException("Base length must be positive");
        }
        if (baseWidth <= 0) {
            throw new IllegalArgumentException("Base width must be positive");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.height = height;
    }

    // Constructor for square pyramid
    public Pyramid(double baseSide, double height) {
        this(baseSide, baseSide, height);
        setName("Square Pyramid");
    }

    // Implement abstract method for surface area calculation
    @Override
    protected double calculateSurfaceArea() {
        // Base area
        double baseArea = baseLength * baseWidth;

        // Calculate slant heights for triangular faces
        double slantHeight1 = Math.sqrt((baseWidth / 2) * (baseWidth / 2) + height * height);
        double slantHeight2 = Math.sqrt((baseLength / 2) * (baseLength / 2) + height * height);

        // Area of triangular faces
        double triangularFaceArea1 = 0.5 * baseLength * slantHeight1; // Two faces with base = baseLength
        double triangularFaceArea2 = 0.5 * baseWidth * slantHeight2;  // Two faces with base = baseWidth

        // Total surface area = base + 2 * each pair of triangular faces
        return baseArea + 2 * triangularFaceArea1 + 2 * triangularFaceArea2;
    }

    // Implement abstract method for volume calculation
    @Override
    protected double calculateVolume() {
        return (1.0 / 3.0) * baseLength * baseWidth * height;
    }

    // Getters and setters
    public double getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(double baseLength) {
        if (baseLength <= 0) {
            throw new IllegalArgumentException("Base length must be positive");
        }
        this.baseLength = baseLength;
    }

    public double getBaseWidth() {
        return baseWidth;
    }

    public void setBaseWidth(double baseWidth) {
        if (baseWidth <= 0) {
            throw new IllegalArgumentException("Base width must be positive");
        }
        this.baseWidth = baseWidth;
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

    // Helper method to check if it's a square pyramid
    public boolean isSquarePyramid() {
        return Math.abs(baseLength - baseWidth) < 0.001; // Using small epsilon for floating point comparison
    }

    // Shape-specific toString method
    @Override
    public String toString() {
        return String.format("Pyramid: %s (%s) - Base: %.2f × %.2f, Height: %.2f, Surface Area: %.2f, Volume: %.2f",
                getName(), getColor(), baseLength, baseWidth, height, getSurfaceArea(), getVolume());
    }
}