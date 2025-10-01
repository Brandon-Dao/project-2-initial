package com.csc205.project2;
/**
 * AI GENERATION DOCUMENTATION
 * ===========================
 * AI Tool Used: Claude Sonnet 4
 * Generation Date: October 1, 2025
 *
 * Original Prompt:
 * "Create a sophisticated ShapeDriver.java that demonstrates polymorphism with arrays/lists,
 * comparative analysis, interactive features, performance timing, and formatted output."
 *
 * Follow-up Prompts (if any):
 * N/A
 *
 * Manual Modifications:
 * - [List any changes you made to the AI output]
 * - [Explain why changes were necessary]
 *
 * Formula Verification:
 * - Driver class uses shape implementations for all calculations
 */

import com.csc205.project2.shapes.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Advanced 3D Shape Analysis System
 * Demonstrates polymorphism, comparative analysis, and interactive features
 */
public class ShapeDriver {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<ThreeDimensionalShape.Shape3D> shapes = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcome();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createPredefinedShapes();
                case 2 -> createCustomShape();
                case 3 -> displayAllShapes();
                case 4 -> performComparativeAnalysis();
                case 5 -> performAdvancedAnalysis();
                case 6 -> performPerformanceTiming();
                case 7 -> demonstratePolymorphism();
                case 8 -> clearAllShapes();
                case 9 -> {
                    running = false;
                    System.out.println("\nThank you for using the 3D Shape Analysis System!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Display welcome banner
     */
    private static void displayWelcome() {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║       3D SHAPE ANALYSIS SYSTEM                        ║");
        System.out.println("║       Advanced Geometric Calculations                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Display main menu
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("                    MAIN MENU");
        System.out.println("=".repeat(55));
        System.out.println("1. Create Predefined Sample Shapes");
        System.out.println("2. Create Custom Shape (Interactive)");
        System.out.println("3. Display All Shapes");
        System.out.println("4. Perform Comparative Analysis");
        System.out.println("5. Perform Advanced Analysis");
        System.out.println("6. Performance Timing Test");
        System.out.println("7. Demonstrate Polymorphism");
        System.out.println("8. Clear All Shapes");
        System.out.println("9. Exit");
        System.out.println("=".repeat(55));
    }

    /**
     * Create predefined sample shapes for demonstration
     */
    private static void createPredefinedShapes() {
        System.out.println("\n>>> Creating Predefined Sample Shapes...");

        shapes.add(new Sphere("Red Ball", "Red", 5.0));
        shapes.add(new Cube("Blue Box", "Blue", 4.0));
        shapes.add(new Cylinder("Green Can", "Green", 3.0, 7.0));
        shapes.add(new RectangularPrism("Yellow Container", "Yellow", 6.0, 4.0, 3.0));
        shapes.add(new Pyramid("Purple Monument", "Purple", 5.0, 5.0, 8.0));

        System.out.println("✓ Successfully created 5 sample shapes!");
        System.out.println("  - Sphere: Red Ball");
        System.out.println("  - Cube: Blue Box");
        System.out.println("  - Cylinder: Green Can");
        System.out.println("  - Rectangular Prism: Yellow Container");
        System.out.println("  - Pyramid: Purple Monument");
    }

    /**
     * Interactive shape creation
     */
    private static void createCustomShape() {
        System.out.println("\n>>> Create Custom Shape");
        System.out.println("Available shapes:");
        System.out.println("1. Sphere");
        System.out.println("2. Cube");
        System.out.println("3. Cylinder");
        System.out.println("4. Rectangular Prism");
        System.out.println("5. Pyramid");

        int shapeType = getIntInput("Select shape type (1-5): ");

        System.out.print("Enter shape name: ");
        String name = scanner.nextLine();

        System.out.print("Enter shape color: ");
        String color = scanner.nextLine();

        try {
            ThreeDimensionalShape.Shape3D shape = createShapeByType(shapeType, name, color);
            shapes.add(shape);
            System.out.println("✓ Successfully created: " + shape.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Create shape based on type with user input
     */
    private static ThreeDimensionalShape.Shape3D createShapeByType(int type, String name, String color) {
        return switch (type) {
            case 1 -> {
                double radius = getDoubleInput("Enter radius: ");
                yield new Sphere(name, color, radius);
            }
            case 2 -> {
                double side = getDoubleInput("Enter side length: ");
                yield new Cube(name, color, side);
            }
            case 3 -> {
                double radius = getDoubleInput("Enter radius: ");
                double height = getDoubleInput("Enter height: ");
                yield new Cylinder(name, color, radius, height);
            }
            case 4 -> {
                double length = getDoubleInput("Enter length: ");
                double width = getDoubleInput("Enter width: ");
                double height = getDoubleInput("Enter height: ");
                yield new RectangularPrism(name, color, length, width, height);
            }
            case 5 -> {
                double baseLength = getDoubleInput("Enter base length: ");
                double baseWidth = getDoubleInput("Enter base width: ");
                double height = getDoubleInput("Enter height: ");
                yield new Pyramid(name, color, baseLength, baseWidth, height);
            }
            default -> throw new IllegalArgumentException("Invalid shape type");
        };
    }

    /**
     * Display all shapes with detailed information
     */
    private static void displayAllShapes() {
        if (shapes.isEmpty()) {
            System.out.println("\n⚠ No shapes created yet. Please create some shapes first.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                        CREATED SHAPES");
        System.out.println("=".repeat(70));

        for (int i = 0; i < shapes.size(); i++) {
            ThreeDimensionalShape.Shape3D shape = shapes.get(i);
            System.out.printf("\n%d. %s {name='%s', color='%s'}%n",
                    i + 1, shape.getClass().getSimpleName(),
                    shape.getName(), shape.getColor());

            // Display shape-specific properties
            displayShapeProperties(shape);

            System.out.printf("   ├─ Surface Area: %.2f square units%n", shape.getSurfaceArea());
            System.out.printf("   └─ Volume: %.2f cubic units%n", shape.getVolume());
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.printf("Total shapes: %d%n", shapes.size());
    }

    /**
     * Display shape-specific properties
     */
    private static void displayShapeProperties(ThreeDimensionalShape.Shape3D shape) {
        if (shape instanceof Sphere sphere) {
            System.out.printf("   ├─ Radius: %.2f%n", sphere.getRadius());
        } else if (shape instanceof Cube cube) {
            System.out.printf("   ├─ Side Length: %.2f%n", cube.getSideLength());
        } else if (shape instanceof Cylinder cylinder) {
            System.out.printf("   ├─ Radius: %.2f%n", cylinder.getRadius());
            System.out.printf("   ├─ Height: %.2f%n", cylinder.getHeight());
        } else if (shape instanceof RectangularPrism prism) {
            System.out.printf("   ├─ Length: %.2f%n", prism.getLength());
            System.out.printf("   ├─ Width: %.2f%n", prism.getWidth());
            System.out.printf("   ├─ Height: %.2f%n", prism.getHeight());
        } else if (shape instanceof Pyramid pyramid) {
            System.out.printf("   ├─ Base Length: %.2f%n", pyramid.getBaseLength());
            System.out.printf("   ├─ Base Width: %.2f%n", pyramid.getBaseWidth());
            System.out.printf("   ├─ Height: %.2f%n", pyramid.getHeight());
            if (pyramid.isSquarePyramid()) {
                System.out.println("   ├─ Type: Square Pyramid");
            }
        }
    }

    /**
     * Perform comparative analysis on all shapes
     */
    private static void performComparativeAnalysis() {
        if (shapes.isEmpty()) {
            System.out.println("\n⚠ No shapes available for analysis.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                     COMPARATIVE ANALYSIS");
        System.out.println("=".repeat(70));

        // Find shapes with maximum values
        ThreeDimensionalShape.Shape3D maxVolume = shapes.stream()
                .max(Comparator.comparingDouble(ThreeDimensionalShape::getVolume))
                .orElse(null);

        ThreeDimensionalShape.Shape3D maxSurfaceArea = shapes.stream()
                .max(Comparator.comparingDouble(ThreeDimensionalShape::getSurfaceArea))
                .orElse(null);

        ThreeDimensionalShape.Shape3D minVolume = shapes.stream()
                .min(Comparator.comparingDouble(ThreeDimensionalShape::getVolume))
                .orElse(null);

        ThreeDimensionalShape.Shape3D minSurfaceArea = shapes.stream()
                .min(Comparator.comparingDouble(ThreeDimensionalShape::getSurfaceArea))
                .orElse(null);

        // Calculate efficiency (Volume / Surface Area ratio)
        ThreeDimensionalShape.Shape3D mostEfficient = shapes.stream()
                .max(Comparator.comparingDouble(s -> s.getVolume() / s.getSurfaceArea()))
                .orElse(null);

        ThreeDimensionalShape.Shape3D leastEfficient = shapes.stream()
                .min(Comparator.comparingDouble(s -> s.getVolume() / s.getSurfaceArea()))
                .orElse(null);

        // Display results
        System.out.println("\n📊 VOLUME ANALYSIS:");
        if (maxVolume != null) {
            System.out.printf("   • Largest Volume: %s (%.2f cubic units)%n",
                    maxVolume.getName(), maxVolume.getVolume());
        }
        if (minVolume != null) {
            System.out.printf("   • Smallest Volume: %s (%.2f cubic units)%n",
                    minVolume.getName(), minVolume.getVolume());
        }

        System.out.println("\n📐 SURFACE AREA ANALYSIS:");
        if (maxSurfaceArea != null) {
            System.out.printf("   • Largest Surface Area: %s (%.2f square units)%n",
                    maxSurfaceArea.getName(), maxSurfaceArea.getSurfaceArea());
        }
        if (minSurfaceArea != null) {
            System.out.printf("   • Smallest Surface Area: %s (%.2f square units)%n",
                    minSurfaceArea.getName(), minSurfaceArea.getSurfaceArea());
        }

        System.out.println("\n⚡ EFFICIENCY ANALYSIS (Volume/Surface Area Ratio):");
        if (mostEfficient != null) {
            double efficiency = mostEfficient.getVolume() / mostEfficient.getSurfaceArea();
            System.out.printf("   • Most Efficient: %s (%.4f)%n",
                    mostEfficient.getName(), efficiency);
        }
        if (leastEfficient != null) {
            double efficiency = leastEfficient.getVolume() / leastEfficient.getSurfaceArea();
            System.out.printf("   • Least Efficient: %s (%.4f)%n",
                    leastEfficient.getName(), efficiency);
        }

        // Calculate totals
        double totalVolume = shapes.stream()
                .mapToDouble(ThreeDimensionalShape::getVolume)
                .sum();
        double totalSurfaceArea = shapes.stream()
                .mapToDouble(ThreeDimensionalShape::getSurfaceArea)
                .sum();

        System.out.println("\n📈 AGGREGATE STATISTICS:");
        System.out.printf("   • Total Volume: %.2f cubic units%n", totalVolume);
        System.out.printf("   • Total Surface Area: %.2f square units%n", totalSurfaceArea);
        System.out.printf("   • Average Volume: %.2f cubic units%n",
                totalVolume / shapes.size());
        System.out.printf("   • Average Surface Area: %.2f square units%n",
                totalSurfaceArea / shapes.size());

        System.out.println("\n" + "=".repeat(70));
    }

    /**
     * Perform advanced analysis with grouping by shape type
     */
    private static void performAdvancedAnalysis() {
        if (shapes.isEmpty()) {
            System.out.println("\n⚠ No shapes available for analysis.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                     ADVANCED ANALYSIS");
        System.out.println("=".repeat(70));

        // Group shapes by type
        Map<String, List<ThreeDimensionalShape.Shape3D>> shapesByType = shapes.stream()
                .collect(Collectors.groupingBy(s -> s.getClass().getSimpleName()));

        System.out.println("\n🔍 SHAPE TYPE DISTRIBUTION:");
        shapesByType.forEach((type, list) -> {
            System.out.printf("   • %s: %d shape(s)%n", type, list.size());
        });

        System.out.println("\n📊 STATISTICS BY SHAPE TYPE:");
        shapesByType.forEach((type, list) -> {
            double avgVolume = list.stream()
                    .mapToDouble(ThreeDimensionalShape::getVolume)
                    .average()
                    .orElse(0.0);
            double avgSurfaceArea = list.stream()
                    .mapToDouble(ThreeDimensionalShape::getSurfaceArea)
                    .average()
                    .orElse(0.0);

            System.out.printf("\n   %s:%n", type);
            System.out.printf("      ├─ Count: %d%n", list.size());
            System.out.printf("      ├─ Avg Volume: %.2f cubic units%n", avgVolume);
            System.out.printf("      └─ Avg Surface Area: %.2f square units%n", avgSurfaceArea);
        });

        // Find shape with best volume-to-surface-area ratio by type
        System.out.println("\n⭐ MOST EFFICIENT SHAPE BY TYPE:");
        shapesByType.forEach((type, list) -> {
            ThreeDimensionalShape.Shape3D best = list.stream()
                    .max(Comparator.comparingDouble(s -> s.getVolume() / s.getSurfaceArea()))
                    .orElse(null);
            if (best != null) {
                double ratio = best.getVolume() / best.getSurfaceArea();
                System.out.printf("   • %s: %s (ratio: %.4f)%n", type, best.getName(), ratio);
            }
        });

        System.out.println("\n" + "=".repeat(70));
    }

    /**
     * Perform performance timing tests
     */
    private static void performPerformanceTiming() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                   PERFORMANCE TIMING TEST");
        System.out.println("=".repeat(70));

        int iterations = 1_000_000;
        System.out.printf("\nPerforming %,d calculations per shape type...%n", iterations);

        // Test each shape type
        testShapePerformance("Sphere", new Sphere(5.0), iterations);
        testShapePerformance("Cube", new Cube(4.0), iterations);
        testShapePerformance("Cylinder", new Cylinder(3.0, 7.0), iterations);
        testShapePerformance("Rectangular Prism", new RectangularPrism(6.0, 4.0, 3.0), iterations);
        testShapePerformance("Pyramid", new Pyramid(5.0, 5.0, 8.0), iterations);

        System.out.println("\n" + "=".repeat(70));
    }

    private static void testShapePerformance(String cylinder, Cylinder cylinder1, int iterations) {
    }

    private static void testShapePerformance(String pyramid, Pyramid pyramid1, int iterations) {
    }

    private static void testShapePerformance(String rectangularPrism, RectangularPrism rectangularPrism1, int iterations) {
    }

    private static void testShapePerformance(String cube, Cube cube1, int iterations) {
    }

    private static void testShapePerformance(String sphere, Sphere sphere1, int iterations) {
    }

    /**
     * Test performance of a specific shape
     */
    private static void testShapePerformance(String shapeName, ThreeDimensionalShape shape, Cylinder sphere, int iterations) {
        // Warm up

        for (int i = 0; i < 1000; i++) {
            shape.getVolume();
            shape.getSurfaceArea();
        }

        // Test volume calculation
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            shape.getVolume();
        }
        long volumeTime = System.nanoTime() - startTime;

        // Test surface area calculation
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            shape.getSurfaceArea();
        }
        long surfaceAreaTime = System.nanoTime() - startTime;

        System.out.printf("\n%s:%n", shapeName);
        System.out.printf("   ├─ Volume Calc: %.2f ms (%.2f ns/call)%n",
                volumeTime / 1_000_000.0, volumeTime / (double) iterations);
        System.out.printf("   └─ Surface Area Calc: %.2f ms (%.2f ns/call)%n",
                surfaceAreaTime / 1_000_000.0, surfaceAreaTime / (double) iterations);
    }

    /**
     * Demonstrate polymorphism
     */
    private static void demonstratePolymorphism() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                  POLYMORPHISM DEMONSTRATION");
        System.out.println("=".repeat(70));

        System.out.println("\nCreating shapes using polymorphism...");

        // Array of ThreeDimensionalShape references
        ThreeDimensionalShape[] shapeArray = {
                new Sphere("Poly Sphere", "Red", 3.0),
                new Cube("Poly Cube", "Blue", 3.0),
                new Cylinder("Poly Cylinder", "Green", 2.0, 5.0)
        };

        System.out.println("\n✓ Created array of ThreeDimensionalShape references");
        System.out.println("  Each reference points to a different concrete shape type");

        System.out.println("\n📋 Calling interface methods polymorphically:");
        for (int i = 0; i < shapeArray.length; i++) {
            ThreeDimensionalShape shape = shapeArray[i];
            System.out.printf("\n%d. Type: %s%n", i + 1, shape.getClass().getSimpleName());
            System.out.printf("   ├─ getVolume() → %.2f%n", shape.getVolume());
            System.out.printf("   └─ getSurfaceArea() → %.2f%n", shape.getSurfaceArea());
        }

        // Demonstrate with List and Shape3D
        System.out.println("\n📋 Using List<Shape3D> for enhanced polymorphism:");
        List<ThreeDimensionalShape> polyList = Arrays.asList(
                new RectangularPrism("Poly Prism", "Yellow", 4.0, 3.0, 2.0),
                new Pyramid("Poly Pyramid", "Purple", 4.0, 4.0, 6.0)
        );

        polyList.forEach(shape -> {
            System.out.printf("\n• %s (%s):%n", shape.getName(), shape.getClass().getSimpleName());
            System.out.printf("  ├─ Color: %s%n", shape.getColor());
            System.out.printf("  ├─ Volume: %.2f%n", shape.getVolume());
            System.out.printf("  └─ Surface Area: %.2f%n", shape.getSurfaceArea());
        });

        System.out.println("\n💡 Key Polymorphism Concepts Demonstrated:");
        System.out.println("   • Interface references (ThreeDimensionalShape)");
        System.out.println("   • Abstract class references (Shape3D)");
        System.out.println("   • Method calls resolved at runtime (dynamic binding)");
        System.out.println("   • Collections holding different shape types");

        System.out.println("\n" + "=".repeat(70));
    }

    /**
     * Clear all shapes
     */
    private static void clearAllShapes() {
        if (shapes.isEmpty()) {
            System.out.println("\n⚠ No shapes to clear.");
            return;
        }

        int count = shapes.size();
        shapes.clear();
        System.out.printf("\n✓ Cleared %d shape(s).%n", count);
    }

    /**
     * Get integer input with validation
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Get double input with validation
     */
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("✗ Value must be positive. Please try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            }
        }
    }
}