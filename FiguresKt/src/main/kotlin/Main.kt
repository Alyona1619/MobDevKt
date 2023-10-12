fun main() {

//    // интерфейсы удобно использовать в коллекциях
//    val figures: Array<Movable>
//    val movable: Movable = Rect(0,0,1,1)
//    movable.move(1,1)
//    // переменной базового класса
//    val f: Figure = Rect(0,0,1,1)
//    val f2: Figure = Circle(0, 0, 5)
//
//    println(f.area())
//    println(f2.area())

    val rect = Rect(4, 3, 4, 2)
    val circle = Circle(6, 4, 5)
    val square = Square(2, 2, 4)

    println("Figures:")
    println("Rect (${rect.x};${rect.y}) (width: ${rect.width}; height: ${rect.height})")
    println("Circle (${circle.x};${circle.y}) (radius: ${circle.radius})")
    println("Square (${square.x};${square.y}) (side: ${square.width})")


    rect.rotate(RotateDirection.Clockwise, 3, -3)
    circle.rotate(RotateDirection.Clockwise, 3, -3)
    square.rotate(RotateDirection.Clockwise, 3, -3)

    println("\nFigures after rotating clockwise:")
    println("Rect (${rect.x};${rect.y})")
    println("Circle (${circle.x};${circle.y})")
    println("Square (${square.x};${square.y})")

    rect.rotate(RotateDirection.CounterClockwise, 3, -3)
    circle.rotate(RotateDirection.CounterClockwise, 3, -3)
    square.rotate(RotateDirection.CounterClockwise, 3, -3)

    println("\nFigures after rotating counter clockwise:")
    println("Rect (${rect.x};${rect.y})")
    println("Circle (${circle.x};${circle.y})")
    println("Square (${square.x};${square.y})")

    rect.move(10, 10)
    circle.move(10, 10)
    square.move(10, 10)

    println("\nFigures after moving (10;10):")
    println("Rect (${rect.x};${rect.y})")
    println("Circle (${circle.x};${circle.y})")
    println("Square (${square.x};${square.y})")

    println()
    println("Areas:")
    println("Rect Area: ${rect.area()} (width: ${rect.width}; height: ${rect.height})")
    println("Circle Area: ${circle.area()} (radius: ${circle.radius})")
    println("Square Area: ${square.area()} (side: ${square.width})")

    rect.resize(2)
    circle.resize(2)
    square.resize(2)

    println("\nAreas after resizing (zoom: 2):")
    println("Rect Area: ${rect.area()} (width: ${rect.width}; height: ${rect.height})" )
    println("Circle Area: ${circle.area()} (radius: ${circle.radius})")
    println("Square Area: ${square.area()} (side: ${square.width})")

}