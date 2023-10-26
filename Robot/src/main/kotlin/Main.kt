
fun moveRobot(robot: Robot, toX: Int, toY: Int){
    while (robot.x != toX || robot.y != toY) {
        when (robot.direction) {
            Direction.RIGHT -> {
                if (robot.x < toX) {
                    robot.stepForward()
                } else {
                    robot.turnLeft()
                }
            }
            Direction.LEFT -> {
                if (robot.x > toX) {
                    robot.stepForward()
                } else {
                    robot.turnLeft()
                }
            }
            Direction.UP -> {
                if (robot.y < toY) {
                    robot.stepForward()
                } else {
                    robot.turnLeft()
                }
            }
            Direction.DOWN -> {
                if (robot.y > toY) {
                    robot.stepForward()
                } else {
                    robot.turnLeft()
                }
            }
        }
    }

    while (robot.direction != Direction.UP) {
        robot.turnLeft()
    }
}


fun main() {
    val WALL_E = Robot(3, 4, Direction.UP)

    println("* Starting position *")
    println(WALL_E.toString())

    WALL_E.turnLeft()
    println("* Command turnLeft() *")
    println(WALL_E.toString())

    WALL_E.stepForward()
    println("* Command stepForward() *")
    println(WALL_E.toString())

    WALL_E.turnRight()
    println("* Command turnRight() *")
    println(WALL_E.toString())

    WALL_E.stepForward()
    println("* Command stepForward() *")
    println(WALL_E.toString())

    moveRobot(WALL_E, 10, 10)
    println("* Command moveRobot(WALL_E, 10, 10) *")
    println(WALL_E.toString())
}
//fun main() {
//    val r1 = Robot(3, 4, Direction.RIGHT)
//    val r2 = Robot(0, 0, Direction.DOWN)
//    println("${r1.x}, ${r1.y}, ${r1.direction}")
//    r1.stepForward()
//    println("${r2.x}, ${r2.y}, ${r2.direction}")
//    println("${r1.x}, ${r1.y}, ${r1.direction}")
//    println(r1)
//    println(r2)
//}