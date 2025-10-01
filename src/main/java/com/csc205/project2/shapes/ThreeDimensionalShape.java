package com.csc205.project2.shapes;
/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: September 27, 2025
 *
 * Original Prompt:
 * "Create an abstract base class Shape3D that implements ThreeDimensionalShape interface with common properties name and color,
 * concrete implementations of getVolume() and getSurfaceArea() that call abstract methods,
 * constructors, toString() method, and getter/setter methods."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 * -- I pulled getSurfaceArea and getVolume to the interface as it was the recomendation of the prompt
 * Formula Verification:
 * - Abstract class delegates formula implementation to concrete classes
 */

public interface ThreeDimensionalShape {
    // Concrete implementation of getSurfaceArea() that delegates to abstract method
    double getSurfaceArea();

    // Concrete implementation of getVolume() that delegates to abstract method
    double getVolume();

    public abstract class Shape3D implements ThreeDimensionalShape {

        // Private fields for common properties
        private String name;
        private String color;

        // Default constructor
        public Shape3D() {
            this.name = "Unknown Shape";
            this.color = "Unknown Color";
        }

        // Parameterized constructor
        public Shape3D(String name, String color) {
            this.name = name;
            this.color = color;
        }

        // Concrete implementation of getSurfaceArea() that delegates to abstract method
        @Override
        public final double getSurfaceArea() {
            return calculateSurfaceArea();
        }

        // Concrete implementation of getVolume() that delegates to abstract method
        @Override
        public final double getVolume() {
            return calculateVolume();
        }

        // Abstract methods that concrete classes must implement
        public abstract double calculateSurfaceArea();

        public abstract double calculateVolume();

        // Getter and setter methods
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // toString method with consistent formatting
        @Override
        public String toString() {
            return String.format("%s (%s) - Surface Area: %.2f, Volume: %.2f",
                    name, color, getSurfaceArea(), getVolume());
        }
    }
}