package apfff.mygame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ktx.math.div

class Bullet(
  override val pos: Vector2,
  val radius: Float,
  var mass: Float = 1f,
  var drag: Float = 0f,
  var rotation: Float = 0f, //degrees turned per second
  var color: Color = Color.WHITE
): Projectile {
  override val prevPos: Vector2 = pos.cpy()
  override val velocity: Vector2 = Vector2()

  override fun apply(force: Vector2){
    velocity.add(force/mass)
  }

  override fun step(dt: Float) {
    prevPos.set(pos)
    velocity.rotateDeg(rotation * dt)
    velocity.scl(1 - drag * dt)
    pos.apply {
      x += dt * velocity.x
      y += dt * velocity.y
    }
  }

  companion object {
    fun mousePos(radius: Float, mass: Float = 1f, drag: Float = 0f, rotation: Float = 0f, color: Color = Color.RED ) =
      Bullet(
        Vector2(
          Gdx.input.x.toFloat(),
          Gdx.graphics.height - Gdx.input.y.toFloat()
        ),
        radius,
        mass,
        drag,
        rotation,
        color
      )
  }
}