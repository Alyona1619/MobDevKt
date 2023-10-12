// TODO: дополнить определение класса размерами и позицией
class Square(var x: Int, var y: Int, var width: Int) : Movable, Figure(0), Transforming {
    // TODO: унаследовать от Figure, реализовать area()
    // TODO: реализовать интерфейс Transforming

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    override fun area(): Float {
        return (width * width).toFloat()
    }

    override fun resize(zoom: Int) {
        width *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        val dx = x - centerX
        val dy = y - centerY
        val newDx = if (direction == RotateDirection.Clockwise) dy else -dy
        val newDy = if (direction == RotateDirection.Clockwise) -dx else dx

        x = centerX + newDx
        y = centerY + newDy
    }
}
